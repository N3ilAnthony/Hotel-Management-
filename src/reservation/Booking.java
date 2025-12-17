package reservation;

import javax.swing.*;
import java.awt.*;
import hotel.Expoore;
import hotel.home;
import hotel.Profile;

public class Booking extends JFrame {

    public Booking() {
        setTitle("Reservation");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 550));
        setLocationRelativeTo(null);

        // --- Main Panel ---
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(255, 102, 0));

        // --- Top Bar ---
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(new Color(255, 102, 0));
        topBar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel title = new JLabel("Reservation");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        topBar.add(title, BorderLayout.WEST);

        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        navPanel.setBackground(new Color(255, 102, 0));

        JButton homeBtn = new JButton("Home");
        JButton exploreBtn = new JButton("Exploore");
        JButton bookingsBtn = new JButton("Bookings");
        JButton profileBtn = new JButton("Profile");

        JButton[] navButtons = {homeBtn, exploreBtn, bookingsBtn, profileBtn};
        for (JButton b : navButtons) {
            b.setBackground(Color.WHITE);
            b.setFocusPainted(false);
        }

        navPanel.add(homeBtn);
        navPanel.add(exploreBtn);
        navPanel.add(bookingsBtn);
        navPanel.add(profileBtn);
        topBar.add(navPanel, BorderLayout.EAST);

        mainPanel.add(topBar, BorderLayout.NORTH);

        // --- Form Panel ---
        JPanel formPanel = new JPanel(new GridLayout(8, 2, 15, 15));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));
        formPanel.setBackground(Color.WHITE);

        JTextField nameField = new JTextField();
        JTextField dateField = new JTextField("DD/MM/YYYY");

        JComboBox<String> roomBox = new JComboBox<>(new String[]{
                "Single Room", "Double Room", "Deluxe Room", "Suite Room"
        });

        JTextField priceField = new JTextField();
        priceField.setEditable(false); // READ ONLY

        JTextField contactField = new JTextField();
        JTextField emailField = new JTextField();

        JTextArea notesArea = new JTextArea();
        notesArea.setLineWrap(true);
        notesArea.setWrapStyleWord(true);
        JScrollPane notesScroll = new JScrollPane(notesArea);

        formPanel.add(new JLabel("Customer Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Booking Date:"));
        formPanel.add(dateField);
        formPanel.add(new JLabel("Room Type:"));
        formPanel.add(roomBox);
        formPanel.add(new JLabel("Price per Night:"));
        formPanel.add(priceField);
        formPanel.add(new JLabel("Contact Number:"));
        formPanel.add(contactField);
        formPanel.add(new JLabel("Email Address:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Additional Notes:"));
        formPanel.add(notesScroll);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // --- ROOM PRICES ---
        roomBox.addActionListener(e -> {
            switch (roomBox.getSelectedItem().toString()) {
                case "Single Room":
                    priceField.setText("₱1,500");
                    break;
                case "Double Room":
                    priceField.setText("₱2,500");
                    break;
                case "Deluxe Room":
                    priceField.setText("₱4,000");
                    break;
                case "Suite Room":
                    priceField.setText("₱6,500");
                    break;
            }
        });

        roomBox.setSelectedIndex(0); // trigger default price

        // --- Bottom Buttons ---
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(255, 102, 0));

        JButton bookBtn = new JButton("Book Now");
        JButton clearBtn = new JButton("Clear");

        bookBtn.setBackground(Color.BLACK);
        bookBtn.setForeground(Color.WHITE);
        clearBtn.setBackground(Color.DARK_GRAY);
        clearBtn.setForeground(Color.WHITE);

        buttonPanel.add(bookBtn);
        buttonPanel.add(clearBtn);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // --- Button Actions ---
        bookBtn.addActionListener(e -> {
            if (nameField.getText().isEmpty() ||
                contactField.getText().isEmpty() ||
                emailField.getText().isEmpty()) {

                JOptionPane.showMessageDialog(this,
                        "Please fill all required fields!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Booking Confirmed!\n\n"
                                + "Name: " + nameField.getText()
                                + "\nRoom: " + roomBox.getSelectedItem()
                                + "\nPrice: " + priceField.getText()
                                + "\nContact: " + contactField.getText()
                                + "\nEmail: " + emailField.getText(),
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        clearBtn.addActionListener(e -> {
            nameField.setText("");
            dateField.setText("DD/MM/YYYY");
            roomBox.setSelectedIndex(0);
            contactField.setText("");
            emailField.setText("");
            notesArea.setText("");
        });

        // --- Navigation ---
        homeBtn.addActionListener(e -> { new home().setVisible(true); dispose(); });
        exploreBtn.addActionListener(e -> { new Expoore().setVisible(true); dispose(); });
        bookingsBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Already in Bookings Page"));
        profileBtn.addActionListener(e -> { new Profile().setVisible(true); dispose(); });

        add(mainPanel);
        pack();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Booking().setVisible(true));
    }
}
