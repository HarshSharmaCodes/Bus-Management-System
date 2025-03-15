package bus;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Customer_login extends JFrame implements ActionListener 
{
	private static final long serialVersionUID = 1L;
	private JButton btn_ext;
	public Customer_login()
	{
		setTitle("Customer Login");
		setSize(1489,788);
		setLocationRelativeTo(null);
		setExtendedState(MAXIMIZED_BOTH);
		getContentPane().setLayout(null);
		
		JPanel backgroundPanel = new JPanel() {
			private static final long serialVersionUID = 1L;
			protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("C:\\Users\\hs089\\Downloads\\LKK.png");
                Image image = icon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null);
        setContentPane(backgroundPanel);
        
		JLabel Main_lbl = new JLabel("User Login");
		Main_lbl.setFont(new Font("Times New Roman", Font.BOLD, 45));
		Main_lbl.setBounds(669, 61, 376, 56);
		getContentPane().add(Main_lbl);
		
		JLabel lbl_usr = new JLabel("Username:");
		lbl_usr.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lbl_usr.setBounds(627, 260, 187, 50);
		getContentPane().add(lbl_usr);
		
		JLabel lbl_pass = new JLabel("Password:");
		lbl_pass.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lbl_pass.setBounds(627, 325, 187, 56);
		getContentPane().add(lbl_pass);
		
		JTextField txt_first = new JTextField();
		txt_first.setBounds(780, 270, 171, 28);
		getContentPane().add(txt_first);
		
		JPasswordField pass = new JPasswordField();
		pass.setBounds(780, 341, 171, 28);
		getContentPane().add(pass);
		pass.setEchoChar('*');	
	
		JButton btn_sign = new JButton("Login");
		btn_sign.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String url = "jdbc:mysql://localhost:3306/bus_mngt";
		        String user = "root";
		        String password = ""; 
		        String enteredUsername = txt_first.getText();
		        String enteredPassword = new String(pass.getPassword()); 
		        try {
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            Connection con = DriverManager.getConnection(url, user, password);
		            Statement st = con.createStatement();
		            ResultSet rs = st.executeQuery("SELECT * FROM cust_signup WHERE usr_name = '" + enteredUsername + "'");
		            if (rs.next()) {
		                String storedPassword = rs.getString("password");
		                if (e.getSource() == btn_sign && enteredPassword.equals(storedPassword)) {
		                    JOptionPane.showMessageDialog(Customer_login.this,
		                            "Welcome! You have successfully logged in.");
		                    Bus_booking ob = new Bus_booking ();
		                    ob.setVisible(true);
		                    dispose();
		                } 
		                else {
		                    JOptionPane.showMessageDialog(Customer_login.this,
		                            "Incorrect password. Please try again.");
		                	}
		            	}
		            else {
		                JOptionPane.showMessageDialog(Customer_login.this,
		                        "Username not found. Please register for an account.");
		            }

		            rs.close();
		            st.close();
		            con.close();
		        } catch (Exception e1) {
		            e1.printStackTrace();
		        }
		    }
		});

		btn_sign.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btn_sign.setBounds(696, 402, 137, 36);
		getContentPane().add(btn_sign);
		
		JLabel lbl_acc = new JLabel("If you don't have an Existing Account");
		lbl_acc.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lbl_acc.setBounds(627, 654, 326, 36);
		getContentPane().add(lbl_acc);
		
		btn_ext = new JButton("Create a New Account");
		btn_ext.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btn_ext.setBounds(669, 700, 229, 51);
		getContentPane().add(btn_ext);
		
		btn_ext.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{
	if(e.getSource()== btn_ext)
		{
			dispose();
			Customer_signup obj = new Customer_signup();
			obj.setVisible(true);		
		}
	}
	
public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				Customer_login frame = new Customer_login();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
}
}