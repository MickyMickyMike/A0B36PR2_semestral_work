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
public class HracPC implements Hrac {

    @Override
    public void tahni(Sachovnice sachovnice) {
        try{
            Thread.sleep(50);
        } catch (InterruptedException ie){          
        }
        int cislo;
        int pismeno;
        boolean go = true;
        while (go) {
            cislo = (int) (Math.random() * sachovnice.getLength());
            pismeno = (int) (Math.random() * sachovnice.getLength());
            if (sachovnice.getSach()[cislo][pismeno] == 2 || sachovnice.getSach()[cislo][pismeno] == -2) {
                if (sachovnice.barva(sachovnice.getSach()[cislo][pismeno]) == sachovnice.kdoHraje() && go) {
                    for (int i = 0; i < 100; i++) {
                        int cislo2 = (int) (Math.random() * sachovnice.getLength());
                        int pismeno2 = (int) (Math.random() * sachovnice.getLength());
                        if (sachovnice.pohybDama(cislo, pismeno, cislo2, pismeno2)) {
                            sachovnice.zamenFigurky(pismeno, cislo, pismeno2, cislo2);
                            go = false;
                        }
                    }
                }
            }
            if (sachovnice.getSach()[cislo][pismeno] == 1 || sachovnice.getSach()[cislo][pismeno] == -1) {
                if (sachovnice.barva(sachovnice.getSach()[cislo][pismeno]) == sachovnice.kdoHraje() && go) {
                    if (sachovnice.barva(sachovnice.getSach()[cislo][pismeno])) {          //bily
                        if (cislo > 1 && pismeno < sachovnice.getLength() - 2) {
                            if (sachovnice.skok(cislo, pismeno, cislo - 2, pismeno + 2) && go) {
                                sachovnice.zamenFigurky(pismeno, cislo, pismeno + 2, cislo - 2);
                                go = false;
                            }
                        }
                        if (cislo > 1 && pismeno > 1) {
                            if (sachovnice.skok(cislo, pismeno, cislo - 2, pismeno - 2) && go) {
                                sachovnice.zamenFigurky(pismeno, cislo, pismeno - 2, cislo - 2);
                                go = false;
                            }
                        }
                        if (cislo > 0 && pismeno < sachovnice.getLength() - 1) {
                            if (sachovnice.pohyb(cislo, pismeno, cislo - 1, pismeno + 1) && go) {
                                sachovnice.zamenFigurky(pismeno, cislo, pismeno + 1, cislo - 1);
                                go = false;
                            }
                        }
                        if (cislo > 0 && pismeno > 0) {
                            if (sachovnice.pohyb(cislo, pismeno, cislo - 1, pismeno - 1) && go) {
                                sachovnice.zamenFigurky(pismeno, cislo, pismeno - 1, cislo - 1);
                                go = false;
                            }
                        }
                    }
                    if (!sachovnice.barva(sachovnice.getSach()[cislo][pismeno])) {          //cerny
                        if (cislo < sachovnice.getLength() - 2 && pismeno < sachovnice.getLength() - 2) {
                            if (sachovnice.skok(cislo, pismeno, cislo + 2, pismeno + 2) && go) {
                                sachovnice.zamenFigurky(pismeno, cislo, pismeno + 2, cislo + 2);
                                go = false;
                            }
                        }
                        if (cislo < sachovnice.getLength() - 2 && pismeno > 1) {
                            if (sachovnice.skok(cislo, pismeno, cislo + 2, pismeno - 2) && go) {
                                sachovnice.zamenFigurky(pismeno, cislo, pismeno - 2, cislo + 2);
                                go = false;
                            }
                        }
                        if (cislo < sachovnice.getLength() - 1 && pismeno < sachovnice.getLength() - 1) {
                            if (sachovnice.pohyb(cislo, pismeno, cislo + 1, pismeno + 1) && go) {
                                sachovnice.zamenFigurky(pismeno, cislo, pismeno + 1, cislo + 1);
                                go = false;
                            }
                        }
                        if (cislo < sachovnice.getLength() - 1 && pismeno > 0) {
                            if (sachovnice.pohyb(cislo, pismeno, cislo + 1, pismeno - 1) && go) {
                                sachovnice.zamenFigurky(pismeno, cislo, pismeno - 1, cislo + 1);
                                go = false;
                            }
                        }
                    }
                }
            }         
        }
    }
}
