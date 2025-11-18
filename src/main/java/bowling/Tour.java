package bowling;

public abstract class Tour {
    protected final int numero;
    protected final int[] lancers;
    protected int lancerCourant;
    
    public Tour(int numero, int nombreLancersMax) {
        this.numero = numero;
        this.lancers = new int[nombreLancersMax];
        this.lancerCourant = 0;
    }
    
    public abstract boolean enregistreLancer(int quilles);
    public abstract boolean estComplete();
    
    public int scoreBase() {
        int score = 0;
        for (int i = 0; i < lancerCourant; i++) {
            score += lancers[i];
        }
        return score;
    }
    
    public boolean estStrike() {
        return lancerCourant >= 1 && lancers[0] == 10;
    }
    
    public boolean estSpare() {
        return lancerCourant >= 2 && lancers[0] + lancers[1] == 10;
    }
    
    public int getLancer(int index) {
        if (index < lancerCourant) {
            return lancers[index];
        }
        return 0;
    }
    
    public int getNumeroLancers() {
        return lancerCourant;
    }
    
    public int getNumero() {
        return numero;
    }
    
    public int getLancerCourant() {
        return lancerCourant;
    }
    
    public int getQuillesLancer(int index) {
        if (index < lancerCourant && index < lancers.length) {
            return lancers[index];
        }
        return 0;
    }
}