/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.tsoTesoreria.entidad;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Luiggi
 */
public class CECuadreCajaDetalle {

    private long IdCuadreCajaDetalle;
    private long       IdCuadreCaja;
    private BigDecimal MontoEfectivoInicial;
    private BigDecimal MontoTotalTesoreria;
    private BigDecimal MontoTotalCuadre;
    private BigDecimal MontoTotalDiferencia;
    private int        IdMoneda;
    private String     Moneda;
    private List<CECuadreCajaTipoPago> LstCECuadreCajaTipoPago;

    public long getIdCuadreCaja() {
        return IdCuadreCaja;
    }

    public void setIdCuadreCaja(long IdCierreCajaDetalle) {
        this.IdCuadreCaja = IdCierreCajaDetalle;
    }

    public int getIdMoneda() {
        return IdMoneda;
    }

    public void setIdMoneda(int IdMoneda) {
        this.IdMoneda = IdMoneda;
    }

    public List<CECuadreCajaTipoPago> getLstCECuadreCajaTipoPago() {
        return LstCECuadreCajaTipoPago;
    }

    public void setLstCECuadreCajaTipoPago(List<CECuadreCajaTipoPago> LstCECuadreCajaTipoPago) {
        this.LstCECuadreCajaTipoPago = LstCECuadreCajaTipoPago;
    }

    public BigDecimal getMontoEfectivoInicial() {
        return MontoEfectivoInicial;
    }

    public void setMontoEfectivoInicial(BigDecimal MontoEfectivoInicial) {
        this.MontoEfectivoInicial = MontoEfectivoInicial;
    }

    public BigDecimal getMontoTotalCuadre() {
        return MontoTotalCuadre;
    }

    public void setMontoTotalCuadre(BigDecimal MontoTotalCuadre) {
        this.MontoTotalCuadre = MontoTotalCuadre;
    }

    public BigDecimal getMontoTotalDiferencia() {
        return MontoTotalDiferencia;
    }

    public void setMontoTotalDiferencia(BigDecimal MontoTotalDiferencia) {
        this.MontoTotalDiferencia = MontoTotalDiferencia;
    }

    public BigDecimal getMontoTotalTesoreria() {
        return MontoTotalTesoreria;
    }

    public void setMontoTotalTesoreria(BigDecimal MontoTotalTesoreria) {
        this.MontoTotalTesoreria = MontoTotalTesoreria;
    }

    public String getMoneda() {
        return Moneda;
    }

    public void setMoneda(String Moneda) {
        this.Moneda = Moneda;
    }

    public long getIdCuadreCajaDetalle() {
        return IdCuadreCajaDetalle;
    }

    public void setIdCuadreCajaDetalle(long IdCuadreCajaDetalle) {
        this.IdCuadreCajaDetalle = IdCuadreCajaDetalle;
    }

    @Override
    public String toString() {
        return this.Moneda;
    }

}
