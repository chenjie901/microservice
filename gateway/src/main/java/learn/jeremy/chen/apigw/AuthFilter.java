package learn.jeremy.chen.apigw;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class AuthFilter implements GatewayFilter, Ordered {

    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println(exchange.getRequest().getPath());
        return chain.filter(exchange);
    }

    public int getOrder() {
        return 0;
    }
}
