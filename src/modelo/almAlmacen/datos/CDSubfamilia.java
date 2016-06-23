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
import modelo.almAlmacen.entidad.CESubfamilia;
import util.clases.vtaVenta.JTreeTableJerarquia.ObjetoJerarquia;
/**
 *
 * @author Katya
 */
public class CDSubfamilia {


    public static int ABMSubFamilia(int pIntAMB,CESubfamilia oCESubfamilia)
    {
       Connection conexion;
       conexion = ConexionBD.obtenerConexion();//5 elimina
        try
          {
            CallableStatement sentenciaABMSubfamilia;
            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            String sql= "{call ALMSPRABMSubFamilia(?,?,?,?,?)}";
            sentenciaABMSubfamilia = conexion.prepareCall(sql);
            sentenciaABMSubfamilia.setInt(1, pIntAMB);
            sentenciaABMSubfamilia.setInt(2, oCESubfamilia.getIdSubFamilia());
            sentenciaABMSubfamilia.setString(3, oCESubfamilia.getCodigo());
            sentenciaABMSubfamilia.setString(4, oCESubfamilia.getDescripcion());
            sentenciaABMSubfamilia.setInt(5, oCESubfamilia.getIdFamilia());
            sentenciaABMSubfamilia.executeUpdate();
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
  public static List<CESubfamilia>  consultarListaSubfamiliaPorIdFamilia(int pIdCns,int pIdCategoria)
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try {
               List<CESubfamilia> oLstAnioSemestreAcademicoMatriz = new ArrayList<CESubfamilia>();
               String sql="{call ALMSPRCNSSubfamilia("+pIdCns+",null,"+pIdCategoria+")}";
               Statement sentencia=conexion.createStatement();
               ResultSet rs=sentencia.executeQuery(sql);
               if (rs!=null)
                {
                    while(rs.next())
                    {
                        CESubfamilia oCESubfamilia=new CESubfamilia();
                        oCESubfamilia.setIdSubFamilia(rs.getInt(1));
                        oCESubfamilia.setIdFamilia(rs.getInt(2));
                        oCESubfamilia.setCodigo(rs.getString(3));
                        oCESubfamilia.setDescripcion(rs.getString(4));
                        oLstAnioSemestreAcademicoMatriz.add(oCESubfamilia);
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
      public static List<ObjetoJerarquia>  consultarListaSubFamiliaPorIdFamiliaVenta(int codigo,int pIdRubro,int pIdCategoria,int pIdFamilia,String pFecha1,String pFecha2)
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try {
               List<ObjetoJerarquia> oLstAnioSemestreAcademicoMatriz = new ArrayList<ObjetoJerarquia>();
               String sql="{call VTASPRRPTNumVentasSubfamilia(?,?,?,?,?,?)}";
               PreparedStatement sentencia=conexion.prepareCall(sql);
               sentencia.setInt(1,codigo);
               sentencia.setInt(2,pIdRubro);
               sentencia.setInt(3,pIdCategoria);
               sentencia.setInt(4,pIdFamilia);
               sentencia.setString(5,pFecha1);
               sentencia.setString(6,pFecha2);
               ResultSet rs=sentencia.executeQuery();
               if (rs!=null)
                {
                    while(rs.next())
                    {
                        ObjetoJerarquia oCECategoria=new ObjetoJerarquia();
                        oCECategoria.setIdSubfamilia(rs.getInt(1));
                        oCECategoria.setIdRubro(pIdRubro);
                        oCECategoria.setIdCategoria(pIdCategoria);
                        oCECategoria.setIdFamilia(pIdFamilia);
                        oCECategoria.setDescripcion(rs.getString(2));
                        oCECategoria.setNumVentas(rs.getFloat(4));
                        oCECategoria.setCantidadVendida(rs.getFloat(5));
                        oCECategoria.setTipoObjeto("Subfamilia");
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
}
