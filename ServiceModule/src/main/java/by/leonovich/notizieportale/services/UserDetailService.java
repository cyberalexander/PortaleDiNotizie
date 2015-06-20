package by.leonovich.notizieportale.services;


import by.leonovich.notizieportale.dao.PersonDao;
import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.domain.enums.RoleEnum;
import by.leonovich.notizieportale.services.util.exception.ServiceExcpetion;
import by.leonovich.notizieportale.util.exception.PersistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by alexanderleonovich on 19.06.15.
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.mysql.jdbc.StringUtils.isNullOrEmpty;

@Service("userDetailsService")
public class UserDetailService implements UserDetailsService {

    @Autowired
    private PersonDao personDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        if (!(isNullOrEmpty(email))) {
            // с помощью нашего сервиса UserService получаем User
            Person person = personDao.getByEmail(email);
            // указываем роли для этого пользователя
            Set<GrantedAuthority> roles = new HashSet();
            // на основании полученныйх даных формируем объект UserDetails
            // который позволит проверить введеный пользователем логин и пароль
            // и уже потом аутентифицировать пользователя
            System.out.println("---------------------------------------------------------------");
            if (person != null) {
            roles.add(new SimpleGrantedAuthority(person.getPersonDetail().getRole().name()));
            UserDetails userDetails =
                    new org.springframework.security.core.userdetails.User(person.getPersonDetail().getEmail(),
                            person.getPersonDetail().getPassword(),
                            roles);
                System.out.println(userDetails.getAuthorities());
                System.out.println(userDetails.getPassword());
                System.out.println(userDetails.getUsername());

            return userDetails;
            }else {
                throw new UsernameNotFoundException("pisec");
            }
        }
        return null;
    }

}
