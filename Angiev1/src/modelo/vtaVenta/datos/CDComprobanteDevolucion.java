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
import modelo.vtaVenta.entidad.CEComprobanteVentaMatriz;
import modelo.vtaVenta.entidad.CENotaCredito;
import modelo.vtaVenta.entidad.CENotaCreditoDetalle;


/**
 *
 * @author Katya
 */
public class CDComprobanteDevolucion {

     public static CENotaCredito ListarComprobanteDevolucionPorId( int id)
    {
        Connection cnn= ConexionBD.obtenerConexion();
        try
        {

            CENotaCredito oCENotaCredito=null;
                if(cnn!=null)
                {
                     CallableStatement cs= null;

                     cs = cnn.prepareCall("{CALL  VTASPRCNSComprobanteDevolucion(?,?,?,?,?)}");
                     cs.setInt(1, 1);
                     cs.setString(2, null);
                     cs.setInt(3,id);
                     cs.setInt(4, 0);
                     cs.setString(5, null);
                     ResultSet rs=cs.executeQuery();
                      while(rs.next())
                      {
                       oCENotaCredito=new CENotaCredito();
                       oCENotaCredito.setIdComprobanteVentaRef(rs.getLong(1));
                       oCENotaCredito.setIdCondicion(rs.getInt(2));
                       oCENotaCredito.setUltimoIdEstado(rs.getInt(3));
                       oCENotaCredito.setIdTipoComprobante(rs.getInt(4));
                       oCENotaCredito.setNumeroNotaCredito(rs.getString(5));
                       oCENotaCredito.setIdEmpleado(rs.getInt(6));
                       oCENotaCredito.setIdUsuario(rs.getInt(7));
                       oCENotaCredito.setIdCliente(rs.getInt(8));
                       oCENotaCredito.setIdSucursal(rs.getInt(9));
                       oCENotaCredito.setFecha(rs.getString(10));
                       oCENotaCredito.setSubtotalSinIGVNuevo(rs.getFloat(11));
                       oCENotaCredito.setIGVNuevo(rs.getFloat(12));
                       oCENotaCredito.setISCNuevo(rs.getFloat(13));
                       oCENotaCredito.setMontoAcreditar(rs.getFloat(14));
                       oCENotaCredito.setIdNotaCredito(rs.getInt(15));
                       oCENotaCredito.setCliente(rs.getString(16));
                       oCENotaCredito.setDNI(rs.getString(17));
                       oCENotaCredito.setDireccion(rs.getString(18));
                       oCENotaCredito.setMoneda(rs.getString(19));
                       oCENotaCredito.setTipoComprobante(rs.getString(20));
                       oCENotaCredito.setIdNotaCredito(rs.getInt(21));
                       oCENotaCredito.setUltimoEstado(rs.getString(22));
                       oCENotaCredito.setIdConcepto(rs.getInt(23));
                       oCENotaCredito.setMontoTotalAcreditar(rs.getFloat(24));
                       oCENotaCredito.setUltimaObservacion(rs.getString(25));
                       oCENotaCredito.setMontoPercepcion(rs.getFloat(26));
                       oCENotaCredito.setTasaPercepcion(rs.getFloat(27));

                      }
                       if(oCENotaCredito!=null){
                       oCENotaCredito.setLstCENotaCreditoDetalle(ListarComprobanteDevolucionDetalle(oCENotaCredito.getIdNotaCredito(),1));}

                     return oCENotaCredito;
                 }
             return oCENotaCredito;
        }

        catch(SQLException ex)
        {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, "Error al cargar Comprobante Devolucion ");
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

     public static List<CENotaCredito> ListarCompDevol(String pDato,int opcion,long idCliente,int idestado,String pFechaFin)
    {
        Connection cnn = ConexionBD.obtenerConexion();
        try
        {
            List<CENotaCredito> lista=new ArrayList<CENotaCredito>();
            CENotaCredito oCENotaCredito = null;
            if (cnn != null)
            {
                CallableStatement cs = null;
                cs = cnn.prepareCall("{CALL  VTASPRCNSComprobanteDevolucion(?,?,?,?,?)}");
                cs.setInt(1, opcion);
                cs.setString(2, pDato);
                cs.setLong(3, idCliente);
                cs.setInt(4, idestado);
                cs.setString(5, pFechaFin);
                ResultSet rs = cs.executeQuery();
                while (rs.next())
                {
                    oCENotaCredito = new CENotaCredito();
                    oCENotaCredito.setIdNotaCredito(rs.getInt(1));
                    oCENotaCredito.setNumeroNotaCredito(rs.getString(2));
                    oCENotaCredito.setFecha(rs.getString(3));
                    oCENotaCredito.setMontoTotalAcreditar(rs.getFloat(4));
                    oCENotaCredito.setMoneda(rs.getString(5));
                    oCENotaCredito.setCliente(rs.getString(6));
                    oCENotaCredito.setTipoComprobante(rs.getString(7));
                    oCENotaCredito.setUltimoEstado(rs.getString(8));
                    lista.add(oCENotaCredito);


                }

            }
            return lista;
        }
        catch (SQLException ex)
        {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, "Error al cargar la devol ");
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
                Logger.getLogger(CDNotaCredito.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     public static List<CENotaCreditoDetalle> ListarComprobanteDevolucionDetalle(long IdNotaCredito,int idCns)
    {
        Connection cnn= ConexionBD.obtenerConexion();
        try
        {
            List<CENotaCreditoDetalle> lista= new ArrayList<CENotaCreditoDetalle>();

            CENotaCreditoDetalle oCENotaCreditoDetalle=null;
                if(cnn!=null)
                {
                     CallableStatement cs= null;
                     cs = cnn.prepareCall("{CALL  VTASPRCNSComprobanteDevolucionDetalle(?,?)}");
                     cs.setLong(1, IdNotaCredito);
                     cs.setInt(2, idCns);
                     ResultSet rs=cs.executeQuery();
                      while(rs.next())
                      {

                       oCENotaCreditoDetalle=new CENotaCreditoDetalle();
                       oCENotaCreditoDetalle.setIdNotaCreditoDetalle(rs.getInt(1));
                       oCENotaCreditoDetalle.setIdComprobanteDetalleRef(rs.getLong(2));
                       oCENotaCreditoDetalle.setImporteAnterior(rs.getFloat(2));
                       oCENotaCreditoDetalle.setCantidadNueva(rs.getFloat(4));
                       oCENotaCreditoDetalle.setPrecioNuevo(rs.getFloat(5));
                       oCENotaCreditoDetalle.setImporteNuevo(rs.getFloat(6));
                       oCENotaCreditoDetalle.setExonerado(rs.getFloat(7));
                       oCENotaCreditoDetalle.setSinoImpuesto(rs.getBoolean(8));
                       oCENotaCreditoDetalle.setUnidadMedida(rs.getString(9));
                       oCENotaCreditoDetalle.setProducto(rs.getString(10));
                       lista.add(oCENotaCreditoDetalle);
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
     public static String[] InsComprobanteDevolucion(CENotaCredito oCENotaCredito,CEComprobanteVentaMatriz oCEComprobanteVentaMatriz,
                                                     int pIdPunto,int pIdAlmacen)
    {
       Connection conexion;
       String[] codigos = new String[1];
       codigos=new String[]{null,null};
       conexion = ConexionBD.obtenerConexion();//5 elimina
       String CodigoNotaCredito=null;
       String CodigoIngreso=null;
        try
          {
            CallableStatement sentencia;
            CallableStatement sentenciaDetalle;
            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);


            String sql= "{call VTASPRINSComprobanteDevolucion(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            sentencia = conexion.prepareCall(sql);
            sentencia.setLong(1, oCENotaCredito.getIdNotaCredito());
            sentencia.setLong(2, oCENotaCredito.getIdComprobanteVentaRef());
            sentencia.setLong(3, oCENotaCredito.getIdCliente());
            sentencia.setLong(4, oCENotaCredito.getIdSucursal());
            sentencia.setFloat(5, oCENotaCredito.getSubtotalSinIGVNuevo());
            sentencia.setInt(6, oCENotaCredito.getUltimoIdEstado());
            sentencia.setInt(7, oCENotaCredito.getIdTipoComprobante());
            sentencia.setInt(8, oCENotaCredito.getIdMoneda());
            sentencia.setInt(9, oCENotaCredito.getIdEmpleado());
            sentencia.setInt(10, oCENotaCredito.getIdUsuario());
            sentencia.setFloat(11, oCENotaCredito.getIGVNuevo());
            sentencia.setFloat(12, oCENotaCredito.getISCNuevo());
            sentencia.setFloat(13, oCENotaCredito.getMontoAcreditar());
            sentencia.registerOutParameter(14, java.sql.Types.VARCHAR);
            sentencia.setInt(15, oCENotaCredito.getIdConcepto());
            sentencia.setFloat(16, oCENotaCredito.getTipoCambio());
            sentencia.setInt(17,pIdPunto);
            sentencia.setInt(18, oCENotaCredito.getIdCondicion());
            sentencia.setFloat(19, oCENotaCredito.getMontoTotalAcreditar());
            sentencia.setLong(20, 0); // idIngresoProd
            sentencia.setString(21, "0");// NumIngreso
            sentencia.setInt(22, pIdAlmacen);
            sentencia.setString(23, oCENotaCredito.getUltimaObservacion());
            sentencia.setFloat(24, oCENotaCredito.getMontoPercepcion());
            sentencia.setFloat(25, oCENotaCredito.getTasaPercepcion());

            sentencia.executeUpdate();
            int id_NotaCredito=sentencia.getInt(1);
            long IdIngresoProducto=sentencia.getLong(20);
             CodigoNotaCredito=sentencia.getString(14);
             CodigoIngreso=sentencia.getString(21);
            for (CENotaCreditoDetalle oCENotaCreditoDetalle : oCENotaCredito.getLstCENotaCreditoDetalle())
            {
                String sql_= "{call VTASPRINSComprobanteDevolucionDetalle(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                sentenciaDetalle = conexion.prepareCall(sql_);
                sentenciaDetalle.setInt(1,id_NotaCredito);
                sentenciaDetalle.setFloat(2,oCENotaCreditoDetalle.getIdComprobanteDetalleRef());
                sentenciaDetalle.setLong(3,oCENotaCreditoDetalle.getIdProducto());
                sentenciaDetalle.setFloat(4,oCENotaCreditoDetalle.getIdAlmacen());
                sentenciaDetalle.setFloat(5,oCENotaCreditoDetalle.getIdUnidadMedidaVenta());
                sentenciaDetalle.setFloat(6,oCENotaCreditoDetalle.getImporteAnterior());
                sentenciaDetalle.setFloat(7,oCENotaCreditoDetalle.getImporteNuevo());
                sentenciaDetalle.setFloat(8,oCENotaCreditoDetalle.getPrecioNuevo());
                sentenciaDetalle.setFloat(9,oCENotaCreditoDetalle.getCantidadNueva());
                sentenciaDetalle.setFloat(10, oCENotaCreditoDetalle.getProporcionDescuentoTotal());
                sentenciaDetalle.setFloat(11, oCENotaCreditoDetalle.getExonerado());
                sentenciaDetalle.setBoolean(12, oCENotaCreditoDetalle.isSinoImpuesto());
                sentenciaDetalle.setLong(13,IdIngresoProducto);
                sentenciaDetalle.setInt(14,oCENotaCredito.getIdConcepto());
                sentenciaDetalle.executeUpdate();
            }

                
                if(oCENotaCredito.getIdConcepto()==1){
                    if(CDComprobanteVenta.AnularComprobanteVenta(conexion, oCEComprobanteVentaMatriz, 4))
                    {
                    conexion.commit();
                    codigos[0]=CodigoNotaCredito;
                    codigos[1]=CodigoIngreso;
                    }
                }
                    else
                    {
                    conexion.commit();
                    codigos[0]=CodigoNotaCredito;
                    codigos[1]=CodigoIngreso;
                    }

               return codigos;
              
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
            return null;
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
     public static boolean UPDComprobanteDevolucion(CENotaCredito oCENotaCredito)
    {
       Connection conexion;
       conexion = ConexionBD.obtenerConexion();//5 elimina
        try
          {
            CallableStatement sentencia;
            CallableStatement sentenciaDetalle;
            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);


            String sql= "{call VTASPRUPDComprobanteDevolucion(?,?,?,?,?,?,?,?,?,?,?,?)}";
            sentencia = conexion.prepareCall(sql);
            sentencia.setLong(1, oCENotaCredito.getIdNotaCredito());
            sentencia.setInt(2, oCENotaCredito.getIdSucursal());
            sentencia.setFloat(3, oCENotaCredito.getSubtotalSinIGVNuevo());
            sentencia.setInt(4, oCENotaCredito.getIdMoneda());
            sentencia.setInt(5, oCENotaCredito.getIdEmpleado());
            sentencia.setInt(6, oCENotaCredito.getIdUsuario());
            sentencia.setFloat(7, oCENotaCredito.getIGVNuevo());
            sentencia.setFloat(8, oCENotaCredito.getISCNuevo());
            sentencia.setFloat(9, oCENotaCredito.getMontoTotalAcreditar());
            sentencia.setInt(10, oCENotaCredito.getIdConcepto());
            sentencia.setFloat(11, oCENotaCredito.getTipoCambio());
            sentencia.setFloat(12, oCENotaCredito.getMontoTotalAcreditar());


            sentencia.executeUpdate();
  
            for (CENotaCreditoDetalle oCENotaCreditoDetalle : oCENotaCredito.getLstCENotaCreditoDetalle())
            {
                String sql_= "{call VTASPRINSComprobanteDevolucionDetalle(?,?,?,?,?,?,?,?,?,?,?,?)}";
                sentenciaDetalle = conexion.prepareCall(sql_);
                sentenciaDetalle.setLong(1,oCENotaCredito.getIdNotaCredito());
                sentenciaDetalle.setFloat(2,oCENotaCreditoDetalle.getIdComprobanteDetalleRef());
                sentenciaDetalle.setLong(3,oCENotaCreditoDetalle.getIdProducto());
                sentenciaDetalle.setFloat(4,oCENotaCreditoDetalle.getIdAlmacen());
                sentenciaDetalle.setFloat(5,oCENotaCreditoDetalle.getIdUnidadMedidaVenta());
                sentenciaDetalle.setFloat(6,oCENotaCreditoDetalle.getImporteAnterior());
                sentenciaDetalle.setFloat(7,oCENotaCreditoDetalle.getImporteNuevo());
                sentenciaDetalle.setFloat(8,oCENotaCreditoDetalle.getPrecioNuevo());
                sentenciaDetalle.setFloat(9,oCENotaCreditoDetalle.getCantidadNueva());
                sentenciaDetalle.setFloat(10, oCENotaCreditoDetalle.getProporcionDescuentoTotal());
                sentenciaDetalle.setFloat(11, oCENotaCreditoDetalle.getExonerado());
                sentenciaDetalle.setBoolean(12, oCENotaCreditoDetalle.isSinoImpuesto());

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
public static int AnularComprobanteDevolucion(CENotaCredito oCENotaCredito)
    {
       Connection conexion;
       conexion = ConexionBD.obtenerConexion();//5 elimina
        try
          {
            CallableStatement sentenciaCompDev;
            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            String sql= "{call VTASPRINSComprobanteDevolucionEstado(?,?,?,?,?,?)}";
            sentenciaCompDev = conexion.prepareCall(sql);
            sentenciaCompDev.setLong(1, oCENotaCredito.getIdNotaCredito());
            sentenciaCompDev.setInt(2, 4);
            sentenciaCompDev.setInt(3, oCENotaCredito.getIdUsuario());
            sentenciaCompDev.setInt(4, oCENotaCredito.getIdEmpleado());
            sentenciaCompDev.setString(5,oCENotaCredito.getUltimaObservacion());
            sentenciaCompDev.setLong(6,oCENotaCredito.getIdComprobanteVentaRef());
            sentenciaCompDev.executeUpdate();
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
     

}
