package src.main;

import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class ShowDialog extends JDialog implements ActionListener
{
    MButton saveButton = new MButton("Save", "Save this animal info");
    MButton closeButton = new MButton("Close", "Close this window");
    JPanel buttonP = new JPanel(new GridLayout(1, 2));
    MLabel titleL = new MLabel("All Animals", 24f);
    JScrollPane sc = new JScrollPane();
    JList<String> animalList2 = new JList<>();

    public ShowDialog(JFrame main, String title, boolean modal)
    {
        super(main, title, modal);
        setSize(700, 500);
        setLocationRelativeTo(main);
        setResizable(false);

        buttonP.setPreferredSize(new Dimension(0, 50));
        buttonP.add(saveButton); buttonP.add(closeButton);
        closeButton.addActionListener(this);
        saveButton.addActionListener(this);
        titleL.setForeground(Color.BLACK);
        titleL.setHorizontalAlignment(SwingConstants.CENTER);
        titleL.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
        
        add(titleL, BorderLayout.NORTH);
        add(buttonP, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == closeButton)
        {
            dispose();
        }
        else if (e.getSource() == saveButton)
        {
            System.out.println("Hello");
        }
    }
}