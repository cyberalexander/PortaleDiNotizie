package by.leonovich.notizieportale.services;


import by.leonovich.notizieportale.dao.PersonDao;
import by.leonovich.notizieportale.domain.Person;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import static com.mysql.jdbc.StringUtils.isNullOrEmpty;

/**
 * Created by alexanderleonovich on 19.06.15.
 */

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private static final Logger logger = Logger.getLogger(UserDetailServiceImpl.class);

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
            if (person != null) {
            roles.add(new SimpleGrantedAuthority(person.getPersonDetail().getRole().name()));
            UserDetails userDetails =
                    new org.springframework.security.core.userdetails.User(person.getPersonDetail().getEmail(),
                            person.getPersonDetail().getPassword(),
                            roles);
                logger.info("User is authorizated " + userDetails.getUsername() + ", user-authorities " + userDetails.getAuthorities());
            return userDetails;
            }else {
                throw new UsernameNotFoundException("error to check user. Email is empty");
            }
        }
        return null;
    }

}
