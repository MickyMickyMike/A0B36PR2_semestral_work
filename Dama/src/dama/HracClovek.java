/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dama;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

/**
 *
 * @author Trololo
 */
public class HracClovek implements Hrac, ActionListener {

    private int pismenoKam;
    private int pismenoOdkud;
    private int cisloOdkud;
    private int cisloKam;
    private int counter;

    Scanner scan = new Scanner(System.in);

    HracClovek() {
    }

    private boolean odkudTah(Sachovnice sachovnice) {

        boolean povoleno;
        //String pole;
        //do {
        povoleno = false;
        System.out.println();
        System.out.println("Zadej odkud tahnout: ");
        /*pole = scan.nextLine();
         if (pole.length() == 2) {       //kontroluje delku pole
         if (pole.charAt(0) == 'a' || pole.charAt(0) == 'b' || pole.charAt(0) == 'c' || pole.charAt(0) == 'd' || pole.charAt(0) == 'e' || pole.charAt(0) == 'f' || pole.charAt(0) == 'g' || pole.charAt(0) == 'h') {
         pismenoOdkud = (pole.charAt(0) - 'a');      //prepocet na int
         }
         if (pole.charAt(0) == 'A' || pole.charAt(0) == 'B' || pole.charAt(0) == 'C' || pole.charAt(0) == 'D' || pole.charAt(0) == 'E' || pole.charAt(0) == 'F' || pole.charAt(0) == 'G' || pole.charAt(0) == 'H') {
         pismenoOdkud = (pole.charAt(0) - 'A');      //prepocet na int
         }
         cisloOdkud = sachovnice.getSach().length - (pole.charAt(1) - '0');*/
        //if (sachovnice.jeNaSachovnici(cisloOdkud, pismenoOdkud)) {      //kontroluje, zda existuje policko, pokud ano, jestli je na nem prislusna figurka
        if (sachovnice.figurka(sachovnice.getSach()[cisloOdkud][pismenoOdkud])) {
            if ((sachovnice.barva(sachovnice.getSach()[cisloOdkud][pismenoOdkud]) && sachovnice.kdoHraje()) || (!sachovnice.barva(sachovnice.getSach()[cisloOdkud][pismenoOdkud]) && !sachovnice.kdoHraje())) {
                povoleno = true;
            } else {
                System.out.println("Nemozny tah");
            }
        } else {
            System.out.println("Nemozny tah");
        }
        /*} else {
         System.out.println("Nemozny tah");
         }
         } else {
         System.out.println("Nemozny tah");
         }*/
        //} while (!povoleno);
        return povoleno;
    }

    private boolean kamTah(Sachovnice sachovnice) {

        boolean povoleno;
        //String pole;
        //do {
        povoleno = false;
        System.out.println();
        System.out.println("Zadej kam tahnout: ");
        /*pole = scan.nextLine();
         if (pole.charAt(0) - '0' == 0 && pole.length() == 1) {
         break;
         }
         if (pole.length() == 2) {               //kontroluje delku pole
         if (pole.charAt(0) == 'a' || pole.charAt(0) == 'b' || pole.charAt(0) == 'c' || pole.charAt(0) == 'd' || pole.charAt(0) == 'e' || pole.charAt(0) == 'f' || pole.charAt(0) == 'g' || pole.charAt(0) == 'h') {
         pismenoKam = (pole.charAt(0) - 'a');        //prepocet na int
         }
         if (pole.charAt(0) == 'A' || pole.charAt(0) == 'B' || pole.charAt(0) == 'C' || pole.charAt(0) == 'D' || pole.charAt(0) == 'E' || pole.charAt(0) == 'F' || pole.charAt(0) == 'G' || pole.charAt(0) == 'H') {
         pismenoKam = (pole.charAt(0) - 'A');        //prepocet na int
         }
         cisloKam = sachovnice.getSach().length - (pole.charAt(1) - '0');*/
        if (sachovnice.jeNaSachovnici(cisloKam, pismenoKam)) {      //zda existuje policko, pokud ano, jestli je prazdne
            if (!sachovnice.figurka(sachovnice.getSach()[cisloKam][pismenoKam])) {
                povoleno = true;
            }
        }
        if (!sachovnice.pohyb(cisloOdkud, pismenoOdkud, cisloKam, pismenoKam) && !sachovnice.skok(cisloOdkud, pismenoOdkud, cisloKam, pismenoKam)) {
            povoleno = false;       //pokud neni tah v souladu s pravidly
        }
        if (sachovnice.jeNaSachovnici(cisloKam, pismenoKam) && sachovnice.pohybDama(cisloOdkud, pismenoOdkud, cisloKam, pismenoKam)) {
            povoleno = true;
        }

        if (!povoleno) {
            System.out.println("Nemozny tah");
        }
        //} while (!povoleno);
        return povoleno;

    }

    @Override
    public void tahni(Sachovnice sachovnice) {
        sachovnice.zamenFigurky(pismenoOdkud, cisloOdkud, pismenoKam, cisloKam);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean povoleno;
        Tlacitko o = (Tlacitko) e.getSource();
        o.setBackground(Color.red);
        if (counter % 2 == 1) {
            do {
                povoleno = false;
                pismenoOdkud = o.getA();
                cisloOdkud = o.getB();
                counter++;
            } while (!povoleno);
        } else {
            pismenoKam = o.getA();
            cisloKam = o.getB();
            counter++;
        };
    }

}
