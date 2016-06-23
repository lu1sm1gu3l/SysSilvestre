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
import modelo.vtaVenta.entidad.CECondicion;


/**
 *
 * @author Joel Povis
 */
public class CDCondicion
{
   
    

    public static ArrayList<CECondicion> getAll()
    {
      Connection cnn= ConexionBD.obtenerConexion();
        try
        {

            ArrayList<CECondicion> oLista=null;
                if(cnn!=null)
                {
                     CallableStatement cs= null;
                   cs = cnn.prepareCall("CALL VTASPRCNSCondicion()");
                  //  System.out.println(cs);
                     ResultSet rs=cs.executeQuery();

                              if(rs.next())
                              {
                                oLista= new ArrayList<CECondicion>();
                                CECondicion oCECondicion;

                                 do
                                  {
                                    oCECondicion= new CECondicion();
                                    oCECondicion.setIdCondicion(rs.getInt(1));
                                    oCECondicion.setDescripcion(rs.getString(2));

                                    oLista.add(oCECondicion);
                                  }while(rs.next());
                               }
                    cnn.close();
                 }

             return oLista;
        }catch(SQLException ex)
        {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "Error al cargar la condicion");
            return null;
        }
    }
}
