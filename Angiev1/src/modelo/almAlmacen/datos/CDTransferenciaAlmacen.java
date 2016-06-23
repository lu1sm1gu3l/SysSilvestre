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
import modelo.almAlmacen.entidad.CETransferenciaAlmacen;
import modelo.almAlmacen.entidad.CETransferenciaAlmacenDetalle;


public class CDTransferenciaAlmacen
{

    public static String InsTransferenciaAlmacen(CETransferenciaAlmacen oCETransferenciaAlmacen)
    {
        String   mensajeStock="";
        Connection conexion;
        conexion = ConexionBD.obtenerConexion();//5 elimina
        try
        {
            CallableStatement sentenciaTransferenciaAlmacen;
            CallableStatement sentenciaTransferenciaAlmacenDetalle;
            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            String sql= "{call ALMSPRINSTransferenciaAlmacen(?,?,?,?,?,?,?,?,?,?,?)}";
            sentenciaTransferenciaAlmacen = conexion.prepareCall(sql);
            sentenciaTransferenciaAlmacen.setLong(1, oCETransferenciaAlmacen.getIdAlmacenOrigen());
            sentenciaTransferenciaAlmacen.setLong(2, oCETransferenciaAlmacen.getIdAlmacenDestino());
            sentenciaTransferenciaAlmacen.setInt(3, oCETransferenciaAlmacen.getIdSucursal());
            sentenciaTransferenciaAlmacen.setInt(4, oCETransferenciaAlmacen.getIdEmpleado());
            sentenciaTransferenciaAlmacen.setInt(5, oCETransferenciaAlmacen.getIdUsuario());
            sentenciaTransferenciaAlmacen.setString(6, oCETransferenciaAlmacen.getFecha());
            sentenciaTransferenciaAlmacen.setString(7, null);//java.sql.Types.VARCHAR);// numero
            sentenciaTransferenciaAlmacen.setLong(8, 0 );// id
            sentenciaTransferenciaAlmacen.setLong(9, 0);// idIngreso
            sentenciaTransferenciaAlmacen.setLong(10,0);// idSalida
            sentenciaTransferenciaAlmacen.setString(11,oCETransferenciaAlmacen.getObservacion());

            sentenciaTransferenciaAlmacen.executeUpdate();
            long id_TransferenciaAlmacen = sentenciaTransferenciaAlmacen.getLong(8);
            long id_Ingreso = sentenciaTransferenciaAlmacen.getLong(9);
            long id_Salida = sentenciaTransferenciaAlmacen.getLong(10);
            oCETransferenciaAlmacen.setNumeroTransferencia(sentenciaTransferenciaAlmacen.getString(7));
            int contarProdSinStock=0;
            for (CETransferenciaAlmacenDetalle oCETransferenciaAlmacenDetalle : oCETransferenciaAlmacen.getLstCETransferenciaAlmacenDetalle())
            {
                String sql_ = "{call ALMSPRINSTransferenciaAlmacenDetalle(?,?,?,?,?,?,?,?,?,?)}";
             //   long id_TransferenciaAlmacenDetalle = oCETransferenciaAlmacenDetalle.getIdTransferenciaAlmacenDetalle();
                sentenciaTransferenciaAlmacenDetalle = conexion.prepareCall(sql_);
                sentenciaTransferenciaAlmacenDetalle.setLong(1, id_TransferenciaAlmacen);
                sentenciaTransferenciaAlmacenDetalle.setLong(2, oCETransferenciaAlmacenDetalle.getIdProducto());
                sentenciaTransferenciaAlmacenDetalle.setInt(3, oCETransferenciaAlmacen.getIdAlmacenOrigen());
                sentenciaTransferenciaAlmacenDetalle.setInt(4, oCETransferenciaAlmacenDetalle.getIdUnidadMedida());
                sentenciaTransferenciaAlmacenDetalle.setFloat(5, oCETransferenciaAlmacenDetalle.getCantidad());
                sentenciaTransferenciaAlmacenDetalle.setLong(6, id_Ingreso);
                sentenciaTransferenciaAlmacenDetalle.setLong(7, id_Salida);
                sentenciaTransferenciaAlmacenDetalle.setInt(8, oCETransferenciaAlmacen.getIdAlmacenDestino());
                sentenciaTransferenciaAlmacenDetalle.setInt(9, 0);
                sentenciaTransferenciaAlmacenDetalle.setString(10, "");
                sentenciaTransferenciaAlmacenDetalle.executeUpdate();
                int actualizar = sentenciaTransferenciaAlmacenDetalle.getInt(9);
                if(actualizar==0)
                {
                    mensajeStock=mensajeStock+sentenciaTransferenciaAlmacenDetalle.getString(10);
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

    public static int UPDTransferenciaAlmacen(CETransferenciaAlmacen oCETransferenciaAlmacen)
    {
        Connection conexion;
        conexion = ConexionBD.obtenerConexion();//5 elimina
        try
        {
            CallableStatement sentenciaTransferenciaAlmacen;
            CallableStatement sentenciaTransferenciaAlmacenDetalle;
            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            String sql= "{call ALMSPRUPDTransferenciaAlmacen(?,?,?,?,?,?,?)}";
            sentenciaTransferenciaAlmacen = conexion.prepareCall(sql);
//            sentenciaTransferenciaAlmacen.setLong(1, oCETransferenciaAlmacen.getIdProveedor());
//            sentenciaTransferenciaAlmacen.setInt(2, oCETransferenciaAlmacen.getIdEmpleado());
//            sentenciaTransferenciaAlmacen.setInt(3, oCETransferenciaAlmacen.getIdUsuario());
//            sentenciaTransferenciaAlmacen.setString(4, oCETransferenciaAlmacen.getFecha());
//            sentenciaTransferenciaAlmacen.setLong(5, oCETransferenciaAlmacen.getIdTransferenciaAlmacen());// idIngresoProd
//            sentenciaTransferenciaAlmacen.setString(6, oCETransferenciaAlmacen.getNumeroDocumento());// numero Doc
            sentenciaTransferenciaAlmacen.setString(7,oCETransferenciaAlmacen.getObservacion());

            sentenciaTransferenciaAlmacen.executeUpdate();
            long id_TransferenciaAlmacen = oCETransferenciaAlmacen.getIdTransferenciaAlmacen();
            for (CETransferenciaAlmacenDetalle oCETransferenciaAlmacenDetalle : oCETransferenciaAlmacen.getLstCETransferenciaAlmacenDetalle())
            {
                String sql_ = "{call ALMSPRINSTransferenciaAlmacenDetalle(?,?,?,?,?,?,?)}";
             //   long id_TransferenciaAlmacenDetalle = oCETransferenciaAlmacenDetalle.getIdTransferenciaAlmacenDetalle();
                sentenciaTransferenciaAlmacenDetalle = conexion.prepareCall(sql_);
                sentenciaTransferenciaAlmacenDetalle.setLong(1, id_TransferenciaAlmacen);
                sentenciaTransferenciaAlmacenDetalle.setLong(2, oCETransferenciaAlmacenDetalle.getIdProducto());
                sentenciaTransferenciaAlmacenDetalle.setInt(3, oCETransferenciaAlmacenDetalle.getIdAlmacen());
                sentenciaTransferenciaAlmacenDetalle.setInt(4, oCETransferenciaAlmacenDetalle.getIdUnidadMedida());
                sentenciaTransferenciaAlmacenDetalle.setFloat(5, oCETransferenciaAlmacenDetalle.getCantidad());
                sentenciaTransferenciaAlmacenDetalle.setFloat(6, oCETransferenciaAlmacenDetalle.getCosto());
                sentenciaTransferenciaAlmacenDetalle.setFloat(7, oCETransferenciaAlmacenDetalle.getImporte());
                sentenciaTransferenciaAlmacenDetalle.executeUpdate();

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


   public static CETransferenciaAlmacen ListarTransferenciaAlmacenPorId(int pOpcion,long pId)
    {
        Connection cnn = ConexionBD.obtenerConexion();
        try
        {
            
            CETransferenciaAlmacen oCETransferenciaAlmacen = null;
            if (cnn != null)
            {
                CallableStatement cs = null;
                cs = cnn.prepareCall("{CALL  ALMSPRCNSTransferenciaAlmacenPorId(?,?)}");
                cs.setInt(1, pOpcion);
                cs.setLong(2, pId);
                ResultSet rs = cs.executeQuery();
                while (rs.next())
                {
                    oCETransferenciaAlmacen = new CETransferenciaAlmacen();
                    oCETransferenciaAlmacen.setIdTransferenciaAlmacen(rs.getLong(1));
                    oCETransferenciaAlmacen.setIdAlmacenOrigen(rs.getInt(2));
                    oCETransferenciaAlmacen.setIdAlmacenDestino(rs.getInt(3));
                    oCETransferenciaAlmacen.setNumeroIngreso(rs.getString(4));
                    oCETransferenciaAlmacen.setNumeroSalida(rs.getString(5));
                    oCETransferenciaAlmacen.setNumeroTransferencia(rs.getString(6));
                    oCETransferenciaAlmacen.setFecha(rs.getString(7));
                    oCETransferenciaAlmacen.setEmpleado(rs.getString(8));
                    oCETransferenciaAlmacen.setSucursal(rs.getString(9));
                    oCETransferenciaAlmacen.setUltimoIdEstado(rs.getInt(10));
                    oCETransferenciaAlmacen.setUltimoEstado(rs.getString(11));
                    oCETransferenciaAlmacen.setAlmacenDestino(rs.getString(12));
                    oCETransferenciaAlmacen.setAlmacenOrigen(rs.getString(13));
                    oCETransferenciaAlmacen.setObservacion(rs.getString(14));
                }
                if (oCETransferenciaAlmacen != null)
                {

                    oCETransferenciaAlmacen.setLstCETransferenciaAlmacenDetalle(ListarTransferenciaAlmacenDetalle(oCETransferenciaAlmacen.getIdTransferenciaAlmacen()));
                }
                return oCETransferenciaAlmacen;
            }
            return oCETransferenciaAlmacen;
        }
        catch (SQLException ex)
        {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, "Error al cargar los registros ");
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
                Logger.getLogger(CDTransferenciaAlmacen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
   public static List<CETransferenciaAlmacen> ListarTransferenciaAlmacenPorCodigo(int pIdOrigen,int pIdDestino,String FechaIni,String FechaFin,int pOpcion)
    {
        Connection cnn = ConexionBD.obtenerConexion();
        try
        {
            List<CETransferenciaAlmacen> Lista=new ArrayList<CETransferenciaAlmacen>();
            CETransferenciaAlmacen oCETransferenciaAlmacen = null;
            if (cnn != null)
            {
                CallableStatement cs = null;
                cs = cnn.prepareCall("{CALL  ALMSPRCNSTransferenciaAlmacenPorCodigo(?,?,?,?,?)}");
                cs.setInt(1, pIdOrigen);
                cs.setInt(2, pIdDestino);
                cs.setString(3, FechaIni);
                cs.setString(4, FechaFin);
                cs.setInt(5, pOpcion);
                ResultSet rs = cs.executeQuery();
                while (rs.next())
                {
                    oCETransferenciaAlmacen = new CETransferenciaAlmacen();
                    oCETransferenciaAlmacen.setIdTransferenciaAlmacen(rs.getLong(1));
                    oCETransferenciaAlmacen.setIdAlmacenOrigen(rs.getInt(2));
                    oCETransferenciaAlmacen.setIdAlmacenDestino(rs.getInt(3));
                    oCETransferenciaAlmacen.setNumeroIngreso(rs.getString(4));
                    oCETransferenciaAlmacen.setNumeroSalida(rs.getString(5));
                    oCETransferenciaAlmacen.setNumeroTransferencia(rs.getString(6));
                    oCETransferenciaAlmacen.setFecha(rs.getString(7));
                    oCETransferenciaAlmacen.setEmpleado(rs.getString(8));
                    oCETransferenciaAlmacen.setSucursal(rs.getString(9));
                    oCETransferenciaAlmacen.setUltimoIdEstado(rs.getInt(10));
                    oCETransferenciaAlmacen.setUltimoEstado(rs.getString(11));
                    oCETransferenciaAlmacen.setAlmacenDestino(rs.getString(12));
                    oCETransferenciaAlmacen.setAlmacenOrigen(rs.getString(13));                    
                    Lista.add(oCETransferenciaAlmacen);
                }
                
                return Lista;
            }
            return Lista;
        }
        catch (SQLException ex)
        {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, "Error al cargar los registros ");
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
                Logger.getLogger(CDTransferenciaAlmacen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

        public static List<CETransferenciaAlmacenDetalle> ListarTransferenciaAlmacenDetalle(long pIdTransferenciaAlmacen)
    {
        Connection cnn = ConexionBD.obtenerConexion();
        try
        {
            List<CETransferenciaAlmacenDetalle> lista = new ArrayList<CETransferenciaAlmacenDetalle>();

            CETransferenciaAlmacenDetalle oCETransferenciaAlmacenDetalle = null;
            if (cnn != null)
            {
                CallableStatement cs = null;
                cs = cnn.prepareCall("{CALL  ALMSPRCNSTansferenciaAlmacenDetalle(?)}");
                cs.setLong(1, pIdTransferenciaAlmacen);
                ResultSet rs = cs.executeQuery();
                while (rs.next())
                {
                    oCETransferenciaAlmacenDetalle = new CETransferenciaAlmacenDetalle();
                    oCETransferenciaAlmacenDetalle.setIdTransferenciaAlmacenDetalle(rs.getLong(1));
                    oCETransferenciaAlmacenDetalle.setIdProducto(rs.getInt(2));
                    oCETransferenciaAlmacenDetalle.setIdAlmacen(rs.getInt(3));
                    oCETransferenciaAlmacenDetalle.setIdUnidadMedida(rs.getInt(4));
                    oCETransferenciaAlmacenDetalle.setCantidad(rs.getFloat(5));
                    oCETransferenciaAlmacenDetalle.setCosto(rs.getFloat(6));
                    oCETransferenciaAlmacenDetalle.setImporte(rs.getFloat(7));
                    oCETransferenciaAlmacenDetalle.setProducto(rs.getString(8));
                    oCETransferenciaAlmacenDetalle.setUnidadMedida(rs.getString(9));
                    oCETransferenciaAlmacenDetalle.setAlmacen(rs.getString(10));
                    lista.add(oCETransferenciaAlmacenDetalle);
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
                Logger.getLogger(CDTransferenciaAlmacen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
      
      public static int AnularTransferenciaAlmacen(CETransferenciaAlmacen oCETransferenciaAlmacen)
    {
        Connection conexion;
        conexion = ConexionBD.obtenerConexion();//5 elimina
        try
        {
            CallableStatement sentenciaOrdenCompra;

            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            String sql = "{call ALMSPRAnularTransferenciaAlmacen(?,?,?,?,?)}";
            sentenciaOrdenCompra = conexion.prepareCall(sql);
//            sentenciaOrdenCompra.setLong(1, oCETransferenciaAlmacen.getIdOrdenCompra());
//            sentenciaOrdenCompra.setInt(2, 4);
//            sentenciaOrdenCompra.setInt(3,FrmSistemaMenu.oCEUsuario.getIdUsuario());
//            sentenciaOrdenCompra.setInt(4, oCETransferenciaAlmacen.getIdEmpleado());
//            sentenciaOrdenCompra.setString(5, oCETransferenciaAlmacen.getObservacion());
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
}
