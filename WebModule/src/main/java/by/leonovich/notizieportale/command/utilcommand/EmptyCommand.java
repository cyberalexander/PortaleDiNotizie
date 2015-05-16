package by.leonovich.notizieportale.command.utilcommand;

import by.leonovich.notizieportale.command.IActionCommand;
import by.leonovich.notizieportale.util.URLManager;
import by.leonovich.notizieportale.util.MessageManager;
import by.leonovich.notizieportale.util.SessionRequestContent;

/**
 * Created by alexanderleonovich on 18.04.15.
 * Command class for redirect user on login page? if  he will get direct access to the controller
 */
public class EmptyCommand implements IActionCommand {

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        // in the event of an error or a direct appeal to the controller redirects to the login page
        sessionRequestContent.setRequestAttribute("directAccessIsDenied", MessageManager.getInstance().getProperty("message.directaccess"));
        String page = URLManager.getInstance().getProperty("path.page.login");
        return page;
    }
}
