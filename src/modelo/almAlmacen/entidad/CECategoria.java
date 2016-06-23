package modelo.almAlmacen.entidad;

public class CECategoria
{
    private int IdCategoria;
    private int IdRubro;
    private String Descripcion;
    private String Codigo;
    private float NumVentas;
    private float CantidadVendida;

    public int getIdCategoria() {
        return IdCategoria;
    }
    public void setIdCategoria(int IdCategoria) {
        this.IdCategoria = IdCategoria;
    }

    /**
     * @return the IdRubro
     */
    public int getIdRubro() {
        return IdRubro;
    }

    /**
     * @param IdRubro the IdRubro to set
     */
    public void setIdRubro(int IdRubro) {
        this.IdRubro = IdRubro;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    /**
     * @param Nombre the Nombre to set
     */
    public void setDescripcion(String Nombre) {
        this.Descripcion = Nombre;
    }

    @Override
    public String toString() {
        return Descripcion ;
    }

    /**
     * @return the Codigo
     */
    public String getCodigo() {
        return Codigo;
    }

    /**
     * @param Codigo the Codigo to set
     */
    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
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
