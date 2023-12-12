package gui;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import data.Lugar;

import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IfrLugares extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTable tabResultado;

	/**
	 * Create the frame.
	 */
	public IfrLugares(FrmMain frmMain) {
		setClosable(true);
		setResizable(true);
		setTitle("Lugares");
		setBounds(100, 100, 450, 300);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNombre = new JLabel("Nombre");
		panel.add(lblNombre);
		
		txtNombre = new JTextField();
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JButton butBuscar = new JButton("Buscar");
		butBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					tabResultado.setModel(
						new LugaresTableModel(Lugar.Search(txtNombre.getText())));
				}
				catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Ha ocurrido un error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(butBuscar);
		
		tabResultado = new JTable();
		tabResultado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Se activa con el doble clic sobre una fila.
				if (e.getClickCount() == 2) {
					
					int iRow = ((JTable)e.getSource()).getSelectedRow();
					Lugar lugar = ((LugaresTableModel)tabResultado.getModel()).getData(iRow);
					
					if (lugar != null)
						frmMain.ShowInternalFrame(new IfrLugar(lugar), 400, 27, 400, 192);
					
				}
			}
		});
		getContentPane().add(tabResultado, BorderLayout.CENTER);

	}

}
