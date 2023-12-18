package gui;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import data.Lugar;

public class LugarListModel 
	extends AbstractListModel<Lugar>
	implements ComboBoxModel<Lugar> {
	private static final long serialVersionUID = 1L;
	private final List<Lugar> _aData;
	private Object _oSelectedItem = null;
	
	LugarListModel(List<Lugar> aData) { _aData = aData; }

	@Override
	public int getSize() { return _aData.size(); }

	@Override
	public Lugar getElementAt(int iIndex) { return _aData.get(iIndex); }

	@Override
	public void setSelectedItem(Object oItem) { _oSelectedItem = oItem; }

	@Override
	public Object getSelectedItem() { return _oSelectedItem; }
}
