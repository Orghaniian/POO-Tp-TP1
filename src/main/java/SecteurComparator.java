import java.util.Comparator;

public class SecteurComparator implements Comparator<Secteur> {

    @Override
    public int compare(Secteur o1, Secteur o2) {
        return o2.getNombreAnimaux() - o1.getNombreAnimaux();
    }
}
