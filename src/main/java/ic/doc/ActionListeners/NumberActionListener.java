package ic.doc.ActionListeners;

import ic.doc.Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberActionListener implements ActionListener {
  private final Model model;
  private final Float number;

  public NumberActionListener(Model model, Float number) {
    this.model = model;
    this.number = number;
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    model.tryPushNumberToStack(number);
  }
}
