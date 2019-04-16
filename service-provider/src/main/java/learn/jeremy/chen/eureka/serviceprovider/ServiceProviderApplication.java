package learn.jeremy.chen.eureka.serviceprovider;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
public class ServiceProviderApplication {

    public static void main(String[] args) throws LifecycleException {
//        Tomcat tomcat = new Tomcat();
//        tomcat.setPort(8701);
//        Connector connector = new Connector();
//        connector.setSecure(false);
//        connector.setPort(8701);
//        tomcat.setConnector(connector);
//        Context context = tomcat.addContext("",null);
//        tomcat.addServlet(context, "myservlet", new MyServlet());
//
//        context.addServletMappingDecoded("/mysevlet/**", "myservlet");
//
//        tomcat.start();
        SpringApplication.run(ServiceProviderApplication.class, args);
    }
}
