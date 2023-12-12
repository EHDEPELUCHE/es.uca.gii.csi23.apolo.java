package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class FrmMain {

	private JFrame _frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMain window = new FrmMain();
					window._frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public FrmMain() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(
				UIManager.getSystemLookAndFeelClassName());
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		_frame = new JFrame();
		_frame.setTitle("Viajes Ciudad Reloj");
		_frame.setBounds(100, 100, 450, 300);
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		_frame.setJMenuBar(menuBar);
		
		JMenu mnuNuevo = new JMenu("Nuevo");
		menuBar.add(mnuNuevo);
		
		JMenuItem mitNuevaExcursion = new JMenuItem("Excursión");
		mitNuevaExcursion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { try {
				ShowInternalFrame(new IfrExcursion(), 10, 27, 500, 192);
			} catch (IOException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} }
		});
		mnuNuevo.add(mitNuevaExcursion);
		
		JMenuItem mitNuevoLugar = new JMenuItem("Lugar");
		mitNuevoLugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { ShowInternalFrame(new IfrLugar(), 27, 44, 400, 192); }
		});
		mnuNuevo.add(mitNuevoLugar);
		
		JMenu mnuBuscar = new JMenu("Buscar");
		menuBar.add(mnuBuscar);
		
		JMenuItem mitBuscarExcursion = new JMenuItem("Excursión");
		mitBuscarExcursion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { try {
				ShowInternalFrame(new IfrExcursiones(getThis()), 25, 43, 400, 250);
			} catch (IOException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} }
		});
		mnuBuscar.add(mitBuscarExcursion);
		
		JMenuItem mitBuscarLugar = new JMenuItem("Lugar");
		mitBuscarLugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { ShowInternalFrame(new IfrLugares(getThis()), 43, 61, 400, 250); }
		});
		mnuBuscar.add(mitBuscarLugar);
		
		_frame.getContentPane().setLayout(null);
	}
	
	public void ShowInternalFrame(
		JInternalFrame ifr, int iX, int iY, int iWidth, int iHeight) {
		ifr.setBounds(iX, iY, iWidth, iHeight);
		_frame.getContentPane().add(ifr);
		ifr.setVisible(true);
	}

	public FrmMain getThis() { return this; }
}
