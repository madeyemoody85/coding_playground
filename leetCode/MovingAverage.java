package leetCode;

import java.util.LinkedList;

/**
 * Created by nachiketlondhe on 2/20/17.
 */
public class MovingAverage {
    LinkedList<Integer> store;
    double size;
    double runningSum;

    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        this.size = size;
        this.store = new LinkedList();
    }

    public double next(int val) {
        if (this.store.size() < this.size) {
            this.store.add(val);
            this.runningSum += val;
            return (this.runningSum/this.store.size());
        } else {
            int popped = this.store.pollFirst();
            this.runningSum -= popped;
            this.store.add(val);
            this.runningSum += val;
            return (this.runningSum/this.size);
        }
    }

    public static void main(String args []) {

        MovingAverage m = new MovingAverage(5);
        System.out.println(m.next(12009));
        System.out.println(m.next(1965));
        System.out.println(m.next(-940));
        System.out.println(m.next(-8516));
        System.out.println(m.next(-16446));
        System.out.println(m.next(7870));
        System.out.println(m.next(25545));
        System.out.println(m.next(-21028));
        System.out.println(m.next(-18430));
        System.out.println(m.next(-23464));
    }
}
