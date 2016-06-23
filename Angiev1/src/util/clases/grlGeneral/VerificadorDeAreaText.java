
package util.clases.grlGeneral;
import java.awt.Toolkit;
import javax.swing.JTextArea;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class VerificadorDeAreaText extends PlainDocument
{
    private JTextArea editor;
    private String mStrTipoDeDato;
    private int numeroDeCaracteres;
    public VerificadorDeAreaText(String mStrTipoDeDato,int numeroCaracteres,JTextArea editor)
    {
        this.mStrTipoDeDato = mStrTipoDeDato;
        this.numeroDeCaracteres = numeroCaracteres;
        this.editor=editor;
    }   
    public String getmStrTipoDeDato()
    {
        return mStrTipoDeDato;
    }
    public void setmStrTipoDeDato(String mStrTipoDeDato)
    {
        this.mStrTipoDeDato = mStrTipoDeDato;
    }
  public void insertString(int arg0, String arg1, AttributeSet arg2) throws BadLocationException
    {
        int size= arg1.length();
        if (this.mStrTipoDeDato.equals("String"))
        {

            if(size>1)
                {

                    if(size+this.editor.getText().length()<=this.numeroDeCaracteres)
                     {
                     super.insertString(arg0, arg1.toUpperCase(), arg2);
                     }
                    else
                    {
                        Toolkit.getDefaultToolkit().beep();
                    }
                  return;
                }
            else
                {
                 for (int i=0;i<size;i++)
                    {
                     if(size+this.editor.getText().length()<=this.numeroDeCaracteres)
                     {
                        super.insertString(arg0, arg1.toUpperCase(), arg2);
                    }
                    else
                    {
                        Toolkit.getDefaultToolkit().beep();
                    }
                    return;
                    }
                }
        }

  }
    public int getNumeroDeCaracteres()
    {
     return numeroDeCaracteres;
    }
    public void setNumeroDeCaracteres(int numeroDeCaracteres)
    {
     this.numeroDeCaracteres = numeroDeCaracteres;
    }

}
