package ic.doc.ActionListeners;

import ic.doc.Model;
import ic.doc.Operator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OperatorActionListener implements ActionListener {
  private final Model model;
  private final Operator operator;

  public OperatorActionListener(Model model, Operator operator) {
    this.model = model;
    this.operator = operator;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    model.operateOnNumbersInStack(operator);
  }
}
