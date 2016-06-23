package modelo.almAlmacen.entidad;
import java.util.Date;
import java.util.List;

public class CEGuiaRemisionRecibido {




  private int IdGuiaRemisionRecibido;
  private int IdProveedor;
  private int IdSucursal;
  private String Fecha;
  private String FechaStr;  
  private String NumeroDocumento;
  private int IdEmpleado;
  private int IdUsuario;
  private int UltimoIdEstado;
  private String UltimoEstado;
  private String Proveedor;
  private String Empleado;
  private String Sucursal;
  private String RUC;
  private String Placa;
  private String Chofer;
  private String Origen;
  private String Destino;
  private String Observacion;
  private String Direccion;
  private Date date;
  private List<CEGuiaRemisionRecibidoDetalle> LstGuiaDetalle;

    public String getChofer() {
        return Chofer;
    }

    public void setChofer(String Chofer) {
        this.Chofer = Chofer;
    }

    public String getDestino() {
        return Destino;
    }

    public void setDestino(String Destino) {
        this.Destino = Destino;
    }

    public int getIdGuiaRemisionRecibido() {
        return IdGuiaRemisionRecibido;
    }

    public void setIdGuiaRemisionRecibido(int IdGuiaRemisionRecibido) {
        this.IdGuiaRemisionRecibido = IdGuiaRemisionRecibido;
    }

    public String getOrigen() {
        return Origen;
    }

    public void setOrigen(String Origen) {
        this.Origen = Origen;
    }


    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String Placa) {
        this.Placa = Placa;
    }


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


    public int getIdProveedor() {
        return IdProveedor;
    }

    public void setIdProveedor(int IdProveedor) {
        this.IdProveedor = IdProveedor;
    }

    public int getIdSucursal() {
        return IdSucursal;
    }

    public void setIdSucursal(int IdSucursal) {
        this.IdSucursal = IdSucursal;
    }


    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public List<CEGuiaRemisionRecibidoDetalle> getLstGuiaDetalle() {
        return LstGuiaDetalle;
    }

    public void setLstGuiaDetalle(List<CEGuiaRemisionRecibidoDetalle> LstGuiaDetalle) {
        this.LstGuiaDetalle = LstGuiaDetalle;
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


    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String Observacion) {
        this.Observacion = Observacion;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    @Override
    public String toString() {
        return this.Proveedor;
    }


}
