package src.main;

import java.awt.Color;
import java.awt.Component;
import javax.swing.*;

public class MComboBox extends JComboBox<String>
{
    public MComboBox()
    {
        setBackground(new Color(60, 60, 60));
        setForeground(Color.WHITE);
        setFont(ZootopiaManagementSystem.mcFont.deriveFont(16f));

        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(30, 30, 30), 2), BorderFactory.createLineBorder(new Color(150, 150, 150))));
        setRenderer(new DefaultListCellRenderer()
        {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, 
                    int index, boolean isSelected, boolean cellHasFocus) {
                
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                
                label.setOpaque(true);
                label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                
                if (isSelected) {
                    label.setBackground(new Color(120, 120, 120)); // Highlight gray
                    label.setForeground(Color.YELLOW);           // Selection text
                } else {
                    label.setBackground(new Color(60, 60, 60));
                    label.setForeground(Color.WHITE);
                }
                
                return label;
            }
        });
    }

    public MComboBox(String[] items)
    {
        super(items);
        setBackground(new Color(60, 60, 60));
        setForeground(Color.WHITE);
        setFont(ZootopiaManagementSystem.mcFont.deriveFont(16f));

        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(30, 30, 30), 2), BorderFactory.createLineBorder(new Color(150, 150, 150))));
        setRenderer(new DefaultListCellRenderer()
        {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, 
                    int index, boolean isSelected, boolean cellHasFocus) {
                
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                
                label.setOpaque(true);
                label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                
                if (isSelected) {
                    label.setBackground(new Color(120, 120, 120)); // Highlight gray
                    label.setForeground(Color.YELLOW);           // Selection text
                } else {
                    label.setBackground(new Color(60, 60, 60));
                    label.setForeground(Color.WHITE);
                }
                
                return label;
            }
        });
    }
}
