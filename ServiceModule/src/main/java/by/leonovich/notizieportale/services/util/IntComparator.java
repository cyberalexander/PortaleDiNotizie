package by.leonovich.notizieportale.services.util;

import java.util.Comparator;

import static by.leonovich.notizieportale.services.util.ServiceConstants.Const.MINUS_ONE;
import static by.leonovich.notizieportale.services.util.ServiceConstants.Const.ONE;
import static by.leonovich.notizieportale.services.util.ServiceConstants.Const.ZERO;

/**
 * Created by alexanderleonovich on 27.05.15.
 */
public class IntComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        return (o1>o2 ? ONE : (o1==o2 ? ZERO : MINUS_ONE));
    }
}