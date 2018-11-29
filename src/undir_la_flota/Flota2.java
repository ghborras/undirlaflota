package undir_la_flota;

import javax.swing.*;
import panelFlota.PanelFondoFlota;
import java.awt.event.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Flota2 extends JFrame {

	private static int[][] shipPlaces;
	private JButton[][] boton;
	private JButton btnNewGame;
	private ImageIcon iconoEscalaBarco;
	private JTextField disparos;
	private JTextField hundidos;
	private JTextField mensaje;
	private int n;
	private int a = 40;
	private int b = 45;
	private int c = 40;
	private int margen = 45;
	private static int cont_disp = 0;
	private static int cont_hund = 0;
	private static int barcos;
	private int tiros = 55;

	public static void main(String[] args) {
		
		Flota2 flota = new Flota2(8);
		flota.setVisible(true);
		shipPlaces = new int[8][8];
		createShips(20, shipPlaces);

		PanelFondoFlota fondo = new PanelFondoFlota();
		fondo.setBounds(-200, 0, 736, 552);
		flota.getContentPane().add(fondo);
	}

	public Flota2(int n) {

		setTitle("UNDIR LA FLOTA v2.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.n = n;
		boton = new JButton[n][n];
		this.setLayout(null);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				boton[i][j] = new JButton();
				boton[i][j].setBounds(b, c, a, a);
				boton[i][j].setBackground(Color.DARK_GRAY);
				b += a;
				String nombre = new Integer(i).toString();
				nombre += new Integer(j).toString();
				boton[i][j].setActionCommand(nombre);
				this.add(boton[i][j]);
			}
			c += a;
			b = margen;
		}

		this.addListeners();
		this.setSize(new Dimension(426, 590));

		disparos = new JTextField(String.valueOf(tiros));
		disparos.setEditable(false);
		disparos.setFont(new Font("Tahoma", Font.BOLD, 20));
		disparos.setHorizontalAlignment(SwingConstants.CENTER);
		disparos.setColumns(10);
		disparos.setBounds(133, 416, 50, 40);
		this.add(disparos);

		hundidos = new JTextField("0");
		hundidos.setEditable(false);
		hundidos.setFont(new Font("Tahoma", Font.BOLD, 20));
		hundidos.setForeground(Color.RED);
		hundidos.setHorizontalAlignment(SwingConstants.CENTER);
		hundidos.setColumns(10);
		hundidos.setBounds(315, 416, 50, 40);
		this.add(hundidos);

		mensaje = new JTextField();
		mensaje.setForeground(Color.BLACK);
		mensaje.setHorizontalAlignment(SwingConstants.CENTER);
		mensaje.setFont(new Font("Tahoma", Font.BOLD, 18));
		mensaje.setEditable(false);
		mensaje.setVisible(false);
		mensaje.setColumns(10);
		mensaje.setBounds(50, 485, 200, 40);
		this.add(mensaje);

		btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				empezar();
			}
		});
		btnNewGame.setBackground(new Color(39, 70, 255));
		btnNewGame.setForeground(Color.WHITE);
		btnNewGame.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewGame.setBounds(258, 485, 107, 40);
		this.add(btnNewGame);

		JLabel lblShoots = new JLabel("SHOOTS");
		lblShoots.setForeground(Color.YELLOW);
		lblShoots.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblShoots.setHorizontalAlignment(SwingConstants.LEFT);
		lblShoots.setBounds(50, 417, 80, 40);
		this.add(lblShoots);

		JLabel lblSunkShips = new JLabel("SUNK SHIPS");
		lblSunkShips.setForeground(Color.YELLOW);
		lblSunkShips.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSunkShips.setHorizontalAlignment(SwingConstants.CENTER);
		lblSunkShips.setBounds(200, 417, 113, 40);
		this.add(lblSunkShips);
	}

	public void addListeners() {

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				boton[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						JButton pulsado = (JButton) evt.getSource();// linea vital
						String posicion = pulsado.getActionCommand();
						int x = Integer.parseInt(posicion.substring(0, 1));
						int y = Integer.parseInt(posicion.substring(1));
						pulsado.setEnabled(false);
						tirada(x, y, pulsado);
					}
				});
			}
		}
	}

	public static void createShips(int numShips, int[][] shipPlaces) {
		
		int cont_bar = 0;
		barcos = numShips;
		int r1, r2;
		while (cont_bar < barcos) {
			r1 = (int) (Math.random() * 8);
			r2 = (int) (Math.random() * 8);
			if (shipPlaces[r1][r2] != 1) {
				shipPlaces[r1][r2] = 1;
				cont_bar++;
			}
		}
	}

	public void tirada(int letra, int numero, JButton boton) {

		cont_disp++;
		if (shipPlaces[letra][numero] == 1) {
			cont_hund++;
			boton.setBackground(Color.WHITE);
			ImageIcon barco = new ImageIcon("src/fondoflota/boat2.png");
			iconoEscalaBarco = new ImageIcon(barco.getImage().getScaledInstance(30, -1, java.awt.Image.SCALE_DEFAULT));
			boton.setIcon(iconoEscalaBarco);
			if (cont_hund == barcos) {
				disparos.setText(String.valueOf(cont_disp));
				hundidos.setText(String.valueOf(cont_hund));
				mensaje.setVisible(true);
				mensaje.setBackground(Color.YELLOW);
				mensaje.setText("CONGRATULATIONS");
			} else if (cont_disp < tiros) {
				disparos.setText(String.valueOf(tiros - cont_disp));
				hundidos.setText(String.valueOf(cont_hund));
			} else {
				mensaje.setVisible(true);
				mensaje.setBackground(Color.YELLOW);
				mensaje.setText("SORRY TRY AGAIN");
				redibuja();
			}
		} else {
			boton.setBackground(Color.CYAN);
			if (cont_disp < tiros) {
				disparos.setText(String.valueOf(tiros - cont_disp));
				hundidos.setText(String.valueOf(cont_hund));
			} else {
				mensaje.setVisible(true);
				mensaje.setBackground(Color.YELLOW);
				mensaje.setText("SORRY TRY AGAIN");
				redibuja();
			}
		}
		disparos.setText(String.valueOf(tiros - cont_disp));
		hundidos.setText(String.valueOf(cont_hund));
	}

	public void redibuja() {

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (shipPlaces[i][j] == 1 && boton[i][j].isEnabled() == true) {
					boton[i][j].setBackground(Color.RED);
					boton[i][j].setIcon(iconoEscalaBarco);
				}
				boton[i][j].setEnabled(false);
			}
		}
	}

	public void empezar() {

		mensaje.setVisible(false);
		shipPlaces = new int[8][8];
		createShips(20, shipPlaces);
		mensaje.setText("");
		hundidos.setText("0");
		cont_disp = 0;
		cont_hund = 0;
		disparos.setText(String.valueOf(tiros));

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				boton[i][j].setEnabled(true);
				// boton[i][j].setBackground(new JButton().getBackground());
				boton[i][j].setBackground(Color.DARK_GRAY);
				boton[i][j].setIcon(null);
			}
		}
	}
}

