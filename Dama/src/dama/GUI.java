/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dama;

/**
 *
 * @author Trololo
 */
import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    public GUI() {
        super("Hra dama");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        Container kon = getContentPane(); // vrací kontejner
        kon.setBackground(Color.magenta);
        GridLayout srg = new GridLayout(8, 8);
        kon.setLayout(srg);
        tlacitka(kon);
        
    }
    
    void tlacitka (Container kon) {
       JButton a8 = new JButton("A8");
        kon.add(a8); 
    }

    public static void main(String[] args) {
        GUI po = new GUI();
        po.pack();
        po.setVisible(true);
    }
}
