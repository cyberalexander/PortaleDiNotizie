package by.leonovich.notizieportale.util.pajinationlogic;

import static by.leonovich.notizieportale.util.WebConstants.Const;

import by.leonovich.notizieportale.util.WebConstants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by alexanderleonovich on 27.05.15.
 */
public class Paginator implements IPaginator {

    private int size(long numberOfPages, int pageSize) {
        return (int)Math.ceil((numberOfPages-1)/pageSize)+1;
    }

    /**
     * use this method for receiving list of pagination numbers.
     * This numbers are used in view
     * @param numberOfPages total count (from cretery) of pages in database
     * @param pageNumber number of page what user click
     * @param newsesPackageSize
     * @return
     */
    public List<Integer> getList(long numberOfPages, int pageNumber, int newsesPackageSize) {
        //**bigSize - number of all pageLists
        int bigSize = size(numberOfPages, newsesPackageSize);
        List<Integer> list = new ArrayList<Integer>(Const.FIVE);
        if (pageNumber < Const.ONE){
            pageNumber = Const.ONE;
        }
        if (pageNumber > bigSize) {
            pageNumber=bigSize;
        }
        list.add(pageNumber);

        int size = bigSize;
        if (size > Const.FIVE) {
            size = Const.FIVE;
        }
        //** every turn of cycle step will be increased by 1
        int step = Const.ONE;

//         pagination number will be written
//         if it is more then 0 and less then bigSize
        for (int i = Const.ZERO;; i++, step++) {
            int elem1 = pageNumber - step;
            if(elem1 >= Const.ONE) {
                list.add(elem1);
            }
            if (list.size() >= size){
                break;
            }
            int elem2 = pageNumber+step;
            if(elem2<=bigSize) {
                list.add(elem2);
            }
            if (list.size()>=size) break;
        }
        Collections.sort(list, new IntComparator());

        return list;
    }
}
