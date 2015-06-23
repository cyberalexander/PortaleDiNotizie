package by.leonovich.notizieportale.services;


import by.leonovich.notizieportale.exception.ServiceLayerException;

/**
 * Created by alexanderleonovich on 03.06.15.
 */
public interface IService<T> {

    Long save(T t)  throws ServiceLayerException;

    Long saveOrUpdate(T t)  throws ServiceLayerException;

    T update(T t) throws ServiceLayerException;

    Long delete(T t) throws ServiceLayerException;

    void remove(T t) throws ServiceLayerException;

    T get(Long pK) throws ServiceLayerException;

    T load(Long pK) throws ServiceLayerException;
}
