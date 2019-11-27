import CustomException.NumberOfDigitsException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Vyacheslav Alekseevich
 * @date 27/11/2019
 */

public class Main {
    enum RomanNumbers {
        I(1), II(2), III(3), IV(4), V(5),
        VI(6), VII(7), VIII(8), IX(9), X(10);

        private int value;

        RomanNumbers(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static void main(String[] args) {
        Calculate calculate = new Calculate();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = reader.readLine();
            while (!line.equals("")) {
                calculate.setNumberLine(line);
                line = reader.readLine().toUpperCase();
            }
        } catch (IOException | NumberOfDigitsException e) {
            e.printStackTrace();
        }
    }


}
