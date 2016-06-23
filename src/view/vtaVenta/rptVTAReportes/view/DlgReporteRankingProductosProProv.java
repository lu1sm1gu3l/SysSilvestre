package view.vtaVenta.rptVTAReportes.view;

import java.awt.CardLayout;
import java.net.URL;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import net.sf.jasperreports.swing.JRViewer;
import util.clases.grlGeneral.ConvertidorFecha;
import util.clases.grlGeneral.DialogoPadre;
import util.clases.reportes.CLReporte;
import util.clases.reportes.ClaseGestionDeReporte;
import util.clases.reportes.ClaseReporte;
import util.clases.reportes.ClaseReporte_Parametro;
import view.cmrComercial.DlgGestionProveedor;

public class DlgReporteRankingProductosProProv extends DialogoPadre
{
 
    public DlgReporteRankingProductosProProv(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        dChFecInicial.setDate(new Date());
        dChFecFinal.setDate(new Date());
       LblCargando.setVisible(false);

    }
private void reportar(String fecha1,String fecha2,String p1,String p2,String url)
    {
    ClaseReporte oClaseReporte = new ClaseReporte();
    String oUrlC = null;
    CLReporte oCLReporte=new CLReporte();
    URL oUrlRC=getClass().getResource(url);
    oCLReporte.setUrlReport(oUrlRC.toString());
    oUrlC = oCLReporte.getUrlReport().replace('\'','/');
    if(oCLReporte!=null)
    {
    oClaseReporte.setUrl(oUrlC);
    ClaseReporte_Parametro oClaseParametroFechaExacta = new ClaseReporte_Parametro();
    oClaseParametroFechaExacta.setNombre(p1);
    oClaseParametroFechaExacta.setPParametro(fecha1);
     ClaseReporte_Parametro oClaseParametroIdSucursal = new ClaseReporte_Parametro();
    oClaseParametroIdSucursal.setNombre("pIdSucursal");
    oClaseParametroIdSucursal.setPParametro("1");
    ClaseReporte_Parametro oClaseParametroFechaExacta2 = new ClaseReporte_Parametro();
    oClaseParametroFechaExacta2.setNombre(p2);
    oClaseParametroFechaExacta2.setPParametro(fecha2);
    Vector<ClaseReporte_Parametro> oLstParametro = new Vector<ClaseReporte_Parametro>();
    oLstParametro.add(oClaseParametroFechaExacta);
    oLstParametro.add(oClaseParametroIdSucursal);
    oLstParametro.add(oClaseParametroFechaExacta2);
    oClaseReporte.setoLstParametros(oLstParametro);
    ClaseGestionDeReporte oGestionDeReporte = new ClaseGestionDeReporte();
    JRViewer oJRViewer=new JRViewer(oGestionDeReporte.ejecutarReporte(oClaseReporte));
    {
            PnlReporte.setViewportView(oJRViewer);
            oJRViewer.setVisible(true);
    }
    }
    else
    {
    JOptionPane.showMessageDialog(null,"El reporte no se encuentra");
    }


    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        BtnGenerar = new javax.swing.JButton();
        PnlMostrar = new javax.swing.JPanel();
        PnlCargando = new javax.swing.JPanel();
        LblCargando = new javax.swing.JLabel();
        PnlReporte = new javax.swing.JScrollPane();
        jLabel2 = new javax.swing.JLabel();
        dChFecInicial = new com.toedter.calendar.JDateChooser("dd/MM/yyyy","##/##/####",'_');
        jLabel3 = new javax.swing.JLabel();
        dChFecFinal = new com.toedter.calendar.JDateChooser("dd/MM/yyyy","##/##/####",'_');
        ChBkDetalle = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        RbnCantidad = new Opcion.RadioButon();
        RbnMonto = new Opcion.RadioButon();
        jLabel6 = new javax.swing.JLabel();
        TxtProveedor1 = new TextField.JTxtLetra();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reporte Ranking de Productos Por Provedor");

        BtnGenerar.setText("Generar");
        BtnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGenerarActionPerformed(evt);
            }
        });

        PnlMostrar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        PnlMostrar.setLayout(new java.awt.CardLayout());

        PnlCargando.setLayout(new java.awt.BorderLayout());

        LblCargando.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblCargando.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/retina_waiting.gif"))); // NOI18N
        PnlCargando.add(LblCargando, java.awt.BorderLayout.CENTER);

        PnlMostrar.add(PnlCargando, "card3");
        PnlMostrar.add(PnlReporte, "card2");

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel2.setForeground(new java.awt.Color(0, 0, 51));
        jLabel2.setText("DEL:");

        dChFecInicial.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dChFecInicialPropertyChange(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel3.setForeground(new java.awt.Color(0, 0, 51));
        jLabel3.setText("AL :");

        ChBkDetalle.setText("Detalle por Unidades");
        ChBkDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChBkDetalleActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel5.setForeground(new java.awt.Color(0, 0, 51));
        jLabel5.setText("Ordenar :");

        buttonGroup1.add(RbnCantidad);
        RbnCantidad.setSelected(true);
        RbnCantidad.setText("Cantidad");

        buttonGroup1.add(RbnMonto);
        RbnMonto.setText("Monto");

        jLabel6.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel6.setForeground(new java.awt.Color(0, 0, 51));
        jLabel6.setText("Proveedor :");

        TxtProveedor1.setFont(new java.awt.Font("Verdana", 0, 11));
        TxtProveedor1.setTamanio(150);
        TxtProveedor1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtProveedor1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PnlMostrar, javax.swing.GroupLayout.DEFAULT_SIZE, 1012, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dChFecInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dChFecFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ChBkDetalle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 260, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(RbnCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(RbnMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnGenerar)
                        .addGap(18, 18, 18))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(464, Short.MAX_VALUE)
                    .addComponent(TxtProveedor1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(317, 317, 317)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BtnGenerar)
                        .addComponent(jLabel5)
                        .addComponent(RbnCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(RbnMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ChBkDetalle)
                        .addComponent(jLabel6))
                    .addComponent(dChFecInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(dChFecFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PnlMostrar, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(TxtProveedor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(592, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGenerarActionPerformed
        BtnGenerar.setEnabled(false);
        dChFecFinal.setEnabled(false);
        dChFecInicial.setEnabled(false);
        RbnCantidad.setEnabled(false);
        RbnMonto.setEnabled(false);
        ChBkDetalle.setEnabled(false);
        ((CardLayout)(PnlMostrar.getLayout())).show(PnlMostrar,"card3");
        LblCargando.setVisible(true);
        Runnable miRunnable = new Runnable() {
            public void run() {
                try {
                    

                    
                                ClaseReporte oClaseReporte = new ClaseReporte();
                                String oUrlC = null;
                                CLReporte oCLReporte=new CLReporte();

                                URL oUrlRC=null;
                                if(!ChBkDetalle.isSelected()){
                 
                                   oUrlRC = getClass().getResource("/view/vtaVenta/rptVTAReportes/VTARPTRankingProductomasVendidoPorProv.jasper");
                                        
                                }else
                                {

                                    oUrlRC = getClass().getResource("/view/vtaVenta/rptVTAReportes/VTARPTRankingProductomasVendidoPorProvPorUnidades.jasper");
                                        
                                }
                                oCLReporte.setUrlReport(oUrlRC.toString());
                                oUrlC = oCLReporte.getUrlReport().replace('\'','/');
                                if(oCLReporte!=null)
                                {

                                    ClaseReporte_Parametro oparametroIdProv = new ClaseReporte_Parametro();
                                    oparametroIdProv.setNombre("pidproveedor");
                                    oparametroIdProv.setPParametro(idProveedor+"");
                                    

                                oClaseReporte.setUrl(oUrlC);
                                ClaseReporte_Parametro oClaseParametroFechaInicial = new ClaseReporte_Parametro();
                                oClaseParametroFechaInicial.setNombre("pfechainicial");
                                ConvertidorFecha oCEConvertidorFecha= new ConvertidorFecha();
                                oCEConvertidorFecha.setFecha(dChFecInicial.getCalendar());
                                oClaseParametroFechaInicial.setPParametro(oCEConvertidorFecha.getFechaYMD());

                                ClaseReporte_Parametro oClaseParametroFechaFinal = new ClaseReporte_Parametro();
                                oClaseParametroFechaFinal.setNombre("pfechafinal");
                                oCEConvertidorFecha= new ConvertidorFecha();
                                oCEConvertidorFecha.setFecha(dChFecFinal.getCalendar());
                                oClaseParametroFechaFinal.setPParametro(oCEConvertidorFecha.getFechaYMD());

                                ClaseReporte_Parametro oparametroOpcion = new ClaseReporte_Parametro();
                                    oparametroOpcion.setNombre("popcion");
                                    oparametroOpcion.setPParametro(opcion()+"");

                                Vector<ClaseReporte_Parametro> oLstParametro = new Vector<ClaseReporte_Parametro>();
                                oLstParametro.add(oClaseParametroFechaInicial);

                                  oLstParametro.add(oClaseParametroFechaFinal);
                                oLstParametro.add(oparametroIdProv);
                                oLstParametro.add(oparametroOpcion);
                                oClaseReporte.setoLstParametros(oLstParametro);


                                ClaseGestionDeReporte oGestionDeReporte = new ClaseGestionDeReporte();
                                JRViewer oJRViewer=new JRViewer(oGestionDeReporte.ejecutarReporte(oClaseReporte));

                                        PnlReporte.setViewportView(oJRViewer);
                                        oJRViewer.setVisible(true);

                                }
                                else
                                {
                                JOptionPane.showMessageDialog(null,"El reporte no se encuentra");
                                }


                                
                    
                    
                    ((CardLayout)(PnlMostrar.getLayout())).show(PnlMostrar,"card2");
                    LblCargando.setVisible(false);
                    BtnGenerar.setEnabled(true);
                    dChFecFinal.setEnabled(true);
                    dChFecInicial.setEnabled(true);
                    RbnCantidad.setEnabled(true);
                    RbnMonto.setEnabled(true);
                    ChBkDetalle.setEnabled(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Thread hilo = new Thread(miRunnable);
        hilo.start();
}//GEN-LAST:event_BtnGenerarActionPerformed

    
    private int opcion()
    {
        if(RbnCantidad.isSelected())
            return 1;
        if(RbnMonto.isSelected())
            return 2;
        return 0;
    }
    private void dChFecInicialPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dChFecInicialPropertyChange

        //  dChFecFinal.setDate(dChFecInicial.getDate());
        dChFecFinal.setMinSelectableDate(dChFecInicial.getDate());
}//GEN-LAST:event_dChFecInicialPropertyChange

    private void ChBkDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChBkDetalleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChBkDetalleActionPerformed

    private void TxtProveedor1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtProveedor1KeyPressed

         if(evt.getKeyCode()==evt.VK_ENTER) {
            buscarProveedor();

        }

    }//GEN-LAST:event_TxtProveedor1KeyPressed

private int idProveedor=0;
   private void buscarProveedor()
    {

                  DlgGestionProveedor odialogo= new DlgGestionProveedor(null, true,1,1,1);//consultar

                  odialogo.setCajaTexo(TxtProveedor1.getText());
                  odialogo.setVisible(true);
                  odialogo.setTitle("Busqueda de Proveedor");
                          if(odialogo.getoCEProveedor()!=null){
                            idProveedor=odialogo.getoCEProveedor().getIdProveedor();
                            TxtProveedor1.setText(odialogo.getoCEProveedor().getRazonSocial());
                            BtnGenerar.requestFocus();
                            }
      }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnGenerar;
    private javax.swing.JCheckBox ChBkDetalle;
    private javax.swing.JLabel LblCargando;
    private javax.swing.JPanel PnlCargando;
    private javax.swing.JPanel PnlMostrar;
    private javax.swing.JScrollPane PnlReporte;
    private Opcion.RadioButon RbnCantidad;
    private Opcion.RadioButon RbnMonto;
    private TextField.JTxtLetra TxtProveedor1;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser dChFecFinal;
    private com.toedter.calendar.JDateChooser dChFecInicial;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables

}
