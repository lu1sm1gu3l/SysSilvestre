

package util.clases.reportes;

import java.util.Vector;


public class ClaseReporte
{   
    private String url;
    private Vector<ClaseReporte_Parametro> oLstParametros;
   public ClaseReporte()
    {
    }   
    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    /**
     * @return the oLstParametros
     */
    public Vector<ClaseReporte_Parametro> getoLstParametros() {
        return oLstParametros;
    }

    /**
     * @param oLstParametros the oLstParametros to set
     */
    public void setoLstParametros(Vector<ClaseReporte_Parametro> oLstParametros) {
        this.oLstParametros = oLstParametros;
    }
   
    
}
