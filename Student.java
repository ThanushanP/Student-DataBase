import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

import static java.lang.Float.POSITIVE_INFINITY;

public class Student {

    private String name;
    private int age;
    private String email;
    private String Bday;
    private double marks;
    private String sex;
    private Test testsNode = new Test("Test", 0, 0, null);

    public Student(String name,String email,String Bday, String Sex){

        this.name = name;
        this.email= email;
        this.Bday = Bday;
        this.sex = Sex;
    }
    public void addTest(String n, int weight){
        Test newt;
        Test p;

        p=testsNode;
        newt = new Test(n, weight, 0, null);

        while (p.next != null) {
            p = p.next;
        }
        p.next=newt;
    }

    public void delTests(int pos){
        Test p;
        Test q;

        p = testsNode;
        q=null;
        if (pos==-1) return;
        for (int j = 0; j < pos+1; j++) {
            q = p;
            p = p.next;
        }
        if (p.next == null) {
            q.next = null;
        } else {
            q.next = p.next;
        }
    }

    public int lengthTest(){
        Test p;
        int length;

        p=testsNode;
        length=0;
        while(p.next!=null){
            p=p.next;
            length++;
        }
        return length;
    }

    public Test getTestsNode(){
        Test p;
        p = testsNode;

        return p.next;
    }

    public Test getOneTest(int i){
        Test p;
        p = testsNode;
        p=p.next;
        for(int j=0;j<i;j++){
            p=p.next;
        }
        return p;
    }

    public String name(){
        return name;
    }

    public int age() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date d= sdf.parse(Bday);
        Calendar c= Calendar.getInstance();
        c.setTime(d);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int date = c.get(Calendar.DATE);
        LocalDate l1 = LocalDate.of(year, month, date);
        LocalDate now1 = LocalDate.now();
        Period diff1 = Period.between(l1,now1);
        return diff1.getYears();
    }

    public String email(){
        return email;
    }

    public String Bday(){
        return Bday;
    }
    public String sex(){
        return sex;
    }
    public void Setname(String name){
        this.name= name;
    }

    public void Setemail(String email){
        this.email= email;
    }

    public void SetBday(String Bday){
        this.Bday= Bday;
    }
    public void Setsex(String sex){
        this.sex=sex;
    }
    public double marks(){
        double result;
        double weightTotal;
        result=0;
        weightTotal=0;
        Test p;
        p = testsNode;
        p=p.next;
        while(p !=null){
            result= result + (p.markT*p.weightT)/100;
            weightTotal = weightTotal + p.weightT;
            p=p.next;
        }
        if (weightTotal <= 100){
            result= (100/weightTotal) * result;
        }
       if (result == POSITIVE_INFINITY){
            result =0;
        }

        this.marks = result;
        return Math.round(result);
    }
}
