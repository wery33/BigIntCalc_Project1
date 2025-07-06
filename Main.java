import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String input;

    while (true) {
      System.out.print("Enter an expression to evaluate (or type 'exit' to quit): ");
      input = sc.nextLine().trim();

      if (input.equalsIgnoreCase("exit")) {
        System.out.println("Calculator terminated.");
        break;
      }

      String result = BigIntCalculation.evaluateExpression(input);
      System.out.println("Result: " + result);
    }

    sc.close();
  }
}
