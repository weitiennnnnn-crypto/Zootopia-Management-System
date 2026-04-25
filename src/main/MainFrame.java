package src.main;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import src.zoo.Animal;
import src.zoo.Species;

public class MainFrame extends JFrame
{
    //What the main frame has
    MainPanel mainP;
    TitlePanel titleP;
    BottomPanel menuP2;
    JPanel bigP = new JPanel(new GridLayout(2, 1));
    InfoPanel infoP;
    InfoPanel2 infoP2;
    MenuPanel menuP;

    //Constructor
    public MainFrame()
    {
        //Setting up everything first
        mainP = new MainPanel();
        titleP = new TitlePanel();
        infoP2 = new InfoPanel2();
        menuP = new MenuPanel(infoP2, this);
        infoP = new InfoPanel(menuP);
        menuP2 = new BottomPanel(this);

        bigP.setBackground(Color.DARK_GRAY);
        setIconImage(new ImageIcon("src/icons/appicon.png").getImage());
        setTitle("Zootopia Management System");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        //Then add them to the frame
        add(titleP, BorderLayout.NORTH);
        add(menuP, BorderLayout.WEST);
        add(menuP2, BorderLayout.SOUTH);
        add(mainP, BorderLayout.CENTER);
    }

    public void displayAnimalInfo(Species s, Animal a) {
        if (mainP.getParent() != null)
            mainP.setVisible(false);

        bigP.removeAll();

        infoP.setAnimalInfo(a);
        infoP2.setSpeciesInfo(s);
        infoP.setVisible(true); infoP2.setVisible(true);
        bigP.add(infoP); bigP.add(infoP2);
        add(bigP);
        
        this.revalidate();
        this.repaint();
    }

    public void displaySpeciesInfo(Species s) {
        if (mainP.getParent() != null)
            mainP.setVisible(false);

        bigP.removeAll();

        infoP2.setSpeciesInfo(s);
        infoP.setEmptyAnimal();
        bigP.add(infoP); bigP.add(infoP2);
        add(bigP);

        this.revalidate();
        this.repaint();
    }

}