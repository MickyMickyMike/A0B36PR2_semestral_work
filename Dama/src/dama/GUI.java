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

    private Tlacitko tlacitka[][] = new Tlacitko[8][8];

    public GUI() {
        super("Hra dama");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        Container kon = getContentPane(); // vrací kontejner
        kon.setBackground(Color.black);
        GridLayout srg = new GridLayout(8, 8);
        kon.setLayout(srg);
        vznikTlacitek(kon, tlacitka);
        setContentPane(kon);
        setSize(800, 800);
    }

    void vznikTlacitek(Container kon, JButton tlacitka[][]) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tlacitka[i][j] = new Tlacitko(i,j);
                if ((i + j) % 2 == 0) {
                    tlacitka[i][j].setBackground(Color.LIGHT_GRAY);
                } else {
                    tlacitka[i][j].setBackground(Color.white);
                }
                tlacitka[i][j].addActionListener(new HracClovek());
                kon.add(tlacitka[i][j]);
            }
        }
    }
}
