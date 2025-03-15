package bus;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Add_route extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField seats_txt;
	private JTextField time_txt;
	private JTextField fare_txt;

	public Add_route() {
		setTitle("Add Bus Info");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1489,788);
		setLocationRelativeTo(null);
		setExtendedState(MAXIMIZED_BOTH);
		getContentPane().setLayout(null);
		
		JPanel backgroundPanel = new JPanel() {
			private static final long serialVersionUID = 1L;
			protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("C:\\Users\\hs089\\Downloads\\bus.jpg");
                Image image = icon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
		backgroundPanel.setForeground(new Color(0, 0, 0));
		backgroundPanel.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		backgroundPanel.setBackground(SystemColor.control);
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

		
		JLabel lbl_bus = new JLabel(" Bus Number");
		lbl_bus.setAlignmentX(Component.CENTER_ALIGNMENT);
		lbl_bus.setOpaque(true);
		lbl_bus.setBackground(new Color(255, 153, 153));
		lbl_bus.setForeground(new Color(255, 255, 255));
		lbl_bus.setBounds(379,230,148,40);
		lbl_bus.setFont(new Font("Times New Roman", Font.BOLD, 25));
		getContentPane().add(lbl_bus);
		
		JLabel lbl_src = new JLabel("      Source");
		lbl_src.setBackground(new Color(255, 153, 153));
		lbl_src.setOpaque(true);
		lbl_src.setAlignmentX(Component.CENTER_ALIGNMENT);
		lbl_src.setForeground(new Color(255, 255, 255));
		lbl_src.setBounds(379,300,149,48);
		lbl_src.setFont(new Font("Times New Roman", Font.BOLD, 25));
		getContentPane().add(lbl_src);
		
		JLabel lbl_dest = new JLabel(" Destination");
		lbl_dest.setBackground(new Color(255, 153, 153));
		lbl_dest.setAlignmentX(Component.CENTER_ALIGNMENT);
		lbl_dest.setOpaque(true);
		lbl_dest.setForeground(new Color(255, 255, 255));
		lbl_dest.setBounds(838,300,138,48);
		lbl_dest.setFont(new Font("Times New Roman", Font.BOLD, 25));
		getContentPane().add(lbl_dest);
		
		JLabel lbl_fare = new JLabel(" Fare(INR)");
		lbl_fare.setAlignmentX(Component.CENTER_ALIGNMENT);
		lbl_fare.setOpaque(true);
		lbl_fare.setBackground(new Color(255, 153, 153));
		lbl_fare.setForeground(new Color(255, 255, 255));
		lbl_fare.setBounds(379,453,148,48);
		lbl_fare.setFont(new Font("Times New Roman", Font.BOLD, 25));
		getContentPane().add(lbl_fare);
		
		JTextField bnum_txt = new JTextField();
        bnum_txt.setBounds(567,230,200,30);
        bnum_txt.setEditable(false);
        getContentPane().add(bnum_txt);
        
		fare_txt = new JTextField();
        fare_txt.setBounds(567,463,200,30);
        getContentPane().add(fare_txt);

		JButton bb1 = new JButton("SUBMIT");
		bb1.setBounds(595,572,200,30);
		getContentPane().add(bb1);
		
		JButton bb2 = new JButton("CLEAR");
		bb2.setBounds(893,572,100,30);
		getContentPane().add(bb2);
		
		seats_txt = new JTextField();
		seats_txt.setBounds(1021, 467, 200, 30);
		getContentPane().add(seats_txt);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(566, 385, 201, 30);
		getContentPane().add(dateChooser);
		
		JLabel lbl_date = new JLabel("       Date");
		lbl_date.setBackground(new Color(255, 153, 153));
		lbl_date.setAlignmentX(Component.CENTER_ALIGNMENT);
		lbl_date.setOpaque(true);
		lbl_date.setForeground(new Color(255, 255, 255));
		lbl_date.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lbl_date.setBounds(379, 375, 148, 48);
		getContentPane().add(lbl_date);
		
		JLabel lbl_time = new JLabel("     Time");
		lbl_time.setBackground(new Color(255, 153, 153));
		lbl_time.setAlignmentX(Component.CENTER_ALIGNMENT);
		lbl_time.setOpaque(true);
		lbl_time.setForeground(new Color(255, 255, 255));
		lbl_time.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lbl_time.setBounds(838, 374, 138, 50);
		getContentPane().add(lbl_time);
		
		time_txt = new JTextField();
		time_txt.setBounds(1021, 385, 200, 30);
		getContentPane().add(time_txt);
		time_txt.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Add Bus Details ");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 45));
		lblNewLabel_2.setBounds(634, 78, 404, 66);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lbl_seat = new JLabel("       Seats");
		lbl_seat.setBackground(new Color(255, 153, 153));
		lbl_seat.setOpaque(true);
		lbl_seat.setForeground(new Color(255, 255, 255));
		lbl_seat.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lbl_seat.setBounds(838, 453, 138, 48);
		backgroundPanel.add(lbl_seat);
		
		JButton btnNewButton = new JButton("Generate Bus Number");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Random random = new Random();
		        int randomNumber = random.nextInt(10000);
		        String formattedNumber = String.format("%04d", randomNumber);
		        String result = "DL14CC" + formattedNumber;
		        bnum_txt.setText(result);				
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(255, 153, 153));
		btnNewButton.setSelected(true);
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton.setBounds(838, 230, 260, 30);
		backgroundPanel.add(btnNewButton);
		
		JComboBox src_combobox = new JComboBox();
		src_combobox.setModel(new DefaultComboBoxModel(new String[] {"Tis Hazari", "Kamla Nagar", "Shahdara", "Rohini", "Chandni Chowk", "Pitampura"}));
		src_combobox.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		src_combobox.setBounds(567, 306, 200, 40);
		backgroundPanel.add(src_combobox);
		
		JComboBox dest_combobox = new JComboBox();
		dest_combobox.setModel(new DefaultComboBoxModel(new String[] {"Gurugram", "Rohini East", "Kohat Enclave", "Punjabi Bagh", "Welcome"}));
		dest_combobox.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		dest_combobox.setBounds(1021, 308, 200, 40);
		backgroundPanel.add(dest_combobox);
		
		bb2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event)
			{
				bnum_txt.setText("");
	            fare_txt.setText("");
			}});	
		
		bb1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent event) {
		        String url = "jdbc:mysql://localhost:3306/bus_mngt";
		        String user = "root";
		        String pass = "";

		        try {
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            Connection con = DriverManager.getConnection(url, user, pass);
		            Statement st = con.createStatement();

		            String s1 = bnum_txt.getText();
		            String s2 = src_combobox.getSelectedItem().toString();
		            String s3 = dest_combobox.getSelectedItem().toString();
		            if(bnum_txt.getText().isEmpty() || seats_txt.getText().isEmpty() || fare_txt.getText().isEmpty())
		            {
		            	JOptionPane.showMessageDialog(null, "Fill The Fields First", "Error", JOptionPane.ERROR_MESSAGE);
		            	return;
		            }
		            long f = Long.parseLong(fare_txt.getText());//
		            int s4 = Integer.parseInt(seats_txt.getText());
		            String s6 = time_txt.getText();
		            java.util.Date selectedDate = dateChooser.getDate();
		            	if (selectedDate != null) {
		                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		                String s5 = dateFormat.format(selectedDate);
		                String sql = "INSERT INTO bus (b_num, source, destination, fare, date, seats, time) " +
		                             "VALUES ('" + s1 + "', '" + s2 + "', '" + s3 + "', " + f + ", '" + s5 + "', " + s4 + ", '" + s6 + "')";
		                st.executeUpdate(sql);
		            }
		            st.close();
		            con.close();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		});
	}

	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Add_route frame = new Add_route();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}