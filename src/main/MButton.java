package src.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.border.*;

public class MButton extends JButton implements MouseListener
{
    //What this button characteristics are: Minecraft style
    private final Color NORMAL_BG = new Color(100, 100, 100);
    private final Color LIGHT_EDGE = new Color(220, 220, 220);
    private final Border padding = BorderFactory.createEmptyBorder(10, 40, 10, 40);
    private final Border bevel = BorderFactory.createRaisedBevelBorder();
    private final Border outline = BorderFactory.createLineBorder(Color.BLACK, 2);

    //Constructor
    public MButton(String text, String text2)
    {
        //Designing the button
        super(text);
        setPreferredSize(new Dimension(100, 40));
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(outline, bevel), padding));
        setFont(ZootopiaManagementSystem.mcFont.deriveFont(16f));
        setForeground(Color.WHITE);
        setBackground(NORMAL_BG);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);
        setToolTipText(text2);
        addMouseListener(this);
    }

    public MButton(String text, String text2, Color color)
    {
        //Designing the button
        super(text);
        setPreferredSize(new Dimension(100, 20));
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(outline, bevel), padding));
        setFont(ZootopiaManagementSystem.mcFont.deriveFont(16f));
        setForeground(Color.WHITE);
        setBackground(color);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);
        setToolTipText(text2);
        addMouseListener(this);
    }

    public MButton(String text, String text2, int width, int height)
    {
        //Designing the button
        super(text);
        setPreferredSize(new Dimension(width, height));
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(outline, bevel), padding));
        setFont(ZootopiaManagementSystem.mcFont.deriveFont(16f));
        setForeground(Color.WHITE);
        setBackground(NORMAL_BG);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);
        setToolTipText(text2);
        addMouseListener(this);
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
        setBorder(new LineBorder(LIGHT_EDGE, 3));
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(outline, bevel), padding));
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override 
    public void mouseClicked(MouseEvent e) {}

    @Override 
    public void mouseReleased(MouseEvent e) {}

    @Override
    public JToolTip createToolTip()
    {
        return new MToolTip();
    }
}

class MToolTip extends JToolTip
{
    public MToolTip()
    {
        setBackground(new Color(16, 0, 16, 230));
        setForeground(Color.WHITE);
        setFont(ZootopiaManagementSystem.mcFont.deriveFont(14f));
        setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(40, 0, 120), 2),
            new LineBorder(new Color(80, 0, 200), 1)
        ));
    }
}

