

package modelo.grlGeneral.datos;

import controller.acceso.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.grlGeneral.entidad.CEUsuarioRolMatriz;
import util.clases.grlGeneral.CLObjectABM;

public class CDUsuarioRol
{
    public  List<CEUsuarioRolMatriz> ListarUsuarioRol(int pIdCns,int pIdUsuario)
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try {
               List<CEUsuarioRolMatriz> LstCEUsuarioRol = new ArrayList<CEUsuarioRolMatriz>();
               String sql="{CALL GRLSPRCNSUsuarioRol("+pIdCns+","+pIdUsuario+")}";
               CallableStatement sentencia=conexion.prepareCall(sql);
               ResultSet rs=sentencia.executeQuery();
               if (rs!=null)
                {
                while (rs.next())
                {
                    CEUsuarioRolMatriz oCEUsuarioRol=new CEUsuarioRolMatriz();
                    oCEUsuarioRol.setUsuario(rs.getString(2));
                    oCEUsuarioRol.setRol(rs.getString(3));
                    oCEUsuarioRol.setIdUsuario(rs.getInt(4));
                    oCEUsuarioRol.setIdRol(rs.getInt(5));
                    oCEUsuarioRol.setIdUsuarioRol(rs.getInt(1));
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
     public boolean ABMUsuarioRol(ArrayList<CLObjectABM> oLstObjetos)
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
               CEUsuarioRolMatriz oCEUsuarioRolMatriz=(CEUsuarioRolMatriz)oLstObjetos.get(i).getObjeto();
               String sql="{CALL GRLSPRABMUsuarioRol(?,?,?,?)}";
               CallableStatement sentencia=conexion.prepareCall(sql);
               sentencia.setInt(1,pIntABM);
               sentencia.setLong(2,oCEUsuarioRolMatriz.getIdUsuarioRol());
               sentencia.setInt(3,oCEUsuarioRolMatriz.getIdUsuario());
               sentencia.setInt(4,oCEUsuarioRolMatriz.getIdRol());
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
                Logger.getLogger(CDUsuarioRol.class.getName()).log(Level.SEVERE, null, ex);
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
}
