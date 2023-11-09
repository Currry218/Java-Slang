import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.*;
import javax.swing.table.TableColumnModel;

import java.util.*;
import java.util.List;
public class Interface {
    public static SlangList sl = new SlangList("slang.txt");

    public static String[][] MapToArray(HashMap<String, String> hmap)
    {
        String[][] tab = new String[hmap.size()][2];
        int tmp = 0;
        for(String i : hmap.keySet())
        {
            tab[tmp][0] = i;
            tab[tmp++][1] = hmap.get(i);
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
        JTextField searchbar = new JTextField(20);
        JButton WordSearch = new JButton("Search by slang");
        JButton DefSearch = new JButton("Search by definition");
        
        JButton hisButton = new JButton("History");        
        JFrame history = new JFrame("History");
        history.add(new JScrollPane(new JList(GetHistoryList().toArray())));
        
        JLabel lab1 = new JLabel("LAB01", JLabel.CENTER);
        JLabel lab2 = new JLabel("LAB02", JLabel.CENTER);        
        
         //----------------------SOUTH----------------------
        JLabel dictlabel = new JLabel("Dictionary edit: ");
        JButton addSlang = new JButton("Add");
        JButton DelSlang = new JButton("Delete");
        JButton EditSlang = new JButton("Edit");       
        
         //----------------------CENTER----------------------
        String[] colName = {"Slang", "Definition"};
        JTable tables = new JTable(MapToArray(sl.slangList),colName);
        tables.setEnabled(false);
        tables.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        TableColumnModel colModel=tables.getColumnModel();
        colModel.getColumn(0).setPreferredWidth(50);    
        colModel.getColumn(1).setPreferredWidth(400);
        JScrollPane slist = new JScrollPane(tables);

//----------------------------PANEL----------------------------        
        
        //----------------------NORTH----------------------
        JPanel northpann = new JPanel();
        northpann.setBackground(Color.PINK);
        northpann.setLayout(new BorderLayout());
        northpann.setPreferredSize(new Dimension(100, 100) );        
        
        JPanel upper = new JPanel();
        upper.setBackground(Color.blue);
        upper.add(searchbar);
        upper.add(WordSearch);
        upper.add(DefSearch);
        upper.add(hisButton);
        northpann.add(upper, BorderLayout.NORTH);
        northpann.add(lab1, BorderLayout.CENTER);  

        //----------------------SOUTH----------------------
        JPanel southpann = new JPanel();
        southpann.setBackground(Color.CYAN);
        southpann.add(dictlabel);
        southpann.add(addSlang);
        southpann.add(DelSlang);
        southpann.add(EditSlang);
        
         //----------------------CENTER----------------------      
        JPanel centerpann = new JPanel();
        centerpann.setBackground(Color.DARK_GRAY);   
        centerpann.setLayout(new BorderLayout());    
        centerpann.add(slist, BorderLayout.CENTER);

        //----------------------EAST----------------------
        JPanel eastpann = new JPanel();
        eastpann.setBackground(Color.ORANGE);   
        eastpann.setMinimumSize(new Dimension(20,20));
        eastpann.add(new JLabel(sl.OnThisDaySlangWord()));    



//----------------------------ACTIONLISTENER----------------------------
        //----------------------NORTH----------------------
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
        hisButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                history.pack();
                history.setVisible(true);
                
            }
        });

         //----------------------EAST----------------------
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
        
         //----------------------HOME----------------------
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
