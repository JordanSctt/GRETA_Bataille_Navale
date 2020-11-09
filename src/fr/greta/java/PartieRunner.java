package fr.greta.java;

import java.util.List;

public class PartieRunner {

    public static Partie partie = new Partie();
    public static UtilisateurInterface ui  = new UtilisateurInterface();
    public static RegleDeJeu regleDeJeu = new RegleDeJeu();

    public static void main(String[] args) throws TirerException {


        // ---------Exec. des paramétrage d'une partie-----------------------------------------

        ui.messageDebutPartie();

        Joueur joueur1 = new Joueur();
        ui.messageChoisirPseudoPremierJoueur();
        regleDeJeu.choisirPseudoJoueur(joueur1);
        partie.setJoueur1(joueur1);

        Joueur joueur2 = new Joueur();
        ui.messageChoisirPseudoDeuxiemeJoueur();
        regleDeJeu.choisirPseudoJoueur(joueur2);
        partie.setJoueur2(joueur2);

        List<BateauType> bateauxChoisis = regleDeJeu.ChoisirBateauxType();


        //----------------Exec. du placement de bateaux pour chaque joueur---------------------
        lancementPlacementBateaux(joueur1, bateauxChoisis);
        lancementPlacementBateaux(joueur2, bateauxChoisis);

        //------Exec. de la fonction de tir, en boucle par joueur/partie/étatTir/terminé-------
        TirerRetour etatTir = null;
        partie.joueurCourant = joueur1;
        while (!partie.estTermine(partie.joueurCourant)){
            do {
                ui.messageDemanderTire(partie.joueurCourant);
                Position positionCible = ui.recupererPosition();
                etatTir = partie.tirer(partie.joueurCourant, positionCible);
                switch(etatTir) {
                    case TOUCHER:
                        ui.messageBateauTouche();
                        break;
                    case TOUCHER_COULER:
                        ui.messageBateauCoule();
                        break;
                    case MANQUER:
                        ui.messageBateauManque();
                        partie.changerJoueurCourant();
                }
            } while (etatTir != TirerRetour.MANQUER && !partie.estTermine(partie.joueurCourant));
        }
        System.out.println(partie.joueurCourant.getPseudo() + " à gagné la partie !! BRAVO !");
    }

    public static void lancementPlacementBateaux (Joueur joueur, List<BateauType> bateauxChoisis) {
        ui.messageDebutPlacerBateau(joueur); // "C'est à [joueur1] de commencer"
        for(BateauType type: bateauxChoisis) {
            boolean placer = false;
            while(!placer) {
                ui.messagePlacerBateau(type); // "On va placer le bateau : type"
                ui.messageRecupererPositionAvant(); // "saisir la position avant du bateau"
                Position positionAvant = ui.recupererPosition();
                ui.messageRecupererPositionArriere(); // "saisir la position arrière du bateau"
                Position positionArriere = ui.recupererPosition();
                try {
                    partie.placer(joueur, type, positionAvant, positionArriere);
                    placer = true;
                } catch (PlacerBateauException exception) {
                    ui.messageErreurPlacementBateau(exception.getMessage());
                }
            }
        }
    }
}
