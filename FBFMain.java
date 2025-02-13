package FBF;

import javax.swing.SwingUtilities;

public class FBFMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FBFModel model = new FBFModel();
            FBFView view = new FBFView();
            new FBFController(model, view);
            view.setVisible(true);
        });
    }
}