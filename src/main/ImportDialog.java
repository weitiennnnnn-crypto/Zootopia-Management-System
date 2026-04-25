package src.main;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class ImportDialog extends JDialog implements ActionListener
{
    String[] diet = { "Carnivore", "Herbivore", "Omnivore" };
    JPanel inputP = new JPanel();
    MainFrame mainRef;
    GridBagConstraints gbc = new GridBagConstraints();
    MTextField typeF = new MTextField(20, "Species Name");
    MTextField habitatF = new MTextField(20, "Species Habitat");
    MTextArea describeArea = new MTextArea(10, 20, "Description of the species (Not more than 100 word or is empty)");
    MComboBox dietBox = new MComboBox(diet);
    MButton importButton = new MButton("Import", "Import new species");
    MButton closeButton = new MButton("Close", "Close this window");
    JPanel buttonP = new JPanel(new GridLayout(1, 2));
    MLabel titleL = new MLabel("Import to the zoo?", 24f);

    public ImportDialog(MainFrame main, String title, boolean modal)
    {
        super(main, title, modal);
        mainRef = main;
        inputP.setLayout(new GridBagLayout());
        inputP.setBackground(new Color(60, 60, 60));
        setSize(700, 500);
        setLocationRelativeTo(main);
        setResizable(false);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        inputP.add(typeF, gbc);
        gbc.gridx = 1;
        inputP.add(habitatF, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 2;
        inputP.add(dietBox, gbc);
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        inputP.add(describeArea, gbc);

        buttonP.setPreferredSize(new Dimension(0, 50));
        buttonP.add(importButton); buttonP.add(closeButton);
        closeButton.addActionListener(this);
        importButton.addActionListener(this);
        titleL.setForeground(Color.BLACK);
        titleL.setHorizontalAlignment(SwingConstants.CENTER);
        titleL.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
        
        add(inputP);
        add(titleL, BorderLayout.NORTH);
        add(buttonP, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == closeButton)
        {
            dispose();
        }
        else if (e.getSource() == importButton)
        {
            String typeName = typeF.getCleanText().trim(); String habitat = habitatF.getCleanText().trim(); String diet = dietBox.getSelectedItem().toString(); String description = describeArea.getCleanText().trim();

            if (describeArea.getText().length() > 100 || description.isEmpty())
                JOptionPane.showMessageDialog(null, "Description cannot be empty or more than 50 words!");
            else if (typeName.isEmpty() || typeName.length() >= 20)
                JOptionPane.showMessageDialog(null, "Species name cannot be empty or more than 20 words!");
            else if (habitat.isEmpty() || habitat.length() >= 30)
                JOptionPane.showMessageDialog(null, "Species habitat cannot be empty or more than 30 words!");
            else if (habitat.isEmpty() && typeName.isEmpty())
                JOptionPane.showMessageDialog(null, "Name and habitat cannot be empty!");
            else if (checkDuplicate(typeName))
                JOptionPane.showMessageDialog(null, "Name already exist!");
            else
            {
                try (FileWriter fw = new FileWriter("species.txt", true); BufferedWriter bw = new BufferedWriter(fw); PrintWriter pw = new PrintWriter(bw)) {
                    pw.println(typeName + "|" + habitat + "|" + diet + "|" + description);
                    pw.flush(); //Flush the memory

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error saving to file: " + ex.getMessage());
                }
                mainRef.menuP.getList().loadFromFile();
                mainRef.menuP.getList().revalidate();
                mainRef.menuP.getList().repaint();
                JOptionPane.showMessageDialog(this, "Saved successfully!");
                dispose();
            }
        }
    }

    boolean checkDuplicate(String typeName) {
        boolean duplicate = false;

        try (BufferedReader br = new BufferedReader(new FileReader("species.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] speciesData = line.split("\\|");
                if (speciesData[0].equalsIgnoreCase(typeName))
                    duplicate = true;
                else 
                    duplicate = false;
            } 

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        return duplicate;
    }
}