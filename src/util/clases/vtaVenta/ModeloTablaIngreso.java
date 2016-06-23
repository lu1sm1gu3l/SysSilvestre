/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.clases.vtaVenta;



/**
 *
 * @author Morales
 */
public class ModeloTablaIngreso extends javax.swing.table.DefaultTableModel{


Class[] types;
private int[] columnsEdit={};
public ModeloTablaIngreso() {

          this.addColumn("N°");
          this.addColumn("Codigo");
          this.addColumn("Producto");
          this.addColumn("Cant. Compra");
          this.addColumn("Cantidad");
          this.addColumn("U. M.");
          this.addColumn("Costo");
          this.addColumn("Importe");
          this.addColumn("Almacen");
          this.addColumn("isEditable");
          types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Boolean.class
            };
          columnsEdit= new int[]{2,4,6};

    }
public boolean isCellEditable(int rowIndex, int columnIndex)
    {
    boolean iscellEdit=false;
          int size = columnsEdit.length;
                if(size!=0)
                {

                    Object obj_isedit=this.getValueAt(rowIndex, 9);
                        if(obj_isedit==null)
                        {
                            iscellEdit=true;
                        }else{
                                iscellEdit= Boolean.parseBoolean(obj_isedit.toString());
                            }
                            
                                    for(int i=0;i<size;i++)
                                    {

                                     if(columnsEdit[i]==columnIndex)
                                     {
                                         if(iscellEdit)
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
