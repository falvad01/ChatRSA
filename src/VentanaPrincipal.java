
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
	private static final int PWIDTH = 750;
	private static final int PHEIGH = 348;
	private JTextField p1;
	private JTextField q1;
	private JTextField e1;
	private JTextField e2;
	private JTextField p2;
	private JTextField q2;

	public VentanaPrincipal() {
		getContentPane().setBackground(new Color(102, 153, 204));

		screen = Toolkit.getDefaultToolkit();

		// setBounds(panelWith / 2, panelHeight / 2, panelWith, panelHeight);

		setBounds(1024 / 4, 768 / 6, PWIDTH, PHEIGH);

		setTitle("Chat");

		setResizable(false);

		Image icono = screen.getImage("etc/images/interface/icono.jpg");

		setIconImage(icono);

		initComponents();

	}
	
	private void initComponents() {

		getContentPane().setLayout(null);
		
		p1 = new JTextField();
		p1.setText("249879448303");
		p1.setBounds(235, 71, 134, 20);
		getContentPane().add(p1);
		p1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("P del participante 1");
		lblNewLabel.setBounds(106, 74, 119, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNDelParticipante = new JLabel("Q del participante 1");
		lblNDelParticipante.setBounds(106, 103, 119, 14);
		getContentPane().add(lblNDelParticipante);
		
		q1 = new JTextField();
		q1.setText("249879448349");
		q1.setColumns(10);
		q1.setBounds(235, 102, 134, 20);
		getContentPane().add(q1);
		
		JLabel lblEDelParticipante = new JLabel("e del participante 1");
		lblEDelParticipante.setBounds(250, 145, 119, 14);
		getContentPane().add(lblEDelParticipante);
		
		JLabel lblEDelParticipante_1 = new JLabel("e del participante 2");
		lblEDelParticipante_1.setBounds(250, 176, 119, 14);
		getContentPane().add(lblEDelParticipante_1);
		
		e1 = new JTextField();
		e1.setText("356812573");
		e1.setColumns(10);
		e1.setBounds(379, 142, 134, 20);
		getContentPane().add(e1);
		
		e2 = new JTextField();
		e2.setText("80263681");
		e2.setColumns(10);
		e2.setBounds(379, 173, 134, 20);
		getContentPane().add(e2);
		
		
		listener list = new listener();
		JButton iniciar = new JButton("Iniciar chat");
		iniciar.setBounds(250, 220, 263, 36);
		iniciar.addActionListener(list);
		getContentPane().add(iniciar);
		
		JLabel lblPDelParticipante = new JLabel("P del participante 2");
		lblPDelParticipante.setBounds(387, 74, 119, 14);
		getContentPane().add(lblPDelParticipante);
		
		p2 = new JTextField();
		p2.setText("27264083009");
		p2.setColumns(10);
		p2.setBounds(516, 71, 134, 20);
		getContentPane().add(p2);
		
		q2 = new JTextField();
		q2.setText("27264083017");
		q2.setColumns(10);
		q2.setBounds(516, 100, 134, 20);
		getContentPane().add(q2);
		
		JLabel label_1 = new JLabel("Q del participante 2");
		label_1.setBounds(387, 103, 119, 14);
		getContentPane().add(label_1);

		
		
		
	}
	
	
	
	public class listener implements ActionListener {

		

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			Chat chat1 = new Chat("Javi", new BigInteger(p1.getText()), new BigInteger(q1.getText()),new BigInteger(e1.getText()));
			Chat chat2 = new Chat("Xian", new BigInteger(p1.getText()), new BigInteger(q2.getText()),new BigInteger(e2.getText()));
			chat1.setOther(chat2);
			chat2.setOther(chat1);
			chat1.setVisible(true);
			chat2.setVisible(true);

			
		}

	}
}
