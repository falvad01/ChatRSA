import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;

import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JLabel;

public class Chat extends JFrame {

	Toolkit screen;
	private static final int PWIDTH = 600;
	private static final int PHEIGH = 1000;
	private JTextField textField;
	private JTextField textField_1;
	JTextArea txtrRecibidos;

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
	private JLabel lblNewLabel;

	public Chat(String nombre, BigInteger p, BigInteger q, BigInteger e) {
		getContentPane().setBackground(new Color(102, 153, 204));

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
		System.out.println("chat "+ nombre);
		System.out.println("n: "+n);
		System.out.println("p: "+p);
		System.out.println("q: "+q);
		System.out.println("e: "+e);
		
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

		txtrRecibidos = new JTextArea();
		txtrRecibidos.setText("Recibidos:");
		txtrRecibidos.setBackground(new Color(102, 153, 204));

		txtrRecibidos.setBounds(50, 32, 919, 321);
		getContentPane().add(txtrRecibidos);
		
		lblNewLabel = new JLabel("Chat cifrado RSA por bloques");
		lblNewLabel.setBounds(22, 7, 200, 14);
		getContentPane().add(lblNewLabel);


	}

	public void codificar() {

		// PASO 1 calculamos fi y d
		BigInteger fi = new BigInteger("2");
		BigInteger uno = new BigInteger("1");

		fi = p.subtract(uno).multiply(q.subtract(uno));

		BigInteger eInvertida = e;
		BigInteger d = eInvertida.modInverse(fi);

		int N = alf.length; // Tamanio alfabeto
		// Calculamos k
		double k = Math.log10(d.doubleValue()) / Math.log10(N);
		long iPart = (long) k;
		int K2 = (int) iPart;

		// PASO 2 y 3

		char msgEnChar[] = new char[msgSinCodificar.length()];

		for (int i = 0; i < msgSinCodificar.length(); i++) {
			msgEnChar[i] = msgSinCodificar.charAt(i);
		}

		BigInteger contador = new BigInteger("0");
		int contadorDeK = 0;
		int contadorDeVecesDeK = 0;
		ArrayList<BigInteger> arr = new ArrayList<BigInteger>();
		ArrayList<String> lista = new ArrayList<String>();
		int salida[] = new int[K2];
		int posicion = 0;
		int contadorDeI = 0;
		for (int i = 0; i < msgEnChar.length; i++) {

			for (int j = 0; j < alf.length; j++) {
				if (msgEnChar[i] == alf[j]) {
					posicion = j;
				}

			}

			salida[contadorDeI] = posicion;
			if (contadorDeI == K2 - 1 && i != 0) {
				// Paso 3, calcular m
				BigInteger resultado = calcularEntero(0, N, salida, K2);

				// Calcular c= m ^e mod n
				Potencia pot2 = new Potencia(resultado, other.e, other.n);
				resultado = pot2.Apotenciacion();

				arr.add(resultado);

				contadorDeI = 0;
				salida = new int[K2];

			} else {
				contadorDeI++;
			}

		}

		// PASO 4 pasamos c a letra c(M)
		ArrayList<BigInteger> arrDescomprimido = arr;
		// pasamos de decimal a texto
		StringBuilder guardarMensaje = new StringBuilder();
		for (int i = 0; i < arrDescomprimido.size(); i++) {
			BigInteger m = arrDescomprimido.get(i);

			ArrayList<Integer> out = new ArrayList<Integer>();
			BigInteger cociente = new BigInteger("0");
			BigInteger resto = new BigInteger("0");

			while (m.compareTo(new BigInteger("84")) == 1) {

				cociente = m.divide(new BigInteger("84"));
				resto = m.remainder(new BigInteger("84"));
				m = cociente;

				out.add(resto.intValue());

			}
			ArrayList<Integer> outDeVerdad = new ArrayList<Integer>();

			// El cifrado para el bloque M de longitud k es el bloque C = C1 . . . Ck+1.
			// aniadiendo 0 a la izquierda hasta longitud k
			if (out.size() < K2) {
				outDeVerdad.add(0);
			}

			outDeVerdad.add(cociente.intValue());
			for (int j = out.size() - 1; j >= 0; j--) {
				outDeVerdad.add(out.get(j));

			}

			for (int j = 0; j < outDeVerdad.size(); j++) {
				if (j != 0 && outDeVerdad.get(j) == 74 && outDeVerdad.get(j - 1) == 74) {
					System.out.println();
					guardarMensaje.append("/n");
				} else {
					System.out.print(alf[outDeVerdad.get(j)]);
					guardarMensaje.append(alf[outDeVerdad.get(j)]);
				}
			}

		}
		System.out.println("En metodo decodificar");
		System.out.println(guardarMensaje.toString());
		msgCodificado = guardarMensaje.toString();

		textField_1.setText(this.msgCodificado);

	}

	public void decodificar() {

		// PASO 1 calculamos fi y d
		BigInteger fi = new BigInteger("2");
		BigInteger uno = new BigInteger("1");

		fi = p.subtract(uno).multiply(q.subtract(uno));

		BigInteger eInvertida = e;
		BigInteger d = eInvertida.modInverse(fi);

		int N = alf.length;

		// Calculamos k al descifrar usamos k + 1
		double k = Math.log10(d.doubleValue()) / Math.log10(N);
		System.out.println("K  :" + k);

		long iPart = (long) k;

		int K2 = (int) iPart + 1;
		System.out.println("k2: "+K2);
		System.out.println("tamaño del msg "+ msgRecibidoCodificado.length());
		char msgEnChar[] = new char[msgRecibidoCodificado.length()];

		for (int i = 0; i < msgRecibidoCodificado.length(); i++) {
			msgEnChar[i] = msgRecibidoCodificado.charAt(i);
		}

		// PASO 2 y 3
		BigInteger contador = new BigInteger("0");
		int contadorDeK = 0;
		int contadorDeVecesDeK = 0;
		ArrayList<BigInteger> arr = new ArrayList<BigInteger>();
		ArrayList<String> lista = new ArrayList<String>();
		int salida[] = new int[K2];
		int posicion = 0;
		int contadorDeI = 0;
		for (int i = 0; i < msgEnChar.length; i++) {

			for (int j = 0; j < alf.length; j++) {
				if (msgEnChar[i] == alf[j]) {
					posicion = j;
				}

			}

			salida[contadorDeI] = posicion;
			if (contadorDeI == K2 - 1 && i != 0) {

				// PASO 3A
				// Calculamos el entero ci
				BigInteger resultado = calcularEntero(0, N, salida, K2);

				// PASO 3B
				// Calculamos bi=ci^d mod n
				Potencia pot = new Potencia(resultado, d, n);
				resultado = pot.Apotenciacion();

				// PASO 3C
				arr.add(resultado);
				contadorDeI = 0;
				salida = new int[K2];

			} else {
				contadorDeI++;
			}

		}

		ArrayList<BigInteger> arrDescomprimido = arr;

		// PASO 4
		// M = B1 . . . Br.
		StringBuilder guardarMensaje = new StringBuilder();
		for (int i = 0; i < arrDescomprimido.size(); i++) {
			BigInteger m = arrDescomprimido.get(i);

			ArrayList<Integer> out = new ArrayList<Integer>();
			BigInteger cociente = new BigInteger("0");
			BigInteger resto = new BigInteger("0");

			while (m.compareTo(new BigInteger("84")) == 1) {

				cociente = m.divide(new BigInteger("84"));
				resto = m.remainder(new BigInteger("84"));
				m = cociente;
				out.add(resto.intValue());

			}
			ArrayList<Integer> outDeVerdad = new ArrayList<Integer>();

			if (out.size() < K2 - 2) {

				outDeVerdad.add(0);

			}
			outDeVerdad.add(cociente.intValue());
			for (int j = out.size() - 1; j >= 0; j--) {
				outDeVerdad.add(out.get(j));

			}
System.out.println("tamañod e bloques: "+outDeVerdad.size());
			for (int j = 0; j < outDeVerdad.size(); j++) {
				if (j != 0 && outDeVerdad.get(j) == 74 && outDeVerdad.get(j - 1) == 74) {
					System.out.println();
					guardarMensaje.append("\n");
				
				} else {
				System.out.print(alf[outDeVerdad.get(j)]);
				guardarMensaje.append(alf[outDeVerdad.get(j)]);
				}
			}
		}
		
		msgRecibidoDecodificado = guardarMensaje.toString();

	//	textArea.setText(this.msgRecibidoDecodificado);
		//textArea.add(this.msgRecibidoDecodificado);
		txtrRecibidos.append("\n"+this.msgRecibidoDecodificado);
		txtrRecibidos.append("\n-------------------------------------------------------------------------------------------------");

	}

	private BigInteger calcularEntero(long bL, int pot, int[] listaB, int K) {
		BigInteger b = BigInteger.valueOf(bL);
		BigInteger potbL = BigInteger.valueOf(pot);
		for (int i = 0; i < listaB.length; i++) {
			BigInteger letra = BigInteger.valueOf(listaB[i]);
			BigInteger potencia = potbL.pow(K - i - 1);
			b = b.add(letra.multiply(potencia));
		}
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
				
				String aCodificar = textField.getText();
				BigInteger fi = new BigInteger("2");
				BigInteger uno = new BigInteger("1");
				fi = p.subtract(uno).multiply(q.subtract(uno));
				BigInteger eInvertida = e;
				BigInteger d = eInvertida.modInverse(fi);

				int N = alf.length;

				double k = Math.log10(d.doubleValue()) / Math.log10(N);
				
				long iPart = (long) k;

				int K2 = (int) iPart;
				

				if (aCodificar.length() % K2 != 0) {
					JOptionPane.showMessageDialog(null,
							"El tamaño del mensaje tiene que ser de k veces, en este caso tiene que ser multiplo o divisor de "
									+ K2 + "\n Faltan :" + (K2 - (aCodificar.length() % K2)) + " caracteres",
							"ERROR EN EL TAMAÑO", JOptionPane.WARNING_MESSAGE);
				} else {

					msgSinCodificar = aCodificar;
					codificar();
				}

			}

		}

	}
}