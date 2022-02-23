import java.util.Comparator;

public class ZToAComparator implements Comparator<Student>{

    public int compare(Student one, Student two){
        char[] oneC = one.name().toCharArray();
        char[] twoC = two.name().toCharArray();
        if (twoC[0] - oneC[0] == 0) {
            for (int i = 1; i < oneC.length | i < twoC.length; i++) {
                if (twoC[i] - oneC[i] == 0) {

                } else
                    return twoC[i] - oneC[i];
            }
        }
        return twoC[0] - oneC[0];
    }
} 