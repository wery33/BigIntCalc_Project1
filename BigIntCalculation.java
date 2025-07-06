import java.util.Stack;

public class BigIntCalculation {

    public static String evaluateExpression(String input) {
      input = input.trim();
        if (input.isEmpty()) return "0";

        String[] portion = input.split(" ");
        if (portion.length != 3) return "The expression entered is incorrect";

        String _left = portion[0];
        String opp = portion[1];
        String _right = portion[2];

        if (opp.equals("+")) {
            return addBigIntegers(_left, _right);
        } else if (opp.equals("-")) {
            return subtractBigIntegers(_left, _right);
        } else {
            return "Unsupported operator";
        }
    }

    private static String addBigIntegers(String first, String second) {
        Stack<Integer> num1 = buildStack(first);
        Stack<Integer> num2 = buildStack(second);
        Stack<Integer> result = new Stack<>();

        int carry = 0;
        while (!num1.isEmpty() || !num2.isEmpty() || carry != 0) {
            int a, b;
            if (!num1.isEmpty()) {
                a = num1.pop();
            } else {
                a = 0;
            }
            if (!num2.isEmpty()) {
                b = num2.pop();
            } else {
                b = 0;
            }

            int _sum = a + b + carry;
            result.push(_sum % 10);
            carry = _sum / 10;
        }

        return buildResult(result);
    }

    private static String subtractBigIntegers(String first, String second) {
        boolean negative = false;
        if (isSmaller(first, second)) {
            String temp = first;
            first = second;
            second = temp;
            negative = true;
        }

        Stack<Integer> num1 = buildStack(first);
        Stack<Integer> num2 = buildStack(second);
        Stack<Integer> result = new Stack<>();

        int borrow = 0;
        while (!num1.isEmpty() || !num2.isEmpty()) {

            int c, d;
            if (!num1.isEmpty()) {
                c = num1.pop();
            } else {
                c = 0;
            }
        
            if (!num2.isEmpty()) {
                d = num2.pop();
            } else {
                d = 0;
            }

            int _sub = c - d - borrow;
            if (_sub < 0) {
              _sub += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }

            result.push(_sub);
        }

        cleanLeadingZeros(result);

        String finalResult = buildResult(result);
        return negative ? "-" + finalResult : finalResult;
    }

    private static Stack<Integer> buildStack(String num) {
        Stack<Integer> stack = new Stack<>();
        for (char c : num.toCharArray()) {
            if (Character.isDigit(c)) {
                stack.push(Character.getNumericValue(c));
            }
        }
        return stack;
    }

    private static boolean isSmaller(String firstString, String secString) {
        if (firstString.length() != secString.length()) {
            return firstString.length() < secString.length();
        }
        return firstString.compareTo(secString) < 0;
    }
    private static String buildResult(Stack<Integer> result) {
        StringBuilder strbd = new StringBuilder();
        while (!result.isEmpty()) {
            strbd.append(result.pop());
        }
        return strbd.toString();
    }
    private static void cleanLeadingZeros(Stack<Integer> stack) {
        while (stack.size() > 1 && stack.peek() == 0) {
            stack.pop();
        }
    }
}
