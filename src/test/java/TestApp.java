import org.junit.Test;

import static org.junit.Assert.*;

public class TestApp {

    @Test
    public void testNBVisiteurOK(){
        Zoo zoo = new Zoo();
        zoo.ajouterSecteur(TypeAnimal.CHIEN);
        Throwable e = null;

        try {
            addVisiteurs(zoo, 15);
        } catch (Throwable ex) {
            e = ex;
        }

        assertFalse(e instanceof LimiteVisiteurException);
    }

    @Test
    public void testNbVisiteurKO() {
        Zoo zoo = new Zoo();
        zoo.ajouterSecteur(TypeAnimal.CHAT);
        Throwable e = null;

        try {
            addVisiteurs(zoo, 18);
        } catch (Throwable ex) {
            e = ex;
        }

        assertTrue(e instanceof LimiteVisiteurException);

    }

    public void addVisiteurs(Zoo zoo, int n) throws LimiteVisiteurException {
        for(int i=0; i < n; i++){
            zoo.nouveauVisiteur();
        }
    }

    @Test
    public void testAnimalBonSecteur(){
        Zoo zoo = new Zoo();
        zoo.ajouterSecteur(TypeAnimal.CHAT);
        Throwable e = null;

        try{
            zoo.nouvelAnimal(new Chat("minou"));
        }catch (Throwable ex){
            e =ex;
        }

        assertFalse(e instanceof AnimalDansMauvaisSecteurException);
        assertEquals(1, zoo.nombreAnimaux());
    }
}
