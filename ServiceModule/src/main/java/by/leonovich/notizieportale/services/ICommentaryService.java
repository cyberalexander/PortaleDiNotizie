package by.leonovich.notizieportale.services;

import by.leonovich.notizieportale.domain.Category;
import by.leonovich.notizieportale.domain.Commentary;

import java.util.List;

/**
 * Created by alexanderleonovich on 03.05.15.
 */
public interface ICommentaryService extends IService<Commentary> {

    List<Commentary> getCommentaries();

    List<Commentary> getCommentariesByAuthorId(Long PK);

    List<Commentary> getCommentariesByNewsId(Long PK);

    Long save(Commentary commentary, Long newsId, Long personId);

}
