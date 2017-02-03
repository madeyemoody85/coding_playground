package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Implement an iterator to flatten a 2d vector.
 * [
 *      [1,2]
 *      [3]
 *      [4,5,6]
 * ]
 * By calling next repeatedly until hasNext returns false,
 * the order of elements returned by next should be: [1,2,3,4,5,6].
 *
 * Created by nachiketlondhe on 1/16/17.
 */
public class Vector2D implements Iterator<Integer> {

    List<List<Integer>> vector;
    int listIndex = 0;
    int elementIndex = 0;

    public Vector2D(List<List<Integer>> vec2d) {
        vector = vec2d;
    }

    @Override
    public boolean hasNext() {
        while (listIndex < vector.size()) {
            if (elementIndex < vector.get(listIndex).size()) {
                return true;
            } else {
                listIndex++;
                elementIndex = 0;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        if (elementIndex < vector.get(listIndex).size()) {
            return vector.get(listIndex).get(elementIndex++);
        } else {
            elementIndex = 0;
            listIndex += listIndex + 1;
            return vector.get(listIndex).get(elementIndex);
        }
    }

    public static void main(String args[]) {
        List<List<Integer>> myVector = new ArrayList<>();

        myVector.add(new ArrayList<Integer>(Arrays.asList(1, 2)));
        myVector.add(new ArrayList<Integer>(Arrays.asList(3)));
        myVector.add(new ArrayList<Integer>(Arrays.asList(4, 5, 6)));

        Vector2D solution = new Vector2D(myVector);

        while(solution.hasNext()) {
            System.out.println(solution.next());
        }
    }
}
