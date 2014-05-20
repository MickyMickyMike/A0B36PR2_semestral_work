/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dama;

import java.awt.Color;
import java.awt.Toolkit;
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

    private int counter = 0;
    private Tah tah = new Tah();
    private Sachovnice sachovnice = new Sachovnice();
    boolean correct = false;

    Scanner scan = new Scanner(System.in);

    HracClovek() {
    }

    @Override
    public void tahni(Sachovnice sachovnice, Tah tah) {
        boolean povoleno;

        //String pole;
        //do {
        povoleno = false;
        //   cisloOdkud = tah.a;
        //    pismenoOdkud = tah.b;
        //    System.out.print(tah.a + " ");
        //    System.out.print(pismenoOdkud + " ");
        //System.out.println();
        //System.out.println("Zadej odkud tahnout: ");
        /*pole = scan.nextLine();
         if (pole.length() == 2) {       //kontroluje delku pole
         if (pole.charAt(0) == 'a' || pole.charAt(0) == 'b' || pole.charAt(0) == 'c' || pole.charAt(0) == 'd' || pole.charAt(0) == 'e' || pole.charAt(0) == 'f' || pole.charAt(0) == 'g' || pole.charAt(0) == 'h') {
         pismenoOdkud = (pole.charAt(0) - 'a');      //prepocet na int
         }
         if (pole.charAt(0) == 'A' || pole.charAt(0) == 'B' || pole.charAt(0) == 'C' || pole.charAt(0) == 'D' || pole.charAt(0) == 'E' || pole.charAt(0) == 'F' || pole.charAt(0) == 'G' || pole.charAt(0) == 'H') {
         pismenoOdkud = (pole.charAt(0) - 'A');      //prepocet na int
         }
         cisloOdkud = sachovnice.getSach().length - (pole.charAt(1) - '0');*/
        if (sachovnice.jeNaSachovnici(tah.getCisloOdkud(), tah.getPismenoOdkud())) {      //kontroluje, zda existuje policko, pokud ano, jestli je na nem prislusna figurka
            if (sachovnice.figurka(sachovnice.getSach()[tah.getCisloOdkud()][tah.getPismenoOdkud()])) {
                if ((sachovnice.barva(sachovnice.getSach()[tah.getCisloOdkud()][tah.getPismenoOdkud()]) && sachovnice.kdoHraje()) || (!sachovnice.barva(sachovnice.getSach()[tah.getCisloOdkud()][tah.getPismenoOdkud()]) && !sachovnice.kdoHraje())) {
                    povoleno = true;
                } /*else {
                 System.out.println("Nemozny tah");
                 }*/

            } /*else {
             System.out.println("Nemozny tah");
             }*/
            /*} else {
             System.out.println("Nemozny tah");
             }
             } else {
             System.out.println("Nemozny tah");*/

        }
        //} while (!povoleno);
        //counter++;

        //String pole;
        // do {
        boolean povolen = false;
        //  cisloKam = tah.c;
        //   pismenoKam = tah.d;
        //System.out.print(cisloKam + " ");
        //System.out.print(pismenoKam + " ");
        //System.out.println();
        //System.out.println("Zadej kam tahnout: ");
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

        if (!povolen) {
            System.out.println("Nemozny tah");
        }

        //while (!povoleno);
        if (povoleno && povolen) {
            sachovnice.zamenFigurky(tah.getPismenoOdkud(), tah.getCisloOdkud(), tah.getPismenoKam(), tah.getCisloKam());
            correct = true;
        }
        sachovnice.vypisSachovnici();
        //counter++;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Tlacitko o = (Tlacitko) e.getSource();
        if (counter % 2 == 0) {
            tah.setCisloOdkud(o.getA());
            tah.setPismenoOdkud(o.getB());
            tah.setFigurka(o.getText());
        } else {
            tah.setCisloKam(o.getA());
            tah.setPismenoKam(o.getB());
            tahni(sachovnice, tah);
            if (correct){
                
            }
        };
        counter++;
        sachovnice.vypisSachovnici();
        System.out.println(counter);
        System.out.println(tah.getCisloOdkud());
        System.out.println(tah.getPismenoOdkud());
        System.out.println(tah.getCisloKam());
        System.out.println(tah.getPismenoKam());

    }

    /*
     @Override
     public void mouseClicked(MouseEvent e) {
     }

     @Override
     public void mousePressed(MouseEvent e) {
     Tlacitko o = (Tlacitko) e.getSource();
     tah.setCisloOdkud(o.getA());
     tah.setPismenoOdkud(o.getB());
     sachovnice.vypisSachovnici();
     System.out.println(counter);
     System.out.println(tah.getCisloOdkud());
     System.out.println(tah.getPismenoOdkud());
     System.out.println(tah.getCisloKam());
     System.out.println(tah.getPismenoKam());
     }

     @Override
     public void mouseReleased(MouseEvent e) {
     Tlacitko o = (Tlacitko) e.getSource();
     tah.setCisloKam(o.getA());
     tah.setPismenoKam(o.getB());
     System.out.println(counter);
     System.out.println(tah.getCisloOdkud());
     System.out.println(tah.getPismenoOdkud());
     System.out.println(tah.getCisloKam());
     System.out.println(tah.getPismenoKam());
     }

     @Override
     public void mouseEntered(MouseEvent e) {
     }

     @Override
     public void mouseExited(MouseEvent e) {
     }*/
}
