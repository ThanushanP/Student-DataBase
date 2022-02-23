import javax.swing.*;

class Node {
    Student name;
    Node next;
    JRadioButton newCB;


    public Node(Student name, Node next){
        this.name= name;
        this.next= next;
        newCB = new JRadioButton(name.name());
    }

}

