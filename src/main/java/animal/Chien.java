package animal;

public class Chien extends Animal{

    /**
     *
     * @param nomAnimal nom du chien
     */
    public Chien(String nomAnimal) {
        super(nomAnimal, TypeAnimal.CHIEN);
    }

    @Override
    public String getNomAnimal() {
        return super.getNomAnimal();
    }
}
