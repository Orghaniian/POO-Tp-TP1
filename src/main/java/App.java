import animal.Animal;
import animal.Chat;
import animal.Chien;
import animal.TypeAnimal;
import exception.AnimalDansMauvaisSecteurException;
import exception.LimiteVisiteurException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class App {
    private static final Logger logger =
            LogManager. getLogger ( App.class );

    public static void main(String[] args) {
        logger.trace("Demarrage de l'application");

        Interacteur interacteur = new Interacteur();
        interacteur.start();
    }
}
