package ic.doc;

import java.util.Stack;

public class Model {
  private UpdateTable view;
  private final Stack<Float> stack = new Stack<Float>();

  public Model() {}

  public void addObserver(UpdateTable observer) {
    view = observer;
  }

  public void tryPushNumberToStack(Float num) {
    // Ensure stack has space (<2 operands in stack)
    if (!moreNumbersAllowed()) {
      return;
    }
    // If so, push the num
    stack.push(num);

    view.update(this);
  }

  public void operateOnNumbersInStack(Operator op) {
    // Ensure stack is full (2 operands)
    if (moreNumbersAllowed()) {
      return;
    }
    // Otherwise, pop the numbers, evaluate
    float j = stack.pop();
    float i = stack.pop();
    float result = op.execute(i, j);

    // Push the number to the stack (this will also update view)
    tryPushNumberToStack(result);
  }

  public void clearStack() {
    stack.pop();
    stack.pop();
  }

  public boolean moreNumbersAllowed() {
    return stack.size() < 2;
  }

  public Float getStackTop() {
    return stack.peek();
  }
}
