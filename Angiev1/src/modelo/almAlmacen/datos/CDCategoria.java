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
import modelo.almAlmacen.entidad.CECategoria;
import util.clases.vtaVenta.JTreeTableJerarquia.ObjetoJerarquia;

public class CDCategoria
{
      public static List<CECategoria>  consultarListaCategoriasPorIdRubro(int pIdCns,int pIdRubro)
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try {
               List<CECategoria> oLstAnioSemestreAcademicoMatriz = new ArrayList<CECategoria>();
               String sql="{call ALMSPRCNSCategoria("+pIdCns+",null,"+pIdRubro+")}";
               Statement sentencia=conexion.createStatement();
               ResultSet rs=sentencia.executeQuery(sql);
               if (rs!=null)
                {
                    while(rs.next())
                    {
                        CECategoria oCECategoria=new CECategoria();
                        oCECategoria.setIdCategoria(rs.getInt(1));
                        oCECategoria.setIdRubro(rs.getInt(2));
                        oCECategoria.setCodigo(rs.getString(3));
                        oCECategoria.setDescripcion(rs.getString(4));
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
      public static List<ObjetoJerarquia>  consultarListaCategoriasPorIdRubroVenta(int codigo,int pIdRubro,String pFecha1,String pFecha2)
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try {
               List<ObjetoJerarquia> oLstAnioSemestreAcademicoMatriz = new ArrayList<ObjetoJerarquia>();
               String sql="{call VTASPRRPTNumVentasCategoria(?,?,?,?)}";
               PreparedStatement sentencia=conexion.prepareCall(sql);
               sentencia.setInt(1,codigo);
               sentencia.setInt(2,pIdRubro);
               sentencia.setString(3,pFecha1);
               sentencia.setString(4,pFecha2);
               ResultSet rs=sentencia.executeQuery();
               if (rs!=null)
                {
                    while(rs.next())
                    {
                        ObjetoJerarquia oCECategoria=new ObjetoJerarquia();
                        oCECategoria.setIdCategoria(rs.getInt(1));
                        oCECategoria.setIdRubro(pIdRubro);
                        oCECategoria.setDescripcion(rs.getString(3));
                        oCECategoria.setNumVentas(rs.getFloat(5));
                        oCECategoria.setCantidadVendida(rs.getFloat(6));
                        oCECategoria.setTipoObjeto("Categoria");
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
         public static int ABMCategoria(int pIntAMB,CECategoria oCECategoria)
    {
       Connection conexion;
       conexion = ConexionBD.obtenerConexion();//5 elimina
        try
          {
            CallableStatement sentenciaABMCategoria;
            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            String sql= "{call ALMSPRABMCategoria(?,?,?,?,?)}";
            sentenciaABMCategoria = conexion.prepareCall(sql);
            sentenciaABMCategoria.setInt(1, pIntAMB);
            sentenciaABMCategoria.setInt(2, oCECategoria.getIdCategoria());
            sentenciaABMCategoria.setString(3, oCECategoria.getCodigo());
            sentenciaABMCategoria.setString(4, oCECategoria.getDescripcion());
            sentenciaABMCategoria.setInt(5, oCECategoria.getIdRubro());
            sentenciaABMCategoria.executeUpdate();
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
