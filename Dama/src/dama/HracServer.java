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
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

/**
 *
 * @author Trololo
 */
public class HracServer extends Hrac implements ActionListener {

    private int counter = 0;
    private Tah tah;
    private Sachovnice sachovnice;
    boolean correct = false;
    private Socket sock;

    Scanner scan = new Scanner(System.in);

    HracServer(Sachovnice sach) {
        sachovnice = sach;
        tah = new Tah();
        try {
            sock = new Socket("147.32.93.141", 9876);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void tahni(Sachovnice sachovnice, Tah tah) {
        boolean povoleno;
        povoleno = false;
        if (sachovnice.jeNaSachovnici(tah.getCisloOdkud(), tah.getPismenoOdkud())) {      //kontroluje, zda existuje policko, pokud ano, jestli je na nem prislusna figurka
            if (sachovnice.figurka(sachovnice.getSach()[tah.getCisloOdkud()][tah.getPismenoOdkud()])) {
                if ((sachovnice.barva(sachovnice.getSach()[tah.getCisloOdkud()][tah.getPismenoOdkud()]) && sachovnice.kdoHraje()) || (!sachovnice.barva(sachovnice.getSach()[tah.getCisloOdkud()][tah.getPismenoOdkud()]) && !sachovnice.kdoHraje())) {
                    povoleno = true;
                }
            }
        }
        boolean povolen = false;
        if (sachovnice.jeNaSachovnici(tah.getCisloKam(), tah.getPismenoKam())) {      //zda existuje policko, pokud ano, jestli je prazdne
            if (!sachovnice.figurka(sachovnice.getSach()[tah.getCisloKam()][tah.getPismenoKam()])) {
                povolen = true;
            }
        }
        if (!sachovnice.pohyb(tah.getCisloOdkud(), tah.getPismenoOdkud(), tah.getCisloKam(), tah.getPismenoKam()) && !sachovnice.skok(tah.getCisloOdkud(), tah.getPismenoOdkud(), tah.getCisloKam(), tah.getPismenoKam())) {
            povolen = false;       //pokud neni tah v souladu s pravidly
        }
        if (sachovnice.jeNaSachovnici(tah.getCisloKam(), tah.getPismenoKam()) && sachovnice.pohybDama(tah.getCisloOdkud(), tah.getPismenoOdkud(), tah.getCisloKam(), tah.getPismenoKam())) {
            povolen = true;
        }
        if (povoleno && povolen) {
            sachovnice.zamenFigurky(tah.getPismenoOdkud(), tah.getCisloOdkud(), tah.getPismenoKam(), tah.getCisloKam());

        }
        System.out.println("Server");
        sachovnice.vypisSachovnici();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!sachovnice.kdoHraje()) {
            Tlacitko o = (Tlacitko) e.getSource();
            if (counter % 2 == 0) {
                tah.setCisloOdkud(o.getA());
                tah.setPismenoOdkud(o.getB());
                o.setBorder(new LineBorder(Color.RED, 2));
                counter++;
            } else {
                if (o.getA() == tah.getCisloOdkud() && o.getB() == tah.getPismenoOdkud()) {
                    o.setBorder(UIManager.getBorder("Button.border"));
                    counter++;
                } else {
                    tah.setCisloKam(o.getA());
                    tah.setPismenoKam(o.getB());
                    tahni(sachovnice, tah);
                    counter++;
                }
            };
        }
    }
}
