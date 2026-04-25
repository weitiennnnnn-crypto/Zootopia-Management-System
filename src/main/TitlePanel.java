package src.main;

import java.awt.Component;
import javax.swing.*;
import java.awt.Color;

public class TitlePanel extends JPanel
{
    //What this panel has: A title label
    JLabel titleLabel = new JLabel("ZooToPia Management System");
    JLabel nickPic = new JLabel(new ImageIcon(new ImageIcon("src/icons/nick.png").getImage().getScaledInstance(48, 48, 24)));
    JLabel judyPic = new JLabel(new ImageIcon(new ImageIcon("src/icons/judy.png").getImage().getScaledInstance(48, 48, 24)));
    JLabel fennPic = new JLabel(new ImageIcon(new ImageIcon("src/icons/fennick.png").getImage().getScaledInstance(32, 48, 24)));

    //Constructor
    public TitlePanel()
    {
        //Setting up everything first
        setBackground(new Color(255, 255, 255));
        titleLabel.setFont(ZootopiaManagementSystem.mcFont.deriveFont(42f));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Then add to the panel
        add(nickPic);
        add(judyPic);
        add(titleLabel);
        add(fennPic);
    }
}