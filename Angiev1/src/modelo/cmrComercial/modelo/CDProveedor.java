package modelo.cmrComercial.modelo;

import controller.acceso.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.cmrComercial.entidad.CEProveedor;

 
public class CDProveedor {
   
  public static ArrayList<CEProveedor> ListaProveedor(int cns_id, int Estado,String DatosProveedor)
    {
        Connection cnn= ConexionBD.obtenerConexion();
        try
        {

            ArrayList<CEProveedor> oLista=new ArrayList<CEProveedor>();
                if(cnn!=null)
                {
                     CallableStatement cs= null;
                   cs = cnn.prepareCall("{CALL  CMRSPRCNSProveedor("+cns_id+",0,"+Estado +",'"+DatosProveedor+"%')}");
                     ResultSet rs=cs.executeQuery();

                              if(rs.next())
                              {
                               CEProveedor oCEProveedor;
                               do
                               {
                               oCEProveedor=new CEProveedor();
                               oCEProveedor.setIdProveedor(rs.getInt(1));
                               oCEProveedor.setEstadoStr(rs.getString(2));
                               oCEProveedor.setRazonSocial(rs.getString(3));
                               oCEProveedor.setDireccion(rs.getString(4));
                               oCEProveedor.setTelefono(rs.getString(5));
                               oCEProveedor.setRUC(rs.getString(6));
                               oCEProveedor.setCorreo(rs.getString(7));
                               oCEProveedor.setFax(rs.getString(8));
                               oLista.add(oCEProveedor);
                               }
                               while(rs.next());
                                  return  oLista;
                               }
                cnn.close();
                 }
             return oLista;
        }catch(SQLException ex)
        {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, "Error al cargar Proveedor ");
            return null;
        }  
    }
    public static int ABMProveedor(List<CEProveedor> oLista, int pABM)
    {
           Connection cnn= ConexionBD.obtenerConexion();
                    try
                    {
                        cnn.setAutoCommit(false);
                          CallableStatement cs= null;
                          int filasAfectadas=0;

                        if(cnn!=null)
                        {
                            int size= oLista.size();

                            CEProveedor oProveedor;
                            for(int i=0;i<size;i++)
                            {
                                oProveedor = new CEProveedor();
                                oProveedor=oLista.get(i);

                              cs = cnn.prepareCall("CALL CMRSPRABMProveedor(?,?,?,?,?,?,?,?,?,?)");
                                    cs.setInt(1, pABM);
                                    cs.setLong(2, oProveedor.getIdProveedor());                                    
                                    cs.setString(3, oProveedor.getRazonSocial());
                                    cs.setString(4, oProveedor.getDireccion());
                                    cs.setString(5, oProveedor.getTelefono());
                                    cs.setString(6, oProveedor.getRUC());
                                    cs.setInt(7,oProveedor.getIdSucursal());
                                    cs.setBoolean(8, true);
                                    cs.setString(9, oProveedor.getCorreo());
                                    cs.setString(10,oProveedor.getFax());
                                    filasAfectadas=  cs.executeUpdate();
                            }

                        }
                            cnn.commit();
                            cnn.close();
                            return filasAfectadas;
                    }catch(SQLException es)
                    {
                           try{
                              es.printStackTrace();
                                    System.out.println("Rollback en Seccion");
                                   cnn.rollback();
                               }catch(SQLException ex)
                              {
                              }
                            return 0;
                    }
    }
}
