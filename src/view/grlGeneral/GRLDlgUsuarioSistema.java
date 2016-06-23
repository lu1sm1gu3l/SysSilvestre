
package view.grlGeneral;

import controller.grlGeneral.CCUsuarioSistema;
import java.util.List;
import modelo.grlGeneral.entidad.CEUsuarioSistema;
import util.clases.grlGeneral.DialogoPadre;
import view.FrmSistemaMenu;
/**
 *
 * @author CeSAr
 */
public class GRLDlgUsuarioSistema extends DialogoPadre {

   CEUsuarioSistema oCEUsuarioSistema;
    public GRLDlgUsuarioSistema(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cargarCabecera();
        cargarControl(CCUsuarioSistema.ListarUsuarioSistema(FrmSistemaMenu.oCEUsuario.getIdUsuario()));
    }

    private void cargarCabecera()
        {
            TxtAControl.append("   "+"Nº"+"\t"+"Fecha de Entrada"+"\t"+"Hora de Entrada"+" \t"+"Fecha de Salida"+" \t"+"Hora de Salida"+"\n");
            TxtAControl.append("  "+"===="+"\t"+"================"+"\t"+"==============="+" \t"+"==============="+" \t"+"=============="+"\n");
        }

    private void cargarControl(List<CEUsuarioSistema> oVctCEUsuarioSistema)
        {
            if(oVctCEUsuarioSistema!=null)
            {
                int size = oVctCEUsuarioSistema.size();
                for(int i=0;i<size;i++)
                {
                    TxtAControl.append("   "+(i+1)+"\t");
                    TxtAControl.append("   "+oVctCEUsuarioSistema.get(i).getFechaEntrada()+"\t\t");
                    TxtAControl.append("   "+oVctCEUsuarioSistema.get(i).getHoraEntrada()+"\t\t");
                    TxtAControl.append("   "+oVctCEUsuarioSistema.get(i).getFechaSalida()+"\t\t");
                    TxtAControl.append("   "+oVctCEUsuarioSistema.get(i).getHoraSalida()+"\n");
                }
            }
         
        }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TxtAControl = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Historial Acceso del Sistema");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("HISTORIAL DE ACCESO AL SISTEMA");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        TxtAControl.setColumns(20);
        TxtAControl.setEditable(false);
        TxtAControl.setRows(5);
        jScrollPane1.setViewportView(TxtAControl);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)
                .addGap(10, 10, 10))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Dimension dialogSize = getSize();
        setLocation((screenSize.width-dialogSize.width)/2,(screenSize.height-dialogSize.height)/2);
    }// </editor-fold>//GEN-END:initComponents



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea TxtAControl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
