import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Application extends JFrame {
	DefaultTableModel model = new DefaultTableModel();
	JTable table;
	String link = "/var/log/snort/alert";
	public Application(String title) {
		super(title);
		
		addControls();
		checkNewLog();
	}
	
	public static void main(String[] args) {
		new Application("Snort").showWindow();
	}
	
	private void checkNewLog() {
		TimerTask task = new FileWatcher( new File(link) ) {
		    protected void onChange( File file ) throws Exception {
		      // here we code the action on a change
		      BufferedReader br = new BufferedReader(new FileReader(file));
				try {
					String last = null, line;

				    while ((line = br.readLine()) != null) { 
				        last = line;
				    }
				    
				    String time = Process.getTime(last);
				    String protocol = Process.getProtocol(last);
				    String ipHost = Process.getIPHost(last);
				    String message = Process.getMessage(last);
				    String ipDest = Process.getIPDestination(last);
				    
				    Log log = new Log(ipHost, time, message, protocol, ipDest);
				    
				    String[] row= {"ST",time, protocol, ipHost, message, ipDest};
					model.addRow(row);
				}
				finally {
				    br.close();
				}
		    }
		  };
		  Timer timer = new Timer();
		  // repeat the check every second
		  timer.schedule( task , new Date(), (long) 1000 );
	}
	
	
	private void addControls() {
		Container con=getContentPane();
		JPanel pnMain=new JPanel(new BorderLayout());
		con.add(pnMain);
		
		//Add column and data
		model.addColumn("ST");
		model.addColumn("DateTime");
		model.addColumn("Protocol");
		model.addColumn("IP Host");
		model.addColumn("Message");
		model.addColumn("IP Destination");
		
		//Set font for table
		table=new JTable(model); 
		table.setFont(new Font(null, 0, 15));
		table.getTableHeader().setBackground(Color.CYAN);
		table.getTableHeader().setForeground(Color.red);
		table.getTableHeader().setFont(new Font("Tahome",Font.BOLD,15));
		
		JScrollPane sc=new JScrollPane(table);
		pnMain.add(sc);
		
		//Try add new row
		String[] row2= {"ST","CNT","Sensor","Alert ID","Date/Time ","Src IP "};
		model.addRow(row2);
	}
	
	
	public void showWindow() {
		setSize(1365, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
}
