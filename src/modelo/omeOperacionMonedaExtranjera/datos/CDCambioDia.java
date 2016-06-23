package modelo.omeOperacionMonedaExtranjera.datos;

import controller.acceso.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.omeOperacionMonedaExtranjera.entidad.CECambioDia;

public class CDCambioDia
{

    public static List<CECambioDia> consultarListaTipoCambioDia(int pcns,String pFecha,int IdMoneda)
    {
         Connection conexion = ConexionBD.obtenerConexion();
         try
         {
               List<CECambioDia> oLstTipoCambioDia = new ArrayList<CECambioDia>();
               String sql="{call OMESPRCNSCambioDia("+pcns+",'"+pFecha+"',"+IdMoneda+")}";
               Statement sentencia=conexion.createStatement();
               ResultSet rs=sentencia.executeQuery(sql);
               if (rs!=null)
                {
                while(rs.next())
                {
                    CECambioDia oCECambioDia=new CECambioDia();
                    oCECambioDia.setIdCambioDia(rs.getInt(1));
                    oCECambioDia.setIdMoneda(rs.getInt(2));
                    oCECambioDia.setMontoCompraMN(rs.getFloat(3));
                    oCECambioDia.setMontoVentaMN(rs.getFloat(4));
                    oCECambioDia.setMontoCompraDolar(rs.getFloat(5));
                    oCECambioDia.setMontoVentaDolar(rs.getFloat(6));
                    oCECambioDia.setFecha(rs.getString(7));
                    oCECambioDia.setIdUsuario(rs.getInt(8));
                    oCECambioDia.setMoneda(rs.getString(9));
                    oLstTipoCambioDia.add(oCECambioDia);
                }
                return oLstTipoCambioDia;
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
     public static int INSUPDTipoCambioDia(CECambioDia oCETipoCambioDia ,int accioabm )
     {
           Connection conexion;
           conexion = ConexionBD.obtenerConexion();
            try
              {
                CallableStatement sentenciaABM;
                conexion.setAutoCommit(false);
                conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

                String sql= "{call OMESPRINSUPDCambioDia(?,?,?,?,?,?,?,?)}";
                sentenciaABM = conexion.prepareCall(sql);
                sentenciaABM.setFloat(1, oCETipoCambioDia.getMontoCompraMN());
                sentenciaABM.setFloat(2, oCETipoCambioDia.getMontoVentaMN());
                sentenciaABM.setFloat(3, oCETipoCambioDia.getMontoCompraDolar());
                sentenciaABM.setFloat(4, oCETipoCambioDia.getMontoVentaDolar());
                sentenciaABM.setInt(5, oCETipoCambioDia.getIdMoneda());
                sentenciaABM.setInt(6, oCETipoCambioDia.getIdUsuario());
                sentenciaABM.setInt(7, accioabm);
                sentenciaABM.setLong(8, oCETipoCambioDia.getIdCambioDia());
                sentenciaABM.executeUpdate();
               conexion.commit();

               return 1;
                }
            catch (Exception e)
              {
                System.out.print(e);
                  try
                    {
                      conexion.rollback();
                    }
                  catch(SQLException sqlExcep)
                    {
                      sqlExcep.printStackTrace();
                    }
                return 0;
              }
           finally
            {
              try
                {
                 conexion.close();
                }
              catch(SQLException e)
                {
                  System.out.print(e);
                }
            }

      }

     public static int DELCambioDia(long idCambioDia)
     {
           Connection conexion;
           conexion = ConexionBD.obtenerConexion();
            try
              {
                CallableStatement sentenciaABM;
                conexion.setAutoCommit(false);
                conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

                String sql= "{call OMESPRDELCambioDia(?)}";
                sentenciaABM = conexion.prepareCall(sql);
                sentenciaABM.setLong(1, idCambioDia);
                sentenciaABM.executeUpdate();
               conexion.commit();

               return 1;
                }
            catch (Exception e)
              {
                System.out.print(e);
                  try
                    {
                      conexion.rollback();
                    }
                  catch(SQLException sqlExcep)
                    {
                      sqlExcep.printStackTrace();
                    }
                return 0;
              }
           finally
            {
              try
                {
                 conexion.close();
                }
              catch(SQLException e)
                {
                  System.out.print(e);
                }
            }

      }
}

