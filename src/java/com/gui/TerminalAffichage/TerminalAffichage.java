package com.gui.TerminalAffichage;

import com.model.Player ;

public class TerminalAffichage {
    public static void main(String[] args) throws Exception {
        System.out.println("-------- TOWER DEFENSE --------");
        Jeu jeu = new Jeu(new Player());
        jeu.play() ;
    }
}
