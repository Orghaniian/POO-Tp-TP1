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

    private void nouveauZoo(){
        System.out.println("----------------NOUVEAU ZOO----------------");
        System.out.println("Quel est le nom du nouveau Zoo ?");
        System.out.print(">");
        try {
            zoo = new Zoo(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void nouveauSecteur(){
        TypeAnimal type = null;
        System.out.println("----------------NOUVEAU SECTEUR----------------");
        type = getTypeAnimal(type);
        zoo.ajouterSecteur(type);
    }

    private void nouvelAnimal() {
        String nom = null;
        TypeAnimal type = null;
        System.out.println("----------------NOUVEL ANIMAL----------------");
        type = getTypeAnimal(type);
        do{
            System.out.print("Quel est le nom de l'animal: ");
            try {
                nom = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }while(nom == null);

        switch (type){
            case CHIEN:
                zoo.nouvelAnimal(new Chien(nom));
                break;
            case CHAT:
                zoo.nouvelAnimal(new Chat(nom));
                break;
        }
    }

    private TypeAnimal getTypeAnimal(TypeAnimal type) {
        do{
            System.out.println("Quelle est l'espèce:");
            System.out.print("Espèce: ");
            try {
                type = TypeAnimal.valueOf(br.readLine().toUpperCase());
            } catch (IOException e) {
                e.printStackTrace();
            }catch (IllegalArgumentException e){
                System.out.println("espèce inconnue");
            }
        }while(type == null);
        return type;
    }

    private void renommerZoo(){
        System.out.println("----------------RENOMMER LE ZOO----------------");
        System.out.println("Quel nom voulez-vous donner au zoo ?");
        System.out.print("Nouveau nom: ");
        try {
            zoo.setNom(br.readLine());
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

    private String getString(String question) throws IOException {
        String path;
        do {
            System.out.println(question);
            System.out.print(">");
            path = br.readLine();
        } while (path == null);
        return path;
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
