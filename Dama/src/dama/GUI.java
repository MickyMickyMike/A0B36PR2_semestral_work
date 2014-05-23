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

    private final ImageIcon pinclB = new ImageIcon("pinclB.png");
    private final ImageIcon pinclC = new ImageIcon("pinclC.png");
    private final ImageIcon damaB = new ImageIcon("damaB.png");
    private final ImageIcon damaC = new ImageIcon("damaC.png");

    /**
     * vytvori graficke hraci pole, nastavi mrizku 8x8 pro sachovnici
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

     /**
     * nastaví barvu tlacitek, aby odpovidala sachovnici
     * rozmisti zakladni figurky na zakladni pozice
     */
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
    
 /**
     * podle hodnot, ktere jsou v sach vykresluje figurky na sachovnici
     * zaroven nastavuje standardni okraje tlacitek
     * @param sach
     */
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
     *volba typu hrace
     * @return 0 pro typ Client(bily), 1 pro typ Server(cerny)
     */
    public int vyberHrace() {
        int volba = JOptionPane.showOptionDialog(this, "Vyberte, jaky chcete byt typ hrace", "Volba Hrace", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, new String[]{"Client (Bily)", "Server (Cerny)"}, null);
        if (volba == 1) {
            JOptionPane.showMessageDialog(this, "Kazdy tah potvrdte mezernikem!", "Informace", JOptionPane.PLAIN_MESSAGE);
        }
        return volba;
    }

    /**
     *zadani IP adresy serveru
     * @return IP adresa
     */
    public String zadejIP() {
        String volba = JOptionPane.showInputDialog(this, "Zadejte IP adresu serveru", "IP adresa", JOptionPane.PLAIN_MESSAGE);
        JOptionPane.showMessageDialog(this, "Kazdy tah potvrdte mezernikem!", "Informace", JOptionPane.PLAIN_MESSAGE);
        return volba;
    }

    /**
     *vypis viteze
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
     *nastavi hrace jako actionListener tlacitek
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
