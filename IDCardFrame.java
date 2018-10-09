import javafx.scene.layout.BackgroundImage;

import javax.swing.*;
import java.awt.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


    /*
    extends the constructor from JFrame
     */
public class IDCardFrame extends JFrame {


    IDCardFrame(String title) {

        setTitle(title);
        setSize(500, 330);
        setLocationRelativeTo(null);
        JTextField textField = new JTextField(10);
        setVisible(true);
        setResizable(false);


        setLayout(new GridBagLayout());
        GridBagConstraints layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(2,2,2,2);

        /*
        imports the image for the ID card
         */
        ImageIcon icon = new ImageIcon("lilPic.jpg");
        JLabel myPic = new JLabel(icon);
        layoutConst.gridx = 5;
        layoutConst.gridy = 5;
        layoutConst.insets = new Insets(1,1,1,1);           //necessary?
        add(myPic,layoutConst);


        JLabel IDname = new JLabel("Rebekah Doochin");
        layoutConst.gridx = 5;
        layoutConst.gridy = 0;
        IDname.setForeground(Color.magenta);
        add(IDname, layoutConst);

        JLabel height = new JLabel("Height: 5' 3.5''");
        layoutConst.gridx = 0;
        layoutConst.gridy = 1;
        add(height, layoutConst);

        JLabel weight = new JLabel("Weight: 130 lb");
        layoutConst.gridx = 0;
        layoutConst.gridy = 2;
        add(weight, layoutConst);

        JLabel address = new JLabel("90 Victoria Park");
        layoutConst.gridx = 10;
        layoutConst.gridy = 1;
        add(address, layoutConst);

        JLabel eyeColor = new JLabel("Brown eyes");
        layoutConst.gridx = 10;
        layoutConst.gridy = 2;
        eyeColor.setForeground(Color.gray);
        add(eyeColor, layoutConst);

        JLabel prog = new JLabel("Java Programmer");
        layoutConst.gridx = 5;
        layoutConst.gridy = 10;
        Font boold = new Font("Courier", Font.BOLD,12);
        prog.setFont(boold);
        prog.setForeground(Color.magenta);
        add(prog, layoutConst);
        setVisible(true);

    }



    public static void main(String args[]) {

         /*
        attempting to add a background
         */
        JLabel background = new JLabel(new ImageIcon("back.jpg"));


        IDCardFrame myID = new IDCardFrame("Rebekah's ID Card");
        myID.add(background);
        myID.pack();
        myID.setResizable(false);
        myID.setVisible(true);
        myID.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}