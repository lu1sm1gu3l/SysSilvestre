/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.clases.almAlmacen;

import util.clases.vtaVenta.*;



/**
 *
 * @author Morales
 */
public class ModeloTablaGuiaRemision extends javax.swing.table.DefaultTableModel{


Class[] types;
private int[] columnsEdit={};
public ModeloTablaGuiaRemision() {

          this.addColumn("N°");
          this.addColumn("Codigo");
          this.addColumn("Producto");
          this.addColumn("Cantidad");
          this.addColumn("U. M.");
          types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class,
            };
          columnsEdit= new int[]{2,4};

    }
public boolean isCellEditable(int rowIndex, int columnIndex)
    {
          int size = columnsEdit.length;
                if(size!=0)
                {
                            
                    for(int i=0;i<size;i++)
                    {

                     if(columnsEdit[i]==columnIndex)
                     {

                         return true;

                     }
                    }
                      
                    
                }

        return false;
    }

   public void setColumnEditable(int[] columns)
   {
        this.columnsEdit=columns;
   }

    public Class getColumnClass(int columnIndex)
    {
        return types [columnIndex];
    }

}
