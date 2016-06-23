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
import modelo.almAlmacen.entidad.CESalidaProducto;
import modelo.almAlmacen.entidad.CESalidaProductoDetalle;
import modelo.vtaVenta.entidad.CEPedidoMatriz;
import view.FrmSistemaMenu;


/**
 *
 * @author Joel
 */
public class CDSalidaProducto
{

    public static String InsSalidaProducto(CESalidaProducto oCESalidaProducto)
    {
        String   mensajeStock="";
        Connection conexion;
        conexion = ConexionBD.obtenerConexion();//5 elimina
        try
        {
            CallableStatement sentenciaSalidaProducto;
            CallableStatement sentenciaSalidaProductoDetalle;
            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            String sql= "{call ALMSPRINSSalidaProducto(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            sentenciaSalidaProducto = conexion.prepareCall(sql);
            sentenciaSalidaProducto.setLong(1, oCESalidaProducto.getIdCliente());
            sentenciaSalidaProducto.setInt(2, oCESalidaProducto.getIdSucursal());
            sentenciaSalidaProducto.setInt(3, oCESalidaProducto.getIdTipoIngresoSalida());
            sentenciaSalidaProducto.setInt(4, oCESalidaProducto.getIdEmpleado());
            sentenciaSalidaProducto.setInt(5, oCESalidaProducto.getIdUsuario());
            sentenciaSalidaProducto.setString(6, oCESalidaProducto.getFecha());
            sentenciaSalidaProducto.setLong(7, oCESalidaProducto.getIdPedido());
            sentenciaSalidaProducto.registerOutParameter(8, java.sql.Types.BIGINT);// id
            sentenciaSalidaProducto.registerOutParameter(9, java.sql.Types.VARCHAR);// numero
            sentenciaSalidaProducto.setString(10, oCESalidaProducto.getNumeroDocumento());
            sentenciaSalidaProducto.setInt(11, oCESalidaProducto.getIdTipoComprobante());
            sentenciaSalidaProducto.setInt(12, oCESalidaProducto.getIdAlmacen());
            sentenciaSalidaProducto.setString(13,oCESalidaProducto.getObservacion());

            sentenciaSalidaProducto.executeUpdate();
            long id_SalidaProducto = sentenciaSalidaProducto.getInt(8);
            String CodigoSalidaProducto = sentenciaSalidaProducto.getString(9);
            oCESalidaProducto.setNumeroSalida(CodigoSalidaProducto);
            int contarProdSinStock=0;
            for (CESalidaProductoDetalle oCESalidaProductoDetalle : oCESalidaProducto.getLstSalidaDetalle())
            {
                String sql_ = "{call ALMSPRINSSalidaProductoDetalle(?,?,?,?,?,?,?,?,?)}";
             //   long id_SalidaProductoDetalle = oCESalidaProductoDetalle.getIdSalidaProductoDetalle();
                sentenciaSalidaProductoDetalle = conexion.prepareCall(sql_);
                sentenciaSalidaProductoDetalle.setLong(1, id_SalidaProducto);
                sentenciaSalidaProductoDetalle.setLong(2, oCESalidaProductoDetalle.getIdProducto());
                sentenciaSalidaProductoDetalle.setInt(3,oCESalidaProducto.getIdAlmacen());
                sentenciaSalidaProductoDetalle.setInt(4, oCESalidaProductoDetalle.getIdUnidadMedida());
                sentenciaSalidaProductoDetalle.setFloat(5, oCESalidaProductoDetalle.getCantidad());
                sentenciaSalidaProductoDetalle.setInt(6,0);
                sentenciaSalidaProductoDetalle.setString(7,"");
                sentenciaSalidaProductoDetalle.setInt(8,oCESalidaProducto.getIdTipoIngresoSalida());
                sentenciaSalidaProductoDetalle.setInt(9,0);
                sentenciaSalidaProductoDetalle.executeUpdate();
                int actualizar = sentenciaSalidaProductoDetalle.getInt(6);
                if(actualizar==0)
                {
                    mensajeStock=mensajeStock+sentenciaSalidaProductoDetalle.getString(7);
                    contarProdSinStock++;
                }
            }
            if(contarProdSinStock>0)
            {
                conexion.rollback();
                return mensajeStock;
            }
            conexion.commit();
            return "OK";
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
            return "Error";
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


    public static String InsSalidaProductoPorCobro(Connection conexion,List<CESalidaProducto> lstSalida)
    {
        try
        {
            String   mensajeStock="";
            for(CESalidaProducto oCESalidaProducto:lstSalida ){

            CallableStatement sentenciaSalidaProducto;
            CallableStatement sentenciaSalidaProductoDetalle;
            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            String sql= "{call ALMSPRINSSalidaProducto(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            sentenciaSalidaProducto = conexion.prepareCall(sql);
            sentenciaSalidaProducto.setLong(1, oCESalidaProducto.getIdCliente());
            sentenciaSalidaProducto.setInt(2, oCESalidaProducto.getIdSucursal());
            sentenciaSalidaProducto.setInt(3, oCESalidaProducto.getIdTipoIngresoSalida());
            sentenciaSalidaProducto.setInt(4, oCESalidaProducto.getIdEmpleado());
            sentenciaSalidaProducto.setInt(5, oCESalidaProducto.getIdUsuario());
            sentenciaSalidaProducto.setString(6, oCESalidaProducto.getFecha());
            sentenciaSalidaProducto.setLong(7, oCESalidaProducto.getIdPedido());
            sentenciaSalidaProducto.registerOutParameter(8, java.sql.Types.BIGINT);// id
            sentenciaSalidaProducto.registerOutParameter(9, java.sql.Types.VARCHAR);// numero
            sentenciaSalidaProducto.setString(10, oCESalidaProducto.getNumeroDocumento());
            sentenciaSalidaProducto.setInt(11, oCESalidaProducto.getIdTipoComprobante());
            sentenciaSalidaProducto.setInt(12, oCESalidaProducto.getIdAlmacen());
            sentenciaSalidaProducto.setString(13,oCESalidaProducto.getObservacion());

            sentenciaSalidaProducto.executeUpdate();
            long id_SalidaProducto = sentenciaSalidaProducto.getInt(8);
            int contarProdSinStock=0;
            for (CESalidaProductoDetalle oCESalidaProductoDetalle : oCESalidaProducto.getLstSalidaDetalle())
            {
                String sql_ = "{call ALMSPRINSSalidaProductoDetalle(?,?,?,?,?,?,?,?,?)}";
             //   long id_SalidaProductoDetalle = oCESalidaProductoDetalle.getIdSalidaProductoDetalle();
                sentenciaSalidaProductoDetalle = conexion.prepareCall(sql_);
                sentenciaSalidaProductoDetalle.setLong(1, id_SalidaProducto);
                sentenciaSalidaProductoDetalle.setLong(2, oCESalidaProductoDetalle.getIdProducto());
                sentenciaSalidaProductoDetalle.setInt(3,oCESalidaProducto.getIdAlmacen());
                sentenciaSalidaProductoDetalle.setInt(4, oCESalidaProductoDetalle.getIdUnidadMedida());
                sentenciaSalidaProductoDetalle.setFloat(5, oCESalidaProductoDetalle.getCantidad());
                sentenciaSalidaProductoDetalle.setInt(6, 1);
                sentenciaSalidaProductoDetalle.setString(7, "");
                sentenciaSalidaProductoDetalle.setInt(8,  oCESalidaProducto.getIdTipoIngresoSalida());
                sentenciaSalidaProductoDetalle.setInt(9,0);
                
                sentenciaSalidaProductoDetalle.executeUpdate();

                int actualizar = sentenciaSalidaProductoDetalle.getInt(6);
                if(actualizar==0)
                {
                    mensajeStock=mensajeStock+sentenciaSalidaProductoDetalle.getString(7);
                    contarProdSinStock++;
                }
            }
            if(contarProdSinStock>0)
            {
                return mensajeStock;
            }

            }
           // conexion.commit();
            return "OK";
        }
        catch (Exception e)
        {
            System.out.print(e);

            return "Error";
        }


    }
    public static int UPDSalidaProducto(CESalidaProducto oCESalidaProducto)
    {
        Connection conexion;
        conexion = ConexionBD.obtenerConexion();//5 elimina
        try
        {
            CallableStatement sentenciaSalidaProducto;
            CallableStatement sentenciaSalidaProductoDetalle;
            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            String sql= "{call ALMSPRUPDSalidaProducto(?,?,?,?,?,?,?)}";
            sentenciaSalidaProducto = conexion.prepareCall(sql);
            sentenciaSalidaProducto.setLong(1, oCESalidaProducto.getIdCliente());
            sentenciaSalidaProducto.setInt(2, oCESalidaProducto.getIdEmpleado());
            sentenciaSalidaProducto.setInt(3, oCESalidaProducto.getIdUsuario());
            sentenciaSalidaProducto.setString(4, oCESalidaProducto.getFecha());
            sentenciaSalidaProducto.setLong(5, oCESalidaProducto.getIdSalidaProducto());// idSalidaProd
            sentenciaSalidaProducto.setString(6, oCESalidaProducto.getNumeroDocumento());// numero Doc
            sentenciaSalidaProducto.setString(7,oCESalidaProducto.getObservacion());

            sentenciaSalidaProducto.executeUpdate();
            long id_SalidaProducto = oCESalidaProducto.getIdSalidaProducto();
            for (CESalidaProductoDetalle oCESalidaProductoDetalle : oCESalidaProducto.getLstSalidaDetalle())
            {
                String sql_ = "{call ALMSPRUPDSalidaProductoDetalle(?,?,?,?,?,?,?,?,?,?)}";
             //   long id_SalidaProductoDetalle = oCESalidaProductoDetalle.getIdSalidaProductoDetalle();
                sentenciaSalidaProductoDetalle = conexion.prepareCall(sql_);
                sentenciaSalidaProductoDetalle.setLong(1, id_SalidaProducto);
                sentenciaSalidaProductoDetalle.setLong(2, oCESalidaProductoDetalle.getIdProducto());
                sentenciaSalidaProductoDetalle.setInt(3, oCESalidaProductoDetalle.getIdAlmacen());
                sentenciaSalidaProductoDetalle.setInt(4, oCESalidaProductoDetalle.getIdUnidadMedida());
                sentenciaSalidaProductoDetalle.setFloat(5, oCESalidaProductoDetalle.getCantidad());
                sentenciaSalidaProductoDetalle.setFloat(6, oCESalidaProductoDetalle.getCosto());
                sentenciaSalidaProductoDetalle.setFloat(7, oCESalidaProductoDetalle.getImporte());
                sentenciaSalidaProductoDetalle.setInt(8,  oCESalidaProducto.getIdTipoIngresoSalida());
                sentenciaSalidaProductoDetalle.setLong(9,  oCESalidaProductoDetalle.getIdSalidaProductoDetalle());
                sentenciaSalidaProductoDetalle.setFloat(10,  oCESalidaProductoDetalle.getCantidadAnterior());
                sentenciaSalidaProductoDetalle.executeUpdate();

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

public static List<CEPedidoMatriz> ListaPedidosPendientesASalida(int pIdCns, int pIdSucursal)
    {
        Connection cnn = ConexionBD.obtenerConexion();
        try
        {

            List<CEPedidoMatriz> oLista = new ArrayList<CEPedidoMatriz>();
            if (cnn != null)
            {
                CallableStatement cs = null;
                cs = cnn.prepareCall("{CALL  ALMSPRCNSSalidaPorPedido(?,?)}");
                cs.setInt(1, pIdCns);
                cs.setInt(2, pIdSucursal);
                ResultSet rs = cs.executeQuery();
                while (rs.next())
                {
                    CEPedidoMatriz oCEPedidoMatriz = new CEPedidoMatriz();
                    oCEPedidoMatriz.setIdPedido(rs.getLong(1));
                    oCEPedidoMatriz.setFecha(rs.getString(2));
                    oCEPedidoMatriz.setTipoComprobante(rs.getString(3));
                    oCEPedidoMatriz.setCliente(rs.getString(4));
                    oCEPedidoMatriz.setUltimoEstado(rs.getString(5));
                    oCEPedidoMatriz.setCodigo(rs.getString(6));
                    oCEPedidoMatriz.setNumComprobante(rs.getString(7));
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
                Logger.getLogger(CDSalidaProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

   public static CESalidaProducto ListarSalidaProductoPorCodigo(long id)
    {
        Connection cnn = ConexionBD.obtenerConexion();
        try
        {
            
            CESalidaProducto oCESalidaProducto = null;
            if (cnn != null)
            {
                CallableStatement cs = null;
                cs = cnn.prepareCall("{CALL  ALMSPRCNSSalidaProductoPorCodigo(?,?,?,?,?,?)}");
                 cs.setInt(1, 1);
                cs.setString(2, null);
                cs.setLong(3, id);
                cs.setInt(4, 0);
                cs.setInt(5, 0);
                cs.setString(6, null);
                ResultSet rs = cs.executeQuery();
                while (rs.next())
                {
                    oCESalidaProducto = new CESalidaProducto();
                    oCESalidaProducto.setIdSalidaProducto(rs.getLong(1));
                    oCESalidaProducto.setNumeroDocumento(rs.getString(2));
                    oCESalidaProducto.setNumeroSalida(rs.getString(3));
                    oCESalidaProducto.setFecha(rs.getString(4));
                    oCESalidaProducto.setIdCliente(rs.getInt(5));
                    oCESalidaProducto.setCliente(rs.getString(6));
                    oCESalidaProducto.setDireccion(rs.getString(7));
                    oCESalidaProducto.setDNI(rs.getString(8));
                    oCESalidaProducto.setIdTipoIngresoSalida(rs.getInt(9));
                    oCESalidaProducto.setTipoSalida(rs.getString(10));
                    oCESalidaProducto.setEmpleado(rs.getString(11));
                    oCESalidaProducto.setSucursal(rs.getString(12));
                    oCESalidaProducto.setUltimoIdEstado(rs.getInt(13));
                    oCESalidaProducto.setUltimoEstado(rs.getString(14));
                    oCESalidaProducto.setIdTipoComprobante(rs.getInt(15));
                    oCESalidaProducto.setTipoComprobante(rs.getString(16));
                    oCESalidaProducto.setIdAlmacen(rs.getInt(17));
                    oCESalidaProducto.setObservacion(rs.getString(18));
                }
                if (oCESalidaProducto != null)
                {

                    oCESalidaProducto.setLstSalidaDetalle(ListarSalidaProductoDetalle(oCESalidaProducto.getIdSalidaProducto()));
                }
                return oCESalidaProducto;
            }
            return oCESalidaProducto;
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
                Logger.getLogger(CDSalidaProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static List<CESalidaProducto> ListarSalidasProductosPorCodigo(String pDato,int opcion,long id,int idestado, int idtipocomp,String pFechaFin)
    {
        Connection cnn = ConexionBD.obtenerConexion();
        try
        {
            List<CESalidaProducto> lista=new ArrayList<CESalidaProducto>();
            CESalidaProducto oCESalidaProducto = null;
            if (cnn != null)
            {
                CallableStatement cs = null;
                cs = cnn.prepareCall("{CALL  ALMSPRCNSSalidaProductoPorCodigo(?,?,?,?,?,?)}");
                cs.setInt(1, opcion);
                cs.setString(2, pDato);
                cs.setLong(3, id);
                cs.setInt(4, idestado);
                cs.setInt(5, idtipocomp);
                cs.setString(6, pFechaFin);
                ResultSet rs = cs.executeQuery();
                while (rs.next())
                {
                    oCESalidaProducto = new CESalidaProducto();
                    oCESalidaProducto.setIdSalidaProducto(rs.getLong(1));
                    oCESalidaProducto.setNumeroDocumento(rs.getString(2));
                    oCESalidaProducto.setFecha(rs.getString(3));
                    oCESalidaProducto.setNumeroSalida(rs.getString(4));
                    oCESalidaProducto.setCliente(rs.getString(5));
                    oCESalidaProducto.setTipoComprobante(rs.getString(6));
                    oCESalidaProducto.setUltimoEstado(rs.getString(7));
                    oCESalidaProducto.setTipoSalida(rs.getString(8));
                    lista.add(oCESalidaProducto);


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



        public static List<CESalidaProductoDetalle> ListarSalidaProductoDetalle(long pIdSalidaProducto)
    {
        Connection cnn = ConexionBD.obtenerConexion();
        try
        {
            List<CESalidaProductoDetalle> lista = new ArrayList<CESalidaProductoDetalle>();

            CESalidaProductoDetalle oCESalidaProductoDetalle = null;
            if (cnn != null)
            {
                CallableStatement cs = null;
                cs = cnn.prepareCall("{CALL  ALMSPRCNSSalidaProductoDetalle(?)}");
                cs.setLong(1, pIdSalidaProducto);
                ResultSet rs = cs.executeQuery();
                while (rs.next())
                {
                    oCESalidaProductoDetalle = new CESalidaProductoDetalle();
                    oCESalidaProductoDetalle.setIdSalidaProductoDetalle(rs.getLong(1));
                    oCESalidaProductoDetalle.setIdProducto(rs.getInt(2));
                    oCESalidaProductoDetalle.setIdAlmacen(rs.getInt(3));
                    oCESalidaProductoDetalle.setIdUnidadMedida(rs.getInt(4));
                    oCESalidaProductoDetalle.setCantidad(rs.getFloat(5));
                    oCESalidaProductoDetalle.setProducto(rs.getString(6));
                    oCESalidaProductoDetalle.setUnidadMedida(rs.getString(7));
                    oCESalidaProductoDetalle.setAlmacen(rs.getString(8));
                    oCESalidaProductoDetalle.setCantidadBase(rs.getFloat(9));
                    lista.add(oCESalidaProductoDetalle);
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
                Logger.getLogger(CDSalidaProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
      public static CESalidaProducto ListarPedidosPorId(long pIdOC)
    {
        Connection cnn = ConexionBD.obtenerConexion();
        try
        {

            CESalidaProducto oCESalidaProducto = null;
            if (cnn != null)
            {
                CallableStatement cs = null;
                cs = cnn.prepareCall("{CALL  CMPSPRCNSPedidoPorId(?)}");
                cs.setLong(1, pIdOC);
                ResultSet rs = cs.executeQuery();
                while (rs.next())
                {
                    oCESalidaProducto = new CESalidaProducto();
                    oCESalidaProducto.setIdTipoIngresoSalida(rs.getInt(1));
                    oCESalidaProducto.setIdCliente(rs.getInt(2));
                    oCESalidaProducto.setFecha(rs.getString(3));

                }
                if (oCESalidaProducto != null)
                {

                  //  oCESalidaProducto.setLstPedidoDetalle(ListarPedidoDetalle(oCESalidaProducto.getIdPedido()));
                }
                return oCESalidaProducto;
            }
            return oCESalidaProducto;
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
                Logger.getLogger(CDSalidaProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
      public static int AnularSalidaProducto(CESalidaProducto oCESalidaProducto)
    {
        Connection conexion;
        conexion = ConexionBD.obtenerConexion();//5 elimina
        try
        {
            CallableStatement sentencia;
            CallableStatement sentenciaDet;

            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            String sql = "{call ALMSPRINSSalidaProductoEstado(?,?,?,?,?)}";
            sentencia = conexion.prepareCall(sql);
            sentencia.setLong(1, oCESalidaProducto.getIdSalidaProducto());
            sentencia.setInt(2, 4);
            sentencia.setInt(3,FrmSistemaMenu.oCEUsuario.getIdUsuario());
            sentencia.setInt(4, oCESalidaProducto.getIdEmpleado());
            sentencia.setString(5, oCESalidaProducto.getObservacion());
            sentencia.executeUpdate();
            

            for (CESalidaProductoDetalle oCESalidaProductoDetalle : oCESalidaProducto.getLstSalidaDetalle())
            {
            String sqlDet = "{call ALMSPRUPDAnularSalidaProductoDetalle(?,?,?,?)}";
            sentenciaDet = conexion.prepareCall(sqlDet);
            sentenciaDet.setLong(1, oCESalidaProductoDetalle.getIdProducto());
            sentenciaDet.setInt(2, oCESalidaProductoDetalle.getIdAlmacen());
            sentenciaDet.setFloat(3, oCESalidaProductoDetalle.getCantidad());
            sentenciaDet.setInt(4, oCESalidaProductoDetalle.getIdUnidadMedida());
            sentenciaDet.executeUpdate();
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

       public static CESalidaProductoDetalle VerificarUltimaSalida(long pIdProducto,int pIdAlmacen)
    {
          Connection cnn = ConexionBD.obtenerConexion();
        try
        {

            CESalidaProductoDetalle oCESalidaProductoDetalle = null;
            if (cnn != null)
            {
                CallableStatement cs = null;
                cs = cnn.prepareCall("{CALL  ALMSPRCNSUltimoMovimientoAlmacen(?,?,?)}");
                cs.setLong(1, pIdProducto);
                cs.setInt(2, pIdAlmacen);
                cs.setInt(3, 2);
                ResultSet rs = cs.executeQuery();
                while (rs.next())
                {
                    oCESalidaProductoDetalle = new CESalidaProductoDetalle();
                    oCESalidaProductoDetalle.setIdSalidaProductoDetalle(rs.getLong(1));
                    oCESalidaProductoDetalle.setSalida(rs.getBoolean(2));
                  //  lista.add(oCEIngresoProductoDetalle);
                }
                return oCESalidaProductoDetalle;
            }
            return oCESalidaProductoDetalle;
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



public static boolean VerificarUltimaSalidaPoNC(long pIdProducto,int pIdAlmacen,int pIdNotaCreditDet)
    {
          Connection cnn = ConexionBD.obtenerConexion();
        try
        {

            
            if (cnn != null)
            {
                CallableStatement cs = null;
                cs = cnn.prepareCall("{CALL  ALMSPRCNSUltimoMovimientoAlmacenConNC(?,?,?)}");
                cs.setLong(1, pIdProducto);
                cs.setInt(2, pIdAlmacen);
                cs.setInt(3, pIdNotaCreditDet);
                ResultSet rs = cs.executeQuery();
                while (rs.next())
                {
                    long idSalidaDet=rs.getLong(1);
                    if(idSalidaDet!=0)
                        return true;
                    if(rs.getBoolean(2))
                        return true;
                }
                return false;
            }
            return false;
        }
        catch (SQLException ex)
        {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, "Error al cargar el Pedido ");
            return false;
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



public static String veificarStockPorNC(long pIdProducto,int pIdAlmacen,float pCantidad,int pidUnidaMedida)
    {
          Connection cnn = ConexionBD.obtenerConexion();
        try
        {


            if (cnn != null)
            {
                CallableStatement cs = null;
                cs = cnn.prepareCall("{CALL  ALMSPRCNSStockDiferenciaProducto(?,?,?,?)}");
                cs.setLong(1, pIdProducto);
                cs.setInt(2, pIdAlmacen);
                cs.setFloat(3, pCantidad);
                cs.setInt(4, pidUnidaMedida);
                ResultSet rs = cs.executeQuery();
                while (rs.next())
                {
                    return rs.getString(1);
                }
                return "";
            }
            return "";
        }
        catch (SQLException ex)
        {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, "Error al cargar el Pedido ");
            return "Error...";
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
