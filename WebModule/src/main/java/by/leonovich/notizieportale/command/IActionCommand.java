package by.leonovich.notizieportale.command;

import by.leonovich.notizieportale.util.SessionRequestContent;

/**
 * Created by alexanderleonovich on 18.04.15.
 * Interface for control intercommunication of users with JDBC
 */
public interface IActionCommand {

    String execute(SessionRequestContent sessionRequestContent);
}
