package modelo.almAlmacen.entidad;
import java.util.List;

public class CEIngresoProducto {




  private long IdIngresoProducto;
  private long IdProveedor;
  private int IdSucursal;
  private int IdAlmacen;
  private String Fecha;
  private String FechaStr;  
  private String NumeroDocumento;
  private String NumeroIngreso;
  private int IdTipoIngresoSalida;
  private int IdEmpleado;
  private int IdUsuario;
  private long IdComprobanteCompra;
  private int UltimoIdEstado;
  private int IdTipoComprobante;
  private String TipoComprobante;
  private String UltimoEstado;
  private String TipoIngreso;
  private String Proveedor;
  private String Empleado;
  private String Sucursal;
  private String RUC;
  private String Direccion;
  private String Observacion;
  private int IdEstadoOC;
  private float PrecioConDesc;
  private int IdGuiaRemision;
  private List<CEIngresoProductoDetalle> LstIngresoDetalle;

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public String getFechaStr() {
        return FechaStr;
    }

    public void setFechaStr(String FechaStr) {
        this.FechaStr = FechaStr;
    }

    public int getIdEmpleado() {
        return IdEmpleado;
    }

    public void setIdEmpleado(int IdEmpleado) {
        this.IdEmpleado = IdEmpleado;
    }

    public long getIdIngresoProducto() {
        return IdIngresoProducto;
    }

    public void setIdIngresoProducto(long IdIngresoProducto) {
        this.IdIngresoProducto = IdIngresoProducto;
    }

    public long getIdComprobanteCompra() {
        return IdComprobanteCompra;
    }

    public void setIdComprobanteCompra(long IdComprobanteCompra) {
        this.IdComprobanteCompra = IdComprobanteCompra;
    }

    public long getIdProveedor() {
        return IdProveedor;
    }

    public void setIdProveedor(long IdProveedor) {
        this.IdProveedor = IdProveedor;
    }

    public int getIdSucursal() {
        return IdSucursal;
    }

    public void setIdSucursal(int IdSucursal) {
        this.IdSucursal = IdSucursal;
    }

    public int getIdTipoIngresoSalida() {
        return IdTipoIngresoSalida;
    }

    public void setIdTipoIngresoSalida(int IdTipoIngresoSalida) {
        this.IdTipoIngresoSalida = IdTipoIngresoSalida;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public List<CEIngresoProductoDetalle> getLstIngresoDetalle() {
        return LstIngresoDetalle;
    }

    public void setLstIngresoDetalle(List<CEIngresoProductoDetalle> LstIngresoDetalle) {
        this.LstIngresoDetalle = LstIngresoDetalle;
    }

    public String getNumeroDocumento() {
        return NumeroDocumento;
    }

    public void setNumeroDocumento(String NumeroDocumento) {
        this.NumeroDocumento = NumeroDocumento;
    }

    public String getNumeroIngreso() {
        return NumeroIngreso;
    }

    public void setNumeroIngreso(String NumeroIngreso) {
        this.NumeroIngreso = NumeroIngreso;
    }

    public String getEmpleado() {
        return Empleado;
    }

    public void setEmpleado(String Empleado) {
        this.Empleado = Empleado;
    }

    public String getProveedor() {
        return Proveedor;
    }

    public void setProveedor(String Proveedor) {
        this.Proveedor = Proveedor;
    }

    public String getSucursal() {
        return Sucursal;
    }

    public void setSucursal(String Sucursal) {
        this.Sucursal = Sucursal;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getRUC() {
        return RUC;
    }

    public void setRUC(String RUC) {
        this.RUC = RUC;
    }

    public int getUltimoIdEstado() {
        return UltimoIdEstado;
    }

    public void setUltimoIdEstado(int UltimoIdEstado) {
        this.UltimoIdEstado = UltimoIdEstado;
    }

 

    public String getUltimoEstado() {
        return UltimoEstado;
    }

    public void setUltimoEstado(String UltimoEstado) {
        this.UltimoEstado = UltimoEstado;
    }

    public String getTipoIngreso() {
        return TipoIngreso;
    }

    public void setTipoIngreso(String TipoIngreso) {
        this.TipoIngreso = TipoIngreso;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String Observacion) {
        this.Observacion = Observacion;
    }

    public int getIdTipoComprobante() {
        return IdTipoComprobante;
    }

    public void setIdTipoComprobante(int IdTipoComprobante) {
        this.IdTipoComprobante = IdTipoComprobante;
    }

    public int getIdEstadoOC() {
        return IdEstadoOC;
    }

    public void setIdEstadoOC(int IdEstadoOC) {
        this.IdEstadoOC = IdEstadoOC;
    }

    public String getTipoComprobante() {
        return TipoComprobante;
    }

    public void setTipoComprobante(String TipoComprobante) {
        this.TipoComprobante = TipoComprobante;
    }

    public int getIdAlmacen() {
        return IdAlmacen;
    }

    public void setIdAlmacen(int IdAlmacen) {
        this.IdAlmacen = IdAlmacen;
    }

    public float getPrecioConDesc() {
        return PrecioConDesc;
    }

    public void setPrecioConDesc(float PrecioConDesc) {
        this.PrecioConDesc = PrecioConDesc;
    }

    public int getIdGuiaRemision() {
        return IdGuiaRemision;
    }

    public void setIdGuiaRemision(int IdGuiaRemision) {
        this.IdGuiaRemision = IdGuiaRemision;
    }

    @Override
    public String toString() {
        return this.Proveedor;
    }


}
