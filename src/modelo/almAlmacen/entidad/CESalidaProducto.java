package modelo.almAlmacen.entidad;
import java.util.List;

public class CESalidaProducto {




 private long IdSalidaProducto;
  private long IdCliente;
  private int IdSucursal;
  private String Fecha;
  private String FechaStr;  
  private String NumeroDocumento;
  private String NumeroSalida;
  private int IdTipoIngresoSalida;
  private int IdAlmacen;
  private int IdEmpleado;
  private int IdUsuario;
  private long IdPedido;
  private int UltimoIdEstado;
  private String UltimoEstado;
  private String TipoSalida;
  private String Cliente;
  private String Empleado;
  private String Sucursal;
  private String DNI;
  private String Direccion;
  private String Observacion;
  private int IdTipoComprobante;
  private String TipoComprobante;
  private List<CESalidaProductoDetalle> LstSalidaDetalle;

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





    public String getNumeroDocumento() {
        return NumeroDocumento;
    }

    public void setNumeroDocumento(String NumeroDocumento) {
        this.NumeroDocumento = NumeroDocumento;
    }


    public String getEmpleado() {
        return Empleado;
    }

    public void setEmpleado(String Empleado) {
        this.Empleado = Empleado;
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

    public String getTipoSalida() {
        return TipoSalida;
    }

    public void setTipoSalida(String TipoSalida) {
        this.TipoSalida = TipoSalida;
    }


    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String Observacion) {
        this.Observacion = Observacion;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String Cliente) {
        this.Cliente = Cliente;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public long getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(long IdCliente) {
        this.IdCliente = IdCliente;
    }

    public long getIdPedido() {
        return IdPedido;
    }

    public void setIdPedido(long IdPedido) {
        this.IdPedido = IdPedido;
    }

    public long getIdSalidaProducto() {
        return IdSalidaProducto;
    }

    public void setIdSalidaProducto(long IdSalidaProducto) {
        this.IdSalidaProducto = IdSalidaProducto;
    }

    public String getNumeroSalida() {
        return NumeroSalida;
    }

    public void setNumeroSalida(String NumeroSalida) {
        this.NumeroSalida = NumeroSalida;
    }

    public List<CESalidaProductoDetalle> getLstSalidaDetalle() {
        return LstSalidaDetalle;
    }

    public void setLstSalidaDetalle(List<CESalidaProductoDetalle> LstSalidaDetalle) {
        this.LstSalidaDetalle = LstSalidaDetalle;
    }

    @Override
    public String toString() {
        return this.Cliente;
    }

    public int getIdTipoComprobante() {
        return IdTipoComprobante;
    }

    public void setIdTipoComprobante(int IdTipoComprobante) {
        this.IdTipoComprobante = IdTipoComprobante;
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




}
