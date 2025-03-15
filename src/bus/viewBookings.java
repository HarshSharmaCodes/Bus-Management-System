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
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;

public class viewBookings extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField usr_txt;
	private JTable table;
	
	public viewBookings() {
		setTitle("View Bookings");		
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
                	Admin_home obj = new Admin_home();
                	obj.setVisible(true);
                }
            }
        });
        menuBar.add(file);
        
        JLabel Ent_lbl = new JLabel("Enter User Id");
        Ent_lbl.setFont(new Font("Times New Roman", Font.BOLD, 25));
        Ent_lbl.setBounds(366, 237, 158, 54);
        backgroundPanel.add(Ent_lbl);
        
        JLabel Main_lbl = new JLabel(" View Bookings");
        Main_lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        Main_lbl.setBackground(new Color(255, 255, 255));
        Main_lbl.setOpaque(true);
        Main_lbl.setFont(new Font("Times New Roman", Font.BOLD, 30));
        Main_lbl.setBounds(674, 149, 211, 54);
        backgroundPanel.add(Main_lbl);
        
        usr_txt = new JTextField();
        usr_txt.setBounds(561, 250, 216, 38);
        backgroundPanel.add(usr_txt);
        usr_txt.setColumns(10);
        
    	JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(674, 333, 211, 48);
		backgroundPanel.add(dateChooser);
        
        JComboBox comboBox = new JComboBox();
        comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Cash", "upi", "Net Banking"}));
		comboBox.setBounds(1053, 249, 140, 38);
		backgroundPanel.add(comboBox);
		
		JLabel pay_lbl = new JLabel(" Payment Mode :");
		pay_lbl.setFont(new Font("Times New Roman", Font.BOLD, 25));
		pay_lbl.setBounds(826, 249, 192, 38);
		backgroundPanel.add(pay_lbl);
        
		JButton btn = new JButton("Submit");
		btn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	java.util.Date selectedDate = dateChooser.getDate();
		    	if(selectedDate == null)
		    	{
		    		JOptionPane.showMessageDialog(null, "Please Select the Date..!", "Error", JOptionPane.ERROR_MESSAGE);
		        	return;
		    	}
		    	String date = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);
		        String url = "jdbc:mysql://localhost:3306/bus_mngt";
		        String user = "root";
		        String pass = "";
		        String userId = usr_txt.getText();
		        String cmb = comboBox.getSelectedItem().toString();

		        String selectQuery = "SELECT * FROM bookings WHERE 1=1"; 

		        if (!userId.isEmpty()) {
		            selectQuery += " AND username = '" + userId + "'";
		        }

		        if (!cmb.isEmpty()) {
		            selectQuery += " AND Payment = '" + cmb + "'";
		        }

		        if (!date.isEmpty()) {
		            selectQuery += " AND date = '" + date + "'";
		        }
		        
		        try {
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            Connection con = DriverManager.getConnection(url, user, pass);
		            Statement st = con.createStatement();
		            ResultSet rs = st.executeQuery(selectQuery);

		            DefaultTableModel tablemodel = (DefaultTableModel) table.getModel();
		            tablemodel.setRowCount(0); 

		            int rowCount = 0;
		            while (rs.next()) {
		            	String usernm = rs.getString("username");
		                String busNumber = rs.getString("busnum");
		                String source = rs.getString("source");
		                String destination = rs.getString("destination");
		                String dte = rs.getString("date");
		                String time = rs.getString("timings");
		                String bkseat = rs.getString("Seats");
		                String pay = rs.getString("Payment");
		                tablemodel.addRow(new Object[]{usernm, busNumber, source, destination, dte, time, bkseat, pay});
		                rowCount++;
		            }

		            if (rowCount == 0) {
		                JOptionPane.showMessageDialog(null, "No Records Found", "Error", JOptionPane.ERROR_MESSAGE);
		            }
		            rs.close();
		            st.close();
		            con.close();
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});

		btn.setFont(new Font("Times New Roman", Font.BOLD, 26));
		btn.setBounds(674, 668, 211, 38);
		backgroundPanel.add(btn);
	
		JScrollPane scrollpane = new JScrollPane();
		scrollpane.setBounds(299, 465, 947, 170);
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
				"User", "Bus Number", "Source", "Destination", "Date", "Timings", "Seats","Payment Mode"
				}
		));
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
					
			}
        public static void main(String[] args) {
    		EventQueue.invokeLater(new Runnable() {
    			public void run() {
    				try {
    					viewBookings frame = new viewBookings();
    					frame.setVisible(true);
    				} catch (Exception e) {
    					e.printStackTrace();
    				}
    			}
    		});
    	}
}
