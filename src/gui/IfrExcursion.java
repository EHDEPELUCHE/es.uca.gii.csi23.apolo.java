package gui;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import data.Excursion;
import data.Lugar;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class IfrExcursion extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	JComboBox<Lugar> cmbLugar = new JComboBox<Lugar>();
	private Excursion _excursion = null;
	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public IfrExcursion() throws IOException, SQLException {
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
		lblNombre.setBounds(55, 40, 50, 13);
		getContentPane().add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setHorizontalAlignment(SwingConstants.LEFT);
		txtNombre.setBounds(105, 37, 165, 19);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		cmbLugar.setModel(new LugarListModel(Lugar.Search(null)));
		cmbLugar.setBounds(348, 36, 80, 21);
		getContentPane().add(cmbLugar);		
		
		JButton butGuardar = new JButton("Guardar");
		butGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if ((Lugar) cmbLugar.getModel().getSelectedItem() == null)
						throw new IllegalStateException("La excursión necesita un lugar.");
					if(_excursion == null)
						_excursion = new Excursion(txtNombre.getText(), (Lugar) cmbLugar.getModel().getSelectedItem());
					else {
						_excursion.setName(txtNombre.getText());
						_excursion.setLugar((Lugar) cmbLugar.getModel().getSelectedItem());
					}
					_excursion.Save();
				}
				catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Ha ocurrido un error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		butGuardar.setBounds(39, 77, 85, 21);
		getContentPane().add(butGuardar);
		
		JLabel lblLugar = new JLabel("Lugar");
		lblLugar.setBounds(312, 40, 26, 13);
		getContentPane().add(lblLugar);
	}
	
	public IfrExcursion(Excursion excursion) throws IOException, SQLException {
		this();
		if (excursion == null)
			throw new IllegalArgumentException("El parámetro 'excursion' no puede ser null.");
		txtNombre.setText(excursion.getName());
		cmbLugar.getModel().setSelectedItem(excursion.getLugar());
		_excursion = excursion;
	}
}
