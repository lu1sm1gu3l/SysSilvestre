package modelo.cmrComercial.modelo;

import controller.acceso.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.cmrComercial.entidad.CECliente;

 
public class CDCliente {
   

  public static ArrayList<CECliente> ListaCliente(int cns_id, int Estado,String DatosCliente)
    {
        Connection cnn= ConexionBD.obtenerConexion();
        try
        {

            ArrayList<CECliente> oLista=new ArrayList<CECliente>();
                if(cnn!=null)
                {
                     CallableStatement cs= null;
                   cs = cnn.prepareCall("{CALL  CMRSPRCNSCliente("+cns_id+",0,"+Estado +",'"+DatosCliente+"%')}");
                     ResultSet rs=cs.executeQuery();

                              if(rs.next())
                              {
                               CECliente oCECliente;
                               do
                               {
                               oCECliente=new CECliente();
                               oCECliente.setIdCliente(rs.getLong(1));
                               oCECliente.setCodigo(rs.getString(2));
                               oCECliente.setEstadoStr(rs.getString(3));
                               oCECliente.setApellidoPaterno(rs.getString(4));
                               oCECliente.setApellidoMaterno(rs.getString(5));
                               oCECliente.setNombres(rs.getString(6));
                               oCECliente.setSexo(rs.getString(7));
                               oCECliente.setDireccion(rs.getString(8));
                               oCECliente.setTelefono(rs.getString(9));
                               oCECliente.setRUC(rs.getString(10));
                               oCECliente.setDNI(rs.getString(11));
                               oCECliente.setCorreo(rs.getString(12));
                               oCECliente.setIdTipoPersona(rs.getInt(13));
                               oCECliente.setNombreCompleto(rs.getString(14));
                               oCECliente.setCelular(rs.getString(15));
                               oLista.add(oCECliente);
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
            JOptionPane.showMessageDialog(null, "Error al cargar Cliente ");
            return null;
        }  
    }
    public static int ABMCliente(List<CECliente> oLista, int pABM)
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

                            CECliente oCliente;
                            for(int i=0;i<size;i++)
                            {
                                oCliente = new CECliente();
                                oCliente=oLista.get(i);

                              cs = cnn.prepareCall("CALL CMRSPRABMCliente(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                                    cs.setInt(1, pABM);
                                    cs.setLong(2, oCliente.getIdCliente());
                                    cs.setString(3, oCliente.getCodigo());
                                    cs.setBoolean(4, true);
                                    cs.setString(5, oCliente.getApellidoPaterno());
                                    cs.setString(6, oCliente.getApellidoMaterno());
                                    cs.setString(7, oCliente.getNombres());
                                    cs.setString(8,oCliente.getSexo());
                                    cs.setString(9, oCliente.getDireccion());
                                    cs.setString(10, oCliente.getTelefono());
                                    cs.setString(11, oCliente.getRUC());
                                    cs.setString(12, oCliente.getDNI());
                                    cs.setString(13, oCliente.getCorreo());
                                    cs.setInt(14,oCliente.getIdTipoPersona());
                                    cs.setInt(15, oCliente.getIdSucursal());
                                    cs.setString(16,oCliente.getCelular());
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
