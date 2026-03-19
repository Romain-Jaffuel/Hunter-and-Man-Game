package classique;

public class AdaptateurPredateur implements ElementInterface {
    private Predateur predateur;
    public AdaptateurPredateur(Predateur predateur) { this.predateur = predateur; }

    @Override
    public int getEnergie() {
        return predateur.perteEnergie(); // adapte perteEnergie() → getEnergie()
    }
}