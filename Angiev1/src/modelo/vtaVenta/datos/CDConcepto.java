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
import modelo.vtaVenta.entidad.CEConcepto;


/**

 */
public class CDConcepto
{
   
    

    public static ArrayList<CEConcepto> getAll(int opcion)
    {
      Connection cnn= ConexionBD.obtenerConexion();
        try
        {

            ArrayList<CEConcepto> oLista=null;
                if(cnn!=null)
                {
                     CallableStatement cs= null;
                   cs = cnn.prepareCall("CALL ALMSPRCNSConcepto("+opcion+")");
                   
                  //  System.out.println(cs);
                     ResultSet rs=cs.executeQuery();

                              if(rs.next())
                              {
                                oLista= new ArrayList<CEConcepto>();
                                CEConcepto oCEConcepto;

                                 do
                                  {
                                    oCEConcepto= new CEConcepto();
                                    oCEConcepto.setIdConcepto(rs.getInt(1));
                                    oCEConcepto.setDescripcion(rs.getString(2));

                                    oLista.add(oCEConcepto);
                                  }while(rs.next());
                               }
                    cnn.close();
                 }

             return oLista;
        }catch(SQLException ex)
        {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "Error al cargar Concepto");
            return null;
        }
    }
}
