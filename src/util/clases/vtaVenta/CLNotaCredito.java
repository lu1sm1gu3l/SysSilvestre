/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.clases.vtaVenta;


import controller.grlGeneral.CCValores;
import util.clases.grlGeneral.CLRedondear;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import modelo.vtaVenta.entidad.CEComprobanteVentaDetalle;
import util.clases.grlGeneral.VerificadorDeTxt;

/**
 *
 * @author Luis Morales
 */
public class CLNotaCredito
{
  private JTable oTabla;

  private DefaultTableModel oModelo;
    private float SubtotalNuevoSinIGV;
    private float IGV;
    private float ISC;
    private float TotalAcreditar;
    private float imporSubtparaIvg=0;
    private boolean conigv=true;
    private boolean calcular=true;
   

    
    public int colItem=0;
    public int colCodigo=1;
    public int colProducto=2;
    public int colStock=3;
    public int colPrecio=4;
    public int colUnidadMedida=5;
    public int colCantidad=6;
    public int colImporSinDesc=7;
    public int colTipoDescuento=8;
    public int colDescuento=9;
    public int colProporcion=10;
    public int colImporConDesc=11;
    public int colExonerado=12;
    public int colCantDevuelta=13;
    public int colPrecioNuevo=14;
    public int colImporNuevo=15;
    public int colCheck=16;


private float igvActual=0;
private float PercepcionActual=0;
private boolean conPercepcion;


private JLabel LblSubTotalSinIgvNuevo;
private JLabel LblMontoAcreditar;
private JLabel LblIgvNuevo;
private JLabel LblISCNuevo;
private JLabel LblMontoTotalAcreditar;
private JLabel LblMontoPercepcion;


    public CLNotaCredito()
    {
    }
   public CLNotaCredito(JTable oTabla,JLabel LblSubTotalSinIgvNuevo,
           JLabel LblMontoAcreditar,JLabel LblIgvNuevo,JLabel LblISCNuevo,
           JLabel LblMontoTotalAcreditar , JLabel LblMontoPercepcion)
    {
        this.oTabla = oTabla;
        cargarEventoTablaGrupos(oTabla);
        oModelo=(DefaultTableModel)oTabla.getModel();

        this.LblSubTotalSinIgvNuevo=LblSubTotalSinIgvNuevo;
        this.LblMontoAcreditar=LblMontoAcreditar;
        this.LblMontoTotalAcreditar=LblMontoTotalAcreditar;
        this.LblIgvNuevo=LblIgvNuevo;
        this.LblISCNuevo=LblISCNuevo;
        this.LblMontoPercepcion=LblMontoPercepcion;
        igvActual=CCValores.obenertIGVActual();
        PercepcionActual=CCValores.obenertPercpcionActual();
        this.calcular=true;
    }

    public Boolean getConigv() {
        return conigv;
    }

    public void setConigv(Boolean conigv) {
        this.conigv = conigv;
    }

    public void setCalcular(boolean calcular) {
        this.calcular = calcular;
    }

    public boolean isConPercepcion() {
        return conPercepcion;
    }

    public void setConPercepcion(boolean conPercepcion) {
        this.conPercepcion = conPercepcion;
    }

private void calcularItem()
    {
    for(int i =0 ; i<oTabla.getRowCount();i++)
    {
        oTabla.setValueAt(i+1, i, colItem);
    }
}
    public DefaultTableModel getoModelo()
    {
        return oModelo;
    }
 private void cargarEventoTablaGrupos(final JTable tbl)
    {
        ((DefaultTableModel)tbl.getModel()).addTableModelListener(
         new TableModelListener()
          {
            public void tableChanged(TableModelEvent tme)
              {
                  {
                      eventoTablaGrupos(tme,tbl);

                  }
               }
          });

    }
    private void eventoTablaGrupos(TableModelEvent tme,JTable source)
    {
        int fil= oTabla.getSelectedRow();
            int col=oTabla.getSelectedColumn();
         if(tme.getColumn()==colPrecioNuevo)
          {                           
            CalcularCeldaImporteNuevo(fil, col);
            CalcularSubtotal();
            calcularImportes();

         }
         if(tme.getColumn()==colImporNuevo)
          {
            CalcularCeldaPrecioNuevo(fil, col);
            CalcularSubtotal();
            calcularImportes();
         }

         if(tme.getColumn()==colCantDevuelta)
          {
            CalcularCeldaImporteNuevoPorCant(fil, col);
            CalcularSubtotal();
            calcularImportes();
         }
          
      
         if(tme.getType()==tme.INSERT||tme.getType()==tme.DELETE)
         {
          calcularItem();
         }
       
    }
   
    
    public void setoModelo(DefaultTableModel oModelo) {
        this.oModelo = oModelo;
    }
   

    public void CalcularSubtotal()
    {
        if(calcular){
        TotalAcreditar=0;
        imporSubtparaIvg=0;
        
        if(oModelo.getRowCount()>0){
            for(int i=0;i<oModelo.getRowCount();i++)
                {
                float totalSinDescTemp=0;
                float imporSubtparaIvgTemp=0;
                if(VerificadorDeTxt.convertBoolean(oModelo.getValueAt(i, colCheck))){
                if(oModelo.getValueAt(i, colImporNuevo)!=null){
                    totalSinDescTemp=Float.parseFloat(oModelo.getValueAt(i, colImporNuevo).toString());
                    }
                    TotalAcreditar=TotalAcreditar+totalSinDescTemp;
                        if(oTabla.getValueAt(i, colCodigo)!=null)
                            {
                            CEComprobanteVentaDetalle oCEComprobanteVentaDetalle=(CEComprobanteVentaDetalle)oTabla.getValueAt(i, colCodigo);
                                if(oCEComprobanteVentaDetalle.isSiNoImpuesto())
                                {
                                  if(oTabla.getValueAt(i, colImporNuevo)!=null)
                                    {
                                     imporSubtparaIvgTemp=Float.parseFloat(oModelo.getValueAt(i, colImporNuevo).toString());
                                    }
                                }
                            }

                    imporSubtparaIvg=imporSubtparaIvg+imporSubtparaIvgTemp;


                    }
              }
                    
              IGV=imporSubtparaIvg-(imporSubtparaIvg/(1+igvActual));
         }
      }
    }

    public void calcularImportes()
    {

        if(calcular){

            SubtotalNuevoSinIGV=TotalAcreditar-IGV;
            this.LblSubTotalSinIgvNuevo.setText(""+CLRedondear.Redondear(SubtotalNuevoSinIGV,2));
            this.LblIgvNuevo.setText(""+CLRedondear.Redondear(IGV,2));
            this.LblMontoAcreditar.setText(""+CLRedondear.Redondear(TotalAcreditar,2));
            if(conPercepcion){
            this.LblMontoPercepcion.setText(CLRedondear.RedondearString(Float.parseFloat(LblMontoAcreditar.getText())*PercepcionActual,2));
            }
            else
            {
                this.LblMontoPercepcion.setText("0.00");
            }
            this.LblMontoTotalAcreditar.setText(CLRedondear.RedondearString(Float.parseFloat(LblMontoAcreditar.getText())+Float.parseFloat(LblMontoPercepcion.getText()),2));

        }
  
    }


 public void limpiarTabla()
    {
        int filas=oModelo.getRowCount();

        if(filas>0)
            for(int i=0;i<filas;i++)
            {
                oModelo.removeRow(0);
            }
    }
    public void QuitarFila()
    {
                if(oTabla.getSelectedRow()!=-1){
                oModelo.removeRow(oTabla.getSelectedRow());
                }
                else JOptionPane.showMessageDialog(null,"Seleccione una fila","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
    }

    
public void CalcularCeldaImporteNuevo(int fil,int col)
{

  try {
      if(calcular){
       if(fil!=-1){
                if(oTabla.getValueAt(fil, col)!=null){
                    String valor=oTabla.getValueAt(fil, col).toString();
                    if(valor.equals(""))
                        {
                        oTabla.setValueAt(""+0, fil,col);
                        }
                    float precioNuevo=0,cantidadNueva=0,ImporNuevo=0;

                    cantidadNueva=VerificadorDeTxt.convertFloat(oTabla.getValueAt(fil, colCantDevuelta));
                     if(col==colPrecioNuevo)
                        {
                            precioNuevo=VerificadorDeTxt.convertFloat(oTabla.getValueAt(fil, colPrecioNuevo));
                            ImporNuevo=precioNuevo*cantidadNueva;
                            oTabla.setValueAt(CLRedondear.Redondear(ImporNuevo,2), fil, colImporNuevo);//con
                            return;
                        }
                       }

                    }
      }
    }
    catch(Exception e)
   {
        
    }
    }

public void CalcularCeldaImporteNuevoPorCant(int fila,int col)
    {
  try {

      if(calcular){
      if(fila!=-1){
            if(oTabla.getValueAt(fila, col)!=null){
                String valor=oTabla.getValueAt(fila, col).toString();
                        if(valor.equals(""))
                        {
                             oTabla.setValueAt(""+0, fila,col);
                        }
                        float precioNuevo=0;
                        float ImporNuevo=0;
                        float cantidadNueva=0;
                        cantidadNueva=VerificadorDeTxt.convertFloat(oTabla.getValueAt(fila, colCantDevuelta));

                 if(col==colCantDevuelta)
                    {
                    if(oTabla.getValueAt(fila, colPrecioNuevo)!=null){
                        precioNuevo=Float.parseFloat(oTabla.getValueAt(fila, colPrecioNuevo).toString());
                    }
                        ImporNuevo=precioNuevo*cantidadNueva;
                    oTabla.setValueAt(CLRedondear.Redondear((ImporNuevo),2), fila, colImporNuevo);//con

                    return;
                    }

                   }
         }
      }
    }
    catch(Exception e)
    {

    }
    }
    public float calcularPrecioNuevo(float cantidad,float importe)
    {
        return importe/cantidad;
    }
    public float calcularImporteNuevo(float cantidad, float precio)
    {
        return precio*cantidad;
    }
public void CalcularCeldaPrecioNuevo(int fil,int col)
    {
  try {
        if(calcular){
        if(fil!=-1){
            if(oTabla.getValueAt(fil, col)!=null){
                String valor=oTabla.getValueAt(fil, col).toString();
                if(valor.equals(""))
                    {
                    oTabla.setValueAt(""+0, fil,col);
                    }
                float precioNuevo=0; float ImporNuevo=0; float cantidad=0;
                    if(oTabla.getValueAt(fil, colCantDevuelta)!=null){
                        cantidad=Float.parseFloat(oTabla.getValueAt(fil, colCantDevuelta).toString());
                    }

                if(col==colImporNuevo)
                    {
                    if(oTabla.getValueAt(fil, colImporNuevo)!=null){
                        ImporNuevo=Float.parseFloat(oTabla.getValueAt(fil, colImporNuevo).toString());
                    }
                    precioNuevo=ImporNuevo/cantidad;
                    oTabla.setValueAt(CLRedondear.Redondear(precioNuevo,4), fil, colPrecioNuevo);//con

                    return;
                    }
            }
        }
      }
    }
    catch(Exception e)
            {

            }
    }
}
