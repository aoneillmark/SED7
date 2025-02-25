package ic.doc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import ic.doc.operators.Sum;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class ModelTest {
  @Rule public JUnitRuleMockery context = new JUnitRuleMockery();

  @Test
  public void canPushNumbersToStack() {
    UpdateTable mockView = context.mock(UpdateTable.class);
    Model model = new Model();
    model.addObserver(mockView);

    context.checking(
        new Expectations() {
          {
            exactly(2).of(mockView).update(model);
          }
        });

    model.tryPushNumberToStack(3f);
    model.tryPushNumberToStack(4f);

    assertThat(model.moreNumbersAllowed(), is(false));
    assertThat(model.getStackTop(), is(4f));
  }

  @Test
  public void operatesOnTwoNumbersCorrectly() {
    UpdateTable mockView = context.mock(UpdateTable.class);
    Model model = new Model();
    model.addObserver(mockView);

    Operator mockOperator = context.mock(Operator.class);

    context.checking(
        new Expectations() {
          {
            exactly(3).of(mockView).update(model);
            oneOf(mockOperator).execute(3f, 4f);
            will(returnValue(999f));
          }
        });

    model.tryPushNumberToStack(3f);
    model.tryPushNumberToStack(4f);
    model.operateOnNumbersInStack(mockOperator);

    assertThat(model.moreNumbersAllowed(), is(true));
    assertThat(model.getStackTop(), is(999f));
  }

  @Test
  public void cannotPushMoreThanTwoNumbersToStack() {
    UpdateTable mockView = context.mock(UpdateTable.class);
    Model model = new Model();
    model.addObserver(mockView);

    context.checking(
        new Expectations() {
          {
            exactly(2).of(mockView).update(model);
          }
        });

    model.tryPushNumberToStack(3f);
    model.tryPushNumberToStack(4f);
    model.tryPushNumberToStack(5f);

    assertThat(model.moreNumbersAllowed(), is(false));
  }

  @Test
  public void cannotOperateWithLessThanTwoNumbers() {
    // Create a mock UpdateTable (view)
    UpdateTable mockView = context.mock(UpdateTable.class);
    Model model = new Model();
    model.addObserver(mockView);

    Operator sumOperator = new Sum();

    context.checking(
        new Expectations() {
          {
            oneOf(mockView).update(model);
          }
        });

    model.tryPushNumberToStack(3f);

    model.operateOnNumbersInStack(sumOperator);
  }

  @Test
  public void clearStackEmptiesModel() {
    UpdateTable mockView = context.mock(UpdateTable.class);
    Model model = new Model();
    model.addObserver(mockView);

    context.checking(
        new Expectations() {
          {
            exactly(2).of(mockView).update(model);
          }
        });

    model.tryPushNumberToStack(10f);
    model.tryPushNumberToStack(20f);

    model.clearStack();

    assertThat(model.moreNumbersAllowed(), is(true));
  }
}
