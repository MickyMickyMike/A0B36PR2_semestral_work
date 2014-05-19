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
class Sachovnice {

    private int sirka;
    private int delka;
    private int[][] sachovnice;
    private int pocetBily;
    private int pocetCerny;
    private int pocitadlo = 1;

    Sachovnice() {
        this.sirka = 8;
        this.delka = 8;
        this.pocetBily = 12;
        this.pocetCerny = 12;
        sachovnice = new int[sirka][delka];
    }

    public void zamenFigurky(int pismenoOdkud, int cisloOdkud, int pismenoKam, int cisloKam) {
        if (this.sachovnice[cisloOdkud][pismenoOdkud] == 1 || this.sachovnice[cisloOdkud][pismenoOdkud] == -1) {
            if (cisloOdkud == cisloKam + 2) {                               //bily vymazani figurky
                if (pismenoOdkud == pismenoKam - 2) {
                    this.sachovnice[cisloOdkud - 1][pismenoOdkud + 1] = 0;
                    minusCerny();
                }
                if (pismenoOdkud == pismenoKam + 2) {
                    this.sachovnice[cisloOdkud - 1][pismenoOdkud - 1] = 0;
                    minusCerny();
                }
            }
            if (cisloOdkud == cisloKam - 2) {                               //cerny vymazani figurky
                if (pismenoOdkud == pismenoKam - 2) {
                    this.sachovnice[cisloOdkud + 1][pismenoOdkud + 1] = 0;
                    minusBily();
                }
                if (pismenoOdkud == pismenoKam + 2) {
                    this.sachovnice[cisloOdkud + 1][pismenoOdkud - 1] = 0;
                    minusBily();
                }
            }

        }
        if (this.sachovnice[cisloOdkud][pismenoOdkud] == 2 || this.sachovnice[cisloOdkud][pismenoOdkud] == -2) {
            if (cisloOdkud < cisloKam) {
                if (pismenoOdkud < pismenoKam) {
                    if (barva(sachovnice[cisloKam - 1][pismenoKam - 1]) && !kdoHraje()) {
                        this.sachovnice[cisloKam - 1][pismenoKam - 1] = 0;
                        minusBily();
                    }
                    if (!barva(sachovnice[cisloKam - 1][pismenoKam - 1]) && kdoHraje() && figurka(sachovnice[cisloKam - 1][pismenoKam - 1])) {
                        this.sachovnice[cisloKam - 1][pismenoKam - 1] = 0;
                        minusCerny();
                    }
                }
                if (pismenoOdkud > pismenoKam) {
                    if (barva(sachovnice[cisloKam - 1][pismenoKam + 1]) && !kdoHraje()) {
                        this.sachovnice[cisloKam - 1][pismenoKam + 1] = 0;
                        minusBily();
                    }
                    if (!barva(sachovnice[cisloKam - 1][pismenoKam + 1]) && kdoHraje() && figurka(sachovnice[cisloKam - 1][pismenoKam + 1])) {
                        this.sachovnice[cisloKam - 1][pismenoKam + 1] = 0;
                        minusCerny();
                    }
                }

            }
            if (cisloOdkud > cisloKam) {
                if (pismenoOdkud > pismenoKam) {
                    if (barva(sachovnice[cisloKam + 1][pismenoKam + 1]) && !kdoHraje()) {
                        this.sachovnice[cisloKam + 1][pismenoKam + 1] = 0;
                        minusBily();
                    }
                    if (!barva(sachovnice[cisloKam + 1][pismenoKam + 1]) && kdoHraje() && figurka(sachovnice[cisloKam + 1][pismenoKam + 1])) {
                        this.sachovnice[cisloKam + 1][pismenoKam + 1] = 0;
                        minusCerny();
                    }
                }
                if (pismenoOdkud < pismenoKam) {
                    if (barva(sachovnice[cisloKam + 1][pismenoKam - 1]) && !kdoHraje()) {
                        this.sachovnice[cisloKam + 1][pismenoKam - 1] = 0;
                        minusBily();
                    }
                    if (!barva(sachovnice[cisloKam + 1][pismenoKam - 1]) && kdoHraje() && figurka(sachovnice[cisloKam + 1][pismenoKam - 1])) {
                        this.sachovnice[cisloKam + 1][pismenoKam - 1] = 0;
                        minusCerny();
                    }
                }

            }
        }
        this.sachovnice[cisloKam][pismenoKam] = sachovnice[cisloOdkud][pismenoOdkud];
        this.sachovnice[cisloOdkud][pismenoOdkud] = 0;
        if (cisloKam == 0 && kdoHraje()) {                   //vytvoreni damy (bily)
            this.sachovnice[cisloKam][pismenoKam] = 2;
        }
        if (cisloKam == 7 && !kdoHraje()) {                  //vytvoreni damy (cerny)
            this.sachovnice[cisloKam][pismenoKam] = -2;
        }
        pocitadlo++;
    }

    public void minusCerny() {
        pocetCerny--;
    }

    public void minusBily() {
        pocetBily--;
    }
    
    public int getPocetBily(){
        return pocetBily;
    }
    
    public int getPocetCerny(){
        return pocetCerny;
    }

    public int getLength() {
        return this.sachovnice.length;
    }

    public int[][] getSach() {
        return this.sachovnice;
    }

    public boolean kdoHraje() {
        if (this.pocitadlo % 2 == 1) {
            return true;                //hraje bily
        } else {
            return false;            //hraje cerny
        }
    }

    public boolean hra() {
        if (this.pocetBily == 0 || this.pocetCerny == 0) {
            return false;
        }
        return true;
    }

    public boolean jeNaSachovnici(int cislo, int pismeno) {
        if (cislo >= 0 && cislo <= 7 && pismeno >= 0 && pismeno <= 7) {
            return true;
        } else {
            return false;
        }
    }

    public boolean figurka(int cislo) {
        if (cislo == 1 || cislo == 2 || cislo == -1 || cislo == -2) {
            return true;
        } else {
            return false;
        }
    }

    public boolean barva(int cislo) {
        if (cislo == 1 || cislo == 2) {
            return true;        //bila
        } else {
            return false;       //cerna
        }
    }

    public boolean pohyb(int cisloOdkud, int pismenoOdkud, int cisloKam, int pismenoKam) {      //kontroluje pohyb figurek (mimo damy)
        if (figurka(this.sachovnice[cisloOdkud][pismenoOdkud]) && !figurka(this.sachovnice[cisloKam][pismenoKam])) {
            if (barva(this.sachovnice[cisloOdkud][pismenoOdkud])) {         //bily
                if (cisloOdkud == cisloKam + 1) {
                    if (pismenoOdkud == pismenoKam - 1) {
                        return true;
                    }
                    if (pismenoOdkud == pismenoKam + 1) {
                        return true;
                    }
                }
            }
            if (!barva(this.sachovnice[cisloOdkud][pismenoOdkud])) {        //cerny
                if (cisloOdkud == cisloKam - 1) {
                    if (pismenoOdkud == pismenoKam - 1) {
                        return true;
                    }
                    if (pismenoOdkud == pismenoKam + 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean skok(int cisloOdkud, int pismenoOdkud, int cisloKam, int pismenoKam) {       //kontroluje skakani figurek (mimo damy)
        if (figurka(this.sachovnice[cisloOdkud][pismenoOdkud]) && !figurka(this.sachovnice[cisloKam][pismenoKam])) {
            if (barva(this.sachovnice[cisloOdkud][pismenoOdkud])) {         //bily
                if (cisloOdkud == cisloKam + 2) {
                    if (pismenoOdkud == pismenoKam - 2) {
                        if (figurka(this.sachovnice[cisloOdkud - 1][pismenoOdkud + 1])) {
                            if (!barva(this.sachovnice[cisloOdkud - 1][pismenoOdkud + 1])) {
                                return true;
                            }
                        }
                    }
                    if (pismenoOdkud == pismenoKam + 2) {
                        if (figurka(this.sachovnice[cisloOdkud - 1][pismenoOdkud - 1])) {
                            if (!barva(this.sachovnice[cisloOdkud - 1][pismenoOdkud - 1])) {
                                return true;
                            }
                        }
                    }
                }
            }
            if (!barva(this.sachovnice[cisloOdkud][pismenoOdkud])) {        //cerny
                if (cisloOdkud == cisloKam - 2) {
                    if (pismenoOdkud == pismenoKam - 2) {
                        if (barva(this.sachovnice[cisloOdkud + 1][pismenoOdkud + 1])) {
                            return true;
                        }
                    }
                    if (pismenoOdkud == pismenoKam + 2) {
                        if (barva(this.sachovnice[cisloOdkud + 1][pismenoOdkud - 1])) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean diagonala(int cisloOdkud, int pismenoOdkud, int cisloKam, int pismenoKam) {       //kontrola volne diagonaly pro damu
        boolean a = true;
        if (cisloOdkud < cisloKam) {
            if (pismenoOdkud < pismenoKam) {
                int j = pismenoOdkud + 1;
                for (int i = cisloOdkud + 1; i <= cisloKam; i++) {
                    if (sachovnice[i][j] != 0) {
                        a = false;
                    }
                    j++;
                }
                if (barva(sachovnice[cisloKam - 1][pismenoKam - 1]) != kdoHraje()) {
                    a = true;
                }
            }
            if (pismenoOdkud > pismenoKam) {
                int j = pismenoOdkud - 1;
                for (int i = cisloOdkud + 1; i <= cisloKam; i++) {
                    if (sachovnice[i][j] != 0) {
                        a = false;
                    }
                    j--;
                }
                if (barva(sachovnice[cisloKam - 1][pismenoKam + 1]) != kdoHraje()) {
                    a = true;
                }
            }

        }
        if (cisloOdkud > cisloKam) {
            if (pismenoOdkud > pismenoKam) {
                int j = pismenoOdkud - 1;
                for (int i = cisloOdkud - 1; i >= cisloKam; i--) {
                    if (sachovnice[i][j] != 0) {
                        a = false;
                    }
                    j--;
                }
                if (barva(sachovnice[cisloKam + 1][pismenoKam + 1]) != kdoHraje()) {
                    a = true;
                }
            }
            if (pismenoOdkud < pismenoKam) {
                int j = pismenoOdkud + 1;
                for (int i = cisloOdkud - 1; i >= cisloKam; i--) {
                    if (sachovnice[i][j] != 0) {
                        a = false;
                    }
                    j++;
                }
                if (barva(sachovnice[cisloKam + 1][pismenoKam - 1]) != kdoHraje()) {
                    a = true;
                }
            }
        }
        return a;
    }

    public boolean pohybDama(int cisloOdkud, int pismenoOdkud, int cisloKam, int pismenoKam) {
        if (sachovnice[cisloOdkud][pismenoOdkud] == 2 || sachovnice[cisloOdkud][pismenoOdkud] == -2) {
            if (!figurka(sachovnice[cisloKam][pismenoKam])) {
                if ((cisloKam - cisloOdkud == pismenoKam - pismenoOdkud) || cisloKam - cisloOdkud == -(pismenoKam - pismenoOdkud)) {
                    if (diagonala(cisloOdkud, pismenoOdkud, cisloKam, pismenoKam)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void naplnSachovnici() {
        for (int i = 0; i < 3; i++) {
            if (i == 1) {
                for (int j = 0; j < sachovnice[0].length; j++) {
                    if (j % 2 == 0) {
                        sachovnice[i][j] = -1;
                    } else {
                        sachovnice[i][j] = 0;
                    }
                }
            } else {
                for (int j = 0; j < sachovnice[0].length; j++) {
                    if (j % 2 == 0) {
                        sachovnice[i][j] = 0;
                    } else {
                        sachovnice[i][j] = -1;
                    }
                }
            }
        }
        for (int i = 3; i < 5; i++) {
            for (int j = 0; j < sachovnice[0].length; j++) {
                sachovnice[i][j] = 0;
            }
        }
        for (int i = 5; i < sachovnice.length; i++) {
            if (i != 6) {
                for (int j = 0; j < sachovnice[0].length; j++) {
                    if (j % 2 == 0) {
                        sachovnice[i][j] = 1;
                    } else {
                        sachovnice[i][j] = 0;
                    }
                }
            } else {
                for (int j = 0; j < sachovnice[0].length; j++) {
                    if (j % 2 == 0) {
                        sachovnice[i][j] = 0;
                    } else {
                        sachovnice[i][j] = 1;
                    }
                }
            }
        }
    }

    public void vypisSachovnici() {
        System.out.println("     A     B     C     D     E     F     G     H");
        System.out.println("   ------------------------------------------------");
        for (int i = 0; i < sachovnice.length; i++) {

            System.out.print(sachovnice.length - i + " ");
            for (int j = 0; j < sachovnice[0].length; j++) {
                System.out.print("|");
                switch (sachovnice[i][j]) {
                    case (0): {
                        System.out.print("     ");
                    }
                    break;
                    case (1): {
                        System.out.print("  ☺  ");
                    }
                    break;
                    case (-1): {
                        System.out.print("  ☻  ");
                    }
                    break;
                    case (2): {
                        System.out.print(" < > ");
                    }
                    break;
                    case (-2): {
                        System.out.print(" ◄► ");
                    }
                    break;
                }
            }
            System.out.print("|");
            System.out.println();
            System.out.println("   ------------------------------------------------");
        }

        System.out.println("     A     B     C     D     E     F     G     H");
    }

    public static void main(String[] args) {
        Sachovnice sachovnice = new Sachovnice();
        sachovnice.naplnSachovnici();
        sachovnice.vypisSachovnici();

    }
}
