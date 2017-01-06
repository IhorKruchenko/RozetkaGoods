package libs;

import java.util.ArrayList;

import static org.junit.Assert.fail;

/**
 * Created by IgorKruchenko on 6.01.2017.
 */
public class ActionStep {

    //checking that the element is from 'firstList' to 'secondList'
    public static void matchArray(ArrayList secondList, ArrayList firstList) {
        //Collections.reverse(secondList);
        ArrayList isNotContain = new ArrayList();
        for (int i = 0; i < firstList.size(); i++ ) {
            if (!secondList.contains(firstList.get(i))) {
                isNotContain.add(firstList.get(i));
            }
        }
        if (isNotContain.size() >= 1) {
            System.out.println("Goods " + isNotContain + "is not contains in Viewed products");
            fail();
        }
    }
}
