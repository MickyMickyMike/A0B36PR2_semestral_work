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
import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    public GUI() {
        super("Hra dama");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        Container kon = getContentPane(); // vrací kontejner
        kon.setBackground(Color.magenta);
        GridLayout srg = new GridLayout(8, 8);
        kon.setLayout(srg);
        tlacitka (kon);
        setSize(800, 800);
    }
    
    void tlacitka (Container kon) {
        JButton a8 = new JButton("A8");
        kon.add(a8);
        JButton b8 = new JButton("B8");
        kon.add(b8);
        JButton c8 = new JButton("C8");
        kon.add(c8);
        JButton d8 = new JButton("D8");
        kon.add(d8);
        JButton e8 = new JButton("E8");
        kon.add(e8);
        JButton f8 = new JButton("F8");
        kon.add(f8);
        JButton g8 = new JButton("G8");
        kon.add(g8);
        JButton h8 = new JButton("H8");
        kon.add(h8);
        JButton a7 = new JButton("A7");
        kon.add(a7);
        JButton b7 = new JButton("B7");
        kon.add(b7);
        JButton c7 = new JButton("C7");
        kon.add(c7);
        JButton d7 = new JButton("D7");
        kon.add(d7);
        JButton e7 = new JButton("E7");
        kon.add(e7);
        JButton f7 = new JButton("F7");
        kon.add(f7);
        JButton g7 = new JButton("G7");
        kon.add(g7);
        JButton h7 = new JButton("H7");
        kon.add(h7);
        JButton a6 = new JButton("A6");
        kon.add(a6);
        JButton b6 = new JButton("B6");
        kon.add(b6);
        JButton c6 = new JButton("C6");
        kon.add(c6);
        JButton d6 = new JButton("D6");
        kon.add(d6);
        JButton e6 = new JButton("E6");
        kon.add(e6);
        JButton f6 = new JButton("F6");
        kon.add(f6);
        JButton g6 = new JButton("G6");
        kon.add(g6);
        JButton h6 = new JButton("H6");
        kon.add(h6);
        JButton a5 = new JButton("A5");
        kon.add(a5);
        JButton b5 = new JButton("B5");
        kon.add(b5);
        JButton c5 = new JButton("C5");
        kon.add(c5);
        JButton d5 = new JButton("D5");
        kon.add(d5);
        JButton e5 = new JButton("E5");
        kon.add(e5);
        JButton f5 = new JButton("F5");
        kon.add(f5);
        JButton g5 = new JButton("G5");
        kon.add(g5);
        JButton h5 = new JButton("H5");
        kon.add(h5);
        JButton a4 = new JButton("A4");
        kon.add(a4);
        JButton b4 = new JButton("B4");
        kon.add(b4);
        JButton c4 = new JButton("C4");
        kon.add(c4);
        JButton d4 = new JButton("D4");
        kon.add(d4);
        JButton e4 = new JButton("E4");
        kon.add(e4);
        JButton f4 = new JButton("F4");
        kon.add(f4);
        JButton g4 = new JButton("G4");
        kon.add(g4);
        JButton h4 = new JButton("H4");
        kon.add(h4);
        JButton a3 = new JButton("A3");
        kon.add(a3);
        JButton b3 = new JButton("B3");
        kon.add(b3);
        JButton c3 = new JButton("C3");
        kon.add(c3);
        JButton d3 = new JButton("D3");
        kon.add(d3);
        JButton e3 = new JButton("E3");
        kon.add(e3);
        JButton f3 = new JButton("F3");
        kon.add(f3);
        JButton g3 = new JButton("G3");
        kon.add(g3);
        JButton h3 = new JButton("H3");
        kon.add(h3);
        JButton a2 = new JButton("A2");
        kon.add(a2);
        JButton b2 = new JButton("B2");
        kon.add(b2);
        JButton c2 = new JButton("C2");
        kon.add(c2);
        JButton d2 = new JButton("D2");
        kon.add(d2);
        JButton e2 = new JButton("E2");
        kon.add(e2);
        JButton f2 = new JButton("F2");
        kon.add(f2);
        JButton g2 = new JButton("G2");
        kon.add(g2);
        JButton h2 = new JButton("H2");
        kon.add(h2);
        JButton a1 = new JButton("A1");
        kon.add(a1);
        JButton b1 = new JButton("B1");
        kon.add(b1);
        JButton c1 = new JButton("C1");
        kon.add(c1);
        JButton d1 = new JButton("D1");
        kon.add(d1);
        JButton e1 = new JButton("E1");
        kon.add(e1);
        JButton f1 = new JButton("F1");
        kon.add(f1);
        JButton g1 = new JButton("G1");
        kon.add(g1);
        JButton h1 = new JButton("H1");
        kon.add(h1);
        pack();
    }

    public static void main(String[] args) {
        GUI po = new GUI();
        po.pack();
        po.setVisible(true);
    }
}
