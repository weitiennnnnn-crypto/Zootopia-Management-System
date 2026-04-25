package src.main;

import src.zoo.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.*;

public class BottomPanel extends JPanel implements ActionListener
{
    JPanel center = new JPanel();
    MButton addButton = new MButton("Add", "Adding a new animal");
    MButton removeButton = new MButton("Remove", "Remove an animal");
    MButton ticketButton = new MButton("Ticket", "Calculating the ticket price");
    MainFrame mainRef;

    public BottomPanel(MainFrame main)
    {
        this.mainRef = main;
        setLayout(new GridLayout(1, 3));
        setPreferredSize(new Dimension(0, 80));
        setBackground(Color.LIGHT_GRAY);
        center.setOpaque(false);
        addButton.addActionListener(this); removeButton.addActionListener(this); ticketButton.addActionListener(this);
        add(addButton); add(removeButton); add(ticketButton);
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == addButton)
        {
            ArrayList<Species> currentSpecies = Species.loadSpeciesFromFile();
            AddDialog addD = new AddDialog(mainRef, "Add New Animal", true, currentSpecies);
            addD.setVisible(true);
        } else if (e.getSource() == removeButton)
        {
            ArrayList<Species> currentSpecies = Species.loadSpeciesFromFile();
            RemoveDialog removeD = new RemoveDialog(mainRef, "Remove An Animal", true, currentSpecies);
            removeD.setVisible(true);
        } else if (e.getSource() == ticketButton)
        {
            TicketDialog ticketD = new TicketDialog(mainRef, "Calculate Ticket Price", true);
            ticketD.setVisible(true);
        }
    }
}