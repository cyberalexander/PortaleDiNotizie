package by.leonovich.notizieportale.config;

import by.leonovich.notizieportale.services.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by alexanderleonovich on 19.06.15.
 */

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailServiceImpl userDetailServiceImpl;

    // регистрируем нашу реализацию UserDetailsService
    // а также PasswordEncoder для приведения пароля в формат SHA1
    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .userDetailsService(userDetailServiceImpl)
                .passwordEncoder(getMd5PasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // включаем защиту от CSRF атак
        http.csrf()
                .disable()
                        // указываем правила запросов
                        // по которым будет определятся доступ к ресурсам и остальным данным
                .authorizeRequests()
                .antMatchers("/resources*", "*").permitAll()
                .anyRequest().permitAll()
                .and();

        http.formLogin()
                // указываем страницу с формой логина
                .loginPage("/login.do")
                        // указываем action с формы логина
                .loginProcessingUrl("/j_spring_security_check")
                        // указываем URL при неудачном логине
                .failureUrl("/loginfailed.do")
                        // Указываем параметры логина и пароля с формы логина
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                        /*.defaultSuccessUrl("/welcome.do", true)*/
                        // даем доступ к форме логина всем
                .permitAll();

        http.logout()
                // разрешаем делать логаут всем
                .permitAll()
                        // указываем URL логаута
                .logoutUrl("/logout.do")
                        // указываем URL при удачном логауте
                .logoutSuccessUrl("/index.jsp")
                        // делаем не валидной текущую сессию
                .invalidateHttpSession(true);

    }

    // Указываем Spring контейнеру, что надо инициализировать <b></b>ShaPasswordEncoder
    // Это можно вынести в WebAppConfig, но для понимаемости оставил тут
    @Bean
    public Md5PasswordEncoder getMd5PasswordEncoder() {
        return new Md5PasswordEncoder();
    }

}
