package gui;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import data.Excursion;
import data.Lugar;

import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JComboBox;

public class IfrExcursiones extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTable tabResultado;
	JComboBox<Lugar> cmbLugar = new JComboBox<Lugar>();

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public IfrExcursiones(FrmMain frmMain) throws IOException, SQLException {
		setClosable(true);
		setResizable(true);
		setTitle("Excursiones");
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
						new ExcursionesTableModel(Excursion.Search(txtNombre.getText(), 
								(cmbLugar.getSelectedItem() == null) ? null : 
									cmbLugar.getSelectedItem().toString())));
				}
				catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Ha ocurrido un error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JLabel lblLugar = new JLabel("Lugar");
		panel.add(lblLugar);
		
		cmbLugar.setModel(new LugarListModel(Lugar.Search(null)));
		cmbLugar.setEditable(true);
		panel.add(cmbLugar);
		panel.add(butBuscar);
		
		tabResultado = new JTable();
		tabResultado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Se activa con el doble clic sobre una fila.
				if (e.getClickCount() == 2) {
					int iRow = ((JTable)e.getSource()).getSelectedRow();
					Excursion excursion = ((ExcursionesTableModel)tabResultado.getModel()).getData(iRow);
					if (excursion != null)
						try {
							frmMain.ShowInternalFrame(new IfrExcursion(excursion), 400, 27, 500, 192);
						} catch (IOException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}
			}
		});
		getContentPane().add(tabResultado, BorderLayout.CENTER);
	}

}
