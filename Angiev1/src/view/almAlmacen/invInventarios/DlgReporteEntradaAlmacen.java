package view.almAlmacen.invInventarios;


import controller.almAlmacen.CCAlmacen;
import java.awt.CardLayout;
import java.net.URL;
import java.util.Date;
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


public class DlgReporteEntradaAlmacen extends DialogoPadre
{
 
    public DlgReporteEntradaAlmacen(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        LblCargando.setVisible(false);
        dtchAl.setDate(new Date());
        dtchDel.setDate(new Date());
       CbxAlmacen.setModel(CLComboBox.cargarCombo(CCAlmacen.listarAlmacenSucursal()));

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
    ClaseReporte_Parametro oClaseParameDel = new ClaseReporte_Parametro();
    oClaseParameDel.setNombre("pfechaini");
    ConvertidorFecha oCEConvertidorFecha= new ConvertidorFecha();
    oCEConvertidorFecha.setFecha(dtchDel.getCalendar());
    oClaseParameDel.setPParametro(oCEConvertidorFecha.getFechaYMD());

    ClaseReporte_Parametro oClaseParametroAl = new ClaseReporte_Parametro();
    oClaseParametroAl.setNombre("pfechafin");
    oCEConvertidorFecha.setFecha(dtchAl.getCalendar());
    oClaseParametroAl.setPParametro(oCEConvertidorFecha.getFechaYMD());

    int idAlmacen=((CEAlmacen)CbxAlmacen.getSelectedItem()).getIdAlmacen();
    ClaseReporte_Parametro oClaseParametroAlmacen = new ClaseReporte_Parametro();
    oClaseParametroAlmacen.setNombre("pidalmacen");
    oClaseParametroAlmacen.setPParametro(""+idAlmacen);

    this.getparametros();
    ClaseReporte_Parametro oClaseParametroidcomp = new ClaseReporte_Parametro();
    oClaseParametroidcomp.setNombre("pidcomp");
    oClaseParametroidcomp.setPParametro(""+pidcomp);

    ClaseReporte_Parametro oClaseParametropiddevedev = new ClaseReporte_Parametro();
    oClaseParametropiddevedev.setNombre("piddevedev");
    oClaseParametropiddevedev.setPParametro(""+piddevedev);

    ClaseReporte_Parametro oClaseParametropiddevnc = new ClaseReporte_Parametro();
    oClaseParametropiddevnc.setNombre("piddevnc");
    oClaseParametropiddevnc.setPParametro(""+piddevnc);

    ClaseReporte_Parametro oClaseParametropidotros = new ClaseReporte_Parametro();
    oClaseParametropidotros.setNombre("pidotros");
    oClaseParametropidotros.setPParametro(""+pidotros);

    ClaseReporte_Parametro oClaseParametropidtrasnf = new ClaseReporte_Parametro();
    oClaseParametropidtrasnf.setNombre("pidtrasnf");
    oClaseParametropidtrasnf.setPParametro(""+pidtrasnf);

    ClaseReporte_Parametro oClaseParametropidajuste = new ClaseReporte_Parametro();
    oClaseParametropidajuste.setNombre("pidajuste");
    oClaseParametropidajuste.setPParametro(""+pidajuste);

    Vector<ClaseReporte_Parametro> oLstParametro = new Vector<ClaseReporte_Parametro>();
    oLstParametro.add(oClaseParameDel);
    oLstParametro.add(oClaseParametroAl);
    oLstParametro.add(oClaseParametroAlmacen);
    oLstParametro.add(oClaseParametroidcomp);
    oLstParametro.add(oClaseParametropiddevedev);
    oLstParametro.add(oClaseParametropiddevnc);
    oLstParametro.add(oClaseParametropidotros);
    oLstParametro.add(oClaseParametropidtrasnf);
    oLstParametro.add(oClaseParametropidajuste);

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
        jLabel3 = new javax.swing.JLabel();
        CbxAlmacen = new ComboBox.ComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        dtchDel = new com.toedter.calendar.JDateChooser("dd/MM/yyyy","##/##/####",'_');
        dtchAl = new com.toedter.calendar.JDateChooser("dd/MM/yyyy","##/##/####",'_');
        chkcompra = new Opcion.CheckBox();
        chekdev = new Opcion.CheckBox();
        chktransf = new Opcion.CheckBox();
        chknc = new Opcion.CheckBox();
        chkotros = new Opcion.CheckBox();
        chkajuste = new Opcion.CheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reporte Resumen Entrada de Producto");

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

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel3.setForeground(new java.awt.Color(0, 0, 51));
        jLabel3.setText("Almacen");

        CbxAlmacen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxAlmacenActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel4.setForeground(new java.awt.Color(0, 0, 51));
        jLabel4.setText("DEL :");

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel5.setForeground(new java.awt.Color(0, 0, 51));
        jLabel5.setText("AL :");

        dtchDel.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtchDelPropertyChange(evt);
            }
        });

        chkcompra.setSelected(true);
        chkcompra.setText("Compras");

        chekdev.setSelected(true);
        chekdev.setText("devol de dinero");
        chekdev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chekdevActionPerformed(evt);
            }
        });

        chktransf.setSelected(true);
        chktransf.setText("Transferencia ");

        chknc.setSelected(true);
        chknc.setText("Dev con Nota Credito");
        chknc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkncActionPerformed(evt);
            }
        });

        chkotros.setSelected(true);
        chkotros.setText("Otros");

        chkajuste.setSelected(true);
        chkajuste.setText("Ajuste Almacen");
        chkajuste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkajusteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dtchDel, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dtchAl, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CbxAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkcompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkotros, 0, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chekdev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chktransf, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(chkajuste, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chknc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnGenerar)
                .addContainerGap(31, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(PnlMostrar, javax.swing.GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(CbxAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chkcompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chekdev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtnGenerar)
                        .addComponent(chknc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5)
                        .addComponent(dtchAl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dtchDel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkajuste, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chktransf, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkotros, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(614, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(60, Short.MAX_VALUE)
                    .addComponent(PnlMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGenerarActionPerformed

        if(!chkcompra.isSelected()&&!chekdev.isSelected()&&!chkotros.isSelected()&&!chktransf.isSelected()&&!chknc.isSelected())
        {
            JOptionPane.showMessageDialog(null,"Seleccione al menos un tipo de Entrada");
        }else{
        BtnGenerar.setEnabled(false);
        dtchAl.setEnabled(false);
        dtchDel.setEnabled(false);
        CbxAlmacen.setEnabled(false);
        chekdev.setEnabled(false);
        chkcompra.setEnabled(false);
        chkotros.setEnabled(false);
        chknc.setEnabled(false);
        chktransf.setEnabled(false);
        chkajuste.setEnabled(false);
        ((CardLayout)(PnlMostrar.getLayout())).show(PnlMostrar,"card3");
        LblCargando.setVisible(true);
        Runnable miRunnable = new Runnable() {
            public void run() {
                try {
                    

                        reportar("/view/almAlmacen/rptALMReportes/ALMRPTEntradaAlmacen.jasper");
                         

                    ((CardLayout)(PnlMostrar.getLayout())).show(PnlMostrar,"card2");
                    LblCargando.setVisible(false);
                    BtnGenerar.setEnabled(true);
                    CbxAlmacen.setEnabled(true);
                    dtchAl.setEnabled(true);
                    dtchDel.setEnabled(true);
                    chekdev.setEnabled(true);
                    chkcompra.setEnabled(true);
                    chkotros.setEnabled(true);
                    chknc.setEnabled(true);
                    chktransf.setEnabled(true);
                    chkajuste.setEnabled(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Thread hilo = new Thread(miRunnable);
        hilo.start();
        }

}//GEN-LAST:event_BtnGenerarActionPerformed

    private int pidcomp;
    private int piddevedev;
    private int piddevnc;
    private int pidotros;
    private int pidtrasnf;
    private int pidajuste;
    private void reiniciar()
    {
      pidcomp=0;
      piddevedev=0;
      piddevnc=0;
      pidotros=0;
      pidtrasnf=0;
      pidajuste=0;
    }
    private void getparametros()
    {
        reiniciar();
      if(chkcompra.isSelected())
      {
          pidcomp=2;
      }
        if(chekdev.isSelected())
      {
          piddevedev=5;
      }
        if(chknc.isSelected())
      {
          piddevnc=4;
      }
        if(chkotros.isSelected())
      {
          pidotros=7;
      }
        if(chktransf.isSelected())
      {
          pidtrasnf=3;
      }
        if(chkajuste.isSelected())
      {
          pidajuste=8;
      }
    }
    private void dtchDelPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtchDelPropertyChange
        if(evt.getPropertyName().equals("date"))
     {
         dtchAl.setMinSelectableDate(dtchDel.getDate());
     }
    }//GEN-LAST:event_dtchDelPropertyChange

    private void CbxAlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxAlmacenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CbxAlmacenActionPerformed

    private void chkncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkncActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkncActionPerformed

    private void chekdevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chekdevActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chekdevActionPerformed

    private void chkajusteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkajusteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkajusteActionPerformed
  
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnGenerar;
    private ComboBox.ComboBox CbxAlmacen;
    private javax.swing.JLabel LblCargando;
    private javax.swing.JPanel PnlCargando;
    private javax.swing.JPanel PnlMostrar;
    private javax.swing.JScrollPane PnlReporte;
    private javax.swing.ButtonGroup buttonGroup1;
    private Opcion.CheckBox chekdev;
    private Opcion.CheckBox chkajuste;
    private Opcion.CheckBox chkcompra;
    private Opcion.CheckBox chknc;
    private Opcion.CheckBox chkotros;
    private Opcion.CheckBox chktransf;
    private com.toedter.calendar.JDateChooser dtchAl;
    private com.toedter.calendar.JDateChooser dtchDel;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables

}

