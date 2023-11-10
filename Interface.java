import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.util.*;
import java.util.List;
// import Slanglist.*;
public class Interface{
    public static SlangList sl = new SlangList("slangedit.data");
    static String right_ans = "0";
    static int row = -1;
    public static String getKeyFromIdx(int idx)
    {
        String key = "";
        int num = 0;
        for(String i: sl.slangList.keySet())
        {
            if(num == idx){
                key = i;
                return key;
            }
            else num++;
        }
        return key;
    }
    public static String[][] MapToArray(HashMap<String, String> hmap)
    {
        String[][] tab = new String[hmap.size()][3];
        int tmp = 0;
        for(String i : hmap.keySet())
        {
            tab[tmp][0] = Integer.toString(tmp);
            tab[tmp][1] = i;
            tab[tmp++][2] = hmap.get(i);
        }        
        return tab;
    }
    public static ArrayList<String> GetHistoryList()
    {
        ArrayList<String> hlist = new ArrayList<String>();
        try {
            File myObj = new File("history.data");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                hlist.add(myReader.nextLine().replace("`", " "));
        }
        myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return hlist;
    }
    public static void createAndShowGUI(){
//----------------------------COMPONENT----------------------------
        //----------------------NORTH----------------------
        JTextField searchbar = new JTextField(25);
        JButton WordSearch = new JButton("Search by slang");
        JButton DefSearch = new JButton("Search by definition");
        JButton hisButton = new JButton("History");        
        JButton quizz = new JButton("Quiz");
        
        JPanel searchpann = new JPanel();
        searchpann.add(searchbar);
        searchpann.add(WordSearch);
        searchpann.add(DefSearch);
        searchpann.add(hisButton);
        searchpann.add(quizz);
        JLabel dailySlang = new JLabel(sl.OnThisDaySlangWord());
        dailySlang.setFont(new Font("Verdana", Font.PLAIN, 17));
        dailySlang.setHorizontalAlignment(JLabel.CENTER);
        
         //----------------------SOUTH----------------------
        JLabel dictlabel = new JLabel(" ");
        JButton addSlang = new JButton("Add");
        JButton DelSlang = new JButton("Delete");
        JButton EditSlang = new JButton("Edit");       
        JButton res = new JButton("Reset dictionary");
        JButton ref = new JButton("Refresh page");
         //----------------------CENTER----------------------
        String[] colName = {"No.","Slang", "Definition"};
        DefaultTableModel tableModel = new DefaultTableModel(MapToArray(sl.slangList), colName) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable tables = new JTable(tableModel);
        tables.setFont(new Font("Verdana", Font.PLAIN, 12));
        // https://stackoverflow.com/a/43301100
        tables.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        TableColumnModel colModel = tables.getColumnModel();
        colModel.getColumn(0).setPreferredWidth(50);    
        colModel.getColumn(1).setPreferredWidth(200);
        colModel.getColumn(2).setPreferredWidth(750);
        tables.setVisible(true);
        // JList<String> lists = new JList<String>();
        JScrollPane slist = new JScrollPane(tables);


//----------------------------PANEL----------------------------        
        
        //----------------------NORTH----------------------
        JPanel northpann = new JPanel();
        northpann.setLayout(new BorderLayout());
        northpann.setPreferredSize(new Dimension(100, 100) );        
        

        northpann.add(dailySlang, BorderLayout.CENTER);
        northpann.add(searchpann, BorderLayout.NORTH);
        

        //----------------------SOUTH----------------------
        JPanel southpann = new JPanel();
        southpann.add(dictlabel);
        southpann.add(addSlang);
        southpann.add(DelSlang);
        southpann.add(EditSlang);
        southpann.add(res);
        southpann.add(ref);
        
         //----------------------CENTER----------------------      
        JPanel centerpann = new JPanel();
        centerpann.setLayout(new CardLayout());
        // centerpann.setLayout(new BorderLayout());    
        centerpann.add(slist, "");
        
//----------------------------FRAME----------------------------
        //----------------------HOME----------------------
        JFrame homepage = new JFrame("Slang Dictionary");
        // https://stackoverflow.com/a/9093526
        homepage.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                sl.SaveList();
                System.exit(0);
            }
        });
        homepage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homepage.setVisible(true); 
        homepage.setMinimumSize(new Dimension(1000, 600));
        homepage.setLayout(new BorderLayout());
        homepage.add(northpann, BorderLayout.NORTH);
        homepage.add(southpann, BorderLayout.SOUTH);
        homepage.add(centerpann, BorderLayout.CENTER);
        homepage.setResizable(false); 
        homepage.setLocationRelativeTo(null);

        //----------------------EDIT----------------------
        JPanel scontainer_edit = new JPanel();
        JPanel dcontainer_edit = new JPanel();
        JPanel btncont_edit = new JPanel();

        JLabel sbox_edit = new JLabel("Slang:      ");
        JLabel dbox_edit = new JLabel("Definition:");

        JTextArea slangbox_edit = new JTextArea(2, 30);
        JTextArea defbox_edit = new JTextArea( 5, 30);

        JButton abtn_edit = new JButton("Edit");
        scontainer_edit.add(sbox_edit);
        scontainer_edit.add(slangbox_edit);

        dcontainer_edit.add(dbox_edit);
        dcontainer_edit.add(defbox_edit);

        btncont_edit.add(abtn_edit);    
        JFrame editPage = new JFrame("Edit Page");
        editPage.setLayout(new BorderLayout());
        editPage.add(scontainer_edit, BorderLayout.NORTH);
        editPage.add(dcontainer_edit, BorderLayout.CENTER);
        editPage.add(btncont_edit, BorderLayout.SOUTH);
        editPage.pack();
        editPage.setResizable(false); 
        editPage.setLocationRelativeTo(null);

        //----------------------ADD----------------------
        JPanel scontainer_add = new JPanel();
        JPanel dcontainer_add = new JPanel();
        JPanel btncont_add = new JPanel();

        JLabel sbox_add = new JLabel("Slang:      ");
        JLabel dbox_add = new JLabel("Definition:");

        JTextArea slangbox_add = new JTextArea(2, 30);
        JTextArea defbox_add = new JTextArea( 5, 30);

        JButton abtn_add = new JButton("Add");
        scontainer_add.add(sbox_add);
        scontainer_add.add(slangbox_add);

        dcontainer_add.add(dbox_add);
        dcontainer_add.add(defbox_add);

        btncont_add.add(abtn_add);    
        JFrame addPage = new JFrame("Add Page");
        addPage.setLayout(new BorderLayout());
        addPage.add(scontainer_add, BorderLayout.NORTH);
        addPage.add(dcontainer_add, BorderLayout.CENTER);
        addPage.add(btncont_add, BorderLayout.SOUTH);
        addPage.pack();
        addPage.setResizable(false); 
        addPage.setLocationRelativeTo(null);

        //----------------------DELETE----------------------
        JPanel scontainer_delete = new JPanel();
        JPanel btncont_delete = new JPanel();

        JLabel sbox_delete = new JLabel("Are you sure you want to delete this?");
        JButton abtn_delete = new JButton("Delete");
        JButton abtn_cancel = new JButton("Cancel");

        scontainer_delete.add(sbox_delete);
        btncont_delete.add(abtn_cancel);
        btncont_delete.add(abtn_delete);    

        JFrame deletePage = new JFrame("Delete Page");
        deletePage.setLayout(new BorderLayout());
        deletePage.add(scontainer_delete, BorderLayout.NORTH);
        deletePage.add(btncont_delete, BorderLayout.SOUTH);
        deletePage.pack();
        deletePage.setResizable(false); 
        deletePage.setLocationRelativeTo(null);

        //----------------------QUIZ----------------------
        JFrame quiz = new JFrame("Quiz");
        quiz.setLayout(new BorderLayout());
        JPanel anscont = new JPanel();
        anscont.setLayout(new GridLayout(2,2));

        JPanel btncont = new JPanel();

        JLabel question = new JLabel(" ");
        question.setFont(new Font("Verdana", Font.PLAIN, 20));
        question.setHorizontalAlignment(JLabel.CENTER);
        question.setPreferredSize(new Dimension(700,150));
        JButton ans1 = new JButton("");
        JButton ans2 = new JButton("");
        JButton ans3 = new JButton("");
        JButton ans4 = new JButton("");
        JButton toDef = new JButton("Guess Definition");
        JButton toSlang = new JButton("Guess Slang");

        anscont.add(ans1);
        anscont.add(ans2);
        anscont.add(ans3);
        anscont.add(ans4);

        btncont.add(toDef);
        btncont.add(toSlang);

        quiz.add(question, BorderLayout.NORTH);
        quiz.add(anscont, BorderLayout.CENTER);
        quiz.add(btncont, BorderLayout.SOUTH);
        quiz.setMinimumSize(new Dimension(700,400));
        quiz.setResizable(false); 
        quiz.setLocationRelativeTo(null);

//----------------------------ACTIONLISTENER----------------------------
        //----------------------NORTH----------------------
        WordSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tableModel = new DefaultTableModel(MapToArray(sl.FindDef(searchbar.getText())), colName) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                tables.setModel(tableModel);
                tables.setFont(new Font("Verdana", Font.PLAIN, 12));
                // https://stackoverflow.com/a/43301100
                tables.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
                TableColumnModel colModel = tables.getColumnModel();
                colModel.getColumn(0).setPreferredWidth(50);    
                colModel.getColumn(1).setPreferredWidth(200);
                colModel.getColumn(2).setPreferredWidth(750);
                tables.setVisible(true);
            }
        });
        DefSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tableModel = new DefaultTableModel(MapToArray(sl.FindSlang(searchbar.getText())), colName) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                tables.setModel(tableModel);
                tables.setFont(new Font("Verdana", Font.PLAIN, 12));
                // https://stackoverflow.com/a/43301100
                tables.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
                TableColumnModel colModel = tables.getColumnModel();
                colModel.getColumn(0).setPreferredWidth(50);    
                colModel.getColumn(1).setPreferredWidth(200);
                colModel.getColumn(2).setPreferredWidth(750);
                tables.setVisible(true);
            }
        });
        hisButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                        JFrame history = new JFrame("History");
        history.add(new JScrollPane(new JList(GetHistoryList().toArray())));
        history.setPreferredSize(new Dimension(300,300));
                history.pack();
                history.setVisible(true);
                history.setLocationRelativeTo(null);
            }
        });

         //----------------------SOUTH----------------------
        addSlang.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // centerpann.add(tabs, BorderLayout.CENTER);   
                // tabs.setVisible(true);
                addPage.setVisible(true);
            }
        });

        DelSlang.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(row >= 0)
                    deletePage.setVisible(true);
            }
        });

        EditSlang.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(row >= 0){
                    editPage.setVisible(true);
                    String key = getKeyFromIdx(row);
                    slangbox_edit.setText(key);
                    defbox_edit.setText(sl.slangList.get(key));                    
                }

            }
        });
        res.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dailySlang.setText(sl.OnThisDaySlangWord());
                sl.ResetList();
                DefaultTableModel d = new DefaultTableModel(MapToArray(sl.slangList), colName) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                tables.setModel(d);
                tables.setFont(new Font("Verdana", Font.PLAIN, 12));
                // https://stackoverflow.com/a/43301100
                tables.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
                TableColumnModel colModel = tables.getColumnModel();
                colModel.getColumn(0).setPreferredWidth(50);    
                colModel.getColumn(1).setPreferredWidth(200);
                colModel.getColumn(2).setPreferredWidth(750);
                tables.setVisible(true);
            }
        });       
        ref.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dailySlang.setText(sl.OnThisDaySlangWord());
                DefaultTableModel d = new DefaultTableModel(MapToArray(sl.slangList), colName) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                tables.setModel(d);
                tables.setFont(new Font("Verdana", Font.PLAIN, 12));
                // https://stackoverflow.com/a/43301100
                tables.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
                TableColumnModel colModel = tables.getColumnModel();
                colModel.getColumn(0).setPreferredWidth(50);    
                colModel.getColumn(1).setPreferredWidth(200);
                colModel.getColumn(2).setPreferredWidth(750);
                tables.setVisible(true);
            }
        });       
        quizz.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] a = sl.GetQuiz(false);
                
                question.setText(a[0]);
                ans1.setText(a[1]);
                ans2.setText(a[2]);
                ans3.setText(a[3]);
                ans4.setText(a[4]);
                right_ans = a[5];
                quiz.setVisible(true);
            }
            
        });          

        toDef.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ans1.setBackground(null);
                ans2.setBackground(null);
                ans3.setBackground(null);
                ans4.setBackground(null);

                String[] a = sl.GetQuiz(true);
                question.setText(a[0]);
                ans1.setText(a[1]);
                ans2.setText(a[2]);
                ans3.setText(a[3]);
                ans4.setText(a[4]);
                right_ans = a[5];
            }
        });         

        toSlang.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ans1.setBackground(null);
                ans2.setBackground(null);
                ans3.setBackground(null);
                ans4.setBackground(null);

                String[] a = sl.GetQuiz(false);
                question.setText(a[0]);
                ans1.setText(a[1]);
                ans2.setText(a[2]);
                ans3.setText(a[3]);
                ans4.setText(a[4]);
                right_ans = a[5];
            }
        });  

        ans1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(right_ans.contains("1")) {
                    ans1.setBackground(Color.GREEN);
                } else {
                    ans1.setBackground(Color.RED);
                }
            }
        });   
        ans2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(right_ans.contains("2")) {
                    ans2.setBackground(Color.GREEN);
                } else {
                    ans2.setBackground(Color.RED);
                }
            }
        }); 

        ans3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(right_ans.contains("3")) {
                    ans3.setBackground(Color.GREEN);
                } else {
                    ans3.setBackground(Color.RED);
                }
            }
        }); 

        ans4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(right_ans.contains("4")) {
                    ans4.setBackground(Color.GREEN);
                } else {
                    ans4.setBackground(Color.RED);
                }
            }
        }); 
        abtn_add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addPage.setVisible(false);
                sl.AddSlangWord(slangbox_add.getText(), defbox_add.getText(), true);
                String[] tmp = {Integer.toString(sl.slangList.size() - 1), slangbox_add.getText(), defbox_add.getText()};
                DefaultTableModel d = (DefaultTableModel)tables.getModel();
                d.addRow(tmp);
                tables.setModel(d);
            }
        });        
        abtn_delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sl.DeleteSlangWord(getKeyFromIdx(row));
                DefaultTableModel d = (DefaultTableModel)tables.getModel();
                d.removeRow(row);
                for(int i = row; i < sl.slangList.size(); i++)
                {
                    d.setValueAt(Integer.toString(i), i, 0);
                }
                row = -1;
                tables.setModel(d);
                deletePage.setVisible(false);
            }
        });            
        abtn_cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deletePage.setVisible(false);
            }
        });     
        abtn_edit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sl.Edit(getKeyFromIdx(row), slangbox_edit.getText(), defbox_edit.getText());
                String[] tmp = {Integer.toString(row), slangbox_edit.getText(), defbox_edit.getText()};
                DefaultTableModel d = (DefaultTableModel)tables.getModel();
                d.removeRow(row);
                d.insertRow(row, tmp);
                tables.setModel(d);
                editPage.setVisible(false);
            }
        });        
        // https://stackoverflow.com/a/7351053
        tables.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                row = tables.rowAtPoint(evt.getPoint());
                // System.out.println(row);
            }
        });


    }
    public static void main(String[] args) {
        createAndShowGUI();
    }


}
