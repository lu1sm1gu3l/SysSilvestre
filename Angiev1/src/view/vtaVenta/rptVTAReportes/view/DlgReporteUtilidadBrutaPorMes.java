package view.vtaVenta.rptVTAReportes.view;

import controller.vtaVenta.CCMoneda;
import controller.vtaVenta.CCSucursal;
import java.awt.CardLayout;
import java.net.URL;
import java.util.Vector;
import javax.swing.JOptionPane;
import modelo.vtaVenta.entidad.CEMoneda;
import modelo.vtaVenta.entidad.CESucursal;
import net.sf.jasperreports.swing.JRViewer;
import util.clases.grlGeneral.CLComboBox;
import util.clases.grlGeneral.DialogoPadre;
import util.clases.reportes.CLReporte;
import util.clases.reportes.ClaseGestionDeReporte;
import util.clases.reportes.ClaseReporte;
import util.clases.reportes.ClaseReporte_Parametro;

public class DlgReporteUtilidadBrutaPorMes extends DialogoPadre
{
 
    public DlgReporteUtilidadBrutaPorMes(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        CbxMoneda.setModel(CLComboBox.cargarCombo(CCMoneda.listaAll()));
        cbxsucursal.setModel(CLComboBox.cargarCombo(CCSucursal.getAll()));
        LblCargando.setVisible(false);

     

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

      CESucursal oCESucursal = (CESucursal)cbxsucursal.getSelectedItem();
      CEMoneda oCEMoneda= (CEMoneda)CbxMoneda.getSelectedItem();

    oClaseReporte.setUrl(oUrlC);
    ClaseReporte_Parametro oClaseParametroFechaInicial = new ClaseReporte_Parametro();
    oClaseParametroFechaInicial.setNombre("panio");
    String anio=""+ychAnio.getValue();
    oClaseParametroFechaInicial.setPParametro(""+anio);
    ClaseReporte_Parametro oClaseParametroFechaFinal = new ClaseReporte_Parametro();
    
     ClaseReporte_Parametro oClaseParametroIdSucursal = new ClaseReporte_Parametro();
    oClaseParametroIdSucursal.setNombre("pidsucursal");
    oClaseParametroIdSucursal.setPParametro(""+oCESucursal.getIdSucursal());

     ClaseReporte_Parametro oClaseParametroconcepto = new ClaseReporte_Parametro();
    oClaseParametroconcepto.setNombre("pidmoneda");
    oClaseParametroconcepto.setPParametro(""+oCEMoneda.getId_moneda());


    Vector<ClaseReporte_Parametro> oLstParametro = new Vector<ClaseReporte_Parametro>();
    oLstParametro.add(oClaseParametroFechaInicial);
    oLstParametro.add(oClaseParametroFechaFinal);
    oLstParametro.add(oClaseParametroIdSucursal);
    oLstParametro.add(oClaseParametroconcepto);

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
        cbxsucursal = new ComboBox.ComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        CbxMoneda = new ComboBox.ComboBox();
        ychAnio = new com.toedter.calendar.JYearChooser();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reporte Utilidad Por Mes");

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

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel5.setForeground(new java.awt.Color(0, 0, 51));
        jLabel5.setText("Suscursal :");

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel3.setForeground(new java.awt.Color(0, 0, 51));
        jLabel3.setText("Moneda :");

        jLabel6.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel6.setForeground(new java.awt.Color(0, 0, 51));
        jLabel6.setText("Año :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PnlMostrar, javax.swing.GroupLayout.DEFAULT_SIZE, 870, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ychAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxsucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CbxMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(152, 152, 152)
                        .addComponent(BtnGenerar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(ychAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel3)
                        .addComponent(CbxMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbxsucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addComponent(BtnGenerar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PnlMostrar, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGenerarActionPerformed

        
        BtnGenerar.setEnabled(false);
        CbxMoneda.setEnabled(false);
        cbxsucursal.setEnabled(false);
        ((CardLayout)(PnlMostrar.getLayout())).show(PnlMostrar,"card3");
        LblCargando.setVisible(true);
        Runnable miRunnable = new Runnable() {
            public void run() {
                try {
                    

                        reportar("/view/vtaVenta/rptVTAReportes/VTARPTUtilidadPorMes.jasper");

                    ((CardLayout)(PnlMostrar.getLayout())).show(PnlMostrar,"card2");
                    LblCargando.setVisible(false);
                    BtnGenerar.setEnabled(true);
                    CbxMoneda.setEnabled(true);
                    cbxsucursal.setEnabled(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Thread hilo = new Thread(miRunnable);
        hilo.start();
}//GEN-LAST:event_BtnGenerarActionPerformed
  
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnGenerar;
    private ComboBox.ComboBox CbxMoneda;
    private javax.swing.JLabel LblCargando;
    private javax.swing.JPanel PnlCargando;
    private javax.swing.JPanel PnlMostrar;
    private javax.swing.JScrollPane PnlReporte;
    private javax.swing.ButtonGroup buttonGroup1;
    private ComboBox.ComboBox cbxsucursal;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private com.toedter.calendar.JYearChooser ychAnio;
    // End of variables declaration//GEN-END:variables

}
