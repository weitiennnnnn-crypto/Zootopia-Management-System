package src.main;

import javax.swing.*;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class MainPanel extends JPanel
{
    GridBagConstraints gbc = new GridBagConstraints();
    MLabel label1 = new MLabel("You can start by checking ANY species to check", 20f);
    MLabel label2 = new MLabel("their information.", 20f);

    public MainPanel()
    {
        label1.setForeground(Color.YELLOW);
        label2.setForeground(Color.YELLOW);
        setLayout(new GridBagLayout());
        setBackground(Color.DARK_GRAY);
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        add(new MLabel("Welcome", 36f), gbc);
        gbc.gridy = 1;
        add(new MLabel("to", 36f), gbc);
        gbc.gridy = 2;
        add(new MLabel("ZooToPia Management System!!!", 34f), gbc);
        gbc.gridy = 3;
        add(new MLabel(" ", 34f), gbc);
        gbc.gridy = 4;
        add(label1, gbc);
        gbc.gridy = 5;
        add(label2, gbc);
    }
}