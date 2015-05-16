package by.leonovich.notizieportale.command.usercommand;

import by.leonovich.notizieportale.command.IActionCommand;
import by.leonovich.notizieportale.util.URLManager;
import by.leonovich.notizieportale.util.SessionRequestContent;
import by.leonovich.notizieportale.util.UrlEnum;

/**
 * Created by alexanderleonovich on 18.04.15.
 * Command class for log out autorized user and invalidate session
 */
public class LogOutCommand implements IActionCommand {

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_INDEX.getUrlCode());
        sessionRequestContent.invalidateSession();
        return page;
    }
}
