import animal.Chat;
import animal.Chien;
import animal.TypeAnimal;
import exception.AnimalDansMauvaisSecteurException;
import exception.LimiteVisiteurException;

public class App {

    public static void main(String[] args) {
        Zoo zoo = new Zoo();
        zoo.ajouterSecteur(TypeAnimal.CHAT);
        try{
            zoo.nouvelAnimal(new Chat("minou"));
            zoo.nouvelAnimal(new Chien("Medor"));
        }catch (AnimalDansMauvaisSecteurException e){
            e.printStackTrace();
        }

        try{
            zoo.nouveauVisiteur();
        }catch (LimiteVisiteurException e){
            e.printStackTrace();
        }

        System.out.println(zoo);

    }
}
