import CustomException.NumberOfDigitsException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Vyacheslav Alekseevich
 * @date 27/11/2019
 */

class Calculate {
    private String numberLine;

    void setNumberLine(String numberLine) throws NumberOfDigitsException {
        this.numberLine = numberLine;
        checkForError();
    }

    private void checkForError() throws NumberOfDigitsException {
        String str = numberLine.toUpperCase();
        String[] line = str.split(" ");

        // check array[]
        if (line.length < 3) {
            throw new NumberOfDigitsException("incorrect entry");
        }

        int secondNumber;
        int firstNumber;

        if (hasRomanNumbers(str) && !hasArabicNumbers(str)) {
            // only Roman numbers
            firstNumber = Main.RomanNumbers.valueOf(line[0]).getValue();
            secondNumber = Main.RomanNumbers.valueOf(line[2]).getValue();
            char operation = line[1].charAt(0);
            if (firstNumber > 0 && firstNumber < 11 && secondNumber > 0 && secondNumber < 11) {
                System.out.println(intToRoman(calculate(firstNumber, operation, secondNumber)));
            } else {
                throw new NumberOfDigitsException("error: range from 1 to 10");
            }
        } else if (hasArabicNumbers(str) && !hasRomanNumbers(str)) {
            // only Arabic numbers
            try {
                firstNumber = Integer.parseInt(line[0]);
                secondNumber = Integer.parseInt(line[2]);
                char operation = line[1].charAt(0);
                if (firstNumber > 0 && firstNumber < 11 && secondNumber > 0 && secondNumber < 11) {
                    System.out.println(calculate(firstNumber, operation, secondNumber));
                } else {
                    throw new NumberOfDigitsException("error: range from 1 to 10");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

        } else if (hasArabicNumbers(str) && hasRomanNumbers(str)) {
            // I + 1
            throw new NumberFormatException("can't calculate " + str);
        }

    }

    private boolean hasArabicNumbers(String line) {
        Matcher matcher = Pattern.compile("\\d").matcher(line);
        return matcher.find();
    }

    private boolean hasRomanNumbers(String line) {
        Matcher matcher = Pattern.compile("I|II|III|IV|V|VI|VII|VIII|IX|X").matcher(line);
        return matcher.find();
    }

    private int calculate(int numberOne, char operation, int numberTwo) {
        int result;
        switch (operation) {
            case '+':
                result = numberOne + numberTwo;
                break;
            case '-':
                result = numberOne - numberTwo;
                break;
            case '*':
                result = numberOne * numberTwo;
                break;
            case '/':
                result = numberOne / numberTwo;
                break;
            default:
                throw new ArithmeticException("Unsupported arithmetic operation \"" + operation + "\"");
        }
        return result;
    }

    // convert to Roman number
    private String intToRoman(int num) {
        int[] values = new int[]{
                1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1
        };
        StringBuilder sb = new StringBuilder();
        for (int val : values) {
            if (val > num) continue;
            int numSymbol = num / val;
            while (numSymbol > 0) {
                sb.append(getSymbol(val));
                numSymbol -= 1;
            }
            num %= val;
        }
        return sb.toString();
    }

    private String getSymbol(int val) {
        if (val == 1) {
            return "I";
        }
        if (val == 5) {
            return "V";
        }
        if (val == 10) {
            return "X";
        }
        if (val == 50) {
            return "L";
        }
        if (val == 100) {
            return "C";
        }
        throw new IllegalArgumentException("Unsupported Value " + val);
    }
}
