package by.leonovich.notizieportale.services;


import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.domain.enums.RoleEnum;
import by.leonovich.notizieportale.services.util.exception.ServiceExcpetion;
import by.leonovich.notizieportale.util.exception.PersistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by alexanderleonovich on 19.06.15.
 */

import java.util.HashSet;
import java.util.Set;

import static com.mysql.jdbc.StringUtils.isNullOrEmpty;

@Service
public class PersonDetailServiceImpl implements UserDetailsService {

    @Autowired
    private PersonService personService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (!(isNullOrEmpty(email))) {
            // с помощью нашего сервиса UserService получаем User
            Person person = null;
                try {
                    person = personService.getByEmail(email);
                } catch (ServiceExcpetion serviceExcpetion) {
                    serviceExcpetion.printStackTrace();
                }
            // указываем роли для этого пользователя
            Set<GrantedAuthority> roles = new HashSet();
            roles.add(new SimpleGrantedAuthority(RoleEnum.USER.name()));
            // на основании полученныйх даных формируем объект UserDetails
            // который позволит проверить введеный пользователем логин и пароль
            // и уже потом аутентифицировать пользователя
            System.out.println("---------------------------------------------------------------");
            if (person != null) {
            UserDetails userDetails =
                    new org.springframework.security.core.userdetails.User(person.getPersonDetail().getEmail(),
                            person.getPersonDetail().getPassword(),
                            roles);
            return userDetails;
            }
        }
        return null;
    }

}
