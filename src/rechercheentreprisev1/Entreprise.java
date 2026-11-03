package rechercheentreprisev1;

public class Entreprise {

    private String nom;
    private String numTel;
    private String email;
    private String etat;

    public Entreprise(String nom, String numTel, String email, String etat) {
        this.nom = nom;
        this.numTel = numTel;
        this.email = email;
        this.etat = etat;
    }

    public String getNom() {
        return this.nom;
    }

    public String getNumTel() {
        return this.numTel;
    }

    public String getEmail() {
        return this.email;
    }

    public String getEtat() {
        return this.etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return nom + " - " + etat;
    }

}
