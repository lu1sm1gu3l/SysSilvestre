package util.clases.almAlmacen;

import util.clases.cmrComercial.*;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import modelo.cmrComercial.entidad.CECliente;



public class CLEquivalencia
{
 private JTable oTabla;

 private DefaultTableModel oModelo = new DefaultTableModel()
  {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }

  };

  public CLEquivalencia(JTable oTabla)
    {
        this.oTabla = oTabla;
       
    }
   public DefaultTableModel getoModelo()
    {
        return oModelo;
    }

    public void llenarDatosATabla(JTable oTabla,
                                  ArrayList<CECliente> oLista)
    {
        limpiarTabla();
        Vector oVector;
       
        CECliente oCECliente;
        if(oLista!=null)
        for(int i=0;i<oLista.size();i++)
        {
            oCECliente=oLista.get(i);
            oVector = new Vector();
            oVector.add(i+1);
            oVector.add(oCECliente);
            oVector.add(oCECliente.getDNI());
            oVector.add(oCECliente.getRUC());
            oVector.add(oCECliente.getDireccion());
            oModelo.addRow(oVector);
        }
    }

         public void armarModelo()
    {
        oModelo.addColumn("Nº");
        oModelo.addColumn("Unidad Base");
        oModelo.addColumn("Cant.");
        oModelo.addColumn("Unidad Equi.");
        oModelo.addColumn("Cant.");

      oTabla.setModel(oModelo);

      TableColumn oCol = oTabla.getColumnModel().getColumn(0);
      oCol.setPreferredWidth(80);

      oCol = oTabla.getColumnModel().getColumn(1);
      oCol.setPreferredWidth(100);

      oCol = oTabla.getColumnModel().getColumn(2);
      oCol.setPreferredWidth(80);

      oCol = oTabla.getColumnModel().getColumn(3);
      oCol.setPreferredWidth(100);
      
    }

    public void setoModelo(DefaultTableModel oModelo) {
        this.oModelo = oModelo;
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
    
    
    

   
  }
