import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.*;
import javax.swing.table.TableColumnModel;

import java.util.*;
public class Interface {
    public static SlangList sl = new SlangList("slang.txt");

    // public static String[][] slToTable()
    // {
    //     String[][] tab = new String[sl.slangList.size()][2];
    //     int tmp = 0;
    //     for(String i : sl.slangList.keySet())
    //     {
    //         tab[tmp][0] = i;
    //         tab[tmp++][1] = sl.DefListToStr(sl.slangList.get(i));
    //     }        
    //     return tab;
    // }
    public static String[][] slToTable()
    {
        String[][] tab = new String[sl.slangList.size()][2];
        int tmp = 0;
        for(String i : sl.slangList.keySet())
        {
            tab[tmp][0] = i;
            tab[tmp++][1] = sl.DefListToStr(sl.slangList.get(i));
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

        JPanel northpann = new JPanel();
        northpann.setBackground(Color.PINK);
        // northpann.setLayout(new BoxLayout(northpann,BoxLayout.PAGE_AXIS));
        northpann.setLayout(new BorderLayout());
        northpann.setPreferredSize(new Dimension(100, 100) );

        JTextField searchbar = new JTextField(20);
        JButton WordSearch = new JButton("Search by slang");
        JButton DefSearch = new JButton("Search by definition");
        JButton hisButton = new JButton("History");

        JLabel lab1 = new JLabel("LAB01", JLabel.CENTER);
        JLabel lab2 = new JLabel("LAB02", JLabel.CENTER);
        WordSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lab1.setText(sl.FindDef(searchbar.getText()));
                lab1.setVisible(true);
                lab2.setVisible(false);
                northpann.add(lab1, BorderLayout.CENTER);
            }
        });
        DefSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lab2.setText(sl.FindSlang(searchbar.getText()).toString());
                lab1.setVisible(false);
                lab2.setVisible(true);
                northpann.add(lab2, BorderLayout.CENTER);
            }
        });
        JFrame history = new JFrame("History");
        history.add(new JScrollPane(new JList(GetHistoryList().toArray())));
        hisButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                history.pack();
                history.setVisible(true);
                
            }
        });




        JPanel upper = new JPanel();
        upper.setBackground(Color.blue);
        upper.add(searchbar);
        upper.add(WordSearch);
        upper.add(DefSearch);
        upper.add(hisButton);
        northpann.add(upper, BorderLayout.NORTH);
        northpann.add(lab1, BorderLayout.CENTER);
        // northpann.add(lab2, BorderLayout.SOUTH);


        JLabel dictlabel = new JLabel("Dictionary edit: ");
        JButton addSlang = new JButton("Add");
        addSlang.getPreferredSize();
        JButton DelSlang = new JButton("Delete");
        JButton EditSlang = new JButton("Edit");       

        JPanel southpann = new JPanel();
        southpann.setBackground(Color.CYAN);
        southpann.add(dictlabel);
        southpann.add(addSlang);
        southpann.add(DelSlang);
        southpann.add(EditSlang);
        
        // JFrame 
        // JFrame editframe = new JFrame();
        addSlang.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        DelSlang.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        EditSlang.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        // JPanel 


        // JScrollPane slist = new JScrollPane(new JList(sl.slangList.keySet().toArray()));
        String[] colName = {"Slang", "Definition"};
        JTable tables = new JTable(slToTable(),colName);
        tables.setEnabled(false);
        tables.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        TableColumnModel colModel=tables.getColumnModel();
        colModel.getColumn(0).setPreferredWidth(50);    
        colModel.getColumn(1).setPreferredWidth(400);
        JScrollPane slist = new JScrollPane(tables);

        // JTable table = new JTable(sl.slangList.keySet().toArray(),colName);
        // slist.set
        // JList deflist = new JList(sl.slangList.keySet().toArray());
        
        // slist.setLayoutOrientation(2);

        JPanel centerpann = new JPanel();
        centerpann.setBackground(Color.DARK_GRAY);   
        centerpann.setLayout(new CardLayout());     
        centerpann.add(slist);
        // centerpann.add(deflist);

        JPanel eastpann = new JPanel();
        eastpann.setBackground(Color.ORANGE);   
        eastpann.setMinimumSize(new Dimension(20,20));
        eastpann.add(new JLabel(sl.OnThisDaySlangWord()));    

        JFrame homepage = new JFrame("Slang Dictionary");
        homepage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homepage.setVisible(true); 
        // homepage.setSize(500, 500);
        homepage.setMinimumSize(new Dimension(1000, 600));
        homepage.setLayout(new BorderLayout());
        homepage.add(northpann, BorderLayout.NORTH);
        homepage.add(southpann, BorderLayout.SOUTH);
        homepage.add(centerpann, BorderLayout.CENTER);
        homepage.add(eastpann, BorderLayout.EAST);
        // homepage.pack();
        // homepage.setResizable(false); 
    }
    public static void main(String[] args) {
        // System.out.println(sl.FindDef("XS"));
        createAndShowGUI();
    }
}
