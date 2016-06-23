package util.clases.reportes;


import controller.acceso.ConexionBD;
import java.util.Map;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;

public class ClaseGestionDeReporte 
    {
                
       public JasperPrint ejecutarReporte(ClaseReporte oReporte)
            {  
                try
                    {
                        String master=null;
                        master=oReporte.getUrl();
                        Vector<ClaseReporte_Parametro> oParametro=oReporte.getoLstParametros();
                        JasperReport masterReport = null;
                        try 
                            {
                                masterReport = (JasperReport) JRLoader.loadObjectFromLocation(master);
                            } 
                        catch (JRException e) 
                            {
                                JOptionPane.showMessageDialog(null, "Error cargando el reporte1"+e.getMessage());
                            }
                        Map parametro = new HashMap();
                        if(oParametro!=null)
                        {
                             for(int i=0;i<oParametro.size();i++)
                            {
                            parametro.put(oParametro.get(i).getNombre(),oParametro.get(i).getPParametro());
                            }
                        }
                        JasperPrint jasperPrint=null;
                       // try
                          //  {
                         jasperPrint = JasperFillManager.fillReport(masterReport,parametro, ConexionBD.obtenerConexion());
                         //   }catch (JRException e)
                               // {
                              //      JOptionPane.showMessageDialog(null, "Error cargando el reporte2"+e.getMessage());
                              //  }

                        return       jasperPrint;

                      }
                catch (Exception j)
                    {

                            j.printStackTrace();
                            JOptionPane.showMessageDialog(null,"No se puede mostrar el reporte"+j.getMessage());
                            return null;
                    }
              }
        }
    
