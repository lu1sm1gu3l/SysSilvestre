/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.almAlmacen.datos;

import controller.acceso.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.almAlmacen.entidad.CEProducto;
import modelo.almAlmacen.entidad.CEProductoPrecio;
import modelo.almAlmacen.entidad.CEUnidadMedidaProducto;

/**
 *
 * @author Luiggi
 */
public class CDProductoPrecio {

    public static List<CEProductoPrecio>  consultarPrecioProducto(long idProducto)
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try {
               List<CEProductoPrecio> ListaPrecioProducto = new ArrayList<CEProductoPrecio>();
               String sql="call ALMSPRCNSProductoPrecioPorProducto("+idProducto+")";
               Statement sentencia=conexion.createStatement();
               ResultSet rs=sentencia.executeQuery(sql);
               if (rs!=null)
                {
                while(rs.next())
                {
                    CEProductoPrecio oCEPrecioProducto=new CEProductoPrecio();
                    oCEPrecioProducto.setIdUnidadMedidaVenta(rs.getInt(1));
                    oCEPrecioProducto.setRangoInicial(rs.getFloat(2));
                    oCEPrecioProducto.setRangoFinal(rs.getFloat(3));
                    oCEPrecioProducto.setPrecioUnitario(rs.getFloat(4));
                    oCEPrecioProducto.setRangoFaltante(rs.getString(5));
                    ListaPrecioProducto.add(oCEPrecioProducto);
                }

                }
               return ListaPrecioProducto;
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

      public static List<CEProductoPrecio>  consultarPrecioProductoUnidad(long IdMedidaProducto,int pIdunidad)
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try {
               List<CEProductoPrecio> ListaPrecioProducto = new ArrayList<CEProductoPrecio>();
               String sql="call ALMSPRCNSProductoPrecioPorIdUnidad("+IdMedidaProducto+","+pIdunidad+")";
               Statement sentencia=conexion.createStatement();
               ResultSet rs=sentencia.executeQuery(sql);
               if (rs!=null)
                {

                while(rs.next())
                {
                    CEProductoPrecio oCEPrecioProducto=new CEProductoPrecio();
                    oCEPrecioProducto.setIdProductoPrecio(rs.getInt(1));
                    oCEPrecioProducto.setRangoInicial(rs.getFloat(2));
                    oCEPrecioProducto.setRangoFinal(rs.getFloat(3));
                    oCEPrecioProducto.setPrecioUnitario(rs.getFloat(4));
                    oCEPrecioProducto.setRangoFaltante(rs.getString(5));
                    oCEPrecioProducto.setIdUnidadMedidaVenta(pIdunidad);
                    ListaPrecioProducto.add(oCEPrecioProducto);
                }

                }
               return ListaPrecioProducto;
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
    public static List<CEProductoPrecio>  consultarPrecioUnidadMedidaProducto(long IdMedidaProducto)
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try {
               List<CEProductoPrecio> ListaPrecioProducto = new ArrayList<CEProductoPrecio>();
               String sql="call ALMSPRCNSProductoPrecioPorUnidadProducto("+IdMedidaProducto+")";
               Statement sentencia=conexion.createStatement();
               ResultSet rs=sentencia.executeQuery(sql);
               if (rs!=null)
                {
                   
                while(rs.next())
                {
                    CEProductoPrecio oCEPrecioProducto=new CEProductoPrecio();
                    oCEPrecioProducto.setIdProductoPrecio(rs.getInt(1));
                    oCEPrecioProducto.setRangoInicial(rs.getFloat(2));
                    oCEPrecioProducto.setRangoFinal(rs.getFloat(3));
                    oCEPrecioProducto.setPrecioUnitario(rs.getFloat(4));
                    oCEPrecioProducto.setRangoFaltante(rs.getString(5));
                    oCEPrecioProducto.setIdProducto(rs.getLong(6));
                    oCEPrecioProducto.setIdUnidadMedidaProducto(rs.getLong(7));
                    oCEPrecioProducto.setIdUnidadMedidaVenta(rs.getInt(8));
                    oCEPrecioProducto.setAbm(0);
                    ListaPrecioProducto.add(oCEPrecioProducto);
                }

                }
               return ListaPrecioProducto;
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
      public static boolean ABMProductoPrecio(List<CEProductoPrecio> oLstCEProductoPrecio,CEProducto oCEProducto,List<CEUnidadMedidaProducto> oLstUnidadMedida)
        {
           Connection conexion;
           conexion = ConexionBD.obtenerConexion();
            try
              {
                CallableStatement sentenciaABMProductoPrecio;
                CallableStatement sentenciadelete;
                conexion.setAutoCommit(false);
                conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
                CallableStatement sentenciaUPDUnidadMedidaProducto;
                for(CEUnidadMedidaProducto oCEUnidadMedidaProducto:oLstUnidadMedida)
                {
                    String sql_= "{call ALMSPRUPDPrecioUnidadMedidaProducto(?,?)}";
                    sentenciaUPDUnidadMedidaProducto = conexion.prepareCall(sql_);
                    sentenciaUPDUnidadMedidaProducto.setLong(1, oCEUnidadMedidaProducto.getIdUnidadMedidaProducto());
                    sentenciaUPDUnidadMedidaProducto.setFloat(2, oCEUnidadMedidaProducto.getPrecioReferencia());
                    sentenciaUPDUnidadMedidaProducto.executeUpdate();

//                    String sqldel= "{call ALMSPRDELProductoPrecio(?,?)}";
//                    sentenciadelete = conexion.prepareCall(sqldel);
//                    sentenciadelete.setLong(1,oCEProducto.getIdProducto());
//                    sentenciadelete.setLong(2,oCEUnidadMedidaProducto.getIdUnidadMedidaProducto());
//                    sentenciadelete.executeUpdate();
                }

                 

                for(CEProductoPrecio oCDProductoPrecio:oLstCEProductoPrecio)
                {
                    String sql= "{call ALMSPRABMProductoPrecio(?,?,?,?,?,?,?,?,?)}";
                    sentenciaABMProductoPrecio = conexion.prepareCall(sql);
                    sentenciaABMProductoPrecio.setInt(1,oCDProductoPrecio.getAbm());
                    sentenciaABMProductoPrecio.setLong(2,oCDProductoPrecio.getIdProductoPrecio());
                    sentenciaABMProductoPrecio.setLong(3,oCDProductoPrecio.getIdUnidadMedidaProducto());
                    sentenciaABMProductoPrecio.setInt(4,oCDProductoPrecio.getIdUnidadMedidaVenta());
                    sentenciaABMProductoPrecio.setLong(5,oCDProductoPrecio.getIdProducto());
                    sentenciaABMProductoPrecio.setFloat(6,oCDProductoPrecio.getRangoInicial());
                    sentenciaABMProductoPrecio.setFloat(7,oCDProductoPrecio.getRangoFinal());
                    sentenciaABMProductoPrecio.setString(8, oCDProductoPrecio.getRangoFaltante());
                    sentenciaABMProductoPrecio.setFloat(9,oCDProductoPrecio.getPrecioUnitario());
                    sentenciaABMProductoPrecio.executeUpdate();
                }
                CallableStatement sentenciaUPDPrecioReferencia;
                String sql= "{call ALMSPRUPDPrecioReferencia(?,?)}";
                sentenciaUPDPrecioReferencia = conexion.prepareCall(sql);
                sentenciaUPDPrecioReferencia.setLong(2,oCEProducto.getIdProducto());
                sentenciaUPDPrecioReferencia.setFloat(1,oCEProducto.getPrecio());
                sentenciaUPDPrecioReferencia.executeUpdate();
                
                conexion.commit();
                return true;
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
                return false;
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
