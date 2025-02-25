package ic.doc;

public class GuiApp {
  private Model calculator = new Model();
  private View view = new View(calculator);

  public GuiApp() {
    calculator.addObserver(view);
    view.displayFrame();
  }

  public static void main(String[] args) {
    new GuiApp();
  }
}
