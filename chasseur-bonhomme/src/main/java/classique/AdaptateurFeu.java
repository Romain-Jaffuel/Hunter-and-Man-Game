package classique;

public class AdaptateurFeu implements ElementInterface {
    private Feu feu;
    public AdaptateurFeu(Feu feu) { this.feu = feu; }

    @Override
    public int getEnergie() {
        return feu.energieMoins(); // adapte energieMoins() → getEnergie()
    }
}