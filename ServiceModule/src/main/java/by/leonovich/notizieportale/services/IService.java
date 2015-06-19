package by.leonovich.notizieportale.services;


import by.leonovich.notizieportale.services.util.exception.ServiceExcpetion;

/**
 * Created by alexanderleonovich on 03.06.15.
 */
public interface IService<T> {

    Long save(T t)  throws ServiceExcpetion;

    T update(T t) throws ServiceExcpetion;

    T delete(T t) throws ServiceExcpetion;

    void remove(T t) throws ServiceExcpetion;

    T get(Long pK) throws ServiceExcpetion;

    T load(Long pK) throws ServiceExcpetion;
}
