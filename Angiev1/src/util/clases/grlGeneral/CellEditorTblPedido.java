/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.clases.grlGeneral;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author Joel
 */
public class CellEditorTblPedido implements CellEditorListener
{
        private JTable oJTable;
        private int c=0;

    public void setJTable(JTable oJTable)
    {
      this.oJTable=oJTable;
    }

    public void editingStopped(ChangeEvent e)
    {
         int col=oJTable.getSelectedColumn();

          System.out.println("Estoy Seleciondo fila" + oJTable.getSelectedRow()+" columna"+oJTable.getSelectedColumn());
    }

    public void editingCanceled(ChangeEvent e)
    {
         int col=oJTable.getSelectedColumn();
      //  if(col==4)
      // {
       //   System.out.println("Estoy Seleciondo 2 " + oJTable.getSelectedRow()+" "+oJTable.getSelectedColumn());
     //  }
    }

    public void ValidarCalcularCelda(int fil,int col)
    {


    }


}
