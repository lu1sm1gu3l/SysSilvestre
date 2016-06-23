package modelo.vtaVenta.datos;

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
import modelo.almAlmacen.entidad.CESalidaProducto;
import modelo.vtaVenta.entidad.CEComprobanteVenta;
import modelo.vtaVenta.entidad.CEComprobanteVentaDetalle;
import modelo.vtaVenta.entidad.CEComprobanteVentaMatriz;
import modelo.vtaVenta.entidad.CEFormaPagoDetalle;
import util.clases.vtaVenta.CLComprobanteVenta;
import view.FrmSistemaMenu;


public class CDComprobanteVenta
{

    public static String InsComprobanteVenta(CEComprobanteVenta oCEComprobanteVenta)
    {
       Connection conexion;
       conexion = ConexionBD.obtenerConexion();//
        try
          {
            CallableStatement sentenciaComprobanteVenta;
            CallableStatement sentenciaComprobanteVentaDetalle;
            CallableStatement sentenciaIngresoFinanciero;
            CallableStatement sentenciaIngresoFinancieroDetalle;
            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            String sql= "{call VTASPRINSComprobanteVenta(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            sentenciaComprobanteVenta = conexion.prepareCall(sql);
            sentenciaComprobanteVenta.registerOutParameter(1,java.sql.Types.BIGINT);
            sentenciaComprobanteVenta.setLong(2,oCEComprobanteVenta.getIdComprobanteVentaRef());
            sentenciaComprobanteVenta.setInt(3,oCEComprobanteVenta.getIdCondicion());
            sentenciaComprobanteVenta.setFloat(4,oCEComprobanteVenta.getUltimoIdEstado());
            sentenciaComprobanteVenta.setFloat(5,oCEComprobanteVenta.getUltimoIdEstadoComprobanteVenta());
            sentenciaComprobanteVenta.setInt(6,oCEComprobanteVenta.getIdTipoComprobanteVenta());
            sentenciaComprobanteVenta.setInt(7,oCEComprobanteVenta.getIdEmpleado());
            sentenciaComprobanteVenta.setInt(8,oCEComprobanteVenta.getIdUsuario());
            sentenciaComprobanteVenta.setLong(9,oCEComprobanteVenta.getIdCliente());
            sentenciaComprobanteVenta.setInt(10,oCEComprobanteVenta.getIdSucursal());
            sentenciaComprobanteVenta.setLong(11,oCEComprobanteVenta.getIdPedidoRef());
            sentenciaComprobanteVenta.setInt(12,oCEComprobanteVenta.getIdMoneda());
            sentenciaComprobanteVenta.setFloat(13,oCEComprobanteVenta.getTipoCambio());
            sentenciaComprobanteVenta.setFloat(14,oCEComprobanteVenta.getSubtotalBruto());
            sentenciaComprobanteVenta.setInt(15,oCEComprobanteVenta.getIdDescuento());
            sentenciaComprobanteVenta.setFloat(16,oCEComprobanteVenta.getDescuento());
            sentenciaComprobanteVenta.setFloat(17,oCEComprobanteVenta.getDescuentoEnValores());
            sentenciaComprobanteVenta.setFloat(18,oCEComprobanteVenta.getDescuentoEnSubtotal());
            sentenciaComprobanteVenta.setFloat(19,oCEComprobanteVenta.getDescuentoTotal());
            sentenciaComprobanteVenta.setFloat(20,oCEComprobanteVenta.getSubtotalNeto());
            sentenciaComprobanteVenta.setFloat(21,oCEComprobanteVenta.getSubTotalNetoSinIGV());
            sentenciaComprobanteVenta.setFloat(22,oCEComprobanteVenta.getIGVActual());
            sentenciaComprobanteVenta.setFloat(23,oCEComprobanteVenta.getIGV());
            sentenciaComprobanteVenta.setFloat(24,oCEComprobanteVenta.getISC());
            sentenciaComprobanteVenta.setFloat(25,oCEComprobanteVenta.getMontoTotal());
            sentenciaComprobanteVenta.setString(26,FrmSistemaMenu.oCEUsuario.getIp());
            sentenciaComprobanteVenta.registerOutParameter(27,java.sql.Types.VARCHAR);
            sentenciaComprobanteVenta.setFloat(28,oCEComprobanteVenta.getTotalPagar());
            sentenciaComprobanteVenta.setString(29,oCEComprobanteVenta.getFecha());
            sentenciaComprobanteVenta.setFloat(30,oCEComprobanteVenta.getMontoPercepcion());
            sentenciaComprobanteVenta.setFloat(31,oCEComprobanteVenta.getMontoExonerado());
            sentenciaComprobanteVenta.setFloat(32,oCEComprobanteVenta.getTasaPercepcion());
            sentenciaComprobanteVenta.executeUpdate();
            long id_ComprobanteVenta=sentenciaComprobanteVenta.getInt(1);
            String numeroComprobante=sentenciaComprobanteVenta.getString(27);
            oCEComprobanteVenta.setNumComprobante(numeroComprobante);
            oCEComprobanteVenta.setFecha(sentenciaComprobanteVenta.getString(29));
            for (CEComprobanteVentaDetalle oCEComprobanteVentaDetalle : oCEComprobanteVenta.getoLstComprobanteDetalle())
            {
                String sql_= "{call VTASPRINSComprobanteDetalle(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                sentenciaComprobanteVentaDetalle = conexion.prepareCall(sql_);                
                sentenciaComprobanteVentaDetalle.setLong(1,id_ComprobanteVenta);
                sentenciaComprobanteVentaDetalle.setLong(2,oCEComprobanteVentaDetalle.getIdPedidoDetalleRef());
                sentenciaComprobanteVentaDetalle.setInt(3,oCEComprobanteVentaDetalle.getIdProducto());
                sentenciaComprobanteVentaDetalle.setInt(4, oCEComprobanteVentaDetalle.getIdAlmacen());
                sentenciaComprobanteVentaDetalle.setInt(5, oCEComprobanteVentaDetalle.getIdUnidadMedidaVenta());
                sentenciaComprobanteVentaDetalle.setFloat(6, oCEComprobanteVentaDetalle.getCantidad());
                sentenciaComprobanteVentaDetalle.setFloat(7, oCEComprobanteVentaDetalle.getPrecio());
                sentenciaComprobanteVentaDetalle.setFloat(8, oCEComprobanteVentaDetalle.getImporteSinDescuento());
                sentenciaComprobanteVentaDetalle.setInt(9, oCEComprobanteVentaDetalle.getIdTipoDescuento());
                sentenciaComprobanteVentaDetalle.setFloat(10, oCEComprobanteVentaDetalle.getDescuento());
                sentenciaComprobanteVentaDetalle.setFloat(11, oCEComprobanteVentaDetalle.getDescuentoEnValores());
                sentenciaComprobanteVentaDetalle.setFloat(12, oCEComprobanteVentaDetalle.getImporteConDescuento());
                sentenciaComprobanteVentaDetalle.setFloat(13, oCEComprobanteVentaDetalle.getExonerado());
                sentenciaComprobanteVentaDetalle.setBoolean(14, oCEComprobanteVentaDetalle.isSiNoImpuesto());
                sentenciaComprobanteVentaDetalle.setFloat(15, oCEComprobanteVentaDetalle.getEquivalenciaBase());
                sentenciaComprobanteVentaDetalle.setFloat(16, oCEComprobanteVentaDetalle.getPrecioconDesc());
                sentenciaComprobanteVentaDetalle.executeUpdate();
            }

            String sql4= "{call VTASPRINSFormaPago(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            sentenciaIngresoFinanciero = conexion.prepareCall(sql4);
            sentenciaIngresoFinanciero.registerOutParameter(1,java.sql.Types.BIGINT);
            sentenciaIngresoFinanciero.registerOutParameter(22,java.sql.Types.BIGINT);
            sentenciaIngresoFinanciero.registerOutParameter(18,java.sql.Types.INTEGER);
            sentenciaIngresoFinanciero.setInt(2,1);
            sentenciaIngresoFinanciero.setString(3,numeroComprobante);
            sentenciaIngresoFinanciero.setLong(4,oCEComprobanteVenta.getFormaPago().getIdCliente());
            sentenciaIngresoFinanciero.setFloat(5,oCEComprobanteVenta.getFormaPago().getIdMoneda());
            sentenciaIngresoFinanciero.setFloat(6,oCEComprobanteVenta.getFormaPago().getTipoCambio());
            sentenciaIngresoFinanciero.setLong(7,id_ComprobanteVenta);
            sentenciaIngresoFinanciero.setString(8,oCEComprobanteVenta.getFormaPago().getObservacion());
            sentenciaIngresoFinanciero.setFloat(9,oCEComprobanteVenta.getFormaPago().getSubTotalNeto());
            sentenciaIngresoFinanciero.setFloat(10,oCEComprobanteVenta.getFormaPago().getSubTotalNetoSinIGV());
            sentenciaIngresoFinanciero.setFloat(11,oCEComprobanteVenta.getFormaPago().getIGVActual());
            sentenciaIngresoFinanciero.setFloat(12,oCEComprobanteVenta.getFormaPago().getIGV());
            sentenciaIngresoFinanciero.setFloat(13,oCEComprobanteVenta.getFormaPago().getISC());
            sentenciaIngresoFinanciero.setFloat(14,oCEComprobanteVenta.getFormaPago().getMontoTotal());
            sentenciaIngresoFinanciero.setFloat(15,oCEComprobanteVenta.getFormaPago().getVueltoTotal());
            sentenciaIngresoFinanciero.setString(16,FrmSistemaMenu.oCEUsuario.getIp());
            sentenciaIngresoFinanciero.setInt(17,oCEComprobanteVenta.getFormaPago().getIdSucursal());
            sentenciaIngresoFinanciero.setInt(19,oCEComprobanteVenta.getFormaPago().getIdTipoComprobanteVenta());
            sentenciaIngresoFinanciero.setInt(20,FrmSistemaMenu.oCEUsuario.getIdEmpleado());
            sentenciaIngresoFinanciero.setInt(21,FrmSistemaMenu.oCEUsuario.getIdUsuario());
            sentenciaIngresoFinanciero.setFloat(23,oCEComprobanteVenta.getFormaPago().getMontoSinCuentaCorriente());
            sentenciaIngresoFinanciero.setFloat(24,oCEComprobanteVenta.getFormaPago().getIngresoTotalLiquido());
            sentenciaIngresoFinanciero.setFloat(25,oCEComprobanteVenta.getFormaPago().getIngresoTotalLiquidoSinCuentaCorriente());
            sentenciaIngresoFinanciero.setFloat(26,oCEComprobanteVenta.getFormaPago().getPagoTotal());
            sentenciaIngresoFinanciero.setFloat(27,oCEComprobanteVenta.getFormaPago().getPagoTotalSinCuentaCorriente());
            sentenciaIngresoFinanciero.setFloat(28,oCEComprobanteVenta.getFormaPago().getMontoPercepcion());
            sentenciaIngresoFinanciero.executeUpdate();
            long id_FormaPago=sentenciaIngresoFinanciero.getInt(1);
            long id_IngresoFinanciero=sentenciaIngresoFinanciero.getInt(22);
            int id_IdPuntoVenta=sentenciaIngresoFinanciero.getInt(18);

            for (CEFormaPagoDetalle oCEFormaPagoDetalle : oCEComprobanteVenta.getFormaPago().getoLstFormaPagoDetalle())
            {
                String sql_= "{call VTASPRINSFormaPagoDetalle(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                sentenciaIngresoFinancieroDetalle = conexion.prepareCall(sql_);
                sentenciaIngresoFinancieroDetalle.setLong(1,id_FormaPago);
                sentenciaIngresoFinancieroDetalle.setInt(2,oCEFormaPagoDetalle.getIdTipoPago());
                sentenciaIngresoFinancieroDetalle.setFloat(3,oCEFormaPagoDetalle.getMonto());
                sentenciaIngresoFinancieroDetalle.setFloat(4,oCEFormaPagoDetalle.getVuelto());
                sentenciaIngresoFinancieroDetalle.setInt(5,oCEFormaPagoDetalle.getIdInstituicionFinanciera());
                sentenciaIngresoFinancieroDetalle.setInt(6,oCEFormaPagoDetalle.getIdCuentaEmpresa());
                sentenciaIngresoFinancieroDetalle.setString(7,oCEFormaPagoDetalle.getFechaCheque());
                sentenciaIngresoFinancieroDetalle.setString(8,oCEFormaPagoDetalle.getCodigoAutorizacion());
                sentenciaIngresoFinancieroDetalle.setString(9,oCEFormaPagoDetalle.getNumeroPago());
                sentenciaIngresoFinancieroDetalle.setInt(10,id_IdPuntoVenta);
                sentenciaIngresoFinancieroDetalle.setLong(11,id_ComprobanteVenta);
                sentenciaIngresoFinancieroDetalle.setString(12,numeroComprobante);
                sentenciaIngresoFinancieroDetalle.setInt(13,oCEComprobanteVenta.getIdMoneda());
                sentenciaIngresoFinancieroDetalle.setLong(14,oCEComprobanteVenta.getIdCliente());
                sentenciaIngresoFinancieroDetalle.setInt(15,FrmSistemaMenu.oCEUsuario.getIdUsuario());
                sentenciaIngresoFinancieroDetalle.setInt(16,FrmSistemaMenu.oCEUsuario.getIdEmpleado());
                sentenciaIngresoFinancieroDetalle.setLong(17,id_IngresoFinanciero);
                sentenciaIngresoFinancieroDetalle.setFloat(18,oCEFormaPagoDetalle.getIngresoLiquido());
                sentenciaIngresoFinancieroDetalle.executeUpdate();
            }

            CLComprobanteVenta oCLComprobanteVenta=new CLComprobanteVenta();
             List<CESalidaProducto> lstSalida=oCLComprobanteVenta.getLstSalidaProducto(oCEComprobanteVenta, numeroComprobante);
           
               String isGrabado= CCSalidaProducto.InsSalidaProductoPorCobro(conexion, lstSalida);
               if(isGrabado.equals("OK"))
               {
                   numeroComprobante=isGrabado;
                   conexion.commit();

               }
                else
               {
                   conexion.rollback();
                   numeroComprobante=isGrabado;
               }
 
           return numeroComprobante;
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

     public static CEComprobanteVentaMatriz ListarComprobanteVentaPorId(long pIdComprobante,int Opcion)
    {
        Connection cnn= ConexionBD.obtenerConexion();
        try
        {
            CEComprobanteVentaMatriz oCEComprobanteVentaMatriz=null;
                if(cnn!=null)
                {
                     CallableStatement cs= null;
                     cs = cnn.prepareCall("{CALL  VTASPRCNSComprobanteVentaPorId(?,?)}");
                     cs.setLong(1, pIdComprobante);
                     cs.setInt(2, Opcion);
                     ResultSet rs=cs.executeQuery();
                      while(rs.next())
                      {
                       oCEComprobanteVentaMatriz=new CEComprobanteVentaMatriz();
                       oCEComprobanteVentaMatriz.setIdComprobanteVenta(rs.getLong(1));
                       oCEComprobanteVentaMatriz.setIdCondicion(rs.getInt(2));
                       oCEComprobanteVentaMatriz.setUltimoIdEstado(rs.getInt(3));
                       oCEComprobanteVentaMatriz.setIdTipoComprobanteVenta(rs.getInt(4));
                       oCEComprobanteVentaMatriz.setNumComprobante(rs.getString(5));
                       oCEComprobanteVentaMatriz.setIdEmpleado(rs.getInt(6));
                       oCEComprobanteVentaMatriz.setIdUsuario(rs.getInt(7));
                       oCEComprobanteVentaMatriz.setIdCliente(rs.getInt(8));
                       oCEComprobanteVentaMatriz.setIdSucursal(rs.getInt(9));
                       oCEComprobanteVentaMatriz.setFecha(rs.getString(10));
                       oCEComprobanteVentaMatriz.setSubtotalBruto(rs.getFloat(11));
                       oCEComprobanteVentaMatriz.setDescuentoEnSubtotal(rs.getFloat(12));
                       oCEComprobanteVentaMatriz.setSubtotalNeto(rs.getFloat(13));
                       oCEComprobanteVentaMatriz.setSubTotalNetoSinIGV(rs.getFloat(14));
                       oCEComprobanteVentaMatriz.setIGV(rs.getFloat(15));
                       oCEComprobanteVentaMatriz.setISC(rs.getFloat(16));
                       oCEComprobanteVentaMatriz.setIdDescuento(rs.getInt(17));
                       oCEComprobanteVentaMatriz.setDescuento(rs.getFloat(18));
                       oCEComprobanteVentaMatriz.setDescuentoTotal(rs.getFloat(19));
                       oCEComprobanteVentaMatriz.setMontoTotal(rs.getFloat(20));
                       oCEComprobanteVentaMatriz.setIdMoneda(rs.getInt(21));
                       oCEComprobanteVentaMatriz.setMoneda(rs.getString(22));
                       oCEComprobanteVentaMatriz.setCliente(rs.getString(23));
                       oCEComprobanteVentaMatriz.setDNI(rs.getString(24));
                       oCEComprobanteVentaMatriz.setDireccion(rs.getString(25));
                       oCEComprobanteVentaMatriz.setTipoDescuento(rs.getString(26));
                       oCEComprobanteVentaMatriz.setTipoComprobante(rs.getString(27));
                       oCEComprobanteVentaMatriz.setUltimoEstado(rs.getString(28));
                       oCEComprobanteVentaMatriz.setNumPedido(rs.getString(29));
                       oCEComprobanteVentaMatriz.setEmpleado(rs.getString(30));
                       oCEComprobanteVentaMatriz.setCobrador(rs.getString(31));                       
                       oCEComprobanteVentaMatriz.setTotalPagar(rs.getFloat(32));
                       oCEComprobanteVentaMatriz.setMontoPercepcion(rs.getFloat(33));
                       oCEComprobanteVentaMatriz.setUltimaFecha(rs.getString(34));

                 //      oCEComprobanteVentaMatriz.setIGVActual(rs.getFloat(31));


                      }
                     if(oCEComprobanteVentaMatriz!=null){
                      oCEComprobanteVentaMatriz.setoLstComprobanteDetalle(ListarComprobanteDetalle(oCEComprobanteVentaMatriz.getIdComprobanteVenta(),1));}
                     return oCEComprobanteVentaMatriz;
                 }
             return oCEComprobanteVentaMatriz;
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

      public static List<CEComprobanteVentaMatriz> ConsultarAuditoria(long id )
    {
        Connection cnn= ConexionBD.obtenerConexion();
        try
        {
            List<CEComprobanteVentaMatriz> olista= new ArrayList<CEComprobanteVentaMatriz>();
            CEComprobanteVentaMatriz oCEComprobanteVentaMatriz=null;
                if(cnn!=null)
                {
                     CallableStatement cs= null;
                     cs = cnn.prepareCall("{CALL  VTASPRCNSAudioriaComprobanteVenta(?)}");
                     cs.setLong(1, id);
                     ResultSet rs=cs.executeQuery();
                      while(rs.next())
                      {
                       oCEComprobanteVentaMatriz=new CEComprobanteVentaMatriz();
                       oCEComprobanteVentaMatriz.setUltimaFecha(rs.getString(1));
                       oCEComprobanteVentaMatriz.setUltimoEstado(rs.getString(2));
                       oCEComprobanteVentaMatriz.setUltimoUsuario(rs.getString(3));
                       oCEComprobanteVentaMatriz.setUltimaObservacion(rs.getString(4));
                       
                       olista.add(oCEComprobanteVentaMatriz);

                      }

                 }
             return olista;
        }

        catch(SQLException ex)
        {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, "Error al cargar la Consulta ");
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

     public static List<CEComprobanteVentaDetalle> ListarComprobanteDetalle(long IdComprobante,int idCns)
    {
        Connection cnn= ConexionBD.obtenerConexion();
        try
        {
            List<CEComprobanteVentaDetalle> lista= new ArrayList<CEComprobanteVentaDetalle>();

            CEComprobanteVentaDetalle oCEComprobanteVentaDetalle=null;
                if(cnn!=null)
                {
                     CallableStatement cs= null;
                     cs = cnn.prepareCall("{CALL  VTASPRCNSComprobanteVentaDetalle(?,?)}");
                     cs.setLong(1, IdComprobante);
                     cs.setInt(2, idCns);
                     ResultSet rs=cs.executeQuery();
                      while(rs.next())
                      {

                       oCEComprobanteVentaDetalle=new CEComprobanteVentaDetalle();
                       oCEComprobanteVentaDetalle.setIdComprobanteVentaDetalle(rs.getLong(1));
                       oCEComprobanteVentaDetalle.setIdProducto(rs.getInt(2));
                       oCEComprobanteVentaDetalle.setImporteSinDescuento(rs.getFloat(3));
                       oCEComprobanteVentaDetalle.setPrecio(rs.getFloat(4));
                       oCEComprobanteVentaDetalle.setCantidad(rs.getFloat(5));
                       oCEComprobanteVentaDetalle.setIdTipoDescuento(rs.getInt(6));
                       oCEComprobanteVentaDetalle.setDescuento(rs.getFloat(7));
                       oCEComprobanteVentaDetalle.setIdAlmacen(rs.getInt(8));
                       oCEComprobanteVentaDetalle.setDescuentoEnValores(rs.getFloat(9));
                       oCEComprobanteVentaDetalle.setImporteConDescuento(rs.getFloat(10));
                       oCEComprobanteVentaDetalle.setIdUnidadMedidaVenta(rs.getInt(11));
                       oCEComprobanteVentaDetalle.setTipoDescuento(rs.getString(12));
                       oCEComprobanteVentaDetalle.setUnidadMedida(rs.getString(13));
                       oCEComprobanteVentaDetalle.setProducto(rs.getString(14));
                       oCEComprobanteVentaDetalle.setCodigoProducto(rs.getString(15));
                       oCEComprobanteVentaDetalle.setExonerado(rs.getFloat(16));
                       oCEComprobanteVentaDetalle.setSiNoImpuesto(rs.getBoolean(17));
                       oCEComprobanteVentaDetalle.setPrecioconDesc(rs.getFloat(18));

                       lista.add(oCEComprobanteVentaDetalle);
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

  public static List<CEComprobanteVentaMatriz> BuscarComprobanteVenta(int pIdCns,String pNum_Fecha,int idEstado,int idtipoComp,long idCliente,String FechaFin,boolean condecuento)
    {
        Connection cnn= ConexionBD.obtenerConexion();
        try
        {
            List<CEComprobanteVentaMatriz> olista= new ArrayList<CEComprobanteVentaMatriz>();
            CEComprobanteVentaMatriz oCEComprobanteVentaMatriz=null;
                if(cnn!=null)
                {
                     CallableStatement cs= null;
                     cs = cnn.prepareCall("{CALL  VTASPRCNSComprobanteVenta(?,?,?,?,?,?,?)}");
                     cs.setInt(1, pIdCns);
                     cs.setString(2, pNum_Fecha);
                     cs.setLong(3, idCliente);
                     cs.setInt(4, idEstado);
                     cs.setInt(5, idtipoComp);
                     cs.setString(6, FechaFin);
                     cs.setBoolean(7, condecuento);
                     ResultSet rs=cs.executeQuery();
                      while(rs.next())
                      {
                       oCEComprobanteVentaMatriz=new CEComprobanteVentaMatriz();
                       oCEComprobanteVentaMatriz.setIdComprobanteVenta(rs.getLong(1));
                       oCEComprobanteVentaMatriz.setNumComprobante(rs.getString(2));
                       oCEComprobanteVentaMatriz.setFecha(rs.getString(3));
                       oCEComprobanteVentaMatriz.setMontoTotal(rs.getFloat(4));
                       oCEComprobanteVentaMatriz.setMoneda(rs.getString(5));
                       oCEComprobanteVentaMatriz.setCliente(rs.getString(6));
                       oCEComprobanteVentaMatriz.setTipoComprobante(rs.getString(7));
                       oCEComprobanteVentaMatriz.setUltimoEstado(rs.getString(8));
                       oCEComprobanteVentaMatriz.setUltimoIdEstado(rs.getInt(9));
                       oCEComprobanteVentaMatriz.setDescuentoTotal(rs.getFloat(10));
                       oCEComprobanteVentaMatriz.setHora(rs.getString(11));
                       oCEComprobanteVentaMatriz.setUltimaFecha(rs.getString(12));
                       oCEComprobanteVentaMatriz.setUltimoUsuario(rs.getString(13));
                       oCEComprobanteVentaMatriz.setMontoExonerado(rs.getFloat(14));
                       oCEComprobanteVentaMatriz.setIGV(rs.getFloat(15));
                       oCEComprobanteVentaMatriz.setSubTotalNetoSinIGV(rs.getFloat(16));
                       oCEComprobanteVentaMatriz.setSerie(rs.getString(17));
                       oCEComprobanteVentaMatriz.setDNI(rs.getString(18));
                       olista.add(oCEComprobanteVentaMatriz);

                      }

                 }
             return olista;
        }

        catch(SQLException ex)
        {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, "Error al cargar la Consulta ");
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

  public static List<CEComprobanteVentaDetalle> BuscarComprobanteVentaDetalle(int pIdCns,String pNum_Fecha,int idEstado,int idtipoComp,long idCliente,String FechaFin,boolean condecuento)
    {
        Connection cnn= ConexionBD.obtenerConexion();
        try
        {
            List<CEComprobanteVentaDetalle> olista= new ArrayList<CEComprobanteVentaDetalle>();
            CEComprobanteVentaDetalle oCEComprobanteVentaDetalleMatriz=null;
                if(cnn!=null)
                {
                     CallableStatement cs= null;
                     cs = cnn.prepareCall("{CALL  VTASPRCNSListaComprobanteVentaDet(?,?,?,?,?,?,?)}");
                     cs.setInt(1, pIdCns);
                     cs.setString(2, pNum_Fecha);
                     cs.setLong(3, idCliente);
                     cs.setInt(4, idEstado);
                     cs.setInt(5, idtipoComp);
                     cs.setString(6, FechaFin);
                     cs.setBoolean(7, condecuento);
                     ResultSet rs=cs.executeQuery();
                      while(rs.next())
                      {
                       oCEComprobanteVentaDetalleMatriz=new CEComprobanteVentaDetalle();
                       oCEComprobanteVentaDetalleMatriz.setIdComprobanteVenta(rs.getLong(1));
                       oCEComprobanteVentaDetalleMatriz.setNumComprobante(rs.getString(2));
                       oCEComprobanteVentaDetalleMatriz.setFecha(rs.getString(3));
                       oCEComprobanteVentaDetalleMatriz.setMontoTotal(rs.getFloat(4));
                       oCEComprobanteVentaDetalleMatriz.setMoneda(rs.getString(5));
                       oCEComprobanteVentaDetalleMatriz.setCliente(rs.getString(6));
                       oCEComprobanteVentaDetalleMatriz.setTipoComprobante(rs.getString(7));
                       oCEComprobanteVentaDetalleMatriz.setUltimoEstado(rs.getString(8));
                       oCEComprobanteVentaDetalleMatriz.setUltimoIdEstado(rs.getInt(9));
                       oCEComprobanteVentaDetalleMatriz.setDescuentoTotal(rs.getFloat(10));
                       oCEComprobanteVentaDetalleMatriz.setHora(rs.getString(11));
                       oCEComprobanteVentaDetalleMatriz.setUltimaFecha(rs.getString(12));
                       oCEComprobanteVentaDetalleMatriz.setUltimoUsuario(rs.getString(13));
                       oCEComprobanteVentaDetalleMatriz.setMontoExonerado(rs.getFloat(14));
                       oCEComprobanteVentaDetalleMatriz.setIGV(rs.getFloat(15));
                       oCEComprobanteVentaDetalleMatriz.setSubTotalNetoSinIGV(rs.getFloat(16));
                       oCEComprobanteVentaDetalleMatriz.setSerie(rs.getString(17));
                       oCEComprobanteVentaDetalleMatriz.setDNI(rs.getString(18));
                       oCEComprobanteVentaDetalleMatriz.setCantidad(rs.getFloat(19));
                       oCEComprobanteVentaDetalleMatriz.setProducto(rs.getString(20));
                       oCEComprobanteVentaDetalleMatriz.setUnidadMedida(rs.getString(21));
                       oCEComprobanteVentaDetalleMatriz.setPrecioconDesc(rs.getFloat(22));
                       oCEComprobanteVentaDetalleMatriz.setImporteConDescuento(rs.getFloat(23));
                       olista.add(oCEComprobanteVentaDetalleMatriz);

                      }

                 }
             return olista;
        }

        catch(SQLException ex)
        {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, "Error al cargar la Consulta ");
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
    public static int AnularComprobanteVenta(CEComprobanteVentaMatriz oCEComprobante)
    {
       Connection conexion;
       conexion = ConexionBD.obtenerConexion();
        try
          {
            CallableStatement sentencia;

            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            String sql= "{call VTASPRINSComprobanteVentaEstado(?,?,?,?,?,?)}";
            sentencia = conexion.prepareCall(sql);
            sentencia.setLong(1, oCEComprobante.getIdComprobanteVenta());
            sentencia.setInt(2, 4);
            sentencia.setInt(3, oCEComprobante.getIdUsuario());
            sentencia.setInt(4, oCEComprobante.getIdEmpleado());
            sentencia.setString(5,oCEComprobante.getUltimaObservacion());
            sentencia.setString(6, oCEComprobante.getUltimoUsuario());
            sentencia.executeUpdate();
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

    public static boolean  AnularComprobanteVenta(Connection conexion,CEComprobanteVentaMatriz oCEComprobante, int estado)
    {
        try
          {
            CallableStatement sentencia;
            String sql= "{call VTASPRINSComprobanteVentaEstado(?,?,?,?,?,?)}";
            sentencia = conexion.prepareCall(sql);
            sentencia.setLong(1, oCEComprobante.getIdComprobanteVenta());
            sentencia.setInt(2, estado);
            sentencia.setInt(3, oCEComprobante.getIdUsuario());
            sentencia.setInt(4, oCEComprobante.getIdEmpleado());
            sentencia.setString(5,oCEComprobante.getUltimaObservacion());
            sentencia.setString(6, oCEComprobante.getUltimoUsuario());
            sentencia.executeUpdate();
            return true;
            }
        catch (Exception e)
          {
            System.out.print(e);
              try
                {
                  conexion.rollback();
                  return false;
                }
              catch(SQLException sqlExcep)
                {
                  sqlExcep.printStackTrace();
                  return false;
                }

          }
  }

}
