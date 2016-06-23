package util.clases.grlGeneral;

import controller.acceso.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CLConsultarFechaSistema
{


    public static String consultarFecha()
    {
        Statement st;
        ResultSet rs;
        String Fecha=null;
        Connection cn=ConexionBD.obtenerConexion();
        try
        {
            String sql = "select DATE_FORMAT(curdate(),\"%d/%m/%Y\")";
            st = cn.createStatement();
            rs=st.executeQuery(sql);
            if(rs.next())
            {
                Fecha=rs.getString(1);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(CLConsultarFechaSistema.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Fecha;
    }
    public static String consultarFechaYMD()

    {
        Statement st;
        ResultSet rs;
        String Fecha=null;
        Connection cn=ConexionBD.obtenerConexion();
        try
        {
            String sql = "select DATE_FORMAT(curdate(),\"%Y/%m/%d\")";
            st = cn.createStatement();
            rs=st.executeQuery(sql);
            if(rs.next())
            {
                Fecha=rs.getString(1);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(CLConsultarFechaSistema.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Fecha;
    }

     public static Date consultarFecha_Date()
    {
        Statement st;
        ResultSet rs;
        Date Fecha=null;
        Connection cn=ConexionBD.obtenerConexion();
        try
        {
            String sql = "select DATE_FORMAT(curdate(),\"%Y/%m/%d\")";
            st = cn.createStatement();
            rs=st.executeQuery(sql);
            if(rs.next())
            {
                Fecha=rs.getDate(1);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(CLConsultarFechaSistema.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Fecha;
    }

    public static int verificarDiferenciaFecha(String oDate)
    {
        Connection cnn= ConexionBD.obtenerConexion();
        try
        {
                if(cnn!=null)
                {
                     CallableStatement cs= null;
                     cs = cnn.prepareCall("{CALL  GRLSPRVerificarDiferenciaFecha(?)}");
                     cs.setString(1,oDate);
                     ResultSet rs=cs.executeQuery();
                      while(rs.next())
                      {                       
                       return rs.getInt(1);

                      }

                 }
             return 0;
        }

        catch(SQLException ex)
        {
            System.out.print(ex);
            return 0;
        }
        finally
        {
            try {
                cnn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CLConsultarFechaSistema.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
