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

/**
 *
 * @author Trololo
 */
public class GUI extends JFrame {

    private Tlacitko tlacitka[][] = new Tlacitko[8][8];

    private final ImageIcon pinclB = new ImageIcon("Resources/pinclB.png");
    private final ImageIcon pinclC = new ImageIcon("Resources/pinclC.png");
    private final ImageIcon damaB = new ImageIcon("Resources/damaB.png");
    private final ImageIcon damaC = new ImageIcon("Resources/damaC.png");

    /**
     *
     * @param sach
     */
    public GUI(Sachovnice sach) {
        super("Hra dama");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        Container kon = getContentPane();
        kon.setBackground(Color.black);
        GridLayout srg = new GridLayout(8, 8);
        kon.setLayout(srg);
        vznikTlacitek(kon, tlacitka, sach);
        setContentPane(kon);
        setSize(800, 800);
    }

    void vznikTlacitek(Container kon, Tlacitko tlacitka[][], Sachovnice sach) {
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

    /**
     *
     * @return
     */
    public int vyberHrace() {
        int volba = JOptionPane.showOptionDialog(this, "Vyberte, jaky chcete byt typ hrace", "Volba Hrace", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, new String[]{"Client (Bily)", "Server (Cerny)"}, null);
        return volba;
    }

    /**
     *
     * @return
     */
    public String zadejIP() {
        String volba = JOptionPane.showInputDialog(this, "Zadejte IP adresu serveru", "IP adresa", JOptionPane.PLAIN_MESSAGE);
        return volba;
    }

    /**
     *
     * @param sach
     */
    public void konecHry(Sachovnice sach) {
        if (sach.getPocetBily() == 0) {
            JOptionPane.showMessageDialog(this, "Vyhral cerny", "Konec hry", JOptionPane.PLAIN_MESSAGE);
        }
        if (sach.getPocetCerny() == 0) {
            JOptionPane.showMessageDialog(this, "Vyhral bily", "Konec hry", JOptionPane.PLAIN_MESSAGE);
        }
    }

    /**
     *
     * @param sach
     */
    public void nastaveniHrace(Sachovnice sach) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tlacitka[i][j].addActionListener(sach.getHrac());
            }
        }
    }
}
