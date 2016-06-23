package modelo.cmpCompra.datos;

import modelo.vtaVenta.datos.*;
import controller.acceso.ConexionBD;
import controller.almAlmacen.CCSalidaProducto;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.cmpCompra.entidad.CENotaCreditoProveedor;
import modelo.cmpCompra.entidad.CENotaCreditoProveedorDetalle;
import view.FrmSistemaMenu;


/**
 *
 * @author Katya
 */
public class CDNotaCreditoProveedor {

     public static CENotaCreditoProveedor CosultarNotaCreditoProveedorPorId(long id)
    {
        Connection cnn= ConexionBD.obtenerConexion();
        try
        {

            CENotaCreditoProveedor oCENotaCreditoProveedor=null;
                if(cnn!=null)
                {
                     CallableStatement cs= null;

                     cs = cnn.prepareCall("{CALL  CMPSPRCNSNotaCreditoProvPorCodigo(?,?,?,?,?)}");
                     cs.setInt(1, 1);
                     cs.setString(2,null);
                     cs.setLong(3, id);
                     cs.setInt(4, 0);
                     cs.setString(5,null);
                     ResultSet rs=cs.executeQuery();
                      while(rs.next())
                      {
                       oCENotaCreditoProveedor=new CENotaCreditoProveedor();
                       oCENotaCreditoProveedor.setIdComprobanteCompraRef(rs.getLong(1));
                       oCENotaCreditoProveedor.setIdCondicion(rs.getInt(2));
                       oCENotaCreditoProveedor.setUltimoIdEstado(rs.getInt(3));
                       oCENotaCreditoProveedor.setIdTipoComprobante(rs.getInt(4));
                       oCENotaCreditoProveedor.setNumeroNotaCreditoProveedor(rs.getString(5));
                       oCENotaCreditoProveedor.setIdEmpleado(rs.getInt(6));
                       oCENotaCreditoProveedor.setIdUsuario(rs.getInt(7));
                       oCENotaCreditoProveedor.setIdProveedor(rs.getInt(8));
                       oCENotaCreditoProveedor.setIdSucursal(rs.getInt(9));
                       oCENotaCreditoProveedor.setFecha(rs.getString(10));
                       oCENotaCreditoProveedor.setSubtotalSinIGVNuevo(rs.getFloat(11));
                       oCENotaCreditoProveedor.setIGVNuevo(rs.getFloat(12));
                       oCENotaCreditoProveedor.setISCNuevo(rs.getFloat(13));
                       oCENotaCreditoProveedor.setMontoAcreditar(rs.getFloat(14));
                       oCENotaCreditoProveedor.setIdNotaCreditoProveedor(rs.getInt(15));
                       oCENotaCreditoProveedor.setProveedor(rs.getString(16));
                       oCENotaCreditoProveedor.setRUC(rs.getString(17));
                       oCENotaCreditoProveedor.setDireccion(rs.getString(18));
                       oCENotaCreditoProveedor.setMoneda(rs.getString(19));
                       oCENotaCreditoProveedor.setTipoComprobante(rs.getString(20));
                       oCENotaCreditoProveedor.setIdNotaCreditoProveedor(rs.getInt(21));
                       oCENotaCreditoProveedor.setUltimoEstado(rs.getString(22));
                       oCENotaCreditoProveedor.setIdConcepto(rs.getInt(23));
                       oCENotaCreditoProveedor.setMontoTotalAcreditar(rs.getFloat(24));
                       oCENotaCreditoProveedor.setDate(rs.getDate(25));
                       oCENotaCreditoProveedor.setObservacion(rs.getString(26));
                       oCENotaCreditoProveedor.setMontoPercepcion(rs.getFloat(27));

                      }
                       if(oCENotaCreditoProveedor!=null){
                       oCENotaCreditoProveedor.setLstCENotaCreditoProveedorDetalle(ListarNotaCreditoProveedorDetalle(oCENotaCreditoProveedor.getIdNotaCreditoProveedor(),1));
                       }

                     return oCENotaCreditoProveedor;
                 }
             return oCENotaCreditoProveedor;
        }

        catch(SQLException ex)
        {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, "Error al cargar Nota Credito ");
            return null;
        }
        finally
        {
            try {
                cnn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CDPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

     public static String VerificarNotaCreditoPorCompra(long id)
    {
        Connection cnn= ConexionBD.obtenerConexion();
        try
        {

                if(cnn!=null)
                {
                     CallableStatement cs= null;

                     cs = cnn.prepareCall("{CALL  CMPSPRCNSCodNotaCreditoPorCompra(?)}");
                     cs.setLong(1, id);
                     ResultSet rs=cs.executeQuery();
                      while(rs.next())
                      {
                       return rs.getString(1);
                    }

                 }
             return null;
        }

        catch(SQLException ex)
        {
            System.out.print(ex);
            return null;
        }
        finally
        {
            try {
                cnn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CDPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     public static List<CENotaCreditoProveedorDetalle> ListarNotaCreditoProveedorDetalle(long IdNotaCreditoProveedor,int idCns)
    {
        Connection cnn= ConexionBD.obtenerConexion();
        try
        {
            List<CENotaCreditoProveedorDetalle> lista= new ArrayList<CENotaCreditoProveedorDetalle>();

            CENotaCreditoProveedorDetalle oCENotaCreditoProveedorDetalle=null;
                if(cnn!=null)
                {
                     CallableStatement cs= null;
                     cs = cnn.prepareCall("{CALL  CMPSPRCNSNotaCreditoProveedorDetalle(?,?)}");
                     cs.setLong(1, IdNotaCreditoProveedor);
                     cs.setInt(2, idCns);
                     ResultSet rs=cs.executeQuery();
                      while(rs.next())
                      {

                       oCENotaCreditoProveedorDetalle=new CENotaCreditoProveedorDetalle();
                       oCENotaCreditoProveedorDetalle.setIdNotaCreditoProveedorDetalle(rs.getInt(1));
                       oCENotaCreditoProveedorDetalle.setIdComprobanteDetalleRef(rs.getLong(2));
                       oCENotaCreditoProveedorDetalle.setImporteAnterior(rs.getFloat(2));
                       oCENotaCreditoProveedorDetalle.setCantidadNueva(rs.getFloat(4));
                       oCENotaCreditoProveedorDetalle.setPrecioNuevo(rs.getFloat(5));
                       oCENotaCreditoProveedorDetalle.setImporteNuevo(rs.getFloat(6));
                       oCENotaCreditoProveedorDetalle.setExonerado(rs.getFloat(7));
                       oCENotaCreditoProveedorDetalle.setSinoImpuesto(rs.getBoolean(8));
                       lista.add(oCENotaCreditoProveedorDetalle);
                      }
                     return lista;
                 }
             return lista;
        }

        catch(SQLException ex)
        {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, "Error al cargar el Pedido ");
            return null;
        }
        finally
        {
            try {
                cnn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CDPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     public static String InsNotaCreditoProveedor(CENotaCreditoProveedor oCENotaCreditoProveedor)
    {
       Connection conexion;
       conexion = ConexionBD.obtenerConexion();//5 elimina
        try
          {
            CallableStatement sentencia;
            CallableStatement sentenciaDetalle;
            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);


            String sql= "{call CMPSPRINSNotaCreditoProveedor(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            sentencia = conexion.prepareCall(sql);
            sentencia.setLong(1, oCENotaCreditoProveedor.getIdNotaCreditoProveedor());
            sentencia.setLong(2, oCENotaCreditoProveedor.getIdComprobanteCompraRef());
            sentencia.setInt(3, oCENotaCreditoProveedor.getIdProveedor());
            sentencia.setInt(4, oCENotaCreditoProveedor.getIdSucursal());
            sentencia.setFloat(5, oCENotaCreditoProveedor.getSubtotalSinIGVNuevo());
            sentencia.setInt(6, oCENotaCreditoProveedor.getUltimoIdEstado());
            sentencia.setInt(7, oCENotaCreditoProveedor.getIdTipoComprobante());
            sentencia.setInt(8, oCENotaCreditoProveedor.getIdMoneda());
            sentencia.setInt(9, oCENotaCreditoProveedor.getIdEmpleado());
            sentencia.setInt(10, oCENotaCreditoProveedor.getIdUsuario());
            sentencia.setFloat(11, oCENotaCreditoProveedor.getIGVNuevo());
            sentencia.setFloat(12, oCENotaCreditoProveedor.getISCNuevo());
            sentencia.setFloat(13, oCENotaCreditoProveedor.getMontoAcreditar());
            sentencia.registerOutParameter(14, java.sql.Types.VARCHAR);
            sentencia.setInt(15, oCENotaCreditoProveedor.getIdConcepto());
            sentencia.setFloat(16, oCENotaCreditoProveedor.getTipoCambio());
            sentencia.setString(17,oCENotaCreditoProveedor.getNumeroNotaCreditoProveedor());
            sentencia.setInt(18, oCENotaCreditoProveedor.getIdCondicion());
            sentencia.setFloat(19, oCENotaCreditoProveedor.getMontoTotalAcreditar());
            sentencia.setString(20,oCENotaCreditoProveedor.getFecha());
            sentencia.setString(21, oCENotaCreditoProveedor.getObservacion());
            sentencia.setLong(22, 0);
            sentencia.setFloat(23, oCENotaCreditoProveedor.getMontoPercepcion());
            sentencia.executeUpdate();
            int id_NotaCreditoProveedor=sentencia.getInt(1);
            long id_salida_producto=sentencia.getLong(22);
            int contarProdSinStock=0;
            String mensajeStock="";
            for (CENotaCreditoProveedorDetalle oCENotaCreditoProveedorDetalle : oCENotaCreditoProveedor.getLstCENotaCreditoProveedorDetalle())
            {
                String sql_= "{call CMPSPRINSNotaCreditoProveedorDetalle(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                sentenciaDetalle = conexion.prepareCall(sql_);
                sentenciaDetalle.setInt(1,id_NotaCreditoProveedor);
                sentenciaDetalle.setFloat(2,oCENotaCreditoProveedorDetalle.getIdComprobanteDetalleRef());
                sentenciaDetalle.setLong(3,oCENotaCreditoProveedorDetalle.getIdProducto());
                sentenciaDetalle.setFloat(4,oCENotaCreditoProveedorDetalle.getIdAlmacen());
                sentenciaDetalle.setFloat(5,oCENotaCreditoProveedorDetalle.getIdUnidadMedidaVenta());
                sentenciaDetalle.setFloat(6,oCENotaCreditoProveedorDetalle.getImporteAnterior());
                sentenciaDetalle.setFloat(7,oCENotaCreditoProveedorDetalle.getImporteNuevo());
                sentenciaDetalle.setFloat(8,oCENotaCreditoProveedorDetalle.getPrecioNuevo());
                sentenciaDetalle.setFloat(9,oCENotaCreditoProveedorDetalle.getCantidadNueva());
                sentenciaDetalle.setFloat(10, oCENotaCreditoProveedorDetalle.getProporcionDescuentoTotal());
                sentenciaDetalle.setFloat(11, oCENotaCreditoProveedorDetalle.getExonerado());
                sentenciaDetalle.setBoolean(12, oCENotaCreditoProveedorDetalle.isSinoImpuesto());
                sentenciaDetalle.setInt(13, oCENotaCreditoProveedorDetalle.getIdNotaCreditoProveedorDetalle());
                sentenciaDetalle.setInt(14, oCENotaCreditoProveedorDetalle.getAccionAbm());
                sentenciaDetalle.setLong(15,id_salida_producto);
                sentenciaDetalle.setString(16,"");
                sentenciaDetalle.setInt(17, oCENotaCreditoProveedor.getIdConcepto());

                sentenciaDetalle.executeUpdate();
                
                String mensajeBD = sentenciaDetalle.getString(16);
                if(!mensajeBD.equals(""))
                {
                    mensajeStock=mensajeStock+mensajeBD;
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
              catch(SQLException sqlExcep)
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
          catch(SQLException e)
            {
              System.out.print(e);
            }
        }

  }
     public static boolean UPDNotaCreditoProveedor(CENotaCreditoProveedor oCENotaCreditoProveedor)
    {
       Connection conexion;
       conexion = ConexionBD.obtenerConexion();//5 elimina
        try
          {
            CallableStatement sentencia;
            CallableStatement sentenciaDetalle;
            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);


            String sql= "{call CMPSPRUPDNotaCreditoProveedor(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            sentencia = conexion.prepareCall(sql);
            sentencia.setLong(1, oCENotaCreditoProveedor.getIdNotaCreditoProveedor());
            sentencia.setInt(2, oCENotaCreditoProveedor.getIdSucursal());
            sentencia.setFloat(3, oCENotaCreditoProveedor.getSubtotalSinIGVNuevo());
            sentencia.setInt(4, oCENotaCreditoProveedor.getIdMoneda());
            sentencia.setInt(5, oCENotaCreditoProveedor.getIdEmpleado());
            sentencia.setInt(6, oCENotaCreditoProveedor.getIdUsuario());
            sentencia.setFloat(7, oCENotaCreditoProveedor.getIGVNuevo());
            sentencia.setFloat(8, oCENotaCreditoProveedor.getISCNuevo());
            sentencia.setFloat(9, oCENotaCreditoProveedor.getMontoAcreditar());
            sentencia.setInt(10, oCENotaCreditoProveedor.getIdConcepto());
            sentencia.setFloat(11, oCENotaCreditoProveedor.getTipoCambio());
            sentencia.setFloat(12, oCENotaCreditoProveedor.getMontoTotalAcreditar());
            sentencia.setString(13,oCENotaCreditoProveedor.getFecha());
            sentencia.setString(14,oCENotaCreditoProveedor.getNumeroNotaCreditoProveedor());
            sentencia.setString(15, oCENotaCreditoProveedor.getObservacion());
            sentencia.setFloat(16, oCENotaCreditoProveedor.getMontoTotalAcreditarAnterior());
            sentencia.setInt(17, oCENotaCreditoProveedor.getIdProveedor());
            sentencia.setLong(18,0);
            sentencia.setFloat(19, oCENotaCreditoProveedor.getMontoPercepcion());
            sentencia.executeUpdate();

            long vIdSalida=sentencia.getLong(18);
  
            for (CENotaCreditoProveedorDetalle oCENotaCreditoProveedorDetalle : oCENotaCreditoProveedor.getLstCENotaCreditoProveedorDetalle())
            {
                String sql_= "{call CMPSPRINSNotaCreditoProveedorDetalle(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                sentenciaDetalle = conexion.prepareCall(sql_);
                sentenciaDetalle.setLong(1,oCENotaCreditoProveedor.getIdNotaCreditoProveedor());
                sentenciaDetalle.setFloat(2,oCENotaCreditoProveedorDetalle.getIdComprobanteDetalleRef());
                sentenciaDetalle.setLong(3,oCENotaCreditoProveedorDetalle.getIdProducto());
                sentenciaDetalle.setFloat(4,oCENotaCreditoProveedorDetalle.getIdAlmacen());
                sentenciaDetalle.setFloat(5,oCENotaCreditoProveedorDetalle.getIdUnidadMedidaVenta());
                sentenciaDetalle.setFloat(6,oCENotaCreditoProveedorDetalle.getImporteAnterior());
                sentenciaDetalle.setFloat(7,oCENotaCreditoProveedorDetalle.getImporteNuevo());
                sentenciaDetalle.setFloat(8,oCENotaCreditoProveedorDetalle.getPrecioNuevo());
                sentenciaDetalle.setFloat(9,oCENotaCreditoProveedorDetalle.getCantidadNueva());
                sentenciaDetalle.setFloat(10, oCENotaCreditoProveedorDetalle.getProporcionDescuentoTotal());
                sentenciaDetalle.setFloat(11, oCENotaCreditoProveedorDetalle.getExonerado());
                sentenciaDetalle.setBoolean(12, oCENotaCreditoProveedorDetalle.isSinoImpuesto());
                sentenciaDetalle.setInt(13, oCENotaCreditoProveedorDetalle.getIdNotaCreditoProveedorDetalle());
                sentenciaDetalle.setInt(14, oCENotaCreditoProveedorDetalle.getAccionAbm());
                sentenciaDetalle.setLong(15, vIdSalida);
                sentenciaDetalle.setString(16, "");
                sentenciaDetalle.setInt(17, oCENotaCreditoProveedor.getIdConcepto());
                sentenciaDetalle.executeUpdate();
            }
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
public static int AnularNotaCreditoProveedor(CENotaCreditoProveedor oCENotaCreditoProveedor)
    {
       Connection conexion;
       conexion = ConexionBD.obtenerConexion();//5 elimina
        try
          {
            CallableStatement sentencia;
            CallableStatement sentenciaDet;
            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            String sql= "{call CMPSPRUPDAnularNotaCreditoProv(?,?,?,?,?,?,?,?,?,?)}";
            sentencia = conexion.prepareCall(sql);
            sentencia.setLong(1, oCENotaCreditoProveedor.getIdNotaCreditoProveedor());
            sentencia.setInt(2, 4);
            sentencia.setInt(3, oCENotaCreditoProveedor.getIdUsuario());
            sentencia.setInt(4, oCENotaCreditoProveedor.getIdEmpleado());
            sentencia.setString(5,oCENotaCreditoProveedor.getUltimaObservacion());
            sentencia.setFloat(6, oCENotaCreditoProveedor.getMontoTotalAcreditarAnterior());
            sentencia.setInt(7, oCENotaCreditoProveedor.getIdProveedor());
            sentencia.setInt(8, oCENotaCreditoProveedor.getIdMoneda());
            sentencia.setString(9, oCENotaCreditoProveedor.getNumeroNotaCreditoProveedor());
            sentencia.setInt(10, oCENotaCreditoProveedor.getIdConcepto());
            sentencia.executeUpdate();
            for (CENotaCreditoProveedorDetalle oCENotaCreditoProveedorDetalle : oCENotaCreditoProveedor.getLstCENotaCreditoProveedorDetalle())
            {
                String sqlDet = "{call ALMSPRUPDAnularSalidaProductoDetalle(?,?,?,?)}";
                sentenciaDet = conexion.prepareCall(sqlDet);
                sentenciaDet.setLong(1, oCENotaCreditoProveedorDetalle.getIdProducto());
                sentenciaDet.setInt(2, oCENotaCreditoProveedorDetalle.getIdAlmacen());
                sentenciaDet.setFloat(3, oCENotaCreditoProveedorDetalle.getCantidadNueva());
                sentenciaDet.setInt(4, oCENotaCreditoProveedorDetalle.getIdUnidadMedidaVenta());
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


     public static List<CENotaCreditoProveedor> ListarNotaCredito(String pDato,int opcion,long id,int idestado,String pFechaFin)
    {
        Connection cnn = ConexionBD.obtenerConexion();
        try
        {
            List<CENotaCreditoProveedor> lista=new ArrayList<CENotaCreditoProveedor>();
            CENotaCreditoProveedor oCENotaCreditoProveedor = null;
            if (cnn != null)
            {
                CallableStatement cs = null;
                cs = cnn.prepareCall("{CALL  CMPSPRCNSNotaCreditoProvPorCodigo(?,?,?,?,?)}");
                cs.setInt(1, opcion);
                cs.setString(2, pDato);
                cs.setLong(3, id);
                cs.setInt(4, idestado);
                cs.setString(5, pFechaFin);
                ResultSet rs = cs.executeQuery();
                while (rs.next())
                {
                    oCENotaCreditoProveedor = new CENotaCreditoProveedor();
                    oCENotaCreditoProveedor.setIdNotaCreditoProveedor(rs.getLong(1));
                    oCENotaCreditoProveedor.setNumeroNotaCreditoProveedor(rs.getString(2));
                    oCENotaCreditoProveedor.setFecha(rs.getString(3));
                    oCENotaCreditoProveedor.setMontoTotalAcreditar(rs.getFloat(4));
                    oCENotaCreditoProveedor.setMoneda(rs.getString(5));
                    oCENotaCreditoProveedor.setProveedor(rs.getString(6));
                    oCENotaCreditoProveedor.setTipoComprobante(rs.getString(7));
                    oCENotaCreditoProveedor.setUltimoEstado(rs.getString(8));
                    lista.add(oCENotaCreditoProveedor);


                }

            }
            return lista;
        }
        catch (SQLException ex)
        {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, "Error al cargar el ComprobanteCompra ");
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
                Logger.getLogger(CDComprobanteCompra.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
