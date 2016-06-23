package modelo.vtaVenta.entidad;


public class CEInstitucionFinanciera
{

    private int IdInstitucionFinanciera;
    private String Descripcion;

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getIdInstitucionFinanciera() {
        return IdInstitucionFinanciera;
    }

    public void setIdInstitucionFinanciera(int IdInstitucionFinanciera) {
        this.IdInstitucionFinanciera = IdInstitucionFinanciera;
    }

    public String toString()
    {
        return Descripcion;
    }
}
