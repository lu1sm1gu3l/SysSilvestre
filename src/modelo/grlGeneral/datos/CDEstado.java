/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.grlGeneral.datos;

import controller.acceso.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.grlGeneral.entidad.CEEstado;

/**
 *
 * @author pc25
 */
public class CDEstado {

    public static ArrayList<CEEstado> ListadoEstado(int pOpcion)
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try {
               ArrayList<CEEstado> LstCEEstado = new ArrayList<CEEstado>();
               String sql="{CALL GRLSPRCNSEstado("+pOpcion+")}";
               CallableStatement sentencia=conexion.prepareCall(sql);
               ResultSet rs=sentencia.executeQuery();
               if (rs!=null)
                {
                while (rs.next())
                {
                    CEEstado oCEEstado=new CEEstado();
                    oCEEstado.setmIntIdEstado(rs.getInt(1));
                    oCEEstado.setmStrDescripcion(rs.getString(2));
                    LstCEEstado.add(oCEEstado);
                }
                return LstCEEstado;
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

public static CEEstado consultarUltimoEstado(long pId, int popcion)
    {
        Connection cnn = ConexionBD.obtenerConexion();
        try
        {

            CEEstado oCEEstado=new CEEstado();
            if (cnn != null)
            {
                CallableStatement cs = null;
                cs = cnn.prepareCall("{CALL  VTASPRCNSUltimoEstdoPorDoc(?,?)}");
                cs.setLong(1, pId);
                cs.setInt(2, popcion);
                ResultSet rs = cs.executeQuery();
                while (rs.next())
                {

                   oCEEstado.setmIntIdEstado(rs.getInt(1));
                   oCEEstado.setmStrDescripcion(rs.getString(2));

                }

                return oCEEstado;
            }
            return oCEEstado;
        }
        catch (SQLException ex)
        {
            System.out.print(ex);
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
                Logger.getLogger(CDEstado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
