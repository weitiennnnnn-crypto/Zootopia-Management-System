package src.main;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class MTextArea extends JTextArea implements FocusListener
{
    String defaultText;

    public MTextArea(int row, int column, String placeholder)
    {
        super(row, column);
        this.defaultText = placeholder;

        setFont(ZootopiaManagementSystem.mcFont.deriveFont(14f));
        setLineWrap(true);
        setWrapStyleWord(true);
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
        setCaretColor(Color.WHITE); 
        setText(defaultText);
        setForeground(Color.GRAY);
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(160, 160, 160), 2), BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        addFocusListener(this);
    } 

    @Override
    public void focusGained(FocusEvent e) {
        if (getText().equals(defaultText)) {
            setText("");
            setForeground(Color.WHITE);
        }
    }

    @Override 
    public void focusLost(FocusEvent e) {
        if (getText().isEmpty()) {
            setText(defaultText);
            setForeground(Color.GRAY);
        }
    }

    public String getCleanText() {
        String current  = getText();
        if (current .equals(defaultText)) {
            return "";
        }
        return current;
    }
}