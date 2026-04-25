package src.main;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import src.zoo.Species;

public class MenuPanel extends JPanel implements ActionListener
{
    //What this menu panel has: Basic animal type and button for create and remove animal
    MBorder menuB = new MBorder("Species");
    MButton importButton = new MButton("New", "Add new species");
    MButton updateButton = new MButton("Update", "Update species info");
    InfoPanel2 panelRef;
    MainFrame mainRef;
    NestedList list;
    
    //Constructor
    public MenuPanel(InfoPanel2 panel, MainFrame mainRef)
    {
        //Setting up everything first
        list = new NestedList(mainRef);
        this.panelRef = panel;
        this.mainRef = mainRef;
        setLayout(new BorderLayout());
        setBorder(menuB);
        setBackground(Color.DARK_GRAY);

        list.loadFromFile();
        list.setPreferredSize(new Dimension(250, 500));
        importButton.addActionListener(this); updateButton.addActionListener(this);

        //Then add them to the panel
        add(list, BorderLayout.NORTH);
        add(importButton);
        add(updateButton, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == importButton) {
            ImportDialog importD = new ImportDialog(mainRef, "Import New Animals", true);
            importD.setVisible(true);
        } else if (e.getSource() == updateButton) {
            ArrayList<Species> currentSpecies = Species.loadSpeciesFromFile();
            UpdateDialog updateD = new UpdateDialog(mainRef, "Update Species Info", true, currentSpecies, this);
            updateD.setVisible(true);
        }
    }

    public NestedList getList() { return list; }
}