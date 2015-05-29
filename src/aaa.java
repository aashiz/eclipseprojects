import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class aaa extends JFrame {

	private JPanel contentPane;
	private JTextField txtSend;
	private Socket soc;
	JTextArea txtrConsole;
	private JButton btnClear;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					aaa frame = new aaa();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public aaa() {
		for(LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
			System.out.println(info.getName());
			System.out.println(info.getClassName())	;
			if("Windows".equals(info.getName())){
				try {
					UIManager.setLookAndFeel(info.getClassName());
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Socket x = new Socket("localhost", 2555);
					soc =x ;
					txtrConsole.append("Connection Successful!!>> \n");
					
					
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							boolean continu = true;
							InputStreamReader read = null;
							try {
								 read  = new InputStreamReader(soc.getInputStream());
							} catch (IOException e) {
								// TODO Auto-generated catch block
								continu = false;
								e.printStackTrace();
							}
							while(continu){
								int i ;
								try {
									while((i = read.read()) != -1){
										System.out.println((char)i)	;
										txtrConsole.append((String.valueOf((char)i)));
									}
								} catch (IOException e) {
									// TODO Auto-generated catch block
									continu =false;
								}
								txtrConsole.append("\n");
							}
						}
					}).start();;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
			}
		});
		contentPane.add(btnConnect, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		txtSend = new JTextField();
		txtSend.setText("Send");
		panel.add(txtSend);
		txtSend.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String txtToSend = txtSend.getText();
				System.out.println(txtToSend);
				try {
					OutputStreamWriter writer = new OutputStreamWriter(soc.getOutputStream());
					writer.write(txtSend.getText().toCharArray());
					writer.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		panel.add(btnSend);
		
		btnClear = new JButton("clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			txtrConsole.setText("");
			}
		});
		panel.add(btnClear);
		
		txtrConsole = new JTextArea();
		txtrConsole.setText("console");
		contentPane.add(txtrConsole, BorderLayout.CENTER);
	}

}
