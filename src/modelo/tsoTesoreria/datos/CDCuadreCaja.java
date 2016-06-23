/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.tsoTesoreria.datos;

import controller.acceso.ConexionBD;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.tsoTesoreria.entidad.CECuadreCaja;
import modelo.tsoTesoreria.entidad.CECuadreCajaDetalle;
import modelo.tsoTesoreria.entidad.CECuadreCajaTipoPago;

/**
 *
 * @author Luiggi
 */
public class CDCuadreCaja {

    public static int INSCuadreCaja(CECuadreCaja oCECuadreCaja)
    {
        Connection conexion;
        conexion = ConexionBD.obtenerConexion();//5 elimina
        try
        {
            CallableStatement sentencia;
            CallableStatement sentenciaDetalle;
            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            String sql= "{call TSOSPRINSCuadreCaja(?,?,?,?,?)}";
            sentencia = conexion.prepareCall(sql);
            sentencia.setInt(1, oCECuadreCaja.getIdPuntoVenta());
            sentencia.setInt(2, oCECuadreCaja.getIdSucursal());
            sentencia.setLong(3, oCECuadreCaja.getIdCuadreCaja());
            sentencia.setInt(4, oCECuadreCaja.getUltimoIdUsuario());
            sentencia.setString(5, oCECuadreCaja.getObservacion());

            sentencia.executeUpdate();
            long id_CuadreCaja = sentencia.getInt(3);
            for (CECuadreCajaDetalle oCECuadreCajaDetalle : oCECuadreCaja.getLstCECuadreCajaDetalle())
            {
                String sql_ = "{call TSOSPRINSCuadreCajaDetalle(?,?,?)}";
       
                sentenciaDetalle = conexion.prepareCall(sql_);
                sentenciaDetalle.setLong(1, id_CuadreCaja);
                sentenciaDetalle.setLong(2, oCECuadreCajaDetalle.getIdMoneda());
                sentenciaDetalle.setBigDecimal(3, oCECuadreCajaDetalle.getMontoEfectivoInicial());
                sentenciaDetalle.executeUpdate();

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


    public static int UPDCuadreCaja(CECuadreCaja oCECuadreCaja,int opcion)// opcion 1=cierre ; opcion 2= cuadre
    {
        Connection conexion;
        conexion = ConexionBD.obtenerConexion();//5 elimina
        try
        {
            CallableStatement sentencia;
            CallableStatement sentenciaDetalle;
            CallableStatement sentenciaDetalleDet;
            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            String sql= "{call TSOSPRUPDCuadreCaja(?,?,?,?)}";
            sentencia = conexion.prepareCall(sql);
            sentencia.setLong(1, oCECuadreCaja.getIdCuadreCaja());
            sentencia.setInt(2, oCECuadreCaja.getUltimoIdUsuario());
            sentencia.setString(3, oCECuadreCaja.getObservacion());
            sentencia.setLong(4, opcion);

            sentencia.executeUpdate();
            if(opcion==2&&oCECuadreCaja.getLstCECuadreCajaDetalle()!=null){
                for (CECuadreCajaDetalle oCECuadreCajaDetalle : oCECuadreCaja.getLstCECuadreCajaDetalle())
                {
                    String sql_ = "{call TSOSPRUPDCuadreCajaDetalle(?,?,?,?,?,?)}";

                    sentenciaDetalle = conexion.prepareCall(sql_);
                    sentenciaDetalle.setLong(1, oCECuadreCaja.getIdCuadreCaja());
                    sentenciaDetalle.setLong(2, oCECuadreCajaDetalle.getIdMoneda());
                    sentenciaDetalle.setBigDecimal(3, oCECuadreCajaDetalle.getMontoTotalTesoreria());
                    sentenciaDetalle.setBigDecimal(4, oCECuadreCajaDetalle.getMontoTotalCuadre());
                    sentenciaDetalle.setBigDecimal(5, oCECuadreCajaDetalle.getMontoTotalDiferencia());
                    sentenciaDetalle.setLong(6, oCECuadreCajaDetalle.getIdCuadreCajaDetalle());
                    sentenciaDetalle.executeUpdate();
                    long vIdCuadreCajaDetalle=sentenciaDetalle.getLong(6);
                    
                    if(oCECuadreCajaDetalle.getLstCECuadreCajaTipoPago()!=null){
                        for (CECuadreCajaTipoPago oCECuadreCajaTipoPago : oCECuadreCajaDetalle.getLstCECuadreCajaTipoPago())
                        {
                        String sql__ = "{call TSOSPRINSCuadreCajaTipoPago(?,?,?,?,?,?,?)}";

                        sentenciaDetalleDet = conexion.prepareCall(sql__);
                        sentenciaDetalleDet.setLong(1, oCECuadreCaja.getIdCuadreCaja());
                        sentenciaDetalleDet.setInt(2, oCECuadreCajaTipoPago.getIdMoneda());
                        sentenciaDetalleDet.setInt(3, oCECuadreCajaTipoPago.getIdTipoPago());
                        sentenciaDetalleDet.setBigDecimal(4, oCECuadreCajaTipoPago.getMontoTesoreria());
                        sentenciaDetalleDet.setBigDecimal(5, oCECuadreCajaTipoPago.getMontoCuadre());
                        sentenciaDetalleDet.setBigDecimal(6, oCECuadreCajaTipoPago.getMontoDiferencia());
                        sentenciaDetalleDet.setLong(7, vIdCuadreCajaDetalle);
                        sentenciaDetalleDet.executeUpdate();

                        }
                    }

                }
                 

                
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
    public static CECuadreCaja ObtenerCuadreCaja(int pIdPuntoVenta,String pFecha,int pcns)
    {
      Connection cnn= ConexionBD.obtenerConexion();
        try
        {
                CECuadreCaja oCECuadreCaja=null;
            
                if(cnn!=null)
                {
                     CallableStatement cs= null;
                     CallableStatement csDet= null;
                     cs = cnn.prepareCall("CALL TSOSPRCNSCuadreCaja(?,?,?)");
                     cs.setInt(1, pIdPuntoVenta);
                     cs.setInt(2, pcns);
                     cs.setString(3, pFecha);

                     ResultSet rs=cs.executeQuery();
                      if(rs.next())
                      {

                         do
                          {
                            oCECuadreCaja= new CECuadreCaja();
                            oCECuadreCaja.setIdCuadreCaja(rs.getLong(1));
                            oCECuadreCaja.setUltimoIdEstado(rs.getInt(2));
                            oCECuadreCaja.setUltimoEstado(rs.getString(3));
                            oCECuadreCaja.setFechaApertura(rs.getString(4));
                            oCECuadreCaja.setFechaCierre(rs.getString(5));
                            oCECuadreCaja.setFechacuadre(rs.getString(6));

                                List<CECuadreCajaDetalle> lstCECuadreCajaDetalle= new ArrayList<CECuadreCajaDetalle>();
                                CECuadreCajaDetalle oCECuadreCajaDetalle=null;
                                csDet = cnn.prepareCall("CALL TSOSPRCNSCuadreCajaDetalle(?)");

                                long pIdCuadreCaja=oCECuadreCaja.getIdCuadreCaja();
                                 csDet.setLong(1,pIdCuadreCaja);
                                 ResultSet rsDet=csDet.executeQuery();
                                  if(rsDet.next())
                                  {
                                      do
                                        {
                                          oCECuadreCajaDetalle =new CECuadreCajaDetalle();
                                          oCECuadreCajaDetalle.setIdMoneda(rsDet.getInt(1));
                                          oCECuadreCajaDetalle.setMontoEfectivoInicial(rsDet.getBigDecimal(2));
                                          oCECuadreCajaDetalle.setMoneda(rsDet.getString(3));
                                          oCECuadreCajaDetalle.setMontoTotalCuadre(rsDet.getBigDecimal(4));
                                          oCECuadreCajaDetalle.setMontoTotalTesoreria(rsDet.getBigDecimal(5));
                                          oCECuadreCajaDetalle.setMontoTotalDiferencia(rsDet.getBigDecimal(6));
                                          oCECuadreCajaDetalle.setIdCuadreCajaDetalle(rsDet.getLong(7));

                                          lstCECuadreCajaDetalle.add(oCECuadreCajaDetalle);
                                        }while(rsDet.next());
                                  }
                                 oCECuadreCaja.setLstCECuadreCajaDetalle(lstCECuadreCajaDetalle);
                          }
                          while(rs.next());
                       }
                           cnn.close();
                 }
             return oCECuadreCaja;
        }
        catch(SQLException ex)
        {
            System.out.println(ex);
            return null;
        }
    }


    public static List<CECuadreCajaTipoPago> listarIngresoTipoPago(String pFecha,int pIdPuntoVenta)
    {
      Connection cnn= ConexionBD.obtenerConexion();
        try
        {
                
                List<CECuadreCajaTipoPago> listCECuadreCajaTipoPago=new ArrayList<CECuadreCajaTipoPago>();
                if(cnn!=null)
                {
                     CallableStatement cs= null;
                     CECuadreCajaTipoPago oCECuadreCajaTipoPago=null;
                     cs = cnn.prepareCall("CALL TSOSPRCNSCuadreCajaTipoPago(?,?)");
                     cs.setString(1, pFecha);
                     cs.setInt(2, pIdPuntoVenta);

                     ResultSet rs=cs.executeQuery();
                      if(rs.next())
                      {

                         do
                          {
                            oCECuadreCajaTipoPago= new CECuadreCajaTipoPago();
                            oCECuadreCajaTipoPago.setMoneda(rs.getString(1));
                            oCECuadreCajaTipoPago.setIdMoneda(rs.getInt(2));
                            oCECuadreCajaTipoPago.setIdTipoPago(rs.getInt(3));
                            oCECuadreCajaTipoPago.setTipoPago(rs.getString(4));
                            oCECuadreCajaTipoPago.setMontoCuadre(rs.getBigDecimal(5));
                            oCECuadreCajaTipoPago.setMontoTesoreria(rs.getBigDecimal(6));
                            oCECuadreCajaTipoPago.setMontoDiferencia(rs.getBigDecimal(7));
                            oCECuadreCajaTipoPago.setIdCuadreCaja(rs.getLong(8));
                            listCECuadreCajaTipoPago.add(oCECuadreCajaTipoPago);

                          }
                          while(rs.next());
                       }
                           cnn.close();
                 }
             return listCECuadreCajaTipoPago;
        }
        catch(SQLException ex)
        {
            System.out.println(ex);
            return null;
        }
    }

}
