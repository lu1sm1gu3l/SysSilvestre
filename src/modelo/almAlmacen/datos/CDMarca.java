package modelo.almAlmacen.datos;

import controller.acceso.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.almAlmacen.entidad.CEMarca;

public class CDMarca 
{
       public static List<CEMarca>  consultarListaMarcasxSubfamilia(int IdSubfamilia,int IdFamilia,int IdCategoria,int IdRubro)
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try
         {
               List<CEMarca> oLstMarca = new ArrayList<CEMarca>();
               String sql="{call ALMSPRCNSMarca(1,"+IdSubfamilia+",null,null,"+IdFamilia+","+IdCategoria+","+IdRubro+")}";
               Statement sentencia=conexion.createStatement();
               ResultSet rs=sentencia.executeQuery(sql);
               if (rs!=null)
                {
                while(rs.next())
                {
                    CEMarca oCEMarca=new CEMarca();
                    oCEMarca.setIdMarca(rs.getInt(1));
                    oCEMarca.setDescripcion(rs.getString(2));
                    oLstMarca.add(oCEMarca);
                }
                return oLstMarca;
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
      public static List<CEMarca>  consultarListaMarcas(String marca)
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try
         {
               List<CEMarca> oLstMarca = new ArrayList<CEMarca>();
               String sql="{call ALMSPRCNSMarca(2,null,null,'"+marca+"',null,null,null)}";
               Statement sentencia=conexion.createStatement();
               ResultSet rs=sentencia.executeQuery(sql);
               if (rs!=null)
                {
                while(rs.next())
                {
                    CEMarca oCEMarca=new CEMarca();
                    oCEMarca.setIdMarca(rs.getInt(1));
                    oCEMarca.setDescripcion(rs.getString(2));
                    oLstMarca.add(oCEMarca);
                }
                return oLstMarca;
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
       public static int ABMMarca(int pIntAMB,CEMarca oCDUnidadMedidaVenta)
        {
           Connection conexion;
           conexion = ConexionBD.obtenerConexion();
            try
              {
                CallableStatement sentenciaABMUnidadMedidaVenta;
                conexion.setAutoCommit(false);
                conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

                String sql= "{call ALMSPRABMarca(?,?,?)}";
                sentenciaABMUnidadMedidaVenta = conexion.prepareCall(sql);
                sentenciaABMUnidadMedidaVenta.setInt(1, pIntAMB);
                sentenciaABMUnidadMedidaVenta.setInt(2, oCDUnidadMedidaVenta.getIdMarca());
                sentenciaABMUnidadMedidaVenta.setString(3, oCDUnidadMedidaVenta.getDescripcion());
                sentenciaABMUnidadMedidaVenta.executeUpdate();
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
       public static List<CEMarca>  consultarListaMarca()
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try {
               List<CEMarca> oLstAnioSemestreAcademicoMatriz = new ArrayList<CEMarca>();
               String sql="{call ALMSPRCNSListaMarca()}";
               Statement sentencia=conexion.createStatement();
               ResultSet rs=sentencia.executeQuery(sql);
               if (rs!=null)
                {
                while(rs.next())
                {
                    CEMarca oCEMarca=new CEMarca();
                    oCEMarca.setIdMarca(rs.getInt(1));
                    oCEMarca.setDescripcion(rs.getString(2));
                    oLstAnioSemestreAcademicoMatriz.add(oCEMarca);
                }
                return oLstAnioSemestreAcademicoMatriz;
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
}
