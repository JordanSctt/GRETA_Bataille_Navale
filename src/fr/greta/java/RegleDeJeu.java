package fr.greta.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RegleDeJeu {

    public static UtilisateurInterface ui  = new UtilisateurInterface();
    Scanner scan = new Scanner(System.in);

    public void choisirPseudoJoueur (Joueur joueur) {
        joueur.setPseudo(scan.next());
    }

    public List<BateauType> ChoisirBateauxType () {
        List<BateauType> bateauTypeAPlacer = new ArrayList<>();

        for (BateauType eachBateauType : BateauType.values()) {
            if (ui.messageBateauTypeAPlacer(eachBateauType)){
                bateauTypeAPlacer.add(eachBateauType);
            }
        }
        return bateauTypeAPlacer;
    }
}
