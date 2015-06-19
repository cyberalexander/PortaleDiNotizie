package by.leonovich.notizieportale.config;

import by.leonovich.notizieportale.services.IPersonService;
import by.leonovich.notizieportale.services.PersonService;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 * Created by alexanderleonovich on 19.06.15.
 */
@Configuration
@EnableWebMvc
//@ComponentScan({"by.leonovich.notizieportale.dao", "by.leonovich.notizieportale.services", "by.leonovich.notizieportale.controller"})
@ComponentScan("by.leonovich.notizieportale")
@ImportResource({"classpath*:beans-services.xml", "classpath*:beans-dao.xml"})
@Import({ SecurityConfig.class })
public class WebAppConfig extends WebMvcConfigurerAdapter {

    // Позволяет видеть все ресурсы в папке pages, такие как картинки, стили и т.п.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/WEB-INF/view/**").addResourceLocations("/WEB-INF/view/");
    }

    // а этот бин инициализирует View нашего проекта
    // точно это же мы делали в mvc-dispatcher-servlet.xml
    @Bean
    public InternalResourceViewResolver setupViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/view/");
        resolver.setSuffix(".jspx");
        resolver.setViewClass(JstlView.class);

        return resolver;
    }

}
