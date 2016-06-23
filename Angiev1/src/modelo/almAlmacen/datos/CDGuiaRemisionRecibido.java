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
import modelo.almAlmacen.entidad.CEGuiaRemisionRecibido;
import modelo.almAlmacen.entidad.CEGuiaRemisionRecibidoDetalle;
import modelo.cmpCompra.entidad.CEOrdenCompraDetalle;
import view.FrmSistemaMenu;


/**
 *
 * @author Luiggi
 */
public class CDGuiaRemisionRecibido
{

    public static boolean InsGuiaRemisionRecibido(CEGuiaRemisionRecibido oCEGuiaRemisionRecibido)
    {
        Connection conexion;
        conexion = ConexionBD.obtenerConexion();//5 elimina
        try
        {
            CallableStatement sentenciaGuiaRemisionRecibido;
            CallableStatement sentenciaGuiaRemisionRecibidoDetalle;
            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            String sql= "{call ALMSPRINSGuiaRemisionRecibido(?,?,?,?,?,?,?,?,?,?,?,?)}";
            sentenciaGuiaRemisionRecibido = conexion.prepareCall(sql);
            sentenciaGuiaRemisionRecibido.setLong(1, oCEGuiaRemisionRecibido.getIdProveedor());
            sentenciaGuiaRemisionRecibido.setInt(2, oCEGuiaRemisionRecibido.getIdSucursal());
            sentenciaGuiaRemisionRecibido.setInt(3, oCEGuiaRemisionRecibido.getIdEmpleado());
            sentenciaGuiaRemisionRecibido.setInt(4, oCEGuiaRemisionRecibido.getIdUsuario());
            sentenciaGuiaRemisionRecibido.setString(5, oCEGuiaRemisionRecibido.getFecha());
            sentenciaGuiaRemisionRecibido.setString(6, oCEGuiaRemisionRecibido.getNumeroDocumento());
            sentenciaGuiaRemisionRecibido.setString(7,oCEGuiaRemisionRecibido.getObservacion());
            sentenciaGuiaRemisionRecibido.setString(8,oCEGuiaRemisionRecibido.getChofer());
            sentenciaGuiaRemisionRecibido.setString(9,oCEGuiaRemisionRecibido.getPlaca());
            sentenciaGuiaRemisionRecibido.setString(10,oCEGuiaRemisionRecibido.getDestino());
            sentenciaGuiaRemisionRecibido.setString(11,oCEGuiaRemisionRecibido.getOrigen());
            sentenciaGuiaRemisionRecibido.setInt(12, oCEGuiaRemisionRecibido.getIdGuiaRemisionRecibido());

            sentenciaGuiaRemisionRecibido.executeUpdate();
            int id_GuiaRemisionRecibido = sentenciaGuiaRemisionRecibido.getInt(12);
            oCEGuiaRemisionRecibido.setIdGuiaRemisionRecibido(id_GuiaRemisionRecibido);
            for (CEGuiaRemisionRecibidoDetalle oCEGuiaRemisionRecibidoDetalle : oCEGuiaRemisionRecibido.getLstGuiaDetalle())
            {
                String sql_ = "{call ALMSPRINSGuiaRemisionRecibidoDetalle(?,?,?,?,?,?)}";
             //   long id_GuiaRemisionRecibidoDetalle = oCEGuiaRemisionRecibidoDetalle.getIdGuiaRemisionRecibidoDetalle();
                sentenciaGuiaRemisionRecibidoDetalle = conexion.prepareCall(sql_);
                sentenciaGuiaRemisionRecibidoDetalle.setInt(1, id_GuiaRemisionRecibido);
                sentenciaGuiaRemisionRecibidoDetalle.setLong(2, oCEGuiaRemisionRecibidoDetalle.getIdProducto());
                sentenciaGuiaRemisionRecibidoDetalle.setInt(3, oCEGuiaRemisionRecibidoDetalle.getIdUnidadMedida());
                sentenciaGuiaRemisionRecibidoDetalle.setFloat(4, oCEGuiaRemisionRecibidoDetalle.getCantidad());
                sentenciaGuiaRemisionRecibidoDetalle.setInt(5, oCEGuiaRemisionRecibidoDetalle.getIdGuiaRemisionRecibidoDetalle());
                sentenciaGuiaRemisionRecibidoDetalle.setInt(6, 1);
                oCEGuiaRemisionRecibidoDetalle.setSaldoCantidad(oCEGuiaRemisionRecibidoDetalle.getCantidad());
                sentenciaGuiaRemisionRecibidoDetalle.executeUpdate();

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

    public static int UPDGuiaRemisionRecibido(CEGuiaRemisionRecibido oCEGuiaRemisionRecibido)
    {
        Connection conexion;
        conexion = ConexionBD.obtenerConexion();//5 elimina
        try
        {
            CallableStatement sentenciaGuiaRemisionRecibido;
            CallableStatement sentenciaGuiaRemisionRecibidoDetalle;
            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            String sql= "{call ALMSPRUPDGuiaRemisionRecibido(?,?,?,?,?,?,?,?,?,?,?)}";
            sentenciaGuiaRemisionRecibido = conexion.prepareCall(sql);
            sentenciaGuiaRemisionRecibido.setLong(1, oCEGuiaRemisionRecibido.getIdProveedor());
            sentenciaGuiaRemisionRecibido.setInt(2, oCEGuiaRemisionRecibido.getIdEmpleado());
            sentenciaGuiaRemisionRecibido.setInt(3, oCEGuiaRemisionRecibido.getIdUsuario());
            sentenciaGuiaRemisionRecibido.setString(4, oCEGuiaRemisionRecibido.getFecha());
            sentenciaGuiaRemisionRecibido.setString(5, oCEGuiaRemisionRecibido.getNumeroDocumento());
            sentenciaGuiaRemisionRecibido.setString(6,oCEGuiaRemisionRecibido.getObservacion());
            sentenciaGuiaRemisionRecibido.setString(7,oCEGuiaRemisionRecibido.getChofer());
            sentenciaGuiaRemisionRecibido.setString(8,oCEGuiaRemisionRecibido.getPlaca());
            sentenciaGuiaRemisionRecibido.setString(9,oCEGuiaRemisionRecibido.getDestino());
            sentenciaGuiaRemisionRecibido.setString(10,oCEGuiaRemisionRecibido.getOrigen());
            sentenciaGuiaRemisionRecibido.setInt(11, oCEGuiaRemisionRecibido.getIdGuiaRemisionRecibido());

            sentenciaGuiaRemisionRecibido.executeUpdate();
            int id_GuiaRemisionRecibido = oCEGuiaRemisionRecibido.getIdGuiaRemisionRecibido();
            for (CEGuiaRemisionRecibidoDetalle oCEGuiaRemisionRecibidoDetalle : oCEGuiaRemisionRecibido.getLstGuiaDetalle())
            {
                 String sql_ = "{call ALMSPRINSGuiaRemisionRecibidoDetalle(?,?,?,?,?,?)}";
                sentenciaGuiaRemisionRecibidoDetalle = conexion.prepareCall(sql_);
                sentenciaGuiaRemisionRecibidoDetalle.setInt(1, id_GuiaRemisionRecibido);
                sentenciaGuiaRemisionRecibidoDetalle.setLong(2, oCEGuiaRemisionRecibidoDetalle.getIdProducto());
                sentenciaGuiaRemisionRecibidoDetalle.setInt(3, oCEGuiaRemisionRecibidoDetalle.getIdUnidadMedida());
                sentenciaGuiaRemisionRecibidoDetalle.setFloat(4, oCEGuiaRemisionRecibidoDetalle.getCantidad());
                sentenciaGuiaRemisionRecibidoDetalle.setInt(5, oCEGuiaRemisionRecibidoDetalle.getIdGuiaRemisionRecibidoDetalle());
                sentenciaGuiaRemisionRecibidoDetalle.setInt(6, oCEGuiaRemisionRecibidoDetalle.getOpcionAbm());
                sentenciaGuiaRemisionRecibidoDetalle.executeUpdate();

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



        public static List<CEGuiaRemisionRecibidoDetalle> ListarGuiaRemisionRecibidoDetalle(long pIdGuiaRemisionRecibido)
    {
        Connection cnn = ConexionBD.obtenerConexion();
        try
        {
            List<CEGuiaRemisionRecibidoDetalle> lista = new ArrayList<CEGuiaRemisionRecibidoDetalle>();

            CEGuiaRemisionRecibidoDetalle oCEGuiaRemisionRecibidoDetalle = null;
            if (cnn != null)
            {
                CallableStatement cs = null;
                cs = cnn.prepareCall("{CALL  ALMSPRCNSGuiaRemisionRecibidoDetalle(?)}");
                cs.setLong(1, pIdGuiaRemisionRecibido);
                ResultSet rs = cs.executeQuery();
                while (rs.next())
                {
                    oCEGuiaRemisionRecibidoDetalle = new CEGuiaRemisionRecibidoDetalle();
                    oCEGuiaRemisionRecibidoDetalle.setIdGuiaRemisionRecibidoDetalle(rs.getInt(1));
                    oCEGuiaRemisionRecibidoDetalle.setIdProducto(rs.getInt(2));
                    oCEGuiaRemisionRecibidoDetalle.setIdUnidadMedida(rs.getInt(3));
                    oCEGuiaRemisionRecibidoDetalle.setCantidad(rs.getFloat(4));
                    oCEGuiaRemisionRecibidoDetalle.setProducto(rs.getString(5));
                    oCEGuiaRemisionRecibidoDetalle.setUnidadMedida(rs.getString(6));
                    oCEGuiaRemisionRecibidoDetalle.setSaldoCantidad(rs.getFloat(7));
                    lista.add(oCEGuiaRemisionRecibidoDetalle);
                }
                return lista;
            }
            return lista;
        }
        catch (SQLException ex)
        {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, "Error al cargar el registro ");
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
                Logger.getLogger(CDGuiaRemisionRecibido.class.getName()).log(Level.SEVERE, null, ex);
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
                cs = cnn.prepareCall("{CALL  ALMSPRCNSSaldoOCdeGuiaRemisionRecibidoDetalle(?,?)}");
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
                Logger.getLogger(CDGuiaRemisionRecibido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


      public static int AnularGuiaRemisionRecibido(CEGuiaRemisionRecibido oCEGuiaRemisionRecibido)
    {
        Connection conexion;
        conexion = ConexionBD.obtenerConexion();//5 elimina
        try
        {
            CallableStatement sentenciaOrdenCompra;
            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            String sql = "{call ALMSPRINSGuiaRemisionRecibidoEstado(?,?,?,?,?)}";
            sentenciaOrdenCompra = conexion.prepareCall(sql);
            sentenciaOrdenCompra.setLong(1, oCEGuiaRemisionRecibido.getIdGuiaRemisionRecibido());
            sentenciaOrdenCompra.setInt(2, 4);
            sentenciaOrdenCompra.setInt(3,FrmSistemaMenu.oCEUsuario.getIdUsuario());
            sentenciaOrdenCompra.setInt(4, oCEGuiaRemisionRecibido.getIdEmpleado());
            sentenciaOrdenCompra.setString(5, oCEGuiaRemisionRecibido.getObservacion());
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




 public static List<CEGuiaRemisionRecibido> ListarGuiaRemisionRecibidosPorCodigo(String pDato,int opcion,long id,int idestado,String pFechaFin)
    {
        Connection cnn = ConexionBD.obtenerConexion();
        try
        {
            List<CEGuiaRemisionRecibido> lista=new ArrayList<CEGuiaRemisionRecibido>();
            CEGuiaRemisionRecibido oCEGuiaRemisionRecibido = null;
            if (cnn != null)
            {
                CallableStatement cs = null;
                cs = cnn.prepareCall("{CALL  ALMSPRCNSGuiaRemisionRecibidoPorCodigo(?,?,?,?,?)}");
                cs.setInt(1, opcion);
                cs.setString(2, pDato);
                cs.setLong(3, id);
                cs.setInt(4, idestado);
                cs.setString(5, pFechaFin);
                ResultSet rs = cs.executeQuery();
                while (rs.next())
                {
                    oCEGuiaRemisionRecibido = new CEGuiaRemisionRecibido();
                    oCEGuiaRemisionRecibido.setIdGuiaRemisionRecibido(rs.getInt(1));
                    oCEGuiaRemisionRecibido.setNumeroDocumento(rs.getString(2));
                    oCEGuiaRemisionRecibido.setFecha(rs.getString(3));
                    oCEGuiaRemisionRecibido.setProveedor(rs.getString(4));
                    oCEGuiaRemisionRecibido.setUltimoEstado(rs.getString(5));
                    lista.add(oCEGuiaRemisionRecibido);


                }

            }
            return lista;
        }
        catch (SQLException ex)
        {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, "Error al cargar el GuiaRemisionRecibido ");
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
                Logger.getLogger(CDGuiaRemisionRecibido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


 public static CEGuiaRemisionRecibido ConsultarGuiaRemisionRecibidosPorId(long id)
    {
        Connection cnn = ConexionBD.obtenerConexion();
        try
        {
            CEGuiaRemisionRecibido oCEGuiaRemisionRecibido = null;
            if (cnn != null)
            {
                CallableStatement cs = null;
                cs = cnn.prepareCall("{CALL  ALMSPRCNSGuiaRemisionRecibidoPorCodigo(?,?,?,?,?)}");
                cs.setInt(1, 1);
                cs.setString(2, null);
                cs.setLong(3, id);
                cs.setInt(4, 0);
                cs.setString(5, null);
                ResultSet rs = cs.executeQuery();
                while (rs.next())
                {
                     oCEGuiaRemisionRecibido = new CEGuiaRemisionRecibido();
                    oCEGuiaRemisionRecibido = new CEGuiaRemisionRecibido();
                    oCEGuiaRemisionRecibido.setIdGuiaRemisionRecibido(rs.getInt(1));
                    oCEGuiaRemisionRecibido.setNumeroDocumento(rs.getString(2));
                    oCEGuiaRemisionRecibido.setFecha(rs.getString(3));
                    oCEGuiaRemisionRecibido.setIdProveedor(rs.getInt(4));
                    oCEGuiaRemisionRecibido.setProveedor(rs.getString(5));
                    oCEGuiaRemisionRecibido.setRUC(rs.getString(6));
                    oCEGuiaRemisionRecibido.setEmpleado(rs.getString(7));
                    oCEGuiaRemisionRecibido.setSucursal(rs.getString(8));
                    oCEGuiaRemisionRecibido.setUltimoIdEstado(rs.getInt(9));
                    oCEGuiaRemisionRecibido.setUltimoEstado(rs.getString(10));
                    oCEGuiaRemisionRecibido.setObservacion(rs.getString(11));
                    oCEGuiaRemisionRecibido.setChofer(rs.getString(12));
                    oCEGuiaRemisionRecibido.setPlaca(rs.getString(13));
                    oCEGuiaRemisionRecibido.setDestino(rs.getString(14));
                    oCEGuiaRemisionRecibido.setOrigen(rs.getString(15));
                    oCEGuiaRemisionRecibido.setDate(rs.getDate(16));
                    oCEGuiaRemisionRecibido.setDireccion(rs.getString(17));


                }
                if (oCEGuiaRemisionRecibido != null)
                {

                    oCEGuiaRemisionRecibido.setLstGuiaDetalle(ListarGuiaRemisionRecibidoDetalle(oCEGuiaRemisionRecibido.getIdGuiaRemisionRecibido()));
                }
                return oCEGuiaRemisionRecibido;
            }
            return oCEGuiaRemisionRecibido;
        }
        catch (SQLException ex)
        {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, "Error al cargar el GuiaRemisionRecibido ");
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
                Logger.getLogger(CDGuiaRemisionRecibido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
