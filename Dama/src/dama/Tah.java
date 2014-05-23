/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dama;

/**
 *trida reprezentujici tah
 * @author Trololo
 */
public class Tah {

    private int cisloOdkud;
    private int pismenoOdkud;
    private int cisloKam;
    private int pismenoKam;

    Tah() {
        cisloOdkud = 20;
        pismenoOdkud = 20;
        cisloKam = 20;
        pismenoKam = 20;
    }

    /**
     *
     * @param cisloOdkud
     */
    public void setCisloOdkud(int cisloOdkud) {
        this.cisloOdkud = cisloOdkud;
    }

    /**
     *
     * @param pismenoOdkud
     */
    public void setPismenoOdkud(int pismenoOdkud) {
        this.pismenoOdkud = pismenoOdkud;
    }

    /**
     *
     * @param cisloKam
     */
    public void setCisloKam(int cisloKam) {
        this.cisloKam = cisloKam;
    }

    /**
     *
     * @param pismenoKam
     */
    public void setPismenoKam(int pismenoKam) {
        this.pismenoKam = pismenoKam;
    }

    /**
     *
     * @return cisloOdkud
     */
    public int getCisloOdkud() {
        return cisloOdkud;
    }

    /**
     *
     * @return pismenoOdkud
     */
    public int getPismenoOdkud() {
        return pismenoOdkud;
    }

    /**
     *
     * @return cisloKam
     */
    public int getCisloKam() {
        return cisloKam;
    }

    /**
     *
     * @return pismenoKam
     */
    public int getPismenoKam() {
        return pismenoKam;
    }

    @Override
    public String toString() {
        return cisloOdkud + "" + pismenoOdkud + "" + cisloKam + "" + pismenoKam;
    }

    /**
     *prevod str na 4 integery odpovidajici souradnicim tahu
     * @param str
     */
    public void prevodTahu(String str) {
        setCisloOdkud(str.charAt(0) - '0');
        setPismenoOdkud(str.charAt(1) - '0');
        setCisloKam(str.charAt(2) - '0');
        setPismenoKam(str.charAt(3) - '0');
    }

}
