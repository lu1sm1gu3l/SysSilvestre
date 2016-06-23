package modelo.vtaVenta.datos;
import controller.acceso.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.vtaVenta.entidad.CECuentaEmpresa;

public class CDCuentaEmpresa
{
    public static ArrayList<CECuentaEmpresa> listarCuentaEmpresa(int IdInstitucionFinanciera,int IdTipoMoneda)
    {
      Connection cnn= ConexionBD.obtenerConexion();
        try
        {

            ArrayList<CECuentaEmpresa> oLista=null;
                if(cnn!=null)
                {
                     CallableStatement cs= null;
                     cs = cnn.prepareCall("CALL VTASPRCNSCuentaEmpresaPorInstitucionFinanciera(?,?)");
                     
                     cs.setInt(1,IdInstitucionFinanciera);
                     cs.setInt(2,IdTipoMoneda);
                     ResultSet rs=cs.executeQuery();
                      if(rs.next())
                      {
                        oLista= new ArrayList<CECuentaEmpresa>();
                        CECuentaEmpresa oCECuentaEmpresa;

                         do
                          {
                            oCECuentaEmpresa= new CECuentaEmpresa();
                            oCECuentaEmpresa.setIdCuentaEmpresa(rs.getInt(1));
                            oCECuentaEmpresa.setIdMoneda(rs.getInt(3));
                            oCECuentaEmpresa.setIdInstitucionFinanciera(rs.getInt(2));
                            oCECuentaEmpresa.setNumeroCuenta(rs.getString(4));
                            oLista.add(oCECuentaEmpresa);
                          }
                          while(rs.next());
                       }
                           cnn.close();
                 }
             return oLista;
        }
        catch(SQLException ex)
        {
            System.out.println(ex);
            return null;
        }
    }
}
