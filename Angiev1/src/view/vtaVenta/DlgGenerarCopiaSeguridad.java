package view.vtaVenta;

import controller.acceso.ConexionBD;
import java.awt.CardLayout;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import util.clases.grlGeneral.DialogoPadre;
import view.FrmSistemaMenu;


public class DlgGenerarCopiaSeguridad extends DialogoPadre
{
    
 java.awt.Frame parent;
    public DlgGenerarCopiaSeguridad(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        this.parent=parent;
        reportar();
    }
     private String getUrlRaiz()
    {
        String URL = "";
        URL = FrmSistemaMenu.class.getResource("").getPath().replace("file:/", "").replace("%20", " ").replace("\\", "/");
            int indice = URL.indexOf("dist") + 5;
            String URLRaiz = URL.substring(0, indice);

        return URLRaiz;
    }

private void reportar()
    {

        ((CardLayout)(PnlMostrar.getLayout())).show(PnlMostrar,"card3");
        LblCargando.setVisible(true);
        Runnable miRunnable = new Runnable() {
            public void run() {
                try {

                    int returnVal = jFileChooser1.showSaveDialog(parent);
                    if (returnVal == jFileChooser1.APPROVE_OPTION) {
                        File ofile = jFileChooser1.getSelectedFile();

                    Date oDate=new Date();

                    SimpleDateFormat oDateFormat=new SimpleDateFormat("dd-MM-yyyy hh-mm-ss");
                    String FEcha=oDateFormat.format(oDate);
                    Properties props = new Properties();
                    try {
                        props.load(ConexionBD.class.getResourceAsStream("ConexionLocalHost.properties"));
                    } catch (IOException ex) {
                        Logger.getLogger(FrmSistemaMenu.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    String command = "\""+getUrlRaiz()+"mysqldump.exe"+"\" --host=192.168.50.102 --password="+props.getProperty("jdbc.password")+" --user="+props.getProperty("jdbc.username")+" -r\""+ofile.getPath()+"AYN-"+FEcha+".sql\" dbaynv1";
                        try {
                            java.lang.Process p = Runtime.getRuntime().exec(command);
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null,"Operación Fallida","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                            Logger.getLogger(FrmSistemaMenu.class.getName()).log(Level.SEVERE, null, ex);
                        }


                    dispose();

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

        jFileChooser1 = new javax.swing.JFileChooser();
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
                .addComponent(PnlMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PnlMostrar, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LblCargando;
    private javax.swing.JPanel PnlCargando;
    private javax.swing.JPanel PnlMostrar;
    private javax.swing.JScrollPane PnlReporte;
    private javax.swing.JFileChooser jFileChooser1;
    // End of variables declaration//GEN-END:variables

}
