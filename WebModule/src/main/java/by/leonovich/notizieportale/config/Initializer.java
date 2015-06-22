package by.leonovich.notizieportale.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

/**
 * Created by alexanderleonovich on 19.06.15.
 */
@ComponentScan("by.leonovich.notizieportale")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ImportResource({"classpath*:DaoConfig.java", "classpath*:ServiceConfig.java"})
public class Initializer implements WebApplicationInitializer {

    // Указываем имя нашему Servlet Dispatcher для мапинга
    private static final String DISPATCHER_SERVLET_NAME = "dispatcher";

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        // Регистрируем в контексте конфигурационный класс, который мы создадим ниже
        ctx.register(WebAppConfig.class);
        ctx.register(SecurityConfig.class);
        servletContext.addListener(new ContextLoaderListener(ctx));

        ctx.setServletContext(servletContext);

        Dynamic servlet = servletContext.addServlet(DISPATCHER_SERVLET_NAME, new DispatcherServlet(ctx));
        servlet.addMapping("*.do");
        servlet.setLoadOnStartup(1);
    }
}
