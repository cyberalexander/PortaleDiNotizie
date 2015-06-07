package by.leonovich.notizieportale.command.person;

import by.leonovich.notizieportale.command.IActionCommand;
import by.leonovich.notizieportale.util.SessionRequestContent;
import by.leonovich.notizieportale.util.URLManager;
import by.leonovich.notizieportale.util.UrlEnum;

/**
 * Created by alexanderleonovich on 02.05.15.
 */
public class Registration implements IActionCommand {
    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_REGISTRATION_1.getUrlCode());
        return page;
    }
}
