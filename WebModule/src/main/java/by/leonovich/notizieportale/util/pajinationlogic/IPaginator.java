package by.leonovich.notizieportale.util.pajinationlogic;

import java.util.List;

/**
 * Created by alexanderleonovich on 28.05.15.
 */
public interface IPaginator {

    /** use this method for receiving list of pagination numbers. This numbers is used in view */
    List<Integer> getList(long numberOfPages, int pageNumber, int newsesPackageSize);
}
