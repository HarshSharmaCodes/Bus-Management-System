	package bus;
	
	import java.awt.EventQueue;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
	import javax.swing.JPanel;
	import javax.swing.JScrollPane;
	
	import java.awt.Font;
	import java.awt.Graphics;
	import java.awt.Image;
	
	import javax.swing.JComboBox;
	import com.toedter.calendar.JDateChooser;
	
	import javax.swing.ImageIcon;
	import javax.swing.JButton;
	import javax.swing.JTable;
	import javax.swing.ListSelectionModel;
	import javax.swing.table.DefaultTableModel;
	import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.Statement;
	import java.text.SimpleDateFormat;
	import java.awt.event.ActionEvent;
	import javax.swing.DefaultComboBoxModel;
	
	public class Bus_booking extends JFrame {
	
		private static final long serialVersionUID = 1L;
		private JTable table;
	
		public Bus_booking() {
			setTitle("Booking of Bus");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(1489,788);
			setLocationRelativeTo(null);
			setExtendedState(MAXIMIZED_BOTH);
			getContentPane().setLayout(null);
			
			JPanel backgroundPanel = new JPanel() {
				private static final long serialVersionUID = 1L;
				protected void paintComponent(Graphics g) {
	                super.paintComponent(g);
	                ImageIcon icon = new ImageIcon("C:\\Users\\hs089\\Downloads\\bb.jpg");
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
	                	Customer_login obj = new Customer_login();
	                	obj.setVisible(true);
	                }
	            }
	        });
	        
	        JMenu file1 = new JMenu("  View Your Bookings ");
	        file1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
	        file1.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent e) {
	                if (e.getButton() == MouseEvent.BUTTON1)
	                {
	                	dispose();
	                	custbook_check obj = new custbook_check();
	                	obj.setVisible(true);
	                }
	            }
	        });
	        
	        
	        menuBar.add(file);
	        menuBar.add(file1);
			
			JLabel lblNewLabel = new JLabel("Book Your Bus");
			lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 45));
			lblNewLabel.setBounds(630, 71, 335, 44);
			getContentPane().add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("Source");
			lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
			lblNewLabel_1.setBounds(436, 159, 147, 32);
			getContentPane().add(lblNewLabel_1);
			
			JComboBox comboBox = new JComboBox();
			comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"Tis Hazari", "Kamla Nagar", "Shahdara", "Rohini", "Chandni Chowk", "Pitampura"}));
			comboBox.setBounds(538, 159, 122, 31);
			getContentPane().add(comboBox);
			
			JLabel lblNewLabel_2 = new JLabel("Destination");
			lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 25));
			lblNewLabel_2.setBounds(719, 162, 142, 27);
			getContentPane().add(lblNewLabel_2);
			
			JComboBox comboBox_1 = new JComboBox();
			comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Gurugram", "Rohini East", "Kohat Enclave", "Punjabi Bagh", "Welcome"}));
			comboBox_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			comboBox_1.setBounds(871, 159, 122, 31);
			getContentPane().add(comboBox_1);
			
			JLabel lblNewLabel_3 = new JLabel("Date");
			lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 25));
			lblNewLabel_3.setBounds(436, 224, 94, 32);
			getContentPane().add(lblNewLabel_3);
			
			JLabel lblNewLabel_4 = new JLabel("Payment");
			lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 25));
			lblNewLabel_4.setBounds(630, 556, 99, 27);
			backgroundPanel.add(lblNewLabel_4);
			
			JDateChooser dateChooser = new JDateChooser();
			dateChooser.setBounds(538, 224, 122, 30);
			getContentPane().add(dateChooser);
			
			JButton btn_search = new JButton("Search");
			
			btn_search.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			        String source = (String) comboBox.getSelectedItem();
			        String destination = (String) comboBox_1.getSelectedItem();
	
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
	
			        try {
			            Class.forName("com.mysql.cj.jdbc.Driver");
			            Connection con = DriverManager.getConnection(url, user, pass);
			            Statement st = con.createStatement();
	
			            String query = "SELECT * FROM bus WHERE source = '" + source + "' AND destination = '" + destination + "' AND Date = '" + date + "'";
			            ResultSet rs = st.executeQuery(query);
	
			            DefaultTableModel tablemodel = (DefaultTableModel) table.getModel();
			            tablemodel.setRowCount(0); // Clear existing rows
	
			            int i = 0;
			            while (rs.next()) {
			                String bno = rs.getString("b_num");
			                String src = rs.getString("source");
			                String dest = rs.getString("destination");
			                String dt = rs.getString("Date");
			                String time = rs.getString("time");
			                String fr = rs.getString("fare");
			                String seatavailable = rs.getString("seats");
			                tablemodel.addRow(new Object[]{bno, src, dest, dt, time, fr,seatavailable});
			                i++;
			            }
	
			            if (i < 1) {
			                JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
			            }
	
			            st.close();
			            con.close();
			        }
			        catch (Exception ex) {
			            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			        }
			    }
			});
			
			btn_search.setFont(new Font("Times New Roman", Font.PLAIN, 25));
			btn_search.setBounds(714, 225, 147, 31);
			getContentPane().add(btn_search);
			
			JScrollPane scrollpane = new JScrollPane();
			scrollpane.setBounds(297, 353, 947, 170);
			getContentPane().add(scrollpane);
			
			table = new JTable();
			scrollpane.setViewportView(table);
			table.setFont(new Font("Times New Roman", Font.PLAIN, 17));
			table.setModel(new DefaultTableModel(
	                new Object[][]{
	                		{null, null, null, null, null,null},
	                		{null, null, null, null, null,null},
	                        {null, null, null, null, null,null},
	                        {null, null, null, null, null,null},
	                        {null, null, null, null, null,null},
	                        {null, null, null, null, null,null},
	                        {null, null, null, null, null,null},
	                        {null, null, null, null, null,null},
	                        {null, null, null, null, null,null},
	                },
	        	new String[] {
					"Bus Number", "Source", "Destination", "Date", "Timings","Fare","Seats"
				}
			));
			table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			
			JComboBox comboBox_2 = new JComboBox();
			comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Upi", "Cash", "Net Banking"}));
			comboBox_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			comboBox_2.setBounds(771, 556, 122, 31);
			backgroundPanel.add(comboBox_2);
			
			JButton btn_book = new JButton("Book\r\n");
			
			btn_book.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	java.util.Date selectedDate = dateChooser.getDate();
		        	if(selectedDate == null)
			        {
			        	JOptionPane.showMessageDialog(null, "Please Select the Date..!", "Error", JOptionPane.ERROR_MESSAGE);
			        	return;
			        }
			    	String seatCountStr = JOptionPane.showInputDialog(null, "Enter the number of seats you want to book:");
			        String usr = JOptionPane.showInputDialog(null, "Enter Your Username to confirm :");
			        
			        try {
			            int seatCount = Integer.parseInt(seatCountStr);
			            if (seatCount <= 0) {
			                JOptionPane.showMessageDialog(null, "Please enter a valid number of seats.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
			                return;
			            }
			            int selectedRow = table.getSelectedRow();
			            if (selectedRow == -1) {
			                JOptionPane.showMessageDialog(null, "Please select a bus to book.", "No Bus Selected", JOptionPane.ERROR_MESSAGE);
			                return;
			            }
			            
			            String busNumber = table.getModel().getValueAt(selectedRow, 0).toString();
			            int availableSeats = Integer.parseInt(table.getModel().getValueAt(selectedRow, 6).toString());
			            String src = table.getModel().getValueAt(selectedRow, 1).toString();
			            String Dest = table.getModel().getValueAt(selectedRow, 2).toString();	
			            String dte = table.getModel().getValueAt(selectedRow, 3).toString();
			            String pay = comboBox_2.getSelectedItem().toString();
			            if (seatCount > availableSeats) {
			                JOptionPane.showMessageDialog(null, "Not enough seats available for booking.", "Booking Error", JOptionPane.ERROR_MESSAGE);
			                return;
			            }
			            
			            String url = "jdbc:mysql://localhost:3306/bus_mngt";
			            String user = "root";
			            String pass = "";
			            
			            Class.forName("com.mysql.cj.jdbc.Driver");
			            Connection con = DriverManager.getConnection(url, user, pass);
			            Statement st = con.createStatement();
			            String updateQuery = "UPDATE bus SET seats = seats - " + seatCount + " WHERE b_num = '" + busNumber + "'";
			            int rowsUpdated = st.executeUpdate(updateQuery);
			            if (rowsUpdated > 0) {
			                JOptionPane.showMessageDialog(null, "Seats booked successfully!");
			                btn_search.doClick();
			            } 
			            else {
			                JOptionPane.showMessageDialog(null, "Failed to book seats. Please try again.", "Booking Error", JOptionPane.ERROR_MESSAGE);
			            }
			            String exQuery = "INSERT INTO bookings (username, busnum, source, destination, date, timings, seats, Payment) " +
			                    "VALUES ('" + usr + "', '" + busNumber + "', '" + src + "', '" + Dest + "', '" + dte + "', '" + table.getModel().getValueAt(selectedRow, 4).toString() + "', " + seatCountStr + ",'" + pay + "')";
			            st.executeUpdate(exQuery);
			            st.close();
			            con.close();
			        } catch (NumberFormatException ex) {
			            JOptionPane.showMessageDialog(null, "Please enter a valid number for seats.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
			        } catch (Exception ex) {
			            JOptionPane.showMessageDialog(null, "Error occurred while booking seats.", "Booking Error", JOptionPane.ERROR_MESSAGE);
			            ex.printStackTrace();
			        }
			    }
			});

			btn_book.setFont(new Font("Times New Roman", Font.PLAIN, 25));
			btn_book.setBounds(652, 620, 209, 44);
			backgroundPanel.add(btn_book);
			
		}
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Bus_booking frame = new Bus_booking();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
