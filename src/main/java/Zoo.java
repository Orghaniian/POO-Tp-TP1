import java.util.ArrayList;
import java.util.List;

public class Zoo {
    private int visiteurs;
    private List<Secteur> secteursAnimaux = new ArrayList<Secteur>();

    public final static int LimiteVisiteur = 15;

    public void ajouterSecteur(TypeAnimal typeAnimal){
        secteursAnimaux.add(new Secteur(typeAnimal));
    }

    public void nouveauVisiteur() throws LimiteVisiteurException {
        if (visiteurs < LimiteVisiteur){
            visiteurs++;
        }else {
            throw new LimiteVisiteurException("Le zoo est déjà plein ("+LimiteVisiteur+" personnes maximum)");
        }

    }

    public int getLimiteVisiteur(){
        return LimiteVisiteur;
    }

    public void nouvelAnimal(Animal animal) throws AnimalDansMauvaisSecteurException {
        Secteur secteur=  secteursAnimaux.stream().filter(s -> s.obtenirType().equals(animal.getTypeAnimal())).findAny().orElse(null);
        if(secteur != null){
            secteur.ajouterAnimal(animal);
        }else{
            System.out.println("Aucun secteur ne convient à " + animal);
        }

    }

    public int nombreAnimaux(){
        return secteursAnimaux.stream().mapToInt(Secteur::getNombreAnimaux).sum();
    }

    @Override
    public String toString() {
        return "Zoo{" +
                "visiteurs=" + visiteurs +
                ", secteursAnimaux=" + secteursAnimaux +
                '}';
    }
}
