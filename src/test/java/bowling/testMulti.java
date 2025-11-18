package bowling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class testMulti {
    private IPartieMultiJoueurs partie;

    @BeforeEach
    void setUp() {
        partie = new PartieMultiJoueurs();
    }

    @Test
    void testScenarioMultiJoueur() {
        String res = partie.demarreNouvellePartie(new String[]{"Pierre", "Paul"});
        assertEquals("Prochain tir : joueur Pierre, tour n° 1, boule n° 1", res);

        res = partie.enregistreLancer(5);
        assertEquals("Prochain tir : joueur Pierre, tour n° 1, boule n° 2", res);

        // Pierre joue 3
        res = partie.enregistreLancer(3);
        assertEquals("Prochain tir : joueur Paul, tour n° 1, boule n° 1", res);

        // Paul joue 10 (strike)
        res = partie.enregistreLancer(10);
        assertEquals("Prochain tir : joueur Pierre, tour n° 2, boule n° 1", res);

        // Pierre joue 7
        res = partie.enregistreLancer(7);
        assertEquals("Prochain tir : joueur Pierre, tour n° 2, boule n° 2", res);

        // Pierre joue 3
        res = partie.enregistreLancer(3);
        assertEquals("Prochain tir : joueur Paul, tour n° 2, boule n° 1", res);

        // Vérification des scores
        assertEquals(18, partie.scorePour("Pierre"));
        assertEquals(10, partie.scorePour("Paul"));

        // Exception pour joueur inconnu
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            partie.scorePour("Jacques");
        });
        assertEquals("Joueur inconnu", exception.getMessage());
    }
}
