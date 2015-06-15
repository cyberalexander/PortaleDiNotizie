package by.leonovich.notizieportale.services;


/**
 * Created by alexanderleonovich on 03.06.15.
 */
public interface IService<T> {

    Long save(T t);

    T update(T t);

    T delete(T t);

    void remove(T t);

    T get(Long pK);

    T load(Long pK);
}
