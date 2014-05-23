/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dama;

/**
 *hlavni trida programu
 * @author Trololo
 */
public class Main {

    static void skok() {
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
    }

    /**
     *vytvori novy objekt sachovnice
     * @param args
     */
    public static void main(String[] args) {
        Sachovnice hra = new Sachovnice();
    }
}
