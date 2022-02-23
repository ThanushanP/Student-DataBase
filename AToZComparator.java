import java.util.Comparator;

public class AToZComparator implements Comparator<Student>{

    public int compare(Student one, Student two) {
        char[] oneC = one.name().toCharArray();
        char[] twoC = two.name().toCharArray();
        if (oneC[0] - twoC[0] == 0){
        for (int i = 1; i < oneC.length | i < twoC.length; i++) {
            if (oneC[i] - twoC[i] == 0) {

            } else
                return oneC[i] - twoC[i];
        }
    }
        return oneC[0] - twoC[0];
    }
} 