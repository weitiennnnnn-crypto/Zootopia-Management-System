package src.main;

import java.awt.Color;

import javax.swing.*;

public class MLabel extends JLabel
{
    public MLabel(String text, float size)
    {
        super(text);
        setForeground(Color.WHITE);
        setFont(ZootopiaManagementSystem.mcFont.deriveFont(size));
    }
}