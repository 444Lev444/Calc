
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Map<Character, Integer> romanToArabicMap = new HashMap<>();
    private static final Map<Integer, String> arabicToRomanMap = new HashMap<>();

    static {

        romanToArabicMap.put('I', 1);
        romanToArabicMap.put('V', 5);
        romanToArabicMap.put('X', 10);
        romanToArabicMap.put('L', 50);
        romanToArabicMap.put('C', 100);

        arabicToRomanMap.put(1, "I");
        arabicToRomanMap.put(4, "IV");
        arabicToRomanMap.put(5, "V");
        arabicToRomanMap.put(9, "IX");
        arabicToRomanMap.put(10, "X");
        arabicToRomanMap.put(40, "XL");
        arabicToRomanMap.put(50, "L");
        arabicToRomanMap.put(90, "XC");
        arabicToRomanMap.put(100, "C");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите римское выражение (например, I + V):");
        while (true) {



            String expression = scanner.nextLine();
            try {
                System.out.println("Результат: " + calc(expression));
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    public static String calc(String input) throws Exception {
        char operation;
        if (input.contains("+")) {
            operation = '+';
        } else if (input.contains("-")) {
            operation = '-';
        } else if (input.contains("/")) {
            operation = '/';
        } else if (input.contains("*")) {
            operation = '*';
        } else {
            throw new Exception("Неподдерживаемая операция");
        }

        int indexOfOperation = input.indexOf(operation);
        String firstNumString = input.substring(0, indexOfOperation).trim();
        String secondNumString = input.substring(indexOfOperation + 1).trim();

       int firstNum = romanToArabic(firstNumString);
        int secondNum = romanToArabic(secondNumString);

         int result;
        switch (operation) {
            case '+':
                result = firstNum + secondNum;
                break;
            case '-':
                result = firstNum - secondNum;
                break;
            case '*':
                result = firstNum * secondNum;
                break;
            case '/':
                if (secondNum == 0) {
                    throw new Exception("Деление на ноль невозможно");
                }
                result = firstNum / secondNum;
                break;
            default:
                throw new Exception("Неизвестная операция");
        }

        return arabicToRoman(result);
    }

    private static int romanToArabic(String roman) throws Exception {
        int result = 0;
        int prevValue = 0;       
        for (int i = roman.length() - 1; i >= 0; i--) {
            char c = roman.charAt(i);
            Integer value = romanToArabicMap.get(c);
            if (value == null) {
                throw new Exception("Некорректное римское число");
            }
            if (value < prevValue) {
                result -= value;
            } else {
                result += value;
            }
            prevValue = value;
        }
        return result;
    }

    private static String


    arabicToRoman(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Число должно быть положительным");
        }
        StringBuilder roman = new StringBuilder();
        int[] arabicValues = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanNumerals = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        for (int i = 0; i < arabicValues.length; i++) {
            while (number >= arabicValues[i]) {
                roman.append(romanNumerals[i]);
                number -= arabicValues[i];
            }
        }
        return roman.toString();
    }
}

