import java.util.ArrayList;
import java.util.List;

public class Secteur {
    private TypeAnimal typeAnimauxDansSecteur;
    private List<Animal> animauxDansSecteur = new ArrayList<Animal>();

    public Secteur(TypeAnimal typeAnimauxDansSecteur) {
        this.typeAnimauxDansSecteur = typeAnimauxDansSecteur;
    }

    public Secteur(TypeAnimal typeAnimauxDansSecteur, List<Animal> animauxDansSecteur) {
        this(typeAnimauxDansSecteur);
        this.animauxDansSecteur = animauxDansSecteur;
    }

    public void ajouterAnimal(Animal animal) throws AnimalDansMauvaisSecteurException {
        if (animal.getTypeAnimal() == typeAnimauxDansSecteur){
            animauxDansSecteur.add(animal);
        }else throw new AnimalDansMauvaisSecteurException(animal.toString() + "n'a pas sa place dans le secteur des " + typeAnimauxDansSecteur);

    }

    public int getNombreAnimaux(){
        return animauxDansSecteur.size();
    }

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
}
