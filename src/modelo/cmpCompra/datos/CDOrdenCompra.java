/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.cmpCompra.datos;

import controller.acceso.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.cmpCompra.entidad.CEOrdenCompra;
import modelo.cmpCompra.entidad.CEOrdenCompraDetalle;
import modelo.cmpCompra.entidad.CEOrdenCompraMatriz;
import view.FrmSistemaMenu;

/**
 *
 * @author Joel
 */
public class CDOrdenCompra
{

    public static CEOrdenCompraMatriz InsOrdenCompra(CEOrdenCompraMatriz oCEOrdenCompra)
    {
        Connection conexion;
        conexion = ConexionBD.obtenerConexion();//5 elimina
        try
        {
            CallableStatement sentenciaOrdenCompra;
            CallableStatement sentenciaOrdenCompraDetalle;
            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            String sql= "{call CMPSPRINSOrdenCompra(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            sentenciaOrdenCompra = conexion.prepareCall(sql);
            sentenciaOrdenCompra.setLong(1, oCEOrdenCompra.getIdOrdenCompra());
            sentenciaOrdenCompra.setLong(2, oCEOrdenCompra.getIdProveedor());
            sentenciaOrdenCompra.setLong(3, oCEOrdenCompra.getIdSucursal());
            sentenciaOrdenCompra.setFloat(4, oCEOrdenCompra.getDescuento());
            sentenciaOrdenCompra.setFloat(5, oCEOrdenCompra.getSubtotalNeto());
            sentenciaOrdenCompra.setInt(6, oCEOrdenCompra.getIdUltimoEstado());
            sentenciaOrdenCompra.setInt(7, oCEOrdenCompra.getIdTipoComprobante());
            sentenciaOrdenCompra.setInt(8, oCEOrdenCompra.getIdCondicion());
            sentenciaOrdenCompra.setInt(9, oCEOrdenCompra.getIdMoneda());
            sentenciaOrdenCompra.setInt(10, oCEOrdenCompra.getIdTipoDescuento());
            sentenciaOrdenCompra.setLong(11, 0);
            sentenciaOrdenCompra.setString(12,"");
            sentenciaOrdenCompra.setInt(13, oCEOrdenCompra.getIdEmpleado());
            sentenciaOrdenCompra.setInt(14, oCEOrdenCompra.getIdUsuario());
            sentenciaOrdenCompra.setFloat(15, oCEOrdenCompra.getTipoCambio());
            sentenciaOrdenCompra.setFloat(16, oCEOrdenCompra.getSubtotalBruto());
            sentenciaOrdenCompra.setFloat(17, oCEOrdenCompra.getDescuentoEnSubtotal());
            sentenciaOrdenCompra.setFloat(18, oCEOrdenCompra.getDescuentoTotal());
            sentenciaOrdenCompra.setFloat(19, oCEOrdenCompra.getDescuentoValores());
            sentenciaOrdenCompra.setFloat(20, oCEOrdenCompra.getIGVActual());
            sentenciaOrdenCompra.setFloat(21, oCEOrdenCompra.getIGV());
            sentenciaOrdenCompra.setFloat(22, oCEOrdenCompra.getSubtotalNetoSinIGV());
            sentenciaOrdenCompra.setFloat(23, oCEOrdenCompra.getMontoTotal());
            sentenciaOrdenCompra.setFloat(24, oCEOrdenCompra.getTotalPagar());
            sentenciaOrdenCompra.setBoolean(25, oCEOrdenCompra.isConPercepcion());
            sentenciaOrdenCompra.setFloat(26, oCEOrdenCompra.getMontoPercepcion());

            sentenciaOrdenCompra.executeUpdate();
            long id_OrdenCompra = sentenciaOrdenCompra.getInt(11);
            oCEOrdenCompra.setIdOrdenCompra(id_OrdenCompra);
            String CodigoOrdenCompra = sentenciaOrdenCompra.getString(12);
            oCEOrdenCompra.setCodigo(CodigoOrdenCompra);
            for (CEOrdenCompraDetalle oCEOrdenCompraDetalle : oCEOrdenCompra.getLstOrdenCompraDetalle())
            {
                String sql_ = "{call CMPSPRINSOrdenCompraDetalle(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                long id_OrdenCompraDetalle = oCEOrdenCompraDetalle.getIdOrdenCompraDetalle();
                sentenciaOrdenCompraDetalle = conexion.prepareCall(sql_);
                sentenciaOrdenCompraDetalle.setLong(1, id_OrdenCompraDetalle);
                sentenciaOrdenCompraDetalle.setLong(2, id_OrdenCompra);
                sentenciaOrdenCompraDetalle.setFloat(3, oCEOrdenCompraDetalle.getCantidad());
                sentenciaOrdenCompraDetalle.setFloat(4, oCEOrdenCompraDetalle.getDescuento());
                sentenciaOrdenCompraDetalle.setLong(5, oCEOrdenCompraDetalle.getIdProducto());
                sentenciaOrdenCompraDetalle.setInt(6, oCEOrdenCompraDetalle.getIdTipoDescuento());
                sentenciaOrdenCompraDetalle.setFloat(7, oCEOrdenCompraDetalle.getPrecio());
                sentenciaOrdenCompraDetalle.setFloat(8, oCEOrdenCompraDetalle.getIdUnidadMedidaVenta());
                sentenciaOrdenCompraDetalle.setFloat(9, oCEOrdenCompraDetalle.getDescuentoEnValores());
                sentenciaOrdenCompraDetalle.setFloat(10, oCEOrdenCompraDetalle.getImportelSinDescuento());
                sentenciaOrdenCompraDetalle.setFloat(11, oCEOrdenCompraDetalle.getImporteConDescuento());
                sentenciaOrdenCompraDetalle.setFloat(12, oCEOrdenCompraDetalle.getExonerado());
                sentenciaOrdenCompraDetalle.setBoolean(13, oCEOrdenCompraDetalle.isSinoImpuesto());
                sentenciaOrdenCompraDetalle.setFloat(14, oCEOrdenCompraDetalle.getPrecioConDesc());
                sentenciaOrdenCompraDetalle.setFloat(15, oCEOrdenCompraDetalle.getImporteSinDescuentoConIgv());
                sentenciaOrdenCompraDetalle.executeUpdate();
                oCEOrdenCompraDetalle.setIdOrdenCompraDetalle(sentenciaOrdenCompraDetalle.getLong(1));

            }
            conexion.commit();
            return oCEOrdenCompra;
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
            return null;
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

    public static int UPDOrdenCompra(CEOrdenCompra oCEOrdenCompra)
    {
        Connection conexion;
        conexion = ConexionBD.obtenerConexion();//5 elimina
        try
        {
            CallableStatement sentenciaOrdenCompra;
            CallableStatement sentenciaOrdenCompraDetalle;
            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            String sql= "{call CMPSPRUPDOrdenCompra(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            sentenciaOrdenCompra = conexion.prepareCall(sql);
            sentenciaOrdenCompra.setLong(1, oCEOrdenCompra.getIdOrdenCompra());
            sentenciaOrdenCompra.setLong(2, oCEOrdenCompra.getIdProveedor());
            sentenciaOrdenCompra.setLong(3, oCEOrdenCompra.getIdSucursal());
            sentenciaOrdenCompra.setFloat(4, oCEOrdenCompra.getDescuento());
            sentenciaOrdenCompra.setFloat(5, oCEOrdenCompra.getSubtotalNetoSinIGV());
            sentenciaOrdenCompra.setInt(6, oCEOrdenCompra.getIdUltimoEstado());
            sentenciaOrdenCompra.setInt(7, oCEOrdenCompra.getIdTipoComprobante());
            sentenciaOrdenCompra.setInt(8, oCEOrdenCompra.getIdCondicion());
            sentenciaOrdenCompra.setInt(9, oCEOrdenCompra.getIdMoneda());
            sentenciaOrdenCompra.setInt(10, oCEOrdenCompra.getIdTipoDescuento());
            sentenciaOrdenCompra.setInt(11, oCEOrdenCompra.getIdEmpleado());
            sentenciaOrdenCompra.setInt(12, oCEOrdenCompra.getIdUsuario());
            sentenciaOrdenCompra.setFloat(13, oCEOrdenCompra.getTipoCambio());
            sentenciaOrdenCompra.setFloat(14, oCEOrdenCompra.getSubtotalBruto());
            sentenciaOrdenCompra.setFloat(15, oCEOrdenCompra.getDescuentoEnSubtotal());
            sentenciaOrdenCompra.setFloat(16, oCEOrdenCompra.getDescuentoTotal());
            sentenciaOrdenCompra.setFloat(17, oCEOrdenCompra.getDescuentoValores());
            sentenciaOrdenCompra.setFloat(18, oCEOrdenCompra.getIGVActual());
            sentenciaOrdenCompra.setFloat(19, oCEOrdenCompra.getIGV());
            sentenciaOrdenCompra.setFloat(20, oCEOrdenCompra.getSubtotalNetoSinIGV());
            sentenciaOrdenCompra.setFloat(21, oCEOrdenCompra.getMontoTotal());
            sentenciaOrdenCompra.setFloat(22, oCEOrdenCompra.getTotalPagar());
            sentenciaOrdenCompra.setBoolean(23, oCEOrdenCompra.isConPercepcion());
            sentenciaOrdenCompra.setFloat(24, oCEOrdenCompra.getMontoPercepcion());

            sentenciaOrdenCompra.executeUpdate();
            long id_OrdenCompra = oCEOrdenCompra.getIdOrdenCompra();
            for (CEOrdenCompraDetalle oCEOrdenCompraDetalle : oCEOrdenCompra.getLstOrdenCompraDetalle())
            {
                String sql_ = "{call CMPSPRABMOrdenCompraDetalle(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                
                sentenciaOrdenCompraDetalle = conexion.prepareCall(sql_);
                sentenciaOrdenCompraDetalle.setLong(1, oCEOrdenCompraDetalle.getIdOrdenCompraDetalle());
                sentenciaOrdenCompraDetalle.setLong(2, id_OrdenCompra);
                sentenciaOrdenCompraDetalle.setFloat(3, oCEOrdenCompraDetalle.getCantidad());
                sentenciaOrdenCompraDetalle.setFloat(4, oCEOrdenCompraDetalle.getDescuento());
                sentenciaOrdenCompraDetalle.setLong(5, oCEOrdenCompraDetalle.getIdProducto());
                sentenciaOrdenCompraDetalle.setInt(6, oCEOrdenCompraDetalle.getIdTipoDescuento());
                sentenciaOrdenCompraDetalle.setFloat(7, oCEOrdenCompraDetalle.getPrecio());
                sentenciaOrdenCompraDetalle.setFloat(8, oCEOrdenCompraDetalle.getIdUnidadMedidaVenta());
                sentenciaOrdenCompraDetalle.setFloat(9, oCEOrdenCompraDetalle.getDescuentoEnValores());
                sentenciaOrdenCompraDetalle.setFloat(10, oCEOrdenCompraDetalle.getImportelSinDescuento());
                sentenciaOrdenCompraDetalle.setFloat(11, oCEOrdenCompraDetalle.getImporteConDescuento());
                sentenciaOrdenCompraDetalle.setFloat(12, oCEOrdenCompraDetalle.getExonerado());
                sentenciaOrdenCompraDetalle.setBoolean(13, oCEOrdenCompraDetalle.isSinoImpuesto());
                sentenciaOrdenCompraDetalle.setFloat(14, oCEOrdenCompraDetalle.getPrecioConDesc());
                sentenciaOrdenCompraDetalle.setFloat(15, oCEOrdenCompraDetalle.getImporteSinDescuentoConIgv());
                int abm=oCEOrdenCompraDetalle.getIdAbm();
                sentenciaOrdenCompraDetalle.setFloat(16, oCEOrdenCompraDetalle.getIdAbm());
                sentenciaOrdenCompraDetalle.executeUpdate();

            }
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
            catch (SQLException sqlExcep)
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
            catch (SQLException e)
            {
                System.out.print(e);
            }
        }

    }

    public static int AnularOrdenCompra(CEOrdenCompra oCEOrdenCompra)
    {
        Connection conexion;
        conexion = ConexionBD.obtenerConexion();//5 elimina
        try
        {
            CallableStatement sentenciaOrdenCompra;

            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            String sql = "{call CMPSPRAnularOrdenCompra(?,?,?,?,?)}";
            sentenciaOrdenCompra = conexion.prepareCall(sql);
            sentenciaOrdenCompra.setLong(1, oCEOrdenCompra.getIdOrdenCompra());
            sentenciaOrdenCompra.setInt(2, 4);
            sentenciaOrdenCompra.setInt(3,FrmSistemaMenu.oCEUsuario.getIdUsuario());
            sentenciaOrdenCompra.setInt(4, oCEOrdenCompra.getIdEmpleado());
            sentenciaOrdenCompra.setString(5, oCEOrdenCompra.getObservacion());
            sentenciaOrdenCompra.executeUpdate();
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
            catch (SQLException sqlExcep)
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
            catch (SQLException e)
            {
                System.out.print(e);
            }
        }
    }
 public static int AprobarOrdenCompra(CEOrdenCompra oCEOrdenCompra)
    {
        Connection conexion;
        conexion = ConexionBD.obtenerConexion();//5 elimina
        try
        {
            CallableStatement sentenciaOrdenCompra;

            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            String sql = "{call CMPSPRAprobarOrdenCompra(?,?,?,?,?)}";
            sentenciaOrdenCompra = conexion.prepareCall(sql);
            sentenciaOrdenCompra.setLong(1, oCEOrdenCompra.getIdOrdenCompra());
            sentenciaOrdenCompra.setInt(2, 4);
            sentenciaOrdenCompra.setInt(3, FrmSistemaMenu.oCEUsuario.getIdUsuario());
            sentenciaOrdenCompra.setInt(4, oCEOrdenCompra.getIdEmpleado());
            sentenciaOrdenCompra.setString(5, oCEOrdenCompra.getObservacion());
            sentenciaOrdenCompra.executeUpdate();
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
            catch (SQLException sqlExcep)
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
            catch (SQLException e)
            {
                System.out.print(e);
            }
        }
    }
    


    public static List<CEOrdenCompraMatriz> ListarOrdenComprasPorCodigo(String pDato,int opcion,long id,int idestado, int idtipocomp,String pFechaFin)
    {
        Connection cnn = ConexionBD.obtenerConexion();
        try
        {
            List<CEOrdenCompraMatriz> lista=new ArrayList<CEOrdenCompraMatriz>();
            CEOrdenCompraMatriz oCEOrdenCompraMatriz = null;
            if (cnn != null)
            {
                CallableStatement cs = null;
                cs = cnn.prepareCall("{CALL  CMPSPRCNSOrdenCompraPorCodigo(?,?,?,?,?,?)}");
                cs.setInt(1, opcion);
                cs.setString(2, pDato);
                cs.setLong(3, id);
                cs.setInt(4, idestado);
                cs.setInt(5, idtipocomp);
                cs.setString(6, pFechaFin);
                ResultSet rs = cs.executeQuery();
                while (rs.next())
                {
                    oCEOrdenCompraMatriz = new CEOrdenCompraMatriz();
                    oCEOrdenCompraMatriz.setIdOrdenCompra(rs.getLong(1));
                    oCEOrdenCompraMatriz.setCodigo(rs.getString(2));
                    oCEOrdenCompraMatriz.setFecha(rs.getString(3));
                    oCEOrdenCompraMatriz.setTotalPagar(rs.getFloat(4));
                    oCEOrdenCompraMatriz.setMoneda(rs.getString(5));
                    oCEOrdenCompraMatriz.setProveedor(rs.getString(6));
                    oCEOrdenCompraMatriz.setTipoComprobante(rs.getString(7));
                    oCEOrdenCompraMatriz.setUltimoEstado(rs.getString(8));
                    lista.add(oCEOrdenCompraMatriz);


                }
                
            }
            return lista;
        }
        catch (SQLException ex)
        {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, "Error al cargar el OrdenCompra ");
            return null;
        }
        finally
        {
            try
            {
                cnn.close();
            }
            catch (SQLException ex)
            {
                Logger.getLogger(CDOrdenCompra.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

   public static CEOrdenCompraMatriz ConsultarOrdenComprasPorId(long id)
    {
        Connection cnn = ConexionBD.obtenerConexion();
        try
        {
            CEOrdenCompraMatriz oCEOrdenCompraMatriz = null;
            if (cnn != null)
            {
                CallableStatement cs = null;
                cs = cnn.prepareCall("{CALL  CMPSPRCNSOrdenCompraPorCodigo(?,?,?,?,?,?)}");
                cs.setInt(1, 1);
                cs.setString(2, null);
                cs.setLong(3, id);
                cs.setInt(4, 0);
                cs.setInt(5, 0);
                cs.setString(6, null);
                ResultSet rs = cs.executeQuery();
                while (rs.next())
                {
                    oCEOrdenCompraMatriz = new CEOrdenCompraMatriz();
                    oCEOrdenCompraMatriz.setIdOrdenCompra(rs.getLong(1));
                    oCEOrdenCompraMatriz.setIdProveedor(rs.getInt(2));
                    oCEOrdenCompraMatriz.setIdSucursal(rs.getInt(3));
                    oCEOrdenCompraMatriz.setFecha(rs.getString(4));
                    oCEOrdenCompraMatriz.setDescuento(rs.getFloat(5));
                    oCEOrdenCompraMatriz.setIdTipoComprobante(rs.getInt(6));
                    oCEOrdenCompraMatriz.setIdCondicion(rs.getInt(7));
                    oCEOrdenCompraMatriz.setIdMoneda(rs.getInt(8));
                    oCEOrdenCompraMatriz.setIdDescuento(rs.getInt(9));
                    oCEOrdenCompraMatriz.setProveedor(rs.getString(10));
                    oCEOrdenCompraMatriz.setDireccion(rs.getString(11));
                    oCEOrdenCompraMatriz.setRUC(rs.getString(12));
                    oCEOrdenCompraMatriz.setEmpleado(rs.getString(13));
                    oCEOrdenCompraMatriz.setIdUltimoEstado(rs.getInt(14));
                    oCEOrdenCompraMatriz.setUltimoEstado(rs.getString(15));
                    oCEOrdenCompraMatriz.setSubtotalBruto(rs.getFloat(16));
                    oCEOrdenCompraMatriz.setDescuentoEnSubtotal(rs.getFloat(17));
                    oCEOrdenCompraMatriz.setDescuentoTotal(rs.getFloat(18));
                    oCEOrdenCompraMatriz.setSubtotalNeto(rs.getFloat(19));
                    oCEOrdenCompraMatriz.setSubTotalNetoSinIGV(rs.getFloat(20));
                    oCEOrdenCompraMatriz.setDescuentoEnValores(rs.getFloat(21));
                    oCEOrdenCompraMatriz.setIGV(rs.getFloat(22));
                    oCEOrdenCompraMatriz.setISC(rs.getFloat(23));
                    oCEOrdenCompraMatriz.setIGVActual(rs.getFloat(24));
                    oCEOrdenCompraMatriz.setMontoTotal(rs.getFloat(25));
                    oCEOrdenCompraMatriz.setTotalPagar(rs.getFloat(26));
                    oCEOrdenCompraMatriz.setSucursal(rs.getString(27));
                    oCEOrdenCompraMatriz.setCodigo(rs.getString(28));
                    oCEOrdenCompraMatriz.setMontoPercepcion(rs.getFloat(29));
                    oCEOrdenCompraMatriz.setConPercepcion(rs.getBoolean(30));

                }
                if (oCEOrdenCompraMatriz != null)
                {

                    oCEOrdenCompraMatriz.setLstOrdenCompraDetalle(ListarOrdenCompraDetalle(oCEOrdenCompraMatriz.getIdOrdenCompra()));
                }
                return oCEOrdenCompraMatriz;
            }
            return oCEOrdenCompraMatriz;
        }
        catch (SQLException ex)
        {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, "Error al cargar el OrdenCompra ");
            return null;
        }
        finally
        {
            try
            {
                cnn.close();
            }
            catch (SQLException ex)
            {
                Logger.getLogger(CDOrdenCompra.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
  

    public static List<CEOrdenCompraDetalle> ListarOrdenCompraDetalle(long IdOrdenCompra)
    {
        Connection cnn = ConexionBD.obtenerConexion();
        try
        {
            List<CEOrdenCompraDetalle> lista = new ArrayList<CEOrdenCompraDetalle>();

            CEOrdenCompraDetalle oCEOrdenCompraDetalle = null;
            if (cnn != null)
            {
                CallableStatement cs = null;
                cs = cnn.prepareCall("{CALL  CMPSPRCNSOrdenCompraDetalle(?)}");
                cs.setLong(1, IdOrdenCompra);
                ResultSet rs = cs.executeQuery();
                while (rs.next())
                {

                    oCEOrdenCompraDetalle = new CEOrdenCompraDetalle();
                    oCEOrdenCompraDetalle.setIdOrdenCompraDetalle(rs.getLong(1));
                    oCEOrdenCompraDetalle.setIdProducto(rs.getInt(2));
                    oCEOrdenCompraDetalle.setIdUnidadMedidaVenta(rs.getInt(3));
                    oCEOrdenCompraDetalle.setCantidad(rs.getFloat(4));
                    oCEOrdenCompraDetalle.setPrecio(rs.getFloat(5));
                    oCEOrdenCompraDetalle.setImporteSinDescuento(rs.getFloat(6));
                    oCEOrdenCompraDetalle.setIdTipoDescuento(rs.getInt(7));
                    oCEOrdenCompraDetalle.setDescuento(rs.getFloat(8));
                    oCEOrdenCompraDetalle.setDescuentoEnValores(rs.getFloat(9));
                    oCEOrdenCompraDetalle.setImporteConDescuento(rs.getFloat(10));
                    oCEOrdenCompraDetalle.setProducto(rs.getString(11));
                    oCEOrdenCompraDetalle.setUnidadMedida(rs.getString(12));
                    oCEOrdenCompraDetalle.setSinoImpuesto(rs.getBoolean(13));
                    oCEOrdenCompraDetalle.setExonerado(rs.getFloat(14));
                    oCEOrdenCompraDetalle.setPrecioConDesc(rs.getFloat(15));
                    oCEOrdenCompraDetalle.setImporteSinDescuentoConIgv(rs.getFloat(16));

                    lista.add(oCEOrdenCompraDetalle);
                }
                return lista;
            }
            return lista;
        }
        catch (SQLException ex)
        {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, "Error al cargar el OrdenCompra ");
            return null;
        }
        finally
        {
            try
            {
                cnn.close();
            }
            catch (SQLException ex)
            {
                Logger.getLogger(CDOrdenCompra.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


    
}
