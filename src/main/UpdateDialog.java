package src.main;

import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.awt.Insets;
import src.zoo.Species;

public class UpdateDialog extends JDialog implements ActionListener{
    MenuPanel menuRef;
    String[] diet = { "Carnivore", "Herbivore", "Omnivore" };
    ArrayList<Species> speciesList;
    GridBagConstraints gbc = new GridBagConstraints();
    JPanel buttonP = new JPanel(new GridLayout(1, 2));
    JPanel inputP = new JPanel(new GridBagLayout());
    MButton updateButton = new MButton("Update", "Update new Info");
    MButton closeButton = new MButton("Close", "Close this window");
    MLabel titleL = new MLabel("Update Species Info", 24f);
    MComboBox speciesBox = new MComboBox();
    MComboBox dietBox = new MComboBox(diet);
    MTextField nameF = new MTextField(15, "Species Name");
    MTextField habitatF = new MTextField(15, "Species Habitat");
    MTextArea describeA = new MTextArea(5, 10, "Description of the species (Not more than 100 word or is empty)");
    JScrollPane textScroll = new JScrollPane(describeA);

    public UpdateDialog(JFrame main, String title, boolean modal, ArrayList<Species> speciesList, MenuPanel menuRef) {
        super(main, title, true);
        setSize(700, 500);
        setLocationRelativeTo(main);
        setResizable(false);

        this.menuRef = menuRef;
        this.speciesList = speciesList;
        textScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        speciesBox.removeAllItems();
        for (Species s : speciesList)
            speciesBox.addItem(s.getTypeName());
        if (speciesList.isEmpty()) {
            speciesBox.addItem("No species found - Import one first!");
            updateButton.setEnabled(false);
        }
        titleL.setForeground(Color.BLACK);
        titleL.setHorizontalAlignment(SwingConstants.CENTER);
        titleL.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
        buttonP.setPreferredSize(new Dimension(0, 50));
        inputP.setBackground(Color.DARK_GRAY);
        gbc.gridx = 0; gbc.gridy = 0; gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 2;
        inputP.add(speciesBox, gbc);
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        inputP.add(nameF, gbc);
        gbc.gridx = 1; 
        inputP.add(habitatF, gbc);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        inputP.add(dietBox, gbc);
        gbc.gridy = 3; gbc.gridwidth = 2;
        inputP.add(textScroll, gbc);

        updateButton.addActionListener(this); closeButton.addActionListener(this); speciesBox.addActionListener(this);
        buttonP.add(updateButton); buttonP.add(closeButton);
        add(buttonP, BorderLayout.SOUTH);
        add(inputP);
        add(titleL, BorderLayout.NORTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            String selected = (String) speciesBox.getSelectedItem();
            String newName = nameF.getText();
            String newHabitat = habitatF.getText();
            String newDiet = (String) dietBox.getSelectedItem();
            String newDesc = describeA.getCleanText();

            if (nameF.getCleanText().length() == 0 || nameF.getText().length() > 20)
                JOptionPane.showMessageDialog(this, "Name cannot be empty or more than 20 words!");
            else if (habitatF.getCleanText().length() == 0 || habitatF.getText().length() > 30)
                JOptionPane.showMessageDialog(this, "Habitat cannot be empty or more than 30 words!");
            else if (describeA.getCleanText().length() == 0 || describeA.getText().length() > 50)
                JOptionPane.showMessageDialog(this, "Description cannot be empty or more than 50 words!");
            else if (checkDuplicate(newName)) 
                JOptionPane.showMessageDialog(this, "Species already exists!");
            else
                saveUpdatedInfo(selected, newName, newHabitat, newDiet, newDesc);
        } else if (e.getSource() == closeButton) { 
            dispose();
        }
        else if (e.getSource() == speciesBox) {
            setSpeciesInfo();
        }
    }

    public void setSpeciesInfo() {
        speciesBox.removeActionListener(this);
        Object selected = speciesBox.getSelectedItem();
        if (selected == null) 
            return;

        String selectedSpecies = (String) speciesBox.getSelectedItem();

        try (BufferedReader br = new BufferedReader(new FileReader("species.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] speciesData = line.split("\\|");
                if (speciesData.length >= 4 && speciesData[0].equals(selectedSpecies)) {
                    nameF.setText(speciesData[0]); 
                    habitatF.setText(speciesData[1]);
                    dietBox.setSelectedItem(speciesData[2]);
                    describeA.setText(speciesData[3]);
                }
            }
        } catch (IOException ex) {
            System.out.println("ERROR Loading species: " + ex.getMessage());
        }
        speciesBox.addActionListener(this);
    }

    public void saveUpdatedInfo(String oldName, String nName, String nHab, String nDiet, String nDesc) {
        ArrayList<String> updatedSpecies = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("species.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|", -1);
                if (data.length >= 1 && data[0].equalsIgnoreCase(oldName)) {
                    updatedSpecies.add(nName + "|" + nHab + "|" + nDiet + "|" + nDesc);
                } else {
                    updatedSpecies.add(line);
                }
            }
        } catch (IOException e) { 
            System.out.println(e.getMessage());
        }

        ArrayList<String> updatedAnimals = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("animal.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|", -1);
                if (data.length >= 1 && data[0].equalsIgnoreCase(oldName)) {
                    data[0] = nName; 
                    updatedAnimals.add(String.join("|", data));
                } else {
                    updatedAnimals.add(line);
                }
            }
        } catch (IOException e) { 
            System.out.println(e.getMessage());
        }

        writeFile("species.txt", updatedSpecies);
        writeFile("animal.txt", updatedAnimals);

        menuRef.getList().loadFromFile();
        JOptionPane.showMessageDialog(this, "Update Successful!");
        dispose();
    }

    private void writeFile(String fileName, ArrayList<String> lines) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            for (String l : lines) pw.println(l);
        } catch (IOException e) { 
            e.printStackTrace();
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