package bowling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartieMultiJoueurs implements IPartieMultiJoueurs {
    private List<String> joueurs;
    private Map<String, PartieMonoJoueur> parties;
    private int joueurCourantIndex;
    private boolean partieDemarree;

    public PartieMultiJoueurs() {
        joueurs = new ArrayList<>();
        parties = new HashMap<>();
        joueurCourantIndex = 0;
        partieDemarree = false;
    }

    @Override
    public String demarreNouvellePartie(String[] nomsDesJoueurs) {
        if (nomsDesJoueurs == null || nomsDesJoueurs.length == 0) {
            throw new IllegalArgumentException("Le tableau des noms de joueurs ne peut pas être vide ou null.");
        }
        joueurs.clear();
        parties.clear();
        for (String nom : nomsDesJoueurs) {
            joueurs.add(nom);
            parties.put(nom, new PartieMonoJoueur());
        }
        joueurCourantIndex = 0;
        partieDemarree = true;
        return prochainTirString();
    }

    @Override
    public String enregistreLancer(int nombreDeQuillesAbattues) {
        if (!partieDemarree) {
            throw new IllegalStateException("La partie n'est pas démarrée.");
        }
        String joueur = joueurs.get(joueurCourantIndex);
        PartieMonoJoueur partie = parties.get(joueur);
        partie.enregistreLancer(nombreDeQuillesAbattues);

        if (partie.numeroProchainLancer() == 1 || partie.estTerminee()) {
            joueurCourantIndex = (joueurCourantIndex + 1) % joueurs.size();
        }

        boolean tousTermines = true;
        for (String nom : joueurs) {
            if (!parties.get(nom).estTerminee()) {
                tousTermines = false;
                break;
            }
        }
        if (tousTermines) {
            partieDemarree = false;
            return "Partie terminée";
        }
        return prochainTirString();
    }

    @Override
    public int scorePour(String nomDuJoueur) {
        if (!parties.containsKey(nomDuJoueur)) {
            throw new IllegalArgumentException("Joueur inconnu");
        }
        return parties.get(nomDuJoueur).score();
    }

    private String prochainTirString() {
        String joueur = joueurs.get(joueurCourantIndex);
        PartieMonoJoueur partie = parties.get(joueur);
        int tour = partie.numeroTourCourant();
        int boule = partie.numeroProchainLancer();
        return String.format("Prochain tir : joueur %s, tour n° %d, boule n° %d", joueur, tour, boule);
    }
}
