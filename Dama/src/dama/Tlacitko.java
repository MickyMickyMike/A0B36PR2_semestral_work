/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dama;

import javax.swing.JButton;

/**
 *trida reprezentujici policko na sachovnici
 * @author Trololo
 */
public class Tlacitko extends JButton {
    private int a;
    private int b;
    
    Tlacitko (int x, int y){
        super();
        this.a = x;
        this.b = y;
    }
   
    /**
     *souradnice urcujici cislo na sachovnici
     * @return a
     */
    public int getA () {
        return a;
    }
    
    /**
     *souradnice urcujici pismeno na sachovnici
     * @return b
     */
    public int getB () {
        return b;
    }
}
