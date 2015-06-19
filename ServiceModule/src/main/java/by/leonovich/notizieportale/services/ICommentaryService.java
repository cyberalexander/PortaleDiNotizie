package by.leonovich.notizieportale.services;

import by.leonovich.notizieportale.domain.Category;
import by.leonovich.notizieportale.domain.Commentary;
import by.leonovich.notizieportale.services.util.exception.ServiceExcpetion;

import java.util.List;

/**
 * Created by alexanderleonovich on 03.05.15.
 */
public interface ICommentaryService extends IService<Commentary> {

    List<Commentary> getCommentaries() throws ServiceExcpetion;

    List<Commentary> getCommentariesByAuthorId(Long PK) throws ServiceExcpetion;

    List<Commentary> getCommentariesByNewsId(Long PK) throws ServiceExcpetion;

    Long save(Commentary commentary, Long newsId, Long personId) throws ServiceExcpetion;

}
