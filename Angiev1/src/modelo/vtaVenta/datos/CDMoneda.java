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
import modelo.vtaVenta.entidad.CEMoneda;


/**

 */
public class CDMoneda
{
   
    

    public static ArrayList<CEMoneda> getAll(int pcns)
    {
      Connection cnn= ConexionBD.obtenerConexion();
        try
        {

            ArrayList<CEMoneda> oLista=null;
                if(cnn!=null)
                {
                     CallableStatement cs= null;
                   cs = cnn.prepareCall("CALL VTASPRCNSMoneda("+pcns+")");
                     ResultSet rs=cs.executeQuery();

                              if(rs.next())
                              {
                                oLista= new ArrayList<CEMoneda>();
                                CEMoneda oCEMoneda;

                                 do
                                  {
                                    oCEMoneda= new CEMoneda();

                                    oCEMoneda.setId_moneda(rs.getInt(1));
                                    oCEMoneda.setDescripcion(rs.getString(2));
                                    oCEMoneda.setAbreviatura(rs.getString(3));
                                   

                                    oLista.add(oCEMoneda);
                                  }while(rs.next());
                               }
                    cnn.close();
                 }

             return oLista;
        }catch(SQLException ex)
        {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "Error al cargar Moneda");
            return null;
        }
    }
     public static ArrayList<CEMoneda> getMonedaConTipoCambio(int pcns)
    {
      Connection cnn= ConexionBD.obtenerConexion();
        try
        {

            ArrayList<CEMoneda> oLista=null;
                if(cnn!=null)
                {
                     CallableStatement cs= null;
                   cs = cnn.prepareCall("CALL VTASPRCNSMoneda("+pcns+")");
                     ResultSet rs=cs.executeQuery();

                              if(rs.next())
                              {
                                oLista= new ArrayList<CEMoneda>();
                                CEMoneda oCEMoneda;

                                 do
                                  {
                                    oCEMoneda= new CEMoneda();
                                    oCEMoneda.setId_moneda(rs.getInt(1));
                                    oCEMoneda.setDescripcion(rs.getString(2));
                                    oCEMoneda.setAbreviatura(rs.getString(3));
                                    oCEMoneda.setUltimoMontoVentaMN(rs.getFloat(4));
                                    oCEMoneda.setUltimoMontoVentaDolar(rs.getFloat(5));
                                     oCEMoneda.setToString(oCEMoneda.getDescripcion());

                                    oLista.add(oCEMoneda);
                                  }while(rs.next());
                               }
                    cnn.close();
                 }

             return oLista;
        }catch(SQLException ex)
        {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "Error al cargar Moneda");
            return null;
        }
    }
}
