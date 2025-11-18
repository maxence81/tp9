package bowling;

public class TourStandard extends Tour {
    
    public TourStandard(int numero) {
        super(numero, 2);
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
        // Un tour standard est complet après un strike ou deux lancers
        return lancerCourant >= 2 || (lancerCourant >= 1 && estStrike());
    }
}

