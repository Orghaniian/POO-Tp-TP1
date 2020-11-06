public class App {

    public static void main(String[] args) {
        Zoo zoo = new Zoo();
        zoo.ajouterSecteur(TypeAnimal.CHAT);
        try{
            zoo.nouvelAnimal(new Chat("minou"));
            zoo.nouvelAnimal(new Chien("Medor"));
        }catch (AnimalDansMauvaisSecteurException e){
            System.out.println(e);
        }

        try{
            zoo.nouveauVisiteur();
        }catch (LimiteVisiteurException e){
            System.out.println(e);
        }

        System.out.println(zoo);

    }
}
