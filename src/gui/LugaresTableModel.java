package gui;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import data.Lugar;

public class LugaresTableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final List<Lugar> _aData;
	
	public LugaresTableModel(List<Lugar> aData) { _aData = aData; }
	
	@Override
	public int getRowCount() { return _aData.size(); }

	@Override
	public int getColumnCount() { return 1; }

	@Override
	public Object getValueAt(int iRow, int iColumn) {
		switch(iColumn) {
			case 0: return _aData.get(iRow).getName();
			default: throw new IllegalArgumentException("La columna " + iColumn + " no es válida."); 
		}
	}
	
	public Lugar getData(int iRow) { return _aData.get(iRow); }
}
