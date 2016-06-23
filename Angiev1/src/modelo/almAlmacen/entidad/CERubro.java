package modelo.almAlmacen.entidad;

public class CERubro {

    private int IdRubro;
    private String Codigo;
    private String Descripcion;
    private float NumVentas;
    private float CantidadVendida;

    public int getIdRubro()
    {
        return IdRubro;
    }

    public void setIdRubro(int IdRubro)
    {
        this.IdRubro = IdRubro;
    }

    public String getCodigo()
    {
        return Codigo;
    }

    public void setCodigo(String Codigo)
    {
        this.Codigo = Codigo;
    }

    public String getDescripcion()
    {
        return Descripcion;
    }


    public void setDescripcion(String nombre)
    {
        this.Descripcion = nombre;
    }

    @Override
    public String toString() {
        return Descripcion;
    }

    /**
     * @return the NumVentas
     */
    public float getNumVentas() {
        return NumVentas;
    }

    /**
     * @param NumVentas the NumVentas to set
     */
    public void setNumVentas(float NumVentas) {
        this.NumVentas = NumVentas;
    }

    /**
     * @return the CantidadVendida
     */
    public float getCantidadVendida() {
        return CantidadVendida;
    }

    /**
     * @param CantidadVendida the CantidadVendida to set
     */
    public void setCantidadVendida(float CantidadVendida) {
        this.CantidadVendida = CantidadVendida;
    }

 
}
