/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dama;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

/**
 *trida zastupujici hrace klienta s bilymi figurkami
 * @author Trololo
 */
public class HracClient extends Hrac implements ActionListener {

    private int counter = 0;
    private Tah tah;
    private Sachovnice sachovnice;
    private Socket sock;
    private OutputStream ostream;
    private PrintWriter pwrite;
    private InputStream istream;
    private BufferedReader receiveRead;
    private String receiveMessage, sendMessage;

    /**
     *vytvori klienta, pripojeneho k serveru, schopneho prijimat a odesilat zpravy
     * @param sachovnice
     */
    HracClient(Sachovnice sach) {
        sachovnice = sach;
        tah = new Tah();
        try {
            sock = new Socket(sachovnice.getPlocha().zadejIP(), 9876);
            ostream = sock.getOutputStream();
            pwrite = new PrintWriter(ostream, true);
            istream = sock.getInputStream();
            receiveRead = new BufferedReader(new InputStreamReader(istream));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     *provadi tah, pokud je v souladu s pravidly
     * @param sachovnice
     * @param tah
     */
    @Override
    public void tahni(Sachovnice sachovnice, Tah tah) {
        boolean odkud = false;
        if (sachovnice.figurka(sachovnice.getSach()[tah.getCisloOdkud()][tah.getPismenoOdkud()])) {
            if ((sachovnice.barva(sachovnice.getSach()[tah.getCisloOdkud()][tah.getPismenoOdkud()]) && sachovnice.kdoHraje()) || (!sachovnice.barva(sachovnice.getSach()[tah.getCisloOdkud()][tah.getPismenoOdkud()]) && !sachovnice.kdoHraje())) {
                odkud = true;
            }
        }
        boolean kam = false;
        if (!sachovnice.figurka(sachovnice.getSach()[tah.getCisloKam()][tah.getPismenoKam()])) {
            kam = true;
        }
        if (!sachovnice.pohyb(tah.getCisloOdkud(), tah.getPismenoOdkud(), tah.getCisloKam(), tah.getPismenoKam()) && !sachovnice.skok(tah.getCisloOdkud(), tah.getPismenoOdkud(), tah.getCisloKam(), tah.getPismenoKam())) {
            kam = false;       //pokud neni tah v souladu s pravidly
        }
        if (sachovnice.pohybDama(tah.getCisloOdkud(), tah.getPismenoOdkud(), tah.getCisloKam(), tah.getPismenoKam())) {
            kam = true;
        }
        if (odkud && kam) {
            sachovnice.zamenFigurky(tah.getPismenoOdkud(), tah.getCisloOdkud(), tah.getPismenoKam(), tah.getCisloKam());
            send(tah);
        }
        sachovnice.vypisSachovnici();
    }

    /**
     *posila zpravy serveru
     * @param tah
     */
    public void send(Tah tah) {
        sendMessage = tah.toString();
        pwrite.println(sendMessage);
        System.out.flush();
    }

    /**
     *prijima zpravy od serveru
     */
    public void receive() {
        while (receiveMessage == null) {
            try {
                receiveMessage = receiveRead.readLine();
                tah.prevodTahu(receiveMessage);
                sachovnice.zamenFigurky(tah.getPismenoOdkud(), tah.getCisloOdkud(), tah.getPismenoKam(), tah.getCisloKam());
            } catch (IOException b) {
                System.out.println(b);
            }
        }
        receiveMessage = null;
    }

    /**
     *
     * @return counter
     */
    public int getCounter() {
        return counter;
    }

    /**
     *set counter
     * @param counter
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }

    /**
     *
     * @return tah
     */
    public Tah getTah() {
        return tah;
    }

    /**
     *set tah
     * @param tah
     */
    public void setTah(Tah tah) {
        this.tah = tah;
    }

    /**
     *
     * @return sachovnice
     */
    public Sachovnice getSachovnice() {
        return sachovnice;
    }

    /**
     *set sachovnice
     * @param sachovnice
     */
    public void setSachovnice(Sachovnice sachovnice) {
        this.sachovnice = sachovnice;
    }

    /**
     *
     * @return sock
     */
    public Socket getSock() {
        return sock;
    }

    /**
     * set sock
     * @param sock
     */
    public void setSock(Socket sock) {
        this.sock = sock;
    }

    /**
     * pokud je hrac na tahu, prijima od tlacitek souradnice "odkud tahnout" pro
     * liche akce a "kam tahnout" pro sude akce. Pokud hrac neni na tahu, zapne
     * prijimani zprav.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (sachovnice.kdoHraje()) {
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
        } else {
            receive();
        }
    }
}
