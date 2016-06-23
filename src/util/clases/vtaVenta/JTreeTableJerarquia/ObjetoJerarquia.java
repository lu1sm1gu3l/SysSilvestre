package util.clases.vtaVenta.JTreeTableJerarquia;

public class ObjetoJerarquia
{

    private int IdRubro;
    private int IdCategoria;
    private int IdFamilia;
    private int IdSubfamilia;
    private int IdMarca;
    private int IdProducto;
    private String Descripcion;
    private float CantidadVendida;
    private float NumVentas;
    private String TipoObjeto;


    public float getCantidadVendida() {
        return CantidadVendida;
    }

    public void setCantidadVendida(float CantidadVendida) {
        this.CantidadVendida = CantidadVendida;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getIdCategoria() {
        return IdCategoria;
    }

    public void setIdCategoria(int IdCategoria) {
        this.IdCategoria = IdCategoria;
    }

    public int getIdFamilia() {
        return IdFamilia;
    }

    public void setIdFamilia(int IdFamilia) {
        this.IdFamilia = IdFamilia;
    }

    public int getIdMarca() {
        return IdMarca;
    }

    public void setIdMarca(int IdMarca) {
        this.IdMarca = IdMarca;
    }

    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int IdProducto) {
        this.IdProducto = IdProducto;
    }

    public int getIdRubro() {
        return IdRubro;
    }

    public void setIdRubro(int IdRubro) {
        this.IdRubro = IdRubro;
    }

    public int getIdSubfamilia() {
        return IdSubfamilia;
    }

    public void setIdSubfamilia(int IdSubfamilia) {
        this.IdSubfamilia = IdSubfamilia;
    }

    public float getNumVentas() {
        return NumVentas;
    }

    public void setNumVentas(float NumVentas) {
        this.NumVentas = NumVentas;
    }

    /**
     * @return the TipoObjeto
     */
    public String getTipoObjeto() {
        return TipoObjeto;
    }

    /**
     * @param TipoObjeto the TipoObjeto to set
     */
    public void setTipoObjeto(String TipoObjeto) {
        this.TipoObjeto = TipoObjeto;
    }

    @Override
    public String toString() {
        return Descripcion ;
    }

    public ObjetoJerarquia() {
        //this.TipoObjeto="Rubro";
    }

}
