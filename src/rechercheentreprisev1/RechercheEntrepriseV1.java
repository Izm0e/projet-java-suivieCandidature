
package rechercheentreprisev1;

import java.util.ArrayList;


public class RechercheEntrepriseV1 {


    public static void main(String[] args) {
        Entreprise ent1 = new Entreprise("TrustPilote", "06251515161", "Test", "Refus");
        Fenetre f = new Fenetre();
        
        ListeEntreprises lst = new ListeEntreprises();
        lst.addEntreprise(ent1);
        
        
    }
    
}
