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
import modelo.vtaVenta.entidad.CESucursal;


/**
 *
 * @author Joel Povis
 */
public class CDSucursal
{
   
    

    public static ArrayList<CESucursal> getSucursal( int pOpcion)
    {
      Connection cnn= ConexionBD.obtenerConexion();
        try
        {

            ArrayList<CESucursal> oLista=null;
                if(cnn!=null)
                {
                     CallableStatement cs= null;
                   cs = cnn.prepareCall("CALL VTASPRCNSSucursal("+pOpcion+")");
                  //  System.out.println(cs);
                     ResultSet rs=cs.executeQuery();

                              if(rs.next())
                              {
                                oLista= new ArrayList<CESucursal>();
                                CESucursal oCESucursal;

                                 do
                                  {
                                    oCESucursal= new CESucursal();
                                    oCESucursal.setIdSucursal(rs.getInt(1));
                                    oCESucursal.setDescripcion(rs.getString(2));

                                    oLista.add(oCESucursal);
                                  }while(rs.next());
                               }
                    cnn.close();
                 }

             return oLista;
        }catch(SQLException ex)
        {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "Error al cargar los registros");
            return null;
        }
    }
}
