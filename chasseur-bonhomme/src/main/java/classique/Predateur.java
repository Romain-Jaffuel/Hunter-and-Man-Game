package classique;

public class Predateur {
    private int valeur;
    public Predateur(int valeur) { this.valeur = valeur; }
    public int perteEnergie() { return -valeur; }
}