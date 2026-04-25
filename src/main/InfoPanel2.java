package src.main;

import javax.swing.*;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import src.zoo.Species;

public class InfoPanel2 extends JPanel
{
    //What this panel has: General Info about an animal
    GridBagConstraints gbc = new GridBagConstraints();
    MBorder infoB2 = new MBorder("General Info");
    MLabel speciesL = new MLabel("Species: ", 16f);
    MLabel describeL = new MLabel("Description: ", 16f);
    MLabel habitatL = new MLabel("Habitat: ", 16f);
    MLabel dietL = new MLabel("Diet: ", 16f);
    MTextArea describeA = new MTextArea(5, 20, "Species description");
    MTextField dietF = new MTextField(10);
    MTextField habitatF = new MTextField(10);
    MTextField speciesF  = new MTextField(10);
    JScrollPane sc = new JScrollPane(describeA);

    //Constructor
    public InfoPanel2()
    {
        //Setting up everything first
        setBorder(infoB2);
        setLayout(new GridBagLayout());
        setBackground(Color.DARK_GRAY);

        sc.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        describeA.setEnabled(false); habitatF.setEnabled(false); habitatF.setEditable(false); dietF.setEnabled(false);
        speciesF.setEnabled(false); speciesF.setEditable(false);

        //Then add them to the panel
        gbc.gridx = 0; gbc.gridy = 0; 
        gbc.insets = new Insets(5, 5, 5, 5); gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        add(speciesL, gbc); 
        gbc.gridx = 1;
        add(speciesF, gbc);
        gbc.gridx = 2;
        add(habitatL, gbc); 
        gbc.gridx = 3;
        add(habitatF, gbc);
        gbc.gridx = 4;
        add(dietL, gbc);
        gbc.gridx = 5;
        add(dietF, gbc);
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 6;
        add(describeA, gbc);
    }

    public void setDescribeText(String text)
    {
        describeA.setText(text);
    }

    public void setSpeciesInfo(Species s) {
        speciesF.setText(s.getTypeName());
        habitatF.setText(s.getHabitat());
        dietF.setText(s.getDiet());
        describeA.setText(s.getDescription());
    }
}