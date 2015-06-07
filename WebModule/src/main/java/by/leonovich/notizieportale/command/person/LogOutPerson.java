package by.leonovich.notizieportale.command.person;

import by.leonovich.notizieportale.command.IActionCommand;
import by.leonovich.notizieportale.services.IPersonService;
import by.leonovich.notizieportale.services.PersonService;
import by.leonovich.notizieportale.util.URLManager;
import by.leonovich.notizieportale.util.SessionRequestContent;
import by.leonovich.notizieportale.util.UrlEnum;

/**
 * Created by alexanderleonovich on 18.04.15.
 * Command class for log out autorized user and invalidate session
 */
public class LogOutPerson implements IActionCommand {

    private IPersonService personService;

    public LogOutPerson() {
        personService = PersonService.getInstance();
    }

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_INDEX.getUrlCode());
        personService.logOutPerson();
        sessionRequestContent.invalidateSession();
        return page;
    }
}
