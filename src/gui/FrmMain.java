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
			public void actionPerformed(ActionEvent e) { ShowInternalFrame(new IfrExcursion(), 10, 27, 400, 192); }
		});
		mnuNuevo.add(mitNuevaExcursion);
		
		JMenu mnuBuscar = new JMenu("Buscar");
		menuBar.add(mnuBuscar);
		
		JMenuItem mitBuscarExcursion = new JMenuItem("Excursión");
		mitBuscarExcursion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { ShowInternalFrame(new IfrExcursiones(getThis()), 25, 43, 400, 250); }
		});
		mnuBuscar.add(mitBuscarExcursion);
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
