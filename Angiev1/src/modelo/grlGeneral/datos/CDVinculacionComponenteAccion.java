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
import modelo.grlGeneral.entidad.CEVinculacionComponenteAccionMatriz;
import util.clases.grlGeneral.CLObjectABM;

/**
 *
 * @author Administrador
 */
public class CDVinculacionComponenteAccion {


    public  List<CEVinculacionComponenteAccionMatriz> ListarVinculacionComponenteAccion(int pIdUnidad)
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try {
               List<CEVinculacionComponenteAccionMatriz> LstCEVinculacionComponenteAccionMatriz = new ArrayList<CEVinculacionComponenteAccionMatriz>();
               String sql="{CALL GRLSPRCNSListaAccionComponente("+pIdUnidad+")}";
               CallableStatement sentencia=conexion.prepareCall(sql);
               ResultSet rs=sentencia.executeQuery();
               if (rs!=null)
                {
                while (rs.next())
                {
                    CEVinculacionComponenteAccionMatriz oCEVinculacionComponenteAccionMatriz=new CEVinculacionComponenteAccionMatriz();
                    oCEVinculacionComponenteAccionMatriz.setIdComponenteAccion(rs.getInt(1));
                    oCEVinculacionComponenteAccionMatriz.setIdComponente(rs.getInt(2));
                    oCEVinculacionComponenteAccionMatriz.setIdAccion(rs.getInt(3));
                    oCEVinculacionComponenteAccionMatriz.setAccion(rs.getString(4));
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
               CEVinculacionComponenteAccionMatriz oCEVinculacionComponenteAccionMatriz=(CEVinculacionComponenteAccionMatriz)oLstObjetos.get(i).getObjeto();
               String sql="{CALL GRLSPRABMVinculacionComponenteAccion(?,?,?,?)}";
               CallableStatement sentencia=conexion.prepareCall(sql);
               sentencia.setInt(1,pIntABM);
               sentencia.setLong(2,oCEVinculacionComponenteAccionMatriz.getIdComponenteAccion());
               sentencia.setInt(3,oCEVinculacionComponenteAccionMatriz.getIdComponente());
               sentencia.setInt(4,oCEVinculacionComponenteAccionMatriz.getIdAccion());
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
}

