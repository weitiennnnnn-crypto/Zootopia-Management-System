package src.main;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.Color;

public class MBorder extends TitledBorder
{
    Border line = BorderFactory.createLineBorder(new Color(150, 150, 150), 2);
    public MBorder(String text)
    {
        super(text);
        setBorder(border);
        setTitleJustification(TitledBorder.CENTER);
        setTitleFont(ZootopiaManagementSystem.mcFont.deriveFont(24f));
        setTitleColor(new Color(255, 255, 85));
    }
}