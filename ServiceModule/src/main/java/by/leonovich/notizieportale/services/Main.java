package by.leonovich.notizieportale.services;

import by.leonovich.notizieportale.services.exception.ServiceLayerException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by alexanderleonovich on 17.06.15.
 */
public class Main {

    private static ClassPathXmlApplicationContext ac;
    private static PersonService personService;


    public static void main(String[] args) {
        ac = new ClassPathXmlApplicationContext(new String[]{"beans-services.xml"});
        personService = (PersonService) ac.getBean("personService");

        try {
            System.out.println(personService.get((long) 1));
        } catch (ServiceLayerException serviceLayerException) {
            serviceLayerException.printStackTrace();
        }
    }
}
