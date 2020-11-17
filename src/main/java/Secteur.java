import animal.Animal;
import animal.TypeAnimal;
import exception.AnimalDansMauvaisSecteurException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


/**
 *Secteur de zoo contenant des {@link Animal} du même {@link TypeAnimal}
 */
public class Secteur implements Comparable<Secteur>{
    private TypeAnimal typeAnimauxDansSecteur;
    private List<Animal> animauxDansSecteur = new ArrayList<>();

    private static final Logger logger =
            LogManager. getLogger ( Secteur.class );


    /**
     * @param typeAnimauxDansSecteur type d'animal dans le secteur
     */
    public Secteur(TypeAnimal typeAnimauxDansSecteur) {
        this.typeAnimauxDansSecteur = typeAnimauxDansSecteur;
        logger.trace("Nouveau Secteur : " + this);
    }

    /**
     *
     * @param typeAnimauxDansSecteur type d'animal dans le secteur
     * @param animauxDansSecteur liste d'animaux dans le secteur
     */
    public Secteur(TypeAnimal typeAnimauxDansSecteur, List<Animal> animauxDansSecteur) {
        this(typeAnimauxDansSecteur);
        this.animauxDansSecteur = animauxDansSecteur;
    }

    /**
     * Ajoute un animal dans le secteur
     * L'animal doit avoir le même {@link TypeAnimal} que le secteur
     *
     * @param animal animal à ajouter
     * @throws AnimalDansMauvaisSecteurException le type de m'animal ne correspond pas à celui du secteur
     */
    public void ajouterAnimal(Animal animal) throws AnimalDansMauvaisSecteurException {
        if (animal.getTypeAnimal() == typeAnimauxDansSecteur){
            animauxDansSecteur.add(animal);
        }else throw new AnimalDansMauvaisSecteurException(animal.toString() + "n'a pas sa place dans le secteur des " + typeAnimauxDansSecteur);

    }

    /**
     * Retourne le nombnre d'{@link Animal} dans le secteur
     *
     * @return nombre d'animaux dans le secteur
     */
    public int getNombreAnimaux(){
        return animauxDansSecteur.size();
    }

    /**
     * Retourne le {@link TypeAnimal} du secteur
     *
     * @return type d'animal du secteur
     */
    public TypeAnimal obtenirType(){
        return typeAnimauxDansSecteur;
    }


    @Override
    public String toString() {
        return "Secteur{" +
                "typeAnimauxDansSecteur=" + typeAnimauxDansSecteur +
                ", animauxDansSecteur=" + animauxDansSecteur +
                '}';
    }

    @Override
    public int compareTo(Secteur o) {
        return getNombreAnimaux() - o.getNombreAnimaux();
    }
}
