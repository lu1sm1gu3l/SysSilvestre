package view.almAlmacen.invInventarios;

import controller.almAlmacen.CCAlmacen;
import java.awt.CardLayout;
import java.net.URL;
import java.util.Vector;
import javax.swing.JOptionPane;
import modelo.almAlmacen.entidad.CEAlmacen;
import net.sf.jasperreports.swing.JRViewer;
import util.clases.grlGeneral.CLComboBox;
import util.clases.grlGeneral.ConvertidorFecha;
import util.clases.grlGeneral.DialogoPadre;
import util.clases.reportes.CLReporte;
import util.clases.reportes.ClaseGestionDeReporte;
import util.clases.reportes.ClaseReporte;
import util.clases.reportes.ClaseReporte_Parametro;
import view.FrmSistemaMenu;

public class DlgRPTKardexProducto_Todos extends DialogoPadre
{

    java.awt.Frame parent;
    public DlgRPTKardexProducto_Todos(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        CbxAlmacen.setModel(CLComboBox.cargarCombo(CCAlmacen.listarAlmacenSucursal()));
        LblCargando.setVisible(false);
        dtchAl.setDate(FrmSistemaMenu.FechaSistema);
        dtchDel.setDate(FrmSistemaMenu.FechaSistema);
        this.parent=parent;

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

        oClaseReporte.setUrl(oUrlC);
        
        ClaseReporte_Parametro oClaseParameFechaIni = new ClaseReporte_Parametro();
    oClaseParameFechaIni.setNombre("pfechaini");
    ConvertidorFecha oCEConvertidorFecha= new ConvertidorFecha();
    oCEConvertidorFecha.setFecha(dtchDel.getCalendar());
    oClaseParameFechaIni.setPParametro(oCEConvertidorFecha.getFechaYMD());

    ClaseReporte_Parametro oClaseParameDel = new ClaseReporte_Parametro();
    oCEConvertidorFecha.setFechaSimpleDMY();
    oClaseParameDel.setNombre("pdel");
    oClaseParameDel.setPParametro(oCEConvertidorFecha.getFechaSimpleDMY());

    ClaseReporte_Parametro oClaseParametroFechaFin = new ClaseReporte_Parametro();
    oClaseParametroFechaFin.setNombre("pfechafin");
    oCEConvertidorFecha.setFecha(dtchAl.getCalendar());
    oClaseParametroFechaFin.setPParametro(oCEConvertidorFecha.getFechaYMD());


    ClaseReporte_Parametro oClaseParameAl = new ClaseReporte_Parametro();
    oCEConvertidorFecha.setFechaSimpleDMY();
    oClaseParameAl.setNombre("pal");
    oClaseParameAl.setPParametro(oCEConvertidorFecha.getFechaSimpleDMY());
    

    ClaseReporte_Parametro oClaseParametroIdAlmacen = new ClaseReporte_Parametro();
    oClaseParametroIdAlmacen.setNombre("p_idalmacen");
    oClaseParametroIdAlmacen.setPParametro(""+((CEAlmacen)CbxAlmacen.getSelectedItem()).getIdAlmacen());


    ClaseReporte_Parametro oClaseParametroAlmacen = new ClaseReporte_Parametro();
    oClaseParametroAlmacen.setNombre("pAlmacen");
    oClaseParametroAlmacen.setPParametro(""+((CEAlmacen)(CbxAlmacen.getSelectedItem())).getDescripcion());


    Vector<ClaseReporte_Parametro> oLstParametro = new Vector<ClaseReporte_Parametro>();
    oLstParametro.add(oClaseParametroIdAlmacen);
    oLstParametro.add(oClaseParametroAlmacen);
    oLstParametro.add(oClaseParameFechaIni);
    oLstParametro.add(oClaseParametroFechaFin);
    oLstParametro.add(oClaseParameDel);
    oLstParametro.add(oClaseParameAl);


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
        CbxAlmacen = new ComboBox.ComboBox();
        label2 = new Label.Label();
        dtchAl = new com.toedter.calendar.JDateChooser("dd/MM/yyyy","##/##/####",'_');
        jLabel5 = new javax.swing.JLabel();
        dtchDel = new com.toedter.calendar.JDateChooser("dd/MM/yyyy","##/##/####",'_');
        jLabel4 = new javax.swing.JLabel();
        chkValores = new Opcion.CheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reporte Kardex de Productos");

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

        CbxAlmacen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxAlmacenActionPerformed(evt);
            }
        });

        label2.setText("Almacen :");

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

        chkValores.setText("con valores");
        chkValores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkValoresActionPerformed(evt);
            }
        });

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
                        .addComponent(dtchDel, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dtchAl, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(400, 400, 400)
                        .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CbxAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(chkValores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CbxAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chkValores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(dtchAl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dtchDel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PnlMostrar, javax.swing.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGenerarActionPerformed


        BtnGenerar.setEnabled(false);
        dtchDel.setEnabled(false);
        dtchAl.setEnabled(false);
        chkValores.setEnabled(false);
        CbxAlmacen.setEnabled(false);
        ((CardLayout)(PnlMostrar.getLayout())).show(PnlMostrar,"card3");
        LblCargando.setVisible(true);
        Runnable miRunnable = new Runnable() {
            public void run() {
                try {
                    
                        if(chkValores.isSelected())
                        reportar("/view/almAlmacen/rptALMReportes/ALMRPTKardexProducto_Todos.jasper");
                        else
                            reportar("/view/almAlmacen/rptALMReportes/ALMRPTKardexProducto_TodosSinValor.jasper");
                        
                    ((CardLayout)(PnlMostrar.getLayout())).show(PnlMostrar,"card2");
                    LblCargando.setVisible(false);
                    BtnGenerar.setEnabled(true);
                    dtchDel.setEnabled(true);
                    dtchAl.setEnabled(true);
                    chkValores.setEnabled(true);
                    CbxAlmacen.setEnabled(true);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                catch (OutOfMemoryError e) {
                JOptionPane.showMessageDialog(null,"No se puede generar el Reporte, la Información que desea procesar es de gran magnitud"
                                                    + " \n Por favor utilice otra opcion para generar su reporte como Reporte Kardex de Producto");

                ((CardLayout)(PnlMostrar.getLayout())).show(PnlMostrar,"card2");
                    LblCargando.setVisible(false);
                    BtnGenerar.setEnabled(true);
                    dtchDel.setEnabled(true);
                    dtchAl.setEnabled(true);
                    chkValores.setEnabled(true);
                    CbxAlmacen.setEnabled(true);
                }
            }
        };

        Thread hilo = new Thread(miRunnable);
        hilo.start();
        
}//GEN-LAST:event_BtnGenerarActionPerformed

    private void CbxAlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxAlmacenActionPerformed
        
}//GEN-LAST:event_CbxAlmacenActionPerformed

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
    private ComboBox.ComboBox CbxAlmacen;
    private javax.swing.JLabel LblCargando;
    private javax.swing.JPanel PnlCargando;
    private javax.swing.JPanel PnlMostrar;
    private javax.swing.JScrollPane PnlReporte;
    private javax.swing.ButtonGroup buttonGroup1;
    private Opcion.CheckBox chkValores;
    private com.toedter.calendar.JDateChooser dtchAl;
    private com.toedter.calendar.JDateChooser dtchDel;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private Label.Label label2;
    // End of variables declaration//GEN-END:variables

}
