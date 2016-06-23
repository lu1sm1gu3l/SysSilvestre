package util.clases.vtaVenta;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/*
 * CustomTableCellRenderer.java
 *
 * Created on 28 de Junio de 2010, 03:30
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 *
 * @author Joel Povis
 */
public class RenderTablaPedido extends DefaultTableCellRenderer

{
private int mEstado=0;
private int colchek=12;
  //  protected static Border noFocusBorder = new EmptyBorder(2, 3, 3,2);
    /*
        Deseamos colorear las celdas, para tener una mejor presentacion del horario academico
        Es por ello que se a utilizado el metodo DefaultTableCellRenderer, para colorear cada celda
    */

    @Override
   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,boolean hasFocus, int row, int column) 
    {
           // Creamos el objeto cell que representa a todas las celdas
     Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

    

  
                if(table.getValueAt(row,colchek)!=null)
                {
                    boolean isSelccionado=Boolean.parseBoolean(table.getValueAt(row,colchek)+"");
                    if(isSelccionado)
                    {
                        cell.setBackground(new Color(198,235,210));
                        return cell;
                    }
                }
       if(isSelected)
                {
                    cell.setBackground(table.getSelectionBackground());
                    cell.setForeground(table.getSelectionForeground());



                }
                else
                {
                    cell.setBackground(table.getBackground());
                    cell.setForeground(table.getForeground());
                }
     

      return cell;

    }


    public void setmEstado(int pEstado) {
        this.mEstado = pEstado;
    }


}
