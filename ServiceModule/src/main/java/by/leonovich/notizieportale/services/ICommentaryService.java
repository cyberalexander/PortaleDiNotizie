package by.leonovich.notizieportale.services;

import by.leonovich.notizieportale.domain.Commentary;

import java.util.List;

/**
 * Created by alexanderleonovich on 03.05.15.
 */
public interface ICommentaryService {
    List<Commentary> getCommentsByNewsIdorAuthorId(String nameOfColum ,Integer id);

    void saveComment(Commentary comment);

    Commentary getCommentById(int comment_id);

    void updateCommentary(Commentary comment);

    void deleteCommentary(Commentary commentary);
}
