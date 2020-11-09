package fr.greta.java;

import java.util.Scanner;

public class UtilisateurInterface {

    Scanner scan = new Scanner(System.in);

    public void messageDebutPartie () {
        System.out.println("Bienvenue sur la plus grande des batailles navale !!");
    }

    public void messageDebutPlacerBateau(Joueur joueur1) {
        System.out.println("C'est à [" + joueur1.getPseudo() + "] de commencer");
    }

    public void messagePlacerBateau(BateauType type) {
        System.out.println("On va placer le bateau: " + type);
    }

    public void messageRecupererPositionAvant() {
        System.out.println("Nous allons placer l'avant du bateau");
    }

    public void messageRecupererPositionArriere() {
        System.out.println("Merci de saisir la position arrière du bateau");
    }

    public Position recupererPosition() {
        while(true) {
            try {
                Position position = new Position();
                System.out.println("Merci de saisir la position X");
                position.setX(scan.nextInt());
                System.out.println("Merci de saisir la position Y");
                position.setY(scan.nextInt());
                return position;
            } catch (Exception e) {
                System.err.println("Saisie invalide !! recommencer !");
            }
        }
    }

    public void messageErreurPlacementBateau(String message) {
        System.err.println(message);
    }

    public void messageDemanderTire(Joueur joueur1) {
        System.out.println("C'est à [" + joueur1.getPseudo() + "] de tirer");
    }

    public void messageBateauTouche() {
        System.out.println("Vous avez touché un bateau !!");
    }

    public void messageBateauCoule() {
        System.out.println("Vous avez coulé un bateau !!");
    }

    public void messageBateauManque() {
        System.out.println("Vous avez manqué votre tir...");
    }

    public void messageChoisirPseudoPremierJoueur() {
        System.out.println("Joueur1 - Merci de saisir votre pseudo: ");
    }
    public void messageChoisirPseudoDeuxiemeJoueur() {
        System.out.println("Joueur2 - Merci de saisir votre pseudo: ");
    }

    public boolean messageBateauTypeAPlacer(BateauType eachBateauType) {
        System.out.println("Voulez vous placer le " + eachBateauType.name().toLowerCase() + " ? : [O]pour oui/[N] pour non");

        String retour = scan.next();
        if (retour.equals("O")) {
            return true;
        }
        return false;
    }
}
