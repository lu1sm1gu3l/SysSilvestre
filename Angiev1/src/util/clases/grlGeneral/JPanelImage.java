package util.clases.grlGeneral;
import java.awt.Graphics;

import java.awt.Graphics2D;

import java.awt.Image;

import javax.swing.Icon;

import javax.swing.ImageIcon;

import javax.swing.JPanel;

public class JPanelImage extends JPanel{

private Image image=null;
private Image imageDefault=null;

    private Icon icon;
    public JPanelImage() 
    {

    }

    protected void paintComponent(Graphics g) 
    {
        Graphics2D g2 =(Graphics2D) g;
        if(getImage()!=null)
        {
           g2.drawImage(getImage(), 0, 0, getWidth(), getHeight(), null);
        }
        else
        {                    
           g2.drawImage(getImageDefault(), 0, 0, getWidth(), getHeight(), null);
        }
    }
    public void setImageDefault(Image imageDefault)
    {
        this.imageDefault=imageDefault;
        
    }
    public Image getImageDefault()
    {
        return imageDefault;
        
    }
    public Image getImage() 
    {
            return image;
            
    }
public void setImage(Image image) {

        this.image = image;
        repaint();
    }

public Icon getIcon() {

        return icon;

    }

public void setIcon(Icon icon){

        this.icon=icon;

        setImage(((ImageIcon)icon).getImage());

    }

}
