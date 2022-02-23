import java.text.ParseException;
import java.util.Comparator;

public class AgeComparator implements Comparator<Student>{

    public int compare(Student one, Student two) {
        int oneC = 0;
        int twoC = 0;
        try {
            oneC = one.age();
            twoC = two.age();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return oneC - twoC;
    }
} 