package modelo.almAlmacen.entidad;

public class CEMarca
{
    private int IdMarca;
    private String Descripcion;

   
    public int getIdMarca()
    {
        return IdMarca;
    }

    public void setIdMarca(int IdMarca)
    {
        this.IdMarca = IdMarca;
    }

    public String getDescripcion()
    {
        return Descripcion;
    }

    public void setDescripcion(String Marca)
    {
        this.Descripcion = Marca;
    }

    @Override
    public String toString() {
        return  Descripcion;
    }

}
