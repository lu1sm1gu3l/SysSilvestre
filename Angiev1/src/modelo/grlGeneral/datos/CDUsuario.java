package modelo.grlGeneral.datos;

import controller.acceso.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.cmrComercial.entidad.CEEmpleado;
import modelo.grlGeneral.entidad.CEComponenteAccion;
import modelo.grlGeneral.entidad.CEUsuario;
import modelo.vtaVenta.entidad.CEPuntoVenta;

public class CDUsuario
{
    public static CEUsuario validarUsuario(CEUsuario oCEUsuarioT)
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try {
               CEUsuario oCEUsuario=new CEUsuario();
                ArrayList<CEComponenteAccion> oLstComponenteAccion = new ArrayList<CEComponenteAccion>();
               String sql="{CALL GRLSPRCNSValidacionUsuario(?,?,?,?)}";
               CallableStatement sentencia=conexion.prepareCall(sql);
               sentencia.setString(2,oCEUsuarioT.getUsuario());
               sentencia.setString(3,oCEUsuarioT.getPassword());
               sentencia.setInt(1,1);
               sentencia.setLong(4,0);
               ResultSet rs=sentencia.executeQuery();               
               if (rs!=null)
                {
                 if(rs.next())
                 {
                  
                    //oCEUsuario=new CEUsuario();
                    oCEUsuario.setIdUsuario(rs.getInt(1));
                    oCEUsuario.setEstado(true);
                    oCEUsuario.setUsuario(rs.getString(2));
                    oLstComponenteAccion = new ArrayList<CEComponenteAccion>();
                    CEComponenteAccion oCEComponenteAccionT=new CEComponenteAccion();
                    oCEComponenteAccionT.setIdComponente(rs.getInt(3));
                    oCEComponenteAccionT.setComponente(rs.getString(4));
                    oCEUsuario.setIdEmpleado(rs.getInt(5));
                    oCEUsuario.setIngresoSistema(rs.getString(6));
                    oCEUsuario.setNombreCompleto(rs.getString(7));
                    oCEUsuario.setSucursal(rs.getString(8));
                    oCEUsuario.setIdSucursal(rs.getInt(9));
                    oCEUsuario.setIdRol(rs.getInt(10));
                    oCEUsuario.setRol(rs.getString(11));
                    oLstComponenteAccion.add(oCEComponenteAccionT);
                    while (rs.next())
                    {
                        CEComponenteAccion oCEComponenteAccion=new CEComponenteAccion();
                        oCEComponenteAccion.setIdComponente(rs.getInt(3));
                        //oCEComponenteAccion.setIdAccion(rs.getInt(4));
                        oCEComponenteAccion.setComponente(rs.getString(4));
                        oLstComponenteAccion.add(oCEComponenteAccion);
                    }
                  
                    }
                   oCEUsuario.setoListComponenteAccion(oLstComponenteAccion);
                return oCEUsuario;
                }
               return null;
            }
         catch (SQLException e)
            {
             System.out.print(e);
             return null;
            }
        catch (Exception e)
            {
            System.out.print(e);
            return null;
            }
        finally
            {
                try
                {
                    if(conexion!=null)
                    {
                conexion.close();}
                }
                catch (SQLException ex)
                {
                System.out.print("No se puede cerrar la conexion");
                }
            }
    }


    public static CEUsuario VerificarClave(int pIdUsuario,String pPassw)
      {
         Connection conexion = ConexionBD.obtenerConexion();
         CEUsuario oCEUsuario=null;
         try {

               String sql="{CALL GRLSPRCNSUsuarioPorPassw(?,?)}";
               CallableStatement sentencia=conexion.prepareCall(sql);
               sentencia.setInt(1,pIdUsuario);
               sentencia.setString(2, pPassw);
               ResultSet rs=sentencia.executeQuery();
               if (rs!=null)
                {
                 if(rs.next())
                 {

                    oCEUsuario=new CEUsuario();
                    oCEUsuario.setIdUsuario(rs.getInt(1));
                    oCEUsuario.setUsuario(rs.getString(2));
                    oCEUsuario.setPassword(rs.getString(3));
                    oCEUsuario.setIdEmpleado(rs.getInt(4));

                  }

                return oCEUsuario;
                }

               return oCEUsuario;
            }
         catch (SQLException e)
            {
             System.out.print(e);
             return null;
            }
        catch (Exception e)
            {
            System.out.print(e);
            return null;
            }
        finally
            {
                try
                {
                    if(conexion!=null)
                    {
                conexion.close();}
                }
                catch (SQLException ex)
                {
                System.out.print("No se puede cerrar la conexion");
                }
            }
    }
      public static CEPuntoVenta consultarTerminal(CEUsuario oCEUsuario)
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try
         {
                CEPuntoVenta oCEPuntoVenta= null;
               String sql="{CALL GRLSPRCNSTerminalUsuario(?,?)}";
               CallableStatement sentencia=conexion.prepareCall(sql);
               sentencia.setString(1,oCEUsuario.getIp());
               sentencia.setInt(2,oCEUsuario.getIdSucursal());
               ResultSet rs=sentencia.executeQuery();
               if (rs!=null)
                {
                     if(rs.next())
                     {
                         oCEPuntoVenta= new CEPuntoVenta();
                         oCEPuntoVenta.setDescrpcion(rs.getString(1));
                         oCEPuntoVenta.setIdPuntoVenta(rs.getInt(2));
                         oCEPuntoVenta.setIdAlmacen(rs.getInt(3));
                     }
                     return oCEPuntoVenta;
                }
               return null;
            }
         catch (SQLException e)
            {
             System.out.print(e);
             return null;
            }
        catch (Exception e)
            {
            System.out.print(e);
            return null;
            }
        finally
            {
                try
                {
                    if(conexion!=null)
                    {
                conexion.close();}
                }
                catch (SQLException ex)
                {
                System.out.print("No se puede cerrar la conexion");
                }
            }
    }
 public static boolean ABM(CEUsuario oUsuario,int codigo_ABM)
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try {
               String sql="{call GRLSPRABMUsuario("+
                            codigo_ABM+","+
                            oUsuario.getIdEmpleado()+","+
                            oUsuario.getIdUsuario()+",'"+
                            oUsuario.getUsuario()+"','"+
                            oUsuario.getPassword()+"'"+
                            ")}";
               CallableStatement sentencia=conexion.prepareCall(sql);
               sentencia.executeUpdate();
               return true;
            }
         catch (SQLException e)
            {
             System.out.print(e);
             return false;
            }
        catch (Exception e)
            {
            System.out.print(e);
            return false;
            }
         finally
         {
            try
            {
              conexion.close();
            }
            catch (SQLException ex)
            {
                System.out.print("No se puede cerrar la conexion");
            }
         }
    }

 public static List<CEUsuario> Listado()
      {
        Connection conexion = ConexionBD.obtenerConexion();
         try {
               List<CEUsuario> LstCEComponente = new ArrayList<CEUsuario>();
               String sql="{CALL GRLSPRCNSUsuario()}";
               CallableStatement sentencia=conexion.prepareCall(sql);
               ResultSet rs=sentencia.executeQuery();
               if (rs!=null)
                {
                while (rs.next())
                {
                    CEUsuario oCEUsuario=new CEUsuario();
                    oCEUsuario.setIdUsuario(rs.getInt(1));
                    oCEUsuario.setIdEmpleado(rs.getInt(2));
                    oCEUsuario.setUsuario(rs.getString(3));
                    oCEUsuario.setPassword(rs.getString(4));
                    oCEUsuario.setNombreCompleto(rs.getString(5));
                    oCEUsuario.setSucursal(rs.getString(6));
                    LstCEComponente.add(oCEUsuario);
                }
                conexion.close();
                return LstCEComponente;
                }
               return null;
            }
         catch (SQLException e)
            {
             System.out.print(e);
             return null;
            }
        catch (Exception e)
            {
            System.out.print(e);
            return null;


            }
    }

    public List<CEUsuario> buscarUsuario(int cns_id,String datoUsuario)
    {
        Connection conexion = ConexionBD.obtenerConexion();
        try
        {
            List<CEUsuario> oVctUsuario = new ArrayList<CEUsuario>();
            String sql = "{CALL CMRSPRCNSUsuario("+cns_id+",'"+datoUsuario+"%')}";
            CallableStatement sentencia = conexion.prepareCall(sql);
            ResultSet rs = sentencia.executeQuery();
            if(rs!=null)
            {
                while(rs.next())
                {
                    CEUsuario oCEUsuario = new CEUsuario();
                    oCEUsuario.setIdUsuario(rs.getInt(1));
                    oCEUsuario.setUsuario(rs.getString(2));
                    oCEUsuario.setNombreCompleto(rs.getString(3));
                    oVctUsuario.add(oCEUsuario);
                }
                return oVctUsuario;
            }
            return null;
        }catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

   
    public  List<CEEmpleado> buscarSujeto(int cns_id,String datoUsuario)
    {
         Connection conexion = ConexionBD.obtenerConexion();
         if(conexion!=null)
         {
            try
              {
               List<CEEmpleado> VctUsuarioMatriz = new ArrayList<CEEmpleado>();
               String sql="{CALL CMRSPRCNSUsuario ("+cns_id+",'"+datoUsuario+"%')}";
               CallableStatement sentencia=conexion.prepareCall(sql);
               ResultSet rs=sentencia.executeQuery();
               if (rs!=null)
                {
                while (rs.next())
                {
                    CEEmpleado oCEUsuario=new CEEmpleado();
                    oCEUsuario.setIdEmpleado(rs.getInt(1));
                    oCEUsuario.setNombreCompleto(rs.getString(2));
                    oCEUsuario.setSucursal(rs.getString(3));
                    VctUsuarioMatriz.add(oCEUsuario);
                }
                return VctUsuarioMatriz;
                }
               return null;
            }
         catch (SQLException e)
            {
             System.out.print(e);
             return null;
            }
        catch (Exception e)
            {
            System.out.print(e);
            return null;
            }
        finally
            {
                try
                {
                conexion.close();
                }
                catch (SQLException ex)
                {
                System.out.print("No se puede cerrar la conexion");
                }
            }
        }
         return null;
    }


}

