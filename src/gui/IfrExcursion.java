package gui;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import data.Excursion;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IfrExcursion extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private Excursion _excursion = null;
	
	/**
	 * Create the frame.
	 */
	public IfrExcursion() {
		setResizable(true);
		setClosable(true);
		setTitle("Excursión");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblMensaje = new JLabel("");
		lblMensaje.setForeground(new Color(0, 0, 255));
		lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensaje.setBounds(10, 0, 418, 13);
		getContentPane().add(lblMensaje);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombre.setBounds(39, 40, 50, 13);
		getContentPane().add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setHorizontalAlignment(SwingConstants.LEFT);
		txtNombre.setBounds(119, 37, 200, 19);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		JButton butGuardar = new JButton("Guardar");
		butGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(_excursion == null)
						_excursion = new Excursion(txtNombre.getText());
					else
						_excursion.setName(txtNombre.getText());
					_excursion.Save();
				}
				catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Ha ocurrido un error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		butGuardar.setBounds(39, 77, 85, 21);
		getContentPane().add(butGuardar);
	}
	
	public IfrExcursion(Excursion excursion) {
		this();
		if (excursion == null)
			throw new IllegalArgumentException("El parámetro 'excursion' no puede ser null.");
		txtNombre.setText(excursion.getName());
		_excursion = excursion;
	}
}
