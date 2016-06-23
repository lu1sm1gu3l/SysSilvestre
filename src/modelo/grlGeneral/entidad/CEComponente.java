package modelo.grlGeneral.entidad;

public class CEComponente
{
 private int IdComponente;
 private String Nombre;
 private String Descripcion;

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getIdComponente() {
        return IdComponente;
    }

    public void setIdComponente(int IdComponente) {
        this.IdComponente = IdComponente;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String toString()
    {
        return this.Nombre;
    }
}
