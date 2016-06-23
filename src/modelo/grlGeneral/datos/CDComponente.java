package modelo.grlGeneral.datos;

import controller.acceso.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.grlGeneral.entidad.CEComponente;

public class CDComponente
{
public static boolean ABM(CEComponente oComponente,int codigo_ABM)
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try {
            /** Baja y Modificacion (Eliminar y Modificar)**/
               String sql="{call CMRSPRABMComponente("+
                            codigo_ABM+","+
                            oComponente.getIdComponente()+"," +
                            "'"+oComponente.getNombre()+"'," +
                             "'"+oComponente.getDescripcion()+"'" +
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
public static CEComponente Alta(CEComponente oCEComponente,int codigo_ABM)
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try {

               String sql="{call CMRSPRINSComponente(?,?)}";
               CallableStatement sentencia=conexion.prepareCall(sql);
               sentencia.setString(1,oCEComponente.getNombre());
               sentencia.setString(2,oCEComponente.getDescripcion());
               ResultSet rs=sentencia.executeQuery();
               if(rs.next())
                   {
                   CEComponente oCEComponenteT=new CEComponente();
                   oCEComponenteT.setIdComponente(rs.getInt(1));
                   oCEComponenteT.setNombre(rs.getString(2));
                   oCEComponenteT.setDescripcion(rs.getString(3));
                   return oCEComponenteT;
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
public static List<CEComponente> Listado()
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try {
               List<CEComponente> LstCEComponente = new ArrayList<CEComponente>();
               String sql="{CALL GRLSPRCNSComponente()}";
               CallableStatement sentencia=conexion.prepareCall(sql);
               ResultSet rs=sentencia.executeQuery();
               if (rs!=null)
                {
                while (rs.next())
                {
                    CEComponente oCEComponente=new CEComponente();
                    oCEComponente.setIdComponente(rs.getInt(1));
                    oCEComponente.setNombre(rs.getString(2));
                    oCEComponente.setDescripcion(rs.getString(3));
                    LstCEComponente.add(oCEComponente);
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
 public  List<CEComponente> buscarComponente(String datoBuscar)
    {
         Connection conexion = ConexionBD.obtenerConexion();
         if(conexion!=null)
         {
            try
              {
               List<CEComponente> VctComponenteMatriz = new ArrayList<CEComponente>();
               String sql="{CALL GRLSPRCNSBuscarComponente('"+datoBuscar+"%')}";
               CallableStatement sentencia=conexion.prepareCall(sql);
               ResultSet rs=sentencia.executeQuery();
               if (rs!=null)
                {
                while (rs.next())
                {
                    CEComponente oComponenteEnC=new CEComponente();
                    oComponenteEnC.setIdComponente(rs.getInt(1));
                    oComponenteEnC.setNombre(rs.getString(2));
                    oComponenteEnC.setDescripcion(rs.getString(3));
                    VctComponenteMatriz.add(oComponenteEnC);
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




}
