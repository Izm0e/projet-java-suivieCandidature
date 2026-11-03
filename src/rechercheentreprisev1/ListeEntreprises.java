package rechercheentreprisev1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ListeEntreprises {

    private ArrayList<Entreprise> lstEntreprise;
    private static int num_save;

    public ListeEntreprises() {
        this.lstEntreprise = new ArrayList<>();
        this.num_save = 0;
    }

    public ArrayList<Entreprise> getList() {
        return this.lstEntreprise;
    }

    public void addEntreprise(Entreprise ent) {
        lstEntreprise.add(ent);
    }

    public void supEntreprise() {
        lstEntreprise.remove(num_save);
    }

    public int getNumSave() {
        return this.num_save;
    }
    
    public void setNumSave(int num_save) {
        this.num_save = num_save;
    }
    
    public int getSize() {
        return this.lstEntreprise.size();
    }
    
    public void suivEntreprise() {
        if (this.num_save < this.lstEntreprise.size()-1) {
            this.num_save++;
        }
    }

    public void precedEntreprise() {
        if (this.num_save > 0) {
            this.num_save--;
        }
    }

    public void sauvegarder() {
        try {
            FileWriter fichier = new FileWriter("entreprises.csv");
            fichier.write("nom,numTel,email,etat\n");
            for (Entreprise ent : lstEntreprise) {
                fichier.write(ent.getNom() + "," + ent.getNumTel() + "," + ent.getEmail() + "," + ent.getEtat() + "\n");
            }
            fichier.close();
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde : " + e.getMessage());
        }
    }

    public void charger() {
        try {
            BufferedReader fichier = new BufferedReader(new FileReader("entreprises.csv"));
            lstEntreprise.clear();
            fichier.readLine();
            String ligne;
            while ((ligne = fichier.readLine()) != null) {
                String[] donnees = ligne.split(",", -1);
                String nom = donnees[0];
                String numTel = donnees[1];
                String email = donnees[2];
                String etat;
                if (donnees[3].equals("")) {
                    etat = " ";
                }else {
                    etat = donnees[3];
                }

                Entreprise ent = new Entreprise(nom, numTel, email, etat);
                lstEntreprise.add(ent);
            }
            fichier.close();
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement : " + e.getMessage());
        }
    }

}
