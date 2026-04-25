package src.main;

import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import src.zoo.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

public class NestedList extends JScrollPane implements TreeSelectionListener
{
    MainFrame mainRef;
    DefaultMutableTreeNode root = new DefaultMutableTreeNode("Species");
    DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
    DefaultTreeModel treeModel = new DefaultTreeModel(root);
    ImageIcon rootIcon = new ImageIcon(new ImageIcon("src/icons/Diamond.png").getImage().getScaledInstance(24, 24, 24));
    ImageIcon speciesIcon = new ImageIcon(new ImageIcon("src/icons/Enchanted_Golden_Apple.png").getImage().getScaledInstance(24, 24, 24));
    ImageIcon smallSpeciesIcon = new ImageIcon(new ImageIcon("src/icons/Apple.png").getImage().getScaledInstance(24, 24, 24));
    JTree tree = new JTree(treeModel);

    public NestedList(MainFrame mainRef)
    {
        this.mainRef = mainRef;
        tree.putClientProperty("JTree.lineStyle", "Angled");
        tree.setShowsRootHandles(true);
        tree.setRowHeight(30);
        renderer.setIconTextGap(10);
        renderer.setTextSelectionColor(Color.WHITE);
        renderer.setTextNonSelectionColor(Color.WHITE);
        renderer.setBackgroundSelectionColor(Color.BLACK);
        renderer.setBackgroundNonSelectionColor(new Color(60, 60, 60));
        renderer.setBorderSelectionColor(null);
        renderer.setClosedIcon(rootIcon);
        renderer.setLeafIcon(smallSpeciesIcon);
        renderer.setOpenIcon(speciesIcon);
        tree.setCellRenderer(renderer);
        setPreferredSize(new Dimension(200, 0));
        setViewportView(tree);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(30, 30, 30), 5), 
                BorderFactory.createLineBorder(new Color(150, 150, 150), 2)));
        getVerticalScrollBar().setBackground(new Color(40, 40, 40));
        getHorizontalScrollBar().setBackground(new Color(40, 40, 40));

        tree.setBackground(new Color(60, 60, 60));
        tree.setForeground(Color.DARK_GRAY);
        tree.setFont(ZootopiaManagementSystem.mcFont.deriveFont(16f));
        tree.addTreeSelectionListener(this);
    }

    public JTree getTree() { return tree; }

    @Override
    public void valueChanged(TreeSelectionEvent e)
    {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

        if(selectedNode == null) return;

        Object userObject = selectedNode.getUserObject();

        if (userObject instanceof Animal) {
            Animal a = (Animal) userObject;
            DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) selectedNode.getParent();
            if (parentNode != null && parentNode.getUserObject() instanceof Species) {
                Species s = (Species) parentNode.getUserObject();
                mainRef.displayAnimalInfo(s, a);
            }
        } else if (userObject instanceof Species) {
            Species s = (Species) userObject;
            mainRef.displaySpeciesInfo(s);
        }
    }

    public void importSpecies(Species species)
    {
        DefaultMutableTreeNode newSpecies = new DefaultMutableTreeNode(species);
        treeModel.insertNodeInto(newSpecies, root, root.getChildCount());
    }

    public void addAnimal(Species species, Animal animal)
    {
        for (int i = 0; i < root.getChildCount(); i++) //Find the species node
        {
            DefaultMutableTreeNode speciesNode = (DefaultMutableTreeNode) root.getChildAt(i);
            Species tempSpecies = (Species) speciesNode.getUserObject();

            if (tempSpecies.getTypeName().equalsIgnoreCase(species.getTypeName())) //Check whether the node hold the species we want
            {
                DefaultMutableTreeNode animalNode = new DefaultMutableTreeNode(animal);
                treeModel.insertNodeInto(animalNode, speciesNode, speciesNode.getChildCount()); //Insert the animal into the species node
                tree.expandPath(new TreePath(speciesNode.getPath())); //Expand the tree path so that the user can see
                return; //exit the function
            }
        }
    }

    public void loadFromFile()
    {
        tree.clearSelection();
        root.removeAllChildren();

        try (BufferedReader br = new BufferedReader(new FileReader("species.txt"))){
            String line;
            while ((line = br.readLine()) != null) {
                String[] speciesData = line.split("\\|");
                if (speciesData.length >= 4) {
                    Species s = new Species(speciesData[0], speciesData[3], speciesData[1], speciesData[2]);
                    importSpecies(s);
                }
            }
        } catch (IOException ex) {
            System.out.println("Species file not found.");
        }

        try (BufferedReader br = new BufferedReader(new FileReader("animal.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] animalData = line.split("\\|");
                if (animalData.length >= 4) {
                    Species s = new Species(animalData[0]);
                    String lastFed = (animalData.length >= 5) ? animalData[4] : "Never";
                    Animal a = new Animal(animalData[1], Integer.parseInt(animalData[3]), animalData[2], lastFed);
                    addAnimal(s, a);
                }
            }
        } catch (IOException ex) {
            System.out.println("Animal file not found.");
        }

        treeModel.reload();

        for (int i = 0; i < tree.getRowCount(); i++) 
            tree.expandRow(i);
    }
}