package util.clases.vtaVenta.JTreeTableJerarquia;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

public class IconTableTreeRenderer extends DefaultTreeCellRenderer {

    private final JLabel label;
    private final Font orgFont;
    private final Font boldFont;

     private ObjetoJerarquia te;

    public IconTableTreeRenderer()
    {
        label = new JLabel();
        label.setBackground(null);
        label.setOpaque(true);
        orgFont = label.getFont().deriveFont(label.getFont().getStyle() ^ Font.PLAIN);
        boldFont = label.getFont().deriveFont(label.getFont().getStyle() ^ Font.BOLD);
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {


        te = ((ObjetoJerarquiaNode) value).getObjetoJerarquia();

        label.setText(te.toString());
        ImageIcon oImageIcon=null;
        if((((ObjetoJerarquiaNode) value).getObjetoJerarquia()).getTipoObjeto()!=null)
        {
        if((((ObjetoJerarquiaNode) value).getObjetoJerarquia()).getTipoObjeto().equals("Rubro"))
        {
            oImageIcon=new ImageIcon(getClass().getResource("/util/icono/Rubro.gif"));
        }
        if((((ObjetoJerarquiaNode) value).getObjetoJerarquia()).getTipoObjeto().equals("Categoria"))
        {
           oImageIcon=new ImageIcon(getClass().getResource("/util/icono/Categoria.gif"));
        }
        if((((ObjetoJerarquiaNode) value).getObjetoJerarquia()).getTipoObjeto().equals("Familia"))
        {
           oImageIcon=new ImageIcon(getClass().getResource("/util/icono/Familia.gif"));
        }
        if((((ObjetoJerarquiaNode) value).getObjetoJerarquia()).getTipoObjeto().equals("Subfamilia"))
        {
           oImageIcon=new ImageIcon(getClass().getResource("/util/icono/Subfamilia.gif"));
        }
         if((((ObjetoJerarquiaNode) value).getObjetoJerarquia()).getTipoObjeto().equals("Producto"))
        {
            oImageIcon=new ImageIcon(getClass().getResource("/util/icono/product.png"));
        }
        }
        if (sel)
        {
            label.setFont(orgFont);
            label.setForeground(Color.BLACK);
            label.setIcon(oImageIcon);
            
        }
        else
        {
            label.setFont(boldFont);
            label.setIcon(oImageIcon);
            label.setForeground(Color.BLACK);
            
        }
        label.setPreferredSize(new Dimension(400, 20));
        return label;
    }

}
