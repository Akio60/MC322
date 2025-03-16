package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import ambiente.Ambiente;
import robo.Robo;

public class RoboGUI extends JFrame {
    private Ambiente ambiente;
    private Robo robo;
    private AmbientePanel ambientePanel;

    public RoboGUI(Ambiente ambiente, Robo robo) {
        this.ambiente = ambiente;
        this.robo = robo;
        
        setTitle("Simulador do Robô");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ambientePanel = new AmbientePanel();
        add(ambientePanel);
        
        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                int deltaX = 0, deltaY = 0;
                
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:  deltaX = -1; break;
                    case KeyEvent.VK_RIGHT: deltaX = 1; break;
                    case KeyEvent.VK_UP:    deltaY = -1; break;
                    case KeyEvent.VK_DOWN:  deltaY = 1; break;
                }
                
                int novoX = robo.getPosicaoX() + deltaX;
                int novoY = robo.getPosicaoY() + deltaY;
                
                if (ambiente.dentroDosLimites(novoX, novoY)) {
                    robo.mover(deltaX, deltaY);
                } else {
                    JOptionPane.showMessageDialog(null, 
                        "Movimento inválido: fora dos limites do ambiente!");
                }
                ambientePanel.repaint();
            }
            
            @Override
            public void keyTyped(KeyEvent e) {}
            
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        setFocusable(true);
    }
    
    private class AmbientePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int cellSize = 5;
            
            // Desenha os pontos do ambiente
            g.setColor(Color.GRAY);
            for (int x = 0; x <= ambiente.getLargura(); x++) {
                for (int y = 0; y <= ambiente.getAltura(); y++) {
                    g.fillOval(x * cellSize, y * cellSize, 2, 2);
                }
            }
            
            // Desenha o robô
            g.setColor(Color.RED);
            g.fillOval(robo.getPosicaoX() * cellSize - 4, 
                      robo.getPosicaoY() * cellSize - 4, 
                      8, 8);
        }
    }
}
