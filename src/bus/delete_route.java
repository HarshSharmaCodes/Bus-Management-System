package bus;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class delete_route extends JFrame {

	private static final long serialVersionUID = 1L;
	private JComboBox comboBox;
	
	public delete_route() {
		setTitle("Delete Bus Info");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1489,788);
		setLocationRelativeTo(null);
		setExtendedState(MAXIMIZED_BOTH);
		getContentPane().setLayout(null);
		
		JPanel backgroundPanel = new JPanel() {
			private static final long serialVersionUID = 1L;
			protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("C:\\Users\\hs089\\Downloads\\bg.jpg");
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
                	Admin_home obj = new Admin_home();
                	obj.setVisible(true);
                }
            }
        });
        
        menuBar.add(file);

		comboBox = new JComboBox();
		comboBox.setBounds(785, 267, 200, 40);
		backgroundPanel.add(comboBox);
        
		JLabel lblNewLabel_2 = new JLabel("Delete Bus Details ");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 45));
		lblNewLabel_2.setBounds(617, 116, 404, 66);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lbl_bus = new JLabel("Bus Number");
		lbl_bus.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lbl_bus.setBounds(575, 267, 200, 40);
		getContentPane().add(lbl_bus);
		
		JButton bb1 = new JButton("SUBMIT");
		bb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String url = "jdbc:mysql://localhost:3306/bus_mngt";
		        String user = "root";
		        String pass = "";
		        try {
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            Connection con = DriverManager.getConnection(url, user, pass);
		            Statement st = con.createStatement();
				
		            String s1 = (String) comboBox.getSelectedItem();
		            String sql = "DELETE FROM bus WHERE b_num = '" + s1 + "'";
               st.executeUpdate(sql);
               st.close();
               con.close();
		        	} 
		        catch (Exception exc)
		        {
		        	exc.printStackTrace();
		        }
				
			}
		});
		bb1.setBounds(677, 373, 200, 30);
		getContentPane().add(bb1);
		get_info();
	}

	private void  get_info()
	{
		String url = "jdbc:mysql://localhost:3306/bus_mngt";
        String user = "root";
        String pass = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);
            Statement st = con.createStatement();

            String sql = "SELECT b_num FROM bus";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String busNumber = rs.getString("b_num");
                comboBox.addItem(busNumber);
            }	
            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading bus numbers: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					delete_route frame = new delete_route();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
