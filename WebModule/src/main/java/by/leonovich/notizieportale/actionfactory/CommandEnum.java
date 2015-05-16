package by.leonovich.notizieportale.actionfactory;


import by.leonovich.notizieportale.command.*;
import by.leonovich.notizieportale.command.commentcommand.AddWriteCommentCommand;
import by.leonovich.notizieportale.command.commentcommand.DeleteCommentCommand;
import by.leonovich.notizieportale.command.commentcommand.EditWriteCommentCommand;
import by.leonovich.notizieportale.command.newscommand.*;
import by.leonovich.notizieportale.command.usercommand.*;

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
    ADDUSER {
        {
            this.command = new AddUserCommand();
        }
    },
    GOINCABINET {
        {
            this.command = new GoInCabinetCommand();
        }
    },
    ADDWRITECOMMENT {
        {
            this.command = new AddWriteCommentCommand();
        }
    },
    EDITWRITECOMMENT{
        {
            this.command = new EditWriteCommentCommand();
        }
    },
    DELETECOMMENT{
        {
            this.command = new DeleteCommentCommand();
        }
    },
    EDITUSER{
        {
            this.command = new EditUserCommand();
        }
    },
    EDITWRITEUSER{
        {
            this.command = new EditWriteUserCommand();
        }
    };

    IActionCommand command;
    public IActionCommand getCurrentCommand() {
        return command;
    }
}