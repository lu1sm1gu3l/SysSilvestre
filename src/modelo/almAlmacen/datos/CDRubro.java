package modelo.almAlmacen.datos;

import controller.acceso.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.almAlmacen.entidad.CERubro;
import util.clases.vtaVenta.JTreeTableJerarquia.ObjetoJerarquia;

public class CDRubro
{
      public static List<CERubro>  consultarListaRubros()
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try {
               List<CERubro> oLstAnioSemestreAcademicoMatriz = new ArrayList<CERubro>();
               String sql="{call ALMSPRCNSRubro(1,null)}";
               Statement sentencia=conexion.createStatement();
               ResultSet rs=sentencia.executeQuery(sql);
               if (rs!=null)
                {
                while(rs.next())
                {
                    CERubro oCERubro=new CERubro();
                    oCERubro.setIdRubro(rs.getInt(1));
                    oCERubro.setCodigo(rs.getString(2));
                    oCERubro.setDescripcion(rs.getString(3));
                    oLstAnioSemestreAcademicoMatriz.add(oCERubro);
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
       public static List<ObjetoJerarquia>  consultarListaRubrosNumVenta(int IdCodigo,String fecha1,String fecha2)
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try {
               List<ObjetoJerarquia> oLstAnioSemestreAcademicoMatriz = new ArrayList<ObjetoJerarquia>();
               String sql="{call VTASPRRPTNumVentasRubro(?,?,?)}";
               PreparedStatement sentencia=conexion.prepareCall(sql);
               sentencia.setInt(1,IdCodigo);
               sentencia.setString(2,fecha1);
               sentencia.setString(3,fecha2);
               ResultSet rs=sentencia.executeQuery();
               if (rs!=null)
                {
                while(rs.next())
                {
                    ObjetoJerarquia oCERubro=new ObjetoJerarquia();
                    oCERubro.setIdRubro(rs.getInt(1));
                    oCERubro.setDescripcion(rs.getString(2));
                    oCERubro.setNumVentas(rs.getFloat(4));
                    oCERubro.setCantidadVendida(rs.getFloat(5));
                    oCERubro.setTipoObjeto("Rubro");
                    oLstAnioSemestreAcademicoMatriz.add(oCERubro);
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
     public static int ABMRubro(int pIntAMB,CERubro oCDRubro)
        {
           Connection conexion;
           conexion = ConexionBD.obtenerConexion();
            try
              {
                CallableStatement sentenciaABMRubro;
                conexion.setAutoCommit(false);
                conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

                String sql= "{call ALMSPRABMRubro(?,?,?,?)}";
                sentenciaABMRubro = conexion.prepareCall(sql);
                sentenciaABMRubro.setInt(1, pIntAMB);
                sentenciaABMRubro.setInt(2, oCDRubro.getIdRubro());
                sentenciaABMRubro.setString(3, oCDRubro.getCodigo());
                sentenciaABMRubro.setString(4, oCDRubro.getDescripcion());
                sentenciaABMRubro.executeUpdate();
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
