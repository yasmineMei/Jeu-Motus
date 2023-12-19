import java.util.ArrayList;
import java.util.Random;

public class Jeu {
    private int nbEssais;
    private int nbEssaisRestants;
    private String motCache;
    private String motTrouve;

    public Jeu(int nbEssais, ArrayList<String> mots) {
        this.nbEssais = nbEssais;
        this.nbEssaisRestants = nbEssais;
        this.motCache = choisirMotAuHasard(mots);
        this.motTrouve = "*".repeat(motCache.length()); // Remplace chaque lettre par une étoile
    }

    private String choisirMotAuHasard(ArrayList<String> mots) {
        Random random = new Random();
        return mots.get(random.nextInt(mots.size()));
    }

    public void decrementerEssai() {
        nbEssaisRestants--;
    }
    public boolean testerMot(String motPropose) {
        if (motPropose.length() != motCache.length() || !motPropose.matches("[a-zA-Z]+")) {
            return false; // Le mot proposé n'a pas la bonne longueur ou n'est pas alphabétique
        }

        StringBuilder nouveauMotTrouve = new StringBuilder(motTrouve);
        for (int i = 0; i < motPropose.length(); i++) {
            char lettreProposee = motPropose.charAt(i);
            char lettreCachee = motCache.charAt(i);

            if (lettreProposee == lettreCachee) {
                nouveauMotTrouve.setCharAt(i, lettreProposee);
            }
        }
        if (!motTrouve.equals(motCache)) {
            decrementerEssai(); // Décrémente le nombre d'essais restants si le mot est incorrect
        }

        return motTrouve.equals(motCache); // Renvoie vrai si le mot trouvé est identique au mot caché
    }

    // Getters pour les attributs nécessaires
    public int getNbEssaisRestants() {
        return nbEssaisRestants;
    }

    public String getMotTrouve() {
        return motTrouve;
    }

    public String getMotCache() {
        return motCache;
    }
}
