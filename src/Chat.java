
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;

import javax.swing.JTextArea;

public class Chat extends JFrame {

	Toolkit screen;
	private static final int PWIDTH = 600;
	private static final int PHEIGH = 1000;
	private JTextField textField;
	private JTextField textField_1;
	JTextArea textArea;

	private char[] alf = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'p', 'q',
			'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
			'M', 'N', 'Ñ', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'á', 'é', 'í', 'ó', 'ú', 'Á',
			'É', 'Í', 'Ó', 'Ú', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ' ', ',', '.', ':', '!', '-', '¿',
			'?', '(', ')' };

	private BigInteger n;
	private BigInteger e;
	private Chat other;
	private String msgSinCodificar;
	private String msgCodificado;
	private String msgRecibidoCodificado;

	public Chat(String nombre, BigInteger n, BigInteger e) {

		screen = Toolkit.getDefaultToolkit();

		// setBounds(panelWith / 2, panelHeight / 2, panelWith, panelHeight);

		setBounds(1024 / 4, 768 / 6, PHEIGH, PWIDTH);

		setTitle("Chat " + nombre);

		setResizable(false);

		this.n = n;
		this.e = e;

		initComponents();

	}

	public void setOther(Chat c) {
		this.other = c;
	}
	
	public void setMsgRecibidoCodificado(String s) {
		this.msgRecibidoCodificado = s;
		textArea.setText(this.msgRecibidoCodificado);
	}

	private void initComponents() {

		getContentPane().setLayout(null);

		textField = new JTextField();
		textField.setBounds(10, 477, 535, 20);
		getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(10, 501, 535, 20);
		getContentPane().add(textField_1);

		listener list = new listener();

		JButton btnNewButton = new JButton("Codificar");
		btnNewButton.setBounds(558, 476, 89, 23);
		btnNewButton.addActionListener(list);
		getContentPane().add(btnNewButton);

		JButton enviar = new JButton("Enviar");
		enviar.setBounds(558, 500, 89, 23);
		enviar.addActionListener(list);
		getContentPane().add(enviar);

		textArea = new JTextArea();

		textArea.setBounds(50, 32, 773, 321);
		getContentPane().add(textArea);

		textArea.setText("aasdasd");

	}

	public class listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			if (arg0.getActionCommand().equals("Enviar")) {
				
				String  aEnviar = textField_1.getText();
				
				other.setMsgRecibidoCodificado(aEnviar);
			}

		}

	}
}
