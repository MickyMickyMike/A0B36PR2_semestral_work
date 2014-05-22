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
 *
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

    HracClient(Sachovnice sach) {
        sachovnice = sach;
        tah = new Tah();
        try {
            sock = new Socket(sachovnice.getPlocha().zadejIP(), 9876);
            // sending to client (pwrite object)
            ostream = sock.getOutputStream();
            pwrite = new PrintWriter(ostream, true);
            // receiving from server ( receiveRead  object)
            istream = sock.getInputStream();
            receiveRead = new BufferedReader(new InputStreamReader(istream));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     *
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
     *
     * @param tah
     */
    public void send(Tah tah) {
        sendMessage = tah.toString();       
        pwrite.println(sendMessage);       
        System.out.flush();
    }

    /**
     *
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
     * @return
     */
    public int getCounter() {
        return counter;
    }

    /**
     *
     * @param counter
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }

    /**
     *
     * @return
     */
    public Tah getTah() {
        return tah;
    }

    /**
     *
     * @param tah
     */
    public void setTah(Tah tah) {
        this.tah = tah;
    }

    /**
     *
     * @return
     */
    public Sachovnice getSachovnice() {
        return sachovnice;
    }

    /**
     *
     * @param sachovnice
     */
    public void setSachovnice(Sachovnice sachovnice) {
        this.sachovnice = sachovnice;
    }

    /**
     *
     * @return
     */
    public Socket getSock() {
        return sock;
    }

    /**
     *
     * @param sock
     */
    public void setSock(Socket sock) {
        this.sock = sock;
    }

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
