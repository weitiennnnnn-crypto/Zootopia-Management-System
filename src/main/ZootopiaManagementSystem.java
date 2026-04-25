package src.main;

import java.io.InputStream;
import java.awt.GraphicsEnvironment;
import java.awt.Font;

public class ZootopiaManagementSystem
{
    //Variable for the other class to access the font
    public static Font mcFont;

    //Setting up new font for the GUI
    static void setFonts()
    {
        try{
            InputStream is = ZootopiaManagementSystem.class.getResourceAsStream("/src/fonts/Minecraftia-Regular.ttf");
            if (is != null) {
                Font baseFont = Font.createFont(Font.TRUETYPE_FONT, is);
                GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(baseFont);
                // Default size for general use
                mcFont = baseFont.deriveFont(18f);
            }else
            {
                System.out.println("Cannot find the fonts");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //Main function here
    public static void main(String[] args)
    {
        //Set the font first so that the global environment knows the font
        setFonts();

        //Creating a new object of MainFrame that contains all the panels
        MainFrame main = new MainFrame();

        //Make the MainFrame object visible
        main.setVisible(true);
    }
}