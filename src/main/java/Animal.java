public abstract class Animal {
    private String nomAnimal;
    private TypeAnimal typeAnimal;

    /**
     *
     * @param nomAnimal nom de l'animal
     * @param typeAnimal type de l'animal
     */
    public Animal(String nomAnimal, TypeAnimal typeAnimal) {
        this.nomAnimal = nomAnimal;
        this.typeAnimal = typeAnimal;
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
        return "Animal{" +
                "nomAnimal='" + nomAnimal + '\'' +
                ", typeAnimal=" + typeAnimal +
                '}';
    }
}
