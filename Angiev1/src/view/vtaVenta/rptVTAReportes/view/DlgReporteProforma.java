package view.vtaVenta.rptVTAReportes.view;

import java.awt.CardLayout;
import java.net.URL;
import java.util.Vector;
import javax.swing.JOptionPane;
import modelo.vtaVenta.entidad.CEProformaMatriz;
import net.sf.jasperreports.swing.JRViewer;
import util.clases.grlGeneral.DialogoPadre;
import util.clases.grlGeneral.ParserNumerosALetras;
import util.clases.reportes.CLReporte;
import util.clases.reportes.ClaseGestionDeReporte;
import util.clases.reportes.ClaseReporte;
import util.clases.reportes.ClaseReporte_Parametro;


public class DlgReporteProforma extends DialogoPadre
{
    private CEProformaMatriz oCEProformaMatriz;
 
    public DlgReporteProforma(java.awt.Frame parent, boolean modal,CEProformaMatriz pCEProformaMatriz)
    {
        super(parent, modal);
        initComponents();
        this.oCEProformaMatriz=pCEProformaMatriz;
        reportar();
    }
private void reportar()
    {

        ((CardLayout)(PnlMostrar.getLayout())).show(PnlMostrar,"card3");
        LblCargando.setVisible(true);
        Runnable miRunnable = new Runnable() {
            public void run() {
                try {

                                

                                ClaseReporte oClaseReporte = new ClaseReporte();
                                String oUrlC = null;
                                CLReporte oCLReporte=new CLReporte();
                                URL oUrlRC=null;
                                
                                   oUrlRC=getClass().getResource("/view/vtaVenta/rptVTAReportes/VTARPTProforma.jasper");
                                
                                oCLReporte.setUrlReport(oUrlRC.toString());
                                oUrlC = oCLReporte.getUrlReport().replace('\'','/');
                                if(oCLReporte!=null)
                                {
                                oClaseReporte.setUrl(oUrlC);


                                ClaseReporte_Parametro oClaseParametroComp_Venta = new ClaseReporte_Parametro();
                                oClaseParametroComp_Venta.setNombre("id_comprobante");
                                oClaseParametroComp_Venta.setPParametro(oCEProformaMatriz.getIdProforma()+"");
                                ClaseReporte_Parametro oClaseParametroNum = new ClaseReporte_Parametro();
                                oClaseParametroNum.setNombre("p_num_letras");
                                 ParserNumerosALetras oParserNumerosALetras=new ParserNumerosALetras();
                                oClaseParametroNum.setPParametro(oParserNumerosALetras.Convertir(oCEProformaMatriz.getMontoTotal()+"", true));
                                Vector<ClaseReporte_Parametro> oLstParametro = new Vector<ClaseReporte_Parametro>();
                                oLstParametro.add(oClaseParametroNum);
                                oLstParametro.add(oClaseParametroComp_Venta);
                                oClaseReporte.setoLstParametros(oLstParametro);
                                ClaseGestionDeReporte oGestionDeReporte = new ClaseGestionDeReporte();
                                JRViewer oJRViewer=new JRViewer(oGestionDeReporte.ejecutarReporte(oClaseReporte));
                                {
                                        PnlReporte.setViewportView(oJRViewer);
                                      
                                }
                                }
                                else
                                {
                                JOptionPane.showMessageDialog(null,"El reporte no se encuentra");
                                }


                    ((CardLayout)(PnlMostrar.getLayout())).show(PnlMostrar,"card2");
                    LblCargando.setVisible(false);

                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Thread hilo = new Thread(miRunnable);
        hilo.start();

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        PnlMostrar = new javax.swing.JPanel();
        PnlReporte = new javax.swing.JScrollPane();
        PnlCargando = new javax.swing.JPanel();
        LblCargando = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reporte Comprobante Venta");

        PnlMostrar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        PnlMostrar.setLayout(new java.awt.CardLayout());
        PnlMostrar.add(PnlReporte, "card2");

        PnlCargando.setLayout(new java.awt.BorderLayout());

        LblCargando.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblCargando.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/retina_waiting.gif"))); // NOI18N
        PnlCargando.add(LblCargando, java.awt.BorderLayout.CENTER);

        PnlMostrar.add(PnlCargando, "card3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PnlMostrar, javax.swing.GroupLayout.DEFAULT_SIZE, 934, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PnlMostrar, javax.swing.GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LblCargando;
    private javax.swing.JPanel PnlCargando;
    private javax.swing.JPanel PnlMostrar;
    private javax.swing.JScrollPane PnlReporte;
    private javax.swing.ButtonGroup buttonGroup1;
    // End of variables declaration//GEN-END:variables

}
