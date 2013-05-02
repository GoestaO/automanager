package automanager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class Autobestand extends ArrayList<Auto> implements TableModel, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8876378735015093817L;
	private List<TableModelListener> tableListener = new ArrayList<TableModelListener>();

	@Override
	public void addTableModelListener(TableModelListener l) {
		tableListener.add(l);

	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return String.class;
		case 1:
			return Integer.class;
		case 2:
			return Integer.class;
		default:
			return null;
		}
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "Hersteller";
		case 1:
			return "Leistung";
		case 2:
			return "Anzahl Türen";
		default:
			return null;
		}
	}

	@Override
	public int getRowCount() {
		return this.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Auto a = (Auto) this.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return a.getHersteller();
		case 1:
			return a.getLeistung();
		case 2:
			return a.getAnzahlTueren();
		default:
			return null;
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		tableListener.remove(l);

	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
	}

	public void listen() {
		for (TableModelListener l : tableListener) {
			l.tableChanged(new TableModelEvent(this));
		}
	}

}
