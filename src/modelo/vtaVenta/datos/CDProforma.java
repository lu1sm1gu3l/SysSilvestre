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
import modelo.vtaVenta.entidad.CEProforma;
import modelo.vtaVenta.entidad.CEProformaDetalle;
import modelo.vtaVenta.entidad.CEProformaMatriz;

/**
 *
 * @author Joel
 */
public class CDProforma
{

    public static String InsProforma(CEProformaMatriz oCEProforma)
    {
        Connection conexion;
        conexion = ConexionBD.obtenerConexion();//
        try
        {
            CallableStatement sentenciaProforma;
            CallableStatement sentenciaProformaDetalle;
            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            String sql= "{call VTASPRINSProforma(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            sentenciaProforma = conexion.prepareCall(sql);
            sentenciaProforma.setInt(1, oCEProforma.getIdProforma());
            sentenciaProforma.setLong(2, oCEProforma.getIdCliente());
            sentenciaProforma.setLong(3, oCEProforma.getIdSucursal());
            sentenciaProforma.setFloat(4, oCEProforma.getDescuento());
            sentenciaProforma.setFloat(5, oCEProforma.getSubTotalNeto());
            sentenciaProforma.setInt(6, oCEProforma.getIdUltimoEstado());
            sentenciaProforma.setInt(7, oCEProforma.getIdTipoComprobante());
            sentenciaProforma.setInt(8, oCEProforma.getIdCondicion());
            sentenciaProforma.setInt(9, oCEProforma.getIdMoneda());
            sentenciaProforma.setInt(10, oCEProforma.getIdTipoDescuento());
            sentenciaProforma.registerOutParameter(11, java.sql.Types.BIGINT);
            sentenciaProforma.registerOutParameter(12, java.sql.Types.VARCHAR);
            sentenciaProforma.setInt(13, oCEProforma.getIdEmpleado());
            sentenciaProforma.setInt(14, oCEProforma.getIdUsuario());
            sentenciaProforma.setFloat(15, oCEProforma.getTipoCambio());
            sentenciaProforma.setFloat(16, oCEProforma.getSubtotalBruto());
            sentenciaProforma.setFloat(17, oCEProforma.getDescuentoEnSubtotal());
            sentenciaProforma.setFloat(18, oCEProforma.getDescuentoTotal());
            sentenciaProforma.setFloat(19, oCEProforma.getDescuentoValores());
            sentenciaProforma.setFloat(20, oCEProforma.getIGVActual());
            sentenciaProforma.setFloat(21, oCEProforma.getIGV());
            sentenciaProforma.setFloat(22, oCEProforma.getSubtotalNetoSinIGV());
            sentenciaProforma.setFloat(23, oCEProforma.getMontoTotal());
            sentenciaProforma.setFloat(24, oCEProforma.getTotalPagar());
            sentenciaProforma.setString(25, oCEProforma.getFecha());
            sentenciaProforma.setString(26,oCEProforma.getFechaVencimiento());
            sentenciaProforma.setFloat(27, oCEProforma.getMontoPercepcion());
            sentenciaProforma.setFloat(28, oCEProforma.getTasaPercepcion());

            sentenciaProforma.executeUpdate();
            int id_Proforma = sentenciaProforma.getInt(11);
            oCEProforma.setIdProforma(id_Proforma);
            String CodigoProforma = sentenciaProforma.getString(12);
            for (CEProformaDetalle oCEProformaDetalle : oCEProforma.getLstProformaDetalle())
            {
                String sql_ = "{call VTASPRINSProformaDetalle(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                int id_ProformaDetalle = oCEProformaDetalle.getIdProformaDetalle();
                sentenciaProformaDetalle = conexion.prepareCall(sql_);
                sentenciaProformaDetalle.setLong(1, id_ProformaDetalle);
                sentenciaProformaDetalle.setInt(2, id_Proforma);
                sentenciaProformaDetalle.setFloat(3, oCEProformaDetalle.getCantidad());
                sentenciaProformaDetalle.setFloat(4, oCEProformaDetalle.getDescuento());
                sentenciaProformaDetalle.setLong(5, oCEProformaDetalle.getIdProducto());
                sentenciaProformaDetalle.setInt(6, oCEProformaDetalle.getIdTipoDescuento());
                sentenciaProformaDetalle.setFloat(7, oCEProformaDetalle.getPrecio());
                sentenciaProformaDetalle.setFloat(8, oCEProformaDetalle.getIdAlmacen());
                sentenciaProformaDetalle.setFloat(9, oCEProformaDetalle.getIdUnidadMedidaVenta());
                sentenciaProformaDetalle.setFloat(10, oCEProformaDetalle.getDescuentoEnValores());
                sentenciaProformaDetalle.setFloat(11, oCEProformaDetalle.getImportelSinDescuento());
                sentenciaProformaDetalle.setFloat(12, oCEProformaDetalle.getImporteConDescuento());
                sentenciaProformaDetalle.setFloat(13, oCEProformaDetalle.getExonerado());
                sentenciaProformaDetalle.setBoolean(14, oCEProformaDetalle.isSinoImpuesto());
                sentenciaProformaDetalle.setFloat(15, oCEProformaDetalle.getPrecioSinRedon());
                sentenciaProformaDetalle.setFloat(16, oCEProformaDetalle.getEqivalenciaUMB());
                sentenciaProformaDetalle.setFloat(17, oCEProformaDetalle.getPrecioConDesc());
                sentenciaProformaDetalle.setBoolean(18, oCEProformaDetalle.isConPercepcion());
                sentenciaProformaDetalle.executeUpdate();

            }
            conexion.commit();
            return CodigoProforma;
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

    public static int UPDProforma(CEProforma oCEProforma)
    {
        Connection conexion;
        conexion = ConexionBD.obtenerConexion();//5 elimina
        try
        {
            CallableStatement sentenciaProforma;
            CallableStatement sentenciaProformaDetalle;
            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            String sql= "{call VTASPRUPDProforma(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            sentenciaProforma = conexion.prepareCall(sql);
            sentenciaProforma.setLong(1, oCEProforma.getIdProforma());
            sentenciaProforma.setLong(2, oCEProforma.getIdCliente());
            sentenciaProforma.setLong(3, oCEProforma.getIdSucursal());
            sentenciaProforma.setFloat(4, oCEProforma.getDescuento());
            sentenciaProforma.setFloat(5, oCEProforma.getSubtotalNetoSinIGV());
            sentenciaProforma.setInt(6, oCEProforma.getIdUltimoEstado());
            sentenciaProforma.setInt(7, oCEProforma.getIdTipoComprobante());
            sentenciaProforma.setInt(8, oCEProforma.getIdCondicion());
            sentenciaProforma.setInt(9, oCEProforma.getIdMoneda());
            sentenciaProforma.setInt(10, oCEProforma.getIdTipoDescuento());
            sentenciaProforma.setInt(11, oCEProforma.getIdEmpleado());
            sentenciaProforma.setInt(12, oCEProforma.getIdUsuario());
            sentenciaProforma.setFloat(13, oCEProforma.getTipoCambio());
            sentenciaProforma.setFloat(14, oCEProforma.getSubtotalBruto());
            sentenciaProforma.setFloat(15, oCEProforma.getDescuentoEnSubtotal());
            sentenciaProforma.setFloat(16, oCEProforma.getDescuentoTotal());
            sentenciaProforma.setFloat(17, oCEProforma.getDescuentoValores());
            sentenciaProforma.setFloat(18, oCEProforma.getIGVActual());
            sentenciaProforma.setFloat(19, oCEProforma.getIGV());
            sentenciaProforma.setFloat(20, oCEProforma.getSubtotalNetoSinIGV());
            sentenciaProforma.setFloat(21, oCEProforma.getMontoTotal());
            sentenciaProforma.setFloat(22, oCEProforma.getTotalPagar());
            sentenciaProforma.setFloat(23, oCEProforma.getMontoPercepcion());
            sentenciaProforma.setString(24,oCEProforma.getFechaVencimiento());
            sentenciaProforma.setFloat(25, oCEProforma.getTasaPercepcion());
            sentenciaProforma.executeUpdate();
            long id_Proforma = oCEProforma.getIdProforma();
            for (CEProformaDetalle oCEProformaDetalle : oCEProforma.getLstProformaDetalle())
            {
                String sql_ = "{call VTASPRABMProformaDetalle(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                long id_ProformaDetalle = oCEProformaDetalle.getIdProformaDetalle();
                sentenciaProformaDetalle = conexion.prepareCall(sql_);
                sentenciaProformaDetalle.setLong(1, id_ProformaDetalle);
                sentenciaProformaDetalle.setFloat(2, oCEProformaDetalle.getCantidad());
                sentenciaProformaDetalle.setFloat(3, oCEProformaDetalle.getDescuento());
                sentenciaProformaDetalle.setLong(4, oCEProformaDetalle.getIdProducto());
                sentenciaProformaDetalle.setInt(5, oCEProformaDetalle.getIdTipoDescuento());
                sentenciaProformaDetalle.setFloat(6, oCEProformaDetalle.getPrecio());
                sentenciaProformaDetalle.setFloat(7, oCEProformaDetalle.getIdAlmacen());
                sentenciaProformaDetalle.setFloat(8, oCEProformaDetalle.getIdUnidadMedidaVenta());
                sentenciaProformaDetalle.setFloat(9, oCEProformaDetalle.getDescuentoEnValores());
                sentenciaProformaDetalle.setFloat(10, oCEProformaDetalle.getImportelSinDescuento());
                sentenciaProformaDetalle.setFloat(11, oCEProformaDetalle.getImporteConDescuento());
                sentenciaProformaDetalle.setFloat(12, oCEProformaDetalle.getExonerado());
                sentenciaProformaDetalle.setBoolean(13, oCEProformaDetalle.isSinoImpuesto());
                sentenciaProformaDetalle.setFloat(14, oCEProformaDetalle.getPrecioSinRedon());
                sentenciaProformaDetalle.setFloat(15, oCEProformaDetalle.getIdAbm());
                sentenciaProformaDetalle.setLong(16, id_Proforma);
                sentenciaProformaDetalle.setFloat(17, oCEProformaDetalle.getEqivalenciaUMB());
                sentenciaProformaDetalle.setFloat(18, oCEProformaDetalle.getPrecioConDesc());
                sentenciaProformaDetalle.setBoolean(19, oCEProformaDetalle.isConPercepcion());
                sentenciaProformaDetalle.executeUpdate();

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

    public static int InsProformaEstado(CEProforma oCEProforma)
    {
        Connection conexion;
        conexion = ConexionBD.obtenerConexion();//5 elimina
        try
        {
            CallableStatement sentenciaProforma;            

            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            String sql = "{call VTASPRINSProformaEstado(?,?,?,?,?)}";
            sentenciaProforma = conexion.prepareCall(sql);
            sentenciaProforma.setLong(1, oCEProforma.getIdProforma());
            sentenciaProforma.setInt(2, oCEProforma.getIdUltimoEstado());
            sentenciaProforma.setInt(3, oCEProforma.getIdUsuario());
            sentenciaProforma.setInt(4, oCEProforma.getIdEmpleado());
            sentenciaProforma.setString(5, oCEProforma.getObservacion());
            sentenciaProforma.executeUpdate();

            
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

    public static List<CEProformaMatriz> ListarProformasPendientes(int pIdCns, int pIdSucursal)
    {
        Connection cnn = ConexionBD.obtenerConexion();
        try
        {

            List<CEProformaMatriz> oLista = new ArrayList<CEProformaMatriz>();
            if (cnn != null)
            {
                CallableStatement cs = null;
                cs = cnn.prepareCall("{CALL  VTASPRCNSProforma(?,?)}");
                cs.setInt(1, pIdCns);
                cs.setInt(2, pIdSucursal);
                ResultSet rs = cs.executeQuery();
                while (rs.next())
                {
                    CEProformaMatriz oCEProformaMatriz = new CEProformaMatriz();
                    oCEProformaMatriz.setIdProforma(rs.getInt(1));
                    oCEProformaMatriz.setIdCliente(rs.getInt(2));
                    oCEProformaMatriz.setIdSucursal(rs.getInt(3));
                    oCEProformaMatriz.setFecha(rs.getString(4));
                    oCEProformaMatriz.setIdDescuento(rs.getInt(5));
                    oCEProformaMatriz.setDescuento(rs.getFloat(6));
                    oCEProformaMatriz.setDescuentoEnValores(rs.getFloat(7));
                    oCEProformaMatriz.setDescuentoEnSubtotal(rs.getFloat(8));
                    oCEProformaMatriz.setDescuentoTotal(rs.getFloat(9));
                    oCEProformaMatriz.setSubTotalNeto(rs.getFloat(10));
                    oCEProformaMatriz.setSubTotalNetoSinIGV(rs.getFloat(11));
                    oCEProformaMatriz.setIGVActual(rs.getFloat(12));
                    oCEProformaMatriz.setIGV(rs.getFloat(13));
                    oCEProformaMatriz.setISC(rs.getFloat(14));
                    oCEProformaMatriz.setMontoTotal(rs.getFloat(15));
                    oCEProformaMatriz.setIdTipoComprobante(rs.getInt(16));
                    oCEProformaMatriz.setIdCondicion(rs.getInt(17));
                    oCEProformaMatriz.setIdMoneda(rs.getInt(18));
                    oCEProformaMatriz.setTipoCambio(rs.getFloat(19));
                    oCEProformaMatriz.setCodigo(rs.getString(20));
                    oCEProformaMatriz.setCliente(rs.getString(21));
                    oCEProformaMatriz.setMoneda(rs.getString(22));
                    oCEProformaMatriz.setTipoComprobante(rs.getString(23));
                    oCEProformaMatriz.setDireccion(rs.getString(24));
                    oCEProformaMatriz.setDNI(rs.getString(25));
                    oCEProformaMatriz.setCondicion(rs.getString(26));
                    oCEProformaMatriz.setTipoDescuento(rs.getString(27));
                    oCEProformaMatriz.setEmpleado(rs.getString(28));
                    oCEProformaMatriz.setIdEmpleado(rs.getInt(29));
                    oCEProformaMatriz.setSucursal(rs.getString(30));
                    oCEProformaMatriz.setSubtotalBruto(rs.getFloat(31));
                    oCEProformaMatriz.setTotalPagar(rs.getFloat(32));
                    oCEProformaMatriz.setRUC(rs.getString(33));
                    oLista.add(oCEProformaMatriz);
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
                Logger.getLogger(CDProforma.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }




    public static CEProformaMatriz ConsultarProformasPorId(long id)
    {
        Connection cnn = ConexionBD.obtenerConexion();
        try
        {

            CEProformaMatriz oCEProformaMatriz = null;
            if (cnn != null)
            {
                CallableStatement cs = null;
                cs = cnn.prepareCall("{CALL  VTASPRCNSProformaPorCodigo(?,?,?,?,?,?)}");
                cs.setInt(1, 1);
                cs.setString(2, null);
                cs.setLong(3, id);
                cs.setInt(4, 0);
                cs.setInt(5, 0);
                cs.setString(6, null);
                ResultSet rs = cs.executeQuery();
                while (rs.next())
                {
                    oCEProformaMatriz = new CEProformaMatriz();
                    oCEProformaMatriz.setIdProforma(rs.getInt(1));
                    oCEProformaMatriz.setIdCliente(rs.getInt(2));
                    oCEProformaMatriz.setIdSucursal(rs.getInt(3));
                    oCEProformaMatriz.setFecha(rs.getString(4));
                    oCEProformaMatriz.setDescuento(rs.getFloat(5));
                    oCEProformaMatriz.setIdTipoComprobante(rs.getInt(6));
                    oCEProformaMatriz.setIdCondicion(rs.getInt(7));
                    oCEProformaMatriz.setIdMoneda(rs.getInt(8));
                    oCEProformaMatriz.setIdDescuento(rs.getInt(9));
                    oCEProformaMatriz.setCliente(rs.getString(10));
                    oCEProformaMatriz.setDireccion(rs.getString(11));
                    oCEProformaMatriz.setDNI(rs.getString(12));
                    oCEProformaMatriz.setEmpleado(rs.getString(13));
                    oCEProformaMatriz.setIdUltimoEstado(rs.getInt(14));
                    oCEProformaMatriz.setUltimoEstado(rs.getString(15));
                    oCEProformaMatriz.setSubtotalBruto(rs.getFloat(16));
                    oCEProformaMatriz.setDescuentoEnSubtotal(rs.getFloat(17));
                    oCEProformaMatriz.setDescuentoTotal(rs.getFloat(18));
                    oCEProformaMatriz.setSubTotalNeto(rs.getFloat(19));
                    oCEProformaMatriz.setSubTotalNetoSinIGV(rs.getFloat(20));
                    oCEProformaMatriz.setDescuentoEnValores(rs.getFloat(21));
                    oCEProformaMatriz.setIGV(rs.getFloat(22));
                    oCEProformaMatriz.setISC(rs.getFloat(23));
                    oCEProformaMatriz.setIGVActual(rs.getFloat(24));
                    oCEProformaMatriz.setMontoTotal(rs.getFloat(25));
                    oCEProformaMatriz.setTotalPagar(rs.getFloat(26));
                    oCEProformaMatriz.setTipoCambio(rs.getFloat(27));
                    oCEProformaMatriz.setSucursal(rs.getString(28));
                    oCEProformaMatriz.setCodigo(rs.getString(29));
                    oCEProformaMatriz.setMontoPercepcion(rs.getFloat(30));
                    oCEProformaMatriz.setRUC(rs.getString(31));
                    oCEProformaMatriz.setDate(rs.getDate(32));
                    oCEProformaMatriz.setTasaPercepcion(rs.getFloat(33));

                }
                if (oCEProformaMatriz != null)
                {

                    oCEProformaMatriz.setLstProformaDetalle(ListarProformaDetalle(oCEProformaMatriz.getIdProforma()));
                }
                return oCEProformaMatriz;
            }
            return oCEProformaMatriz;
        }
        catch (SQLException ex)
        {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, "Error al cargar el Proforma ");
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
                Logger.getLogger(CDProforma.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

     public static List<CEProformaMatriz> ListarProforma(String pDato,int opcion,long id,int idestado,int pidtipocomp,String pFechaFin)
    {
        Connection cnn = ConexionBD.obtenerConexion();
        try
        {
            List<CEProformaMatriz> lista=new ArrayList<CEProformaMatriz>();
            CEProformaMatriz oCEProforma = null;
            if (cnn != null)
            {
                CallableStatement cs = null;
                cs = cnn.prepareCall("{CALL  VTASPRCNSProformaPorCodigo(?,?,?,?,?,?)}");
                cs.setInt(1, opcion);
                cs.setString(2, pDato);
                cs.setLong(3, id);
                cs.setInt(4, idestado);
                cs.setInt(5, pidtipocomp);
                cs.setString(6, pFechaFin);
                ResultSet rs = cs.executeQuery();
                while (rs.next())
                {
                    oCEProforma = new CEProformaMatriz();
                    oCEProforma.setIdProforma(rs.getInt(1));
                    oCEProforma.setCodigo(rs.getString(2));
                    oCEProforma.setFecha(rs.getString(3));
                    oCEProforma.setTotalPagar(rs.getFloat(4));
                    oCEProforma.setMoneda(rs.getString(5));
                    oCEProforma.setCliente(rs.getString(6));
                    oCEProforma.setTipoComprobante(rs.getString(7));
                    oCEProforma.setUltimoEstado(rs.getString(8));
                    lista.add(oCEProforma);


                }

            }
            return lista;
        }
        catch (SQLException ex)
        {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, "Error al cargar el Proforma ");
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
                Logger.getLogger(CDProforma.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static List<CEProformaDetalle> ListarProformaDetalle(long IdProforma)
    {
        Connection cnn = ConexionBD.obtenerConexion();
        try
        {
            List<CEProformaDetalle> lista = new ArrayList<CEProformaDetalle>();

            CEProformaDetalle oCEProformaDetalle = null;
            if (cnn != null)
            {
                CallableStatement cs = null;
                cs = cnn.prepareCall("{CALL  VTASPRCNSProformaDetalle(?)}");
                cs.setLong(1, IdProforma);
                ResultSet rs = cs.executeQuery();
                while (rs.next())
                {

                    oCEProformaDetalle = new CEProformaDetalle();
                    oCEProformaDetalle.setIdProformaDetalle(rs.getInt(1));
                    oCEProformaDetalle.setIdProducto(rs.getInt(2));
                    oCEProformaDetalle.setIdAlmacen(rs.getInt(3));
                    oCEProformaDetalle.setIdUnidadMedidaVenta(rs.getInt(4));
                    oCEProformaDetalle.setCantidad(rs.getFloat(5));
                    oCEProformaDetalle.setPrecio(rs.getFloat(6));
                    oCEProformaDetalle.setImporteSinDescuento(rs.getFloat(7));
                    oCEProformaDetalle.setIdTipoDescuento(rs.getInt(8));
                    oCEProformaDetalle.setDescuento(rs.getFloat(9));
                    oCEProformaDetalle.setDescuentoEnValores(rs.getFloat(10));
                    oCEProformaDetalle.setImporteConDescuento(rs.getFloat(11));
                    oCEProformaDetalle.setProducto(rs.getString(12));
                    oCEProformaDetalle.setUnidadMedida(rs.getString(13));
                    oCEProformaDetalle.setAlmacen(rs.getString(14));
                    oCEProformaDetalle.setSinoImpuesto(rs.getBoolean(15));
                    oCEProformaDetalle.setExonerado(rs.getFloat(16));
                    oCEProformaDetalle.setPrecioSinRedon(rs.getFloat(17));
                    oCEProformaDetalle.setEqivalenciaUMB(rs.getFloat(18));
                    oCEProformaDetalle.setPrecioConDesc(rs.getFloat(19));
                    oCEProformaDetalle.setConPercepcion(rs.getBoolean(20));
                    lista.add(oCEProformaDetalle);
                }
                return lista;
            }
            return lista;
        }
        catch (SQLException ex)
        {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, "Error al cargar el Proforma ");
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
                Logger.getLogger(CDProforma.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static float StockProductoPorProforma(long pIdProducto,int pIdALmacen)
    {
        Connection cnn = ConexionBD.obtenerConexion();
        float stock=0;
        try
        {
            List<CEProformaDetalle> lista = new ArrayList<CEProformaDetalle>();

            CEProformaDetalle oCEProformaDetalle = null;
            if (cnn != null)
            {
                
                CallableStatement cs = null;
                cs = cnn.prepareCall("{CALL  ALMSPRCNSStockPorProforma(?,?)}");
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
            JOptionPane.showMessageDialog(null, "Error al cargar el Proforma ");
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
                Logger.getLogger(CDProforma.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public static List<CEProformaDetalle> ListarProformaDetalleAlmacen(long IdProforma)
    {
        Connection cnn = ConexionBD.obtenerConexion();
        try
        {
            List<CEProformaDetalle> lista = new ArrayList<CEProformaDetalle>();

            CEProformaDetalle oCEProformaDetalle = null;
            if (cnn != null)
            {
                CallableStatement cs = null;
                cs = cnn.prepareCall("{CALL  VTASPRCNSProformaDetalleAlmacen(?)}");
                cs.setLong(1, IdProforma);
                ResultSet rs = cs.executeQuery();
                while (rs.next())
                {

                    oCEProformaDetalle = new CEProformaDetalle();
                    oCEProformaDetalle.setIdProformaDetalle(rs.getInt(1));
                    oCEProformaDetalle.setIdProducto(rs.getInt(2));
                    oCEProformaDetalle.setIdAlmacen(rs.getInt(3));
                    oCEProformaDetalle.setIdUnidadMedidaVenta(rs.getInt(4));
                    oCEProformaDetalle.setCantidad(rs.getFloat(5));
                   // oCEProformaDetalle.setCostoUnitario(rs.getFloat(6));
                    oCEProformaDetalle.setProducto(rs.getString(6));
                    oCEProformaDetalle.setUnidadMedida(rs.getString(7));
                    oCEProformaDetalle.setAlmacen(rs.getString(8));
                    lista.add(oCEProformaDetalle);
                }
                return lista;
            }
            return lista;
        }
        catch (SQLException ex)
        {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, "Error al cargar el Proforma ");
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
                Logger.getLogger(CDProforma.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    



    
}

