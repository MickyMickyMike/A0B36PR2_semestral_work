/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dama;

/**
 * trida zastupujici sachovnici
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
    private Hrac hrac;
    private GUI plocha;

    /**
     * vytvoreni sachovnice 8x8 s 12 figurkami na kazde strane vytvoreni
     * graficke hraci plochy a hrace
     */
    Sachovnice() {
        this.sirka = 8;
        this.delka = 8;
        this.pocetBily = 12;
        this.pocetCerny = 12;
        sachovnice = new int[sirka][delka];
        naplnSachovnici();
        plocha = new GUI(this);
        hrac();
        plocha.nastaveniHrace(this);
    }

    /**
     * zamenuje figurky na sachovnici podle tahu ktery je zadan 4 parametry po
     * zamene vypise sachovnici a zkontroluje, jestli neni konec hry
     *
     * @param pismenoOdkud
     * @param cisloOdkud
     * @param pismenoKam
     * @param cisloKam
     */
    public void zamenFigurky(int pismenoOdkud, int cisloOdkud, int pismenoKam, int cisloKam) {
        //if (lzeTahnout(cisloOdkud, pismenoOdkud, cisloKam, pismenoKam)) {
        if (this.sachovnice[cisloOdkud][pismenoOdkud] == 1 || this.sachovnice[cisloOdkud][pismenoOdkud] == -1) { //pesec
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
        if (this.sachovnice[cisloOdkud][pismenoOdkud] == 2 || this.sachovnice[cisloOdkud][pismenoOdkud] == -2) { //dama
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
                // }
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
        this.vypisSachovnici();
        if (!hra()) {
            plocha.konecHry(this);
        }
    }

    /**
     * nastavi typ hrace
     */
    public void hrac() {
        if (plocha.vyberHrace() == 0) {
            setHrac(new HracClient(this));
        } else {
            setHrac(new HracServer(this));
        }
    }

    /*
     public boolean lzeTahnout(int cisloOdkud, int pismenoOdkud, int cisloKam, int pismenoKam) {
     boolean odkud = false;
     if (jeNaSachovnici(cisloOdkud, pismenoOdkud)) {      //kontroluje, zda existuje policko, pokud ano, jestli je na nem prislusna figurka
     if (figurka(getSach()[cisloOdkud][pismenoOdkud])) {
     if ((barva(getSach()[cisloOdkud][pismenoOdkud]) && kdoHraje()) || (!barva(getSach()[cisloOdkud][pismenoOdkud]) && !kdoHraje())) {
     odkud = true;
     }
     }
     }
     boolean kam = false;
     if (jeNaSachovnici(cisloKam, pismenoKam)) {      //zda existuje policko, pokud ano, jestli je prazdne
     if (!figurka(getSach()[cisloKam][pismenoKam])) {
     kam = true;
     }
     }
     if (!pohyb(cisloOdkud, pismenoOdkud, cisloKam, pismenoKam) && !skok(cisloOdkud, pismenoOdkud, cisloKam, pismenoKam)) {
     kam = false;       //pokud neni tah v souladu s pravidly
     }
     if (jeNaSachovnici(cisloKam, pismenoKam) && pohybDama(cisloOdkud, pismenoOdkud, cisloKam, pismenoKam)) {
     kam = true;
     }
     if (odkud && kam) {
     return true;
     } else {
     return false;
     }

     }*/
    /**
     * snizi pocet figurek cerneho
     */
    public void minusCerny() {
        pocetCerny--;
    }

    /**
     * snizi pocet figurek bileho
     */
    public void minusBily() {
        pocetBily--;
    }

    /**
     *
     * @return pocitadlo
     */
    public int getPocitadlo() {
        return pocitadlo;
    }

    /**
     * set pocitadlo
     *
     * @param pocitadlo
     */
    public void setPocitadlo(int pocitadlo) {
        this.pocitadlo = pocitadlo;
    }

    /**
     *
     * @return plocha
     */
    public GUI getPlocha() {
        return plocha;
    }

    /**
     *
     * @param plocha
     */
    public void setPlocha(GUI plocha) {
        this.plocha = plocha;
    }

    /**
     *
     * @return hrac
     */
    public Hrac getHrac() {
        return hrac;
    }

    /**
     *
     * @param hrac
     */
    public void setHrac(Hrac hrac) {
        this.hrac = hrac;
    }

    /**
     * vraci pocet figurek bileho
     *
     * @return pocetBily
     */
    public int getPocetBily() {
        return pocetBily;
    }

    /**
     * vraci pocet figurek cerneho
     *
     * @return pocetCerny
     */
    public int getPocetCerny() {
        return pocetCerny;
    }

    /**
     *
     * @return delka sachovnice
     */
    public int getLength() {
        return this.sachovnice.length;
    }

    /**
     *
     * @return sachovnice
     */
    public int[][] getSach() {
        return this.sachovnice;
    }

    /**
     * 
     * @return hrac na tahu
     */
    public boolean kdoHraje() {
        if (this.pocitadlo % 2 == 1) {
            return true;                //hraje bily
        } else {
            return false;            //hraje cerny
        }
    }

    /**
     * kontroluje, jestli maji oba hraci stale nejakou figurku
     * @return boolean
     */
    public boolean hra() {
        if (this.pocetBily == 0 || this.pocetCerny == 0) {
            return false;
        }
        return true;
    }
    
    /*
     public boolean jeNaSachovnici(int cislo, int pismeno) {
     if (cislo >= 0 && cislo <= 7 && pismeno >= 0 && pismeno <= 7) {
     return true;
     } else {
     return false;
     }
     }*/

    /**
     * kontroluje, jestli je na pozici "cislo" figurka
     * @param cislo
     * @return boolean
     */
    public boolean figurka(int cislo) {
        if (cislo == 1 || cislo == 2 || cislo == -1 || cislo == -2) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 
     * @param cislo
     * @return 
     */
    public boolean barva(int cislo) {
        if (cislo == 1 || cislo == 2) {
            return true;        //bila
        } else {
            return false;       //cerna
        }
    }

    /**
     * kontroluje, jestli je tah pěšce (pohyb) v souladu s pravidly
     * @param cisloOdkud
     * @param pismenoOdkud
     * @param cisloKam
     * @param pismenoKam
     * @return 
     */
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

    /**
     * kontroluje, jestli je tah pěšce (skok) v souladu s pravidly
     * @param cisloOdkud
     * @param pismenoOdkud
     * @param cisloKam
     * @param pismenoKam
     * @return 
     */
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

    /**
     * kontroluje volnou diagonalu mezi pocatecnimi souradnicemi a koncovymi souradnicemi
     * @param cisloOdkud
     * @param pismenoOdkud
     * @param cisloKam
     * @param pismenoKam
     * @return 
     */
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
                if (barva(sachovnice[cisloKam - 1][pismenoKam - 1]) != kdoHraje() && figurka(sachovnice[cisloKam - 1][pismenoKam - 1])) {
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
                if (barva(sachovnice[cisloKam - 1][pismenoKam + 1]) != kdoHraje() && figurka(sachovnice[cisloKam - 1][pismenoKam + 1])) {
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
                if (barva(sachovnice[cisloKam + 1][pismenoKam + 1]) != kdoHraje() && figurka(sachovnice[cisloKam + 1][pismenoKam + 1])) {
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
                if (barva(sachovnice[cisloKam + 1][pismenoKam - 1]) != kdoHraje() && figurka(sachovnice[cisloKam + 1][pismenoKam - 1])) {
                    a = true;
                }
            }
        }
        return a;
    }

    /**
     * kontroluje, jestli je pohyb damy v souladu  pravidly
     * @param cisloOdkud
     * @param pismenoOdkud
     * @param cisloKam
     * @param pismenoKam
     * @return 
     */
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

    /**
     * nastavi pocatecni rozmisteni figurek na sachovnici.
     * figurky stoji na bilych hracich polích, ve trech radach na obou stranach
     */
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

    /*
    vola metodu GUI vypisTlacitek
    */
    public void vypisSachovnici() {
        plocha.vypisTlacitek(this);

        /*
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
         }           }
         System.out.print("|");
         System.out.println();
         System.out.println("   ------------------------------------------------");
         }

         System.out.println("     A     B     C     D     E     F     G     H");
         */
    }
}
