package gradle.swagger.docs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class Application
{
    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    public WebMvcConfigurer webMvcConfigurer()
    {
        return new WebMvcConfigurerAdapter()
        {
            @Override
            public void addViewControllers(final ViewControllerRegistry registry)
            {

                // redirect / to swagger page
                registry.addViewController("/").setViewName(
                        "redirect:/swagger-ui.html");
                registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
                super.addViewControllers(registry);
            }
        };
    }
}
