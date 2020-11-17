package animal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Animal {
    private String nomAnimal;
    private TypeAnimal typeAnimal;

    private static final Logger logger =
            LogManager. getLogger ( Animal.class );

    /**
     *
     * @param nomAnimal nom de l'animal
     * @param typeAnimal type de l'animal
     */
    public Animal(String nomAnimal, TypeAnimal typeAnimal) {
        this.nomAnimal = nomAnimal;
        this.typeAnimal = typeAnimal;
        logger.trace("Nouvel animal : " + this);
    }

    /**
     *Retourne le nom de l'animal
     *
     * @return nom
     */
    public String getNomAnimal() {
        return nomAnimal;
    }

    /**
     *Retourne le type de l'animal
     *
     * @return type
     */
    public TypeAnimal getTypeAnimal() {
        return typeAnimal;
    }

    @Override
    public String toString() {
        return "animal.Animal{" +
                "nomAnimal='" + nomAnimal + '\'' +
                ", typeAnimal=" + typeAnimal +
                '}';
    }
}
