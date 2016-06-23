package util.clases.almAlmacen;

import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import modelo.almAlmacen.entidad.CEMarca;



public class CLMarca
{
 private JTable oTabla;

 private DefaultTableModel oModelo = new DefaultTableModel()
  {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }

  };

  public CLMarca(JTable oTabla)
    {
        this.oTabla = oTabla;
       
    }
   public DefaultTableModel getoModelo()
    {
        return oModelo;
    }

    public void llenarDatosATabla(JTable oTabla,
                                  ArrayList<CEMarca> oLista)
    {
        limpiarTabla();
        Vector oVector;
       
        CEMarca oCEMarca;
        if(oLista!=null)
        for(int i=0;i<oLista.size();i++)
        {
            oCEMarca=oLista.get(i);
            oVector = new Vector();
            oVector.add(i+1);
            oVector.add(oCEMarca);
            oModelo.addRow(oVector);
        }
    }

         public void armarModelo()
    {
        oModelo.addColumn("Nº");
        oModelo.addColumn("Marca");

      oTabla.setModel(oModelo);

      TableColumn oCol = oTabla.getColumnModel().getColumn(0);
      oCol.setPreferredWidth(40);

      oCol = oTabla.getColumnModel().getColumn(1);
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
