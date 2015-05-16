package by.leonovich.notizieportale.command.usercommand;

import by.leonovich.notizieportale.command.IActionCommand;
import by.leonovich.notizieportale.util.SessionRequestContent;
import by.leonovich.notizieportale.util.URLManager;
import by.leonovich.notizieportale.util.UrlEnum;

/**
 * Created by alexanderleonovich on 03.05.15.
 * Class for forward response on user-cabinet page
 */
public class GoInCabinetCommand implements IActionCommand {

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_USERCABINET.getUrlCode());
        return page;
    }
}
