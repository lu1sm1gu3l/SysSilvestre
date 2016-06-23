package modelo.vtaVenta.entidad;


public class CEMoneda 
{
   private int IdMoneda;
   private String Descripcion;
   private String Abreviatura;
   private String toString;
    private float UltimoMontoVentaMN;
    private float UltimoMontoVentaDolar;


   public CEMoneda() 
   {
   }
    public String getDescripcion()
    {
        return Descripcion;
    }

    public void setDescripcion(String descripcion)
    {
        this.Descripcion = descripcion;
    }

    public int getId_moneda()
    {
        return IdMoneda;
    }

    public void setId_moneda(int id_moneda)
    {
        this.IdMoneda = id_moneda;
    }

    public String toString()
    {
        if(toString==null)
        {
         return Abreviatura;
        }
        return toString;
    }

    public String getAbreviatura()
    {
        return Abreviatura;
    }

    public void setAbreviatura(String abreviatura)
    {
        this.Abreviatura = abreviatura;
    }

    public float getUltimoMontoVentaDolar() {
        return UltimoMontoVentaDolar;
    }

    public void setUltimoMontoVentaDolar(float UltimoMontoVentaDolar) {
        this.UltimoMontoVentaDolar = UltimoMontoVentaDolar;
    }

    public float getUltimoMontoVentaMN() {
        return UltimoMontoVentaMN;
    }

    public void setUltimoMontoVentaMN(float UltimoMontoVentaMN) {
        this.UltimoMontoVentaMN = UltimoMontoVentaMN;
    }

    public String getToString() {
        return toString;
    }

    public void setToString(String toString) {
        this.toString = toString;
    }

}
