import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public class Interface {
    public static SlangList sl = new SlangList("slang.txt");
    
    public static void createAndShowGUI(){

        JTextField searchbar = new JTextField(20);
        JButton WordSearch = new JButton("Search by slang");
        JButton DefSearch = new JButton("Search by definition");

        WordSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(sl.FindDef(searchbar.getText()));
            }
        });
        DefSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(sl.FindSlang(searchbar.getText()));
            }
        });
        JPanel northpann = new JPanel();
        northpann.setBackground(Color.PINK);
        // northpann.setLayout(new BoxLayout(northpann,BoxLayout.PAGE_AXIS));
         northpann.setLayout(new BorderLayout());
        JPanel upper = new JPanel();
        upper.setBackground(Color.blue);
        upper.add(searchbar);
        upper.add(WordSearch);
        upper.add(DefSearch);
        northpann.add(upper, BorderLayout.NORTH);
        northpann.add(new JLabel("gvuhbkj", JLabel.CENTER), BorderLayout.EAST);

        JButton addSlang = new JButton("Add");
        addSlang.getPreferredSize();
        JButton DelSlang = new JButton("Delete");
        JButton EditSlang = new JButton("Edit");       

        JPanel southpann = new JPanel();
        southpann.setBackground(Color.CYAN);
        southpann.add(addSlang);
        southpann.add(DelSlang);
        southpann.add(EditSlang);
        
        // JPanel 


        JScrollPane slist = new JScrollPane(new JList(sl.slangList.keySet().toArray()));
        // String[] colName = {"Slang","Definition"};

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
        eastpann.add(new JLabel("Today"));    

        JFrame homepage = new JFrame("Slang Dictionary");
        homepage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homepage.setVisible(true); 
        homepage.setSize(500, 500);
        homepage.setMinimumSize(new Dimension(800, 200));
        homepage.setLayout(new BorderLayout());
        homepage.add(northpann, BorderLayout.NORTH);
        homepage.add(southpann, BorderLayout.SOUTH);
        homepage.add(centerpann, BorderLayout.CENTER);
        // homepage.add(WordSearch, BorderLayout.NORTH);
        // homepage.add(DefSearch, BorderLayout.SOUTH);
        // homepage.pack();
        // homepage.setResizable(false); 
    }
    public static void main(String[] args) {
        // System.out.println(sl.FindDef("XS"));
        createAndShowGUI();
    }
}
