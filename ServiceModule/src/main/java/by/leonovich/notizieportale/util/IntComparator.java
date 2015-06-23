package by.leonovich.notizieportale.util;

import java.util.Comparator;

/**
 * Created by alexanderleonovich on 27.05.15.
 */
public class IntComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        return (o1>o2 ? ServiceConstants.Const.ONE : (o1==o2 ? ServiceConstants.Const.ZERO : ServiceConstants.Const.MINUS_ONE));
    }
}