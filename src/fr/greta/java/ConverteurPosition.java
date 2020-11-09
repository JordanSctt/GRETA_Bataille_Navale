package fr.greta.java;

import java.util.ArrayList;
import java.util.List;

public class ConverteurPosition {


    //récupére les positions X et Y (positions avant et arrière) pour déterminer si le bateau est à l'horizontale ou à la verticale
    // (sinon exception du converteur (bateau en diagonale)
    public List<Position> convertir(Position positionAvant, Position positionArriere) throws ConverteurException {

            if (positionAvant.getX() == positionArriere.getX()) {
                return convertirHorizontal(positionAvant, positionArriere);
            }
            if (positionAvant.getY() == positionArriere.getY()) {
                return convertirVertical(positionAvant, positionArriere);
            }
        throw new ConverteurException();
    }

    // Je veux définir la position compléte de mon bateau par rapport à la position avant et arrière
    // à l'horizontale mon X ne change pas, je parcoure donc mon Y du Min au Max et je garde l'ensemble
    // des position Y entre les deux, jeretourne la position compléte de mon bateau placé horizontallement
    private List<Position> convertirHorizontal(Position positionAvant, Position positionArriere) {
        List<Position> positionComplete = new ArrayList<>();
        int yMin = Math.min(positionAvant.getY(), positionArriere.getY());
        int yMax = Math.max(positionAvant.getY(), positionArriere.getY());
            for (int yQuiVarie = yMin; yQuiVarie <= yMax ; yQuiVarie++) {
                Position position = new Position();
                position.setX(positionAvant.getX());
                position.setY(yQuiVarie);
                positionComplete.add(position);
            }
            return positionComplete;
    }

    // Même procédé de récupération de la postion compléte de mon bateau pour le cas bateau à la verticale
    private List<Position> convertirVertical(Position positionAvant, Position positionArriere) {
        List<Position> positionComplete = new ArrayList<>();
        int xMin = Math.min(positionAvant.getX(), positionArriere.getX());
        int xMax = Math.max(positionAvant.getX(), positionArriere.getX());
        for (int xQuiVarie = xMin; xQuiVarie <= xMax ; xQuiVarie++) {
            Position position = new Position();
            position.setY(positionAvant.getY());
            position.setX(xQuiVarie);
            positionComplete.add(position);
        }
        return positionComplete;
    }
}
