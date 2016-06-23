package modelo.grlGeneral.datos;

import controller.acceso.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.grlGeneral.entidad.CERol;

public class CDRol
{
public static boolean ABM(CERol oRol,int codigo_ABM)
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try {
            /** Baja y Modificacion (Eliminar y Modificar)**/
               String sql="{call CMRSPRAMBRol("+
                            codigo_ABM+","+
                            oRol.getIdRol()+"," +
                            "'"+oRol.getNombre()+"'," +
                             "'"+oRol.getDescripcion()+"'" +
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
    }
public static CERol Alta(CERol oCERol,int codigo_ABM)
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try {

               String sql="{call CMRSPRINSRol(?,?)}";
               CallableStatement sentencia=conexion.prepareCall(sql);
               sentencia.setString(1,oCERol.getNombre());
               sentencia.setString(2,oCERol.getDescripcion());
               ResultSet rs=sentencia.executeQuery();
               if(rs.next())
                   {
                   CERol oCERolT=new CERol();
                   oCERolT.setIdRol(rs.getInt(1));
                   oCERolT.setNombre(rs.getString(2));
                   oCERolT.setDescripcion(rs.getString(3));
                   return oCERolT;
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
public  List<CERol> buscarRol(String datoBuscar)
    {
         Connection conexion = ConexionBD.obtenerConexion();
         if(conexion!=null)
         {
            try
              {
               List<CERol> VctComponenteMatriz = new ArrayList<CERol>();
               String sql="{CALL GRLSPRCNSBuscarRol('"+datoBuscar+"%')}";
               CallableStatement sentencia=conexion.prepareCall(sql);
               ResultSet rs=sentencia.executeQuery();
               if (rs!=null)
                {
                while (rs.next())
                {
                    CERol oRol=new CERol();
                    oRol.setIdRol(rs.getInt(1));
                    oRol.setNombre(rs.getString(2));
                    oRol.setDescripcion(rs.getString(3));
                    VctComponenteMatriz.add(oRol);
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
public static List<CERol> Listado()
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try {
               List<CERol> LstCERol = new ArrayList<CERol>();
               String sql="{CALL GRLSPRCNSRol()}";
               CallableStatement sentencia=conexion.prepareCall(sql);
               ResultSet rs=sentencia.executeQuery();
               if (rs!=null)
                {
                while (rs.next())
                {
                    CERol oCERol=new CERol();
                    oCERol.setIdRol(rs.getInt(1));
                    oCERol.setNombre(rs.getString(2));
                    oCERol.setDescripcion(rs.getString(3));
                    LstCERol.add(oCERol);
                }
                conexion.close();
                return LstCERol;
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

    public  List<CERol> ListarRoles()
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try {
               List<CERol> LstCEUsuarioRol = new ArrayList<CERol>();
               String sql="CALL GRLSPRCNSRol";
               CallableStatement sentencia=conexion.prepareCall(sql);
               ResultSet rs=sentencia.executeQuery();
               if (rs!=null)
                {
                while (rs.next())
                {
                    CERol oCEUsuarioRol=new CERol();
                    oCEUsuarioRol.setNombre(rs.getString(2));
                    oCEUsuarioRol.setIdRol(rs.getInt(1));
                    LstCEUsuarioRol.add(oCEUsuarioRol);
                }
                return LstCEUsuarioRol;
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
