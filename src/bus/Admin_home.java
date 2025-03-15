	package bus;
	
	import java.awt.EventQueue;
	
	import javax.swing.JFrame;
	import javax.swing.JMenuBar;
	import java.awt.Font;
	import java.awt.Graphics;
	import java.awt.Image;
	
	import javax.swing.JPanel;
	import javax.swing.ImageIcon;
	import javax.swing.JButton;
	import javax.swing.JLabel;
import javax.swing.JMenu;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
	import java.awt.Color;
	
	public class Admin_home extends JFrame {
	
		private static final long serialVersionUID = 1L;
	
		public Admin_home() {
			setTitle("Admin Home page");		
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(1491,811);
			setExtendedState(MAXIMIZED_BOTH);
			setLocationRelativeTo(null);
			getContentPane().setLayout(null);
			
			JPanel backgroundPanel = new JPanel() {
				private static final long serialVersionUID = 1L;
				protected void paintComponent(Graphics g) {
	                super.paintComponent(g);
	                ImageIcon icon = new ImageIcon("C:\\Users\\hs089\\Downloads\\Ad.jpeg");
	                Image image = icon.getImage();
	                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
	            }
	        };
	        backgroundPanel.setLayout(null);
	        setContentPane(backgroundPanel);
			
			JMenuBar menuBar = new JMenuBar();
			menuBar.setName("");
			menuBar.setFont(new Font("Times New Roman", Font.BOLD, 25));
			menuBar.setBounds(0, 0, 1732, 48);
			getContentPane().add(menuBar);
			
			JMenu file = new JMenu("  Back ");
	        file.setFont(new Font("Times New Roman", Font.PLAIN, 25));
	        file.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent e) {
	                if (e.getButton() == MouseEvent.BUTTON1)
	                {
	                	dispose();
	                	Admin_page obj = new Admin_page();
	                	obj.setVisible(true);
	                }
	            }
	        });
	        
	        menuBar.add(file);
			
			JButton btnNewButton = new JButton("Add Route");
			btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if( e.getSource() == btnNewButton)
					{
						Add_route obj = new Add_route();
						obj.setVisible(true);
						dispose();
					}
				}
			});
			btnNewButton.setBounds(648, 233, 256, 39);
			getContentPane().add(btnNewButton);
			
			JButton btnNewButton_1 = new JButton("Delete Route");
			btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if( e.getSource() == btnNewButton_1)
					{
						delete_route obj1 = new delete_route();
						obj1.setVisible(true);
						dispose();
					}
				}
			});
			btnNewButton_1.setBounds(648, 324, 256, 39);
			getContentPane().add(btnNewButton_1);
			
			JButton btnNewButton_2 = new JButton("View Bookings");
			btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 25));
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					viewBookings obj = new viewBookings();
					obj.setVisible(true);
					dispose();
				}
			});
			btnNewButton_2.setBounds(648, 411, 256, 39);
			getContentPane().add(btnNewButton_2);
			
			JLabel lblNewLabel = new JLabel("ADMINISTRATION PAGE");
			lblNewLabel.setForeground(new Color(0, 0, 0));
			lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
			lblNewLabel.setBounds(593, 119, 429, 60);
			getContentPane().add(lblNewLabel);
			
		}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin_home frame = new Admin_home();
					frame.setVisible(true);
					} catch (Exception e) {
					e.printStackTrace();
					}
				}
			});
		}
	}