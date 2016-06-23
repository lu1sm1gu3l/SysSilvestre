/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.almAlmacen.entidad;

import java.util.List;

/**
 *
 * @author Morales
 */
public class CETransferenciaAlmacen {

    private long IdTransferenciaAlmacen;
    private int IdAlmacenOrigen;
    private int IdAlmacenDestino;
    private String NumeroIngreso;
    private String NumeroSalida;
    private String NumeroTransferencia;
    private long IdIngresoProducto;
    private long IdSalidaProducto;
    private int IdSucursal;
    private int IdUsuario;
    private int IdEmpleado;
    private String Sucursal;
    private String Empleado;
    private String Usuario;
    private int UltimoIdEstado;
    private String UltimoEstado;
    private String Fecha;
    private String Observacion;
    private String AlmacenOrigen;
    private String AlmacenDestino;
    private List<CETransferenciaAlmacenDetalle> LstCETransferenciaAlmacenDetalle;


    public int getIdAlmacenDestino() {
        return IdAlmacenDestino;
    }

    public void setIdAlmacenDestino(int IdAlmacenDestino) {
        this.IdAlmacenDestino = IdAlmacenDestino;
    }

    public int getIdAlmacenOrigen() {
        return IdAlmacenOrigen;
    }

    public void setIdAlmacenOrigen(int IdAlmacenOrigen) {
        this.IdAlmacenOrigen = IdAlmacenOrigen;
    }

    public long getIdTransferenciaAlmacen() {
        return IdTransferenciaAlmacen;
    }

    public void setIdTransferenciaAlmacen(long IdTransferenciaAlmacen) {
        this.IdTransferenciaAlmacen = IdTransferenciaAlmacen;
    }

    public String getNumeroIngreso() {
        return NumeroIngreso;
    }

    public void setNumeroIngreso(String NumeroIngreso) {
        this.NumeroIngreso = NumeroIngreso;
    }

    public String getNumeroSalida() {
        return NumeroSalida;
    }

    public void setNumeroSalida(String NumeroSalida) {
        this.NumeroSalida = NumeroSalida;
    }

    public long getIdIngresoProducto() {
        return IdIngresoProducto;
    }

    public void setIdIngresoProducto(long IdIngresoProducto) {
        this.IdIngresoProducto = IdIngresoProducto;
    }

    public long getIdSalidaProducto() {
        return IdSalidaProducto;
    }

    public void setIdSalidaProducto(long IdSalidaProducto) {
        this.IdSalidaProducto = IdSalidaProducto;
    }

    public List<CETransferenciaAlmacenDetalle> getLstCETransferenciaAlmacenDetalle() {
        return LstCETransferenciaAlmacenDetalle;
    }

    public void setLstCETransferenciaAlmacenDetalle(List<CETransferenciaAlmacenDetalle> LstCETransferenciaAlmacenDetalle) {
        this.LstCETransferenciaAlmacenDetalle = LstCETransferenciaAlmacenDetalle;
    }

    public String getEmpleado() {
        return Empleado;
    }

    public void setEmpleado(String Empleado) {
        this.Empleado = Empleado;
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

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public String getSucursal() {
        return Sucursal;
    }

    public void setSucursal(String Sucursal) {
        this.Sucursal = Sucursal;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public String getUltimoEstado() {
        return UltimoEstado;
    }

    public void setUltimoEstado(String UltimoEstado) {
        this.UltimoEstado = UltimoEstado;
    }

    public int getUltimoIdEstado() {
        return UltimoIdEstado;
    }

    public void setUltimoIdEstado(int UltimoIdEstado) {
        this.UltimoIdEstado = UltimoIdEstado;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String Observacion) {
        this.Observacion = Observacion;
    }

    public String getNumeroTransferencia() {
        return NumeroTransferencia;
    }

    public void setNumeroTransferencia(String NumeroTransferencia) {
        this.NumeroTransferencia = NumeroTransferencia;
    }

    public String getAlmacenDestino() {
        return AlmacenDestino;
    }

    public void setAlmacenDestino(String AlmacenDestino) {
        this.AlmacenDestino = AlmacenDestino;
    }

    public String getAlmacenOrigen() {
        return AlmacenOrigen;
    }

    public void setAlmacenOrigen(String AlmacenOrigen) {
        this.AlmacenOrigen = AlmacenOrigen;
    }

    @Override
    public String toString() {
       return AlmacenOrigen;
    }



}
