/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.tsoTesoreria.entidad;

import java.math.BigDecimal;

/**
 *
 * @author Luiggi
 */
public class CECuadreCajaTipoPago {
private long IdCuadreCaja;
private long IdCuadreCajaDetalle;
private int IdMoneda;
private String Moneda;
private int IdTipoPago;
private String TipoPago;
private BigDecimal MontoTesoreria;
private BigDecimal MontoCuadre;
private BigDecimal MontoDiferencia;

    public long getIdCuadreCaja() {
        return IdCuadreCaja;
    }

    public void setIdCuadreCaja(long IdCuadreCaja) {
        this.IdCuadreCaja = IdCuadreCaja;
    }

    public long getIdCuadreCajaDetalle() {
        return IdCuadreCajaDetalle;
    }

    public void setIdCuadreCajaDetalle(long IdCuadreCajaDetalle) {
        this.IdCuadreCajaDetalle = IdCuadreCajaDetalle;
    }


    public int getIdMoneda() {
        return IdMoneda;
    }

    public void setIdMoneda(int IdMoneda) {
        this.IdMoneda = IdMoneda;
    }

    public int getIdTipoPago() {
        return IdTipoPago;
    }

    public void setIdTipoPago(int IdTipoPago) {
        this.IdTipoPago = IdTipoPago;
    }

    public BigDecimal getMontoCuadre() {
        return MontoCuadre;
    }

    public void setMontoCuadre(BigDecimal MontoCuadre) {
        this.MontoCuadre = MontoCuadre;
    }

    public BigDecimal getMontoDiferencia() {
        return MontoDiferencia;
    }

    public void setMontoDiferencia(BigDecimal MontoDiferencia) {
        this.MontoDiferencia = MontoDiferencia;
    }

    public BigDecimal getMontoTesoreria() {
        return MontoTesoreria;
    }

    public void setMontoTesoreria(BigDecimal MontoTesoreria) {
        this.MontoTesoreria = MontoTesoreria;
    }

    public String getMoneda() {
        return Moneda;
    }

    public void setMoneda(String Moneda) {
        this.Moneda = Moneda;
    }

    public String getTipoPago() {
        return TipoPago;
    }

    public void setTipoPago(String TipoPago) {
        this.TipoPago = TipoPago;
    }



    @Override
    public String toString() {
        return  TipoPago ;
    }



}
