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
import java.io.IOException;
import javax.imageio.ImageIO;

public class GUI extends JFrame {

    private Tlacitko tlacitka[][] = new Tlacitko[8][8];
    ImageIcon pinclB = new ImageIcon("Sources/pinclB.png");
    ImageIcon pinclC = new ImageIcon("Sources/pinclC.png");
    ImageIcon damaB = new ImageIcon("Sources/damaB.png");
    ImageIcon damaC = new ImageIcon("Sources/damaC.png");

    public GUI(Sachovnice sach, HracClovek hrac) {
        super("Hra dama");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        Container kon = getContentPane(); // vrací kontejner
        kon.setBackground(Color.black);
        GridLayout srg = new GridLayout(8, 8);
        kon.setLayout(srg);
        vznikTlacitek(kon, tlacitka, sach, hrac);
        setContentPane(kon);
        setSize(800, 800);
    }

    void vznikTlacitek(Container kon, Tlacitko tlacitka[][], Sachovnice sach, HracClovek hrac) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tlacitka[i][j] = new Tlacitko(i, j);
                if ((i + j) % 2 == 0) {
                    tlacitka[i][j].setBackground(Color.LIGHT_GRAY);
                } else {
                    tlacitka[i][j].setBackground(Color.white);
                }
                if (sach.getSach()[i][j] == 1) {
                    tlacitka[i][j].setIcon(pinclB);
                }
                if (sach.getSach()[i][j] == -1) {
                    tlacitka[i][j].setIcon(pinclC);
                }
                tlacitka[i][j].addActionListener(hrac);
                kon.add(tlacitka[i][j]);
            }
        }
    }

    void vypisTlacitek(Sachovnice sach) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                    tlacitka[i][j].setBorder(UIManager.getBorder("Button.border"));              
                switch (sach.getSach()[i][j]) {
                    case (0): {
                        tlacitka[i][j].setIcon(null);
                    }
                    break;
                    case (1): {
                        tlacitka[i][j].setIcon(pinclB);;
                    }
                    break;
                    case (-1): {
                        tlacitka[i][j].setIcon(pinclC);
                    }
                    break;
                    case (2): {
                        tlacitka[i][j].setIcon(damaB);
                    }
                    break;
                    case (-2): {
                        tlacitka[i][j].setIcon(damaC);
                    }
                    break;
                }
            }
        }
    }
}
