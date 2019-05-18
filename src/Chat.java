
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.ArrayList;

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
	private String nombre;
	private BigInteger p;
	private BigInteger q;
	private BigInteger n;
	private BigInteger e;
	private Chat other;
	private String msgSinCodificar;
	private String msgCodificado;
	private String msgRecibidoCodificado;
	private String msgRecibidoDecodificado;

	public Chat(String nombre, BigInteger p, BigInteger q, BigInteger e) {

		screen = Toolkit.getDefaultToolkit();

		// setBounds(panelWith / 2, panelHeight / 2, panelWith, panelHeight);

		setBounds(1024 / 4, 768 / 6, PHEIGH, PWIDTH);

		setTitle("Chat " + nombre);
		
		setResizable(false);
		this.nombre = nombre;
		this.p = p;
		this.q = q;
		this.n = this.p.multiply(this.q);
		this.e = e;

		initComponents();

	}

	public void setOther(Chat c) {
		this.other = c;
	}

	public void setMsgRecibidoCodificado(String s) {
		System.out.println(nombre + " recibe el mensaje");
		this.msgRecibidoCodificado = s;
		
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

		textArea.setBounds(50, 32, 919, 321);
		getContentPane().add(textArea);

		textArea.setText("aasdasd");

	}

	public void codificar() {

	}

	public void decodificar() {
		System.out.println(this.msgRecibidoCodificado);
		BigInteger fiPepa = new BigInteger("2");
		BigInteger uno = new BigInteger("1");

		fiPepa = p.subtract(uno).multiply(q.subtract(uno));
		System.out.println("Fi de Pepa: " + fiPepa);

//		BigInteger fiBenito = new BigInteger("");
//		
//		fiBenito= n1Benito.subtract(uno).multiply(n2Benito.subtract(uno));
//		//d=e modulo fn
//		(%i3)	inv_mod(356812573,62439738695206345305096)
//		;
//		(%o3)	6913234582562878332397

		// BigInteger d = new BigInteger("6913234582562878332397");
		// lo mismo que en maxima
		BigInteger eInvertida = e;
		BigInteger d = eInvertida.modInverse(fiPepa);
		System.out.println(d + "     dsfdsfdsf");
		// PASAR MSG A NUMERO
		int N = alf.length;
		System.out.println(N);

		double k = Math.log10(e.doubleValue()) / Math.log10(N);
		System.out.println("K  :" + k);
		// sacamos K, para descifrar hay que usar k+1 y cifrear k, que sería la parte
		// entera del numero an terior, en este caso 11
		// CAMBIAR DEPENDIENDO DE K
		int K2 = 12;
		// CAMBIAR DEPENDIENDO DE K
		// CAMBIAR DEPENDIENDO DE K
		System.out.println("LONGITUD: " + msgRecibidoCodificado.length());
		char msgEnChar[] = new char[msgRecibidoCodificado.length()];

		for (int i = 0; i < msgRecibidoCodificado.length(); i++) {
			msgEnChar[i] = msgRecibidoCodificado.charAt(i);
		}

		BigInteger contador = new BigInteger("0");
		int contadorDeK = 0;
		int contadorDeVecesDeK = 0;
		ArrayList<BigInteger> arr = new ArrayList<BigInteger>();
		ArrayList<String> lista = new ArrayList<String>();
		int salida[] = new int[12];
		int posicion = 0;
		int contadorDeI = 0;
		for (int i = 0; i < msgEnChar.length; i++) {

			for (int j = 0; j < alf.length; j++) {
				if (msgEnChar[i] == alf[j]) {
					posicion = j;
				}

			}
			// System.out.println("contador de i vale "+ contadorDeI+" posicion vale:
			// "+posicion);
			salida[contadorDeI] = posicion;
			if (contadorDeI == 11 && i != 0) {
//				System.out.println("salida vale:  ");
//				for (int j = 0; j < salida.length; j++) {
//					System.out.print(salida[j]);
//				}
//				System.out.println();

				BigInteger resultado = calcularEntero(0, N, salida);
				// System.out.println("salida vale: "+ resultado);
				Potencia pot = new Potencia(resultado, d, n);
				resultado = pot.Apotenciacion();
				// System.out.println("despues de la potencia modular vale: "+ resultado);
				arr.add(resultado);
				contadorDeI = 0;
				salida = new int[12];

			} else {
				contadorDeI++;
			}

		}
		System.out.println();

		ArrayList<BigInteger> arrDescomprimido = arr;
		// pasamos de decimal a texto
		StringBuilder guardarMensaje = new StringBuilder();
		for (int i = 0; i < arrDescomprimido.size(); i++) {
			BigInteger m = arrDescomprimido.get(i);

			ArrayList<Integer> out = new ArrayList<Integer>();
			BigInteger cociente = new BigInteger("0");
			BigInteger resto = new BigInteger("0");

			// System.out.println("m vale: "+m);
			// CAMBIAR 84 por N
			while (m.compareTo(new BigInteger("84")) == 1) {

				cociente = m.divide(new BigInteger("84"));
				resto = m.remainder(new BigInteger("84"));
				m = cociente;
				// System.out.println("cociente vale :" + cociente);
				out.add(resto.intValue());

			}
			ArrayList<Integer> outDeVerdad = new ArrayList<Integer>();
			// System.out.println(out.size());
			// vambiar 10 por k-2
			if (out.size() < k - 2) {

				outDeVerdad.add(0);

			}
			outDeVerdad.add(cociente.intValue());
			for (int j = out.size() - 1; j >= 0; j--) {
				outDeVerdad.add(out.get(j));

			}

			for (int j = 0; j < outDeVerdad.size(); j++) {
				// System.out.print(outDeVerdad.get(j) + " ");

			}
			
			// System.out.println("en letra");
			for (int j = 0; j < outDeVerdad.size(); j++) {
				if (j != 0 && outDeVerdad.get(j) == 74 && outDeVerdad.get(j - 1) == 74) {
					System.out.println();
					guardarMensaje.append("/n");
				} else {
					System.out.print(alf[outDeVerdad.get(j)]);
					guardarMensaje.append(alf[outDeVerdad.get(j)]);
				}
			}
			// System.out.println();
		}
		System.out.println("En metodo decodificar");
		System.out.println(guardarMensaje.toString());
		msgRecibidoDecodificado = guardarMensaje.toString();
		
		
		
		textArea.setText(this.msgRecibidoDecodificado);
		

	}

	private BigInteger calcularEntero(long bL, int pot, int[] listaB) {
		BigInteger b = BigInteger.valueOf(bL);
		BigInteger potbL = BigInteger.valueOf(pot);
		for (int i = 0; i < listaB.length; i++) {
			// System.out.print(listaB[i]+ "\t");
			BigInteger letra = BigInteger.valueOf(listaB[i]);
			BigInteger potencia = potbL.pow(12 - i - 1);
			// System.out.println(b+" potencia "+potencia);
			b = b.add(letra.multiply(potencia));
		}
		// System.out.println("b vale: "+b);
		return b;

	}

	public class listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			if (arg0.getActionCommand().equals("Enviar")) {

				String aEnviar = textField_1.getText();
				
				other.setMsgRecibidoCodificado(aEnviar);
				other.decodificar();
				
			} else if (arg0.getActionCommand().equals("Codificar")) {

			}

		}

	}
}
