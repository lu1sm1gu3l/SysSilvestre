package util.clases.vtaVenta.JTreeTableJerarquia;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.TreeModel;

public class JTreeTable extends JTable
{
    protected TreeTableCellRenderer tree;
    public JTreeTable()
    {
	super();

    }
    public void generarReporte(String fecha1,String fecha2,int codigo,JPanel pnl,JLabel lbl)
    {
        JerarquiaModel oFileSystemModel=new JerarquiaModel(fecha1, fecha2, codigo);
	tree = new TreeTableCellRenderer(oFileSystemModel);
	super.setModel(new TreeTableModelAdapter(oFileSystemModel, tree,pnl,lbl));
	tree.setSelectionModel(new DefaultTreeSelectionModel()
        {
	   {
                setSelectionModel(listSelectionModel);
	   }
	});
       tree.setRowHeight(22);
       setRowHeight(22);
       tree.setRootVisible(false);
       tree.setShowsRootHandles(true);
       setDefaultRenderer(TreeTableModel.class, tree);
       setDefaultRenderer(Float.class, new DefaultTableCellRenderer()
        {

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cp=super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                 if(isSelected)
		setBackground(table.getSelectionBackground());
	    else
		if(row%2==0)
                {
                setBackground(new Color(204,204,204));
                }
                else
                {
                setBackground(Color.WHITE);
                }
                ((JLabel)cp).setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }


            }
            )

             ;
	setDefaultEditor(TreeTableModel.class, new TreeTableCellEditor());
        setShowGrid(false);
	setIntercellSpacing(new Dimension(0, 0));
        getColumnModel().getColumn(0).setPreferredWidth(600);
        getColumnModel().getColumn(1).setPreferredWidth(120);
        getColumnModel().getColumn(2).setPreferredWidth(120);
    }

    public int getEditingRow() {
        return (getColumnClass(editingColumn) == TreeTableModel.class) ? -1 : editingRow;  
    }

    public class TreeTableCellRenderer extends JTree implements TableCellRenderer {

	protected int visibleRow;
   
	public TreeTableCellRenderer(TreeModel model)
        {
	    super(model);
            this.setRootVisible(false);            
            this.setCellRenderer(new IconTableTreeRenderer());
	}

	public void setBounds(int x, int y, int w, int h)
        {
	    super.setBounds(x, 0, w, JTreeTable.this.getHeight());
	}

	public void paint(Graphics g)
        {
	    g.translate(0, -visibleRow * getRowHeight());
	    super.paint(g);
	}

	public Component getTableCellRendererComponent(JTable table,
						       Object value,
						       boolean isSelected,
						       boolean hasFocus,
						       int row, int column)
        {

            if(isSelected)
		setBackground(table.getSelectionBackground());
	    else
		if(row%2==0)
                {
                setBackground(new Color(204,204,204));
                }
                else
                {
                setBackground(Color.WHITE);
                }
	    visibleRow = row;
	    return this;
        }

    }


    public class TreeTableCellEditor extends AbstractCellEditor implements TableCellEditor
    {
	public Component getTableCellEditorComponent(JTable table, Object value,
						     boolean isSelected, int r, int c)
        {
	    return tree;
	}
    }




}

