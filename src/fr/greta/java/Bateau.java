package fr.greta.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bateau {

    private BateauType type;
    private Map<Position, Boolean> positionToucher = new HashMap<>();


    //----------Constructor, Getter et Setter ---------------------

    public Bateau(BateauType type) {
        this.type = type;
    }
    public BateauType getType() {
        return type;
    }
    public void setType(BateauType type) {
        this.type = type;
    }
    public Map<Position, Boolean> getPositionToucher() {
        return positionToucher;
    }
    public void setPositionToucher(Map<Position, Boolean> positionToucher) {
        this.positionToucher = positionToucher;
    }
    //-------------------------------------------------------------

    //Fct qui permet d'enregistrer la position d'un bateau
    public void placerBateau(List<Position> positions){
        for (Position position : positions){
            positionToucher.put(position, Boolean.FALSE);
        }
    }

    // S'assure que les positions Av. et Ar. ne sont pas l'une sur l'autre
    public boolean estSur(Position position) {
        for (Position positionBateau : positionToucher.keySet()){
            if (positionBateau.equals(position)){
                return true;
            }
        }
        return false;
    }

    // S'assure que l'ensemble des positions pour un bateau donné ne traverse pas un autre bateau déjà placé
    public boolean traverse(List<Position> positions) {
        for (Position positionRechercher : positions){
            if (estSur(positionRechercher)){
                return true;
            }
        }
        return false;
    }

    // Fct qui regarde si un tir a touché un bateau
    public void bateauTouche(Position position) {
        for (Position positionBateau : positionToucher.keySet()){
            if (positionBateau.equals(position)) {
                positionToucher.put(position, Boolean.TRUE);
            }
        }
    }
// Fct qui retourne vrai si un bateau est touché sur toute ces cases
    public boolean bateauCoule() {
        for (Position positionBateau : positionToucher.keySet()){
            if (!positionToucher.get(positionBateau)){
                return false;
            }
        }
        return true;
    }
}
