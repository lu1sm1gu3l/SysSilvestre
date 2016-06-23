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
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.almAlmacen.entidad.CEEquivalenciaUnidad;
import modelo.almAlmacen.entidad.CEProducto;
import modelo.almAlmacen.entidad.CEUnidadMedidaProducto;
import util.clases.almAlmacen.CLEquivalenciaABM;
import util.clases.vtaVenta.JTreeTableJerarquia.ObjetoJerarquia;


public class CDProducto
{
    public static boolean registrarProducto(CEProducto oCEProducto)
    {
        Connection conexion = ConexionBD.obtenerConexion();
         try
         {
               String sql="{call ALMSPRINSProducto(?,?,?,?,?,?,?,?,?,?,?,?)}";
               conexion.setAutoCommit(false);
               CallableStatement sentencia=conexion.prepareCall(sql);
               sentencia.setInt(1,oCEProducto.getIdUnidadBase());
               sentencia.setString(2,oCEProducto.getDescripcion());
               sentencia.setInt(3,oCEProducto.getIdSubFamilia());
               sentencia.setInt(4,oCEProducto.getIdFamilia());
               sentencia.setInt(5,oCEProducto.getIdCategoria());
               sentencia.setInt(6,oCEProducto.getIdRubro());
               sentencia.setInt(7,oCEProducto.getIdMarca());
               sentencia.setBoolean(9,oCEProducto.isSiNoImpuesto());
               sentencia.setString(10,oCEProducto.getCodigo());
               sentencia.setInt(11,oCEProducto.getIdUnidadPresentacionVenta());
               sentencia.setBoolean(12,oCEProducto.isPercepcion());
               sentencia.registerOutParameter(8,java.sql.Types.BIGINT);
               sentencia.execute();
               oCEProducto.setIdProducto(sentencia.getLong(8));
               for(CEEquivalenciaUnidad oCEEquivalencia:oCEProducto.getoListEquivalencia())
               {
                    String sqlEq="{call ALMSPRINSEquivalenciaProducto(?,?,?,?,?)}"; // se obsera que se al momento de insertar toma la unidad equivalencia como unidad de pedido .. eso quiero decir que graba al reves
                    PreparedStatement sentenciaEq=conexion.prepareCall(sqlEq);
                    sentenciaEq.setLong(1,oCEProducto.getIdProducto());
                    sentenciaEq.setInt(2,oCEEquivalencia.getIdUnidadBase());
                    sentenciaEq.setDouble(3,oCEEquivalencia.getCantidadBase());
                    sentenciaEq.setInt(4,oCEEquivalencia.getIdUnidadPedido());
                    sentenciaEq.setDouble(5,oCEEquivalencia.getCantidadPedido());
                    
                    sentenciaEq.execute();
               }
               for(CEUnidadMedidaProducto oCEUnidadMedidaProducto:oCEProducto.getoCEUnidadMedidaProducto())
               {
                    String sqlEq="{call ALMSPRINSUnidadMedidaProducto(?,?,?,?,?,?,?,?,?,?,?)}";
                    PreparedStatement sentenciaEq=conexion.prepareCall(sqlEq);
                    sentenciaEq.setInt(1,1);
                    sentenciaEq.setLong(2,oCEUnidadMedidaProducto.getIdUnidadMedidaProducto());
                    sentenciaEq.setLong(3,oCEProducto.getIdProducto());
                    sentenciaEq.setLong(4,oCEUnidadMedidaProducto.getIdUnidadMedidaVenta());
                    sentenciaEq.setInt(5,oCEUnidadMedidaProducto.getCorrelativo());
                    sentenciaEq.setInt(6,oCEUnidadMedidaProducto.getSiNoUnidadBase());
                    sentenciaEq.setFloat(7,oCEUnidadMedidaProducto.getEquivalenciaConBase());
                    sentenciaEq.setString(8,oCEUnidadMedidaProducto.getDescripcion());
                    sentenciaEq.setFloat(9,oCEUnidadMedidaProducto.getSiNoUnidadMasComercial());
                    sentenciaEq.setFloat(10,oCEUnidadMedidaProducto.getNumUnidades());
                    sentenciaEq.setInt(11,oCEProducto.getIdUnidadPresentacionVenta());
                    sentenciaEq.execute();
               }
               conexion.commit();
               return true;
            }
        catch (Exception e)
            {
            e.printStackTrace();
            try
            {
                conexion.rollback();
                return false;
            }
            catch (SQLException ex)
            {
                Logger.getLogger(CDProducto.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
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
      public static boolean actualizarProducto(CEProducto oCEProducto,List<CLEquivalenciaABM> oLstEquivalencia)
    {
        Connection conexion = ConexionBD.obtenerConexion();
         try
         {
               String sql="{call ALMSPRUPDProducto(?,?,?,?,?,?)}";
               conexion.setAutoCommit(false);
               CallableStatement sentencia=conexion.prepareCall(sql);
               sentencia.setInt(1,oCEProducto.getIdUnidadBase());
               sentencia.setString(2,oCEProducto.getDescripcion());
               sentencia.setInt(3,oCEProducto.getIdMarca());
               sentencia.setBoolean(4,oCEProducto.isSiNoImpuesto());
               sentencia.setLong(5,oCEProducto.getIdProducto());
               sentencia.setInt(6,oCEProducto.getIdUnidadPresentacionVenta());
               sentencia.execute();
               for(CLEquivalenciaABM oCLEquivalencia:oLstEquivalencia)
               {
                    if(oCLEquivalencia.getAbm()==1)
                    {
                        String sqlEq="{call ALMSPRINSEquivalenciaProducto(?,?,?,?,?)}";
                        PreparedStatement sentenciaEq=conexion.prepareCall(sqlEq);
                        sentenciaEq.setLong(1,oCEProducto.getIdProducto());
                        sentenciaEq.setInt(2,oCLEquivalencia.getoCEEquivalencia().getIdUnidadBase());
                        sentenciaEq.setFloat(3,oCLEquivalencia.getoCEEquivalencia().getCantidadBase());
                        sentenciaEq.setDouble(4,oCLEquivalencia.getoCEEquivalencia().getIdUnidadPedido());
                        sentenciaEq.setFloat(5,oCLEquivalencia.getoCEEquivalencia().getCantidadPedido());                       
                        sentenciaEq.execute();
                    }
                    else
                    {
                       if(oCLEquivalencia.getAbm()==2)
                       {
                            String sqlEq="{call ALMSPRUPDEquivalenciaProducto(?,?,?,?,?)}";
                            PreparedStatement sentenciaEq=conexion.prepareCall(sqlEq);
                            sentenciaEq.setLong(1,oCLEquivalencia.getoCEEquivalencia().getIdEquivalenciaUnidad());
                            sentenciaEq.setInt(2,oCLEquivalencia.getoCEEquivalencia().getIdUnidadBase());
                            sentenciaEq.setFloat(3,oCLEquivalencia.getoCEEquivalencia().getCantidadBase());
                            sentenciaEq.setDouble(4,oCLEquivalencia.getoCEEquivalencia().getIdUnidadPedido());
                            sentenciaEq.setFloat(5,oCLEquivalencia.getoCEEquivalencia().getCantidadPedido());

                            sentenciaEq.execute();
                       }
                       else
                       {
                            String sqlEq="{call ALMSPRDELEquivalenciaProducto(?)}";
                            PreparedStatement sentenciaEq=conexion.prepareCall(sqlEq);
                            sentenciaEq.setLong(1,oCLEquivalencia.getoCEEquivalencia().getIdEquivalenciaUnidad());
                            sentenciaEq.execute();
                       }
                    }
               }
               for(CEUnidadMedidaProducto oCEUnidadMedidaProducto:oCEProducto.getoCEUnidadMedidaProducto())
               {
                    if(oCEUnidadMedidaProducto.getAbm()==1)
                    {
                         String sqlEq="{call ALMSPRINSUnidadMedidaProducto(?,?,?,?,?,?,?,?,?,?,?)}";
                        PreparedStatement sentenciaEq=conexion.prepareCall(sqlEq);
                        sentenciaEq.setInt(1,1);
                        sentenciaEq.setLong(2,oCEUnidadMedidaProducto.getIdUnidadMedidaProducto());
                        sentenciaEq.setLong(3,oCEProducto.getIdProducto());
                        sentenciaEq.setLong(4,oCEUnidadMedidaProducto.getIdUnidadMedidaVenta());
                        sentenciaEq.setInt(5,oCEUnidadMedidaProducto.getCorrelativo());
                        sentenciaEq.setInt(6,oCEUnidadMedidaProducto.getSiNoUnidadBase());
                        sentenciaEq.setFloat(7,oCEUnidadMedidaProducto.getEquivalenciaConBase());
                        sentenciaEq.setString(8,oCEUnidadMedidaProducto.getDescripcion());
                        sentenciaEq.setFloat(9,oCEUnidadMedidaProducto.getSiNoUnidadMasComercial());
                        sentenciaEq.setFloat(10,oCEUnidadMedidaProducto.getNumUnidades());
                        sentenciaEq.setInt(11,oCEProducto.getIdUnidadPresentacionVenta());
                        sentenciaEq.execute();
                    }
                    else
                    {
                       if(oCEUnidadMedidaProducto.getAbm()==2)
                       {
                            String sqlEq="{call ALMSPRUPDUnidadMedidaProducto(?,?,?,?,?,?,?,?,?,?,?)}";
                            PreparedStatement sentenciaEq=conexion.prepareCall(sqlEq);
                            sentenciaEq.setInt(1,1);
                            sentenciaEq.setLong(2,oCEUnidadMedidaProducto.getIdUnidadMedidaProducto());
                            sentenciaEq.setLong(3,oCEProducto.getIdProducto());
                            sentenciaEq.setLong(4,oCEUnidadMedidaProducto.getIdUnidadMedidaVenta());
                            sentenciaEq.setInt(5,oCEUnidadMedidaProducto.getCorrelativo());
                            sentenciaEq.setInt(6,oCEUnidadMedidaProducto.getSiNoUnidadBase());
                            sentenciaEq.setFloat(7,oCEUnidadMedidaProducto.getEquivalenciaConBase());
                            sentenciaEq.setString(8,oCEUnidadMedidaProducto.getDescripcion());
                            sentenciaEq.setFloat(9,oCEUnidadMedidaProducto.getSiNoUnidadMasComercial());
                            sentenciaEq.setFloat(10,oCEUnidadMedidaProducto.getNumUnidades());
                            sentenciaEq.setInt(11,oCEProducto.getIdUnidadPresentacionVenta());
                            sentenciaEq.execute();
                       }
                       else
                       {
                            String sqlEq="{call ALMSPRDELUnidadMedidaProducto(?)}";
                            PreparedStatement sentenciaEq=conexion.prepareCall(sqlEq);
                            sentenciaEq.setLong(1,oCEUnidadMedidaProducto.getIdUnidadMedidaProducto());
                            sentenciaEq.execute();
                       }
                    }
               }
               conexion.commit();
               return true;
            }
        catch (Exception e)
            {
            e.printStackTrace();
            try
            {
                conexion.rollback();
                return false;
            }
            catch (SQLException ex)
            {
                Logger.getLogger(CDProducto.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
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
        public static boolean actualizarTodoProducto(CEProducto oCEProducto,List<CLEquivalenciaABM> oLstEquivalencia)
    {
        Connection conexion = ConexionBD.obtenerConexion();
         try
         {
               String sql="{call ALMSPRUPDTodoProducto(?,?,?,?,?,?,?,?,?,?,?)}";
               conexion.setAutoCommit(false);
               CallableStatement sentencia=conexion.prepareCall(sql);
               sentencia.setInt(1,oCEProducto.getIdUnidadBase());
               sentencia.setString(2,oCEProducto.getDescripcion());
               sentencia.setInt(3,oCEProducto.getIdMarca());
               sentencia.setBoolean(4,oCEProducto.isSiNoImpuesto());
               sentencia.setLong(5,oCEProducto.getIdProducto());
               sentencia.setInt(6,oCEProducto.getIdUnidadPresentacionVenta());
               sentencia.setInt(7,oCEProducto.getIdSubFamilia());
               sentencia.setInt(8,oCEProducto.getIdFamilia());
               sentencia.setInt(9,oCEProducto.getIdCategoria());
               sentencia.setInt(10,oCEProducto.getIdRubro());
               sentencia.setBoolean(11,oCEProducto.isPercepcion());
               sentencia.execute();
               for(CLEquivalenciaABM oCLEquivalencia:oLstEquivalencia)
               {
                    if(oCLEquivalencia.getAbm()==1)
                    {
                        String sqlEq="{call ALMSPRINSEquivalenciaProducto(?,?,?,?,?)}";
                        PreparedStatement sentenciaEq=conexion.prepareCall(sqlEq);
                        sentenciaEq.setLong(1,oCEProducto.getIdProducto());
                        sentenciaEq.setInt(2,oCLEquivalencia.getoCEEquivalencia().getIdUnidadBase());
                        sentenciaEq.setFloat(3,oCLEquivalencia.getoCEEquivalencia().getCantidadBase());
                        sentenciaEq.setDouble(4,oCLEquivalencia.getoCEEquivalencia().getIdUnidadPedido());
                        sentenciaEq.setFloat(5,oCLEquivalencia.getoCEEquivalencia().getCantidadPedido());
                        sentenciaEq.execute();
                    }
                    else
                    {
                       if(oCLEquivalencia.getAbm()==2)
                       {
                            String sqlEq="{call ALMSPRUPDEquivalenciaProducto(?,?,?,?,?)}";
                            PreparedStatement sentenciaEq=conexion.prepareCall(sqlEq);
                            sentenciaEq.setLong(1,oCLEquivalencia.getoCEEquivalencia().getIdEquivalenciaUnidad());
                            sentenciaEq.setInt(2,oCLEquivalencia.getoCEEquivalencia().getIdUnidadBase());
                            sentenciaEq.setFloat(3,oCLEquivalencia.getoCEEquivalencia().getCantidadBase());
                            sentenciaEq.setDouble(4,oCLEquivalencia.getoCEEquivalencia().getIdUnidadPedido());
                            sentenciaEq.setFloat(5,oCLEquivalencia.getoCEEquivalencia().getCantidadPedido());

                            sentenciaEq.execute();
                       }
                       else
                       {
                            String sqlEq="{call ALMSPRDELEquivalenciaProducto(?)}";
                            PreparedStatement sentenciaEq=conexion.prepareCall(sqlEq);
                            sentenciaEq.setLong(1,oCLEquivalencia.getoCEEquivalencia().getIdEquivalenciaUnidad());
                            sentenciaEq.execute();
                       }
                    }
               }
               for(CEUnidadMedidaProducto oCEUnidadMedidaProducto:oCEProducto.getoCEUnidadMedidaProducto())
               {
                    if(oCEUnidadMedidaProducto.getAbm()==1)
                    {
                         String sqlEq="{call ALMSPRINSUnidadMedidaProducto(?,?,?,?,?,?,?,?,?,?,?)}";
                        PreparedStatement sentenciaEq=conexion.prepareCall(sqlEq);
                        sentenciaEq.setInt(1,1);
                        sentenciaEq.setLong(2,oCEUnidadMedidaProducto.getIdUnidadMedidaProducto());
                        sentenciaEq.setLong(3,oCEProducto.getIdProducto());
                        sentenciaEq.setLong(4,oCEUnidadMedidaProducto.getIdUnidadMedidaVenta());
                        sentenciaEq.setInt(5,oCEUnidadMedidaProducto.getCorrelativo());
                        sentenciaEq.setInt(6,oCEUnidadMedidaProducto.getSiNoUnidadBase());
                        sentenciaEq.setFloat(7,oCEUnidadMedidaProducto.getEquivalenciaConBase());
                        sentenciaEq.setString(8,oCEUnidadMedidaProducto.getDescripcion());
                        sentenciaEq.setFloat(9,oCEUnidadMedidaProducto.getSiNoUnidadMasComercial());
                        sentenciaEq.setFloat(10,oCEUnidadMedidaProducto.getNumUnidades());
                        sentenciaEq.setInt(11,oCEProducto.getIdUnidadPresentacionVenta());
                        sentenciaEq.execute();
                    }
                    else
                    {
                       if(oCEUnidadMedidaProducto.getAbm()==2)
                       {
                            String sqlEq="{call ALMSPRUPDUnidadMedidaProducto(?,?,?,?,?,?,?,?,?,?,?)}";
                            PreparedStatement sentenciaEq=conexion.prepareCall(sqlEq);
                            sentenciaEq.setInt(1,1);
                            sentenciaEq.setLong(2,oCEUnidadMedidaProducto.getIdUnidadMedidaProducto());
                            sentenciaEq.setLong(3,oCEProducto.getIdProducto());
                            sentenciaEq.setLong(4,oCEUnidadMedidaProducto.getIdUnidadMedidaVenta());
                            sentenciaEq.setInt(5,oCEUnidadMedidaProducto.getCorrelativo());
                            sentenciaEq.setInt(6,oCEUnidadMedidaProducto.getSiNoUnidadBase());
                            sentenciaEq.setFloat(7,oCEUnidadMedidaProducto.getEquivalenciaConBase());
                            sentenciaEq.setString(8,oCEUnidadMedidaProducto.getDescripcion());
                            sentenciaEq.setFloat(9,oCEUnidadMedidaProducto.getSiNoUnidadMasComercial());
                            sentenciaEq.setFloat(10,oCEUnidadMedidaProducto.getNumUnidades());
                            sentenciaEq.setInt(11,oCEProducto.getIdUnidadPresentacionVenta());
                            sentenciaEq.execute();
                       }
                       else
                       {
                            String sqlEq="{call ALMSPRDELUnidadMedidaProducto(?)}";
                            PreparedStatement sentenciaEq=conexion.prepareCall(sqlEq);
                            sentenciaEq.setLong(1,oCEUnidadMedidaProducto.getIdUnidadMedidaProducto());
                            sentenciaEq.execute();
                       }
                    }
               }
               conexion.commit();
               return true;
            }
        catch (Exception e)
            {
            e.printStackTrace();
            try
            {
                conexion.rollback();
                return false;
            }
            catch (SQLException ex)
            {
                Logger.getLogger(CDProducto.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
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
     public static boolean eliminarProducto(long IdProducto)
        {
           Connection conexion;
           conexion = ConexionBD.obtenerConexion();
            try
              {
                CallableStatement sentencia;
                conexion.setAutoCommit(false);
                conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

                String sql= "{call ALMSPRDELProducto(?)}";
                sentencia = conexion.prepareCall(sql);
                sentencia.setLong(1,IdProducto);
                sentencia.executeUpdate();
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
                  catch(SQLException sqlExcep)
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
              catch(SQLException e)
                {
                  System.out.print(e);
                }
            }
      }    
    public static List<CEProducto>  consultarProductoxMarca(int IdMarca,int IdSubFamilia)
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try
         {
               List<CEProducto> oLstMarca = new ArrayList<CEProducto>();
               String sql="{call ALMSPRCNSProducto(?,?,?,?,?)}";
               PreparedStatement sentencia=conexion.prepareCall(sql);
               sentencia.setInt(1,1);
               sentencia.setInt(2,IdMarca);
               sentencia.setInt(3,IdSubFamilia);
               sentencia.setInt(4,0);
               sentencia.setString(5,"");
               ResultSet rs=sentencia.executeQuery();
               if (rs!=null)
                {
                while(rs.next())
                {
                    CEProducto oCEProducto=new CEProducto();
                    oCEProducto.setIdProducto(rs.getLong(1));
                    oCEProducto.setDescripcion(rs.getString(2));
                    oCEProducto.setCodigo(rs.getString(3));
                    oCEProducto.setSiNoImpuesto(rs.getBoolean(4));
                    oCEProducto.setIdUnidadBase(rs.getInt(5));
                    oCEProducto.setIdUnidadPresentacionVenta(rs.getInt(6));
                    oCEProducto.setIdMarca(IdMarca);
                    oLstMarca.add(oCEProducto);
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
     public static List<CEProducto>  consultarListadoDeProductos(int pCodigo,String parametro)
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try
         {
               List<CEProducto> oLstMarca = new ArrayList<CEProducto>();
               String sql="{call ALMSPRCNSProducto(?,?,?,?,?)}";
               PreparedStatement sentencia=conexion.prepareCall(sql);
               sentencia.setInt(1,pCodigo);
               sentencia.setInt(2,0);
               sentencia.setInt(3,0);
               sentencia.setInt(4,0);
               sentencia.setString(5,parametro);
               ResultSet rs=sentencia.executeQuery();
               if (rs!=null)
                {
                    while(rs.next())
                    {
                        CEProducto oCEProducto=new CEProducto();
                        oCEProducto.setIdProducto(rs.getLong(1));
                        oCEProducto.setCodigoBarra(rs.getString(2));
                        oCEProducto.setCodigo(rs.getString(3));
                        oCEProducto.setDescripcion(rs.getString(4));
                        oCEProducto.setMarca(rs.getString(5));
                        oCEProducto.setUMB(rs.getString(6));
                        oCEProducto.setUMP(rs.getString(7));
                        oCEProducto.setSubfamilia(rs.getString(8));
                        oCEProducto.setFamilia(rs.getString(9));
                        oCEProducto.setCategoria(rs.getString(10));
                        oCEProducto.setRubro(rs.getString(11));
                        oCEProducto.setSiNoImpuesto(rs.getBoolean(12));
                        oCEProducto.setIdMarca(rs.getInt(13));
                        oCEProducto.setIdSubFamilia(rs.getInt(14));
                        oCEProducto.setIdFamilia(rs.getInt(15));
                        oCEProducto.setIdCategoria(rs.getInt(16));
                        oCEProducto.setIdRubro(rs.getInt(17));
                        oCEProducto.setIdUnidadBase(rs.getInt(18));
                        oCEProducto.setIdUnidadPresentacionVenta(rs.getInt(19));
                        oCEProducto.setPrecio(rs.getFloat(20));
                        oCEProducto.setPercepcion(rs.getBoolean(21));
//                        oCEProducto.setIdUnidadMedida(rs.getInt(21));
                        oLstMarca.add(oCEProducto);
                    }
                        return oLstMarca;
                }
               return oLstMarca;
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
    public static List<CEEquivalenciaUnidad>  consultarEquivalenciaProducto(long IdProducto)
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try
         {
               List<CEEquivalenciaUnidad> oLstMarca = new ArrayList<CEEquivalenciaUnidad>();
               String sql="{call ALMSPRCNSProducto(?,?,?,?,?)}";
               PreparedStatement sentencia=conexion.prepareCall(sql);
               sentencia.setInt(1,2);
               sentencia.setInt(2,0);
               sentencia.setInt(3,0);
               sentencia.setLong(4,IdProducto);
               sentencia.setString(5,"");
               ResultSet rs=sentencia.executeQuery();
               if (rs!=null)
                {
                while(rs.next())
                {
                    CEEquivalenciaUnidad oCEEquivalencia=new CEEquivalenciaUnidad();
                    oCEEquivalencia.setIdUnidadBase(rs.getInt(1));
                    oCEEquivalencia.setCantidadBase(rs.getFloat(2));
                    oCEEquivalencia.setIdUnidadPedido(rs.getInt(3));
                    oCEEquivalencia.setCantidadPedido(rs.getFloat(4));
                    oCEEquivalencia.setIdEquivalenciaUnidad(rs.getLong(5));
                    oLstMarca.add(oCEEquivalencia);
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
      public static List<CEUnidadMedidaProducto>  consultarUnidadMedidaProducto(long IdProducto)
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try
         {
               List<CEUnidadMedidaProducto> oLstMarca = new ArrayList<CEUnidadMedidaProducto>();
               String sql="{call ALMSPRCNSUnidadMedidaProducto(?,?)}";
               PreparedStatement sentencia=conexion.prepareCall(sql);
               sentencia.setInt(1,1);
               sentencia.setLong(2,IdProducto);
               ResultSet rs=sentencia.executeQuery();
               if (rs!=null)
                {
                while(rs.next())
                {
                    CEUnidadMedidaProducto oCEEquivalencia=new CEUnidadMedidaProducto();
                    oCEEquivalencia.setDescripcion(rs.getString(2));
                    oCEEquivalencia.setTipoUnidad(rs.getString(1));
                    oCEEquivalencia.setEquivalenciaConBase(rs.getFloat(3));
                    oCEEquivalencia.setSiNoUnidadMasComercial(rs.getInt(4));
                    oCEEquivalencia.setIdUnidadMedidaProducto(rs.getLong(5));
                    oCEEquivalencia.setIdProducto(rs.getLong(6));
                    oCEEquivalencia.setNumUnidades(rs.getLong(7));
                    oCEEquivalencia.setIdUnidadMedidaVenta(rs.getInt(8));
                    oCEEquivalencia.setSiNoUnidadBase(rs.getInt(9));
                    oLstMarca.add(oCEEquivalencia);
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
      public static List<CEProducto>  consultarProducto(String pDato,int popcion)
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try {
               List<CEProducto> ListaProducto = new ArrayList<CEProducto>();
               String sql="call ALMSPRCNSProductoPorNombre('"+pDato+"',"+popcion+")";
               Statement sentencia=conexion.createStatement();
               ResultSet rs=sentencia.executeQuery(sql);
               if (rs!=null)
                {
                while(rs.next())
                {
                    CEProducto oCEProducto=new CEProducto();
                    oCEProducto.setIdProducto(rs.getInt(1));
                    oCEProducto.setDescripcion(rs.getString(2));
                    oCEProducto.setCodigo(rs.getString(3));
                    oCEProducto.setSiNoImpuesto(rs.getBoolean(4));
                    oCEProducto.setPercepcion(rs.getBoolean(5));
                    ListaProducto.add(oCEProducto);
                }

                }
               return ListaProducto;
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
       public static List<ObjetoJerarquia>  consultarListaProductoPorIdFamiliaVenta(int codigo,int pIdRubro,int pIdCategoria,int pIdFamilia,int pIdSubfamilia,String pFecha1,String pFecha2)
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try {
               List<ObjetoJerarquia> oLstAnioSemestreAcademicoMatriz = new ArrayList<ObjetoJerarquia>();
               String sql="{call VTASPRRPTNumVentasProducto(?,?,?,?,?,?,?)}";
               PreparedStatement sentencia=conexion.prepareCall(sql);
               sentencia.setInt(1,codigo);
               sentencia.setInt(2,pIdRubro);
               sentencia.setInt(3,pIdCategoria);
               sentencia.setInt(4,pIdFamilia);
               sentencia.setInt(5,pIdSubfamilia);
               sentencia.setString(6,pFecha1);
               sentencia.setString(7,pFecha2);
               ResultSet rs=sentencia.executeQuery();
               if (rs!=null)
                {
                    while(rs.next())
                    {
                        ObjetoJerarquia oCECategoria=new ObjetoJerarquia();
                        oCECategoria.setIdProducto(rs.getInt(1));
                        oCECategoria.setIdRubro(pIdRubro);
                        oCECategoria.setIdCategoria(pIdCategoria);
                        oCECategoria.setIdFamilia(pIdFamilia);
                        oCECategoria.setIdSubfamilia(pIdSubfamilia);
                        oCECategoria.setDescripcion(rs.getString(2));
                        oCECategoria.setNumVentas(rs.getFloat(4));
                        oCECategoria.setCantidadVendida(rs.getFloat(5));
                        oCECategoria.setTipoObjeto("Producto");
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
