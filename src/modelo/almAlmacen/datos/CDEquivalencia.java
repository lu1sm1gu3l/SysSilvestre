/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.almAlmacen.datos;


import controller.acceso.ConexionBD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.almAlmacen.entidad.CEEquivalenciaUnidad;



/**
 *
 * @author Katya
 */
public class CDEquivalencia {

     public static ArrayList<CEEquivalenciaUnidad>  consultarUnidadMedidaVentasPorIdProducto(int IdProducto)
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try {
               ArrayList<CEEquivalenciaUnidad> oListaUnidades = new ArrayList<CEEquivalenciaUnidad>();
               String sql="{call ALMSPRCNSEquivalenciaPorIdProducto("+IdProducto+")}";
               Statement sentencia=conexion.createStatement();
               ResultSet rs=sentencia.executeQuery(sql);
               if (rs!=null)
                {
                while(rs.next())
                {
                    CEEquivalenciaUnidad oCEEquivalenciaUnidad=new CEEquivalenciaUnidad();
                    oCEEquivalenciaUnidad.setIdEquivalenciaUnidad(rs.getInt(1));
                    oCEEquivalenciaUnidad.setIdProducto(rs.getInt(2));
                    oCEEquivalenciaUnidad.setIdUnidadBase(rs.getInt(3));
                    oCEEquivalenciaUnidad.setCantidadBase(rs.getFloat(4));
                    oCEEquivalenciaUnidad.setIdUnidadPedido(rs.getInt(5));
                    oCEEquivalenciaUnidad.setCantidadPedido(rs.getFloat(6));
                    oCEEquivalenciaUnidad.setUnidadEquivalencia(rs.getString(7));
                    oListaUnidades.add(oCEEquivalenciaUnidad);
                }
                return oListaUnidades;
                }
               return null;
            }
         catch (SQLException e)
            {
             System.out.print(e);
             return null;
            }
        catch (Exception e)
            {
            System.out.print(e);
            return null;
            }
        finally
            {
                try
                {
                conexion.close();
                }
                catch (SQLException ex)
                {
                System.out.print("No se puede cerrar la conexion");
                }
            }
      }

     

}
