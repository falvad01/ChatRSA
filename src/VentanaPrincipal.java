
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;


import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class VentanaPrincipal extends JFrame {

	Toolkit screen;
	private static final int PWIDTH = 550;
	private static final int PHEIGH = 750;
	private JTextField n1;
	private JTextField n2;
	private JTextField e1;
	private JTextField e2;

	public VentanaPrincipal() {

		screen = Toolkit.getDefaultToolkit();

		// setBounds(panelWith / 2, panelHeight / 2, panelWith, panelHeight);

		setBounds(1024 / 4, 768 / 6, PHEIGH, PWIDTH);

		setTitle("Solitario");

		setResizable(false);

		Image icono = screen.getImage("etc/images/interface/icono.jpg");

		setIconImage(icono);

		initComponents();

	}
	
	private void initComponents() {

		getContentPane().setLayout(null);
		
		n1 = new JTextField();
		n1.setBounds(379, 143, 134, 20);
		getContentPane().add(n1);
		n1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("N del participante 1");
		lblNewLabel.setBounds(250, 146, 119, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNDelParticipante = new JLabel("N del participante 2");
		lblNDelParticipante.setBounds(250, 175, 119, 14);
		getContentPane().add(lblNDelParticipante);
		
		n2 = new JTextField();
		n2.setColumns(10);
		n2.setBounds(379, 172, 134, 20);
		getContentPane().add(n2);
		
		JLabel lblEDelParticipante = new JLabel("e del participante 2");
		lblEDelParticipante.setBounds(250, 213, 119, 14);
		getContentPane().add(lblEDelParticipante);
		
		JLabel lblEDelParticipante_1 = new JLabel("e del participante 2");
		lblEDelParticipante_1.setBounds(250, 244, 119, 14);
		getContentPane().add(lblEDelParticipante_1);
		
		e1 = new JTextField();
		e1.setColumns(10);
		e1.setBounds(379, 210, 134, 20);
		getContentPane().add(e1);
		
		e2 = new JTextField();
		e2.setColumns(10);
		e2.setBounds(379, 241, 134, 20);
		getContentPane().add(e2);
		
		
		listener list = new listener();
		JButton iniciar = new JButton("Iniciar chat");
		iniciar.setBounds(250, 314, 263, 36);
		iniciar.addActionListener(list);
		getContentPane().add(iniciar);

		
		
		
	}
	
	
	
	public class listener implements ActionListener {

		

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			Chat chat1 = new Chat("Javi", new BigInteger(n1.getText()), new BigInteger(e1.getText()));
			Chat chat2 = new Chat("Xian", new BigInteger(n2.getText()), new BigInteger(e2.getText()));
			chat1.setOther(chat2);
			chat2.setOther(chat1);
			chat1.setVisible(true);
			chat2.setVisible(true);

			
		}

	}
}
