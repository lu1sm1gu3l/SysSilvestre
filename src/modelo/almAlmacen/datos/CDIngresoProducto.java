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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.almAlmacen.entidad.CEIngresoProducto;
import modelo.almAlmacen.entidad.CEIngresoProductoDetalle;
import modelo.cmpCompra.entidad.CEOrdenCompraDetalle;
import view.FrmSistemaMenu;


/**
 *
 * @author Joel
 */
public class CDIngresoProducto
{

    public static String InsIngresoProducto(CEIngresoProducto oCEIngresoProducto)
    {
        Connection conexion;
        conexion = ConexionBD.obtenerConexion();//5 elimina
        try
        {
            CallableStatement sentenciaIngresoProducto;
            CallableStatement sentenciaIngresoProductoDetalle;
            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            String sql= "{call ALMSPRINSIngresoProducto(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            sentenciaIngresoProducto = conexion.prepareCall(sql);
            sentenciaIngresoProducto.setLong(1, oCEIngresoProducto.getIdProveedor());
            sentenciaIngresoProducto.setInt(2, oCEIngresoProducto.getIdSucursal());
            sentenciaIngresoProducto.setInt(3, oCEIngresoProducto.getIdTipoIngresoSalida());
            sentenciaIngresoProducto.setInt(4, oCEIngresoProducto.getIdEmpleado());
            sentenciaIngresoProducto.setInt(5, oCEIngresoProducto.getIdUsuario());
            sentenciaIngresoProducto.setString(6, oCEIngresoProducto.getFecha());
            sentenciaIngresoProducto.setLong(7, oCEIngresoProducto.getIdComprobanteCompra());
            sentenciaIngresoProducto.setLong(8, 0);// id
            sentenciaIngresoProducto.setString(9, null);// numero
            sentenciaIngresoProducto.setString(10, oCEIngresoProducto.getNumeroDocumento());
            sentenciaIngresoProducto.setInt(11, oCEIngresoProducto.getIdTipoComprobante());
            sentenciaIngresoProducto.setInt(12, oCEIngresoProducto.getIdEstadoOC());
            sentenciaIngresoProducto.setInt(13, oCEIngresoProducto.getIdAlmacen());
            sentenciaIngresoProducto.setString(14,oCEIngresoProducto.getObservacion());
            sentenciaIngresoProducto.setInt(15, oCEIngresoProducto.getIdGuiaRemision());

            sentenciaIngresoProducto.executeUpdate();
            long id_IngresoProducto = sentenciaIngresoProducto.getInt(8);
            String CodigoIngresoProducto = sentenciaIngresoProducto.getString(9);
            for (CEIngresoProductoDetalle oCEIngresoProductoDetalle : oCEIngresoProducto.getLstIngresoDetalle())
            {
                String sql_ = "{call ALMSPRINSIngresoProductoDetalle(?,?,?,?,?,?,?,?,?,?,?)}";
             //   long id_IngresoProductoDetalle = oCEIngresoProductoDetalle.getIdIngresoProductoDetalle();
                sentenciaIngresoProductoDetalle = conexion.prepareCall(sql_);
                sentenciaIngresoProductoDetalle.setLong(1, id_IngresoProducto);
                sentenciaIngresoProductoDetalle.setLong(2, oCEIngresoProductoDetalle.getIdProducto());
                sentenciaIngresoProductoDetalle.setInt(3, oCEIngresoProductoDetalle.getIdAlmacen());
                sentenciaIngresoProductoDetalle.setInt(4, oCEIngresoProductoDetalle.getIdUnidadMedida());
                sentenciaIngresoProductoDetalle.setFloat(5, oCEIngresoProductoDetalle.getCantidad());
                sentenciaIngresoProductoDetalle.setFloat(6, oCEIngresoProductoDetalle.getCosto());
                sentenciaIngresoProductoDetalle.setFloat(7, oCEIngresoProductoDetalle.getImporte());
                sentenciaIngresoProductoDetalle.setFloat(8, oCEIngresoProductoDetalle.getIdIngresoProductoDetalle());
                sentenciaIngresoProductoDetalle.setFloat(9, oCEIngresoProductoDetalle.getIdComprobanteCompraDetalle());
                sentenciaIngresoProductoDetalle.setInt(10, oCEIngresoProducto.getIdTipoIngresoSalida());
                sentenciaIngresoProductoDetalle.setLong(11, oCEIngresoProductoDetalle.getIdComprobanteCompraDetalle());
                sentenciaIngresoProductoDetalle.executeUpdate();

            }
            conexion.commit();
            return CodigoIngresoProducto;
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

    public static String InsIngresoProductoPorGuiaRemision(CEIngresoProducto oCEIngresoProducto)
    {
        Connection conexion;
        conexion = ConexionBD.obtenerConexion();//5 elimina
        try
        {
            CallableStatement sentenciaIngresoProducto;
            CallableStatement sentenciaIngresoProductoDetalle;
            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            String sql= "{call ALMSPRINSIngresoProducto(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            sentenciaIngresoProducto = conexion.prepareCall(sql);
            sentenciaIngresoProducto.setLong(1, oCEIngresoProducto.getIdProveedor());
            sentenciaIngresoProducto.setInt(2, oCEIngresoProducto.getIdSucursal());
            sentenciaIngresoProducto.setInt(3, oCEIngresoProducto.getIdTipoIngresoSalida());
            sentenciaIngresoProducto.setInt(4, oCEIngresoProducto.getIdEmpleado());
            sentenciaIngresoProducto.setInt(5, oCEIngresoProducto.getIdUsuario());
            sentenciaIngresoProducto.setString(6, oCEIngresoProducto.getFecha());
            sentenciaIngresoProducto.setLong(7, oCEIngresoProducto.getIdComprobanteCompra());
            sentenciaIngresoProducto.setLong(8, 0);// id
            sentenciaIngresoProducto.setString(9, null);// numero
            sentenciaIngresoProducto.setString(10, oCEIngresoProducto.getNumeroDocumento());
            sentenciaIngresoProducto.setInt(11, oCEIngresoProducto.getIdTipoComprobante());
            sentenciaIngresoProducto.setInt(12, oCEIngresoProducto.getIdEstadoOC());
            sentenciaIngresoProducto.setInt(13, oCEIngresoProducto.getIdAlmacen());
            sentenciaIngresoProducto.setString(14,oCEIngresoProducto.getObservacion());
            sentenciaIngresoProducto.setInt(15, oCEIngresoProducto.getIdGuiaRemision());

            sentenciaIngresoProducto.executeUpdate();
            long id_IngresoProducto = sentenciaIngresoProducto.getInt(8);
            String CodigoIngresoProducto = sentenciaIngresoProducto.getString(9);
            for (CEIngresoProductoDetalle oCEIngresoProductoDetalle : oCEIngresoProducto.getLstIngresoDetalle())
            {
                String sql_ = "{call ALMSPRINSIngresoProductoDetallePorGuiaRem(?,?,?,?,?,?,?,?,?,?,?)}";
             //   long id_IngresoProductoDetalle = oCEIngresoProductoDetalle.getIdIngresoProductoDetalle();
                sentenciaIngresoProductoDetalle = conexion.prepareCall(sql_);
                sentenciaIngresoProductoDetalle.setLong(1, id_IngresoProducto);
                sentenciaIngresoProductoDetalle.setLong(2, oCEIngresoProductoDetalle.getIdProducto());
                sentenciaIngresoProductoDetalle.setInt(3, oCEIngresoProductoDetalle.getIdAlmacen());
                sentenciaIngresoProductoDetalle.setInt(4, oCEIngresoProductoDetalle.getIdUnidadMedida());
                sentenciaIngresoProductoDetalle.setFloat(5, oCEIngresoProductoDetalle.getCantidad());
                sentenciaIngresoProductoDetalle.setFloat(6, oCEIngresoProductoDetalle.getCosto());
                sentenciaIngresoProductoDetalle.setFloat(7, oCEIngresoProductoDetalle.getImporte());
                sentenciaIngresoProductoDetalle.setFloat(8, oCEIngresoProductoDetalle.getIdIngresoProductoDetalle());
                sentenciaIngresoProductoDetalle.setFloat(9, oCEIngresoProductoDetalle.getIdComprobanteCompraDetalle());
                sentenciaIngresoProductoDetalle.setInt(10, oCEIngresoProducto.getIdTipoIngresoSalida());
                sentenciaIngresoProductoDetalle.setInt(11, oCEIngresoProductoDetalle.getIdGuiaRemisionRecibidoDetalle());                
                sentenciaIngresoProductoDetalle.executeUpdate();

            }
            conexion.commit();
            return CodigoIngresoProducto;
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

    public static int UPDIngresoProducto(CEIngresoProducto oCEIngresoProducto)
    {
        Connection conexion;
        conexion = ConexionBD.obtenerConexion();//5 elimina
        try
        {
            CallableStatement sentenciaIngresoProducto;
            CallableStatement sentenciaIngresoProductoDetalle;
            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            String sql= "{call ALMSPRUPDIngresoProducto(?,?,?,?,?,?,?,?,?,?)}";
            sentenciaIngresoProducto = conexion.prepareCall(sql);
            sentenciaIngresoProducto.setLong(1, oCEIngresoProducto.getIdProveedor());
            sentenciaIngresoProducto.setInt(2, oCEIngresoProducto.getIdEmpleado());
            sentenciaIngresoProducto.setInt(3, oCEIngresoProducto.getIdUsuario());
            sentenciaIngresoProducto.setString(4, oCEIngresoProducto.getFecha());
            sentenciaIngresoProducto.setLong(5, oCEIngresoProducto.getIdIngresoProducto());// idIngresoProd
            sentenciaIngresoProducto.setString(6, oCEIngresoProducto.getNumeroDocumento());// numero Doc
            sentenciaIngresoProducto.setInt(7, oCEIngresoProducto.getIdEstadoOC());
            sentenciaIngresoProducto.setLong(8, oCEIngresoProducto.getIdComprobanteCompra());
            sentenciaIngresoProducto.setInt(9, oCEIngresoProducto.getIdTipoIngresoSalida());
            sentenciaIngresoProducto.setString(10,oCEIngresoProducto.getObservacion());
            sentenciaIngresoProducto.executeUpdate();
            long id_IngresoProducto = oCEIngresoProducto.getIdIngresoProducto();
            for (CEIngresoProductoDetalle oCEIngresoProductoDetalle : oCEIngresoProducto.getLstIngresoDetalle())
            {
                String sql_ = "{call ALMSPRUPDIngresoProductoDetalle(?,?,?,?,?,?,?,?,?,?,?)}";
             //   long id_IngresoProductoDetalle = oCEIngresoProductoDetalle.getIdIngresoProductoDetalle();
                sentenciaIngresoProductoDetalle = conexion.prepareCall(sql_);
                sentenciaIngresoProductoDetalle.setLong(1, oCEIngresoProductoDetalle.getIdProducto());
                sentenciaIngresoProductoDetalle.setInt(2, oCEIngresoProductoDetalle.getIdAlmacen());
                sentenciaIngresoProductoDetalle.setFloat(3, oCEIngresoProductoDetalle.getCantidad());
                sentenciaIngresoProductoDetalle.setFloat(4, oCEIngresoProductoDetalle.getCantidadAnterior());
                sentenciaIngresoProductoDetalle.setFloat(5, oCEIngresoProductoDetalle.getCosto());
                sentenciaIngresoProductoDetalle.setFloat(6, oCEIngresoProductoDetalle.getIdUnidadMedida());
                sentenciaIngresoProductoDetalle.setFloat(7, oCEIngresoProductoDetalle.getIdIngresoProductoDetalle());
                sentenciaIngresoProductoDetalle.setFloat(8, oCEIngresoProductoDetalle.getIdComprobanteCompraDetalle());
                sentenciaIngresoProductoDetalle.setLong(9, oCEIngresoProducto.getIdIngresoProducto());
                sentenciaIngresoProductoDetalle.setFloat(10, oCEIngresoProductoDetalle.getImporte());
                sentenciaIngresoProductoDetalle.setInt(11, oCEIngresoProducto.getIdTipoIngresoSalida());
                sentenciaIngresoProductoDetalle.executeUpdate();

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



        public static List<CEIngresoProductoDetalle> ListarIngresoProductoDetalle(long pIdIngresoProducto)
    {
        Connection cnn = ConexionBD.obtenerConexion();
        try
        {
            List<CEIngresoProductoDetalle> lista = new ArrayList<CEIngresoProductoDetalle>();

            CEIngresoProductoDetalle oCEIngresoProductoDetalle = null;
            if (cnn != null)
            {
                CallableStatement cs = null;
                cs = cnn.prepareCall("{CALL  ALMSPRCNSIngresoProductoDetalle(?)}");
                cs.setLong(1, pIdIngresoProducto);
                ResultSet rs = cs.executeQuery();
                while (rs.next())
                {
                    oCEIngresoProductoDetalle = new CEIngresoProductoDetalle();
                    oCEIngresoProductoDetalle.setIdIngresoProductoDetalle(rs.getLong(1));
                    oCEIngresoProductoDetalle.setIdProducto(rs.getInt(2));
                    oCEIngresoProductoDetalle.setIdAlmacen(rs.getInt(3));
                    oCEIngresoProductoDetalle.setIdUnidadMedida(rs.getInt(4));
                    oCEIngresoProductoDetalle.setCantidad(rs.getFloat(5));
                    oCEIngresoProductoDetalle.setCosto(rs.getFloat(6));
                    oCEIngresoProductoDetalle.setImporte(rs.getFloat(7));
                    oCEIngresoProductoDetalle.setProducto(rs.getString(8));
                    oCEIngresoProductoDetalle.setUnidadMedida(rs.getString(9));
                    oCEIngresoProductoDetalle.setAlmacen(rs.getString(10));
                    oCEIngresoProductoDetalle.setIdComprobanteCompraDetalle(rs.getLong(11));
                    lista.add(oCEIngresoProductoDetalle);
                }
                return lista;
            }
            return lista;
        }
        catch (SQLException ex)
        {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, "Error al cargar el Pedido ");
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
                Logger.getLogger(CDIngresoProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public static List<CEOrdenCompraDetalle> CargarSaldoCantidades(int pIdTipoIngreso,long pIdIngreso)
    {
          Connection cnn = ConexionBD.obtenerConexion();
        try
        {
            List<CEOrdenCompraDetalle> lista = new ArrayList<CEOrdenCompraDetalle>();

            CEOrdenCompraDetalle oCEOrdenCompraDetalle = null;
            if (cnn != null)
            {
                CallableStatement cs = null;
                cs = cnn.prepareCall("{CALL  ALMSPRCNSSaldoOCdeIngresoProductoDetalle(?,?)}");
                cs.setInt(1, pIdTipoIngreso);
                cs.setLong(2, pIdIngreso);
                ResultSet rs = cs.executeQuery();
                while (rs.next())
                {
                    oCEOrdenCompraDetalle = new CEOrdenCompraDetalle();
                    oCEOrdenCompraDetalle.setIdOrdenCompraDetalle(rs.getLong(1));
                    oCEOrdenCompraDetalle.setIdProducto(rs.getInt(2));
                    oCEOrdenCompraDetalle.setIdUnidadMedidaVenta(rs.getInt(4));
            //        oCEOrdenCompraDetalle.setSaldoCantidad(rs.getFloat(5));
       //             oCEOrdenCompraDetalle.setIdAlmacen(rs.getInt(6));
                    oCEOrdenCompraDetalle.setUnidadMedida(rs.getString(7));
                    oCEOrdenCompraDetalle.setCantidad(rs.getFloat(8));
                    oCEOrdenCompraDetalle.setPrecioConDesc(rs.getFloat(9));
                    oCEOrdenCompraDetalle.setProducto(rs.getString(10));
                    oCEOrdenCompraDetalle.setIdOrdenCompra(rs.getLong(11));
                    lista.add(oCEOrdenCompraDetalle);
                }
                return lista;
            }
            return lista;
        }
        catch (SQLException ex)
        {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, "Error al cargar el Pedido ");
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
                Logger.getLogger(CDIngresoProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

     public static CEIngresoProductoDetalle VerificarUltimoIngreso(long pIdProducto,int pIdAlmacen)
    {
          Connection cnn = ConexionBD.obtenerConexion();
        try
        {

            CEIngresoProductoDetalle oCEIngresoProductoDetalle = null;
            if (cnn != null)
            {
                CallableStatement cs = null;
                cs = cnn.prepareCall("{CALL  ALMSPRCNSUltimoMovimientoAlmacen(?,?,?)}");
                cs.setLong(1, pIdProducto);
                cs.setInt(2, pIdAlmacen);
                cs.setInt(3, 1);
                ResultSet rs = cs.executeQuery();
                while (rs.next())
                {
                    oCEIngresoProductoDetalle = new CEIngresoProductoDetalle();
                    oCEIngresoProductoDetalle.setIdIngresoProductoDetalle(rs.getLong(1));
                    oCEIngresoProductoDetalle.setIsSalida(rs.getBoolean(2));
                  //  lista.add(oCEIngresoProductoDetalle);
                }
                return oCEIngresoProductoDetalle;
            }
            return oCEIngresoProductoDetalle;
        }
        catch (SQLException ex)
        {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, "Error al cargar el Pedido ");
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
                Logger.getLogger(CDIngresoProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    

      public static long VerificarUltimoIngreso()
    {
          Connection cnn = ConexionBD.obtenerConexion();
        try
        {

            long UltimoIdProductoDetalle=0;
            if (cnn != null)
            {
                CallableStatement cs = null;
                cs = cnn.prepareCall("{CALL  ALMSPRCNSUltimoIngreso()}");
                ResultSet rs = cs.executeQuery();
                while (rs.next())
                {
                    UltimoIdProductoDetalle=rs.getLong(1);
                  //  lista.add(oCEIngresoProductoDetalle);
                }
                return UltimoIdProductoDetalle;
            }
            return UltimoIdProductoDetalle;
        }
        catch (SQLException ex)
        {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, "Error al cargar el Pedido ");
            return 0;
        }
        finally
        {
            try
            {
                cnn.close();
            }
            catch (SQLException ex)
            {
                Logger.getLogger(CDIngresoProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
      public static int AnularIngresoProducto(CEIngresoProducto oCEIngresoProducto)
    {
        Connection conexion;
        conexion = ConexionBD.obtenerConexion();//5 elimina
        try
        {
            CallableStatement sentenciaOrdenCompra;
            CallableStatement sentenciaOrdenCompraDet;
            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            String sql = "{call ALMSPRINSIngresoProductoEstado(?,?,?,?,?)}";
            sentenciaOrdenCompra = conexion.prepareCall(sql);
            sentenciaOrdenCompra.setLong(1, oCEIngresoProducto.getIdIngresoProducto());
            sentenciaOrdenCompra.setInt(2, 4);
            sentenciaOrdenCompra.setInt(3,FrmSistemaMenu.oCEUsuario.getIdUsuario());
            sentenciaOrdenCompra.setInt(4, oCEIngresoProducto.getIdEmpleado());
            sentenciaOrdenCompra.setString(5, oCEIngresoProducto.getObservacion());
            sentenciaOrdenCompra.executeUpdate();
            
            for (CEIngresoProductoDetalle oCEIngresoProductoDet : oCEIngresoProducto.getLstIngresoDetalle())
            {
            String sqlDet = "{call ALMSPRUPDAnularIngresoProductoDetalle(?,?,?,?,?,?)}";
            sentenciaOrdenCompraDet = conexion.prepareCall(sqlDet);
            sentenciaOrdenCompraDet.setLong(1, oCEIngresoProductoDet.getIdProducto());
            sentenciaOrdenCompraDet.setInt(2, oCEIngresoProductoDet.getIdAlmacen());
            sentenciaOrdenCompraDet.setFloat(3, oCEIngresoProductoDet.getCantidad());
            sentenciaOrdenCompraDet.setInt(4, oCEIngresoProductoDet.getIdUnidadMedida());
            sentenciaOrdenCompraDet.setFloat(5, oCEIngresoProductoDet.getIdComprobanteCompraDetalle());
            sentenciaOrdenCompraDet.setInt(6, oCEIngresoProducto.getIdTipoIngresoSalida());
            sentenciaOrdenCompraDet.executeUpdate();
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



 public static List<CEIngresoProducto> ListarIngresoProductosPorCodigo(String pDato,int opcion,long id,int idestado, int idtipocomp,String pFechaFin)
    {
        Connection cnn = ConexionBD.obtenerConexion();
        try
        {
            List<CEIngresoProducto> lista=new ArrayList<CEIngresoProducto>();
            CEIngresoProducto oCEIngresoProducto = null;
            if (cnn != null)
            {
                CallableStatement cs = null;
                cs = cnn.prepareCall("{CALL  ALMSPRCNSIngresoProductoPorCodigo(?,?,?,?,?,?)}");
                cs.setInt(1, opcion);
                cs.setString(2, pDato);
                cs.setLong(3, id);
                cs.setInt(4, idestado);
                cs.setInt(5, idtipocomp);
                cs.setString(6, pFechaFin);
                ResultSet rs = cs.executeQuery();
                while (rs.next())
                {
                    oCEIngresoProducto = new CEIngresoProducto();
                    oCEIngresoProducto.setIdIngresoProducto(rs.getLong(1));
                    oCEIngresoProducto.setNumeroDocumento(rs.getString(2));
                    oCEIngresoProducto.setFecha(rs.getString(3));
                    oCEIngresoProducto.setNumeroIngreso(rs.getString(4));
                    oCEIngresoProducto.setProveedor(rs.getString(5));
                    oCEIngresoProducto.setTipoComprobante(rs.getString(6));
                    oCEIngresoProducto.setUltimoEstado(rs.getString(7));
                    oCEIngresoProducto.setTipoIngreso(rs.getString(8));
                    lista.add(oCEIngresoProducto);


                }

            }
            return lista;
        }
        catch (SQLException ex)
        {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, "Error al cargar el IngresoProducto ");
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
                Logger.getLogger(CDIngresoProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


 public static CEIngresoProducto ConsultarIngresoProductosPorId(long id)
    {
        Connection cnn = ConexionBD.obtenerConexion();
        try
        {
            CEIngresoProducto oCEIngresoProducto = null;
            if (cnn != null)
            {
                CallableStatement cs = null;
                cs = cnn.prepareCall("{CALL  ALMSPRCNSIngresoProductoPorCodigo(?,?,?,?,?,?)}");
                cs.setInt(1, 1);
                cs.setString(2, null);
                cs.setLong(3, id);
                cs.setInt(4, 0);
                cs.setInt(5, 0);
                cs.setString(6, null);
                ResultSet rs = cs.executeQuery();
                while (rs.next())
                {
                     oCEIngresoProducto = new CEIngresoProducto();
                    oCEIngresoProducto.setIdIngresoProducto(rs.getLong(1));
                    oCEIngresoProducto.setNumeroDocumento(rs.getString(2));
                    oCEIngresoProducto.setNumeroIngreso(rs.getString(3));
                    oCEIngresoProducto.setFecha(rs.getString(4));
                    oCEIngresoProducto.setIdProveedor(rs.getInt(5));
                    oCEIngresoProducto.setProveedor(rs.getString(6));
                    oCEIngresoProducto.setDireccion(rs.getString(7));
                    oCEIngresoProducto.setRUC(rs.getString(8));
                    oCEIngresoProducto.setIdTipoIngresoSalida(rs.getInt(9));
                    oCEIngresoProducto.setTipoIngreso(rs.getString(10));
                    oCEIngresoProducto.setEmpleado(rs.getString(11));
                    oCEIngresoProducto.setSucursal(rs.getString(12));
                    oCEIngresoProducto.setUltimoIdEstado(rs.getInt(13));
                    oCEIngresoProducto.setUltimoEstado(rs.getString(14));
                    oCEIngresoProducto.setIdTipoComprobante(rs.getInt(15));
                    oCEIngresoProducto.setIdAlmacen(rs.getInt(17));
                    oCEIngresoProducto.setObservacion(rs.getString(18));


                }
                if (oCEIngresoProducto != null)
                {

                    oCEIngresoProducto.setLstIngresoDetalle(ListarIngresoProductoDetalle(oCEIngresoProducto.getIdIngresoProducto()));
                }
                return oCEIngresoProducto;
            }
            return oCEIngresoProducto;
        }
        catch (SQLException ex)
        {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, "Error al cargar el IngresoProducto ");
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
                Logger.getLogger(CDIngresoProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
