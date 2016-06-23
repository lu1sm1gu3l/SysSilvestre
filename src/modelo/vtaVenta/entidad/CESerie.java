package modelo.vtaVenta.entidad;

public class CESerie
{
    private int IdSerie;
    private String Numero;
    private int IdTipoComprobante;
    private long UltimoNumeroGenerado;
    private String NumeroGeneradoString;
    private String UltimoNumeroGeneradoString;
    private String TipoComprobante;
    private String NombreImpresora;
    private int IdSucursal;
    private CEPuntoVenta oCEPuntoVenta;
    private int IdPuntoVentaSerie;
    private int accionabm;
    private float alto;
    private float ancho;
    private int ceroSerie;
    private int ceroCorrelativo;
    public String getNumeroGeneradoString() {
        return NumeroGeneradoString;
    }

    public void setNumeroGeneradoString(String NumeroGeneradoString) {
        this.NumeroGeneradoString = NumeroGeneradoString;
    }

    public String getUltimoNumeroGeneradoString() {
        return UltimoNumeroGeneradoString;
    }

    public void setUltimoNumeroGeneradoString(String UltimoNumeroGeneradoString) {
        this.UltimoNumeroGeneradoString = UltimoNumeroGeneradoString;
    }

    public int getIdSerie() {
        return IdSerie;
    }

    public void setIdSerie(int IdSerie) {
        this.IdSerie = IdSerie;
    }

    public int getIdTipoComprobante() {
        return IdTipoComprobante;
    }

    public void setIdTipoComprobante(int IdTipoComprobante) {
        this.IdTipoComprobante = IdTipoComprobante;
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String Numero) {
        this.Numero = Numero;
    }

    public long getUltimoNumeroGenerado() {
        return UltimoNumeroGenerado;
    }

    public void setUltimoNumeroGenerado(long UltimoNumeroGenerado) {
        this.UltimoNumeroGenerado = UltimoNumeroGenerado;
    }

    /**
     * @return the IdSucursal
     */
    public int getIdSucursal() {
        return IdSucursal;
    }

    /**
     * @param IdSucursal the IdSucursal to set
     */
    public void setIdSucursal(int IdSucursal) {
        this.IdSucursal = IdSucursal;
    }

    public CEPuntoVenta getoCEPuntoVenta() {
        return oCEPuntoVenta;
    }

    public void setoCEPuntoVenta(CEPuntoVenta oCEPuntoVenta) {
        this.oCEPuntoVenta = oCEPuntoVenta;
    }

    public int getIdPuntoVentaSerie() {
        return IdPuntoVentaSerie;
    }

    public void setIdPuntoVentaSerie(int IdPuntoVentaSerie) {
        this.IdPuntoVentaSerie = IdPuntoVentaSerie;
    }

    public int getAccionabm() {
        return accionabm;
    }

    public void setAccionabm(int accionabm) {
        this.accionabm = accionabm;
    }

    public String getNombreImpresora() {
        return NombreImpresora;
    }

    public void setNombreImpresora(String NombreImpresora) {
        this.NombreImpresora = NombreImpresora;
    }

    

    public String getTipoComprobante() {
        return TipoComprobante;
    }

   

    public void setTipoComprobante(String TipoComprobante) {
        this.TipoComprobante = TipoComprobante;
    }

    @Override
    public String toString() {
        return ""+Numero ;
    }

    public float getAlto() {
        return alto;
    }

    public void setAlto(float alto) {
        this.alto = alto;
    }

    public float getAncho() {
        return ancho;
    }

    public void setAncho(float ancho) {
        this.ancho = ancho;
    }

    public int getCeroCorrelativo() {
        return ceroCorrelativo;
    }

    public void setCeroCorrelativo(int ceroCorrelativo) {
        this.ceroCorrelativo = ceroCorrelativo;
    }

    public int getCeroSerie() {
        return ceroSerie;
    }

    public void setCeroSerie(int ceroSerie) {
        this.ceroSerie = ceroSerie;
    }


    
   
}
