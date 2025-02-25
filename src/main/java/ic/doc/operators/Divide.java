package ic.doc.operators;

import ic.doc.Operator;

public class Divide implements Operator {

  @Override
  public float execute(Float i, Float j) {
    if (j != 0) {
      return (float) i / j;
    } else {
      return 0;
    }
  }
}
