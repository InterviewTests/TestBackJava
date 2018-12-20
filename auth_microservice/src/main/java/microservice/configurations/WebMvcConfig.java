package microservice.configurations;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import microservice.interceptors.JWTInterceptor;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private JWTInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(interceptor).addPathPatterns("/**");
    }

}
