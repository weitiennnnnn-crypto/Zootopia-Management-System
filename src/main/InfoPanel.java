package src.main;

import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.zoo.Animal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InfoPanel extends JPanel implements ActionListener
{
    //What this panel has: Basic animal info
    MenuPanel menuRef;
    int currentAnimalID; String currentAnimalName;
    GridBagConstraints gbc = new GridBagConstraints();
    MButton feedButton = new MButton("Feed", "Reset the feeding timer", 150, 40);
    MBorder infoB = new MBorder("Animal Info");
    MTextField timeField = new MTextField(15, "Feeding time");
    MTextField nameF = new MTextField(15);
    MTextField idF = new MTextField(15);
    MTextField growthF = new MTextField(15);

    //Constructor
    public InfoPanel(MenuPanel menuRef)
    {
        //Setting up everything first
        this.menuRef = menuRef;
        feedButton.addActionListener(this);
        nameF.setEnabled(false); idF.setEnabled(false); growthF.setEnabled(false); timeField.setEnabled(false);
        infoB.setTitleFont(ZootopiaManagementSystem.mcFont.deriveFont(24f));
        setLayout(new GridBagLayout());
        setBackground(Color.DARK_GRAY);
        setBorder(infoB);

        gbc.gridx = 0; gbc.gridy = 0; gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        add(new MLabel("Name: ", 16f), gbc);
        gbc.gridx = 1;
        add(nameF, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        add(new MLabel("ID: ", 16f), gbc);
        gbc.gridx = 1;
        add(idF, gbc);
        gbc.gridx = 0; gbc.gridy = 2; 
        add(new MLabel("Growth: ", 16f), gbc);
        gbc.gridx = 1;
        add(growthF, gbc);
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        add(timeField, gbc);
        gbc.gridx = 2; gbc.gridwidth = 1;
        add(feedButton, gbc);
    }

    public void setAnimalInfo(Animal a) {
        nameF.setText(a.getName());
        idF.setText(a.toStringID());
        growthF.setText(a.getGrowth());
        timeField.setText(a.getLastFed());
    } 

    public void setEmptyAnimal() {
        nameF.setText("Choose an animal");
        idF.setText("Choose an animal");
        growthF.setText("Choose an animal");
        timeField.setText("Choose an animal");
    }

    @Override 
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == feedButton) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String timeStamp = now.format(formatter);

            currentAnimalID = Integer.parseInt(idF.getText());
            currentAnimalName = nameF.getText();
            timeField.setText("Last Fed: " + timeStamp);
            updateAnimalFeedingTime(currentAnimalID, timeStamp);
            JOptionPane.showMessageDialog(this, currentAnimalName + " has been fed!");
            menuRef.getList().loadFromFile();
        }
    }

    public void updateAnimalFeedingTime(int animalID, String timeStamp) {
        File file = new File("animal.txt");
        ArrayList<String> lines = new ArrayList<>();
        String targetID = String.valueOf(animalID);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                if (parts.length >= 4 && parts[3].equals(targetID)) {
                    line = parts[0] + "|" + parts[1] + "|" + parts[2] + "|" + parts[3] + "|" + timeStamp;
                }
                lines.add(line);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage());
            return;
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(file, false))) {
            for (String updatedLine : lines) {
                pw.println(updatedLine);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving feeding time: " + e.getMessage());
        }
    }
}