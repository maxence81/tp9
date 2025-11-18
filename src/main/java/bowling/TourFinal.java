package bowling;

public class TourFinal extends Tour {
    
    public TourFinal() {
        super(10, 3);
    }
    
    @Override
    public boolean enregistreLancer(int quilles) {
        if (estComplete() || lancerCourant >= lancers.length) {
            return false;
        }
        
        lancers[lancerCourant] = quilles;
        lancerCourant++;
        
        return !estComplete();
    }
    
    @Override
    public boolean estComplete() {
        if (lancerCourant < 2) {
            return false;
        }
        
        // Si strike ou spare, on a droit à 3 lancers
        if (estStrike() || estSpare()) {
            return lancerCourant >= 3;
        }
        
        // Sinon, tour ouvert après 2 lancers
        return lancerCourant >= 2;
    }
}