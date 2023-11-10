import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.util.*;
public class Interface {
    public static SlangList sl = new SlangList("slangedit.data");
    public static String[][] dataTable = MapToArray(sl.slangList);
    static int row = -1;
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
    public static JTable createTable(String[][] arr, String[] colName)
    {
        // https://stackoverflow.com/a/3134006
        DefaultTableModel tableModel = new DefaultTableModel(arr, colName) {
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
        colModel.getColumn(0).setPreferredWidth(10);    
        colModel.getColumn(1).setPreferredWidth(20);
        colModel.getColumn(2).setPreferredWidth(700);
        tables.setVisible(true);
        return tables;
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
        
        JPanel searchpann = new JPanel();
        searchpann.setBackground(Color.blue);
        searchpann.add(searchbar);
        searchpann.add(WordSearch);
        searchpann.add(DefSearch);
        searchpann.add(hisButton);

        JLabel lab1 = new JLabel("LAB01", JLabel.CENTER);
        JLabel lab2 = new JLabel("LAB02", JLabel.CENTER);        
        
         //----------------------SOUTH----------------------
        JLabel dictlabel = new JLabel("Dictionary edit: ");
        JButton addSlang = new JButton("Add");
        JButton DelSlang = new JButton("Delete");
        JButton EditSlang = new JButton("Edit");       
        
         //----------------------CENTER----------------------
        String[] colName = {"No.","Slang", "Definition"};

        JTable tables = createTable(dataTable, colName);
        JScrollPane slist = new JScrollPane(tables);

        // JTable tabs = new JTable(3,2);

        //----------------------EAST----------------------
        JLabel dailySlang = new JLabel(sl.OnThisDaySlangWord());
        dailySlang.setFont(new Font("Verdana", Font.PLAIN, 17));
        dailySlang.setHorizontalAlignment(JLabel.CENTER);
        JButton res = new JButton("Reset");
//----------------------------PANEL----------------------------        
        
        //----------------------NORTH----------------------
        JPanel northpann = new JPanel();
        northpann.setBackground(Color.PINK);
        northpann.setLayout(new BorderLayout());
        northpann.setPreferredSize(new Dimension(100, 100) );        
        

        northpann.add(dailySlang, BorderLayout.CENTER);
        northpann.add(searchpann, BorderLayout.NORTH);
        // northpann.add(lab1, BorderLayout.CENTER);  

        //----------------------SOUTH----------------------
        JPanel southpann = new JPanel();
        southpann.setBackground(Color.CYAN);
        southpann.add(dictlabel);
        southpann.add(addSlang);
        southpann.add(DelSlang);
        southpann.add(EditSlang);
        southpann.add(res);
         //----------------------CENTER----------------------      
        JPanel centerpann = new JPanel();
        centerpann.setBackground(Color.DARK_GRAY);   
        centerpann.setLayout(new CardLayout());
        // centerpann.setLayout(new BorderLayout());    
        centerpann.add(slist);

        //----------------------EAST----------------------
        JPanel eastpann = new JPanel();
        eastpann.setBackground(Color.ORANGE);   
        eastpann.setLayout(new BoxLayout(eastpann, BoxLayout.X_AXIS));
        // eastpann.setPreferredSize(new Dimension(100,400));
        // eastpann.add(dailySlang);    
        
//----------------------------FRAME----------------------------
        //----------------------HOME----------------------
        JFrame homepage = new JFrame("Slang Dictionary");
        homepage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homepage.setVisible(true); 
        homepage.setMinimumSize(new Dimension(1000, 600));
        homepage.setLayout(new BorderLayout());
        homepage.add(northpann, BorderLayout.NORTH);
        homepage.add(southpann, BorderLayout.SOUTH);
        homepage.add(centerpann, BorderLayout.CENTER);
        homepage.add(eastpann, BorderLayout.EAST);
        // homepage.setResizable(false); 
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
        deletePage.setLocationRelativeTo(null);

//----------------------------ACTIONLISTENER----------------------------
        //----------------------NORTH----------------------
        WordSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // tables.setVisible(false);
                // centerpann.remove(slist);
                // centerpann.setVisible(false);
                // slist.remove(tables);
                JTable ans = createTable(MapToArray(sl.FindDef(searchbar.getText())), colName);
                ans.setVisible(true);
                slist.setVisible(false);
                centerpann.add(ans);
                // centerpann.setLayout(null);
                // homepage.add(createTable(MapToArray(sl.FindDef(searchbar.getText())), colName), BorderLayout.CENTER);
            }
        });
        DefSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
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

         //----------------------EAST----------------------
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
                    slangbox_edit.setText(dataTable[row][1]);
                    defbox_edit.setText(dataTable[row][2]);                    
                }

            }
        });
         res.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dailySlang.setText(sl.OnThisDaySlangWord());
                sl.ResetList();
            }
        });       abtn_add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addPage.setVisible(false);
                sl.AddSlangWord(slangbox_add.getText(), defbox_add.getText(), true);
            }
        });        
        abtn_delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sl.DeleteSlangWord(dataTable[row][1]);
            }
        });            
        abtn_cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deletePage.setVisible(false);
            }
        });     
        abtn_edit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sl.Edit(dataTable[row][1], slangbox_edit.getText(), defbox_edit.getText());
            }
        });        
        // https://stackoverflow.com/a/7351053
        tables.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                row = tables.rowAtPoint(evt.getPoint());
            }
        });
        // tables.listener
        //instance table model


    }
    public static void main(String[] args) {
        // System.out.println(sl.FindDef("XS"));
        createAndShowGUI();
    }


}
