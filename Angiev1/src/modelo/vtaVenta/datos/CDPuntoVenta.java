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
import modelo.vtaVenta.entidad.CEPuntoVenta;

/**
 *
 * @author Luiggi
 */
public class CDPuntoVenta {

    public static ArrayList<CEPuntoVenta> listarPuntoVenta(int pIdSucursal,boolean sinoCaja,int opcion)
    {
      Connection cnn= ConexionBD.obtenerConexion();
        try
        {

            ArrayList<CEPuntoVenta> oLista=null;
                if(cnn!=null)
                {
                     CallableStatement cs= null;
                     cs = cnn.prepareCall("CALL VTASPRCNSPuntoVentaSucursal(?,?,?)");

                     cs.setBoolean(1,sinoCaja);
                     cs.setInt(2,pIdSucursal);
                     cs.setInt(3,opcion);
                     
                     ResultSet rs=cs.executeQuery();
                      if(rs.next())
                      {
                        oLista= new ArrayList<CEPuntoVenta>();
                        CEPuntoVenta oCEPuntoVenta;

                         do
                          {
                            oCEPuntoVenta= new CEPuntoVenta();
                            oCEPuntoVenta.setIdPuntoVenta(rs.getInt(1));
                            oCEPuntoVenta.setDescrpcion(rs.getString(2));
                            oCEPuntoVenta.setIP(rs.getString(3));
                            oCEPuntoVenta.setSinocaja(rs.getBoolean(4));
                            oCEPuntoVenta.setSucursal(rs.getString(5));
                            oLista.add(oCEPuntoVenta);
                          }
                          while(rs.next());
                       }
                           cnn.close();
                 }
             return oLista;
        }
        catch(SQLException ex)
        {
            System.out.println(ex);
            return null;
        }
    }


      public static int ABMPuntoVenta(int pIntAMB,CEPuntoVenta oCPuntoVenta)
        {
           Connection conexion;
           conexion = ConexionBD.obtenerConexion();
            try
              {
                CallableStatement sentenciaABMUnidadMedidaVenta;
                conexion.setAutoCommit(false);
                conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

                String sql= "{call VTASPRABMPuntoVenta(?,?,?,?,?,?)}";
                sentenciaABMUnidadMedidaVenta = conexion.prepareCall(sql);
                sentenciaABMUnidadMedidaVenta.setInt(1, pIntAMB);
                sentenciaABMUnidadMedidaVenta.setInt(2, oCPuntoVenta.getIdPuntoVenta());
                sentenciaABMUnidadMedidaVenta.setString(3, oCPuntoVenta.getIP());
                sentenciaABMUnidadMedidaVenta.setString(4, oCPuntoVenta.getDescrpcion());
                sentenciaABMUnidadMedidaVenta.setInt(5, oCPuntoVenta.getIdSucursal());
                sentenciaABMUnidadMedidaVenta.setBoolean(6, oCPuntoVenta.isSinocaja());
                sentenciaABMUnidadMedidaVenta.executeUpdate();
               conexion.commit();

               return 1;
                }
            catch (Exception e)
              {
                System.out.print(e);
                  try
                    {
                      conexion.rollback();
                    }
                  catch(SQLException sqlExcep)
                    {
                      sqlExcep.printStackTrace();
                    }
                return 0;
              }
           finally
            {
              try
                {
                 conexion.close();
                }
              catch(SQLException e)
                {
                  System.out.print(e);
                }
            }

      }


}
