package modelo.vtaVenta.datos;


import controller.acceso.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.vtaVenta.entidad.CEInstitucionFinanciera;

public class CDInstitucionFinanciera
{
    public static List<CEInstitucionFinanciera> listarInstitucionFinanciera()
    {
      Connection cnn= ConexionBD.obtenerConexion();
        try
        {

            ArrayList<CEInstitucionFinanciera> oLista=null;
                if(cnn!=null)
                {
                     CallableStatement cs= null;
                   cs = cnn.prepareCall("CALL VTASPRCNSInstitucionFinanciera()");
                     ResultSet rs=cs.executeQuery();

                              if(rs.next())
                              {
                                oLista= new ArrayList<CEInstitucionFinanciera>();
                                CEInstitucionFinanciera oCEInstitucionFinanciera;

                                 do
                                  {
                                    oCEInstitucionFinanciera= new CEInstitucionFinanciera();
                                    oCEInstitucionFinanciera.setIdInstitucionFinanciera(rs.getInt(1));
                                    oCEInstitucionFinanciera.setDescripcion(rs.getString(2));
                                    oLista.add(oCEInstitucionFinanciera);
                                  }while(rs.next());
                               }
                    cnn.close();
                 }

             return oLista;
        }catch(SQLException ex)
        {
            System.out.println(ex);
            return null;
        }
    }
}
