package modelo.vtaVenta.entidad;

public class CETipoPago
{
    private int IdTipoPago;
    private String Descripcion;

    
    public int getIdTipoPago() {
        return IdTipoPago;
    }

    public void setIdTipoPago(int IdTipoIngresoFinanciero) {
        this.IdTipoPago = IdTipoIngresoFinanciero;
    }

 
    public String getDescripcion() {
        return Descripcion;
    }

 
    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    @Override
    public String toString()
    {
        return  Descripcion ;
    }

}
