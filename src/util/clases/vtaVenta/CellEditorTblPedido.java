/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.clases.vtaVenta;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author Joel
 */
public class CellEditorTblPedido implements CellEditorListener
{
        private JTable oTabla;
    public CellEditorTblPedido(JTable oJTable)
    {
      this.oTabla=oJTable;
    }



     private void CalcularCelda(int fil,int col)
    {
         
        String valor=oTabla.getValueAt(fil, col).toString();
        if(valor.equals(""))
            {
            oTabla.setValueAt(""+0, fil,col);
            }
         if(col==4||col==6)
            {            
            float cantidad=Float.parseFloat(oTabla.getValueAt(fil, 4).toString());
            float descuentodet=Float.parseFloat(oTabla.getValueAt(fil, 6).toString());
            oTabla.setValueAt(""+cantidad, fil, 4);
            oTabla.setValueAt(""+descuentodet, fil, 6);
            }
    }

    public void editingStopped(ChangeEvent e) {
       int fil= oTabla.getSelectedRow();
       int col=oTabla.getSelectedColumn();
       CalcularCelda(fil, col);
    }

    public void editingCanceled(ChangeEvent e) {
       
    }


}
