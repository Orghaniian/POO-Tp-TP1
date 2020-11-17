import animal.Chat;
import animal.Chien;
import animal.TypeAnimal;
import exception.AnimalDansMauvaisSecteurException;
import exception.LimiteVisiteurException;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    private void addVisiteurs(Zoo zoo, int n) throws LimiteVisiteurException {
        for(int i=0; i < n; i++){
            zoo.nouveauVisiteur();
        }
    }

    @Test
    public void testAnimalBonSecteur(){
        Zoo zoo = new Zoo();
        zoo.ajouterSecteur(TypeAnimal.CHIEN);
        Throwable e = null;

        try{
            zoo.nouvelAnimal(new Chien("medor"));
        }catch (Throwable ex){
            e = ex;
        }

        assertFalse(e instanceof AnimalDansMauvaisSecteurException);
        assertEquals(1, zoo.nombreAnimaux());
    }

    @Test
    public void testComparatorSecteur() throws AnimalDansMauvaisSecteurException {
        Secteur secteurChat = new Secteur(TypeAnimal.CHAT);
        Secteur secteurChien = new Secteur(TypeAnimal.CHIEN);

        ArrayList<Secteur> s = new ArrayList<Secteur>();
        s.add(secteurChien);
        s.add(secteurChat);

        secteurChien.ajouterAnimal(new Chien("medor"));
        secteurChat.ajouterAnimal(new Chat("Trigou"));
        secteurChat.ajouterAnimal(new Chat("Minou"));

        s.sort(new SecteurComparator());

        assertEquals(secteurChat, s.get(0));
    }

    @Test
    public void testComparableSecteur() throws AnimalDansMauvaisSecteurException {
        Secteur secteurChat = new Secteur(TypeAnimal.CHAT);
        Secteur secteurChien = new Secteur(TypeAnimal.CHIEN);

        secteurChien.ajouterAnimal(new Chien("medor"));
        secteurChat.ajouterAnimal(new Chat("Trigou"));
        secteurChat.ajouterAnimal(new Chat("Minou"));

        assertTrue(secteurChat.compareTo(secteurChien) > 0);
    }
}
