public abstract class Animal {
    private String nomAnimal;
    private TypeAnimal typeAnimal;

    public Animal(String nomAnimal, TypeAnimal typeAnimal) {
        this.nomAnimal = nomAnimal;
        this.typeAnimal = typeAnimal;
    }

    public String getNomAnimal() {
        return nomAnimal;
    }

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
