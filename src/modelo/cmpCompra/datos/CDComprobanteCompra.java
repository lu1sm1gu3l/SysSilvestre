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
import modelo.cmpCompra.entidad.CEComprobanteCompra;
import modelo.cmpCompra.entidad.CEComprobanteCompraDetalle;
import modelo.cmpCompra.entidad.CEComprobanteCompraMatriz;
import modelo.cmpCompra.entidad.CEEgresoFinaciero;
import modelo.cmpCompra.entidad.CEEgresoFinancieroDetalle;
import view.FrmSistemaMenu;


public class CDComprobanteCompra
{

    public static boolean pagarCompra(CEEgresoFinaciero oCEEgresoFinanicero)
    {
       Connection conexion;
       conexion = ConexionBD.obtenerConexion();//5 elimina
        try
          {

            CallableStatement sentenciaIngresoFinanciero;
            CallableStatement sentenciaIngresoFinancieroDetalle;
            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

           

            String sql4= "{call CMPSPRINSEgresoFinanciero(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            sentenciaIngresoFinanciero = conexion.prepareCall(sql4);
            sentenciaIngresoFinanciero.setLong(1,0);
            sentenciaIngresoFinanciero.setInt(2,4);
            sentenciaIngresoFinanciero.setString(3,oCEEgresoFinanicero.getNumDoc());
            sentenciaIngresoFinanciero.setLong(4,oCEEgresoFinanicero.getIdProveedor());
            sentenciaIngresoFinanciero.setFloat(5,oCEEgresoFinanicero.getIdMoneda());
            sentenciaIngresoFinanciero.setFloat(6,oCEEgresoFinanicero.getTipoCambio());
            sentenciaIngresoFinanciero.setLong(7,oCEEgresoFinanicero.getIdComprobanteCompra());
            sentenciaIngresoFinanciero.setString(8,oCEEgresoFinanicero.getObservacion());
            sentenciaIngresoFinanciero.setInt(9,oCEEgresoFinanicero.getIdSucursal());
            sentenciaIngresoFinanciero.setInt(10,oCEEgresoFinanicero.getIdPuntoVenta());
            sentenciaIngresoFinanciero.setInt(11,oCEEgresoFinanicero.getIdTipoComprobanteCompra());
            sentenciaIngresoFinanciero.setInt(12,FrmSistemaMenu.oCEUsuario.getIdEmpleado());
            sentenciaIngresoFinanciero.setInt(13,FrmSistemaMenu.oCEUsuario.getIdUsuario());
            sentenciaIngresoFinanciero.setFloat(14,oCEEgresoFinanicero.getEgresoTotalLiquido());
            sentenciaIngresoFinanciero.setFloat(15,oCEEgresoFinanicero.getMontoCuentaCorriente());
            sentenciaIngresoFinanciero.setFloat(16,oCEEgresoFinanicero.getSaldoPagar());
            sentenciaIngresoFinanciero.setFloat(17,oCEEgresoFinanicero.getIGVActual());            
            sentenciaIngresoFinanciero.setBoolean(18,true);
            sentenciaIngresoFinanciero.setString(19, oCEEgresoFinanicero.getFecha());
            sentenciaIngresoFinanciero.setFloat(20, oCEEgresoFinanicero.getMontoPago());
            sentenciaIngresoFinanciero.executeUpdate();
            long id_Egreso=sentenciaIngresoFinanciero.getInt(1);

            for (CEEgresoFinancieroDetalle oCEEgresoFinancieroDetalle : oCEEgresoFinanicero.getLstEgresoFinancieroDetalle())
            {
                String sql_= "{call CMPSPRINSEgresoFinacieroDetalle(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                sentenciaIngresoFinancieroDetalle = conexion.prepareCall(sql_);
                sentenciaIngresoFinancieroDetalle.setLong(1,id_Egreso);
                sentenciaIngresoFinancieroDetalle.setInt(2,oCEEgresoFinancieroDetalle.getIdTipoPago());
                sentenciaIngresoFinancieroDetalle.setInt(3,oCEEgresoFinancieroDetalle.getIdInstituicionFinanciera());
                sentenciaIngresoFinancieroDetalle.setInt(4,oCEEgresoFinancieroDetalle.getIdCuentaEmpresa());
                sentenciaIngresoFinancieroDetalle.setString(5,oCEEgresoFinancieroDetalle.getFechaCheque());
                sentenciaIngresoFinancieroDetalle.setString(6,oCEEgresoFinancieroDetalle.getCodigoAutorizacion());
                sentenciaIngresoFinancieroDetalle.setString(7,oCEEgresoFinancieroDetalle.getNumeroPago());
                sentenciaIngresoFinancieroDetalle.setInt(8,oCEEgresoFinanicero.getIdPuntoVenta());
                sentenciaIngresoFinancieroDetalle.setLong(9,oCEEgresoFinanicero.getIdComprobanteCompra());
                sentenciaIngresoFinancieroDetalle.setString(10,oCEEgresoFinanicero.getNumDoc());
                sentenciaIngresoFinancieroDetalle.setInt(11,oCEEgresoFinanicero.getIdMoneda());
                sentenciaIngresoFinancieroDetalle.setLong(12,oCEEgresoFinanicero.getIdProveedor());
                sentenciaIngresoFinancieroDetalle.setInt(13,oCEEgresoFinanicero.getIdUsuario());
                sentenciaIngresoFinancieroDetalle.setFloat(14,oCEEgresoFinancieroDetalle.getEgresoLiquido());
                sentenciaIngresoFinancieroDetalle.setLong(15,oCEEgresoFinancieroDetalle.getIdEgresoFinancieroDetalle());
                sentenciaIngresoFinancieroDetalle.setInt(16,oCEEgresoFinanicero.getIdEmpleado());

                sentenciaIngresoFinancieroDetalle.executeUpdate();
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
    public static CEComprobanteCompraMatriz InsComprobanteCompra(CEComprobanteCompraMatriz oCEComprobanteCompra)
    {
        Connection conexion;
        conexion = ConexionBD.obtenerConexion();//
        try
        {
            CallableStatement sentenciaComprobanteCompra;
            CallableStatement sentenciaComprobanteCompraDetalle;
            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            String sql= "{call CMPSPRINSComprobanteCompra(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            sentenciaComprobanteCompra = conexion.prepareCall(sql);
            sentenciaComprobanteCompra.setLong(1, oCEComprobanteCompra.getIdProveedor());
            sentenciaComprobanteCompra.setLong(2, oCEComprobanteCompra.getIdSucursal());
            sentenciaComprobanteCompra.setFloat(3, oCEComprobanteCompra.getDescuento());
            sentenciaComprobanteCompra.setFloat(4, oCEComprobanteCompra.getSubtotalNeto());
            sentenciaComprobanteCompra.setInt(5, oCEComprobanteCompra.getUltimoIdEstado());
            sentenciaComprobanteCompra.setInt(6, oCEComprobanteCompra.getIdTipoComprobanteCompra());
            sentenciaComprobanteCompra.setInt(7, oCEComprobanteCompra.getIdCondicion());
            sentenciaComprobanteCompra.setInt(8, oCEComprobanteCompra.getIdMoneda());
            sentenciaComprobanteCompra.setInt(9, oCEComprobanteCompra.getIdTipoDescuento());
            sentenciaComprobanteCompra.registerOutParameter(10, java.sql.Types.BIGINT);
            sentenciaComprobanteCompra.setInt(11, oCEComprobanteCompra.getIdEmpleado());
            sentenciaComprobanteCompra.setInt(12, oCEComprobanteCompra.getIdUsuario());
            sentenciaComprobanteCompra.setFloat(13, oCEComprobanteCompra.getTipoCambio());
            sentenciaComprobanteCompra.setFloat(14, oCEComprobanteCompra.getSubtotalBruto());
            sentenciaComprobanteCompra.setFloat(15, oCEComprobanteCompra.getDescuentoEnSubtotal());
            sentenciaComprobanteCompra.setFloat(16, oCEComprobanteCompra.getDescuentoTotal());
            sentenciaComprobanteCompra.setFloat(17, oCEComprobanteCompra.getDescuentoEnValores());
            sentenciaComprobanteCompra.setFloat(18, oCEComprobanteCompra.getIGVActual());
            sentenciaComprobanteCompra.setFloat(19, oCEComprobanteCompra.getIGV());
            sentenciaComprobanteCompra.setFloat(20, oCEComprobanteCompra.getSubTotalNetoSinIGV());
            sentenciaComprobanteCompra.setFloat(21, oCEComprobanteCompra.getMontoTotal());
            sentenciaComprobanteCompra.setFloat(22, oCEComprobanteCompra.getTotalPagar());
            sentenciaComprobanteCompra.setBoolean(23, oCEComprobanteCompra.isCompraDirecta());
            sentenciaComprobanteCompra.setString(24, oCEComprobanteCompra.getNumComprobante());
            sentenciaComprobanteCompra.setLong(25, oCEComprobanteCompra.getIdOrdenCompra());
            sentenciaComprobanteCompra.setString(26, oCEComprobanteCompra.getFecha());
            sentenciaComprobanteCompra.setBoolean(27, oCEComprobanteCompra.isConPercepcion());
            sentenciaComprobanteCompra.setFloat(28, oCEComprobanteCompra.getMontoPercepcion());
            sentenciaComprobanteCompra.setInt(29, oCEComprobanteCompra.getIdGuiaRemision());
            sentenciaComprobanteCompra.setInt(30, oCEComprobanteCompra.getIdEstadoInventario());

            sentenciaComprobanteCompra.executeUpdate();
            long id_ComprobanteCompra = sentenciaComprobanteCompra.getInt(10);
            oCEComprobanteCompra.setIdComprobanteCompra(id_ComprobanteCompra);
            for (CEComprobanteCompraDetalle oCEComprobanteCompraDetalle : oCEComprobanteCompra.getoLstComprobanteDetalle())
            {
                String sql_ = "{call CMPSPRINSComprobanteCompraDetalle(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                sentenciaComprobanteCompraDetalle = conexion.prepareCall(sql_);
                sentenciaComprobanteCompraDetalle.setLong(1, id_ComprobanteCompra);
                sentenciaComprobanteCompraDetalle.setLong(2, oCEComprobanteCompraDetalle.getIdOrdenCompraDetalleRef());
                sentenciaComprobanteCompraDetalle.setLong(3, oCEComprobanteCompraDetalle.getIdProducto());
                sentenciaComprobanteCompraDetalle.setFloat(4, oCEComprobanteCompraDetalle.getCantidad());
                sentenciaComprobanteCompraDetalle.setFloat(5, oCEComprobanteCompraDetalle.getDescuento());
                sentenciaComprobanteCompraDetalle.setInt(6, oCEComprobanteCompraDetalle.getIdTipoDescuento());
                sentenciaComprobanteCompraDetalle.setFloat(7, oCEComprobanteCompraDetalle.getPrecio());
                sentenciaComprobanteCompraDetalle.setFloat(8, oCEComprobanteCompraDetalle.getIdUnidadMedidaCompra());
                sentenciaComprobanteCompraDetalle.setFloat(9, oCEComprobanteCompraDetalle.getDescuentoEnValores());
                sentenciaComprobanteCompraDetalle.setFloat(10, oCEComprobanteCompraDetalle.getImporteSinDescuento());
                sentenciaComprobanteCompraDetalle.setFloat(11, oCEComprobanteCompraDetalle.getImporteConDescuento());
                sentenciaComprobanteCompraDetalle.setFloat(12, oCEComprobanteCompraDetalle.getExonerado());
                sentenciaComprobanteCompraDetalle.setBoolean(13, oCEComprobanteCompraDetalle.isSiNoImpuesto());
                sentenciaComprobanteCompraDetalle.setFloat(14, oCEComprobanteCompraDetalle.getPrecioConDesc());
                sentenciaComprobanteCompraDetalle.setFloat(15, oCEComprobanteCompraDetalle.getImporteSinDescuentoConIgv());
                sentenciaComprobanteCompraDetalle.setFloat(16, oCEComprobanteCompraDetalle.getIdComprobanteCompraDetalle());
                sentenciaComprobanteCompraDetalle.setLong(17, oCEComprobanteCompra.getIdProveedor());
                sentenciaComprobanteCompraDetalle.setBoolean(18, oCEComprobanteCompra.isConPercepcion());
                sentenciaComprobanteCompraDetalle.executeUpdate();
                oCEComprobanteCompraDetalle.setIdComprobanteCompraDetalle(sentenciaComprobanteCompraDetalle.getLong(16));

            }
            conexion.commit();
            return oCEComprobanteCompra;
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
    public static int UPDComprobanteCompra(CEComprobanteCompra oCEComprobanteCompra,boolean actDet)
    {
        Connection conexion;
        conexion = ConexionBD.obtenerConexion();//5 elimina
        try
        {
            CallableStatement sentenciaComprobanteCompra;
            CallableStatement sentenciaComprobanteCompraDetalle;
            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            String sql= "{call CMPSPRUPDComprobanteCompra(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            sentenciaComprobanteCompra = conexion.prepareCall(sql);
            sentenciaComprobanteCompra.setLong(1, oCEComprobanteCompra.getIdComprobanteCompra());
            sentenciaComprobanteCompra.setLong(2, oCEComprobanteCompra.getIdProveedor());
            sentenciaComprobanteCompra.setLong(3, oCEComprobanteCompra.getIdSucursal());
            sentenciaComprobanteCompra.setFloat(4, oCEComprobanteCompra.getDescuento());
            sentenciaComprobanteCompra.setFloat(5, oCEComprobanteCompra.getSubtotalNeto());
            sentenciaComprobanteCompra.setInt(6, oCEComprobanteCompra.getUltimoIdEstado());
            sentenciaComprobanteCompra.setInt(7, oCEComprobanteCompra.getIdTipoComprobanteCompra());
            sentenciaComprobanteCompra.setInt(8, oCEComprobanteCompra.getIdCondicion());
            sentenciaComprobanteCompra.setInt(9, oCEComprobanteCompra.getIdMoneda());
            sentenciaComprobanteCompra.setInt(10, oCEComprobanteCompra.getIdTipoDescuento());
            sentenciaComprobanteCompra.setInt(11, oCEComprobanteCompra.getIdEmpleado());
            sentenciaComprobanteCompra.setInt(12, oCEComprobanteCompra.getIdUsuario());
            sentenciaComprobanteCompra.setFloat(13, oCEComprobanteCompra.getTipoCambio());
            sentenciaComprobanteCompra.setFloat(14, oCEComprobanteCompra.getSubtotalBruto());
            sentenciaComprobanteCompra.setFloat(15, oCEComprobanteCompra.getDescuentoEnSubtotal());
            sentenciaComprobanteCompra.setFloat(16, oCEComprobanteCompra.getDescuentoTotal());
            sentenciaComprobanteCompra.setFloat(17, oCEComprobanteCompra.getDescuentoEnValores());
            sentenciaComprobanteCompra.setFloat(18, oCEComprobanteCompra.getIGVActual());
            sentenciaComprobanteCompra.setFloat(19, oCEComprobanteCompra.getIGV());
            sentenciaComprobanteCompra.setFloat(20, oCEComprobanteCompra.getSubTotalNetoSinIGV());
            sentenciaComprobanteCompra.setFloat(21, oCEComprobanteCompra.getMontoTotal());
            sentenciaComprobanteCompra.setFloat(22, oCEComprobanteCompra.getTotalPagar());
            sentenciaComprobanteCompra.setString(23, oCEComprobanteCompra.getNumComprobante());
            sentenciaComprobanteCompra.setString(24, oCEComprobanteCompra.getFecha());
            sentenciaComprobanteCompra.setBoolean(25, oCEComprobanteCompra.isConPercepcion());
            sentenciaComprobanteCompra.setFloat(26, oCEComprobanteCompra.getMontoPercepcion());
            sentenciaComprobanteCompra.setBoolean(27, oCEComprobanteCompra.isCompraDirecta());

            sentenciaComprobanteCompra.executeUpdate();
            long id_ComprobanteCompra = oCEComprobanteCompra.getIdComprobanteCompra();
            if(actDet){
                for (CEComprobanteCompraDetalle oCEComprobanteCompraDetalle : oCEComprobanteCompra.getoLstComprobanteDetalle())
                {
                    String sql_ = "{call CMPSPRABMComprobanteCompraDetalle(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

                    sentenciaComprobanteCompraDetalle = conexion.prepareCall(sql_);
                    sentenciaComprobanteCompraDetalle.setLong(1, oCEComprobanteCompraDetalle.getIdComprobanteCompraDetalle());
                    sentenciaComprobanteCompraDetalle.setLong(2, id_ComprobanteCompra);
                    sentenciaComprobanteCompraDetalle.setFloat(3, oCEComprobanteCompraDetalle.getCantidad());
                    sentenciaComprobanteCompraDetalle.setFloat(4, oCEComprobanteCompraDetalle.getDescuento());
                    sentenciaComprobanteCompraDetalle.setLong(5, oCEComprobanteCompraDetalle.getIdProducto());
                    sentenciaComprobanteCompraDetalle.setInt(6, oCEComprobanteCompraDetalle.getIdTipoDescuento());
                    sentenciaComprobanteCompraDetalle.setFloat(7, oCEComprobanteCompraDetalle.getPrecio());
                    sentenciaComprobanteCompraDetalle.setFloat(8, oCEComprobanteCompraDetalle.getIdUnidadMedidaCompra());
                    sentenciaComprobanteCompraDetalle.setFloat(9, oCEComprobanteCompraDetalle.getDescuentoEnValores());
                    sentenciaComprobanteCompraDetalle.setFloat(10, oCEComprobanteCompraDetalle.getImporteSinDescuento());
                    sentenciaComprobanteCompraDetalle.setFloat(11, oCEComprobanteCompraDetalle.getImporteConDescuento());
                    sentenciaComprobanteCompraDetalle.setFloat(12, oCEComprobanteCompraDetalle.getExonerado());
                    sentenciaComprobanteCompraDetalle.setBoolean(13, oCEComprobanteCompraDetalle.isSiNoImpuesto());
                    sentenciaComprobanteCompraDetalle.setFloat(14, oCEComprobanteCompraDetalle.getPrecioConDesc());
                    sentenciaComprobanteCompraDetalle.setFloat(15, oCEComprobanteCompraDetalle.getImporteSinDescuentoConIgv());
                    sentenciaComprobanteCompraDetalle.setFloat(16, oCEComprobanteCompraDetalle.getIdAbm());
                    sentenciaComprobanteCompraDetalle.setLong(17, oCEComprobanteCompra.getIdProveedor());
                    sentenciaComprobanteCompraDetalle.setBoolean(18, oCEComprobanteCompra.isConPercepcion());
                    sentenciaComprobanteCompraDetalle.executeUpdate();

                }
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

   
    public static List<CEComprobanteCompraMatriz> BuscarComprobanteCompra(int pIdCns,String pDato,int idEstado)
    {
        Connection cnn= ConexionBD.obtenerConexion();
        try
        {
            List<CEComprobanteCompraMatriz> olista= new ArrayList<CEComprobanteCompraMatriz>();
            CEComprobanteCompraMatriz oCEComprobanteCompraMatriz=null;
                if(cnn!=null)
                {
                     CallableStatement cs= null;
                     cs = cnn.prepareCall("{CALL  VTASPRCNSComprobanteCompra(?,?,?,?)}");
                     cs.setInt(1, pIdCns);
                     cs.setString(2, pDato);
                     cs.setLong(3, 0);
                     cs.setInt(4, idEstado);
                     ResultSet rs=cs.executeQuery();
                      while(rs.next())
                      {
                       oCEComprobanteCompraMatriz=new CEComprobanteCompraMatriz();
                       oCEComprobanteCompraMatriz.setIdComprobanteCompra(rs.getLong(1));
                       oCEComprobanteCompraMatriz.setIdCondicion(rs.getInt(2));
                       oCEComprobanteCompraMatriz.setUltimoIdEstado(rs.getInt(3));
                       oCEComprobanteCompraMatriz.setIdTipoComprobanteCompra(rs.getInt(4));
                       oCEComprobanteCompraMatriz.setNumComprobante(rs.getString(5));
                       oCEComprobanteCompraMatriz.setIdEmpleado(rs.getInt(6));
                       oCEComprobanteCompraMatriz.setIdUsuario(rs.getInt(7));
                       oCEComprobanteCompraMatriz.setIdProveedor(rs.getInt(8));
                       oCEComprobanteCompraMatriz.setIdSucursal(rs.getInt(9));
                       oCEComprobanteCompraMatriz.setFecha(rs.getString(10));
                       oCEComprobanteCompraMatriz.setSubtotalBruto(rs.getFloat(11));
                       oCEComprobanteCompraMatriz.setDescuentoEnSubtotal(rs.getFloat(12));
                       oCEComprobanteCompraMatriz.setSubtotalNeto(rs.getFloat(13));
                       oCEComprobanteCompraMatriz.setSubTotalNetoSinIGV(rs.getFloat(14));
                       oCEComprobanteCompraMatriz.setIGV(rs.getFloat(15));
                       oCEComprobanteCompraMatriz.setISC(rs.getFloat(16));
                       oCEComprobanteCompraMatriz.setIdDescuento(rs.getInt(17));
                       oCEComprobanteCompraMatriz.setDescuento(rs.getFloat(18));
                       oCEComprobanteCompraMatriz.setDescuentoTotal(rs.getFloat(19));
                       oCEComprobanteCompraMatriz.setMontoTotal(rs.getFloat(20));
                       oCEComprobanteCompraMatriz.setIdMoneda(rs.getInt(21));
                       oCEComprobanteCompraMatriz.setMoneda(rs.getString(22));
                       oCEComprobanteCompraMatriz.setProveedor(rs.getString(23));
                       oCEComprobanteCompraMatriz.setRUC(rs.getString(24));
                       oCEComprobanteCompraMatriz.setDireccion(rs.getString(25));
                       oCEComprobanteCompraMatriz.setTipoDescuento(rs.getString(26));
                       oCEComprobanteCompraMatriz.setTipoComprobante(rs.getString(27));
                       oCEComprobanteCompraMatriz.setUltimoEstado(rs.getString(28));

                       olista.add(oCEComprobanteCompraMatriz);

                      }

                 }
             return olista;
        }

        catch(SQLException ex)
        {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, "Error al cargar el ComprobanteCompra ");
            return null;
        }
        finally
        {
            try {
                cnn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CDComprobanteCompra.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static List<CEComprobanteCompraMatriz> ListarComprobanteComprasPorCodigo(String pDato,int opcion,long id,int idestado, int idtipocomp,int idestadoInv,String pFechaFin,boolean pIsCompradir)
    {
        Connection cnn = ConexionBD.obtenerConexion();
        try
        {
            List<CEComprobanteCompraMatriz> lista=new ArrayList<CEComprobanteCompraMatriz>();
            CEComprobanteCompraMatriz oCEComprobanteCompraMatriz = null;
            if (cnn != null)
            {
                CallableStatement cs = null;
                cs = cnn.prepareCall("{CALL  CMPSPRCNSComprobanteCompraPorCodigo(?,?,?,?,?,?,?,?)}");
                cs.setInt(1, opcion);
                cs.setString(2, pDato);
                cs.setLong(3, id);
                cs.setInt(4, idestado);
                cs.setInt(5, idtipocomp);
                cs.setInt(6, idestadoInv);
                cs.setString(7, pFechaFin);
                cs.setBoolean(8, pIsCompradir);
                ResultSet rs = cs.executeQuery();
                while (rs.next())
                {
                    oCEComprobanteCompraMatriz = new CEComprobanteCompraMatriz();
                    oCEComprobanteCompraMatriz.setIdComprobanteCompra(rs.getLong(1));
                    oCEComprobanteCompraMatriz.setNumComprobante(rs.getString(2));
                    oCEComprobanteCompraMatriz.setFecha(rs.getString(3));
                    oCEComprobanteCompraMatriz.setTotalPagar(rs.getFloat(4));
                    oCEComprobanteCompraMatriz.setMoneda(rs.getString(5));
                    oCEComprobanteCompraMatriz.setProveedor(rs.getString(6));
                    oCEComprobanteCompraMatriz.setTipoComprobante(rs.getString(7));
                    oCEComprobanteCompraMatriz.setUltimoEstado(rs.getString(8));
                    oCEComprobanteCompraMatriz.setSaldoPagar(rs.getFloat(9));
                    oCEComprobanteCompraMatriz.setEstadoInventario(rs.getString(10));
                    oCEComprobanteCompraMatriz.setIdEstadoInventario(rs.getInt(11));
                    oCEComprobanteCompraMatriz.setUltimoIdEstado(rs.getInt(12));
                    lista.add(oCEComprobanteCompraMatriz);


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


 public static CEComprobanteCompraMatriz ConsultarComprobanteComprasPorId(long id,boolean conDetalle)
    {
        Connection cnn = ConexionBD.obtenerConexion();
        try
        {
            CEComprobanteCompraMatriz oCEComprobanteCompraMatriz = null;
            if (cnn != null)
            {
                CallableStatement cs = null;
                cs = cnn.prepareCall("{CALL  CMPSPRCNSComprobanteCompraPorId(?)}");
                cs.setLong(1, id);
                ResultSet rs = cs.executeQuery();
                while (rs.next())
                {
                    oCEComprobanteCompraMatriz = new CEComprobanteCompraMatriz();
                    oCEComprobanteCompraMatriz.setIdComprobanteCompra(rs.getLong(1));
                    oCEComprobanteCompraMatriz.setIdProveedor(rs.getInt(2));
                    oCEComprobanteCompraMatriz.setIdSucursal(rs.getInt(3));
                    oCEComprobanteCompraMatriz.setFecha(rs.getString(4));
                    oCEComprobanteCompraMatriz.setDescuento(rs.getFloat(5));
                    oCEComprobanteCompraMatriz.setIdTipoComprobanteCompra(rs.getInt(6));
                    oCEComprobanteCompraMatriz.setIdCondicion(rs.getInt(7));
                    oCEComprobanteCompraMatriz.setIdMoneda(rs.getInt(8));
                    oCEComprobanteCompraMatriz.setIdDescuento(rs.getInt(9));
                    oCEComprobanteCompraMatriz.setProveedor(rs.getString(10));
                    oCEComprobanteCompraMatriz.setDireccion(rs.getString(11));
                    oCEComprobanteCompraMatriz.setRUC(rs.getString(12));
                    oCEComprobanteCompraMatriz.setEmpleado(rs.getString(13));
                    oCEComprobanteCompraMatriz.setUltimoIdEstado(rs.getInt(14));
                    oCEComprobanteCompraMatriz.setUltimoEstado(rs.getString(15));
                    oCEComprobanteCompraMatriz.setSubtotalBruto(rs.getFloat(16));
                    oCEComprobanteCompraMatriz.setDescuentoEnSubtotal(rs.getFloat(17));
                    oCEComprobanteCompraMatriz.setDescuentoTotal(rs.getFloat(18));
                    oCEComprobanteCompraMatriz.setSubtotalNeto(rs.getFloat(19));
                    oCEComprobanteCompraMatriz.setSubTotalNetoSinIGV(rs.getFloat(20));
                    oCEComprobanteCompraMatriz.setDescuentoEnValores(rs.getFloat(21));
                    oCEComprobanteCompraMatriz.setIGV(rs.getFloat(22));
                    oCEComprobanteCompraMatriz.setISC(rs.getFloat(23));
                    oCEComprobanteCompraMatriz.setIGVActual(rs.getFloat(24));
                    oCEComprobanteCompraMatriz.setMontoTotal(rs.getFloat(25));
                    oCEComprobanteCompraMatriz.setTotalPagar(rs.getFloat(26));
                    oCEComprobanteCompraMatriz.setSucursal(rs.getString(27));
                    oCEComprobanteCompraMatriz.setNumComprobante(rs.getString(28));
                    oCEComprobanteCompraMatriz.setIdEstadoInventario(rs.getInt(29));
                    oCEComprobanteCompraMatriz.setEstadoInventario(rs.getString(30));
                    oCEComprobanteCompraMatriz.setSaldoPagar(rs.getFloat(31));
                    oCEComprobanteCompraMatriz.setTipoCambio(rs.getFloat(32));
                    oCEComprobanteCompraMatriz.setDate(rs.getDate(33));
                    oCEComprobanteCompraMatriz.setMontoPercepcion(rs.getFloat(34));
                    oCEComprobanteCompraMatriz.setConPercepcion(rs.getBoolean(35));
                    oCEComprobanteCompraMatriz.setTipoComprobante(rs.getString(36));
                    oCEComprobanteCompraMatriz.setCondicion(rs.getString(37));
                    oCEComprobanteCompraMatriz.setMoneda(rs.getString(38));
                    oCEComprobanteCompraMatriz.setCompraDirecta(rs.getBoolean(39));
                    oCEComprobanteCompraMatriz.setUltimaFecha(rs.getString(40));
                }
                if(conDetalle){
                    if (oCEComprobanteCompraMatriz != null)
                    {

                        oCEComprobanteCompraMatriz.setoLstComprobanteDetalle(ListarComprobanteDetalle(oCEComprobanteCompraMatriz.getIdComprobanteCompra()));
                    }
                }
                return oCEComprobanteCompraMatriz;
            }
            return oCEComprobanteCompraMatriz;
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

 public static CEEgresoFinaciero ConsultarEgresoPorId(long id)
    {
        Connection cnn = ConexionBD.obtenerConexion();
        try
        {
            CEEgresoFinaciero oCEEgresoFinanciero = null;
            if (cnn != null)
            {
                CallableStatement cs = null;
                cs = cnn.prepareCall("{CALL  CMPSPRCNSEgresoFinanciero(?)}");
                cs.setLong(1, id);
                ResultSet rs = cs.executeQuery();
                while (rs.next())
                {
                    oCEEgresoFinanciero = new CEEgresoFinaciero();
                    oCEEgresoFinanciero.setIdEgresoFianciero(rs.getLong(1));
                    oCEEgresoFinanciero.setIdComprobanteCompra(rs.getLong(2));
                    oCEEgresoFinanciero.setIdProveedor(rs.getInt(3));
                    oCEEgresoFinanciero.setIdSucursal(rs.getInt(4));
                    oCEEgresoFinanciero.setFecha(rs.getString(5));
                    oCEEgresoFinanciero.setIdTipoComprobanteCompra(rs.getInt(6));
                    oCEEgresoFinanciero.setIdMoneda(rs.getInt(7));
                    oCEEgresoFinanciero.setProveedor(rs.getString(8));
                    oCEEgresoFinanciero.setDireccion(rs.getString(9));
                    oCEEgresoFinanciero.setRUC(rs.getString(10));
                    oCEEgresoFinanciero.setEmpleado(rs.getString(11));
                    oCEEgresoFinanciero.setUltimoIdEstado(rs.getInt(12));
                    oCEEgresoFinanciero.setUltimoEstado(rs.getString(13));
                    oCEEgresoFinanciero.setIGVActual(rs.getFloat(14));
                    oCEEgresoFinanciero.setMontoTotal(rs.getFloat(15));
                    oCEEgresoFinanciero.setEgresoTotalLiquido(rs.getFloat(16));
                    oCEEgresoFinanciero.setSucursal(rs.getString(17));
                    oCEEgresoFinanciero.setNumDoc(rs.getString(18));
                    oCEEgresoFinanciero.setSaldoPagar(rs.getFloat(19));
                    oCEEgresoFinanciero.setTipoCambio(rs.getFloat(20));
                    oCEEgresoFinanciero.setDate(rs.getDate(21));
                    oCEEgresoFinanciero.setUltimoIdEstado(rs.getInt(22));

                }

                List<CEEgresoFinancieroDetalle> lstCEEgresoFinancieroDetalle= new ArrayList<CEEgresoFinancieroDetalle>();
                if(oCEEgresoFinanciero.getIdEgresoFianciero()!=0)
                {
                    CEEgresoFinancieroDetalle oCEEgresoFinancieroDetalle= null;

                    CallableStatement csdt = null;
                        csdt = cnn.prepareCall("{CALL  CMPSPRCNSEgresoFinancieroDetalle(?)}");
                        csdt.setLong(1, oCEEgresoFinanciero.getIdEgresoFianciero());
                        ResultSet rsdt = csdt.executeQuery();
                        while (rsdt.next())
                        {
                            oCEEgresoFinancieroDetalle= new CEEgresoFinancieroDetalle();
                           oCEEgresoFinancieroDetalle.setIdEgresoFinancieroDetalle(rsdt.getLong(1));
                           oCEEgresoFinancieroDetalle.setIdEgresoFianciero(rsdt.getLong(2));
                           oCEEgresoFinancieroDetalle.setIdTipoPago(rsdt.getInt(3));
                           oCEEgresoFinancieroDetalle.setTipoPago(rsdt.getString(4));
                           oCEEgresoFinancieroDetalle.setIdInstituicionFinanciera(rsdt.getInt(5));
                           oCEEgresoFinancieroDetalle.setInstitucionFinanciera(rsdt.getString(6));
                           oCEEgresoFinancieroDetalle.setIdCuentaEmpresa(rsdt.getInt(7));
                           oCEEgresoFinancieroDetalle.setNumeroCuenta(rsdt.getString(8));
                           oCEEgresoFinancieroDetalle.setFechaCheque(rsdt.getString(9));
                           oCEEgresoFinancieroDetalle.setCodigoAutorizacion(rsdt.getString(10));
                           oCEEgresoFinancieroDetalle.setNumeroPago(rsdt.getString(11));
                           oCEEgresoFinancieroDetalle.setIdPuntoVenta(rsdt.getInt(12));
                           oCEEgresoFinancieroDetalle.setEgresoLiquido(rsdt.getInt(13));
                           oCEEgresoFinancieroDetalle.setFecha(rsdt.getString(14));
                           oCEEgresoFinancieroDetalle.setUsuario(rsdt.getString(15));

                           lstCEEgresoFinancieroDetalle.add(oCEEgresoFinancieroDetalle);
                        }                        
                }

                
                oCEEgresoFinanciero.setLstEgresoFinancieroDetalle(lstCEEgresoFinancieroDetalle);

                return oCEEgresoFinanciero;
            }
            return oCEEgresoFinanciero;
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
public static CEEgresoFinaciero ConsultarEgresoPorIdCompra(long id)
    {
        Connection cnn = ConexionBD.obtenerConexion();
        try
        {
            CEEgresoFinaciero oCEEgresoFinanciero = null;
            if (cnn != null)
            {
                CallableStatement cs = null;
                cs = cnn.prepareCall("{CALL  CMPSPRCNSEgresoFinanciero(?)}");
                cs.setLong(1, id);
                ResultSet rs = cs.executeQuery();
                while (rs.next())
                {
                    oCEEgresoFinanciero = new CEEgresoFinaciero();
                    oCEEgresoFinanciero.setIdEgresoFianciero(rs.getLong(1));
                    oCEEgresoFinanciero.setIdComprobanteCompra(rs.getLong(2));
                    oCEEgresoFinanciero.setIdProveedor(rs.getInt(3));
                    oCEEgresoFinanciero.setIdSucursal(rs.getInt(4));
                    oCEEgresoFinanciero.setFecha(rs.getString(5));
                    oCEEgresoFinanciero.setIdTipoComprobanteCompra(rs.getInt(6));
                    oCEEgresoFinanciero.setIdMoneda(rs.getInt(7));
                    oCEEgresoFinanciero.setProveedor(rs.getString(8));
                    oCEEgresoFinanciero.setDireccion(rs.getString(9));
                    oCEEgresoFinanciero.setRUC(rs.getString(10));
                    oCEEgresoFinanciero.setEmpleado(rs.getString(11));
                    oCEEgresoFinanciero.setUltimoIdEstado(rs.getInt(12));
                    oCEEgresoFinanciero.setUltimoEstado(rs.getString(13));
                    oCEEgresoFinanciero.setIGVActual(rs.getFloat(14));
                    oCEEgresoFinanciero.setMontoTotal(rs.getFloat(15));
                    oCEEgresoFinanciero.setEgresoTotalLiquido(rs.getFloat(16));
                    oCEEgresoFinanciero.setSucursal(rs.getString(17));
                    oCEEgresoFinanciero.setNumDoc(rs.getString(18));
                    oCEEgresoFinanciero.setSaldoPagar(rs.getFloat(19));
                    oCEEgresoFinanciero.setTipoCambio(rs.getFloat(20));
                    oCEEgresoFinanciero.setDate(rs.getDate(21));
                    oCEEgresoFinanciero.setUltimoIdEstado(rs.getInt(22));

                }

                List<CEEgresoFinancieroDetalle> lstCEEgresoFinancieroDetalle= new ArrayList<CEEgresoFinancieroDetalle>();

                oCEEgresoFinanciero.setLstEgresoFinancieroDetalle(lstCEEgresoFinancieroDetalle);

                return oCEEgresoFinanciero;
            }
            return oCEEgresoFinanciero;
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
    public static List<CEComprobanteCompraDetalle> ListarComprobanteDetalle(long IdComprobante)
    {
        Connection cnn= ConexionBD.obtenerConexion();
        try
        {
            List<CEComprobanteCompraDetalle> lista= new ArrayList<CEComprobanteCompraDetalle>();

            CEComprobanteCompraDetalle oCEComprobanteCompraDetalle=null;
                if(cnn!=null)
                {
                     CallableStatement cs= null;
                     cs = cnn.prepareCall("{CALL  CMPSPRCNSComprobanteCompraDetalle(?)}");
                     cs.setLong(1, IdComprobante);
                     ResultSet rs=cs.executeQuery();
                      while(rs.next())
                      {

                       oCEComprobanteCompraDetalle=new CEComprobanteCompraDetalle();
                       oCEComprobanteCompraDetalle.setIdComprobanteCompraDetalle(rs.getLong(1));
                       oCEComprobanteCompraDetalle.setIdProducto(rs.getInt(2));
                       oCEComprobanteCompraDetalle.setImporteSinDescuento(rs.getFloat(3));
                       oCEComprobanteCompraDetalle.setPrecio(rs.getFloat(4));
                       oCEComprobanteCompraDetalle.setCantidad(rs.getFloat(5));
                       oCEComprobanteCompraDetalle.setIdTipoDescuento(rs.getInt(6));
                       oCEComprobanteCompraDetalle.setDescuento(rs.getFloat(7));
                       oCEComprobanteCompraDetalle.setDescuentoEnValores(rs.getFloat(8));
                       oCEComprobanteCompraDetalle.setImporteConDescuento(rs.getFloat(9));
                       oCEComprobanteCompraDetalle.setIdUnidadMedidaCompra(rs.getInt(10));
                       oCEComprobanteCompraDetalle.setTipoDescuento(rs.getString(11));
                       oCEComprobanteCompraDetalle.setUnidadMedida(rs.getString(12));
                       oCEComprobanteCompraDetalle.setProducto(rs.getString(13));
                       oCEComprobanteCompraDetalle.setSiNoImpuesto(rs.getBoolean(14));
                       oCEComprobanteCompraDetalle.setExonerado(rs.getFloat(15));
                       oCEComprobanteCompraDetalle.setPrecioConDesc(rs.getFloat(16));
                       oCEComprobanteCompraDetalle.setSaldoCantidad(rs.getFloat(17));
                       oCEComprobanteCompraDetalle.setImporteSinDescuentoConIgv(rs.getFloat(18));

                       lista.add(oCEComprobanteCompraDetalle);
                      }
                     return lista;
                 }
             return lista;
        }

        catch(SQLException ex)
        {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, "Error al cargar el ComprobanteCompra ");
            return null;
        }
        finally
        {
            try {
                cnn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CDComprobanteCompra.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 
    public static int AnularComprobanteCompra(CEComprobanteCompraMatriz oCEComprobante)
    {
       Connection conexion;
       conexion = ConexionBD.obtenerConexion();
        try
          {
            CallableStatement sentencia;

            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            String sql= "{call CMPSPRAnularComprobanteCompra(?,?,?,?,?)}";
            sentencia = conexion.prepareCall(sql);
            sentencia.setLong(1, oCEComprobante.getIdComprobanteCompra());
            sentencia.setInt(2, 4);
            sentencia.setInt(3, oCEComprobante.getIdUsuario());
            sentencia.setInt(4, oCEComprobante.getIdEmpleado());
            sentencia.setString(5,oCEComprobante.getUltimaObservacion());
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
    public static boolean  AnularComprobanteCompra(Connection conexion,CEComprobanteCompraMatriz oCEComprobante, int estado)
    {
        try
          {
            CallableStatement sentencia;
            String sql= "{call VTASPRINSComprobanteCompraEstado(?,?,?,?,?,?)}";
            sentencia = conexion.prepareCall(sql);
            sentencia.setLong(1, oCEComprobante.getIdComprobanteCompra());
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
    

    public static CEComprobanteCompraMatriz ListarComprasPorIdIngreso(long pId,int opcion)
    {
        Connection cnn = ConexionBD.obtenerConexion();
        try
        {

            CEComprobanteCompraMatriz oCEComprobanteCompraMatriz = null;
            if (cnn != null)
            {
                CallableStatement cs = null;
                cs = cnn.prepareCall("{CALL  CMPSPRCNSComporbanteCompraPorIdIngreso(?,?)}");
                cs.setLong(1, pId);
                cs.setInt(2, opcion);
                ResultSet rs = cs.executeQuery();
                while (rs.next())
                {
                    oCEComprobanteCompraMatriz = new CEComprobanteCompraMatriz();
                    oCEComprobanteCompraMatriz.setIdComprobanteCompra(rs.getLong(1));
                    oCEComprobanteCompraMatriz.setIdMoneda(rs.getInt(2));
                    oCEComprobanteCompraMatriz.setTipoCambio(rs.getFloat(3));
                }
                if (oCEComprobanteCompraMatriz != null)
                {

                    oCEComprobanteCompraMatriz.setoLstComprobanteDetalle(ListarComprobanteDetalle(oCEComprobanteCompraMatriz.getIdComprobanteCompra()));
                }
                return oCEComprobanteCompraMatriz;
            }
            return oCEComprobanteCompraMatriz;
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
                Logger.getLogger(CDComprobanteCompra.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
