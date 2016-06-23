/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.vtaVenta.datos;


import controller.acceso.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.vtaVenta.entidad.CETipoDescuento;


/**
 *
 * @author Joel Povis
 */
public class CDTipoDescuento
{
   
    

    public static ArrayList<CETipoDescuento> getAll()
    {
      Connection cnn= ConexionBD.obtenerConexion();
        try
        {

            ArrayList<CETipoDescuento> oLista=null;
                if(cnn!=null)
                {
                     CallableStatement cs= null;
                   cs = cnn.prepareCall("CALL VTASPRCNSTipoDescuento()");
                  //  System.out.println(cs);
                     ResultSet rs=cs.executeQuery();

                              if(rs.next())
                              {
                                oLista= new ArrayList<CETipoDescuento>();
                                CETipoDescuento oCETipoDescuento;

                                 do
                                  {
                                    oCETipoDescuento= new CETipoDescuento();
                                    oCETipoDescuento.setIdTipoDescuento(rs.getInt(1));
                                    oCETipoDescuento.setDescripcion(rs.getString(2));

                                    oLista.add(oCETipoDescuento);
                                  }while(rs.next());
                               }
                    cnn.close();
                 }

             return oLista;
        }catch(SQLException ex)
        {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "Error al cargar Tipo Descuento");
            return null;
        }
    }
}
