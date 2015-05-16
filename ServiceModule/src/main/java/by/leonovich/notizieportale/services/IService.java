package by.leonovich.notizieportale.services;

import by.leonovich.notizieportale.domain.Identified;
import by.leonovich.notizieportale.exception.PersistException;

import java.io.Serializable;
import java.util.List;

/**
 * Created by alexanderleonovich on 29.04.15.
 */
public interface IService<T extends Identified<PK>, PK extends Serializable> {

    /** Создает новую запись, соответствующую объекту object */
    public T persistEntity(T object)  throws PersistException;

    /** Возвращает объект соответствующий записи с первичным ключом key или null */
    public T getByPK(PK key) throws PersistException;

    /** Сохраняет состояние объекта group в базе данных */
    public void update(T object) throws PersistException;

    /** Удаляет запись об объекте из базы данных */
    public void delete(T object) throws PersistException;

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    public List<T> getAll() throws PersistException;

    /** Возвращает список объектов соответствующих записям в базе данных, где присутствует поле с определнным значением */
    public List<T> getListByStringCretery(String parameter, String value) throws PersistException;

    /** Возвращает объект, соответствующий записи в базе данных, где присутствует поле с определнным значением */
    public T getByStringCretery(String parameter, String value) throws PersistException;

}
