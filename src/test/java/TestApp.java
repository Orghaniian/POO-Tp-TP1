import animal.Animal;
import animal.Chat;
import animal.Chien;
import animal.TypeAnimal;
import exception.AnimalDansMauvaisSecteurException;
import exception.LimiteVisiteurException;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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
    public void testAnimalMauvaisSecteur(){
        Secteur secteur = new Secteur(TypeAnimal.CHIEN);
        Throwable e = null;

        try{
            secteur.ajouterAnimal(new Chat("minou"));
        }catch (Throwable ex){
            e = ex;
        }

        assertTrue(e instanceof AnimalDansMauvaisSecteurException);
        assertEquals(0, secteur.getNombreAnimaux());
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

    @Test
    public void test_ajouterAnimal() throws AnimalDansMauvaisSecteurException {
        Secteur secteur = new Secteur(TypeAnimal.CHIEN);
        secteur.ajouterAnimal(new Chien("Minou"));

        assertEquals(1, secteur.getNombreAnimaux());
    }

    @Test
    public void testConstructeurSecteur() {
        List<Animal> chats = Arrays.asList(new Chat("Minou"), new Chat("Poilu"));
        Secteur secteur = new Secteur(TypeAnimal.CHAT, chats);

        assertEquals(2, secteur.getNombreAnimaux());
        assertEquals(TypeAnimal.CHAT, secteur.obtenirType());
    }

    private Zoo creerZoo(){
        Zoo zoo = new Zoo();

        zoo.ajouterSecteur(TypeAnimal.CHAT);
        zoo.ajouterSecteur(TypeAnimal.CHIEN);
        zoo.nouvelAnimal(new Chien("medor"));
        zoo.nouvelAnimal(new Chat("Trigou"));
        zoo.nouvelAnimal(new Chat("Minou"));

        return zoo;
    }

    @Test
    public void testZoo_setNom() {
        Zoo zoo = new Zoo("truc");
        assertEquals(zoo.getNom(), "truc");
        zoo.setNom("bidule");
        assertEquals(zoo.getNom(), "bidule");
    }

    @Test
    public void testZoo_getLimiteVisiteur() {
        Zoo zoo = new Zoo();
        assertEquals(zoo.getLimiteVisiteur(), Zoo.LimiteVisiteur);
    }
}
