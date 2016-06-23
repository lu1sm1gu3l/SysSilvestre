package modelo.vtaVenta.datos;


import controller.acceso.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.vtaVenta.entidad.CETipoPago;


/**

 */
public class CDTipoPago
{
   
    

    public static ArrayList<CETipoPago> listarTipoIngresoFinaciero(int opcion)
    {
      Connection cnn= ConexionBD.obtenerConexion();
        try
        {

            ArrayList<CETipoPago> oLista=null;
                if(cnn!=null)
                {
                     CallableStatement cs= null;
                   cs = cnn.prepareCall("CALL VTASPRCNSTipoIngresoFinanciero("+opcion+")");
                     ResultSet rs=cs.executeQuery();

                              if(rs.next())
                              {
                                oLista= new ArrayList<CETipoPago>();
                                CETipoPago oCETipoIngresoFinanciero;

                                 do
                                  {
                                    oCETipoIngresoFinanciero= new CETipoPago();
                                    oCETipoIngresoFinanciero.setIdTipoPago(rs.getInt(1));
                                    oCETipoIngresoFinanciero.setDescripcion(rs.getString(2));

                                    oLista.add(oCETipoIngresoFinanciero);
                                  }while(rs.next());
                               }
                    cnn.close();
                 }

             return oLista;
        }catch(SQLException ex)
        {
            return null;
        }
    }
}
