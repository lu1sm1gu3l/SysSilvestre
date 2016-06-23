package modelo.cmrComercial.modelo;

import controller.acceso.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.cmrComercial.entidad.CEEmpleado;

 
public class CDEmpleado {
   
  public static ArrayList<CEEmpleado> ListaEmpleado(int cns_id, int Estado,String DatosEmpleado)
    {
        Connection cnn= ConexionBD.obtenerConexion();
        try
        {

            ArrayList<CEEmpleado> oLista=new ArrayList<CEEmpleado>();
                if(cnn!=null)
                {
                     CallableStatement cs= null;
                   cs = cnn.prepareCall("{CALL  CMRSPRCNSEmpleado("+cns_id+",0,"+Estado +",'"+DatosEmpleado+"%')}");
                     ResultSet rs=cs.executeQuery();

                              if(rs.next())
                              {
                               CEEmpleado oCEEmpleado;
                               do
                               {
                               oCEEmpleado=new CEEmpleado();
                               oCEEmpleado.setIdEmpleado(rs.getInt(1));
                               oCEEmpleado.setCodigo(rs.getString(2));
                               oCEEmpleado.setEstadoStr(rs.getString(3));
                               oCEEmpleado.setApellidoPaterno(rs.getString(4));
                               oCEEmpleado.setApellidoMaterno(rs.getString(5));
                               oCEEmpleado.setNombres(rs.getString(6));
                               oCEEmpleado.setSexo(rs.getString(7));
                               oCEEmpleado.setDireccion(rs.getString(8));
                               oCEEmpleado.setTelefono(rs.getString(9));
                               oCEEmpleado.setDNI(rs.getString(10));
                               oCEEmpleado.setCorreo(rs.getString(11));
                               oCEEmpleado.setNombreCompleto(rs.getString(12));
                               oCEEmpleado.setCelular(rs.getString(13));
          //                    oCEEmpleado.setFechaNacimiento(rs.getString(15));
              //                oCEEmpleado.setFechaNacimientoDate(rs.getDate(15));
                               oLista.add(oCEEmpleado);
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
            JOptionPane.showMessageDialog(null, "Error al cargar Empleado ");
            return null;
        }  
    }
    public static int ABMEmpleado(List<CEEmpleado> oLista, int pABM)
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

                            CEEmpleado oEmpleado;
                            for(int i=0;i<size;i++)
                            {
                                oEmpleado = new CEEmpleado();
                                oEmpleado=oLista.get(i);

                              cs = cnn.prepareCall("CALL CMRSPRABMEmpleado(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                                    cs.setInt(1, pABM);
                                    cs.setLong(2, oEmpleado.getIdEmpleado());
                                    cs.setString(3, oEmpleado.getCodigo());
                                    cs.setBoolean(4, true);
                                    cs.setString(5, oEmpleado.getApellidoPaterno());
                                    cs.setString(6, oEmpleado.getApellidoMaterno());
                                    cs.setString(7, oEmpleado.getNombres());
                                    cs.setString(8,oEmpleado.getSexo());
                                    cs.setString(9, oEmpleado.getDireccion());
                                    cs.setString(10, oEmpleado.getTelefono());
                                    cs.setString(11, oEmpleado.getDNI());
                                    cs.setString(12, oEmpleado.getCorreo());
                                    cs.setInt(13,oEmpleado.getIdSucursal());
                                    cs.setString(14,oEmpleado.getCelular());
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
