import ambiente.Ambiente;
import gui.RoboGUI;
import javax.swing.SwingUtilities;
import robo.Robo;

public class Main {
    public static void main(String[] args) {
        Ambiente ambiente = new Ambiente(100, 100);
        Robo robo = new Robo("Alpha", 50, 50);
        
        SwingUtilities.invokeLater(() -> {
            RoboGUI gui = new RoboGUI(ambiente, robo);
            gui.setVisible(true);
        });
    }
}