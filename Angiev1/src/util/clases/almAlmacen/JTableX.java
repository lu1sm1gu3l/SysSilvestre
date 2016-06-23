package util.clases.almAlmacen;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.text.*;
import org.jdesktop.swingx.JXTable;

// JTable customizado para eventos de clic,F2 y edicion comun.
public class JTableX extends JTable
{
	private boolean isSelectAllForMouseEvent = false;
	private boolean isSelectAllForActionEvent = false;
	private boolean isSelectAllForKeyEvent = false;

    public JTableX()
    {
        this(null, null, null);
    //    setColumnModel(new XTableColumnModel());
    }

   
    public JTableX(TableModel dm)
    {
        this(dm, null, null);
    }

   
    public JTableX(TableModel dm, TableColumnModel cm)
    {
        this(dm, cm, null);
    }

   
    public JTableX(TableModel dm, TableColumnModel cm, ListSelectionModel sm)
    {
        super(dm, cm, sm);
    }

    public JTableX(int numRows, int numColumns)
    {
        this(new DefaultTableModel(numRows, numColumns));
    }

    public JTableX(Vector rowData, Vector columnNames)
    {
        this(new DefaultTableModel(rowData, columnNames));
    }

   
    public JTableX(final Object[][] rowData, final Object[] columnNames)
    {
        super(rowData, columnNames);
    }

    public boolean editCellAt(int row, int column, EventObject e)
	{
		boolean result = super.editCellAt(row, column, e);

		if (isSelectAllForMouseEvent
		||  isSelectAllForActionEvent
		||  isSelectAllForKeyEvent)
		{
			selectAll(e);
		}

		return result;
	}

	
	private void selectAll(EventObject e)
	{
		final Component editor = getEditorComponent();

		if (editor == null
		|| ! (editor instanceof JTextComponent))
			return;

		if (e == null)
		{
			((JTextComponent)editor).selectAll();
			return;
		}

		if (e instanceof KeyEvent && isSelectAllForKeyEvent)
		{
			((JTextComponent)editor).selectAll();
			return;
		}

		if (e instanceof ActionEvent && isSelectAllForActionEvent)
		{
			((JTextComponent)editor).selectAll();
			return;
		}

		if (e instanceof MouseEvent && isSelectAllForMouseEvent)
		{
			SwingUtilities.invokeLater(new Runnable()
			{
				public void run()
				{
					((JTextComponent)editor).selectAll();
				}
			});
		}
	}

	public void setSelectAllForEdit(boolean isSelectAllForEdit)
	{
		setSelectAllForMouseEvent( isSelectAllForEdit );
		setSelectAllForActionEvent( isSelectAllForEdit );
		setSelectAllForKeyEvent( isSelectAllForEdit );
	}

	
	public void setSelectAllForMouseEvent(boolean isSelectAllForMouseEvent)
	{
		this.isSelectAllForMouseEvent = isSelectAllForMouseEvent;
	}

	
	public void setSelectAllForActionEvent(boolean isSelectAllForActionEvent)
	{
		this.isSelectAllForActionEvent = isSelectAllForActionEvent;
	}

	public void setSelectAllForKeyEvent(boolean isSelectAllForKeyEvent)
	{
		this.isSelectAllForKeyEvent = isSelectAllForKeyEvent;
	}


}  
