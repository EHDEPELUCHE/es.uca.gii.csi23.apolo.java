package gui;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import data.Excursion;

public class ExcursionesTableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final List<Excursion> _aData;
	
	public ExcursionesTableModel(List<Excursion> aData) { _aData = aData; }
	
	@Override
	public int getRowCount() { return _aData.size(); }

	@Override
	public int getColumnCount() { return 2; }

	@Override
	public Object getValueAt(int iRow, int iColumn) {
		switch(iColumn) {
			case 0: return _aData.get(iRow).getName();
			case 1: return _aData.get(iRow).getLugar();
			default: throw new IllegalArgumentException("La columna " + iColumn + " no es válida."); 
		}
	}
	
	public Excursion getData(int iRow) { return _aData.get(iRow); }
}
