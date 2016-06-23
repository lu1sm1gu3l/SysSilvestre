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
import modelo.vtaVenta.entidad.CETipoComprobante;


/**
 *
 * @author Joel Povis
 */
public class CDTipoComprobante
{
   
    

    public static ArrayList<CETipoComprobante> getAll(int opcion)
    {
      Connection cnn= ConexionBD.obtenerConexion();
        try
        {

            ArrayList<CETipoComprobante> oLista=null;
                if(cnn!=null)
                {
                     CallableStatement cs= null;
                   cs = cnn.prepareCall("CALL VTASPRCNSTipoComprobante("+opcion+")");
                  //  System.out.println(cs);
                     ResultSet rs=cs.executeQuery();

                              if(rs.next())
                              {
                                oLista= new ArrayList<CETipoComprobante>();
                                CETipoComprobante oTipoComprobante;

                                 do
                                  {
                                    oTipoComprobante= new CETipoComprobante();
                                    oTipoComprobante.setIdTipoComprobante(rs.getInt(1));
                                    oTipoComprobante.setDescripcion(rs.getString(2));

                                    oLista.add(oTipoComprobante);
                                  }while(rs.next());
                               }
                    cnn.close();
                 }

             return oLista;
        }catch(SQLException ex)
        {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "Error al cargar Tipo Comprobante");
            return null;
        }
    }
}
