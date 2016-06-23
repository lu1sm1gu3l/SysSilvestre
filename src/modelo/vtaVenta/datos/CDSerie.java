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
import modelo.vtaVenta.entidad.CEPuntoVenta;
import modelo.vtaVenta.entidad.CESerie;

public class CDSerie
{ 
    public static CESerie consultarSeriePorPuntoVenta(String pIp,int pIdTipoComprobante,int pIdSucursal)
    {
       Connection cnn= ConexionBD.obtenerConexion();
       CESerie oCESerie=null;
        try
        {            
           if(cnn!=null)
           {
               CallableStatement cs= null;
               cs = cnn.prepareCall("CALL VTASPRCNSNumSeriePorPuntoVenta(?,?,?,?,?)");
               cs.setString(1, pIp);
               cs.setInt(2, pIdTipoComprobante);
               cs.registerOutParameter(3,java.sql.Types.VARCHAR);
               cs.registerOutParameter(4,java.sql.Types.VARCHAR);
               cs.setInt(5,pIdSucursal);
               cs.execute();
               oCESerie= new CESerie();
               oCESerie.setNumeroGeneradoString(cs.getString(3));
               oCESerie.setUltimoNumeroGeneradoString(cs.getString(4));
               cnn.close();
           }
               return oCESerie;
        }
        catch(SQLException ex)
        {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "Error al cargar Serie");
            return null;
        }
    }



    public static CESerie consultarImprimirSerie(int pIdPuntoVenta,int pIdTipoComprobante)
    {
        Connection cnn= ConexionBD.obtenerConexion();
        try
        {
            
            CESerie oCESerie=null;
                if(cnn!=null)
                {
                     CallableStatement cs= null;
                     cs = cnn.prepareCall("CALL VTASPRCNSSerieImprimir(?,?)");
                     cs.setInt(1, pIdPuntoVenta);
                     cs.setInt(2, pIdTipoComprobante);
                     ResultSet rs=cs.executeQuery();
                      while(rs.next())
                      {
                       oCESerie=new CESerie();
                       oCESerie.setAncho(rs.getFloat(1));
                       oCESerie.setAlto(rs.getFloat(2));
                       oCESerie.setNombreImpresora(rs.getString(3));

                      }

                 }
             return oCESerie;
        }

        catch(SQLException ex)
        {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, "Error al cargar la Serie ");
            return null;
        }
        finally
        {
            try {
                cnn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CDSerie.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static List<CESerie> consultarSeriePorComp(int pIdTipoComprobante)
    {
        Connection cnn= ConexionBD.obtenerConexion();
        try
        {
            List<CESerie> olista= new ArrayList<CESerie>();
            CESerie oCESerie=null;
                if(cnn!=null)
                {
                     CallableStatement cs= null;
                     cs = cnn.prepareCall("{CALL  VTASPRCNSSeriePorComprobante(?)}");
                     cs.setInt(1, pIdTipoComprobante);
                     ResultSet rs=cs.executeQuery();
                      while(rs.next())
                      {
                       oCESerie=new CESerie();
                       oCESerie.setIdSerie(rs.getInt(1));
                       oCESerie.setNumero(rs.getString(2));
                       oCESerie.setUltimoNumeroGenerado(rs.getLong(3));
                       CEPuntoVenta oCEPuntoVenta= new CEPuntoVenta();
                       oCESerie.setIdPuntoVentaSerie(rs.getInt(5));
                       oCEPuntoVenta.setIdPuntoVenta(rs.getInt(4));                       
                       oCEPuntoVenta.setDescrpcion(rs.getString(6));
                       oCESerie.setoCEPuntoVenta(oCEPuntoVenta);
                       olista.add(oCESerie);

                      }

                 }
             return olista;
        }

        catch(SQLException ex)
        {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, "Error al cargar la Serie ");
            return null;
        }
        finally
        {
            try {
                cnn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CDSerie.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }



    public static int consultaExistePuntoComprobante(int pIdTipoComprobante,int pIdPuntoVenta)
    {
       Connection cnn= ConexionBD.obtenerConexion();
       int numeroRegistros=0;
        try
        {
               if(cnn!=null)
               {
                   CallableStatement cs= null;
                   cs = cnn.prepareCall("CALL VTASPRCNSExistePuntoVentaComprobante(?,?,?)");
                   cs.setInt(1, pIdPuntoVenta);
                   cs.setInt(2,pIdTipoComprobante);
                   cs.registerOutParameter(3,java.sql.Types.INTEGER);
                   cs.execute();
                   numeroRegistros=cs.getInt(3);
                   cnn.close();
               }
           return numeroRegistros;
        }
        catch(SQLException ex)
        {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "Error al cargar Serie");
            return 0;
        }
    }
    public static List<CESerie> consultarSerieComprobantePorIdPuntoVenta(int pIdPuntoVenta)
    {
        Connection cnn= ConexionBD.obtenerConexion();
        try
        {
            List<CESerie> olista= new ArrayList<CESerie>();
            CESerie oCESerie=null;
                if(cnn!=null)
                {
                     CallableStatement cs= null;
                     cs = cnn.prepareCall("{CALL  VTASPRCNSSerieComprobantePorPuntoVenta(?)}");
                     cs.setInt(1, pIdPuntoVenta);
                     ResultSet rs=cs.executeQuery();
                      while(rs.next())
                      {
                       oCESerie=new CESerie();
                       oCESerie.setIdSerie(rs.getInt(1));
                       oCESerie.setNumero(rs.getString(2));
                       oCESerie.setUltimoNumeroGenerado(rs.getLong(3));
                       CEPuntoVenta oCEPuntoVenta= new CEPuntoVenta();
                       oCESerie.setIdPuntoVentaSerie(rs.getInt(4));
                       oCEPuntoVenta.setIdPuntoVenta(rs.getInt(5));
                       oCEPuntoVenta.setDescrpcion(rs.getString(6));
                       oCESerie.setTipoComprobante(rs.getString(7));
                       oCESerie.setIdTipoComprobante(rs.getInt(8));
                       oCESerie.setNombreImpresora(rs.getString(9));
                       oCESerie.setoCEPuntoVenta(oCEPuntoVenta);
                       oCESerie.setAncho(rs.getFloat(10));
                       oCESerie.setAlto(rs.getFloat(11));
                       oCESerie.setCeroCorrelativo(rs.getInt(12));
                       oCESerie.setCeroSerie(rs.getInt(13));
                       olista.add(oCESerie);

                      }

                 }
             return olista;
        }

        catch(SQLException ex)
        {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, "Error al cargar la Serie ");
            return null;
        }
        finally
        {
            try {
                cnn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CDSerie.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static CESerie consultarSeriePorIdPuntoVenta(int idPunto,int pIdTipoComprobante,int pIdSucursal)
    {
       Connection cnn= ConexionBD.obtenerConexion();
       CESerie oCESerie=null;
        try
        {
           if(cnn!=null)
           {
               CallableStatement cs= null;
               cs = cnn.prepareCall("CALL VTASPRCNSGenerarNumComprobantePorPunto(?,?,?,?,?)");
               cs.setInt(1, idPunto);
               cs.setInt(2, pIdTipoComprobante);
               cs.registerOutParameter(3,java.sql.Types.VARCHAR);
               cs.registerOutParameter(4,java.sql.Types.VARCHAR);
               cs.setInt(5,pIdSucursal);
               cs.execute();
               oCESerie= new CESerie();
               oCESerie.setNumeroGeneradoString(cs.getString(3));
               oCESerie.setUltimoNumeroGeneradoString(cs.getString(3));
               cnn.close();
           }
               return oCESerie;
        }
        catch(SQLException ex)
        {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "Error al cargar Serie");
            return null;
        }
    }


     public static boolean InsPuntoVentaComprobante(CESerie oCESerie)
    {
        Connection conexion;
        conexion = ConexionBD.obtenerConexion();//
        try
        {
            CallableStatement sentenciaSerie;
            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            String sql= "{call VTASPRINSPuntoVentaComprobante(?,?,?,?,?,?,?,?,?,?)}";
            sentenciaSerie = conexion.prepareCall(sql);
            sentenciaSerie.setInt(1, oCESerie.getIdTipoComprobante());
            sentenciaSerie.setString(2, oCESerie.getNumero());
            sentenciaSerie.setLong(3, oCESerie.getUltimoNumeroGenerado());
            sentenciaSerie.setString(4, oCESerie.getNombreImpresora());
            sentenciaSerie.setInt(5, oCESerie.getCeroSerie());
            sentenciaSerie.setInt(6, oCESerie.getCeroCorrelativo());
            sentenciaSerie.setFloat(7, oCESerie.getAncho());
            sentenciaSerie.setFloat(8, oCESerie.getAlto());
            sentenciaSerie.setInt(9, oCESerie.getoCEPuntoVenta().getIdPuntoVenta());
            sentenciaSerie.setInt(10, oCESerie.getoCEPuntoVenta().getIdSucursal());
            sentenciaSerie.executeUpdate();

            
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


     public static boolean UPDPuntoVentaComprobante(CESerie oCESerie)
    {
        Connection conexion;
        conexion = ConexionBD.obtenerConexion();//
        try
        {
            CallableStatement sentenciaSerie;
            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            String sql= "{call VTASPRUPDPuntoVentaComprobante(?,?,?,?,?,?,?,?)}";
            sentenciaSerie = conexion.prepareCall(sql);
            sentenciaSerie.setInt(1, oCESerie.getIdSerie());
            sentenciaSerie.setString(2, oCESerie.getNumero());
            sentenciaSerie.setLong(3, oCESerie.getUltimoNumeroGenerado());
            sentenciaSerie.setString(4, oCESerie.getNombreImpresora());
            sentenciaSerie.setInt(5, oCESerie.getCeroSerie());
            sentenciaSerie.setInt(6, oCESerie.getCeroCorrelativo());
            sentenciaSerie.setFloat(7, oCESerie.getAncho());
            sentenciaSerie.setFloat(8, oCESerie.getAlto());
            sentenciaSerie.executeUpdate();


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

     public static boolean DELPuntoVentaComprobante(CESerie oCESerie)
    {
        Connection conexion;
        conexion = ConexionBD.obtenerConexion();//
        try
        {
            CallableStatement sentenciaSerie;
            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            String sql= "{call VTASPRDELPuntoVentaComprobante(?,?)}";
            sentenciaSerie = conexion.prepareCall(sql);
            sentenciaSerie.setInt(1, oCESerie.getIdSerie());
            sentenciaSerie.setInt(2, oCESerie.getIdPuntoVentaSerie());
            sentenciaSerie.executeUpdate();


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
}

