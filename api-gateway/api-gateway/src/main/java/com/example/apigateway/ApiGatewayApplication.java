package com.example.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p
						.path("/get")
						.filters(f -> f.addRequestHeader("Hello", "World"))
						.uri("http://httpbin.org:80")
				)
				.route(p -> p
						.path("/converter/from/*/to/*/**")
						.filters(fs -> fs.rewritePath("/converter/from/(?<from>.\\S+)/to/(?<to>.\\S+)/(?<quantity>\\d+)",
								"/converter/from/${from}/to/${to}/${quantity}"))
						.uri("http://localhost:8100/")
				)
				.route(p -> p
						.path("/converter-feign/from/*/to/*/**")
						.filters(fs -> fs.rewritePath("/converter-feign/from/(?<from>.\\S+)/to/(?<to>.\\S+)/(?<quantity>\\d+)",
								"/converter-feign/from/${from}/to/${to}/${quantity}"))
						.uri("http://localhost:8100/")
				)
				.route(p -> p
						.path("/currency-exchange/from/*/to/**")
						.filters(fs -> fs.rewritePath("/currency-exchange/from/(?<from>.\\S+)/to/(?<to>\\d+)",
								"/currency-exchange/from/${from}/to/${to}"))
						.uri("http://localhost:8000/")
				)
				.build();
	}

}
