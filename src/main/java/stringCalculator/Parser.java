package stringCalculator;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Parser {

  private Parser() {}

  public static Expression parse(String input) {
    List<String> elements = Arrays.asList(input.split(" "));
    Queue<Operator> operators = new LinkedList<>();
    Queue<Number> numbers = new LinkedList<>();

    if (isEven(elements.size())) {
      throw new IllegalArgumentException("연산자는 항상 피연산자 사이에 들어가야 합니다");
    }

    for (int i = 0; i < elements.size(); i++) {
      put(operators, numbers, elements.get(i), i);
    }

    return new Expression(operators, numbers);
  }

  private static void put(Queue<Operator> operators, Queue<Number> numbers, String element, int index) {
    if (isEven(index)) {
      numbers.add(new Number(element));
      return;
    }

    operators.add(Operator.from(element));
  }

  private static boolean isEven(int target) {
    return target % 2 == 0;
  }
}
