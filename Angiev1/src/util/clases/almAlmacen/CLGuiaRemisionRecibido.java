/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.clases.almAlmacen;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import util.clases.vtaVenta.ModeloTablaIngreso;
/**
 *
 * @author Luis Morales
 */
public class CLGuiaRemisionRecibido 
{
    private JTable oTabla;
   // private DefaultTableModel oModelo;
     
    public int colItem=0;
    public int colCodigo=1;
    public int colProducto=2;
    public int colCantidad=3;
    public int colUnidadMedida=4;

    private ModeloTablaGuiaRemision oModelo= new ModeloTablaGuiaRemision();

    public CLGuiaRemisionRecibido(JTable oTabla)
    {
        this.oTabla = oTabla;
        oTabla.setModel(oModelo);
        armarModelo();
        cargarEventoTablaGrupos(oTabla);
      //  oModelo=oTabla.getModel();    
    }

 public void setColumnEditable(int[] columns)
   {
        oModelo.setColumnEditable(columns);
   }
private void calcularItem()
    {
    for(int i =0 ; i<oTabla.getRowCount();i++)
    {
        oTabla.setValueAt(i+1, i, colItem);
    }
}

 private void cargarEventoTablaGrupos(final JTable tbl)
    {
        ((DefaultTableModel)tbl.getModel()).addTableModelListener(
         new TableModelListener()
          {
            public void tableChanged(TableModelEvent tme)
              {
                  {
                      eventoTablaGrupos(tme,tbl);

                  }
               }
          });

    }
    private void eventoTablaGrupos(TableModelEvent tme,JTable source)
    {
             int fil= oTabla.getSelectedRow();
             int col=oTabla.getSelectedColumn();

             if(tme.getType()==tme.INSERT||tme.getType()==tme.DELETE)
             {
                 calcularItem();
             }
               

//             }


    }

    private void armarModelo()
    {

        oTabla.getColumnModel().getColumn(0).setPreferredWidth(35);
        oTabla.getColumnModel().getColumn(colCodigo).setMinWidth(0);
        oTabla.getColumnModel().getColumn(colCodigo).setPreferredWidth(0);
        oTabla.getColumnModel().getColumn(colCodigo).setMaxWidth(0);
        oTabla.getColumnModel().getColumn(colProducto).setPreferredWidth(415);
        oTabla.getColumnModel().getColumn(colCantidad).setPreferredWidth(95);
        oTabla.getColumnModel().getColumn(colUnidadMedida).setPreferredWidth(100);

       

    }

 


 public void limpiarTabla()
    {
        int filas=oModelo.getRowCount();

        if(filas>0)
            for(int i=0;i<filas;i++)
            {
                oModelo.removeRow(0);
            }
    }
  public void QuitarFilaSel(int fila)
    {

                oModelo.removeRow(fila);
    }
    public void QuitarFila()
    {
        if(oTabla.getRowCount()>0){
            int rowSelect=oTabla.getSelectedRow();
                if(rowSelect!=-1){
                oModelo.removeRow(rowSelect);

                //((DefaultTableModel)oTabla.getModel()).removeRow(rowSelect);
                }
                else JOptionPane.showMessageDialog(null,"Seleccione una fila","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }



}
