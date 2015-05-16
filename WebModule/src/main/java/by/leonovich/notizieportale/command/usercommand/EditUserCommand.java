package by.leonovich.notizieportale.command.usercommand;

import by.leonovich.notizieportale.command.IActionCommand;
import by.leonovich.notizieportale.util.MessageManager;
import by.leonovich.notizieportale.util.SessionRequestContent;
import by.leonovich.notizieportale.util.URLManager;
import by.leonovich.notizieportale.util.UrlEnum;

/**
 * Created by alexanderleonovich on 10.05.15.
 */
public class EditUserCommand implements IActionCommand {
    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page = URLManager.getInstance().getProperty(UrlEnum.EDIT_USER_INFO.getUrlCode());
        return page;
    }
}
