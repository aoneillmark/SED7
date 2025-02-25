package ic.doc.operators;

import ic.doc.Operator;

public class Subtract implements Operator {

  @Override
  public float execute(Float i, Float j) {
    return i - j;
  }
}
