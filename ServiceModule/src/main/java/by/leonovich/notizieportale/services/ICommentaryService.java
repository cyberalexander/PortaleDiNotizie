package by.leonovich.notizieportale.services;

import by.leonovich.notizieportale.domain.Category;
import by.leonovich.notizieportale.domain.Commentary;

import java.util.List;

/**
 * Created by alexanderleonovich on 03.05.15.
 */
public interface ICommentaryService {

    Commentary getCommentaryByPK(Long PK);

    List<Commentary> getCommentaries();

    List<Commentary> getCommentariesByAuthorId(Long PK);

    List<Commentary> getCommentariesByNewsId(Long PK);

    Commentary saveCommentary(Commentary commentary);

    Commentary updateCommentary(Commentary commentary);

    Commentary deleteCommentary(Commentary commentary);

    void removeCommentary(Commentary commentary);

}
