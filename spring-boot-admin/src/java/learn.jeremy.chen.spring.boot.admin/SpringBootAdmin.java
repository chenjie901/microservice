package learn.jeremy.chen.spring.boot.admin;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.security.cert.X509Certificate;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAdminServer
@Configuration
public class SpringBootAdmin {

    public static void main(String[] args) {
//        SSLUtilities.trustAllHostnames();
//        SSLUtilities.trustAllHttpsCertificates();
        TrustAllCertificates.install();
        SpringApplication.run(SpringBootAdmin.class, args);
    }


//    @Bean
    public RestTemplateBuilder restTemplateBuilder()
    throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContextBuilder
                .create()
                .loadTrustMaterial(new TrustSelfSignedStrategy())
                .build();

        // we can optionally disable hostname verification.
        // if you don't want to further weaken the security, you don't have to include this.
        HostnameVerifier allowAllHosts = new NoopHostnameVerifier();

        // create an SSL Socket Factory to use the SSLContext with the trust self signed certificate strategy
        // and allow all hosts verifier.
        SSLConnectionSocketFactory connectionFactory = new SSLConnectionSocketFactory(sslContext, allowAllHosts);

        // finally create the HttpClient using HttpClient factory methods and assign the ssl socket factory


        CloseableHttpClient httpClient =  HttpClients
                .custom()
                .setSSLSocketFactory(connectionFactory)
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory();

        requestFactory.setHttpClient(httpClient);
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        restTemplateBuilder.requestFactory(requestFactory);

        return restTemplateBuilder;
    }
}
