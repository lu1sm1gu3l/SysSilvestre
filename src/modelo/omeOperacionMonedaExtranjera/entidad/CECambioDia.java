/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.omeOperacionMonedaExtranjera.entidad;

/**
 *
 * @author Luiggi
 */
public class CECambioDia {

   private long IdCambioDia;
   private int IdMoneda;
   private float MontoCompraMN;
   private float MontoVentaMN;
   private float MontoCompraDolar;
   private float MontoVentaDolar;
   private String Fecha;
   private int IdUsuario;
   private String moneda;

    public long getIdCambioDia() {
        return IdCambioDia;
    }

    public void setIdCambioDia(long IdTipoCambioDia) {
        this.IdCambioDia = IdTipoCambioDia;
    }
   

    public float getMontoCompraDolar() {
        return MontoCompraDolar;
    }

    public void setMontoCompraDolar(float MontoCompraDolar) {
        this.MontoCompraDolar = MontoCompraDolar;
    }

    public float getMontoCompraMN() {
        return MontoCompraMN;
    }

    public int getIdMoneda() {
        return IdMoneda;
    }

    public void setIdMoneda(int IdMoneda) {
        this.IdMoneda = IdMoneda;
    }

    public void setMontoCompraMN(float MontoCompraMN) {
        this.MontoCompraMN = MontoCompraMN;
    }

    public float getMontoVentaDolar() {
        return MontoVentaDolar;
    }

    public void setMontoVentaDolar(float MontoVentaDolar) {
        this.MontoVentaDolar = MontoVentaDolar;
    }

    public float getMontoVentaMN() {
        return MontoVentaMN;
    }

    public void setMontoVentaMN(float MontoVentaMN) {
        this.MontoVentaMN = MontoVentaMN;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    @Override
    public String toString() {
        return moneda;
    }


  

  
   

}
