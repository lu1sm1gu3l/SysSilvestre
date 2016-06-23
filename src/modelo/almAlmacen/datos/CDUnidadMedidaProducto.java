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
import java.util.List;
import modelo.almAlmacen.entidad.CEUnidadMedidaProducto;

/**
 *
 * @author Katya
 */
public class CDUnidadMedidaProducto {

     public static List<CEUnidadMedidaProducto>  consultarEquivalenciaPorProducto(long idProducto)
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try {
               List<CEUnidadMedidaProducto> ListaStockProducto = new ArrayList<CEUnidadMedidaProducto>();
               String sql="call ALMSPRCNSUnidadProductoPorProducto("+idProducto+")";
               Statement sentencia=conexion.createStatement();
               ResultSet rs=sentencia.executeQuery(sql);
               if (rs!=null)
                {//  IdAlmacenProducto, IdUnidadBase, StockMinimo, StockReal, CostoUnitario, tst.CostoUnitario*1.15, Inventario, IdProducto, Descripcion
                while(rs.next())
                {
                    CEUnidadMedidaProducto oCEUnidadMedidaProducto=new CEUnidadMedidaProducto();
                    oCEUnidadMedidaProducto.setIdProducto(idProducto);
                    oCEUnidadMedidaProducto.setIdUnidadMedidaVenta(rs.getInt(2));
                    oCEUnidadMedidaProducto.setTipoUnidad(rs.getString(3));
                    oCEUnidadMedidaProducto.setEquivalenciaConBase(rs.getFloat(4));
                    oCEUnidadMedidaProducto.setSiNoUnidadBase(rs.getInt(5));
                    oCEUnidadMedidaProducto.setSiNoUnidadMasComercial(rs.getInt(6));
                    ListaStockProducto.add(oCEUnidadMedidaProducto);
                }

                }
               return ListaStockProducto;
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
