package src.main;

import java.awt.Color;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import javax.swing.*;
import javax.swing.border.*;

public class MTextField extends JTextField implements FocusListener
{
    String defaultText;

    public MTextField(int width)
    {
        super(width);
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
        setCaretColor(Color.WHITE);
        setFont(ZootopiaManagementSystem.mcFont.deriveFont(12f));
        setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(160, 160, 160), 2),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        addFocusListener(this);
    }

    public MTextField(int width, String placeholder)
    {
        super(width);
        this.defaultText = placeholder;
        setText(defaultText);
        setBackground(Color.BLACK);
        setForeground(Color.GRAY);
        setCaretColor(Color.WHITE);
        setFont(ZootopiaManagementSystem.mcFont.deriveFont(12f));
        setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(160, 160, 160), 2),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        addFocusListener(this);
    }

    @Override
    public void focusGained(FocusEvent e)
    {
        if (getText().equals(defaultText))
        {
            setForeground(Color.WHITE);
            setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e)
    {
        if (getText().isEmpty())
        {
            setForeground(Color.GRAY);
            setText(defaultText);
        }
    }

    public String getCleanText() {
        String current  = getText();
        if (current.equals(defaultText)) {
            return "";
        }
        return current;
    }
}