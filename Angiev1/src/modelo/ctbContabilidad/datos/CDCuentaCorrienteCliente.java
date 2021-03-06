package modelo.ctbContabilidad.datos;


import controller.acceso.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.ctbContabilidad.entidad.CECuentaCorrienteCliente;
import modelo.ctbContabilidad.entidad.CEMovimientoCuentaCliente;
import view.FrmSistemaMenu;


public class CDCuentaCorrienteCliente
{
    public static CECuentaCorrienteCliente consultarCuentaCorrientePorCliente(int IdCliente,int IdMoneda)
    {
      Connection cnn= ConexionBD.obtenerConexion();
        try
        {
            CECuentaCorrienteCliente oCECuentaCorriente=null;
            if(cnn!=null)
            {
                     CallableStatement cs= null;
                     cs = cnn.prepareCall("CALL VTASPRCNSCuentaCorrientePorCliente(?,?)");
                     cs.setInt(1,IdCliente);
                     cs.setInt(2,IdMoneda);
                     ResultSet rs=cs.executeQuery();
                      if(rs.next())
                      {
                            oCECuentaCorriente= new CECuentaCorrienteCliente();
                            oCECuentaCorriente.setIdCuentaCorriente(rs.getInt(1));
                            oCECuentaCorriente.setIdMoneda(rs.getInt(2));
                            oCECuentaCorriente.setIdCliente(rs.getInt(3));
                            oCECuentaCorriente.setSaldo(rs.getFloat(4));
                            oCECuentaCorriente.setCliente(rs.getString(5));
                            oCECuentaCorriente.setMoneda(rs.getString(6));

                      }
                    cnn.close();
                 }

             return oCECuentaCorriente;
        }
        catch(SQLException ex)
        {
            System.out.println(ex);            
            return null;
        }
    }
 public static List<CECuentaCorrienteCliente> listarCuentaCorriente()
    {
      Connection cnn= ConexionBD.obtenerConexion();
        try
        {
            List<CECuentaCorrienteCliente> oLstCuentaCorriente=new ArrayList<CECuentaCorrienteCliente>();
            if(cnn!=null)
            {
                     CallableStatement cs= null;
                     cs = cnn.prepareCall("CALL CTBSPRCNSListarCuentasCorrienteConEquivalencias2()");
                     ResultSet rs=cs.executeQuery();
                      while(rs.next())
                      {
                            CECuentaCorrienteCliente oCECuentaCorriente= new CECuentaCorrienteCliente();
                            oCECuentaCorriente.setIdCuentaCorriente(rs.getInt(1));
                            oCECuentaCorriente.setCliente(rs.getString(2));
                            oCECuentaCorriente.setMoneda(rs.getString(3));
                            oCECuentaCorriente.setSaldo(rs.getFloat(4));
                            oCECuentaCorriente.setTipoCambio(rs.getFloat(5));
                            oCECuentaCorriente.setSaldoEquivalenteSoles(rs.getFloat(6));
                            oCECuentaCorriente.setIdMoneda(rs.getInt(7));
                            oCECuentaCorriente.setIdCliente(rs.getInt(8));
                            oLstCuentaCorriente.add(oCECuentaCorriente);
                      }
                    cnn.close();
                 }

             return oLstCuentaCorriente;
        }
        catch(SQLException ex)
        {
            System.out.println(ex);
            return null;
        }
    }
 public static List<CECuentaCorrienteCliente> listarCuentaCorrienteConFiltro(String parametro)
    {
      Connection cnn= ConexionBD.obtenerConexion();
        try
        {
            List<CECuentaCorrienteCliente> oLstCuentaCorriente=new ArrayList<CECuentaCorrienteCliente>();
            if(cnn!=null)
            {
                     CallableStatement cs= null;
                     cs = cnn.prepareCall("CALL CTBSPRCNSListarCuentasCorrienteConEquivalenciasFiltro(?)");
                     cs.setString(1,parametro);
                     ResultSet rs=cs.executeQuery();
                      while(rs.next())
                      {
                            CECuentaCorrienteCliente oCECuentaCorriente= new CECuentaCorrienteCliente();
                            oCECuentaCorriente.setIdCuentaCorriente(rs.getInt(1));
                            oCECuentaCorriente.setCliente(rs.getString(2));
                            oCECuentaCorriente.setMoneda(rs.getString(3));
                            oCECuentaCorriente.setSaldo(rs.getFloat(4));
                            oCECuentaCorriente.setTipoCambio(rs.getFloat(5));
                            oCECuentaCorriente.setSaldoEquivalenteSoles(rs.getFloat(6));
                            oCECuentaCorriente.setIdMoneda(rs.getInt(7));
                            oCECuentaCorriente.setIdCliente(rs.getInt(8));
                            oLstCuentaCorriente.add(oCECuentaCorriente);
                      }
                    cnn.close();
                 }

             return oLstCuentaCorriente;
        }
        catch(SQLException ex)
        {
            System.out.println(ex);
            return null;
        }
    }
     public static CECuentaCorrienteCliente consultarCuentaCorrientePorClienteConDetalle(int IdCliente,int IdMoneda)
    {
      Connection cnn= ConexionBD.obtenerConexion();
        try
        {
            CECuentaCorrienteCliente oCECuentaCorriente=null;
            if(cnn!=null)
            {
                     CallableStatement cs= null;
                     cs = cnn.prepareCall("CALL VTASPRCNSCuentaCorrientePorCliente(?,?)");
                     cs.setInt(1,IdCliente);
                     cs.setInt(2,IdMoneda);
                     ResultSet rs=cs.executeQuery();
                     if(rs.next())
                      {
                            oCECuentaCorriente= new CECuentaCorrienteCliente();
                            oCECuentaCorriente.setIdCuentaCorriente(rs.getInt(1));
                            oCECuentaCorriente.setIdMoneda(rs.getInt(2));
                            oCECuentaCorriente.setIdCliente(rs.getInt(3));
                            oCECuentaCorriente.setSaldo(rs.getInt(4));
                            oCECuentaCorriente.setCliente(rs.getString(5));
                            oCECuentaCorriente.setMoneda(rs.getString(6));
                            oCECuentaCorriente.setDni(rs.getString(7));
                      }
                      CallableStatement cs1= null;
                      cs1 = cnn.prepareCall("CALL CTBSPRCNSMovimientoCuenta(?,?)");
                      cs1.setInt(1,1);
                      cs1.setInt(2,oCECuentaCorriente.getIdCuentaCorriente());
                      ResultSet rs_=cs1.executeQuery();
                      List<CEMovimientoCuentaCliente> oLstMovimientoCuenta=new ArrayList<CEMovimientoCuentaCliente>();
                      while(rs_.next())
                      {
                       CEMovimientoCuentaCliente oCEMovimientoCuenta=new CEMovimientoCuentaCliente();
                       oCEMovimientoCuenta.setFecha(rs_.getString(1));
                       oCEMovimientoCuenta.setTipoTransaccion(rs_.getString(2));
                       oCEMovimientoCuenta.setTipoMovimiento(rs_.getString(3));
                       oCEMovimientoCuenta.setNumComprobanteVenta(rs_.getString(4));
                       oCEMovimientoCuenta.setMonto(rs_.getFloat(5));
                       oLstMovimientoCuenta.add(oCEMovimientoCuenta);
                      }
                      oCECuentaCorriente.setoLstMovimientoCuenta(oLstMovimientoCuenta);
                    cnn.close();
                 }

             return oCECuentaCorriente;
        }
        catch(SQLException ex)
        {
            System.out.println(ex);
            return null;
        }
    }
      public static boolean registrarDepositoEnCuentaCorriente(CEMovimientoCuentaCliente oCEMovimientoCuenta)
      {
      Connection cnn= ConexionBD.obtenerConexion();
        try
        {
            if(cnn!=null)
            {
                     cnn.setAutoCommit(false);
                     CallableStatement cs= null;
                     cs = cnn.prepareCall("CALL CTBSPRINSDepositoCuentaCorriente(?,?,?,?,?,?,?,?)");
                     cs.setInt(1,oCEMovimientoCuenta.getIdCuentaCorriente());
                     cs.setFloat(2,oCEMovimientoCuenta.getMonto());
                     cs.setString(3,FrmSistemaMenu.oCEUsuario.getIp());
                     cs.setInt(4,FrmSistemaMenu.oCEUsuario.getIdEmpleado());
                     cs.setInt(5,FrmSistemaMenu.oCEUsuario.getIdUsuario());
                     cs.setInt(6,FrmSistemaMenu.oCEUsuario.getIdSucursal());
                     cs.setInt(7,oCEMovimientoCuenta.getIdCliente());
                     cs.setInt(8,oCEMovimientoCuenta.getIdMoneda());
                     cs.execute();
                     cnn.commit();
                     return true;
            }
            return false;
        }
        catch(SQLException ex)
        {
            try {
                cnn.rollback();
                System.out.println(ex);
                return false;
            } catch (SQLException ex1) {
                Logger.getLogger(CDCuentaCorrienteCliente.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return false;
        }
      finally
        {
            try {
                cnn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CDCuentaCorrienteCliente.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    public static boolean registrarNuevaCuentaCorriente(CECuentaCorrienteCliente oCECuentaCorriente)
      {
      Connection cnn= ConexionBD.obtenerConexion();
        try
        {
            if(cnn!=null)
            {
                     cnn.setAutoCommit(false);
                     CallableStatement cs= null;
                     cs = cnn.prepareCall("CALL CTBSPRNuevaCuentaCorriente(?,?)");
                     cs.setInt(1,oCECuentaCorriente.getIdCliente());
                     cs.setInt(2,oCECuentaCorriente.getIdMoneda());
                     cs.execute();
                     cnn.commit();
                     return true;
            }
            return false;
        }
        catch(SQLException ex)
        {
            try {
                cnn.rollback();
                System.out.println(ex);
                return false;
            } catch (SQLException ex1) {
                Logger.getLogger(CDCuentaCorrienteCliente.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return false;
        }
      finally
        {
            try {
                cnn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CDCuentaCorrienteCliente.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
