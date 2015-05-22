package by.leonovich.notizieportale.services;

import by.leonovich.notizieportale.domain.Category;
import by.leonovich.notizieportale.domain.Commentary;
import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.domain.util.StatusEnum;

import java.util.List;

/**
 * Created by alexanderleonovich on 20.05.15.
 */
public class MainTest {

    private static ICategoryService categoryService;
    private static IPersonService personService;
    private static ICommentaryService commentaryService;


    public static void main(String[] args) {
        categoryService = CategoryService.getInstance();
//        List<Category> categories = categoryService.getCategories();
//        for (Category element : categories) {
//            System.out.println(element.toString());
//        }

        Category category = new Category("Test", StatusEnum.SAVED);
        categoryService.saveCategory(category);


//        personService = PersonService.getInstance();
//        List<Person> persons = personService.getPersons();
//        for (Person element : persons) {
//            System.out.println(element.toString());
//        }

        /*commentaryService = CommentaryService.getInstance();
        List<Commentary> comments = commentaryService.getCommentaries();
        for (Commentary element : comments) {
            System.out.println(element.toString() + "---" + element.getPerson().getName());
        }*/


    }
}
