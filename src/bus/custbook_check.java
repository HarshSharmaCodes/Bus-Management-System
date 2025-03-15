package bus;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;

public class custbook_check extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField usr_txt;
	private JTable table;
	
	public custbook_check() {
		setTitle("View Your Bookings");		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1491,811);
		setExtendedState(MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
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
                	Bus_booking obj = new Bus_booking();
                	obj.setVisible(true);
                }
            }
        });
        menuBar.add(file);
        
        JLabel Ent_lbl = new JLabel("Enter User Id");
        Ent_lbl.setFont(new Font("Times New Roman", Font.BOLD, 25));
        Ent_lbl.setBounds(529, 243, 158, 54);
        backgroundPanel.add(Ent_lbl);
        
        JLabel Main_lbl = new JLabel(" View Your Bookings");
        Main_lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        Main_lbl.setBackground(new Color(255, 255, 255));
        Main_lbl.setOpaque(true);
        Main_lbl.setFont(new Font("Times New Roman", Font.BOLD, 30));
        Main_lbl.setBounds(661, 149, 275, 54);
        backgroundPanel.add(Main_lbl);
        
        usr_txt = new JTextField();
        usr_txt.setBounds(765, 249, 216, 38);
        backgroundPanel.add(usr_txt);
        usr_txt.setColumns(10);
        
		JButton btn = new JButton("Submit");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String url = "jdbc:mysql://localhost:3306/bus_mngt";
		        String user = "root";
		        String pass = "";
		        try {
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            Connection con = DriverManager.getConnection(url, user, pass);
		            Statement st = con.createStatement();
		            String userId = usr_txt.getText();
		            if(userId.isEmpty())
		            {
		            	JOptionPane.showMessageDialog(null, "Fill the field first..!", "Error", JOptionPane.ERROR_MESSAGE);
		            	return;
		            }
		            String selectQuery = "SELECT * FROM bookings WHERE username = '" + userId + "'";
		            ResultSet rs = st.executeQuery(selectQuery);

		            DefaultTableModel tablemodel = (DefaultTableModel) table.getModel();
		            tablemodel.setRowCount(0); 

		            int i = 0;
		            while (rs.next()) {
				    String busNumber = rs.getString("busnum");
				    String source = rs.getString("source");
				    String destination = rs.getString("destination");
				    String date = rs.getString("date");
				    String time = rs.getString("timings");
				    String bkseat = rs.getString("Seats");
				    String pay = rs.getString("Payment");
				    tablemodel.addRow(new Object[]{busNumber, source, destination, date,time,bkseat,pay});
				    i++;
		            }
		        	
		            if (i < 1) {
		                JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
		            }

		            st.close();
		            con.close();
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		btn.setFont(new Font("Times New Roman", Font.BOLD, 26));
		btn.setBounds(674, 580, 211, 38);
		backgroundPanel.add(btn);
	
		JScrollPane scrollpane = new JScrollPane();
		scrollpane.setBounds(297, 353, 947, 170);
		getContentPane().add(scrollpane);
		
		table = new JTable();
		scrollpane.setViewportView(table);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"Bus Number", "Source", "Destination", "Date", "Timings", "Seats","Payment"
				}
		));
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		}
        public static void main(String[] args) {
    		EventQueue.invokeLater(new Runnable() {
    			public void run() {
    				try {
    					custbook_check frame = new custbook_check();
    					frame.setVisible(true);
    				} catch (Exception e) {
    					e.printStackTrace();
    				}
    			}
    		});
    	}
}
