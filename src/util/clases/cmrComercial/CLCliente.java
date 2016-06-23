package util.clases.cmrComercial;

import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import modelo.cmrComercial.entidad.CECliente;


/**
 *
 * @author Joel Povis
 */
public class CLCliente
{
 private JTable oTabla;

 private DefaultTableModel oModelo = new DefaultTableModel()
  {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }

  };

  public CLCliente(JTable oTabla)
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
        oModelo.addColumn("Item");
        oModelo.addColumn("Cliente");
        oModelo.addColumn("DNI");
        oModelo.addColumn("RUC");
        oModelo.addColumn("Direccion");

      oTabla.setModel(oModelo);

      TableColumn oCol = oTabla.getColumnModel().getColumn(0);
      oCol.setPreferredWidth(50);

      oCol = oTabla.getColumnModel().getColumn(1);
      oCol.setPreferredWidth(400);

      oCol = oTabla.getColumnModel().getColumn(2);
      oCol.setPreferredWidth(110);

      oCol = oTabla.getColumnModel().getColumn(3);
      oCol.setPreferredWidth(130);

      oCol = oTabla.getColumnModel().getColumn(4);
      oCol.setPreferredWidth(400);
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
