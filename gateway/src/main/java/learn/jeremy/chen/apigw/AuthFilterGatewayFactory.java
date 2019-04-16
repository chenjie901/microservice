package learn.jeremy.chen.apigw;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class AuthFilterGatewayFactory implements GatewayFilterFactory<AuthFilterGatewayFactory.Config> {
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            System.out.println("gateway filter name "+ name());
            return chain.filter(exchange);
        };
    }

    @Override
    public String name() {
        return "authFilter";
    }

    @Override
    public Config newConfig() {
        return new Config(true);
    }

    public static class Config {

        private boolean enabled;

        public Config(boolean enabled) {
            this.enabled = enabled;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

    }
}
