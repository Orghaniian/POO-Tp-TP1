import animal.Animal;
import animal.TypeAnimal;
import exception.AnimalDansMauvaisSecteurException;
import exception.LimiteVisiteurException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 *Zoo accueillant des {@link #visiteurs} et contenant des {@link Secteur} d'{@link Animal}
 */
public class Zoo {
    private String nom;
    private int visiteurs;
    private List<Secteur> secteursAnimaux = new ArrayList<>();

    private static final Logger logger =
            LogManager. getLogger ( Zoo.class );

    public final static int LimiteVisiteur = 15;

    public Zoo() {
        logger.trace("Nouveau Zoo : " + this);
    }

    public Zoo(String nom) {
        this.nom = nom;
        logger.trace("Nouveau Zoo : " + this);
    }

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

    public void nouvelAnimal(Animal animal){
        Secteur secteur=  secteursAnimaux.stream().filter(s -> s.obtenirType().equals(animal.getTypeAnimal())).findAny().orElse(null);
        if(secteur != null){
            try{
                secteur.ajouterAnimal(animal);
            }catch (AnimalDansMauvaisSecteurException e){
                logger.error(e);
            }

        }else{
            System.out.println("Aucun secteur ne convient à " + animal);
        }

    }

    public int nombreAnimaux(){
        return secteursAnimaux.stream().mapToInt(Secteur::getNombreAnimaux).sum();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Zoo{" +
                "nom: " + nom +
                ", visiteurs=" + visiteurs +
                ", secteursAnimaux=" + secteursAnimaux +
                '}';
    }
}
