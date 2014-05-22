/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dama;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
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

    public void setCisloOdkud(int cisloOdkud) {
        this.cisloOdkud = cisloOdkud;
    }

    public void setPismenoOdkud(int pismenoOdkud) {
        this.pismenoOdkud = pismenoOdkud;
    }

    public void setCisloKam(int cisloKam) {
        this.cisloKam = cisloKam;
    }

    public void setPismenoKam(int pismenoKam) {
        this.pismenoKam = pismenoKam;
    }

    public int getCisloOdkud() {
        return cisloOdkud;
    }

    public int getPismenoOdkud() {
        return pismenoOdkud;
    }

    public int getCisloKam() {
        return cisloKam;
    }

    public int getPismenoKam() {
        return pismenoKam;
    }

    @Override
    public String toString() {
        return cisloOdkud + "" + pismenoOdkud + "" + cisloKam + "" + pismenoKam;
    }

    public void prevodTahu(String str) {
        setCisloOdkud(str.charAt(0) - '0');
        setPismenoOdkud(str.charAt(1) - '0');
        setCisloKam(str.charAt(2) - '0');
        setPismenoKam(str.charAt(3) - '0');
    }

}
