package controller.acceso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import javax.swing.JOptionPane;


public class ConexionBD
{
    public static Connection obtenerConexion()
    {
         Connection cnn= null;
         try
         {
            Properties props = new Properties();
            props.load(ConexionBD.class.getResourceAsStream("ConexionLocalHost.properties"));
            String drivers = props.getProperty("jdbc.drivers");
            if (drivers != null)
                
            Class.forName(drivers);
            String url = props.getProperty("jdbc.url")+props.getProperty("jdbc.server")+":"+props.getProperty("jdbc.port")+"/"+props.getProperty("jdbc.database");
            String username = props.getProperty("jdbc.username");
            String password = props.getProperty("jdbc.password");
            cnn = DriverManager.getConnection( url, username, password );
         }
         catch(Exception ex)
         {
             JOptionPane.showMessageDialog(null, "Error en Conexion");
             cnn= null;
         }

         return cnn;
   }
}
