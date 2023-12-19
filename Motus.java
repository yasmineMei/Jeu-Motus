import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Motus {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: Motus <action> <taille>");
            return;
        }

        String action = args[0];
        int tailleMot = Integer.parseInt(args[1]);

        if (action.equals("config")) {
            Scanner scanner = new Scanner(System.in);
            configurerDictionnaire(scanner, tailleMot);
            scanner.close();
        } else if (action.equals("jeu")) {
            Scanner scanner = new Scanner(System.in);
            demarrerJeu(scanner, tailleMot);
            scanner.close();
        } else {
            System.out.println("Action non reconnue.");
        }
    }


    private static void configurerDictionnaire(Scanner scanner, int tailleMot) {
        Dictionnaire dictionnaire = new Dictionnaire(tailleMot);

        while (true) {
            System.out.println("1. Ajouter un mot");
            System.out.println("2. Supprimer un mot");
            System.out.println("3. Sauvegarder dans un fichier");
            System.out.println("4. Quitter la configuration");

            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer le retour à la ligne après nextInt

            switch (choix) {
                case 1:
                    System.out.println("Entrez le mot à ajouter : ");
                    String motAjoute = scanner.nextLine().toUpperCase(); // En majuscules pour uniformiser
                    dictionnaire.ajouterMot(motAjoute);
                    break;
                case 2:
                    System.out.println("Entrez le mot à supprimer : ");
                    String motSupprime = scanner.nextLine().toUpperCase(); // En majuscules pour uniformiser
                    dictionnaire.supprimerMot(motSupprime);
                    break;
                case 3:
                    System.out.println("Entrez le nom du fichier pour sauvegarder : ");
                    String nomFichier = scanner.nextLine();
                    dictionnaire.sauvegarderMotsDansFichier(nomFichier);
                    break;
                case 4:
                    return; // Sort de la méthode après la configuration
                default:
                    System.out.println("Choix non valide.");
                    break;
            }
        }
    }


    private static void demarrerJeu(Scanner scanner, int tailleMot) {
        System.out.println("Entrez le nombre d'essais autorisés : ");
        int nbEssais = scanner.nextInt();
        scanner.nextLine(); // Consommer le retour à la ligne

        // Charge les mots de la taille spécifiée depuis le dictionnaire (à implémenter)
        ArrayList<String> mots = obtenirMotsDeTaille(tailleMot);

        Jeu jeu = new Jeu(nbEssais, mots);

        jouerPartie(scanner, jeu);
    }


    private static ArrayList<String> obtenirMotsDeTaille(int tailleMot) {
        ArrayList<String> motsDeTaille = new ArrayList<>();

        // Chemin vers le fichier contenant les mots de la taille spécifiée
        String nomFichier = "d" + tailleMot + ".txt";

        try (BufferedReader br = new BufferedReader(new FileReader(nomFichier))) {
            String mot;
            while ((mot = br.readLine()) != null) {
                if (mot.length() == tailleMot) {
                    motsDeTaille.add(mot.toUpperCase()); // Ajoute le mot en majuscules dans la liste
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return motsDeTaille;
    }

    private static void jouerPartie(Scanner scanner, Jeu jeu) {
        while (jeu.getNbEssaisRestants() > 0 && !jeu.getMotTrouve().equals(jeu.getMotCache())) {
            System.out.println("Essais restants : " + jeu.getNbEssaisRestants());
            System.out.println("Mot trouve : " + jeu.getMotTrouve());
            System.out.println("Entrez un mot : ");
            String motPropose = scanner.nextLine().toUpperCase(); // En majuscules pour uniformiser
            boolean motTrouve = jeu.testerMot(motPropose);

            if (motTrouve) {
                System.out.println("Bravo ! Vous avez trouve le mot : " + jeu.getMotCache());
                break;
            } else {
                System.out.println("Mot incorrect.");
            }
        }

        if (!jeu.getMotTrouve().equals(jeu.getMotCache())) {
            System.out.println("Désolé, vous avez epuisé tous les essais. Le mot était : " + jeu.getMotCache());
        }
    }
}
