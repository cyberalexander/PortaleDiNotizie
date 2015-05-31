package by.leonovich.notizieportale.actionfactory;


import by.leonovich.notizieportale.command.*;
import by.leonovich.notizieportale.command.commentcommand.AddWriteCommentCommand;
import by.leonovich.notizieportale.command.commentcommand.DeleteCommentCommand;
import by.leonovich.notizieportale.command.commentcommand.EditWriteCommentCommand;
import by.leonovich.notizieportale.command.newscommand.*;
import by.leonovich.notizieportale.command.personcommand.*;

/**
 * Created by alexanderleonovich on 18.04.15.
 * COMMAND ENUMERATION
 */
public enum CommandEnum {


    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogOutCommand();
        }
    },
    SHOWNEWS {
        {
            this.command = new ShowNewsCommand();
        }
    },
    ADDNEWS {
        {
            this.command = new AddNewsCommand();
        }
    },
    ADDWRITENEWS {
        {
            this.command = new AddWriteNewsCommand();
        }
    },
    DELETENEWS {
        {
            this.command = new DeleteNewsCommand();
        }
    },
    EDITNEWS {
        {
            this.command = new EditNewsCommand();
        }
    },
    EDITWRITENEWS {
        {
            this.command = new EditWriteNewsCommand();
        }
    },
    REGISTRATION {
        {
            this.command = new RegistrationCommand();
        }
    },
    ADDPERSON {
        {
            this.command = new AddPersonCommand();
        }
    },
    GOINCABINET {
        {
            this.command = new GoInCabinetCommand();
        }
    },
    ADDWRITECOMMENTARY {
        {
            this.command = new AddWriteCommentCommand();
        }
    },
    EDITWRITECOMMENTARY{
        {
            this.command = new EditWriteCommentCommand();
        }
    },
    DELETECOMMENTARY{
        {
            this.command = new DeleteCommentCommand();
        }
    },
    EDITPERSON{
        {
            this.command = new EditPersonCommand();
        }
    },
    EDITWRITEPERSON{
        {
            this.command = new EditWritePersonCommand();
        }
    };

    IActionCommand command;
    public IActionCommand getCurrentCommand() {
        return command;
    }
}
