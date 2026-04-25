    package src.main;

    import java.awt.BorderLayout;
    import java.awt.Color;
    import java.awt.Dimension;
    import java.awt.GridBagConstraints;
    import java.awt.GridBagLayout;
    import java.awt.GridLayout;
    import java.awt.Insets;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import javax.swing.*;

    public class TicketDialog extends JDialog implements ActionListener
    {
        int qC = 0, qA = 0, qS = 0, totalPrice = 0, totalPeople = 0, childP = 0, adultP = 0, seniorP = 0;
        double finalPrice = 0;
        GridBagConstraints gbc = new GridBagConstraints();
        String[] categoryList = { "Malaysian", "Non-Malaysian" };
        MButton closeButton = new MButton("Close", "Close this window");
        MButton clearButton = new MButton("Clear", "Clear all");
        MButton addButtonC = new MButton("+", "Add 1");
        MButton addButtonA = new MButton("+", "Add 1");
        MButton addButtonS = new MButton("+", "Add 1");
        MButton deductButtonS = new MButton("-", "Minus 1");
        MButton deductButtonC = new MButton("-", "Minus 1");
        MButton deductButtonA = new MButton("-", "Minus 1");
        MTextField totalF = new MTextField(10);
        MLabel priceC = new MLabel("RM 10", 16f);
        MLabel priceA = new MLabel("RM 20", 16f);
        MLabel priceS = new MLabel("RM 12", 16f);
        MLabel titleL = new MLabel("ZooToPia Ticket Calculator", 24f);
        MLabel quantityA = new MLabel("0", 16f);
        MLabel quantityS = new MLabel("0", 16f);
        MLabel quantityC = new MLabel("0", 16f);
        JPanel buttonP = new JPanel(new GridLayout(1, 2));
        JPanel inputP = new JPanel(new GridBagLayout());
        MComboBox categoryBox = new MComboBox(categoryList);

        public TicketDialog(JFrame main, String title, boolean modal)
        {
            super(main, title, modal);
            setSize(700, 500);
            setLocationRelativeTo(main);
            setResizable(false);

            gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 5; gbc.insets = new Insets(10, 10, 10, 10);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.EAST;
            inputP.add(categoryBox, gbc);
            gbc.gridy = 1; gbc.gridwidth = 1;
            inputP.add(new MLabel("Children", 16f), gbc);
            gbc.gridx = 1;
            inputP.add(priceC, gbc);
            gbc.gridx = 2;
            inputP.add(deductButtonC, gbc);
            gbc.gridx = 3;
            inputP.add(quantityC, gbc);
            gbc.gridx = 4;
            inputP.add(addButtonC, gbc);
            gbc.gridx = 0; gbc.gridy = 2;
            inputP.add(new MLabel("Adult", 16f), gbc);
            gbc.gridx = 1;
            inputP.add(priceA, gbc);
            gbc.gridx = 2;
            inputP.add(deductButtonA, gbc);
            gbc.gridx = 3;
            inputP.add(quantityA, gbc);
            gbc.gridx = 4;
            inputP.add(addButtonA, gbc);
            gbc.gridx = 0; gbc.gridy = 3;
            inputP.add(new MLabel("Senior adult", 16f), gbc);
            gbc.gridx = 1;
            inputP.add(priceS, gbc);
            gbc.gridx = 2;
            inputP.add(deductButtonS, gbc);
            gbc.gridx = 3;
            inputP.add(quantityS, gbc);
            gbc.gridx = 4;
            inputP.add(addButtonS, gbc);
            gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 3;
            inputP.add(new MLabel("Total: ", 16f), gbc);
            gbc.gridx = 2; totalF.setEnabled(false);
            inputP.add(totalF, gbc);
            inputP.setBackground(Color.DARK_GRAY);
            buttonP.setPreferredSize(new Dimension(0, 50));
            buttonP.add(clearButton); buttonP.add(closeButton);
            clearButton.addActionListener(this);
            closeButton.addActionListener(this);
            titleL.setForeground(Color.BLACK); 
            titleL.setHorizontalAlignment(SwingConstants.CENTER);
            titleL.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));

            addButtonC.addActionListener(this); deductButtonC.addActionListener(this);
            addButtonA.addActionListener(this); deductButtonA.addActionListener(this);
            addButtonS.addActionListener(this); deductButtonS.addActionListener(this);
            categoryBox.addActionListener(this);
            add(inputP);
            add(titleL, BorderLayout.NORTH);
            add(buttonP, BorderLayout.SOUTH);
        }

        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == clearButton)
            {
                qC = 0; qA = 0; qS = 0; 
                quantityA.setText("0"); quantityC.setText("0"); quantityS.setText("0");
                totalF.setText("RM 0");
            } else if (e.getSource() == closeButton) {
                dispose();
            } else if (e.getSource() == addButtonC) {
                if (qC < 20)
                qC = qC + 1;
                quantityC.setText(Integer.toString(qC));
            } else if (e.getSource() == deductButtonC) {
                if (qC != 0)
                qC--;
                quantityC.setText(Integer.toString(qC));
            } else if (e.getSource() == addButtonA) {
                if (qA < 20)
                    qA++;
                quantityA.setText(Integer.toString(qA));
            } else if (e.getSource() == deductButtonA) {
                if (qA != 0) 
                    qA--;
                quantityA.setText(Integer.toString(qA));
            } else if (e.getSource() == addButtonS) {
                if (qS < 20)
                    qS++;
                quantityS.setText(Integer.toString(qS));
            } else if (e.getSource() == deductButtonS) {
                if (qS != 0) 
                    qS--;
                quantityS.setText(Integer.toString(qS));
            }

            if (categoryBox.getSelectedItem() == "Malaysian") {
                priceC.setText("RM 10");
                priceS.setText("RM 12");
                priceA.setText("RM 20");
                childP = qC * 10;
                adultP = qA * 20;
                seniorP = qS * 12;
            } else if (categoryBox.getSelectedItem() == "Non-Malaysian") {
                priceC.setText("RM 40");
                priceS.setText("RM 42");
                priceA.setText("RM 50");
                childP = qC * 40;
                adultP = qA * 50;
                seniorP = qS * 42;
            }

            finalPrice = childP + adultP + seniorP;

            totalF.setText("RM " + String.format("%.2f", finalPrice));
        }
}