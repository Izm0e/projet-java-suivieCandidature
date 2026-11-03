package rechercheentreprisev1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Fenetre extends JFrame {

    private ListeEntreprises listeEntreprises;
    private JPanel pan, modifpan, ajoutpan;
    private JTextField tf_affichage, tf_noment, tf_nument, tf_emailent;
    private JButton but_precedent, but_valider, but_suivant, but_en_attente, but_refuser, but_modif_list, but_sauvegarder, but_ajouter, but_supprimer, but_valide_ajout;
    private JList<Entreprise> lst;
    private JScrollPane scrollpan;
    private DefaultListModel<Entreprise> modLst;

    public Fenetre() {
        super("RechercheEntreprise");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        initPan();
        initModifPan();
        initajoutpan();
        initActionButton();
        Design();

        this.setContentPane(pan);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    private void initPan() {
        pan = new JPanel();
        tf_affichage = new JTextField(" ");

        listeEntreprises = new ListeEntreprises();
        listeEntreprises.charger();

        modLst = new DefaultListModel<>();

        chargerListe();
        actualiseTextField();

        lst = new JList<>(modLst);
        lst.setSelectionBackground(Color.decode("#1E4D82"));
        lst.setSelectionForeground(Color.white);
        scrollpan = new JScrollPane(lst);

        but_precedent = new JButton("Précédent");
        but_valider = new JButton("Valider");
        but_suivant = new JButton("Suivant");
        but_en_attente = new JButton("En attente");
        but_refuser = new JButton("Refusé");
        but_modif_list = new JButton("Modifier la Liste");
        but_sauvegarder = new JButton("Sauvegarder");

        tf_affichage.setPreferredSize(new Dimension(100, 40));

        pan.setLayout(new GridBagLayout());
        GridBagConstraints gcpan = new GridBagConstraints();

        gcpan.fill = GridBagConstraints.BOTH;
        gcpan.insets = new Insets(5, 5, 5, 5);
        gcpan.gridx = 0;
        gcpan.gridy = 0;
        gcpan.gridwidth = 3;
        pan.add(tf_affichage, gcpan);
        gcpan.gridwidth = 1;

        gcpan.gridy = 1;
        gcpan.gridheight = 3;
        pan.add(but_precedent, gcpan);
        gcpan.gridheight = 1;

        gcpan.gridx++;
        pan.add(but_valider, gcpan);

        gcpan.gridx++;
        gcpan.gridheight = 3;
        pan.add(but_suivant, gcpan);
        gcpan.gridheight = 1;

        gcpan.gridx--;
        gcpan.gridy++;
        pan.add(but_en_attente, gcpan);

        gcpan.gridy++;
        pan.add(but_refuser, gcpan);

        gcpan.gridx = 3;
        gcpan.gridy = 0;
        gcpan.gridwidth = 2;
        gcpan.gridheight = 3;
        pan.add(scrollpan, gcpan);
        gcpan.gridwidth = 1;
        gcpan.gridheight = 1;

        gcpan.gridy = 3;
        pan.add(but_modif_list, gcpan);

        gcpan.gridx++;
        pan.add(but_sauvegarder, gcpan);
    }

    private void initModifPan() {
        modifpan = new JPanel();

        but_ajouter = new JButton("Ajouter");
        but_supprimer = new JButton("Supprimer");

        modifpan.setLayout(new GridBagLayout());

        GridBagConstraints gcmodifpan = new GridBagConstraints();

        gcmodifpan.insets = new Insets(5, 5, 5, 5);
        gcmodifpan.gridx = 0;
        gcmodifpan.gridy = 0;
        modifpan.add(but_ajouter, gcmodifpan);

        gcmodifpan.gridx++;
        modifpan.add(but_supprimer, gcmodifpan);
    }

    private void initajoutpan() {
        ajoutpan = new JPanel();

        tf_noment = new JTextField();
        tf_nument = new JTextField();
        tf_emailent = new JTextField();

        tf_noment.setPreferredSize(new Dimension(400, 40));
        tf_nument.setPreferredSize(new Dimension(400, 40));
        tf_emailent.setPreferredSize(new Dimension(400, 40));

        but_valide_ajout = new JButton("Valider");

        but_valide_ajout.setPreferredSize(new Dimension(100, 150));

        ajoutpan.setLayout(new GridBagLayout());

        GridBagConstraints gcajoutpan = new GridBagConstraints();

        gcajoutpan.fill = GridBagConstraints.VERTICAL;
        gcajoutpan.gridx = 0;
        gcajoutpan.gridy = 0;
        ajoutpan.add(tf_noment, gcajoutpan);

        gcajoutpan.insets = new Insets(20, 0, 20, 0);
        gcajoutpan.gridy++;
        ajoutpan.add(tf_nument, gcajoutpan);

        gcajoutpan.gridy++;
        gcajoutpan.insets = new Insets(0, 0, 0, 0);
        ajoutpan.add(tf_emailent, gcajoutpan);

        gcajoutpan.gridy = 0;
        gcajoutpan.gridx++;
        gcajoutpan.gridheight = 3;
        gcajoutpan.insets = new Insets(0, 10, 0, 0);
        ajoutpan.add(but_valide_ajout, gcajoutpan);
        gcajoutpan.gridheight = 1;
    }

    private void initActionButton() {
        but_en_attente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ind = listeEntreprises.getNumSave();
                listeEntreprises.getList().get(ind).setEtat("en_attente");
                actualiseModelList();
            }
        });

        but_modif_list.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        but_precedent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listeEntreprises.precedEntreprise();
                actualiseTextField();
                SelecteurList();
            }
        });

        but_refuser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ind = listeEntreprises.getNumSave();
                listeEntreprises.getList().get(ind).setEtat("refus");
                actualiseModelList();
            }
        });

        but_suivant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listeEntreprises.suivEntreprise();
                actualiseTextField();
                SelecteurList();
            }
        });

        but_valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ind = listeEntreprises.getNumSave();
                listeEntreprises.getList().get(ind).setEtat("valide");
                actualiseModelList();
            }
        });

        but_sauvegarder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                but_sauvegarder.setBackground(Color.red);
                listeEntreprises.sauvegarder();
                but_sauvegarder.setBackground(Color.green);
            }
        });

        but_modif_list.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePan(modifpan);
            }
        });

        but_ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePan(ajoutpan);
            }
        });

        but_valide_ajout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = tf_noment.getText();
                String num = tf_nument.getText();
                String email = tf_emailent.getText();

                createEntreprise(name, num, email);
                listeEntreprises.sauvegarder();
                changePan(pan);
            }
        });

        but_supprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listeEntreprises.supEntreprise();
                modLst.remove(listeEntreprises.getNumSave());
                listeEntreprises.sauvegarder();
                changePan(pan);

            }
        });

        lst.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                listeEntreprises.setNumSave(lst.getSelectedIndex());
                actualiseTextField();
            }
        });
    }

    private void chargerListe() {
        modLst.clear();

        for (Entreprise ent : listeEntreprises.getList()) {
            modLst.addElement(ent);
        }
    }

    private void actualiseTextField() {
        int ind = listeEntreprises.getNumSave();
        if (ind <= 0) {
            tf_affichage.setText("");
        } else {
            Entreprise entrepriseSelectionné = listeEntreprises.getList().get(ind);

            if (entrepriseSelectionné.getEmail().equals("")) {
                tf_affichage.setText(entrepriseSelectionné.getNom() + " - " + entrepriseSelectionné.getNumTel());
            } else {
                tf_affichage.setText(entrepriseSelectionné.getNom() + " - " + entrepriseSelectionné.getEmail());
            }
        }

    }

    private void actualiseModelList() {
        int ind = listeEntreprises.getNumSave();
        modLst.setElementAt(listeEntreprises.getList().get(ind), ind);
    }

    private void SelecteurList() {
        int ind = listeEntreprises.getNumSave();
        lst.setSelectedIndex(ind);
    }

    private void changePan(JPanel pan) {
        this.setContentPane(pan);
        this.setVisible(true);
    }

    private void createEntreprise(String nom, String numTel, String email) {
        Entreprise entnew = new Entreprise(nom, numTel, email, "");
        modLst.add(modLst.getSize(), entnew);
        listeEntreprises.addEntreprise(entnew);
    }

    private void Design() {
        String background = "#334670", foreground = "#FFFFFF";

        pan.setBackground(Color.decode("#161E31"));
        ajoutpan.setBackground(Color.decode("#161E31"));
        modifpan.setBackground(Color.decode("#161E31"));

        but_precedent.setBackground(Color.decode(background));
        but_precedent.setForeground(Color.decode(foreground));

        but_suivant.setBackground(Color.decode(background));
        but_suivant.setForeground(Color.decode(foreground));

        but_valider.setBackground(Color.decode("#2D6E22"));
        but_valider.setForeground(Color.decode(foreground));

        but_en_attente.setBackground(Color.decode("#1E4D82"));
        but_en_attente.setForeground(Color.decode(foreground));

        but_refuser.setBackground(Color.decode("#691B1B"));
        but_refuser.setForeground(Color.decode(foreground));

        but_sauvegarder.setBackground(Color.decode(background));
        but_sauvegarder.setForeground(Color.decode(foreground));

        but_modif_list.setBackground(Color.decode("#1E4D82"));
        but_modif_list.setForeground(Color.decode(foreground));

        but_ajouter.setBackground(Color.decode(background));
        but_ajouter.setForeground(Color.decode(foreground));

        but_supprimer.setBackground(Color.decode(background));
        but_supprimer.setForeground(Color.decode(foreground));

        but_valide_ajout.setBackground(Color.decode(background));
        but_valide_ajout.setForeground(Color.decode(foreground));

        // ------------------------------------------------------------------------------------------------------------- //
        // ------------------------------------------------------------------------------------------------------------- //
        // ------------------------------------------------------------------------------------------------------------- //
        tf_affichage.setBackground(Color.decode(background));
        tf_affichage.setForeground(Color.decode(foreground));
        tf_affichage.setCaretColor(Color.white);

        tf_emailent.setBackground(Color.decode(background));
        tf_emailent.setForeground(Color.decode(foreground));
        tf_emailent.setCaretColor(Color.white);

        tf_noment.setBackground(Color.decode(background));
        tf_noment.setForeground(Color.decode(foreground));
        tf_noment.setCaretColor(Color.white);

        tf_nument.setBackground(Color.decode(background));
        tf_nument.setForeground(Color.decode(foreground));
        tf_nument.setCaretColor(Color.white);

    }
}
