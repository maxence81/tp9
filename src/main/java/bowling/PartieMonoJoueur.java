package bowling;

import java.util.ArrayList;
import java.util.List;

public class PartieMonoJoueur {
    private final List<Tour> tours;
    private int tourActuelIndex;
    private final CalculateurScore calculateurScore;
    private boolean estTerminee;
    
    public PartieMonoJoueur() {
        this.tours = new ArrayList<>();
        this.calculateurScore = new CalculateurScore();
        
        // Créer les 9 premiers tours standards
        for (int i = 1; i <= 9; i++) {
            tours.add(new TourStandard(i));
        }
        // Créer le 10ème tour final
        tours.add(new TourFinal());
        
        this.tourActuelIndex = 0;
        this.estTerminee = false;
    }
    
    public boolean enregistreLancer(int nombreDeQuillesAbattues) {
        if (estTerminee) {
            throw new IllegalStateException("La partie est terminée, on ne peut plus enregistrer de lancer.");
        }
        
        if (tourActuelIndex >= tours.size()) {
            estTerminee = true;
            throw new IllegalStateException("Tous les tours sont complets.");
        }
        
        Tour tourActuel = tours.get(tourActuelIndex);
        boolean tourContinue = tourActuel.enregistreLancer(nombreDeQuillesAbattues);
        
        // Si le tour actuel est complet, passer au tour suivant
        if (!tourContinue) {
            tourActuelIndex++;
            
            // Vérifier si la partie est terminée
            if (tourActuelIndex >= tours.size()) {
                estTerminee = true;
            }
        }
        
        return tourContinue;
    }
    
    public int scoreTotal() {
        return calculateurScore.calculerScore(tours, tours.size());
    }
    
    public int scorePourTour(int numeroTour) {
        return calculateurScore.calculerScore(tours, numeroTour);
    }
    
    public int numeroTourCourant() {
        if (estTerminee) {
            return 0;
        }
        return Math.min(tourActuelIndex + 1, 10);
    }
    
    public int numeroProchainLancer() {
        if (estTerminee) {
            return 0;
        }
        
        if (tourActuelIndex >= tours.size()) {
            return 0;
        }
        
        Tour tourActuel = tours.get(tourActuelIndex);
        return tourActuel.getLancerCourant() + 1;
    }
    
    public boolean estTerminee() {
        return estTerminee;
    }
    
    public int score() {
        return scoreTotal();
    }
    
    // Méthode utilitaire pour les tests
    public Tour getTour(int numeroTour) {
        if (numeroTour >= 1 && numeroTour <= tours.size()) {
            return tours.get(numeroTour - 1);
        }
        return null;
    }
}