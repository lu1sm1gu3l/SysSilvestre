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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.vtaVenta.entidad.CEPedido;
import modelo.vtaVenta.entidad.CEPedidoDetalle;
import modelo.vtaVenta.entidad.CEPedidoMatriz;

/**
 *
 * @author Joel
 */
public class CDPedido
{

    public static String InsPedido(CEPedidoMatriz oCEPedido,boolean confecha)
    {
        Connection conexion;
        conexion = ConexionBD.obtenerConexion();//
        try
        {
            CallableStatement sentenciaPedido;
            CallableStatement sentenciaPedidoDetalle;
            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            String sql= "{call VTASPRINSPedido(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            sentenciaPedido = conexion.prepareCall(sql);
            sentenciaPedido.setLong(1, oCEPedido.getIdPedido());
            sentenciaPedido.setLong(2, oCEPedido.getIdCliente());
            sentenciaPedido.setLong(3, oCEPedido.getIdSucursal());
            sentenciaPedido.setFloat(4, oCEPedido.getDescuento());
            sentenciaPedido.setFloat(5, oCEPedido.getSubTotalNeto());
            sentenciaPedido.setInt(6, oCEPedido.getIdUltimoEstado());
            sentenciaPedido.setInt(7, oCEPedido.getIdTipoComprobante());
            sentenciaPedido.setInt(8, oCEPedido.getIdCondicion());
            sentenciaPedido.setInt(9, oCEPedido.getIdMoneda());
            sentenciaPedido.setInt(10, oCEPedido.getIdTipoDescuento());
            sentenciaPedido.registerOutParameter(11, java.sql.Types.BIGINT);
            sentenciaPedido.registerOutParameter(12, java.sql.Types.VARCHAR);
            sentenciaPedido.setInt(13, oCEPedido.getIdEmpleado());
            sentenciaPedido.setInt(14, oCEPedido.getIdUsuario());
            sentenciaPedido.setFloat(15, oCEPedido.getTipoCambio());
            sentenciaPedido.setFloat(16, oCEPedido.getSubtotalBruto());
            sentenciaPedido.setFloat(17, oCEPedido.getDescuentoEnSubtotal());
            sentenciaPedido.setFloat(18, oCEPedido.getDescuentoTotal());
            sentenciaPedido.setFloat(19, oCEPedido.getDescuentoValores());
            sentenciaPedido.setFloat(20, oCEPedido.getIGVActual());
            sentenciaPedido.setFloat(21, oCEPedido.getIGV());
            sentenciaPedido.setFloat(22, oCEPedido.getSubtotalNetoSinIGV());
            sentenciaPedido.setFloat(23, oCEPedido.getMontoTotal());
            sentenciaPedido.setFloat(24, oCEPedido.getTotalPagar());
            sentenciaPedido.setString(25, oCEPedido.getFecha());
            sentenciaPedido.setBoolean(26, confecha);
            sentenciaPedido.setFloat(27, oCEPedido.getMontoPercepcion());
            sentenciaPedido.setFloat(28, oCEPedido.getMontoExonerado());
            sentenciaPedido.setFloat(29, oCEPedido.getTasaPercepcion());

            sentenciaPedido.executeUpdate();
            long id_Pedido = sentenciaPedido.getInt(11);
            oCEPedido.setIdPedido(id_Pedido);
            String CodigoPedido = sentenciaPedido.getString(12);
            for (CEPedidoDetalle oCEPedidoDetalle : oCEPedido.getLstPedidoDetalle())
            {
                String sql_ = "{call VTASPRINSPedidoDetalle(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                long id_pedidoDetalle = oCEPedidoDetalle.getIdPedidoDetalle();
                sentenciaPedidoDetalle = conexion.prepareCall(sql_);
                sentenciaPedidoDetalle.setLong(1, id_pedidoDetalle);
                sentenciaPedidoDetalle.setLong(2, id_Pedido);
                sentenciaPedidoDetalle.setFloat(3, oCEPedidoDetalle.getCantidad());
                sentenciaPedidoDetalle.setFloat(4, oCEPedidoDetalle.getDescuento());
                sentenciaPedidoDetalle.setLong(5, oCEPedidoDetalle.getIdProducto());
                sentenciaPedidoDetalle.setInt(6, oCEPedidoDetalle.getIdTipoDescuento());
                sentenciaPedidoDetalle.setFloat(7, oCEPedidoDetalle.getPrecio());
                sentenciaPedidoDetalle.setFloat(8, oCEPedidoDetalle.getIdAlmacen());
                sentenciaPedidoDetalle.setFloat(9, oCEPedidoDetalle.getIdUnidadMedidaVenta());
                sentenciaPedidoDetalle.setFloat(10, oCEPedidoDetalle.getDescuentoEnValores());
                sentenciaPedidoDetalle.setFloat(11, oCEPedidoDetalle.getImportelSinDescuento());
                sentenciaPedidoDetalle.setFloat(12, oCEPedidoDetalle.getImporteConDescuento());
                sentenciaPedidoDetalle.setFloat(13, oCEPedidoDetalle.getExonerado());
                sentenciaPedidoDetalle.setBoolean(14, oCEPedidoDetalle.isSinoImpuesto());
                sentenciaPedidoDetalle.setFloat(15, oCEPedidoDetalle.getPrecioSinRedon());
                sentenciaPedidoDetalle.setFloat(16, oCEPedidoDetalle.getEqivalenciaUMB());
                sentenciaPedidoDetalle.setFloat(17, oCEPedidoDetalle.getPrecioConDesc());
                sentenciaPedidoDetalle.setBoolean(18, oCEPedidoDetalle.isConPercepcion());
                sentenciaPedidoDetalle.executeUpdate();

            }
            conexion.commit();
            return CodigoPedido;
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

    public static int UPDPedido(CEPedido oCEPedido)
    {
        Connection conexion;
        conexion = ConexionBD.obtenerConexion();//5 elimina
        try
        {
            CallableStatement sentenciaPedido;
            CallableStatement sentenciaPedidoDetalle;
            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            String sql= "{call VTASPRUPDPedido(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            sentenciaPedido = conexion.prepareCall(sql);
            sentenciaPedido.setLong(1, oCEPedido.getIdPedido());
            sentenciaPedido.setLong(2, oCEPedido.getIdCliente());
            sentenciaPedido.setLong(3, oCEPedido.getIdSucursal());
            sentenciaPedido.setFloat(4, oCEPedido.getDescuento());
            sentenciaPedido.setFloat(5, oCEPedido.getSubtotalNetoSinIGV());
            sentenciaPedido.setInt(6, oCEPedido.getIdUltimoEstado());
            sentenciaPedido.setInt(7, oCEPedido.getIdTipoComprobante());
            sentenciaPedido.setInt(8, oCEPedido.getIdCondicion());
            sentenciaPedido.setInt(9, oCEPedido.getIdMoneda());
            sentenciaPedido.setInt(10, oCEPedido.getIdTipoDescuento());
            sentenciaPedido.setInt(11, oCEPedido.getIdEmpleado());
            sentenciaPedido.setInt(12, oCEPedido.getIdUsuario());
            sentenciaPedido.setFloat(13, oCEPedido.getTipoCambio());
            sentenciaPedido.setFloat(14, oCEPedido.getSubtotalBruto());
            sentenciaPedido.setFloat(15, oCEPedido.getDescuentoEnSubtotal());
            sentenciaPedido.setFloat(16, oCEPedido.getDescuentoTotal());
            sentenciaPedido.setFloat(17, oCEPedido.getDescuentoValores());
            sentenciaPedido.setFloat(18, oCEPedido.getIGVActual());
            sentenciaPedido.setFloat(19, oCEPedido.getIGV());
            sentenciaPedido.setFloat(20, oCEPedido.getSubtotalNetoSinIGV());
            sentenciaPedido.setFloat(21, oCEPedido.getMontoTotal());
            sentenciaPedido.setFloat(22, oCEPedido.getTotalPagar());
            sentenciaPedido.setFloat(23, oCEPedido.getMontoPercepcion());
            sentenciaPedido.setFloat(24, oCEPedido.getMontoExonerado());
            sentenciaPedido.setFloat(25, oCEPedido.getTasaPercepcion());
            sentenciaPedido.executeUpdate();
            long id_Pedido = oCEPedido.getIdPedido();
            for (CEPedidoDetalle oCEPedidoDetalle : oCEPedido.getLstPedidoDetalle())
            {
                String sql_ = "{call VTASPRABMPedidoDetalle(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                long id_pedidoDetalle = oCEPedidoDetalle.getIdPedidoDetalle();
                sentenciaPedidoDetalle = conexion.prepareCall(sql_);
                sentenciaPedidoDetalle.setLong(1, id_pedidoDetalle);
                sentenciaPedidoDetalle.setFloat(2, oCEPedidoDetalle.getCantidad());
                sentenciaPedidoDetalle.setFloat(3, oCEPedidoDetalle.getDescuento());
                sentenciaPedidoDetalle.setLong(4, oCEPedidoDetalle.getIdProducto());
                sentenciaPedidoDetalle.setInt(5, oCEPedidoDetalle.getIdTipoDescuento());
                sentenciaPedidoDetalle.setFloat(6, oCEPedidoDetalle.getPrecio());
                sentenciaPedidoDetalle.setFloat(7, oCEPedidoDetalle.getIdAlmacen());
                sentenciaPedidoDetalle.setFloat(8, oCEPedidoDetalle.getIdUnidadMedidaVenta());
                sentenciaPedidoDetalle.setFloat(9, oCEPedidoDetalle.getDescuentoEnValores());
                sentenciaPedidoDetalle.setFloat(10, oCEPedidoDetalle.getImportelSinDescuento());
                sentenciaPedidoDetalle.setFloat(11, oCEPedidoDetalle.getImporteConDescuento());
                sentenciaPedidoDetalle.setFloat(12, oCEPedidoDetalle.getExonerado());
                sentenciaPedidoDetalle.setBoolean(13, oCEPedidoDetalle.isSinoImpuesto());
                sentenciaPedidoDetalle.setFloat(14, oCEPedidoDetalle.getPrecioSinRedon());
                sentenciaPedidoDetalle.setFloat(15, oCEPedidoDetalle.getIdAbm());
                sentenciaPedidoDetalle.setLong(16, id_Pedido);
                sentenciaPedidoDetalle.setFloat(17, oCEPedidoDetalle.getEqivalenciaUMB());
                sentenciaPedidoDetalle.setFloat(18, oCEPedidoDetalle.getPrecioConDesc());
                sentenciaPedidoDetalle.setBoolean(19, oCEPedidoDetalle.isConPercepcion());
                sentenciaPedidoDetalle.executeUpdate();

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

    public static int AnularPedido(CEPedidoMatriz oCEPedido)
    {
        Connection conexion;
        conexion = ConexionBD.obtenerConexion();//5 elimina
        try
        {
            CallableStatement sentenciaPedido;
//            CallableStatement sentenciaPedidoDetalle;

            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            String sql = "{call VTASPRINSPedidoEstado(?,?,?,?,?)}";
            sentenciaPedido = conexion.prepareCall(sql);
            sentenciaPedido.setLong(1, oCEPedido.getIdPedido());
            sentenciaPedido.setInt(2, 4);
            sentenciaPedido.setInt(3, oCEPedido.getIdUsuario());
            sentenciaPedido.setInt(4, oCEPedido.getIdEmpleado());
            sentenciaPedido.setString(5, oCEPedido.getObservacion());
            sentenciaPedido.executeUpdate();

//            for (CEPedidoDetalle oCEPedidoDetalle : oCEPedido.getLstPedidoDetalle())
//            {
//                String sql_ = "{call ALMSPRActualizarStockPedido(?,?,?)}";
//
//                sentenciaPedidoDetalle = conexion.prepareCall(sql_);
//                sentenciaPedidoDetalle.setLong(1, oCEPedidoDetalle.getIdProducto());
//                sentenciaPedidoDetalle.setInt(2, oCEPedidoDetalle.getIdAlmacen());
//                sentenciaPedidoDetalle.setFloat(3, oCEPedidoDetalle.getEqivalenciaUMB()*oCEPedidoDetalle.getCantidad());
//                sentenciaPedidoDetalle.executeUpdate();
//
//            }
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

    public static List<CEPedidoMatriz> ListarPedidosPendientes(int pIdCns, int pIdSucursal)
    {
        Connection cnn = ConexionBD.obtenerConexion();
        try
        {

            List<CEPedidoMatriz> oLista = new ArrayList<CEPedidoMatriz>();
            if (cnn != null)
            {
                CallableStatement cs = null;
                cs = cnn.prepareCall("{CALL  VTASPRCNSPedido(?,?)}");
                cs.setInt(1, pIdCns);
                cs.setInt(2, pIdSucursal);
                ResultSet rs = cs.executeQuery();
                while (rs.next())
                {
                    CEPedidoMatriz oCEPedidoMatriz = new CEPedidoMatriz();
                    oCEPedidoMatriz.setIdPedido(rs.getLong(1));
                    oCEPedidoMatriz.setIdCliente(rs.getInt(2));
                    oCEPedidoMatriz.setIdSucursal(rs.getInt(3));
                    oCEPedidoMatriz.setFecha(rs.getString(4));
                    oCEPedidoMatriz.setIdDescuento(rs.getInt(5));
                    oCEPedidoMatriz.setDescuento(rs.getFloat(6));
                    oCEPedidoMatriz.setDescuentoEnValores(rs.getFloat(7));
                    oCEPedidoMatriz.setDescuentoEnSubtotal(rs.getFloat(8));
                    oCEPedidoMatriz.setDescuentoTotal(rs.getFloat(9));
                    oCEPedidoMatriz.setSubTotalNeto(rs.getFloat(10));
                    oCEPedidoMatriz.setSubTotalNetoSinIGV(rs.getFloat(11));
                    oCEPedidoMatriz.setIGVActual(rs.getFloat(12));
                    oCEPedidoMatriz.setIGV(rs.getFloat(13));
                    oCEPedidoMatriz.setISC(rs.getFloat(14));
                    oCEPedidoMatriz.setMontoTotal(rs.getFloat(15));
                    oCEPedidoMatriz.setIdTipoComprobante(rs.getInt(16));
                    oCEPedidoMatriz.setIdCondicion(rs.getInt(17));
                    oCEPedidoMatriz.setIdMoneda(rs.getInt(18));
                    oCEPedidoMatriz.setTipoCambio(rs.getFloat(19));
                    oCEPedidoMatriz.setCodigo(rs.getString(20));
                    oCEPedidoMatriz.setCliente(rs.getString(21));
                    oCEPedidoMatriz.setMoneda(rs.getString(22));
                    oCEPedidoMatriz.setTipoComprobante(rs.getString(23));
                    oCEPedidoMatriz.setDireccion(rs.getString(24));
                    oCEPedidoMatriz.setDNI(rs.getString(25));
                    oCEPedidoMatriz.setCondicion(rs.getString(26));
                    oCEPedidoMatriz.setTipoDescuento(rs.getString(27));
                    oCEPedidoMatriz.setEmpleado(rs.getString(28));
                    oCEPedidoMatriz.setIdEmpleado(rs.getInt(29));
                    oCEPedidoMatriz.setSucursal(rs.getString(30));
                    oCEPedidoMatriz.setSubtotalBruto(rs.getFloat(31));
                    oCEPedidoMatriz.setTotalPagar(rs.getFloat(32));
                    oCEPedidoMatriz.setRUC(rs.getString(33));
                    oLista.add(oCEPedidoMatriz);
                }
                return oLista;
            }
            return oLista;
        }
        catch (SQLException ex)
        {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, "Error al cargar Cliente ");
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
                Logger.getLogger(CDPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }




    public static CEPedidoMatriz ConsultarPedidosPorId(long id)
    {
        Connection cnn = ConexionBD.obtenerConexion();
        try
        {

            CEPedidoMatriz oCEPedidoMatriz = null;
            if (cnn != null)
            {
                CallableStatement cs = null;
                cs = cnn.prepareCall("{CALL  VTASPRCNSPedidoPorCodigo(?,?,?,?,?,?)}");
                cs.setInt(1, 1);
                cs.setString(2, null);
                cs.setLong(3, id);
                cs.setInt(4, 0);
                cs.setInt(5, 0);
                cs.setString(6, null);
                ResultSet rs = cs.executeQuery();
                while (rs.next())
                {
                    oCEPedidoMatriz = new CEPedidoMatriz();
                    oCEPedidoMatriz.setIdPedido(rs.getLong(1));
                    oCEPedidoMatriz.setIdCliente(rs.getInt(2));
                    oCEPedidoMatriz.setIdSucursal(rs.getInt(3));
                    oCEPedidoMatriz.setFecha(rs.getString(4));
                    oCEPedidoMatriz.setDescuento(rs.getFloat(5));
                    oCEPedidoMatriz.setIdTipoComprobante(rs.getInt(6));
                    oCEPedidoMatriz.setIdCondicion(rs.getInt(7));
                    oCEPedidoMatriz.setIdMoneda(rs.getInt(8));
                    oCEPedidoMatriz.setIdDescuento(rs.getInt(9));
                    oCEPedidoMatriz.setCliente(rs.getString(10));
                    oCEPedidoMatriz.setDireccion(rs.getString(11));
                    oCEPedidoMatriz.setDNI(rs.getString(12));
                    oCEPedidoMatriz.setEmpleado(rs.getString(13));
                    oCEPedidoMatriz.setIdUltimoEstado(rs.getInt(14));
                    oCEPedidoMatriz.setUltimoEstado(rs.getString(15));
                    oCEPedidoMatriz.setSubtotalBruto(rs.getFloat(16));
                    oCEPedidoMatriz.setDescuentoEnSubtotal(rs.getFloat(17));
                    oCEPedidoMatriz.setDescuentoTotal(rs.getFloat(18));
                    oCEPedidoMatriz.setSubTotalNeto(rs.getFloat(19));
                    oCEPedidoMatriz.setSubTotalNetoSinIGV(rs.getFloat(20));
                    oCEPedidoMatriz.setDescuentoEnValores(rs.getFloat(21));
                    oCEPedidoMatriz.setIGV(rs.getFloat(22));
                    oCEPedidoMatriz.setISC(rs.getFloat(23));
                    oCEPedidoMatriz.setIGVActual(rs.getFloat(24));
                    oCEPedidoMatriz.setMontoTotal(rs.getFloat(25));
                    oCEPedidoMatriz.setTotalPagar(rs.getFloat(26));
                    oCEPedidoMatriz.setTipoCambio(rs.getFloat(27));
                    oCEPedidoMatriz.setSucursal(rs.getString(28));
                    oCEPedidoMatriz.setCodigo(rs.getString(29));
                    oCEPedidoMatriz.setMontoPercepcion(rs.getFloat(30));
                    oCEPedidoMatriz.setRUC(rs.getString(31));
                    oCEPedidoMatriz.setMontoExonerado(rs.getFloat(32));
                    oCEPedidoMatriz.setTasaPercepcion(rs.getFloat(33));
                }
                if (oCEPedidoMatriz != null)
                {

                    oCEPedidoMatriz.setLstPedidoDetalle(ListarPedidoDetalle(oCEPedidoMatriz.getIdPedido()));
                }
                return oCEPedidoMatriz;
            }
            return oCEPedidoMatriz;
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
                Logger.getLogger(CDPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

     public static List<CEPedidoMatriz> ListarPedido(String pDato,int opcion,long id,int idestado,int pidtipocomp,String pFechaFin)
    {
        Connection cnn = ConexionBD.obtenerConexion();
        try
        {
            List<CEPedidoMatriz> lista=new ArrayList<CEPedidoMatriz>();
            CEPedidoMatriz oCEPedido = null;
            if (cnn != null)
            {
                CallableStatement cs = null;
                cs = cnn.prepareCall("{CALL  VTASPRCNSPedidoPorCodigo(?,?,?,?,?,?)}");
                cs.setInt(1, opcion);
                cs.setString(2, pDato);
                cs.setLong(3, id);
                cs.setInt(4, idestado);
                cs.setInt(5, pidtipocomp);
                cs.setString(6, pFechaFin);
                ResultSet rs = cs.executeQuery();
                while (rs.next())
                {
                    oCEPedido = new CEPedidoMatriz();
                    oCEPedido.setIdPedido(rs.getInt(1));
                    oCEPedido.setCodigo(rs.getString(2));
                    oCEPedido.setFecha(rs.getString(3));
                    oCEPedido.setTotalPagar(rs.getFloat(4));
                    oCEPedido.setMoneda(rs.getString(5));
                    oCEPedido.setCliente(rs.getString(6));
                    oCEPedido.setTipoComprobante(rs.getString(7));
                    oCEPedido.setUltimoEstado(rs.getString(8));
                    lista.add(oCEPedido);


                }

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
                Logger.getLogger(CDPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static List<CEPedidoDetalle> ListarPedidoDetalle(long IdPedido)
    {
        Connection cnn = ConexionBD.obtenerConexion();
        try
        {
            List<CEPedidoDetalle> lista = new ArrayList<CEPedidoDetalle>();

            CEPedidoDetalle oCEPedidoDetalle = null;
            if (cnn != null)
            {
                CallableStatement cs = null;
                cs = cnn.prepareCall("{CALL  VTASPRCNSPedidoDetalle(?)}");
                cs.setLong(1, IdPedido);
                ResultSet rs = cs.executeQuery();
                while (rs.next())
                {

                    oCEPedidoDetalle = new CEPedidoDetalle();
                    oCEPedidoDetalle.setIdPedidoDetalle(rs.getLong(1));
                    oCEPedidoDetalle.setIdProducto(rs.getInt(2));
                    oCEPedidoDetalle.setIdAlmacen(rs.getInt(3));
                    oCEPedidoDetalle.setIdUnidadMedidaVenta(rs.getInt(4));
                    oCEPedidoDetalle.setCantidad(rs.getFloat(5));
                    oCEPedidoDetalle.setPrecio(rs.getFloat(6));
                    oCEPedidoDetalle.setImporteSinDescuento(rs.getFloat(7));
                    oCEPedidoDetalle.setIdTipoDescuento(rs.getInt(8));
                    oCEPedidoDetalle.setDescuento(rs.getFloat(9));
                    oCEPedidoDetalle.setDescuentoEnValores(rs.getFloat(10));
                    oCEPedidoDetalle.setImporteConDescuento(rs.getFloat(11));
                    oCEPedidoDetalle.setProducto(rs.getString(12));
                    oCEPedidoDetalle.setUnidadMedida(rs.getString(13));
                    oCEPedidoDetalle.setAlmacen(rs.getString(14));
                    oCEPedidoDetalle.setSinoImpuesto(rs.getBoolean(15));
                    oCEPedidoDetalle.setExonerado(rs.getFloat(16));
                    oCEPedidoDetalle.setPrecioSinRedon(rs.getFloat(17));
                    oCEPedidoDetalle.setEqivalenciaUMB(rs.getFloat(18));
                    oCEPedidoDetalle.setPrecioConDesc(rs.getFloat(19));
                    oCEPedidoDetalle.setConPercepcion(rs.getBoolean(20));
                    oCEPedidoDetalle.setCantidadBaseAnte(rs.getFloat(21));
                    lista.add(oCEPedidoDetalle);
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
                Logger.getLogger(CDPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static float StockProductoPorPedido(long pIdProducto,int pIdALmacen)
    {
        Connection cnn = ConexionBD.obtenerConexion();
        float stock=0;
        try
        {
            List<CEPedidoDetalle> lista = new ArrayList<CEPedidoDetalle>();

            CEPedidoDetalle oCEPedidoDetalle = null;
            if (cnn != null)
            {
                
                CallableStatement cs = null;
                cs = cnn.prepareCall("{CALL  ALMSPRCNSStockPorPedido(?,?)}");
                cs.setLong(1, pIdProducto);
                cs.setLong(2, pIdALmacen);

                ResultSet rs = cs.executeQuery();
                while (rs.next())
                {

                    stock=rs.getFloat(1);

                }
                return stock;
            }
            return stock;
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
                Logger.getLogger(CDPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public static List<CEPedidoDetalle> ListarPedidoDetalleAlmacen(long IdPedido)
    {
        Connection cnn = ConexionBD.obtenerConexion();
        try
        {
            List<CEPedidoDetalle> lista = new ArrayList<CEPedidoDetalle>();

            CEPedidoDetalle oCEPedidoDetalle = null;
            if (cnn != null)
            {
                CallableStatement cs = null;
                cs = cnn.prepareCall("{CALL  VTASPRCNSPedidoDetalleAlmacen(?)}");
                cs.setLong(1, IdPedido);
                ResultSet rs = cs.executeQuery();
                while (rs.next())
                {

                    oCEPedidoDetalle = new CEPedidoDetalle();
                    oCEPedidoDetalle.setIdPedidoDetalle(rs.getLong(1));
                    oCEPedidoDetalle.setIdProducto(rs.getInt(2));
                    oCEPedidoDetalle.setIdAlmacen(rs.getInt(3));
                    oCEPedidoDetalle.setIdUnidadMedidaVenta(rs.getInt(4));
                    oCEPedidoDetalle.setCantidad(rs.getFloat(5));
                   // oCEPedidoDetalle.setCostoUnitario(rs.getFloat(6));
                    oCEPedidoDetalle.setProducto(rs.getString(6));
                    oCEPedidoDetalle.setUnidadMedida(rs.getString(7));
                    oCEPedidoDetalle.setAlmacen(rs.getString(8));
                    lista.add(oCEPedidoDetalle);
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
                Logger.getLogger(CDPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    



    
}

