 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.grlGeneral.datos;


import controller.acceso.ConexionBD;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.grlGeneral.entidad.CEComponenteAccion;
import modelo.grlGeneral.entidad.CEVinculacionRolComponenteAccionMatriz;
import util.clases.grlGeneral.CLObjectABM;

public class CDVinculacionRolComponenteAccion
{


    public  List<CEVinculacionRolComponenteAccionMatriz> ListarVinculacionComponenteAccion(int pIdUnidad)
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try {
               List<CEVinculacionRolComponenteAccionMatriz> LstCEVinculacionComponenteAccionMatriz = new ArrayList<CEVinculacionRolComponenteAccionMatriz>();
               String sql="{CALL GRLCNSVinculacionRolComponentePorAccion("+pIdUnidad+")}";
               CallableStatement sentencia=conexion.prepareCall(sql);
               ResultSet rs=sentencia.executeQuery();
               if (rs!=null)
                {
                while (rs.next())
                {
                    CEVinculacionRolComponenteAccionMatriz oCEVinculacionComponenteAccionMatriz=new CEVinculacionRolComponenteAccionMatriz();
                    oCEVinculacionComponenteAccionMatriz.setIdRolCompoenteAccion(rs.getInt(1));
                    oCEVinculacionComponenteAccionMatriz.setIdRol(rs.getInt(2));
                    oCEVinculacionComponenteAccionMatriz.setRol(rs.getString(3));
                    oCEVinculacionComponenteAccionMatriz.setIdComponenteAccion(rs.getInt(4));
                    oCEVinculacionComponenteAccionMatriz.setAccion(rs.getString(5));
                    oCEVinculacionComponenteAccionMatriz.setComponente(rs.getString(6));
                    LstCEVinculacionComponenteAccionMatriz.add(oCEVinculacionComponenteAccionMatriz);
                }
                return LstCEVinculacionComponenteAccionMatriz;
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
     public boolean ABMVinculacionComponenteAccion(ArrayList<CLObjectABM> oLstObjetos)
     {
          Connection conexion = ConexionBD.obtenerConexion();
          try
          {
          conexion.setAutoCommit(false);
          if(oLstObjetos!=null)
          {
               int size=oLstObjetos.size();
               for(int i=0;i<size;i++)
               {
               int pIntABM=((CLObjectABM)oLstObjetos.get(i)).getAbm();
               CEVinculacionRolComponenteAccionMatriz oCEVinculacionComponenteAccionMatriz=(CEVinculacionRolComponenteAccionMatriz)oLstObjetos.get(i).getObjeto();
               String sql="{CALL GRLSPRABMVinculacionRolComponenteAccion(?,?,?,?)}";
               CallableStatement sentencia=conexion.prepareCall(sql);
               sentencia.setInt(1,pIntABM);
               sentencia.setInt(2,oCEVinculacionComponenteAccionMatriz.getIdRolCompoenteAccion());
               sentencia.setInt(3,oCEVinculacionComponenteAccionMatriz.getIdRol());
               sentencia.setInt(4,oCEVinculacionComponenteAccionMatriz.getIdComponenteAccion());
               sentencia.executeUpdate();
               }
           }
           conexion.commit();
           conexion.close();
           return true;
           }
        catch (Exception e)
            {
                try
                {
                conexion.rollback();
                }
                catch (SQLException ex)
                {
                Logger.getLogger(CDVinculacionRolComponenteAccion.class.getName()).log(Level.SEVERE, null, ex);
                }
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
     public  List<CEComponenteAccion> buscarComponenteAccion(String datoBuscar)
    {
         Connection conexion = ConexionBD.obtenerConexion();
         if(conexion!=null)
         {
            try
              {
               List<CEComponenteAccion> VctComponenteMatriz = new ArrayList<CEComponenteAccion>();
               String sql="{CALL GRLSPRCNSBuscarComponente('"+datoBuscar+"%')}";
               CallableStatement sentencia=conexion.prepareCall(sql);
               ResultSet rs=sentencia.executeQuery();
               if (rs!=null)
                {
                while (rs.next())
                {
                    CEComponenteAccion oComponenteAccion=new CEComponenteAccion();
                    oComponenteAccion.setIdComponenteAccion(rs.getInt(1));
                    oComponenteAccion.setDescripcion(rs.getString(2));
                    oComponenteAccion.setNombre(rs.getString(3));
                    VctComponenteMatriz.add(oComponenteAccion);
                }
                return VctComponenteMatriz;
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

     public  List<CEVinculacionRolComponenteAccionMatriz> ListarVinculacionRolComponenteAccion()
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try {
               List<CEVinculacionRolComponenteAccionMatriz> LstCEVinculacionComponenteAccionMatrizs = new ArrayList<CEVinculacionRolComponenteAccionMatriz>();
               String sql="{CALL GRLCNSRolComponenteAccion()}";
               CallableStatement sentencia=conexion.prepareCall(sql);
               ResultSet rs=sentencia.executeQuery();
               if (rs!=null)
                {
                while (rs.next())
                {
                    CEVinculacionRolComponenteAccionMatriz oCEVinculacionComponenteAccionMatriz=new CEVinculacionRolComponenteAccionMatriz();
                    oCEVinculacionComponenteAccionMatriz.setIdComponenteAccion(rs.getInt(1));
                    oCEVinculacionComponenteAccionMatriz.setComponente(rs.getString(2));
                    oCEVinculacionComponenteAccionMatriz.setAccion(rs.getString(3));
                    LstCEVinculacionComponenteAccionMatrizs.add(oCEVinculacionComponenteAccionMatriz);
                }
                return LstCEVinculacionComponenteAccionMatrizs;
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

     public  List<CEVinculacionRolComponenteAccionMatriz> PermisosPorAccion(String Componente,int vIdRol)
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try {
               List<CEVinculacionRolComponenteAccionMatriz> LstCEVinculacionComponenteAccionMatrizs = new ArrayList<CEVinculacionRolComponenteAccionMatriz>();
               String sql="{CALL GRLCNSPermisosPorComponente('"+Componente+"',"+vIdRol+")}";
               CallableStatement sentencia=conexion.prepareCall(sql);
               ResultSet rs=sentencia.executeQuery();
               if (rs!=null)
                {
                while (rs.next())
                {
                    CEVinculacionRolComponenteAccionMatriz oCEVinculacionComponenteAccionMatriz=new CEVinculacionRolComponenteAccionMatriz();
                    oCEVinculacionComponenteAccionMatriz.setIdComponenteAccion(rs.getInt(1));
                    oCEVinculacionComponenteAccionMatriz.setAccion(rs.getString(2));
                    LstCEVinculacionComponenteAccionMatrizs.add(oCEVinculacionComponenteAccionMatriz);
                }
                return LstCEVinculacionComponenteAccionMatrizs;
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
}

