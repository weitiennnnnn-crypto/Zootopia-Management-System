package src.main;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.*;
import src.zoo.*;
import java.util.ArrayList;

public class RemoveDialog extends JDialog implements ActionListener
{
    ArrayList<Species> speciesList;
    ArrayList<Animal> animalList = new ArrayList<>();
    GridBagConstraints gbc = new GridBagConstraints();
    MButton removeButton = new MButton("Release", "Remove this animal");
    MButton closeButton = new MButton("Close", "Close this window");
    MLabel titleL = new MLabel("Release to the wild?", 24f);
    MLabel warnL = new MLabel("THIS ACTION CANNOT BE UNDONE!", 24f);
    MComboBox speciesBox = new MComboBox();
    MComboBox animalBox = new MComboBox();
    JPanel buttonP = new JPanel(new GridLayout(1, 2));
    JPanel centerP = new JPanel(new GridBagLayout());
    MainFrame main;

    public RemoveDialog(MainFrame main, String title, boolean modal, ArrayList<Species> speciesList)
    {
        super(main, title, modal);
        setSize(700, 500);
        setLocationRelativeTo(main);
        setResizable(false);
        
        this.main = main;
        speciesBox.removeAllItems();
        this.speciesList = speciesList;
        for (Species s : speciesList)
            speciesBox.addItem(s.getTypeName());
        if (speciesList.isEmpty()) {
            speciesBox.addItem("No species found - Import one first!");
            removeButton.setEnabled(false);
        }
        if (animalList.isEmpty()) {
            animalBox.addItem("No animal found - Import one first!");
        }
        
        warnL.setForeground(Color.RED);
        centerP.setBackground(new Color(60, 60, 60));
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0; gbc.gridy = 0;
        centerP.add(new MLabel("Are you sure you want to", 24f), gbc);
        gbc.gridy = 1;
        centerP.add(new MLabel("release this animal?", 24f), gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 2;
        centerP.add(speciesBox, gbc);
        gbc.gridy = 3;
        centerP.add(animalBox, gbc);
        gbc.gridy = 4;
        centerP.add(warnL, gbc);
        buttonP.setPreferredSize(new Dimension(0, 50));
        buttonP.add(removeButton); buttonP.add(closeButton);
        speciesBox.addActionListener(this);
        removeButton.addActionListener(this);
        closeButton.addActionListener(this);
        titleL.setForeground(Color.BLACK);
        titleL.setHorizontalAlignment(SwingConstants.CENTER);
        titleL.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));

        add(centerP);
        add(titleL, BorderLayout.NORTH);
        add(buttonP, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == removeButton)
        {
            performRelease();
        }else if (e.getSource() == closeButton)
        {
            dispose();
        } else if (e.getSource() == speciesBox) {
            updateAnimalList();
        }
    }

    private void updateAnimalList()
    {
        speciesBox.removeActionListener(this);
        animalBox.removeAllItems();
        Object selected = speciesBox.getSelectedItem();
        if (selected == null) 
            return;

        String selectedSpecies = (String) speciesBox.getSelectedItem();

        try (BufferedReader br = new BufferedReader(new FileReader("animal.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] animalData = line.split("\\|");
                if (animalData.length >= 2 && animalData[0].equalsIgnoreCase(selectedSpecies)) {
                    animalBox.addItem(animalData[1]);
                }
            }
        } catch (IOException ex) {
            System.out.println("ERROR Loading animals: " + ex.getMessage());
        }
        speciesBox.addActionListener(this);
    }

    private void performRelease() {
        String selectedSpecies = (String) speciesBox.getSelectedItem();
        String selectedAnimal = (String) animalBox.getSelectedItem();

        if (selectedAnimal == null)
            return;

        ArrayList<String> remainingList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("animal.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] animalData = line.split("\\|");
                if (!(animalData[0].equalsIgnoreCase(selectedSpecies) && animalData[1].equalsIgnoreCase(selectedAnimal))) 
                    remainingList.add(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter("animal.txt", false))) {
            for (String line : remainingList) {
                pw.println(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        main.menuP.getList().loadFromFile();

        for (int i = 0; i < main.menuP.getList().getTree().getRowCount(); i++) 
            main.menuP.getList().getTree().expandRow(i);

        JOptionPane.showMessageDialog(this, "Animal released back to the wild!");
        dispose();
    }
}