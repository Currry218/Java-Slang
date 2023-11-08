import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Interface {
    public static void createAndShowGUI(){

        JTextField searchbar = new JTextField(20);
        JButton WordSearch = new JButton("Search by word");
        JButton DefSearch = new JButton("Search by definition");

        JPanel northpann = new JPanel();
        northpann.setBackground(Color.PINK);
        northpann.add(searchbar);
        northpann.add(WordSearch);
        northpann.add(DefSearch);

        JButton addSlang = new JButton("Add");
        addSlang.getPreferredSize();
        JButton DelSlang = new JButton("Delete");
        JButton EditSlang = new JButton("Edit");       

        JPanel southpann = new JPanel();
        southpann.setBackground(Color.CYAN);

        southpann.add(addSlang);
        southpann.add(DelSlang);
        southpann.add(EditSlang);

        JList slist = new JList();
        slist.setLayoutOrientation(2);

        JPanel centerpann = new JPanel();
        centerpann.setBackground(Color.DARK_GRAY);        

        centerpann.add(slist);

        JFrame homepage = new JFrame("Slang Dictionary");
        homepage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homepage.setVisible(true); 
        // homepage.setSize(500, 500);
        homepage.setLayout(new BorderLayout());
        homepage.add(northpann, BorderLayout.NORTH);
        homepage.add(southpann, BorderLayout.SOUTH);
        homepage.add(centerpann, BorderLayout.CENTER);
        // homepage.add(WordSearch, BorderLayout.NORTH);
        // homepage.add(DefSearch, BorderLayout.SOUTH);
        homepage.pack();
        homepage.setResizable(false); 
    }
    public static void main(String[] args) {
        createAndShowGUI();
    }
}
