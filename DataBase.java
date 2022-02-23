        import javax.swing.*;
        import javax.swing.event.TableModelEvent;
        import javax.swing.event.TableModelListener;
        import javax.swing.table.TableModel;
        import javax.swing.text.NumberFormatter;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.text.NumberFormat;
        import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.util.Comparator;
        import java.util.Date;

        public class DataBase extends JFrame implements ActionListener, TableModelListener {

            private JPanel display;
            private JPanel display2;
            private JFrame frame;  //Holds all screens
            private String[] sort = {"A-Z", "Z-A", "By Age"};
            private JComboBox sorting;
            private JComboBox Sexchoice;
            private JTable table;
            private Node stds;
            private JPanel addSscreenB;
            private JPanel addSscreenT;
            private JPanel delSscreenB;
            private JPanel delSscreenT;
            private JPanel addTestSscreenB;
            private JPanel addTestSscreenT;
            private JFormattedTextField N;
            private JFormattedTextField E;
            private SpinnerDateModel Bd;
            private JFormattedTextField nameTest;
            private JFormattedTextField weight;
            private JPanel EditTestSscreenB;
            private JPanel EditTestSscreenT;
            private JPanel EditTestSscreenC;
            private JPanel DelTestSscreenT;
            private JPanel DelTestSscreenB;
            private JFormattedTextField Editweight;
            private JFormattedTextField EditTName;

            public DataBase() {

                frame = new JFrame();
                defaultStd();

                sorting = new JComboBox(sort);
                sorting.setSelectedIndex(0);
                sorting.addActionListener(this);

                setUpDisplay();
            }

            private void defaultStd() {
                Student std1;
                Student std2;
                Student std3;

                std1 = new Student("George Brown", "georgebrown@gmail.com", "01/01/2002", "M");
                std2 = new Student("Sarah Hans", "sarahhans@gmail.com", "04/19/2002", "F");
                std3 = new Student("Jermaine Neil", "jermaineneil@gmail.com", "12/23/2002", "Other");

                Node stdthree = new Node(std3, null);
                Node stdtwo = new Node(std2, stdthree);
                stds = new Node(std1, stdtwo);
            }


            private void delstd() {
                JButton ok;
                JButton cancel;

                delSscreenB = new JPanel();
                delSscreenT = new JPanel();
                frame.setVisible(false);
                frame.remove(display);
                frame.remove(display2);

                frame.add(delSscreenB, BorderLayout.SOUTH);
                frame.add(delSscreenT, BorderLayout.NORTH);

                cancel = new JButton("Cancel");
                cancel.addActionListener(this);
                cancel.setActionCommand("canceldel");
                delSscreenB.add(cancel);

                ok = new JButton("Confirm");
                ok.addActionListener(this);
                ok.setActionCommand("confirm");
                delSscreenB.add(ok);

                Node p;
                p = stds;
                for (int i = 0; i < stdsCount(); i++) {
                    delSscreenT.add(p.newCB);
                    p = p.next;
                }

                frame.setVisible(true);

            }

            private void addstd() {
                JButton ok;
                JButton cancel;
                JLabel Name;
                JLabel Email;
                JLabel Bday;
                JSpinner B;
                String[] Schoice = {"M", "F", "Other"};

                frame.setVisible(false);
                addSscreenB = new JPanel();
                addSscreenT = new JPanel();

                frame.remove(display);
                frame.remove(display2);
                frame.add(addSscreenB, BorderLayout.SOUTH);
                frame.add(addSscreenT, BorderLayout.NORTH);

                Name = new JLabel("Name:");
                N = new JFormattedTextField();
                N.setColumns(10);
                Name.setLabelFor(N);
                addSscreenT.add(Name);
                addSscreenT.add(N);

                Email = new JLabel("Email:");
                E = new JFormattedTextField();
                E.setColumns(10);
                Email.setLabelFor(E);
                addSscreenT.add(Email);
                addSscreenT.add(E);

                Bday = new JLabel("Birthday: MM/DD/YYYY");
                Bd = new SpinnerDateModel();
                B = new JSpinner(Bd);
                B.setEditor(new JSpinner.DateEditor(B, "MM/dd/yyyy"));
                Bday.setLabelFor(B);
                addSscreenT.add(Bday);
                addSscreenT.add(B);

                Sexchoice = new JComboBox(Schoice);
                Sexchoice.setSelectedIndex(0);
                Sexchoice.addActionListener(this);
                addSscreenT.add(Sexchoice);

                cancel = new JButton("Cancel");
                cancel.addActionListener(this);
                cancel.setActionCommand("cancel");
                addSscreenB.add(cancel);

                ok = new JButton("Save");
                ok.addActionListener(this);
                ok.setActionCommand("ok");
                addSscreenB.add(ok);

                frame.setVisible(true);
            }


            private void setUpDisplay() {
                JButton Nstudent;
                JButton Dstudent;
                JButton Ntest;
                JButton Emark;
                JButton Dtest;
                JButton Quit;
                JButton Refresh;
                display = new JPanel();
                display2 = new JPanel();
                frame.setLayout(new BorderLayout());
                display2.setLayout(new BorderLayout());
                frame.resize(1440, 300);
                frame.add(display, BorderLayout.NORTH);
                frame.add(display2, BorderLayout.CENTER);

                Nstudent = new JButton("New Student");
                Nstudent.addActionListener(this);
                Nstudent.setActionCommand("Nstd");
                display.add(Nstudent);

                Dstudent = new JButton("Delete Student");
                Dstudent.addActionListener(this);
                Dstudent.setActionCommand("Dstd");
                display.add(Dstudent);

                Ntest = new JButton("New Test");
                Ntest.addActionListener(this);
                Ntest.setActionCommand("Ntest");
                display.add(Ntest);


                Emark = new JButton("Edit Tests");
                Emark.addActionListener(this);
                Emark.setActionCommand("Emark");
                display.add(Emark);

                Dtest = new JButton("Delete Tests");
                Dtest.addActionListener(this);
                Dtest.setActionCommand("Dtest");
                display.add(Dtest);

                Refresh = new JButton("Refresh");
                Refresh.addActionListener(this);
                Refresh.setActionCommand("Refresh");
                display.add(Refresh);

                Quit = new JButton("Quit");
                Quit.addActionListener(this);
                Quit.setActionCommand("Quit");
                display.add(Quit);

                display2.add(sorting, BorderLayout.NORTH);
                SortComboBox();

                Node pointer;
                pointer = stds;
                int r = 0;
                int i = 0;
                if (stds != null) {
                    String[] tableC = new String[6 + pointer.name.lengthTest()];
                    tableC[i] = "Name";
                    i++;
                    tableC[i] = "Email";
                    i++;
                    tableC[i] = "Age";
                    i++;
                    tableC[i] = "MM/DD/YYYY";
                    i++;
                    tableC[i] = "Sex";
                    i++;
                    int num = 1;
                    Test p;
                    p = stds.name.getTestsNode();
                    while (i < stds.name.lengthTest() + 5) {
                        tableC[i] = p.nameT;
                        i++;
                        num++;
                        p = p.next;
                    }
                    tableC[i] = "Mark %";

                    String[][] rows = new String[stdsCount()][tableC.length];
                    while (pointer != null) {
                        rows[r][0] = pointer.name.name();
                        rows[r][1] = pointer.name.email();
                        try {
                            rows[r][2] = String.valueOf(pointer.name.age());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        rows[r][3] = pointer.name.Bday();
                        rows[r][4] = pointer.name.sex();
                        for (int j = 5; j < pointer.name.lengthTest() + 5; j++) {
                            rows[r][j] = String.valueOf(pointer.name.getOneTest(j - 5).markT);
                        }
                        rows[r][pointer.name.lengthTest() + 5] = String.valueOf(pointer.name.marks());
                        pointer = pointer.next;
                        r++;
                    }
                    table = new JTable(rows, tableC);
                    table.setGridColor(Color.black);
                    JScrollPane scrollPane = new JScrollPane(table);
                    table.setFillsViewportHeight(true);
                    table.getModel().addTableModelListener(this);
                    display2.add(scrollPane, BorderLayout.CENTER);
                }
                frame.setVisible(true);
            }

            public int stdsCount() {
                Node p;
                p = stds;
                int moves = 0;

                while (p != null) {
                    moves++;
                    p = p.next;
                }
                return moves;
            }

            public void saveChanges() {
                Student Newstd;
                Node student;
                Node p;

                p = stds;
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                Newstd = new Student(N.getText(), E.getText(), sdf.format((Date) Bd.getValue()), (String) Sexchoice.getSelectedItem());
                student = new Node(Newstd, null);
                if (p == null) {
                    stds = student;
                } else {
                    while (p.next != null) {
                        p = p.next;
                    }
                    p.next = student;
                }
                Test pointer = stds.name.getTestsNode();
                if (stds.name.lengthTest()>0){
                    for (int i=0;i<stds.name.lengthTest();i++){
                        student.name.addTest(pointer.nameT, pointer.weightT);
                        pointer=pointer.next;
                    }
                }
            }

            public void tableChanged(TableModelEvent e) {
                Node p;
                p = stds;
                int row = e.getFirstRow();
                int cols = e.getColumn();

                TableModel model = (TableModel) e.getSource();
                String columnName = model.getColumnName(cols);
                Object data = model.getValueAt(row, cols);

                if (cols == 2 | cols == 5 + stds.name.lengthTest()) {
                    return;
                } else if (cols == 0) {
                    for (int i = 0; i < row; i++) {
                        p = p.next;
                    }
                    p.name.Setname((String) data);
                } else if (cols == 1) {
                    for (int i = 0; i < row; i++) {
                        p = p.next;
                    }
                    p.name.Setemail((String) data);
                } else if (cols == 3) {
                    for (int i = 0; i < row; i++) {
                        p = p.next;
                    }
                    p.name.SetBday((String) data);
                } else if (cols == 4) {
                    for (int i = 0; i < row; i++) {
                        p = p.next;
                    }
                    p.name.Setsex((String) data);
                } else {
                    for (int j = 5; j < p.name.lengthTest() + 5; j++) {
                        if (cols == j) {
                            for (int i = 0; i < row; i++) {
                                p = p.next;
                            }
                            p.name.getOneTest(j - 5).setMarkT(new Double(data.toString()));
                        }
                    }
                }
            }

            public void saveDel2() {
                Node p;
                Node q;
                int count = stdsCount();

                p = stds;
                q = null;
                for (int i = 0; i < count; i++) {
                    if (p.newCB.getModel().isSelected()) {
                        if (q == null && p.next == null) {
                            stds = null;
                        } else if (q == null) {
                            stds = p.next;
                        } else if (p == null) {
                            q.next = null;
                        } else {
                            q.next = p.next;
                        }
                    }
                    q = p;
                    p = p.next;
                }
            }

            public void saveDel() {
                int num;
                Node p;
                p = stds;
                num = 0;
                for (int i = 0; i < stdsCount(); i++) {
                    if (p.newCB.getModel().isSelected()) {
                        num++;
                    }
                    p = p.next;
                }
                for (int i = 0; i < num; i++) {
                    saveDel2();
                }

            }

            public void addTest() {

                JLabel nameLabel;
                JLabel weightLabel;
                JButton ConfirmTest;
                JButton cancelTest;

                addTestSscreenB = new JPanel();
                addTestSscreenT = new JPanel();
                frame.setVisible(false);
                frame.remove(display);
                frame.remove(display2);

                frame.add(addTestSscreenT, BorderLayout.NORTH);
                frame.add(addTestSscreenB, BorderLayout.SOUTH);

                nameLabel = new JLabel("Test Name:");
                nameTest = new JFormattedTextField();
                nameTest.setColumns(10);
                nameLabel.setLabelFor(nameTest);
                addTestSscreenT.add(nameLabel);
                addTestSscreenT.add(nameTest);

                weightLabel = new JLabel("Weight:");
                NumberFormat format = NumberFormat.getInstance();
                NumberFormatter formatter = new NumberFormatter(format);
                formatter.setValueClass(Integer.class);
                formatter.setMaximum(100);
                formatter.setAllowsInvalid(false);
                weight = new JFormattedTextField(formatter);
                weight.setColumns(10);
                weightLabel.setLabelFor(weight);
                addTestSscreenT.add(weightLabel);
                addTestSscreenT.add(weight);

                cancelTest = new JButton("Cancel");
                cancelTest.addActionListener(this);
                cancelTest.setActionCommand("cancelTest");
                addTestSscreenB.add(cancelTest);

                ConfirmTest = new JButton("Confirm");
                ConfirmTest.addActionListener(this);
                ConfirmTest.setActionCommand("ConfirmTest");
                addTestSscreenB.add(ConfirmTest);

                frame.setVisible(true);

            }

            public void saveTest() {
                Node p;
                p = stds;
                if (weight.getValue() == null) return;
                for (int i = 0; i < stdsCount(); i++) {
                    p.name.addTest(nameTest.getText(), (Integer) weight.getValue());
                    p = p.next;
                }
            }

            public void EditTest() {
                JLabel nameLabel;
                JLabel weightLabel;
                JButton okEdit;
                JButton cancelEdit;

                EditTestSscreenC = new JPanel();
                EditTestSscreenT = new JPanel();
                EditTestSscreenB = new JPanel();
                frame.setVisible(false);
                frame.remove(display);
                frame.remove(display2);

                frame.add(EditTestSscreenT, BorderLayout.NORTH);
                frame.add(EditTestSscreenC, BorderLayout.CENTER);
                frame.add(EditTestSscreenB, BorderLayout.SOUTH);

                nameLabel = new JLabel("Test Name:");
                EditTName = new JFormattedTextField();
                EditTName.setColumns(10);
                nameLabel.setLabelFor(EditTName);
                EditTestSscreenT.add(nameLabel);
                EditTestSscreenT.add(EditTName);

                weightLabel = new JLabel("Weight:");
                NumberFormat format = NumberFormat.getInstance();
                NumberFormatter formatter = new NumberFormatter(format);
                formatter.setValueClass(Integer.class);
                formatter.setMaximum(100);
                formatter.setAllowsInvalid(false);
                Editweight = new JFormattedTextField(formatter);
                Editweight.setColumns(10);
                weightLabel.setLabelFor(Editweight);
                EditTestSscreenT.add(weightLabel);
                EditTestSscreenT.add(Editweight);

                Test p;
                if (stds.name.lengthTest() > 0) {
                    p = stds.name.getTestsNode();
                    for (int i = 0; i < stds.name.lengthTest(); i++) {
                        EditTestSscreenC.add(p.newCB);
                        p = p.next;
                    }
                }

                cancelEdit = new JButton("Cancel");
                cancelEdit.addActionListener(this);
                cancelEdit.setActionCommand("cancelEdit");
                EditTestSscreenB.add(cancelEdit);

                okEdit = new JButton("Save");
                okEdit.addActionListener(this);
                okEdit.setActionCommand("okEdit");
                EditTestSscreenB.add(okEdit);

                frame.setVisible(true);
            }

            public void saveTestEdit() {
                int num;
                Test p;
                p = stds.name.getTestsNode();
                num = 0;
                if (Editweight.getValue() == null) return;
                for (int i = 0; i < stds.name.lengthTest(); i++) {
                    if (p.newCB.getModel().isSelected()) {
                        num++;
                    }
                    p = p.next;
                }
                if (num > 0) {
                    Test pointer;
                    Node NodeP;
                    Test q;

                    pointer = stds.name.getTestsNode();
                    q = null;
                    for (int i = 0; i < stds.name.lengthTest(); i++) {
                        if (pointer.newCB.getModel().isSelected()) {
                            NodeP = stds;
                            for (int j = 0; j < stdsCount(); j++) {
                                NodeP.name.getTestsNode().setNameT(EditTName.getText());
                                NodeP.name.getTestsNode().setWeightT((Integer) Editweight.getValue());
                                pointer.newCB.setSelected(false);
                                NodeP = NodeP.next;
                            }
                        }
                        q = pointer;
                        pointer = pointer.next;
                    }
                } else {
                    frame.setVisible(false);
                    frame.remove(EditTestSscreenB);
                    frame.remove(EditTestSscreenC);
                    frame.remove(EditTestSscreenT);
                    setUpDisplay();
                }
            }


            public static void main(String[] args) {
                new DataBase();
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == sorting) {
                    JComboBox cb = (JComboBox) e.getSource();
                    String msg = ((String) cb.getSelectedItem());
                } else if (e.getActionCommand() == "Nstd") {
                    addstd();
                } else if (e.getActionCommand() == "Dstd") {
                    delstd();
                } else if (e.getActionCommand() == "Quit") {
                    System.exit(0);
                } else if (e.getActionCommand() == "Refresh") {
                    frame.setVisible(false);
                    frame.remove(display);
                    frame.remove(display2);
                    setUpDisplay();
                } else if (e.getActionCommand() == "ok") {
                    saveChanges();
                    frame.setVisible(false);
                    frame.remove(addSscreenB);
                    frame.remove(addSscreenT);
                    setUpDisplay();
                } else if (e.getActionCommand() == "cancel") {
                    frame.setVisible(false);
                    frame.remove(addSscreenB);
                    frame.remove(addSscreenT);
                    setUpDisplay();
                } else if (e.getActionCommand() == "Dtest") {
                    deltest();
                } else if (e.getActionCommand() == "canceldel") {
                    frame.setVisible(false);
                    frame.remove(delSscreenB);
                    frame.remove(delSscreenT);
                    setUpDisplay();
                } else if (e.getActionCommand() == "confirm") {
                    saveDel();
                    frame.setVisible(false);
                    frame.remove(delSscreenB);
                    frame.remove(delSscreenT);
                    setUpDisplay();
                } else if (e.getActionCommand() == "Ntest") {
                    addTest();
                } else if (e.getActionCommand() == "cancelTest") {
                    frame.setVisible(false);
                    frame.remove(addTestSscreenB);
                    frame.remove(addTestSscreenT);
                    setUpDisplay();
                } else if (e.getActionCommand() == "ConfirmTest") {
                    saveTest();
                    frame.setVisible(false);
                    frame.remove(addTestSscreenB);
                    frame.remove(addTestSscreenT);
                    setUpDisplay();
                } else if (e.getActionCommand() == "Emark") {
                    if (stds.name.lengthTest() > 0)
                        EditTest();
                } else if (e.getActionCommand() == "cancelEdit") {
                    frame.setVisible(false);
                    frame.remove(EditTestSscreenB);
                    frame.remove(EditTestSscreenC);
                    frame.remove(EditTestSscreenT);
                    Test pointer;
                    Node p;
                    p = stds;
                    for (int i = 0; i < stdsCount(); i++) {
                        pointer = p.name.getTestsNode();
                        for (int j = 0; j < stds.name.lengthTest(); j++) {
                            pointer.newCB.setSelected(false);
                            pointer = pointer.next;
                        }
                        p = p.next;
                    }
                    setUpDisplay();
                } else if (e.getActionCommand() == "okEdit") {
                    saveTestEdit();
                    frame.setVisible(false);
                    frame.remove(EditTestSscreenB);
                    frame.remove(EditTestSscreenC);
                    frame.remove(EditTestSscreenT);
                    setUpDisplay();
                } else if (e.getActionCommand() == "cancelDelTest") {
                    frame.setVisible(false);
                    frame.remove(DelTestSscreenB);
                    frame.remove(DelTestSscreenT);
                    Test pointer;
                    Node p;
                    p = stds;
                    for (int i = 0; i < stdsCount(); i++) {
                        pointer = p.name.getTestsNode();
                        for (int j = 0; j < stds.name.lengthTest(); j++) {
                            pointer.newCB.setSelected(false);
                            pointer = pointer.next;
                        }
                        p = p.next;
                    }
                    setUpDisplay();
                } else if (e.getActionCommand() == "saveDelTest") {
                    saveTestDel();
                    frame.setVisible(false);
                    frame.remove(DelTestSscreenB);
                    frame.remove(DelTestSscreenT);
                    setUpDisplay();
                }
            }

            private void saveTestDel() {
                Node p;
                p = stds;
                int num = stds.name.lengthTest();
                int count;

                for (int i = 0; i < num; i++) {
                    count = PositionDElTest();
                    p=stds;
                    for (int j = 0; j < stdsCount(); j++) {
                        p.name.delTests(count);
                        p = p.next;
                    }
                }
            }

            private int PositionDElTest(){
                Test p;
                int position;
                p = stds.name.getTestsNode();
                position = 0;
                for (int i = 0; i < stds.name.lengthTest(); i++) {
                    if (p.newCB.getModel().isSelected()) {
                        return position;
                        }
                    p=p.next;
                    position++;
                    }
                    return -1;
                }

            private void deltest() {
                JButton saveDelTest;
                JButton cancelDelTest;

                DelTestSscreenT = new JPanel();
                DelTestSscreenB = new JPanel();
                frame.setVisible(false);
                frame.remove(display);
                frame.remove(display2);

                frame.add(DelTestSscreenB, BorderLayout.SOUTH);
                frame.add(DelTestSscreenT, BorderLayout.NORTH);

                Test p;
                if (stds.name.lengthTest() > 0) {
                    p = stds.name.getTestsNode();
                    for (int i = 0; i < stds.name.lengthTest(); i++) {
                        DelTestSscreenT.add(p.newCB);
                        p = p.next;
                    }
                }
                cancelDelTest = new JButton("Cancel");
                cancelDelTest.addActionListener(this);
                cancelDelTest.setActionCommand("cancelDelTest");
                DelTestSscreenB.add(cancelDelTest);

                saveDelTest = new JButton("Save");
                saveDelTest.addActionListener(this);
                saveDelTest.setActionCommand("saveDelTest");
                DelTestSscreenB.add(saveDelTest);

                frame.setVisible(true);

            }
            private void SortComboBox(){
                if (sorting.getSelectedIndex() == 0) {
                    stds = merge(new AToZComparator(), stds);
                }
                else if (sorting.getSelectedIndex() == 1){
                    stds = merge(new ZToAComparator(),stds);
                }
                else if (sorting.getSelectedIndex() == 2){
                    stds = merge(new AgeComparator(),stds);
                }
            }

            private Node sorting(Comparator<Student> k, Node std1, Node std2) {
                Node result = null;
                if (std1 == null)
                    return std2;
                if (std2 == null)
                    return std1;
                if (k.compare(std1.name,std2.name) <= 0) {
                    result = std1;
                    result.next = sorting(k,std1.next,std2);
                }
                else{
                    result = std2;
                    result.next = sorting(k,std1,std2.next);
                }
                return result;
            }
        private Node merge(Comparator<Student> k,Node p){
                if (p==null || p.next==null)
                    return p;
                Node middle = getMiddle(p);
                Node middleNext = middle.next;
                middle.next = null;
                Node left= merge(k,p);
                Node right = merge(k,middleNext);
                Node sorted = sorting(k,left,right);
                return sorted;
            }
        private Node getMiddle(Node h){
                if(h==null)
                    return h;
                Node slow = h;
                Node fast = h;

                while(fast.next !=null && fast.next.next !=null){
                    slow = slow.next;
                    fast = fast.next.next;
                }
                return slow;
            }

        }