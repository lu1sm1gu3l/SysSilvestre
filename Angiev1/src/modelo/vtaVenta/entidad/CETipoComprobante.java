package modelo.vtaVenta.entidad;

public class CETipoComprobante 
{
   public int IdTipoComprobante;
   public String Descripcion;

   public CETipoComprobante() 
   {
    
   }
    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getIdTipoComprobante() {
        return IdTipoComprobante;
    }

    public void setIdTipoComprobante(int IdTipoComprobante) {
        this.IdTipoComprobante = IdTipoComprobante;
    }

    public String toString()
    {
        return Descripcion;
    }
}
