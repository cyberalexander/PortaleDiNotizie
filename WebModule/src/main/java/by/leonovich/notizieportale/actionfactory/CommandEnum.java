package by.leonovich.notizieportale.actionfactory;


import by.leonovich.notizieportale.command.*;
import by.leonovich.notizieportale.command.category.AddCategory;
import by.leonovich.notizieportale.command.category.AddWriteCategory;
import by.leonovich.notizieportale.command.commentary.AddWriteCommentary;
import by.leonovich.notizieportale.command.commentary.DeleteCommentary;
import by.leonovich.notizieportale.command.commentary.EditWriteCommentary;
import by.leonovich.notizieportale.command.news.*;
import by.leonovich.notizieportale.command.person.*;

/**
 * Created by alexanderleonovich on 18.04.15.
 * COMMAND ENUMERATION
 */
public enum CommandEnum {


    LOGIN {
        {
            this.command = new LoginPerson();
        }
    },
    LOGOUT {
        {
            this.command = new LogOutPerson();
        }
    },
    SHOWNEWS {
        {
            this.command = new ShowNews();
        }
    },
    ADDNEWS {
        {
            this.command = new AddNews();
        }
    },
    ADDWRITENEWS {
        {
            this.command = new AddWriteNews();
        }
    },
    ADDCATEGORY {
        {
            this.command = new AddCategory();
        }
    },
    ADDWRITECATEGORY {
        {
            this.command = new AddWriteCategory();
        }
    },
    DELETENEWS {
        {
            this.command = new DeleteNews();
        }
    },
    EDITNEWS {
        {
            this.command = new EditNews();
        }
    },
    EDITWRITENEWS {
        {
            this.command = new EditWriteNews();
        }
    },
    REGISTRATION {
        {
            this.command = new Registration();
        }
    },
    ADDPERSONFIRSTSTEP {
        {
            this.command = new RegisterFirstStep();
        }
    },
    ADDPERSONSECONDSTEP {
        {
            this.command = new RegisterSecondStep();
        }
    },
    REGISTERBACK {
        {
            this.command = new RegisterBack();
        }
    },
    GOINCABINET {
        {
            this.command = new GoInCabinet();
        }
    },
    ADDWRITECOMMENTARY {
        {
            this.command = new AddWriteCommentary();
        }
    },
    EDITWRITECOMMENTARY{
        {
            this.command = new EditWriteCommentary();
        }
    },
    DELETECOMMENTARY{
        {
            this.command = new DeleteCommentary();
        }
    },
    EDITPERSON{
        {
            this.command = new EditPerson();
        }
    },
    EDITWRITEPERSON{
        {
            this.command = new EditWritePerson();
        }
    };

    IActionCommand command;
    public IActionCommand getCurrentCommand() {
        return command;
    }
}
