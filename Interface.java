import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Interface {
    public static void createAndShowGUI(){

        JTextField searchbar = new JTextField(20);
        // searchbar.setMinimumSize(new Dimension(50, 50));
        JButton WordSearch = new JButton("Search by word");
        // WordSearch.setPreferredSize(new Dimension(10, 10));

        JButton DefSearch = new JButton("Search by definition");
        // DefSearch.setPreferredSize(new Dimension(10, 10));

        JPanel southpann = new JPanel();
        southpann.setBackground(Color.PINK);
        southpann.add(searchbar);
        southpann.add(WordSearch);
        southpann.add(DefSearch);

        JPanel eastpann = new JPanel();
        eastpann.setBackground(Color.CYAN);
        
        JFrame homepage = new JFrame("Slang Dictionary");
        homepage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homepage.setVisible(true); 
        homepage.setSize(500, 500);
        homepage.setLayout(new BorderLayout());
        homepage.add(southpann, BorderLayout.NORTH);
        homepage.add(eastpann, BorderLayout.WEST);
        // homepage.add(WordSearch, BorderLayout.NORTH);
        // homepage.add(DefSearch, BorderLayout.SOUTH);
        homepage.pack();
        homepage.setResizable(false); 
    }
    public static void main(String[] args) {
        createAndShowGUI();
    }
}
