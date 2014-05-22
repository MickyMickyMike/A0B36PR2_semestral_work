/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dama;

import java.awt.event.ActionEvent;

/**
 *
 * @author Trololo
 */
public class PCalternativa extends Hrac {

    @Override
    public void tahni(Sachovnice sachovnice, Tah tah) {
        boolean povoleno = true;
        for (int i = 0; i < sachovnice.getSach().length; i++) {
            for (int j = 0; j < sachovnice.getSach()[0].length; j++) {
                if (i > 0 && j > 0) {
                    if (sachovnice.getSach()[i][j] == 1 && sachovnice.getSach()[i - 1][j - 1] == 0 && povoleno) {
                        sachovnice.zamenFigurky(j, i, j - 1, i - 1);
                        povoleno = false;
                    }
                }
                if (i > 0 && j < sachovnice.getSach()[0].length -1) {
                    if (sachovnice.getSach()[i][j] == 1 && sachovnice.getSach()[i - 1][j + 1] == 0 && povoleno) {
                        sachovnice.zamenFigurky(j, i, j + 1, i - 1);
                        povoleno = false;
                    }
                }

            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

}
