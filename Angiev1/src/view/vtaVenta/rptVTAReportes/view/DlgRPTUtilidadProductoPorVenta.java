package view.vtaVenta.rptVTAReportes.view;

import java.awt.CardLayout;
import java.net.URL;
import java.util.Vector;
import javax.swing.JOptionPane;
import modelo.almAlmacen.entidad.CEProducto;
import net.sf.jasperreports.swing.JRViewer;
import util.clases.grlGeneral.ConvertidorFecha;
import util.clases.grlGeneral.DialogoPadre;
import util.clases.reportes.CLReporte;
import util.clases.reportes.ClaseGestionDeReporte;
import util.clases.reportes.ClaseReporte;
import util.clases.reportes.ClaseReporte_Parametro;
import view.FrmSistemaMenu;

public class DlgRPTUtilidadProductoPorVenta extends DialogoPadre
{
    private  CEProducto oCEProducto;
 
    public DlgRPTUtilidadProductoPorVenta(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        LblCargando.setVisible(false);
        dtchAl.setDate(FrmSistemaMenu.FechaSistema);
        dtchDel.setDate(FrmSistemaMenu.FechaSistema);
        dtchAl.setMaxSelectableDate(FrmSistemaMenu.FechaSistema);
        dtchDel.setMaxSelectableDate(FrmSistemaMenu.FechaSistema);

    }
private void reportar(String url)
    {
    ClaseReporte oClaseReporte = new ClaseReporte();
    String oUrlC = null;
    CLReporte oCLReporte=new CLReporte();
    URL oUrlRC=getClass().getResource(url);
    oCLReporte.setUrlReport(oUrlRC.toString());
    oUrlC = oCLReporte.getUrlReport().replace('\'','/');
    if(oCLReporte!=null)
    {

        ClaseReporte_Parametro oClaseParameIni = new ClaseReporte_Parametro();
    oClaseParameIni.setNombre("pfehaini");
    ConvertidorFecha oCEConvertidorFecha= new ConvertidorFecha();
    oCEConvertidorFecha.setFecha(dtchDel.getCalendar());
    oClaseParameIni.setPParametro(oCEConvertidorFecha.getFechaYMD());

    ClaseReporte_Parametro oClaseParameDel = new ClaseReporte_Parametro();
    oCEConvertidorFecha.setFechaSimpleDMY();
    oClaseParameDel.setNombre("pdel");
    oClaseParameDel.setPParametro(oCEConvertidorFecha.getFechaSimpleDMY());

    ClaseReporte_Parametro oClaseParametroFin = new ClaseReporte_Parametro();
    oClaseParametroFin.setNombre("pfechafin");
    oCEConvertidorFecha.setFecha(dtchAl.getCalendar());
    oClaseParametroFin.setPParametro(oCEConvertidorFecha.getFechaYMD());

     ClaseReporte_Parametro oClaseParameAl = new ClaseReporte_Parametro();
    oCEConvertidorFecha.setFechaSimpleDMY();
    oClaseParameAl.setNombre("pal");
    oClaseParameAl.setPParametro(oCEConvertidorFecha.getFechaSimpleDMY());

     oClaseReporte.setUrl(oUrlC);


    ClaseReporte_Parametro oClaseParametroProducto = new ClaseReporte_Parametro();
    oClaseParametroProducto.setNombre("pproducto");
    oClaseParametroProducto.setPParametro(""+TxtProducto.getText());

    ClaseReporte_Parametro oClaseParametroOpcion = new ClaseReporte_Parametro();
    oClaseParametroOpcion.setNombre("popcion");
    oClaseParametroOpcion.setPParametro(""+opcion());



    Vector<ClaseReporte_Parametro> oLstParametro = new Vector<ClaseReporte_Parametro>();
    oLstParametro.add(oClaseParametroProducto);
    oLstParametro.add(oClaseParameIni);
    oLstParametro.add(oClaseParametroFin);
    oLstParametro.add(oClaseParametroOpcion);
    oLstParametro.add(oClaseParameAl);
    oLstParametro.add(oClaseParameDel);
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

private int opcion()
{
    if(RbnCU.isSelected())
        return 1;
    return 2;
}
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        BtnGenerar = new javax.swing.JButton();
        PnlMostrar = new javax.swing.JPanel();
        PnlCargando = new javax.swing.JPanel();
        LblCargando = new javax.swing.JLabel();
        PnlReporte = new javax.swing.JScrollPane();
        TxtProducto = new TextField.JTxtNinguno();
        label3 = new Label.Label();
        dtchAl = new com.toedter.calendar.JDateChooser("dd/MM/yyyy","##/##/####",'_');
        jLabel5 = new javax.swing.JLabel();
        dtchDel = new com.toedter.calendar.JDateChooser("dd/MM/yyyy","##/##/####",'_');
        jLabel4 = new javax.swing.JLabel();
        chkValores = new Opcion.CheckBox();
        RbnCU = new javax.swing.JRadioButton();
        RBCCompra = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reporte Utilidad de Producto");

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

        TxtProducto.setTamanio(200);
        TxtProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtProductoKeyPressed(evt);
            }
        });

        label3.setText("Producto :");

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel5.setForeground(new java.awt.Color(0, 0, 51));
        jLabel5.setText("AL :");

        dtchDel.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtchDelPropertyChange(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel4.setForeground(new java.awt.Color(0, 0, 51));
        jLabel4.setText("DEL :");

        chkValores.setText("Detalles de Venta");
        chkValores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkValoresActionPerformed(evt);
            }
        });

        buttonGroup1.add(RbnCU);
        RbnCU.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        RbnCU.setSelected(true);
        RbnCU.setText("Por C.U");

        buttonGroup1.add(RBCCompra);
        RBCCompra.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        RBCCompra.setText("Por Costo Compra");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PnlMostrar, javax.swing.GroupLayout.DEFAULT_SIZE, 1116, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dtchDel, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dtchAl, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(RbnCU)
                        .addGap(20, 20, 20)
                        .addComponent(RBCCompra, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(chkValores, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnGenerar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BtnGenerar)
                        .addComponent(RBCCompra)
                        .addComponent(chkValores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TxtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(RbnCU))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dtchDel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addComponent(dtchAl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PnlMostrar, javax.swing.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGenerarActionPerformed

        BtnGenerar.setEnabled(false);
        TxtProducto.setEnabled(false);
        dtchDel.setEnabled(false);
        dtchAl.setEnabled(false);
        chkValores.setEnabled(false);
        ((CardLayout)(PnlMostrar.getLayout())).show(PnlMostrar,"card3");
        LblCargando.setVisible(true);
        Runnable miRunnable = new Runnable() {
            public void run() {
                try {
                    
                        if(!chkValores.isSelected())
                        reportar("/view/vtaVenta/rptVTAReportes/VTARPTProductosVendidosPorComprob.jasper");
                        else
                            reportar("/view/vtaVenta/rptVTAReportes/VTARPTProductosVendidosPorComprobDetalles.jasper");
                    ((CardLayout)(PnlMostrar.getLayout())).show(PnlMostrar,"card2");
                    LblCargando.setVisible(false);
                    BtnGenerar.setEnabled(true);
                    dtchDel.setEnabled(true);
                    dtchAl.setEnabled(true);
                    TxtProducto.setEnabled(true);
                    chkValores.setEnabled(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Thread hilo = new Thread(miRunnable);
        hilo.start();

}//GEN-LAST:event_BtnGenerarActionPerformed

    private void TxtProductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtProductoKeyPressed

        if(evt.getKeyCode()==evt.VK_ENTER) {
           BtnGenerar.requestFocus();            
        }
    }//GEN-LAST:event_TxtProductoKeyPressed

    private void dtchDelPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtchDelPropertyChange
        if(evt.getPropertyName().equals("date")) {
            dtchAl.setMinSelectableDate(dtchDel.getDate());
        }
}//GEN-LAST:event_dtchDelPropertyChange

    private void chkValoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkValoresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkValoresActionPerformed
  
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnGenerar;
    private javax.swing.JLabel LblCargando;
    private javax.swing.JPanel PnlCargando;
    private javax.swing.JPanel PnlMostrar;
    private javax.swing.JScrollPane PnlReporte;
    private javax.swing.JRadioButton RBCCompra;
    private javax.swing.JRadioButton RbnCU;
    private TextField.JTxtNinguno TxtProducto;
    private javax.swing.ButtonGroup buttonGroup1;
    private Opcion.CheckBox chkValores;
    private com.toedter.calendar.JDateChooser dtchAl;
    private com.toedter.calendar.JDateChooser dtchDel;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private Label.Label label3;
    // End of variables declaration//GEN-END:variables

}
