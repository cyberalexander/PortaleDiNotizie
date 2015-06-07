package by.leonovich.notizieportale.actionfactory;

import by.leonovich.notizieportale.command.EmptyCommand;
import by.leonovich.notizieportale.command.IActionCommand;
import by.leonovich.notizieportale.util.MessageManager;
import by.leonovich.notizieportale.util.SessionRequestContent;
import org.apache.log4j.Logger;


/**
 * Created by alexanderleonovich on 18.04.15.
 */
public class ActionFactory {

    private static final Logger logger = Logger.getLogger(ActionFactory.class);


    public IActionCommand defineCommand(SessionRequestContent sesReqContent) {

            // 1.1 - Create a link through the object to the object interferon EmptyCommand
        // (class processing option when comes request c empty value attribute command)
        IActionCommand current = new EmptyCommand();

        // 1.2 - get name of command from request
        String action = sesReqContent.getParameter("command");
        if (action == null || action.isEmpty()) {
            // если команда не задана в текущем запросе
            return current;
        }
        // 1.3 - get object of class, who matches command
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
        // 1.4 - assign a new value link Interfom one of the classes in the command and I return back to the servlet
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            sesReqContent.setRequestAttribute("wrongAction", action + MessageManager.getInstance().getProperty("message.wrongaction"));
        }
        logger.info("COMMAND from request is - " + action + "; CURRENT CLASS FOR EXECUTE OPERATION - " + current.getClass());
        return current;
    }
}
