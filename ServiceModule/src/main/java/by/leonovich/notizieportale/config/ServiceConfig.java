package by.leonovich.notizieportale.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by alexanderleonovich on 20.06.15.
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true, mode = AdviceMode.PROXY)
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan("by.leonovich.notizieportale")
@Import({DaoConfig.class})
public class ServiceConfig{

    @Autowired
    HibernateTransactionManager transactionManager;

}
