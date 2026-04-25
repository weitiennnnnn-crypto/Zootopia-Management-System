package src.main;

import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.*;
import src.zoo.Species;

public class AddDialog extends JDialog implements ActionListener {
    ArrayList<Species> speciesList;
    String[] growth = { "Embryo", "Juvenile", "Mature" };
    MComboBox speciesBox = new MComboBox();
    MComboBox growBox = new MComboBox(growth);
    MTextField nameF = new MTextField(20, "Animal Name");
    MTextField idF = new MTextField(20, "Animal ID");
    MButton addButton = new MButton("Save", "Add this animal");
    MButton closeButton = new MButton("Close", "Close this window");
    JPanel buttonP = new JPanel(new GridLayout(1, 2));
    JPanel inputP = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    MLabel titleL = new MLabel("Add new member to the zoo?", 24f);
    MainFrame mainRef;

    public AddDialog(MainFrame main, String title, boolean modal, ArrayList<Species> speciesList) {
        super(main, title, modal);
        setSize(700, 500);
        setLocationRelativeTo(main);
        setResizable(false);

        mainRef = main;
        speciesBox.removeAllItems();
        this.speciesList = speciesList;
        for (Species s : speciesList)
            speciesBox.addItem(s.getTypeName());
        if (speciesList.isEmpty()) {
            speciesBox.addItem("No species found - Import one first!");
            addButton.setEnabled(false);
        }
        buttonP.setPreferredSize(new Dimension(0, 50));
        buttonP.add(addButton); buttonP.add(closeButton);
        inputP.setBackground(new Color(60, 60, 60));

        gbc.gridx = 0; gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL; gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 5, 5);
        inputP.add(nameF, gbc);
        gbc.gridx = 1;
        inputP.add(idF, gbc);
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        inputP.add(growBox, gbc);
        gbc.gridy = 2;
        inputP.add(speciesBox, gbc);
        closeButton.addActionListener(this);
        addButton.addActionListener(this);
        titleL.setForeground(Color.BLACK);
        titleL.setHorizontalAlignment(SwingConstants.CENTER);
        titleL.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));

        add(inputP);
        add(titleL, BorderLayout.NORTH);
        add(buttonP, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == closeButton) {
            dispose();
        } else if (e.getSource() == addButton) {
            String name = nameF.getCleanText().trim(); String IDRaw = idF.getCleanText().trim(); String growth = growBox.getSelectedItem().toString(); String species = speciesBox.getSelectedItem().toString();

            if (name.isEmpty() || IDRaw.isEmpty())
                JOptionPane.showMessageDialog(null, "Name and ID cannot be empty!");
            else if (species.isEmpty())
                JOptionPane.showMessageDialog(null, "Animal must belong to a species!");
            else
            {
                try{
                    int ID = Integer.parseInt(IDRaw);
                
                    try (FileWriter fw = new FileWriter("animal.txt", true); BufferedWriter bw = new BufferedWriter(fw); PrintWriter pw = new PrintWriter(bw)) {
                        pw.println(species + "|" + name + "|" + growth + "|" + ID);
                        pw.flush();

                    }

                    SwingUtilities.invokeLater(() -> {
                        // Clear selection to avoid the NPE in the EventQueue
                        
                        // Reload from file
                        mainRef.menuP.getList().loadFromFile();
                        
                        // Notify UI
                        mainRef.menuP.getList().revalidate();
                        mainRef.menuP.getList().repaint();

                        JOptionPane.showMessageDialog(this, "Saved successfully!");
                        dispose();
                    });
                    
                } catch (NumberFormatException nfe) { 
                    JOptionPane.showMessageDialog(this, "ID must be a valid number!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error saving to file: " + ex.getMessage());
                }
            }
        }
    }
}
