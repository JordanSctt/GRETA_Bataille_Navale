package fr.greta.java;

import java.util.ArrayList;
import java.util.List;

public class Partie {

    private Joueur joueur1;
    private Joueur joueur2;
    public Joueur joueurCourant;

    private List<Bateau> armadaJoueur1 = new ArrayList<>();
    private List<Bateau> armadaJoueur2 = new ArrayList<>();

    private ConverteurPosition converteur = new ConverteurPosition();


    //-----------------------------------------------------------
    public void setJoueur1(Joueur joueur1) {
        this.joueur1 = joueur1;
    }
    public void setJoueur2(Joueur joueur2) {
        this.joueur2 = joueur2;
    }
    //-----------------------------------------------------------

    // fonction principale pour le placement des bateaux dans la partie, s'assure que le bateau est bien sur la grille, que le bateau fait la bonne taille,
    // que les bateaux ne se traverse pas et enfin place l'ensemble des bateaux des joueurs dans leur "armada" avec leurs types et leurs positions.
    public void placer(Joueur joueur, BateauType type, Position positionAvant, Position positionArriere) throws PlacerBateauException {
        try {
            if (!estDansLaGrille(positionAvant) || !estDansLaGrille(positionArriere)) {
                throw new PlacerBateauException("Le positionnement n'est pas dans la grille de jeu'");
            }
            List<Position> positions = converteur.convertir(positionAvant, positionArriere);
            if (positions.size() != type.nbCase()) {
                throw new PlacerBateauException("Le positionnement ne correspond pas à la taille du bateau");
            }
            List<Bateau> armada = armada(joueur);
            for (Bateau chaqueBateauDejaPlace : armada) {
                if (chaqueBateauDejaPlace.traverse(positions)) {
                    throw new PlacerBateauException("Le positionnement chevauche un autre bateau");
                }
            }
            Bateau bateau = new Bateau(type);
            bateau.placerBateau(positions);
            armada.add(bateau);
        } catch (ConverteurException e) {
            throw new PlacerBateauException("Le positionnement ne correspond pas à la taille du bateau");
        }
    }


    // Fct principal de Tire, s'assure que le tire est bien sur la grille de jeu, vérifie si un tire touche un bateau ou non et si il le coule.
    public TirerRetour tirer(Joueur joueur, Position position) throws TirerException {
        if (!estDansLaGrille(position)) {
            throw new TirerException("Le tir n'est pas sur la grille de jeu");
        }
        List<Bateau> armada = armadaDeAdversaire(joueur);
        for (Bateau eachBateauAdverse : armada) {
            if (eachBateauAdverse.estSur(position)) {
                eachBateauAdverse.bateauTouche(position);
                if (eachBateauAdverse.bateauCoule()){
                    return TirerRetour.TOUCHER_COULER;
                }
                return TirerRetour.TOUCHER;
            }
        }
        return TirerRetour.MANQUER;
    }


    //-----------------------------------------------------------
    //Récupére dans un tableau l'ensemble des bateaux à placer
    /*public List<BateauType> bateauAPlacer() {
        List<BateauType> bateauAPlacer = new ArrayList<>();
        for (BateauType eachBateauType : BateauType.values()){
            bateauAPlacer.add(eachBateauType);
        }
        return bateauAPlacer;
    }*/

    // S'assure que les positions Av. et Ar. du bateau ne sortent pas de la grille (10x10)
    private boolean estDansLaGrille(Position positions) {
        if (positions.getX() > 9 || positions.getX() < 0) {
            return false;
        }
        if (positions.getY() > 9 || positions.getY() < 0) {
            return false;
        }
        return true;
    }

    //Créer l'armada des bateaux pour chaque joueur
    private List<Bateau> armada(Joueur joueur) {
        if (joueur.equals(joueur1)) {
            return armadaJoueur1;
        }
        return armadaJoueur2;
    }

    //Créer l'armada de l'adversaire pour les tirs du joueur adverse
    private List<Bateau> armadaDeAdversaire(Joueur joueur) {
        if (joueur.equals(joueur1)) {
            return armadaJoueur2;
        }
        return armadaJoueur1;
    }

    //Fct qui utilise le controle des armadas pour déterminer si la partie est fini
    public boolean estTermine(Joueur joueur) {
        if (joueur.equals(joueur1)) {
            return controleArmada(armadaJoueur2);
        }
        return controleArmada(armadaJoueur1);
    }

    // Fct qui contrôle  les armadas des joueurs afin de déterminer si ils sont tous coulés ou non
    private boolean controleArmada(List<Bateau> armada) {
        for (Bateau chaqueBateau : armada) {
            if (!chaqueBateau.bateauCoule()) {
                return false;
            }
        }
        return true;
    }

    //Fct pour changer de joueur courant
    public void changerJoueurCourant() {
        if (joueurCourant.equals(joueur1)) {
            joueurCourant = joueur2;
        } else {
            joueurCourant = joueur1;
        }
    }


}
