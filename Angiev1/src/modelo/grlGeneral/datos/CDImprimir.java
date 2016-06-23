package modelo.grlGeneral.datos;
import controller.acceso.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.clases.grlGeneral.CLImprimir;
public class CDImprimir
{
  

    public static boolean Actualizar(List<CLImprimir> oListImprimir)
    {
        Connection conexion = ConexionBD.obtenerConexion();
        try
        {

            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            for (int i = 0; i < oListImprimir.size(); i++) {
                CLImprimir oImprimir = oListImprimir.get(i);
                String sql = "{call GRLUPDImprimir(?,?,?)}";
                CallableStatement sentencia = conexion.prepareCall(sql);
                sentencia.setInt(1,oImprimir.getIdImprimir());
                sentencia.setInt(2,oImprimir.getX());
                sentencia.setInt(3,oImprimir.getY());
                sentencia.executeUpdate();
            }


            conexion.commit();
            return true;
            
        }
        catch (Exception e)
        {
            System.out.print(e);
            try
            {
                conexion.rollback();
            }
            catch (SQLException sqlExcep)
            {
                sqlExcep.printStackTrace();
            }
            return false;
        }
        finally
        {
            try
            {
                conexion.close();
            }
            catch (SQLException e)
            {
                System.out.print(e);
            }
        }

    }

        public static List<CLImprimir> Listado( int pidComp)
         {
                Connection conexion = ConexionBD.obtenerConexion();
                try
                {
                    List<CLImprimir> LstCLImprimir = new ArrayList<CLImprimir>();
                    String sql = "{call GRLSPRCNSImprimir("+pidComp+")}";
                    CallableStatement sentencia = conexion.prepareCall(sql);
                    ResultSet resultado = sentencia.executeQuery();
                    if (resultado!=null)
                {
                    while (resultado.next())
                    {
                        CLImprimir oCLImprimir = new CLImprimir();
                        oCLImprimir.setIdImprimir(resultado.getInt(1));
                        oCLImprimir.setIdTipoComprobante(resultado.getInt(2));
                        oCLImprimir.setDescripcion(resultado.getString(3));
                        oCLImprimir.setX(resultado.getInt(4));
                        oCLImprimir.setY(resultado.getInt(5));
                        LstCLImprimir.add(oCLImprimir);
                    }
                    conexion.close();
                    return LstCLImprimir;
                    }
               return null;

                }
                catch(SQLException e)

            {
                e.printStackTrace();
                return null;
            }
        
                catch(Exception e)
                {
                    e.printStackTrace();
                    return null;
                }
         }
}


