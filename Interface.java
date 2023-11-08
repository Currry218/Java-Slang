import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
// class Frame{
//     Frame(){
//         JFrame frame = new JFrame("Slang Dictionary");
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         frame.setSize(1000, 500);
//         frame.setResizable(false); 
//         // ____IMAGE ICON____
//         ImageIcon ico = new ImageIcon("icon.png");
//         frame.setIconImage(ico.getImage());

//         frame.getContentPane().setBackground(Color.PINK); //new Color(R,G,B or 0x123456); 
//         frame.setVisible(true); 
//     }
// }
// class Label{
//     Label(){
//         JLabel label = new JLabel();
//         label.setText("Meow");
//         // JLabel label = new JLabel(String "");
//     }
// }

public class Interface {
    public static void main(String[] args) {
        // ____JFRAME____
        JFrame frame = new JFrame("Slang Dictionary");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 500);
        frame.setResizable(false); 
        // ____IMAGE ICON____
        ImageIcon ico = new ImageIcon("icon.png");
        frame.setIconImage(ico.getImage());

        frame.getContentPane().setBackground(Color.PINK); //new Color(R,G,B or 0x123456); 
        frame.setVisible(true); 
        
        // ____LABEL____


        
    }


}
