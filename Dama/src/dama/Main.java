/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dama;

import java.util.*;

public class Main {

    static void skok() {
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
    }

    public static void main(String[] args) {
        GUI plocha = new GUI();
        Scanner scan = new Scanner(System.in);
        int vybirac = 1;
        int n, m;
        Hrac hrac1 = new HracClovek();
        //Hrac hrac2 = new HracClovek();
        boolean hra = true;
        System.out.println("----- DAMA -----");
        System.out.println("");
        System.out.println("Prvni hrac (bily): ");
        System.out.println("1 - Uzivatel");
        System.out.println("2 - Pocitac");
        System.out.println("");
        /*do {
            n = scan.nextInt();
            switch (n) {
                case (1): {
                    hrac1 = new HracClovek();
                }
                break;
                case (2): {
                    hrac1 = new HracPC();
                }
                break;
                default: {
                    System.out.println("Chyba! Zadej znovu");
                }
            }

        } while (n != 1 && n != 2);
        System.out.println("");
        System.out.println("Druhy hrac (cerny): ");
        System.out.println("1 - Uzivatel");
        System.out.println("2 - Pocitac");
        System.out.println("");
        do {
            m = scan.nextInt();
            switch (m) {
                case (1): {
                    hrac2 = new HracClovek();
                }
                break;
                case (2): {
                    hrac2 = new HracPC();
                }
                break;
                default: {
                    System.out.println("Chyba! Zadej znovu");
                }
            }

        } while (m != 1 && m != 2);
        System.out.println("");
        skok();
        Sachovnice sachovnice = new Sachovnice();
        sachovnice.naplnSachovnici();
        sachovnice.vypisSachovnici();

            if (vybirac % 2 == 1) {
                System.out.println("");
                System.out.println("--- Bily na tahu ---");
                hrac1.tahni(sachovnice, new Tah());
                skok();
                sachovnice.vypisSachovnici();
            } else {
                System.out.println("");
                System.out.println("--- Cerny na tahu ---");
                hrac2.tahni(sachovnice, new Tah());
                skok();
                sachovnice.vypisSachovnici();

            }
            vybirac++;
        
        System.out.println();
        if (vybirac % 2 == 0) {
            System.out.println("Vyhral bily");
        } else {
            System.out.println("Vyhral cerny");
        }
        System.out.println();*/
    }
}
