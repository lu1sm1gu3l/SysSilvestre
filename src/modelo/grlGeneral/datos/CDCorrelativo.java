/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.grlGeneral.datos;

import controller.acceso.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import modelo.vtaVenta.entidad.CESerie;

/**
 *
 * @author pc25
 */
public class CDCorrelativo {

    public static String getcorrlativo()
      {
        int Numcor=0;
         Connection conexion = ConexionBD.obtenerConexion();
         try {
               
               String sql="{CALL VTASPRConsultarCorrelativo()}";
               CallableStatement sentencia=conexion.prepareCall(sql);
               ResultSet rs=sentencia.executeQuery();
               if (rs!=null)
                {
                while (rs.next())
                {
                    Numcor=(rs.getInt(1));
                }

                }
               return Numcor+"";
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
                    if(conexion!=null)
                    {
                conexion.close();}
                }
                catch (SQLException ex)
                {
                System.out.print("No se puede cerrar la conexion");
                }
            }
    }

    public static int Actializar(int corelativo)
    {
        Connection conexion;
        conexion = ConexionBD.obtenerConexion();//5 elimina
        try
        {
            CallableStatement sentencia;
            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            String sql= "{call VTASPRUpdateCorrelativo(?)}";
            sentencia = conexion.prepareCall(sql);
            sentencia.setInt(1, corelativo);

            sentencia.executeUpdate();
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
            catch (SQLException sqlExcep)
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
            catch (SQLException e)
            {
                System.out.print(e);
            }
        }

    }

    public static int Guardar( List<CESerie> lista)
    {
        Connection conexion;
        conexion = ConexionBD.obtenerConexion();//5 elimina
        try
        {
            CallableStatement sentencia;
            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            String sql= "{call VTASPRUpdateCorrelativo(?,?,?,?,?,?,?)}";
            sentencia = conexion.prepareCall(sql);

            for (CESerie cESerie : lista) {
                sentencia.setInt(1, cESerie.getIdSerie());
                sentencia.setInt(2, cESerie.getIdTipoComprobante());
                sentencia.setString(3, cESerie.getNumero());
                sentencia.setLong(4, cESerie.getUltimoNumeroGenerado());
                sentencia.setInt(5, cESerie.getIdPuntoVentaSerie());
                sentencia.setInt(6, cESerie.getoCEPuntoVenta().getIdPuntoVenta());
                sentencia.setInt(7, cESerie.getAccionabm());

                sentencia.executeUpdate();
            }



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
            catch (SQLException sqlExcep)
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
            catch (SQLException e)
            {
                System.out.print(e);
            }
        }

    }

}

