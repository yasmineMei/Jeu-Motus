import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Dictionnaire {


    private int tailleMot;
    private int nbMots;
    private ArrayList<String> collectionMots;

    public Dictionnaire(int tailleMot) {
        this.tailleMot = tailleMot;
        this.nbMots = 0; // Initialement, aucun mot dans le dictionnaire
        this.collectionMots = new ArrayList<>();
    }

    public int getTailleMot() {
        return tailleMot;
    }

    public void setTailleMot(int tailleMot) {
        this.tailleMot = tailleMot;
    }

    public int getNbMots() {
        return nbMots;
    }

    public ArrayList<String> getCollectionMots() {
        return collectionMots;
    }

    public void chargerMots(ArrayList<String> mots) {
        if (mots != null) {
            this.collectionMots.addAll(mots);
            this.nbMots = this.collectionMots.size();
        }
    }

    public void ajouterMot(String mot) {
        if (mot.length() == this.tailleMot) {
            this.collectionMots.add(mot);
            this.nbMots++;
        } else {
            System.out.println("Le mot ne correspond pas à la taille du dictionnaire.");
        }
    }

    public void supprimerMot(String mot) {
        if (this.collectionMots.remove(mot)) {
            this.nbMots--;
        } else {
            System.out.println("Le mot n'existe pas dans le dictionnaire.");
        }
    }

    public boolean rechercheMot(String mot) {
        return this.collectionMots.contains(mot);
    }


    public void sauvegarderMotsDansFichier(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String mot : collectionMots) {
                writer.write(mot);
                writer.newLine(); // Nouvelle ligne pour chaque mot
            }
            System.out.println("Mots sauvegardés dans le fichier : " + fileName);
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde dans le fichier : " + e.getMessage());
        }
    }
}

