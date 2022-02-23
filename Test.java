import javax.swing.*;

class Test {
        String nameT;
        double markT;
        int weightT;
        Test next;
        JRadioButton newCB;

        public Test(String name, int weight, double markT, Test next) {
            this.nameT = name;
            this.weightT = weight;
            this.markT=markT;
            this.next = next;
            newCB = new JRadioButton(nameT);

        }

        public void setMarkT(double mark) {
            this.markT = mark;
        }

        public void setWeightT(int weight) {
            this.weightT = weight;
        }
        public void setNameT(String name) {
            this.nameT = name;
        }
    }

