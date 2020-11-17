import animal.Animal;
import animal.TypeAnimal;
import com.thoughtworks.xstream.XStream;
import exception.AnimalDansMauvaisSecteurException;
import exception.LimiteVisiteurException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
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

    public void sauvegarder(String chemin) throws FileNotFoundException {
        XStream xstream = new XStream();
        xstream.toXML(this, new FileOutputStream(new File(chemin + ".xml")));
    }

    public static Zoo charger(String chemin) throws IOException {
        FileInputStream file;
        XStream xstream = new XStream();
        file = new FileInputStream(chemin);
        Zoo zoo = (Zoo) xstream.fromXML(file);
        file.close();
        return zoo;
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
