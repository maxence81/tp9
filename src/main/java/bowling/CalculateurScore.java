package bowling;

import java.util.List;

public class CalculateurScore {
    
    public int calculerScore(List<Tour> tours, int tourLimite) {
        int score = 0;
        
        for (int i = 0; i < Math.min(tourLimite, tours.size()); i++) {
            Tour tour = tours.get(i);
            if (tour instanceof TourFinal) {
                for (int j = 0; j < tour.getNumeroLancers(); j++) {
                    score += tour.getQuillesLancer(j);
                }
            } else if (tour.estStrike()) {
                score += 10 + calculerBonusStrike(i, tours);
            } else if (tour.estSpare()) {
                score += 10 + calculerBonusSpare(i, tours);
            } else {
                score += tour.scoreBase();
            }
        }
        
        return score;
    }
    
    private int calculerBonusStrike(int indexTour, List<Tour> tours) {
        int bonus = 0;
        int lancersRestants = 2;
        
        // Parcourir les tours suivants pour obtenir les 2 lancers suivants
        for (int i = indexTour + 1; i < tours.size() && lancersRestants > 0; i++) {
            Tour tour = tours.get(i);
            
            for (int j = 0; j < tour.getNumeroLancers() && lancersRestants > 0; j++) {
                bonus += tour.getQuillesLancer(j);
                lancersRestants--;
            }
        }
        
        return bonus;
    }
    
    private int calculerBonusSpare(int indexTour, List<Tour> tours) {
        if (indexTour + 1 >= tours.size()) {
            return 0;
        }
        
        Tour tourSuivant = tours.get(indexTour + 1);
        if (tourSuivant.getNumeroLancers() > 0) {
            return tourSuivant.getQuillesLancer(0);
        }
        
        return 0;
    }
}