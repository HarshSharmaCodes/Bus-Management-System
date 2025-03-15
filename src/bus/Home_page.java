package bus;
		
import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Home_page extends JFrame implements ActionListener{

    private static final long serialVersionUID = 1L;
    private JButton btnNewButton;
    private JButton btnNewButton_1;
    
	public Home_page() {
		
        setTitle("Main Home Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1458, 783);
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null); 
        
        JPanel backgroundPanel = new JPanel() {
			private static final long serialVersionUID = 1L;
			protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("C:\\Users\\hs089\\Downloads\\12.png");
                Image image = icon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null);
        setContentPane(backgroundPanel);
        
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 1586, 38);
        backgroundPanel.add(menuBar);
        menuBar.setFont(new Font("Times New Roman", Font.BOLD, 24));
        menuBar.setBackground(UIManager.getColor("MenuBar.background")); 
        
        JMenu file = new JMenu("  About  ");
        file.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        file.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    JOptionPane.showMessageDialog(Home_page.this,
                            "About Our Project :\n" +
                            " - Buses are a common form of public transportation.\n" +
                            " - They typically have designated routes and stops.\n" +
                            " - There are two Users : Admin and User.\n" + 
                            " - Admin can manage the bookings and Buses.\n" +
                            " - Users can book their tickets and check the availability of Buses.\n" );
                }
            }
        });

        
        JMenu file2 = new JMenu("  Help  ");
        file2.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        file2.addMouseListener(new MouseAdapter()
        {
        	public void mouseClicked(MouseEvent e)
        	{
        	if(e.getButton() == MouseEvent.BUTTON1)
        	{
        		JOptionPane.showMessageDialog(Home_page.this,
                        " Contact us :\n" +
                        " - Mail us at : hs0899728@gmail.com \n" +
                        " - Mobile : +91 8076304776 \n" );
        	}
        }
	});
	
        menuBar.add(file);
        menuBar.add(file2);
        
        btnNewButton = new JButton("ADMIN\n");
        btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
        btnNewButton.setBounds(1201, 382, 265, 69);
        backgroundPanel.add(btnNewButton);
        
        btnNewButton_1 = new JButton("CUSTOMER");
        btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
        btnNewButton_1.setBounds(1201, 489, 265, 69);
        backgroundPanel.add(btnNewButton_1);
        
        JLabel lblNewLabel_1 = new JLabel("Select User :");
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 37));
        lblNewLabel_1.setBounds(1213, 311, 279, 49);
        backgroundPanel.add(lblNewLabel_1);
        setVisible(true);
        btnNewButton.addActionListener(this);
        btnNewButton_1.addActionListener(this);
    }

	public void actionPerformed(ActionEvent e) 
	{
        if (e.getSource() == btnNewButton) 
        {
            Admin_page frame = new Admin_page();
            frame.setVisible(true);
        }
        else if (e.getSource() == btnNewButton_1)
        {
        	Customer_login obj = new Customer_login();
        	obj.setVisible(true);       
        }
    }
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Home_page();
        });
    }
}