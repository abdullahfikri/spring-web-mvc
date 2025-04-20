package dev.mfikri.web;

import dev.mfikri.web.interceptor.SessionInterceptor;
import dev.mfikri.web.resolver.PartnerArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class MyWebConfig implements WebMvcConfigurer {
    private final SessionInterceptor sessionInterceptor;
    private final PartnerArgumentResolver partnerArgumentResolver;


    public MyWebConfig(SessionInterceptor sessionInterceptor, PartnerArgumentResolver partnerArgumentResolver) {
        this.sessionInterceptor = sessionInterceptor;
        this.partnerArgumentResolver = partnerArgumentResolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor).addPathPatterns("/user/*");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(partnerArgumentResolver);
    }
}
