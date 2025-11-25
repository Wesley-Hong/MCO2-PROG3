import model.*;
import view.*;
import controller.*;

public class Main {
    public static void main(String[] args) {
        PropertyManagement model = new PropertyManagement();
        Controller controller = new Controller(model);
        MainFrame mainFrame = new MainFrame(controller);
        controller.setMainFrame(mainFrame);
    }
}
