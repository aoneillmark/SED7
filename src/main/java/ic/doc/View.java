package ic.doc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import ic.doc.ActionListeners.*;
import ic.doc.operators.*;
import java.util.ArrayList;

class View implements UpdateTable {
  private final JFrame frame = new JFrame("Polish Notation Calculator");
  private final JTextField display = new JTextField(10);
  private final JPanel mainPanel = new JPanel();
  private final JPanel numberPanel = new JPanel(new GridLayout(4, 3));
  private final JPanel operatorPanel = new JPanel(new GridLayout(4, 1));
  private final JButton sum = new JButton("+");
  private final JButton subtract = new JButton("-");

  private final ArrayList<JButton> numberButtons = new ArrayList<>();
  private final ArrayList<JButton> operatorButtons = new ArrayList<>();

  public View(Model model) {
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(300, 400);
    frame.setLayout(new BorderLayout());

    display.setEditable(false);
    display.setHorizontalAlignment(JTextField.RIGHT);
    frame.add(display, BorderLayout.NORTH);

    for (int i = 0; i <= 9; i++) {
      JButton button = new JButton(String.valueOf(i));
      numberButtons.add(button);
      button.addActionListener(new NumberActionListener(model, (float) i));
      numberPanel.add(button);
    }

    JButton b = new JButton("\u03C0");
    numberButtons.add(b);
    b.addActionListener(new NumberActionListener(model, (float) Math.PI));
    numberPanel.add(b);

    String[] operators = {"+", "-", "*", "/"};
    for (String op : operators) {
      JButton button = new JButton(op);
      operatorButtons.add(button);

      Operator operator;
      switch (op) {
        case "+" -> operator = new Sum();
        case "-" -> operator = new Subtract();
        case "*" -> operator = new Multiply();
        case "/" -> operator = new Divide();
        default -> throw new IllegalArgumentException("Unknown operator: " + op);
      }

      button.addActionListener(new OperatorActionListener(model, operator));
      operatorPanel.add(button);
    }

    mainPanel.setLayout(new BorderLayout());
    mainPanel.add(numberPanel, BorderLayout.CENTER);
    mainPanel.add(operatorPanel, BorderLayout.EAST);
    frame.add(mainPanel, BorderLayout.CENTER);
  }

  public void displayFrame() {
    frame.setVisible(true);
  }

  @Override
  public void update(Model model) {
    display.setText(String.valueOf(model.getStackTop()));
    boolean enableNumbers = model.moreNumbersAllowed();
    for (JButton btn : numberButtons) {
      btn.setEnabled(enableNumbers);
    }
  }
}
