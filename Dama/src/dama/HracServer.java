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
 * trida zastupujici hrace server s cernymi figurkami
 *
 * @author Trololo
 */
public class HracServer extends Hrac implements ActionListener {

    private int counter = 0;
    private Tah tah;
    private Sachovnice sachovnice;
    private ServerSocket sersock;
    private Socket sock;
    private OutputStream ostream;
    private PrintWriter pwrite;
    private InputStream istream;
    private BufferedReader receiveRead;
    private String receiveMessage, sendMessage;

    /**
     * vytvori server, schopny komunikovat s klientem. Pred vytvorenim prijme
     * prvni tah od soupere a ulozi do promenne tah, a tah provede.
     */
    HracServer(Sachovnice sach) {
        sachovnice = sach;
        tah = new Tah();
        try {
            sersock = new ServerSocket(9876);
            sock = sersock.accept();
            ostream = sock.getOutputStream();
            pwrite = new PrintWriter(ostream, true);
            istream = sock.getInputStream();
            receiveRead = new BufferedReader(new InputStreamReader(istream));
        } catch (IOException e) {
            System.out.println(e);
        }
        while (receiveMessage == null) {
            try {
                receiveMessage = receiveRead.readLine();
                tah.prevodTahu(receiveMessage);
            } catch (IOException b) {
                System.out.println(b);
            }
        }
        sachovnice.zamenFigurky(tah.getPismenoOdkud(), tah.getCisloOdkud(), tah.getPismenoKam(), tah.getCisloKam());
    }

    /**
     * provadi tah, pokud je v souladu s pravidly
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
     * posila zpravy klientovi
     *
     * @param tah
     */
    public void send(Tah tah) {
        sendMessage = tah.toString();
        pwrite.println(sendMessage);
        System.out.flush();
    }

    /**
     * prijima zpravy od klienta
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
     * pokud je hrac na tahu, prijima od tlacitek souradnice "odkud tahnout" pro
     * liche akce a "kam tahnout" pro sude akce. Pokud hrac neni na tahu, zapne
     * prijimani zprav.
     */
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
        } else {
            receive();
        }
    }
}
