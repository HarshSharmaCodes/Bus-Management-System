package bus;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Customer_signup extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	JLabel lbl1=new JLabel();
	JLabel lbl2=new JLabel();
	JLabel lbl3=new JLabel();
	JLabel lbl4=new JLabel();
	private JTextField txt_first;
	private JTextField txt_last;
	private JTextField txt_mob;
	private JTextField txt_user;
	public JPasswordField pass;
	
	public Customer_signup() {
		setTitle("Customer Signup");
		setSize(1489,788);
		setExtendedState(MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JPanel backgroundPanel = new JPanel() {
			private static final long serialVersionUID = 1L;
			protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("C:\\Users\\hs089\\Downloads\\cust_sign.jpg");
                Image image = icon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null);
        setContentPane(backgroundPanel);
		
		JLabel Main_lbl = new JLabel("User Sign Up");
		Main_lbl.setFont(new Font("Times New Roman", Font.BOLD, 45));
		Main_lbl.setBounds(669, 61, 376, 56);
		getContentPane().add(Main_lbl);
		
		JLabel lbl_first = new JLabel("First Name");
		lbl_first.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lbl_first.setBounds(595, 164, 187, 61);
		getContentPane().add(lbl_first);
		
		JLabel lbl_last = new JLabel("Last Name");
		lbl_last.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lbl_last.setBounds(595, 235, 187, 56);
		getContentPane().add(lbl_last);
		
		JLabel lbl_mob = new JLabel("Mobile Number:");
		lbl_mob.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lbl_mob.setBounds(553, 310, 187, 56);
		getContentPane().add(lbl_mob);
		
		JLabel lbl_user = new JLabel("Username");
		lbl_user.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lbl_user.setBounds(603, 383, 137, 61);
		getContentPane().add(lbl_user);
		
		JLabel lbl_pass = new JLabel("Password\r\n:");
		lbl_pass.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lbl_pass.setBounds(603, 456, 129, 56);
		getContentPane().add(lbl_pass);
		
		txt_first = new JTextField();
		txt_first.setBounds(818, 185, 171, 28);
		getContentPane().add(txt_first);
		txt_first.setColumns(10);
		
		txt_last = new JTextField();
		txt_last.setBounds(818, 254, 171, 28);
		getContentPane().add(txt_last);
		txt_last.setColumns(10);
		
		txt_mob = new JTextField();
		txt_mob.setBounds(818, 329, 171, 28);
		getContentPane().add(txt_mob);
		txt_mob.setColumns(10);
		
		txt_user = new JTextField();
		txt_user.setBounds(818, 404, 171, 28);
		getContentPane().add(txt_user);
		txt_user.setColumns(10);
		
		pass = new JPasswordField();
		pass.setBounds(818, 475, 171, 28);
		getContentPane().add(pass);
		pass.setEchoChar('*');	
		
		JButton btn_sign = new JButton("Sign Up");
		
		btn_sign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    String url = "jdbc:mysql://localhost:3306/bus_mngt";
			    String user = "root";
			    String password = "";

			    if(txt_first.getText().isEmpty() || txt_mob.getText().isEmpty() || txt_last.getText().isEmpty() || txt_user.getText().isEmpty())
			    {
			    	JOptionPane.showMessageDialog(null, "Please Fill all the Fields First.", "Fill the Fields", JOptionPane.WARNING_MESSAGE);
			    	return;
			    }
			    try {
			        Class.forName("com.mysql.cj.jdbc.Driver");
			        Connection con = DriverManager.getConnection(url, user, password);
			        Statement st = con.createStatement();
			        String firstName = txt_first.getText();
			        String lastName = txt_last.getText();
			        long mobileNumber = Long.parseLong(txt_mob.getText());
			        String username = txt_user.getText();
			        char[] passwordChars = pass.getPassword();
			        String userPassword = new String(passwordChars);
			        
			        if(txt_mob.getText().length() > 10)
			        {
			        	JOptionPane.showMessageDialog(null, "Length Exceeded .", "Invalid Length", JOptionPane.WARNING_MESSAGE);
			        	return;
			        }
			        if (isValidPassword(userPassword)) {
			        String insertQuery = "INSERT INTO cust_signup (f_name, s_name, mob_num, usr_name, password) " +
			                             "VALUES ('" + firstName + "', '" + lastName + "', " + mobileNumber + ", '" + username + "', '" + userPassword + "')";
			        st.executeUpdate(insertQuery);
			        st.close();
			        con.close();
			        }
			        else {
                        JOptionPane.showMessageDialog(null, "Password must contain at least one lowercase letter, one uppercase letter, and one special character.", "Invalid Password", JOptionPane.WARNING_MESSAGE);
                    }
			    }
			    catch (NumberFormatException ex) {
			    	JOptionPane.showMessageDialog(null,"Invalid input. Please enter a valid number.","Invalid Input", JOptionPane.WARNING_MESSAGE);			    
			    }
			    catch (Exception ex) {
			        ex.printStackTrace();
			    }
			}
		});
		
		btn_sign.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btn_sign.setBounds(611, 563, 137, 36);
		getContentPane().add(btn_sign);
		
		JButton btn_clr = new JButton("Clear");
		btn_clr.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btn_clr.setBounds(803, 563, 147, 36);
		getContentPane().add(btn_clr);
		btn_clr.addActionListener(this);
		
		JLabel lbl_acc = new JLabel("If you already have an Account");
		lbl_acc.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		lbl_acc.setBounds(645, 624, 270, 36);
		getContentPane().add(lbl_acc);
		
		JButton btn_ext = new JButton("Login to Existing Account");
		btn_ext.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btn_ext.setBounds(655, 670, 252, 41);
		getContentPane().add(btn_ext);
		btn_ext.addActionListener(this);
		
		txt_mob.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent e)
			{
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9'))
                    && (c != '\b')) 
				{
					e.consume();
				}
			}
		});
	
		btn_clr.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event)
			{
				txt_first.setText("");
				txt_last.setText("");
				txt_mob.setText(""); 			
				txt_user.setText("");
				pass.setText("");
			}});
		
		btn_ext.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event)
					{
						dispose();
						Customer_login obj = new Customer_login();
						obj.setVisible(true);
					}});	
	}
	
	public static boolean isValidPassword(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()-+=]).{8,}$";
        return password.matches(regex);
    }
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customer_signup frame = new Customer_signup();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public void actionPerformed(ActionEvent e) {
	}

}
