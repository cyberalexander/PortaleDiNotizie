package by.leonovich.notizieportale.controller;

import by.leonovich.notizieportale.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by alexanderleonovich on 20.06.15.
 */
@Controller
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;
}
