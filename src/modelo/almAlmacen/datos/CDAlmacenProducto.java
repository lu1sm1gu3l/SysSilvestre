package modelo.almAlmacen.datos;

import controller.acceso.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.almAlmacen.entidad.CEAlmacenProducto;

public class CDAlmacenProducto
{
    

       public static List<CEAlmacenProducto>  consultarAlamcenPorProducto(long idProducto,int opcion,int pIdAlmacen)
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try {
               List<CEAlmacenProducto> ListaAlmacenProducto = new ArrayList<CEAlmacenProducto>();
               String sql="call ALMSPRCNSAlmacenProducto("+idProducto+","+opcion+","+pIdAlmacen+")";
               Statement sentencia=conexion.createStatement();
               ResultSet rs=sentencia.executeQuery(sql);
               if (rs!=null)
                {
                while(rs.next())
                {
                    CEAlmacenProducto oCEAlmacenProducto=new CEAlmacenProducto();
                    oCEAlmacenProducto.setIdAlmacen(rs.getInt(1));
                    oCEAlmacenProducto.setStockReal(rs.getFloat(2));
                    oCEAlmacenProducto.setDescripcion(rs.getString(3));
                     oCEAlmacenProducto.setCostoUnitario(rs.getFloat(4));
                    ListaAlmacenProducto.add(oCEAlmacenProducto);
                }

                }
               return ListaAlmacenProducto;
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
    public static ArrayList<CEAlmacenProducto> listarAlmacen(int tipoCns,String pDato,int pIdAlmacen)
    {
      Connection cnn= ConexionBD.obtenerConexion();
        try
        {

            ArrayList<CEAlmacenProducto> oLista=null;
                if(cnn!=null)
                {
                     CallableStatement cs= null;
                     cs = cnn.prepareCall("CALL ALMSPRCNSStockProductoPorAlmacen(?,?,?)");

                     cs.setInt(1,tipoCns);
                     cs.setString(2,pDato);
                     cs.setInt(3, pIdAlmacen);
                     ResultSet rs=cs.executeQuery();
                      if(rs.next())
                      {
                        oLista= new ArrayList<CEAlmacenProducto>();
                        CEAlmacenProducto oCEAlmacenProducto;

                         do
                          {
                            oCEAlmacenProducto= new CEAlmacenProducto();
                            oCEAlmacenProducto.setCodigo(rs.getString(1));
                            oCEAlmacenProducto.setDescripcion(rs.getString(2));
                            oCEAlmacenProducto.setUMB(rs.getString(3));
                            oCEAlmacenProducto.setStockMinimo(rs.getFloat(4));
                            oCEAlmacenProducto.setStockMaximo(rs.getFloat(5));
                            oCEAlmacenProducto.setStockReal(rs.getFloat(6));
                            oCEAlmacenProducto.setCostoUnitario(rs.getFloat(7));
                            oCEAlmacenProducto.setInventario(rs.getFloat(8));
                            oCEAlmacenProducto.setIdAlmacen(rs.getInt(9));
                            oCEAlmacenProducto.setAlmacen(rs.getString(10));
                            oCEAlmacenProducto.setIdProducto(rs.getLong(11));
                            oCEAlmacenProducto.setUltimoCostocompra(rs.getFloat(12));
                            oCEAlmacenProducto.setIdUnidadBase(rs.getInt(13));
                            oLista.add(oCEAlmacenProducto);
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

     public static ArrayList<CEAlmacenProducto> listarAlmacenProducto(int tipoCns,String pDato,int pIdAlmacen)
    {
      Connection cnn= ConexionBD.obtenerConexion();
        try
        {

            ArrayList<CEAlmacenProducto> oLista=null;
                if(cnn!=null)
                {
                     CallableStatement cs= null;
                     cs = cnn.prepareCall("CALL ALMSPRCNSDetalleProducto(?,?,?)");

                     cs.setInt(1,tipoCns);
                     cs.setString(2,pDato);
                     cs.setInt(3, pIdAlmacen);
                     ResultSet rs=cs.executeQuery();
                      if(rs.next())
                      {
                        oLista= new ArrayList<CEAlmacenProducto>();
                        CEAlmacenProducto oCEAlmacenProducto;

                         do
                          {
                            oCEAlmacenProducto= new CEAlmacenProducto();
                            oCEAlmacenProducto.setCodigo(rs.getString(1));
                            oCEAlmacenProducto.setDescripcion(rs.getString(2));
                            oCEAlmacenProducto.setUnidad_medida(rs.getString(3));
                            oCEAlmacenProducto.setStockMinimo(rs.getFloat(4));
                            oCEAlmacenProducto.setStockMaximo(rs.getFloat(5));
                            oCEAlmacenProducto.setStockReal(rs.getFloat(6));
                            oCEAlmacenProducto.setCostoUnitario(rs.getFloat(7));
                            oCEAlmacenProducto.setInventario(rs.getFloat(8));
                            oCEAlmacenProducto.setIdAlmacen(rs.getInt(9));
                            oCEAlmacenProducto.setAlmacen(rs.getString(10));
                            oCEAlmacenProducto.setIdProducto(rs.getLong(11));
                            oCEAlmacenProducto.setUltimoCostocompra(rs.getFloat(12));
                            oCEAlmacenProducto.setIdUnidadMedida(rs.getInt(13));
                            oCEAlmacenProducto.setPrecio(rs.getFloat(14));
                            oCEAlmacenProducto.setEquivalenciaUMB(rs.getFloat(15));
                            oCEAlmacenProducto.setSiNoImpuesto(rs.getBoolean(16));
                            oCEAlmacenProducto.setPercepcion(rs.getBoolean(17));
                            oLista.add(oCEAlmacenProducto);
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

    public static ArrayList<CEAlmacenProducto> listarStockPorUnidadPedido(CEAlmacenProducto oCEAlmacenProducto)
    {
      Connection cnn= ConexionBD.obtenerConexion();
        try
        {

            ArrayList<CEAlmacenProducto> oLista=null;
                if(cnn!=null)
                {
                     CallableStatement cs= null;
                     cs = cnn.prepareCall("CALL ALMSPRCNSUnidadPedido(?,?,?,?,?)");

                     cs.setLong(1,oCEAlmacenProducto.getIdProducto());
                     cs.setFloat(2,oCEAlmacenProducto.getCostoUnitario());
                     cs.setFloat(3, oCEAlmacenProducto.getUltimoCostocompra());
                     cs.setFloat(4, oCEAlmacenProducto.getStockReal());
                     cs.setFloat(5, oCEAlmacenProducto.getIdAlmacen());
                     ResultSet rs=cs.executeQuery();
                      if(rs.next())
                      {
                        oLista= new ArrayList<CEAlmacenProducto>();
                        CEAlmacenProducto vCEAlmacenProducto;

                         do
                          {
                            vCEAlmacenProducto= new CEAlmacenProducto();
                            vCEAlmacenProducto.setUnidad_medida(rs.getString(1));
                            vCEAlmacenProducto.setStockReal(rs.getFloat(2));
                            vCEAlmacenProducto.setCostoUnitario(rs.getFloat(3));
                            vCEAlmacenProducto.setUltimoCostocompra(rs.getFloat(4));
                            vCEAlmacenProducto.setEquivalenciaUMB(rs.getFloat(5));
                           

                            oLista.add(vCEAlmacenProducto);
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
    public static CEAlmacenProducto ConsultarAlmcenProd(int tipoCns,long id,int pIdAlmacen)
    {
      Connection cnn= ConexionBD.obtenerConexion();
        try
        {

            CEAlmacenProducto oCEAlmacenProducto= null;
                if(cnn!=null)
                {
                     CallableStatement cs= null;
                     cs = cnn.prepareCall("CALL ALMSPRCNSStockProductoPorAlmacen(?,?,?)");

                     cs.setInt(1,tipoCns);
                     cs.setLong(2,id);
                     cs.setInt(3, pIdAlmacen);
                     ResultSet rs=cs.executeQuery();

                      if(rs.next())
                      {


                         do
                          {
                            oCEAlmacenProducto= new CEAlmacenProducto();
                            oCEAlmacenProducto.setCodigo(rs.getString(1));
                            oCEAlmacenProducto.setDescripcion(rs.getString(2));
                            oCEAlmacenProducto.setUMB(rs.getString(3));
                            oCEAlmacenProducto.setStockMinimo(rs.getFloat(4));
                            oCEAlmacenProducto.setStockMaximo(rs.getFloat(5));
                            oCEAlmacenProducto.setStockReal(rs.getFloat(6));
                            oCEAlmacenProducto.setCostoUnitario(rs.getFloat(7));
                            oCEAlmacenProducto.setInventario(rs.getFloat(8));
                            oCEAlmacenProducto.setIdAlmacen(rs.getInt(9));
                            oCEAlmacenProducto.setAlmacen(rs.getString(10));
                            oCEAlmacenProducto.setIdProducto(rs.getLong(11));
                            oCEAlmacenProducto.setUltimoCostocompra(rs.getFloat(12));
                            oCEAlmacenProducto.setIdUnidadBase(rs.getInt(13));

                          }
                          while(rs.next());
                       }
                           cnn.close();
                 }
             return oCEAlmacenProducto;
        }
        catch(SQLException ex)
        {
            System.out.println(ex);
            return null;
        }
    }


    public static boolean UPDAlmacenProducto(CEAlmacenProducto oCEAlmacenProducto)
    {
        Connection conexion;
        conexion = ConexionBD.obtenerConexion();//
        try
        {
            CallableStatement sentenciaPedido;
            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            String sql= "{call ALMSPRUpdateAlmacenProducto(?,?,?,?,?,?,?,?)}";
            sentenciaPedido = conexion.prepareCall(sql);
            sentenciaPedido.setFloat(1, oCEAlmacenProducto.getStockReal());
            sentenciaPedido.setFloat(2, oCEAlmacenProducto.getStockMinimo());
            sentenciaPedido.setFloat(3, oCEAlmacenProducto.getStockMaximo());
            sentenciaPedido.setInt(4, oCEAlmacenProducto.getIdAlmacen());
            sentenciaPedido.setLong(5, oCEAlmacenProducto.getIdProducto());
            sentenciaPedido.setFloat(6, oCEAlmacenProducto.getCostoUnitario());
            sentenciaPedido.setInt(7, oCEAlmacenProducto.getIdUnidadBase());
            sentenciaPedido.setFloat(8, oCEAlmacenProducto.getUltimoCostocompra());
            sentenciaPedido.executeUpdate();
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
            catch (SQLException sqlExcep)
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
            catch (SQLException e)
            {
                System.out.print(e);
            }
        }

    }

     public static boolean FinalizarPeriodo(String pDescripcion)
    {
        Connection conexion;
        conexion = ConexionBD.obtenerConexion();//
        try
        {
            CallableStatement sentenciaPedido;
            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            String sql= "{call ALMSPRUpdateAlmacenProducto(?)}";
            sentenciaPedido = conexion.prepareCall(sql);
            sentenciaPedido.setString(1, pDescripcion);


            sentenciaPedido.executeUpdate();
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
            catch (SQLException sqlExcep)
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
            catch (SQLException e)
            {
                System.out.print(e);
            }
        }

    }
}

