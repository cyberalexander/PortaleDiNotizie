package by.leonovich.notizieportale.dao;

import by.leonovich.notizieportale.domain.Commentary;
import by.leonovich.notizieportale.exception.PersistException;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by alexanderleonovich on 09.06.15.
 */
public interface ICommentaryDao extends IGenericDao<Commentary> {

    List<Commentary> getByNewsPK(Long pK, Session session) throws PersistException;

    List<Commentary> getByPersonPK(Long pK, Session session) throws PersistException;
}
