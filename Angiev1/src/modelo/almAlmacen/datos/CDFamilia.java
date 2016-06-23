/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
import modelo.almAlmacen.entidad.CEFamilia;
import util.clases.vtaVenta.JTreeTableJerarquia.ObjetoJerarquia;

/**
 *
 * @author Katya
 */
public class CDFamilia {


    public static int ABMFamilia(int pIntAMB,CEFamilia oCEFamilia)
    {
       Connection conexion;
       conexion = ConexionBD.obtenerConexion();//5 elimina
        try
          {
            CallableStatement sentenciaABMFamilia;
            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            String sql= "{call ALMSPRABMFamilia(?,?,?,?,?)}";
            sentenciaABMFamilia = conexion.prepareCall(sql);
            sentenciaABMFamilia.setInt(1, pIntAMB);
            sentenciaABMFamilia.setInt(2, oCEFamilia.getIdFamilia());
            sentenciaABMFamilia.setString(3, oCEFamilia.getCodigo());
            sentenciaABMFamilia.setString(4, oCEFamilia.getDescripcion());
            sentenciaABMFamilia.setInt(5, oCEFamilia.getIdCategoria());
            sentenciaABMFamilia.executeUpdate();
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
    public static List<ObjetoJerarquia>  consultarFamiliaPorIdCategoriaVenta(int codigo,int pIdRubro,int pIdCategoria,String pFecha1,String pFecha2)
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try {
               List<ObjetoJerarquia> oLstAnioSemestreAcademicoMatriz = new ArrayList<ObjetoJerarquia>();
               String sql="{call VTASPRRPTNumVentasFamilia(?,?,?,?,?)}";
               PreparedStatement sentencia=conexion.prepareCall(sql);
               sentencia.setInt(1,codigo);
               sentencia.setInt(2,pIdRubro);
               sentencia.setInt(3,pIdCategoria);
               sentencia.setString(4,pFecha1);
               sentencia.setString(5,pFecha2);
               ResultSet rs=sentencia.executeQuery();
               if (rs!=null)
                {
                    while(rs.next())
                    {
                        ObjetoJerarquia oCECategoria=new ObjetoJerarquia();
                        oCECategoria.setIdFamilia(rs.getInt(1));
                        oCECategoria.setDescripcion(rs.getString(2));
                        oCECategoria.setNumVentas(rs.getFloat(4));
                        oCECategoria.setCantidadVendida(rs.getFloat(5));
                        oCECategoria.setIdCategoria(pIdCategoria);
                        oCECategoria.setIdRubro(pIdRubro);
                        oCECategoria.setTipoObjeto("Familia");
                        oLstAnioSemestreAcademicoMatriz.add(oCECategoria);
                    }
                    return oLstAnioSemestreAcademicoMatriz.isEmpty()?null:oLstAnioSemestreAcademicoMatriz;
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
      public static List<CEFamilia>  consultarListaFamiliasPorIdCategoria(int pIdCns,int pIdCategoria)
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try {
               List<CEFamilia> oLstAnioSemestreAcademicoMatriz = new ArrayList<CEFamilia>();
               String sql="{call ALMSPRCNSFamilia("+pIdCns+",null,"+pIdCategoria+")}";
               Statement sentencia=conexion.createStatement();
               ResultSet rs=sentencia.executeQuery(sql);
               if (rs!=null)
                {
                    while(rs.next())
                    {
                        CEFamilia oCEFamilia=new CEFamilia();
                        oCEFamilia.setIdFamilia(rs.getInt(1));
                        oCEFamilia.setIdCategoria(rs.getInt(2));
                        oCEFamilia.setCodigo(rs.getString(3));
                        oCEFamilia.setDescripcion(rs.getString(4));
                        oLstAnioSemestreAcademicoMatriz.add(oCEFamilia);
                    }
                    return oLstAnioSemestreAcademicoMatriz.isEmpty()?null:oLstAnioSemestreAcademicoMatriz;
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
