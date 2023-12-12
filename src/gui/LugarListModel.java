package gui;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import data.Lugar;

public class LugarListModel 
	extends AbstractListModel<Lugar>
	implements ComboBoxModel<Lugar> 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final List<Lugar> _aData;
	private Object _oSelectedItem = null;
	
	LugarListModel(List<Lugar> aData) { _aData = aData; }

	@Override
	public int getSize() { return _aData.size(); }

	@Override
	public Lugar getElementAt(int index) { return _aData.get(index); }

	@Override
	public void setSelectedItem(Object anItem) { _oSelectedItem = anItem; }

	@Override
	public Object getSelectedItem() { return _oSelectedItem; }
}
