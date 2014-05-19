/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dama;

import java.util.*;

/**
 *
 * @author Trololo
 */
public class HracClovek implements Hrac {

    Scanner scan = new Scanner(System.in);

    HracClovek() {
    }

    @Override
    public void vyberTah(Sachovnice sachovnice) {
        int pismenoKam = 0, pismenoOdkud = 0, cisloOdkud = 0, cisloKam = 0;
        boolean povoleno = false;
        String pole;
        do {
            do {
                povoleno = false;
                System.out.println();
                System.out.println("Zadej odkud tahnout: ");
                pole = scan.nextLine();
                if (pole.length() == 2) {       //kontroluje delku pole
                    if (pole.charAt(0) == 'a' || pole.charAt(0) == 'b' || pole.charAt(0) == 'c' || pole.charAt(0) == 'd' || pole.charAt(0) == 'e' || pole.charAt(0) == 'f' || pole.charAt(0) == 'g' || pole.charAt(0) == 'h') {
                        pismenoOdkud = (pole.charAt(0) - 'a');      //prepocet na int
                    }
                    if (pole.charAt(0) == 'A' || pole.charAt(0) == 'B' || pole.charAt(0) == 'C' || pole.charAt(0) == 'D' || pole.charAt(0) == 'E' || pole.charAt(0) == 'F' || pole.charAt(0) == 'G' || pole.charAt(0) == 'H') {
                        pismenoOdkud = (pole.charAt(0) - 'A');      //prepocet na int
                    }
                    cisloOdkud = sachovnice.getSach().length - (pole.charAt(1) - '0');
                    if (sachovnice.jeNaSachovnici(cisloOdkud, pismenoOdkud)) {      //kontroluje, zda existuje policko, pokud ano, jestli je na nem prislusna figurka
                        if (sachovnice.figurka(sachovnice.getSach()[cisloOdkud][pismenoOdkud])) {
                            if ((sachovnice.barva(sachovnice.getSach()[cisloOdkud][pismenoOdkud]) && sachovnice.kdoHraje()) || (!sachovnice.barva(sachovnice.getSach()[cisloOdkud][pismenoOdkud]) && !sachovnice.kdoHraje())) {
                                povoleno = true;
                            } else {
                                System.out.println("Nemozny tah");
                            }
                        } else {
                            System.out.println("Nemozny tah");
                        }
                    } else {
                        System.out.println("Nemozny tah");
                    }
                } else {
                    System.out.println("Nemozny tah");
                }
            } while (!povoleno);
            do {
                povoleno = false;
                System.out.println();
                System.out.println("Zadej kam tahnout: ");
                pole = scan.nextLine();
                if (pole.charAt(0) - '0' == 0 && pole.length() == 1){
                    break;
                }
                if (pole.length() == 2) {               //kontroluje delku pole
                    if (pole.charAt(0) == 'a' || pole.charAt(0) == 'b' || pole.charAt(0) == 'c' || pole.charAt(0) == 'd' || pole.charAt(0) == 'e' || pole.charAt(0) == 'f' || pole.charAt(0) == 'g' || pole.charAt(0) == 'h') {
                        pismenoKam = (pole.charAt(0) - 'a');        //prepocet na int
                    }
                    if (pole.charAt(0) == 'A' || pole.charAt(0) == 'B' || pole.charAt(0) == 'C' || pole.charAt(0) == 'D' || pole.charAt(0) == 'E' || pole.charAt(0) == 'F' || pole.charAt(0) == 'G' || pole.charAt(0) == 'H') {
                        pismenoKam = (pole.charAt(0) - 'A');        //prepocet na int
                    }
                    cisloKam = sachovnice.getSach().length - (pole.charAt(1) - '0');
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
                }
                if (!povoleno) {
                    System.out.println("Nemozny tah");
                }
            } while (!povoleno);
        } while (!povoleno);
        sachovnice.zamenFigurky(pismenoOdkud, cisloOdkud, pismenoKam, cisloKam);
    }

}
