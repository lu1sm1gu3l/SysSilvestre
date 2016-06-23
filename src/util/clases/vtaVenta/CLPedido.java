/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.clases.vtaVenta;

import controller.grlGeneral.CCValores;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import util.clases.grlGeneral.CLRedondear;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import modelo.almAlmacen.entidad.CEProductoPrecio;
import modelo.almAlmacen.entidad.CEUnidadMedidaProducto;
import modelo.vtaVenta.entidad.CEMoneda;
import modelo.vtaVenta.entidad.CEPedidoDetalle;
import modelo.vtaVenta.entidad.CETipoDescuento;
import util.clases.grlGeneral.VerificadorDeTxt;
import view.vtaVenta.DlgConsultarPrecio;
/**
 *
 * @author Luis Morales
 */
public class CLPedido 
{
    private JTable oTabla;
    private DefaultTableModel oModelo;
    private float SubtotalNeto;
    private float SubtotalBruto;
    private float descuentoEnSubtotal;
    private float descuento=0;
    private float TotalConDescuento;
    private float MontoExonerado;
    private Boolean conigv=true;
    private float Subtototalsinigv;
    private boolean actionPrecio=true;
    private float tipocambio=1;
    public int colItem=0;
    public int colCodigo=1;
    public int colProducto=2;
    public int colStock=3;
    public int colCantidad=4;
    public int colUnidadMedida=5;
    public int colprecio_sin_redon=6;
    public int colPrecio=7;
    public int colImporSinDesc=8;
    public int colTipoDescuento=9;
    public int colDescuento=10;
    public int colDestValores=11;
    public int colPrecioConDesc=12;
    public int colImporConDesc=13;
    public int colExonerado=14;
    public int colAlm=15;
    public int colListaPrecio=16;
    public int colisnPrecio=17;


    private float igvActual=0;
    private float TasaPercepcion=0;
    private JLabel lblsubtNeto;
    private JLabel lblIgv;
    private JLabel lblISC;
    private JLabel lblMontoTotal;
    private JLabel lblSubTotalNetoSinIgv;
    private JLabel LblDescuentoEquivalente;
    private JLabel LblSubtotalBruto;
    private JLabel LblDescuentoSubtotal;
    private JLabel LblDescuentoTotal;
    private JLabel lblMontoPercepcion;
    private JLabel LblMontoTotalPagar;
    private JTextField TxtDescuento;
    private JComboBox CbxTipoDescuentoPedido;
    private JCheckBox ChkPercepcion;

    private boolean calcular;

    public CLPedido(JTable oTabla,JLabel lblsubtSinDesc,
                    JLabel lblIgv,JLabel lblISC,JLabel lblMontoTotal,JLabel LblMontoTotalPagar,
                    JLabel lblSubTotalSinIgv,
                    JLabel LblDescuentoEquivalente,
                    JLabel LblSubtototalBruto,JLabel LblDescuentoSubtotal,JLabel LblDescuentoTotal,
                    JTextField txtDescuento,JComboBox CbxTipoDescuentoPedido,JLabel lblMontoPercepcion,JCheckBox ChkPercepcion)
    {
        this.oTabla = oTabla;
        cargarEventoTablaGrupos(oTabla);
        oModelo=(DefaultTableModel)oTabla.getModel();
        
        this.calcular=true;
        this.lblsubtNeto=lblsubtSinDesc;
        this.lblIgv=lblIgv;
        this.lblISC=lblISC;
        this.lblMontoTotal=lblMontoTotal;
        this.lblMontoPercepcion=lblMontoPercepcion;
        this.LblMontoTotalPagar=LblMontoTotalPagar;
        this.lblSubTotalNetoSinIgv=lblSubTotalSinIgv;
        this.LblDescuentoEquivalente=LblDescuentoEquivalente;
        this.LblSubtotalBruto=LblSubtototalBruto;
        this.LblDescuentoSubtotal=LblDescuentoSubtotal;
        this.LblDescuentoTotal=LblDescuentoTotal;
        this.TxtDescuento=txtDescuento;
        this.CbxTipoDescuentoPedido=CbxTipoDescuentoPedido;
        this.ChkPercepcion=ChkPercepcion;
        igvActual=CCValores.obenertIGVActual();
        TasaPercepcion=CCValores.obenertPercpcionActual();
        armarModelo();
        
      
    }
public CLPedido()
    {
        igvActual=CCValores.obenertIGVActual();
}

    public float getIgvActual() {
        return igvActual;
    }

    public float getTasaPercepcion() {
        return TasaPercepcion;
    }

    public Boolean getConigv() {
        return conigv;
    }

    public void setConigv(Boolean conigv) {
        this.conigv = conigv;
    }

    public float getMontoExonerado() {
        return MontoExonerado;
    }


private void calcularItem()
    {
    for(int i =0 ; i<oTabla.getRowCount();i++)
    {
        oTabla.setValueAt(i+1, i, colItem);
    }
}

    public boolean isActionPrecio() {
        return actionPrecio;
    }

    public void setActionPrecio(boolean actionPrecio) {
        this.actionPrecio = actionPrecio;
    }

    public DefaultTableModel getoModelo()
    {
        return oModelo;
    }


 public void setCalcular(boolean pcalcular)
 {
     calcular=pcalcular;
 }

 private void cargarEventoTablaGrupos(final JTable tbl)
    {
        ((DefaultTableModel)tbl.getModel()).addTableModelListener(
         new TableModelListener()
          {
            public void tableChanged(TableModelEvent tme)
              {
                  {
                      if(calcular)
                      eventoTablaGrupos(tme,tbl);

                  }
               }
          });

    }
    private void eventoTablaGrupos(TableModelEvent tme,JTable source)
    {
        try {
             int fil= oTabla.getSelectedRow();
             int col=oTabla.getSelectedColumn();
             if(tme.getColumn()==colCantidad||tme.getColumn()==colTipoDescuento||tme.getColumn()==colPrecio||tme.getColumn()==colDescuento)
              {

                CalcularCelda(fil, col);
             }
               CalcularSubtotales();
               eventoDescuento();
               calcularImportes();
             if(tme.getType()==tme.INSERT||tme.getType()==tme.DELETE)
             {
                 calcularItem();
             }
               
        }
        catch (Exception e)
            {
                System.out.println("eventoTablaGrupos - CLPedido : "+ e);

            }

    }
    private boolean sinodescuento=true;
    public void setSinoDescuento( boolean  sinoDesc)
    {
        this.sinodescuento=sinoDesc;
    }
    public void eventoDescuento()
    {
        try{
        if(calcular){
                if(sinodescuento){
                      if(TxtDescuento.getText().equals(""))
                        {
                        setDescuento(0);
                      LblDescuentoEquivalente.setText("0.00");
                        }else{

                          CETipoDescuento oDescuento=(CETipoDescuento)CbxTipoDescuentoPedido.getSelectedItem();
                          if(oDescuento!=null)
                          {
                                  float descuentoTemp=0;
                                 float descuentoaux=0;
                                  if(oDescuento.getIdTipoDescuento()==2)
                                  {
                                       descuentoTemp=((SubtotalNeto)*Float.parseFloat(TxtDescuento.getText()))/100;
                                  }
                                else{
                                    descuentoTemp=VerificadorDeTxt.convertFloat(TxtDescuento.getText());
                                    }
                                descuentoaux=VerificadorDeTxt.convertFloat(TxtDescuento.getText());
                                  if(Float.parseFloat(lblMontoTotal.getText())!=0){
                                          if(descuentoTemp<=Float.parseFloat(lblMontoTotal.getText())){
                                          LblDescuentoEquivalente.setText(""+CLRedondear.RedondearString(descuentoTemp,2));
                                          setDescuento(descuentoTemp);}
                                          else
                                                {
                                                JOptionPane.showMessageDialog(null,"El Descuento no puede ser mayor al Monto a pagar","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                                                TxtDescuento.setText("0.00");
                                                TxtDescuento.selectAll();
                                                LblDescuentoEquivalente.setText("0.00");
                                                }
                                    }else{
                                                if(descuentoaux!=0){
                                                JOptionPane.showMessageDialog(null,"El Monto Total es cero,No se Puede Realizar el Descuento","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                                                TxtDescuento.setText("0.00");
                                                TxtDescuento.selectAll();
                                                LblDescuentoEquivalente.setText("0.00");
                                                }

                                    }

                             }
                           }
                }
            }
        }
        catch (Exception e)
            {
                System.out.println("eventoDescuento - CLPedido : "+ e);
                JOptionPane.showMessageDialog(null,"error, actualize el sistema");

            }
    }

    private void armarModelo()
    {
        

    }
 
    public void setoModelo(DefaultTableModel oModelo) {
        this.oModelo = oModelo;
    }


    public void calcularEquivalenciaMoneda()
    {
        try{
            if(calcular){
                float precio=1;
                if(oModelo.getRowCount()>0){
                    for(int i=0;i<oModelo.getRowCount();i++)
                    {
                        if(oTabla.getValueAt(i, colPrecio)!=null){
                                precio=Float.parseFloat(oTabla.getValueAt(i, colprecio_sin_redon).toString());
                                float precio_final=(precio/tipoCambioAnterior)*tipocambio;
                                float cantidad=0;
                                if(oTabla.getValueAt(i, colCantidad)!=null)
                                {
                                    cantidad=Float.parseFloat(oTabla.getValueAt(i, colCantidad).toString());
                                }
                                oTabla.setValueAt(CLRedondear.RedondearString(precio_final,4), i,colPrecio );
                                oTabla.setValueAt(CLRedondear.RedondearString(precio,4), i,colprecio_sin_redon );
                                ajustarPrecio(precio_final, cantidad, i);
                                this.CalcularCelda(i, colPrecio);
                        }
                    }

                    this.CalcularSubtotales();
                    this.calcularImportes();
                    this.eventoDescuento();
                }
            }
        }
        catch (Exception e)
            {
                System.out.println("calcularEquivalenciaMoneda - CLPedido : "+ e);

            }
    }

    public float getTipoCambioAnterior() {
        return tipoCambioAnterior;
    }

    public void setTipoCambioAnterior(float tipoCambioAnterior) {
        this.tipoCambioAnterior = tipoCambioAnterior;
    }
    
    private int idMoneda=1, idMonedaAnterior=1;
    private float tipoCambioAnterior=1;
    public float ObtenerTipoCambioMoneda(JComboBox cbxMoneda)
    {
        CEMoneda oCEMoneda=(CEMoneda)cbxMoneda.getSelectedItem();
        if(oCEMoneda!=null)
        {
            idMonedaAnterior=idMoneda;
            tipoCambioAnterior=tipocambio;
            tipocambio= oCEMoneda.getUltimoMontoVentaMN();
            idMoneda=oCEMoneda.getId_moneda();
        }

        return tipocambio;
    }
    public void CalcularSubtotales()
    {
        if(calcular){

        SubtotalNeto=0;SubtotalBruto=0;descuentoEnSubtotal=0;Subtototalsinigv=0;montoTotalconigv=0;
        MontoExonerado=0;igvCalculado=0;

        if(oModelo.getRowCount()>0){
            for(int i=0;i<oModelo.getRowCount();i++)
            {
                float ImporteConDescuento=0,ImporteSinDescuento=0,DescuentoenImporte=0,imporSubtparaIvg=0;

                CEPedidoDetalle oCEPedidoDetalle=(CEPedidoDetalle)oTabla.getValueAt(i, colCodigo);
                if(oCEPedidoDetalle!=null){
                        if(oCEPedidoDetalle.isSinoImpuesto())
                        {
                            if(oModelo.getValueAt(i, colImporConDesc)!=null){
                                ImporteConDescuento=Float.parseFloat(oModelo.getValueAt(i, colImporConDesc).toString());
                            }
                        }
                        else
                        {
                             if(oModelo.getValueAt(i, colExonerado)!=null){
                             MontoExonerado=MontoExonerado+Float.parseFloat(oModelo.getValueAt(i, colExonerado).toString());
                             ImporteConDescuento=Float.parseFloat(oModelo.getValueAt(i, colExonerado).toString());
                             }
                        }
                    
                    if(oModelo.getValueAt(i, colImporSinDesc)!=null){
                        ImporteSinDescuento=Float.parseFloat(oModelo.getValueAt(i, colImporSinDesc).toString());
                        }

                    if(oTabla.getValueAt(i, colTipoDescuento)!=null){
                        CETipoDescuento oDescuento=(CETipoDescuento)oTabla.getValueAt(i, colTipoDescuento);

                        if(oDescuento!=null)
                        {
                         DescuentoenImporte=VerificadorDeTxt.convertFloat(oTabla.getValueAt(i, colDestValores));
                        }
                        if(oTabla.getValueAt(i, colImporConDesc)!=null)
                        {
                             imporSubtparaIvg=Float.parseFloat(oModelo.getValueAt(i, colImporConDesc).toString());
                        }

                    }
                }
                 
                SubtotalNeto=SubtotalNeto+ImporteConDescuento;
                SubtotalBruto=SubtotalBruto+ImporteSinDescuento;
                descuentoEnSubtotal=descuentoEnSubtotal+DescuentoenImporte;
                Subtototalsinigv=Subtototalsinigv+imporSubtparaIvg;


            }

            montoTotalconigv=Subtototalsinigv;
            Subtototalsinigv=montoTotalconigv/(1+igvActual);
        }
      }
    }

   private float  montoTotalconigv=0;
   private float  igvCalculado=0;
    public void calcularImportes()
    {
        if(calcular){
               this.lblsubtNeto.setText(CLRedondear.RedondearString(SubtotalNeto,2));
               this.lblMontoTotal.setText(CLRedondear.RedondearString(SubtotalNeto-descuento,2));
               TotalConDescuento=SubtotalNeto-descuento;
               this.LblSubtotalBruto.setText(CLRedondear.RedondearString(SubtotalBruto,2));
               this.LblDescuentoSubtotal.setText(CLRedondear.RedondearString(descuentoEnSubtotal,2));

                if(!conigv)
                {
                    this.lblIgv.setText("0.00");
                    this.lblISC.setText("0.00");
                    this.lblSubTotalNetoSinIgv.setText(CLRedondear.RedondearString(SubtotalNeto,2));
                    this.lblMontoTotal.setText(CLRedondear.RedondearString(TotalConDescuento,2));
                    this.LblDescuentoTotal.setText(CLRedondear.RedondearString(Float.parseFloat((CLRedondear.Redondear(descuentoEnSubtotal,2)+CLRedondear.Redondear(descuento,2))+""),2));

                   if(ChkPercepcion.isSelected()){
                         this.lblMontoPercepcion.setText(CLRedondear.RedondearString(Float.parseFloat(lblMontoTotal.getText())*TasaPercepcion,2));
                        }
                       else{
                            this.lblMontoPercepcion.setText("0.00");
                            }

                        this.LblMontoTotalPagar.setText(CLRedondear.RedondearString(Float.parseFloat(lblMontoTotal.getText())+Float.parseFloat(lblMontoPercepcion.getText()),2));


                }else{
                   igvCalculado=montoTotalconigv-Subtototalsinigv;
                this.lblIgv.setText((CLRedondear.RedondearString(igvCalculado,2)));
                this.lblISC.setText("0.00");
                this.lblSubTotalNetoSinIgv.setText(CLRedondear.RedondearString(SubtotalNeto-igvCalculado,2));
                this.lblMontoTotal.setText(CLRedondear.RedondearString(TotalConDescuento,2));
                this.LblDescuentoTotal.setText(CLRedondear.RedondearString(Float.parseFloat((CLRedondear.Redondear(descuentoEnSubtotal,2)+CLRedondear.Redondear(descuento,2))+""),2));
                if(ChkPercepcion.isSelected()){
                    this.lblMontoPercepcion.setText(CLRedondear.RedondearString(Float.parseFloat(lblMontoTotal.getText())*TasaPercepcion,2));
                }
               else{
                    this.lblMontoPercepcion.setText("0.00");
                    }

                this.LblMontoTotalPagar.setText(CLRedondear.RedondearString(Float.parseFloat(lblMontoTotal.getText())+Float.parseFloat(lblMontoPercepcion.getText()),2));
            }
        }
    }


  

    public void setDescuento(float descuento) {
        this.descuento = descuento;      
        calcularImportes();
    }
    public float getSubtotalNeto()
    {
        return Float.parseFloat(""+CLRedondear.Redondear(SubtotalNeto,2));
    }
    public float getDescuento()
    {

        float DescTemp=0;
        if(!TxtDescuento.getText().isEmpty())
        {
            DescTemp=Float.parseFloat(TxtDescuento.getText());
        }
        return Float.parseFloat(""+CLRedondear.Redondear(DescTemp,2));
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
               // else JOptionPane.showMessageDialog(null,"Seleccione una fila","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
    }
    public void QuitarFilaSel(int fila)
    {
                
                oModelo.removeRow(fila);


    }

    
public void CalcularCelda(int fil,int col)
    {

  try {
    if(fil!=-1){
    if(oTabla.getValueAt(fil, col)!=null){


        String valor=oTabla.getValueAt(fil, col).toString();
        if(valor.equals(""))
            {
            oTabla.setValueAt(""+0, fil,col);
            }
        
         if(col==colUnidadMedida||col==colCantidad||col==colTipoDescuento||col==colDescuento||col==colPrecio)
            {
             
            float precio=VerificadorDeTxt.convertFloat(oTabla.getValueAt(fil, colPrecio));
            float cantidad=VerificadorDeTxt.convertFloat(oTabla.getValueAt(fil, colCantidad));
            float descuentodet=VerificadorDeTxt.convertFloat(oTabla.getValueAt(fil, colDescuento));
            String importSinDescStr=CLRedondear.RedondearEnteroInmeditoSup(cantidad*precio);
            if(oTabla.getValueAt(fil, colTipoDescuento)!=null){
             CETipoDescuento oDescuento=(CETipoDescuento)oTabla.getValueAt(fil, colTipoDescuento);
                if(oDescuento!=null)
                     {
                        if(oDescuento.getIdTipoDescuento()==2)
                        {
                           descuentodet=Float.parseFloat(importSinDescStr)*descuentodet/100;
                        }
                      }

            }            
            oTabla.setValueAt(CLRedondear.RedondearString(descuentodet,2), fil,colDestValores );            
            descuentodet=Float.parseFloat(CLRedondear.RedondearString(descuentodet,2));
            if(oTabla.getValueAt(fil, colCodigo)!=null)
            {                                  
                 oTabla.setValueAt(importSinDescStr, fil, colImporSinDesc);//sin                 
                 
                CEPedidoDetalle oCEPedidoDetalle=(CEPedidoDetalle)oTabla.getValueAt(fil, colCodigo);
                String importConDes=CLRedondear.RedondearString(VerificadorDeTxt.convertFloat(importSinDescStr)-descuentodet,2);
                oTabla.setValueAt(CLRedondear.RedondearString(VerificadorDeTxt.convertFloat(importConDes)/cantidad,4), fil, colPrecioConDesc);
                if(oCEPedidoDetalle.isSinoImpuesto())
                {
                  oTabla.setValueAt(importConDes, fil, colImporConDesc);
                  oTabla.setValueAt(null, fil, colExonerado);
                }
                else{
                     oTabla.setValueAt(importConDes, fil, colExonerado);
                     oTabla.setValueAt(null, fil, colImporConDesc);
                }               
             }

           }
            else if(col==colCantidad)
            {
                 oTabla.setValueAt("0.00", fil, colImporSinDesc);
                 oTabla.setValueAt("0.00", fil, colExonerado);
                 oTabla.setValueAt("0.00", fil, colImporConDesc);
            }

        }                                  
           
      }
    }
    catch (Exception e)
        {
            System.out.println("CalcularCelda - CLPedido : "+ e);
            

        }
    }


public void calcularPrecio(int fila)
    {

    try{
    if(isActionPrecio()){
        boolean conPrecio=false;

       
        float cantidad=0;
        if(oTabla.getValueAt(fila, colCantidad)!=null){
           cantidad=Float.parseFloat(oTabla.getValueAt(fila, colCantidad).toString());
           }

        CEPedidoDetalle oCEPedidoDetalle= (CEPedidoDetalle)oTabla.getValueAt(fila, colCodigo);
         if(oCEPedidoDetalle!=null){
        List<CEProductoPrecio> olistaPrecio=(List<CEProductoPrecio>)oTabla.getValueAt(fila, colListaPrecio);
        CEUnidadMedidaProducto oCEUnidadMedidaProducto=(CEUnidadMedidaProducto)oTabla.getValueAt(fila, colUnidadMedida);
        if(cantidad!=0)
        {

            if(olistaPrecio!=null){
            for (CEProductoPrecio oCEProductoPrecioTemp : olistaPrecio)
            {
                if(oCEUnidadMedidaProducto.getIdUnidadMedidaVenta()==oCEProductoPrecioTemp.getIdUnidadMedidaVenta())
                {
                    float RangoInicial=oCEProductoPrecioTemp.getRangoInicial();
                    float RangoFinal=oCEProductoPrecioTemp.getRangoFinal();
                    if(cantidad>=RangoInicial&&cantidad<RangoFinal)
                    {
                    this.ajustarPrecio(oCEProductoPrecioTemp.getPrecioUnitario()*tipocambio, cantidad, fila);
                    conPrecio=true;
                    CalcularCelda(fila, colCantidad);
                    oTabla.setValueAt(false, fila, colisnPrecio);
                    break;
                    }
                }
            }
            }
        //    float precio=0;
//            if(!conPrecio)
//            {
//            boolean isSinPrecio=false;
//            if(oTabla.getValueAt(fila, colisnPrecio)!=null){
//                    isSinPrecio=Boolean.parseBoolean(oTabla.getValueAt(fila, colisnPrecio).toString());
//                }
//               // if(oTabla.getValueAt(fila, colPrecio)!=null){
//                 //  precio=Float.parseFloat(oTabla.getValueAt(fila, colPrecio).toString());
//                   if(isSinPrecio){
//                        conPrecio=true;}
//                //    }
//            }
            if(!conPrecio)
            {                    
                int rspt= JOptionPane.showConfirmDialog(null,"<html><center>La cantidad ingresada no tiene un precio establecido,<br> Desea Ingresar un precio</center></html>","Aviso",
                                            JOptionPane.YES_NO_OPTION);
                if(rspt==JOptionPane.OK_OPTION)
                {
                 DlgConsultarPrecio odialogo=new DlgConsultarPrecio(null, true,obtenerListaPorUnidad(),oCEPedidoDetalle.getProducto(),oCEUnidadMedidaProducto.getTipoUnidad(),1,true,0,cantidad);
                 odialogo.setTitle("Ingresar Precio");
                 odialogo.setLocationRelativeTo(null);
                 odialogo.setVisible(true);
                 float precioNuevo=odialogo.getPrecio();
                     if(precioNuevo!=0){
                    // oTabla.setValueAt(CLRedondear.RedondearString((float)precioNuevo,4), fila, colPrecio);
                       this.ajustarPrecio(precioNuevo, cantidad, fila);
                     oTabla.setValueAt(true, fila, colisnPrecio);
                     CalcularCelda(fila, colCantidad);
                     }else{
                           oTabla.setValueAt(0+"", fila, colPrecio);
                           oTabla.setValueAt(0+"", fila, colprecio_sin_redon);
                           oTabla.setValueAt(null, fila, colCantidad);
                           oTabla.setValueAt(null, fila, colDescuento);
                           oTabla.setValueAt(false, fila, colisnPrecio);
                           }
                }else{
                        oTabla.setValueAt(0+"", fila, colPrecio);
                        oTabla.setValueAt(0+"", fila, colprecio_sin_redon);
                        oTabla.setValueAt(null, fila, colCantidad);
                        oTabla.setValueAt(null, fila, colDescuento);
                        oTabla.setValueAt(false, fila, colisnPrecio);
                }
            }
        }
    }
   }
    actionPrecio=true;
    }
        catch (Exception e)
            {
                System.out.println("calcularPrecio - CLPedido : "+ e);

            }
}
public void ajustarPrecio(float precioNuevo,float cantidad,int fila)
{
    float monto=(float)precioNuevo*cantidad;
    String MontoStr=CLRedondear.RedondearEnteroInmeditoSup(monto);
    monto=VerificadorDeTxt.convertFloat(MontoStr);
    float precioAjustado=monto/cantidad;
    oTabla.setValueAt(CLRedondear.RedondearString(precioNuevo,4)+"", fila, colprecio_sin_redon);
    oTabla.setValueAt(CLRedondear.RedondearString(precioAjustado,4)+"", fila, colPrecio);
}
public List<CEProductoPrecio>  obtenerListaPorUnidad()
    {
            int fila=oTabla.getSelectedRow();

            List<CEProductoPrecio> olistaPrecioTemp= new ArrayList<CEProductoPrecio>();
            List<CEProductoPrecio> olistaPrecio=(List<CEProductoPrecio>)oTabla.getValueAt(fila, colListaPrecio);
            CEUnidadMedidaProducto oCEUnidadMedidaProducto=(CEUnidadMedidaProducto)oTabla.getValueAt(fila, colUnidadMedida);
            if(olistaPrecio!=null&&oCEUnidadMedidaProducto!=null){
            for (CEProductoPrecio oCEProductoPrecioTemp : olistaPrecio) {
                        if(oCEUnidadMedidaProducto.getIdUnidadMedidaVenta()==oCEProductoPrecioTemp.getIdUnidadMedidaVenta())
                        {
                            olistaPrecioTemp.add(oCEProductoPrecioTemp);
                        }

                 }
        }
           return olistaPrecioTemp;
    }
}
