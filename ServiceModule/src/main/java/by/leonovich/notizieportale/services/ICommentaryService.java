package by.leonovich.notizieportale.services;

import by.leonovich.notizieportale.domain.Commentary;
import by.leonovich.notizieportale.exception.ServiceLayerException;

import java.util.List;

/**
 * Created by alexanderleonovich on 03.05.15.
 */
public interface ICommentaryService extends IService<Commentary> {

    List<Commentary> getCommentaries() throws ServiceLayerException;

    List<Commentary> getCommentariesByAuthorId(Long PK) throws ServiceLayerException;

    List<Commentary> getCommentariesByNewsId(Long PK) throws ServiceLayerException;

    Long save(Commentary commentary, Long newsId, String login) throws ServiceLayerException;

}
