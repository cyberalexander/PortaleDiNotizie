package by.leonovich.notizieportale.dao;

import by.leonovich.notizieportale.domain.Commentary;
import by.leonovich.notizieportale.exception.PersistException;

import java.util.List;

/**
 * Created by alexanderleonovich on 09.06.15.
 */
public interface ICommentaryDao extends IGenericDao<Commentary> {

    List<Commentary> getByNewsPK(Long pK) throws PersistException;

    List<Commentary> getByPersonId(Long pK) throws PersistException;
}
