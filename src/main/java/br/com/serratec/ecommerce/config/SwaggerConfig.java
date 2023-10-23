package br.com.serratec.ecommerce.config;

import javax.servlet.http.HttpServletRequest;

import org.springdoc.core.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import feign.RequestInterceptor;
import feign.RequestTemplate;


@Configuration
public class SwaggerConfig {

    @Bean
    public BearerTokenRequestInterceptor bearerTokenRequestInterceptor() {
        return new BearerTokenRequestInterceptor();
    }

    static class BearerTokenRequestInterceptor implements RequestInterceptor {
        @Override
        public void apply(RequestTemplate template) {
            // Obtém o token JWT do contexto da requisição
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                String token = request.getHeader("Authorization");
                if (token != null && token.startsWith("Bearer ")) {
                    template.header("Authorization", token);
                }
            }
        }
    }

    static {
        SpringDocUtils.getConfig().addRequestWrapperToIgnore(ServletRequestAttributes.class);
    }
}
