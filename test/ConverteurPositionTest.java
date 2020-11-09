
import fr.greta.java.ConverteurException;
import fr.greta.java.ConverteurPosition;
import fr.greta.java.Position;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ConverteurPositionTest {

    private ConverteurPosition converteur = new ConverteurPosition();

    // fct test qui s'assure que la fct convertirHorizontal DANS la fct convertir retourne correctement l'ensemble des positions
    // du bateau comprise entre le yMax et le yMin
    @Test
    public void verifyConvertirPourHorizontal() throws Exception {
        List<Position> positions = new ArrayList<>();
        positions.add(new Position(2, 3));
        positions.add(new Position(2, 4));
        positions.add(new Position(2,5));
        positions.add(new Position(2, 6));
        assertEquals(positions, converteur.convertir(new Position(2, 3), new Position(2, 6)));
    }

    // fct test qui s'assure que la fct convertirVertical DANS la fct convertir retourne correctement l'ensemble des positions
    // du bateau comprise entre le xMax et le xMin
    @Test
    public void verifyConvertirPourVertical() throws Exception {
        List<Position> positions = new ArrayList<>();
        positions.add(new Position(2, 3));
        positions.add(new Position(3, 3));
        positions.add(new Position(4,3));
        positions.add(new Position(5, 3));
        assertEquals(positions, converteur.convertir(new Position(2, 3), new Position(5, 3)));
    }

    //@Test(expentected = "type exception".class) tester une fonction qui comprend une gestion d'exception
    // fct test qui s'assure que lorsqu'un bateau n'est pas à l'horizontale ou à la verticale, l'exception s'exécute
    @Test(expected = ConverteurException.class)
    public void verifyConvertirDiagonale() throws Exception {
            converteur.convertir(new Position(4,5), new Position(3, 6));
        }
}
