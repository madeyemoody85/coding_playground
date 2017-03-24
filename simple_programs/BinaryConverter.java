package simple_programs;

/**
 * Class to do simple binary conversions
 * 1. binary to decimal
 * 2. decimal to binary
 * Created by nachiketlondhe on 2/18/17.
 */
public class BinaryConverter {

    public static void main(String args[]) {

        BinaryConverter solution = new BinaryConverter();

        solution.binaryToDecimal(101);

        solution.decimalToBinary(4);
    }

    /**
     * 0100
     */
    public int binaryToDecimal(int binary) {
        int power = 0;
        int decimal = 0;

        while (binary != 0) {
            decimal += (binary%10)*Math.pow(2, power);
            binary = binary / 10;
            power++;
        }

        return decimal;
    }

    public void decimalToBinary(int decimal) {
        int[] binary = new int[50];

        int index = 0;
        while (decimal != 0) {
            binary[index] = decimal % 2;
            decimal = decimal / 2;
            index++;
        }

        for (int i  = index; i >= 0; i--) {
            System.out.print(binary[i] + "");
        }
    }
}
