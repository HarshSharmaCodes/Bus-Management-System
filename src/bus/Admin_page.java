package bus;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Color;

public class Admin_page extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JPasswordField passwordField;

	public Admin_page() {
		setTitle("Admin Page");
        setSize(1458, 783);
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null); 
        
        JPanel backgroundPanel = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g){
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("C:\\Users\\hs089\\Downloads\\LKK.png");
                Image image = icon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        backgroundPanel.setLayout(null);
        setContentPane(backgroundPanel);
        
        JButton btn = new JButton("Login");
		btn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btn.setBounds(721, 385, 100, 50);
		backgroundPanel.add(btn);
		
		JLabel userlbl = new JLabel("USERNAME");
		userlbl.setForeground(Color.BLACK);
		userlbl.setBounds(622, 229, 117, 100);
		userlbl.setFont(new Font("Times New Roman", Font.BOLD, 18));
		backgroundPanel.add(userlbl);
		
		textField = new JTextField();
		textField.setBounds(764, 268, 176, 28);
		backgroundPanel.add(textField);
		textField.setColumns(10);
		
		JLabel passlbl = new JLabel("PASSWORD");
		passlbl.setForeground(Color.BLACK);
		passlbl.setBounds(622, 327, 117, 28);
		passlbl.setFont(new Font("Times New Roman", Font.BOLD, 18));
		backgroundPanel.add(passlbl);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(764, 330, 176, 28);
		backgroundPanel.add(passwordField);
		
		JLabel Basiclbl = new JLabel("ADMINISTRATOR LOGIN");
		Basiclbl.setForeground(Color.BLACK);
		Basiclbl.setFont(new Font("Times New Roman", Font.BOLD, 30));
		Basiclbl.setBounds(592, 83, 478, 68);
		backgroundPanel.add(Basiclbl);
		
		btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = textField.getText();
                String password = new String(passwordField.getPassword());

                if ("Harsh".equals(username) && "admin".equals(password) && e.getSource() == btn)
                {
                	JOptionPane.showMessageDialog(Admin_page.this,
                            "Welcome! You have successfully logged in.");
                	Admin_home obj = new Admin_home();
                	obj.setVisible(true);
                	dispose();
                } 
                else
                {
                	textField.setText("");
                    passwordField.setText("");
                    JOptionPane.showMessageDialog(Admin_page.this,
                            "Invalid username or password!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin_page frame = new Admin_page();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
