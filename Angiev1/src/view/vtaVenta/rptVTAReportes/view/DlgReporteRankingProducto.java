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

public class DlgReporteRankingProducto extends DialogoPadre
{
 
    public DlgReporteRankingProducto(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        dChFecInicial.setDate(new Date());
        dChFecFinal.setDate(new Date());
     

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
        PnlReporte = new javax.swing.JScrollPane();
        PnlCargando = new javax.swing.JPanel();
        LblCargando = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        dChFecInicial = new com.toedter.calendar.JDateChooser("dd/MM/yyyy","##/##/####",'_');
        jLabel3 = new javax.swing.JLabel();
        dChFecFinal = new com.toedter.calendar.JDateChooser("dd/MM/yyyy","##/##/####",'_');
        ChBkDetalle = new javax.swing.JCheckBox();
        CbxTop = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        RbnCantidad = new Opcion.RadioButon();
        RbnMonto = new Opcion.RadioButon();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reporte Ranking de Productos");

        BtnGenerar.setText("Generar");
        BtnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGenerarActionPerformed(evt);
            }
        });

        PnlMostrar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        PnlMostrar.setLayout(new java.awt.CardLayout());
        PnlMostrar.add(PnlReporte, "card2");

        PnlCargando.setLayout(new java.awt.BorderLayout());

        LblCargando.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblCargando.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/retina_waiting.gif"))); // NOI18N
        PnlCargando.add(LblCargando, java.awt.BorderLayout.CENTER);

        PnlMostrar.add(PnlCargando, "card3");

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

        ChBkDetalle.setText("Detalle por Unidades de Medida");
        ChBkDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChBkDetalleActionPerformed(evt);
            }
        });

        CbxTop.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20", "50", "100", "Todos" }));

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel4.setForeground(new java.awt.Color(0, 0, 51));
        jLabel4.setText("TOP:");

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel5.setForeground(new java.awt.Color(0, 0, 51));
        jLabel5.setText("Ordenar :");

        buttonGroup1.add(RbnCantidad);
        RbnCantidad.setSelected(true);
        RbnCantidad.setText("Cantidad");

        buttonGroup1.add(RbnMonto);
        RbnMonto.setText("Monto");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PnlMostrar, javax.swing.GroupLayout.DEFAULT_SIZE, 937, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(dChFecInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(dChFecFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ChBkDetalle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CbxTop, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(RbnCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(RbnMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addComponent(BtnGenerar)
                        .addGap(18, 18, 18))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BtnGenerar)
                        .addComponent(ChBkDetalle)
                        .addComponent(CbxTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5)
                        .addComponent(RbnCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(RbnMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dChFecInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(dChFecFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PnlMostrar, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGenerarActionPerformed
        BtnGenerar.setEnabled(false);
        dChFecFinal.setEnabled(false);
        dChFecInicial.setEnabled(false);
        CbxTop.setEnabled(false);
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
                 
                                   oUrlRC = getClass().getResource("/view/vtaVenta/rptVTAReportes/VTARPTRankingProductomasVendido.jasper");
                                        
                                }else
                                {

                                    oUrlRC = getClass().getResource("/view/vtaVenta/rptVTAReportes/VTARPTRankingProductomasVendidoPorUnidades.jasper");
                                        
                                }
                                oCLReporte.setUrlReport(oUrlRC.toString());
                                oUrlC = oCLReporte.getUrlReport().replace('\'','/');
                                if(oCLReporte!=null)
                                {

                                    ClaseReporte_Parametro oparametrotop = new ClaseReporte_Parametro();
                                    oparametrotop.setNombre("ptop");
                                    oparametrotop.setPParametro(getTop());
                                    

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
                                oLstParametro.add(oparametrotop);
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
                    CbxTop.setEnabled(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Thread hilo = new Thread(miRunnable);
        hilo.start();
}//GEN-LAST:event_BtnGenerarActionPerformed

    private String getTop()
    {
        if(CbxTop.getSelectedIndex()==3)
            return "0";
        return CbxTop.getSelectedItem().toString();
    }
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnGenerar;
    private javax.swing.JComboBox CbxTop;
    private javax.swing.JCheckBox ChBkDetalle;
    private javax.swing.JLabel LblCargando;
    private javax.swing.JPanel PnlCargando;
    private javax.swing.JPanel PnlMostrar;
    private javax.swing.JScrollPane PnlReporte;
    private Opcion.RadioButon RbnCantidad;
    private Opcion.RadioButon RbnMonto;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser dChFecFinal;
    private com.toedter.calendar.JDateChooser dChFecInicial;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables

}
