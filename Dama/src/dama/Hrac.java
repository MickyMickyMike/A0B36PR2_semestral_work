/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dama;

import java.awt.event.ActionListener;

/**
 *nadrazena trida pro vsechny hrace, urcuje schnopnost hracu tahnout, prijimat a odesilat zpravy
 * @author Trololo
 */
public abstract class Hrac implements ActionListener {

    /**
     *
     * @param sachovnice
     * @param tah
     */
    public void tahni(Sachovnice sachovnice, Tah tah) {
    }

    /**
     *metoda pro prijimani zprav od protihrace
     */
    public void receive(){
        
    }
    
    /**
     *metoda pro posilani zprav protihraci
     */
    public void send(){
        
    }

}
