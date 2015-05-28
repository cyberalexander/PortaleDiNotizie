package by.leonovich.notizieportale.util.pajinationlogic;

import java.util.Comparator;

/**
 * Created by alexanderleonovich on 27.05.15.
 */
public class IntComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        return (o1>o2 ? 1 : (o1==o2 ? 0 : -1));
    }
}