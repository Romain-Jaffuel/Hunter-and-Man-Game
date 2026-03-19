package classique;

public class Feu {
    private int valeur;
    public Feu(int valeur) { this.valeur = valeur; }
    public int energieMoins() { return -valeur; }
}