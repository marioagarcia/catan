package client.catan;

import client.facade.ClientModelFacade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


@SuppressWarnings("serial")
public class SaveView extends JDialog {

    public SaveView(JFrame frame) {
        super(frame, true);

        this.setTitle("Save");
        this.setResizable(false);

        // filename panel
        JLabel filenameLabel = new JLabel("Are you sure that you want to save the current game?");
        Font defaultFont = filenameLabel.getFont();
        defaultFont = new Font(defaultFont.getName(), defaultFont.getStyle(), 14);
        filenameLabel.setFont(defaultFont);

        JPanel portPanel = new JPanel();
        portPanel.setLayout(new BoxLayout(portPanel, BoxLayout.Y_AXIS));

        portPanel.add(filenameLabel);
        portPanel.add(Box.createRigidArea(new Dimension(0, 5)));


        // Button panel
        JButton okButton = new JButton("Save");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveView.this.save();
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveView.this.setVisible(false);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        buttonPanel.add(Box.createRigidArea(new Dimension(150, 0)));
        buttonPanel.add(okButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        buttonPanel.add(exitButton);

        // Root panel
        JPanel rootPanel = new JPanel();
        rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.Y_AXIS));

        rootPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        rootPanel.add(portPanel);
        rootPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        rootPanel.add(buttonPanel);
        rootPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        this.add(rootPanel);

        this.pack();
    }

    private void save() {
        ClientModelFacade.getInstance(null).saveGame();
        this.setVisible(false);
    }
}

