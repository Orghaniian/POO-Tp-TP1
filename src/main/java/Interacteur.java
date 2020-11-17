import animal.Chat;
import animal.Chien;
import animal.TypeAnimal;
import com.thoughtworks.xstream.XStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Scanner;

public class Interacteur {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private Zoo zoo = new Zoo("SuperZoo");
    private static final Logger logger = LogManager. getLogger ( Interacteur.class );

    public Interacteur() {
        logger.trace("Nouvel Interacteur : " + this);
    }

    public Interacteur(Zoo zoo) {
        this.zoo = zoo;
        logger.trace("Nouvel Interacteur : " + this);
    }

    private String getString(String question) throws IOException {
        String reponse;
        do {
            System.out.println(question);
            System.out.print(">");
            reponse = br.readLine();
        } while (reponse == null);
        return reponse;
    }

    private TypeAnimal getTypeAnimal() {
        TypeAnimal type = null;
        do{
            try {
                type = TypeAnimal.valueOf(getString("Quelle est l'espèce ?").toUpperCase());
            } catch (IOException e) {
                e.printStackTrace();
            }catch (IllegalArgumentException e){
                System.out.println("espèce inconnue");
            }
        }while(type == null);
        return type;
    }

    private void nouveauZoo(){
        System.out.println("----------------NOUVEAU ZOO----------------");
        try {
            zoo = new Zoo(getString("Quel est le nom du nouveau Zoo ?"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void nouveauSecteur(){
        System.out.println("----------------NOUVEAU SECTEUR----------------");
        TypeAnimal type = getTypeAnimal();
        zoo.ajouterSecteur(type);
    }

    private void nouvelAnimal() {
        String nom = null;
        System.out.println("----------------NOUVEL ANIMAL----------------");
        TypeAnimal type = getTypeAnimal();
        try {
            nom = getString("Quel est le nom de l'animal ?");
        } catch (IOException e) {
            e.printStackTrace();
        }

        switch (type){
            case CHIEN:
                zoo.nouvelAnimal(new Chien(nom));
                break;
            case CHAT:
                zoo.nouvelAnimal(new Chat(nom));
                break;
        }
    }

    private void renommerZoo(){
        System.out.println("----------------RENOMMER LE ZOO----------------");
        try {
            zoo.setNom(getString("Quel nom voulez-vous donner au zoo ?"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sauvegarder() {
        System.out.println("----------------SAUVEGARDE----------------");
        try {
            String path = getString("Veuillez choisir un nom pour le fichier:");
            zoo.sauvegarder("output/"+path);
        } catch (FileNotFoundException e) {
            System.out.println("Sauvegarde échouée");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Erreur de saisie");
            e.printStackTrace();
        }
    }

    private void charger() {
        System.out.println("----------------SAUVEGARDE----------------");
        try {
            String path = getString("Charger un fichier:");
            zoo = Zoo.charger(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int Menu() {
        int reponse = 0;
        System.out.println("----------------MENU----------------");
        System.out.println(zoo.getNom() + ": Que souhaitez-vous faire ? \n"
                + "1: Créer un nouveau Zoo \n"
                + "2: Ajouter un nouveau secteur \n"
                + "3: Inscrire un nouvel animal \n"
                + "4: Renommer votre Zoo \n"
                + "5: Afficher zoo \n"
                + "6: Sauvegarder dans un fichier XML \n"
                + "7: Charger un zoo\n"
                + "-1: Arrêter le programme");

        System.out.print("Votre choix: ");
        try {
            reponse = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reponse;
    }

    public void start() {
        int reponse;
        do{
            reponse = Menu();
            switch (reponse) {
                case 1:
                    System.out.println("nouveau zoo");
                    nouveauZoo();
                    break;
                case 2:
                    nouveauSecteur();
                    break;
                case 3:
                    nouvelAnimal();
                    break;
                case 4:
                    renommerZoo();
                    break;
                case 5:
                    System.out.println(zoo);
                    break;
                case 6:
                    sauvegarder();
                    break;
                case 7:
                    charger();
                    break;
                default:
                    break;
            }
        }while (reponse != -1);
    }
}
