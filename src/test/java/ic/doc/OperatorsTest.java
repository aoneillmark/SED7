package ic.doc;

import ic.doc.operators.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class OperatorsTest {

  @Test
  public void sumOperatorAddsCorrectly() {
    Operator sum = new Sum();
    assertThat(sum.execute(3f, 4f), is(7f));
    assertThat(sum.execute(-2f, 5f), is(3f));
    assertThat(sum.execute(0f, 0f), is(0f));
  }

  @Test
  public void subtractOperatorSubtractsCorrectly() {
    Operator subtract = new Subtract();
    assertThat(subtract.execute(10f, 4f), is(6f));
    assertThat(subtract.execute(5f, 7f), is(-2f));
    assertThat(subtract.execute(0f, 0f), is(0f));
  }

  @Test
  public void multiplyOperatorMultipliesCorrectly() {
    Operator multiply = new Multiply();
    assertThat(multiply.execute(3f, 4f), is(12f));
    assertThat(multiply.execute(-2f, 5f), is(-10f));
    assertThat(multiply.execute(0f, 10f), is(0f));
  }

  @Test
  public void divideOperatorDividesCorrectly() {
    Operator divide = new Divide();
    assertThat(divide.execute(10f, 2f), is(5f));
    assertThat(divide.execute(9f, -3f), is(-3f));
    assertThat(divide.execute(0f, 5f), is(0f));
  }

  @Test
  public void divideOperatorHandlesDivisionByZero() {
    Operator divide = new Divide();
    assertThat(divide.execute(10f, 0f), is(0f));
  }
}
