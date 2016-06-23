
package util.clases.grlGeneral;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class VerificadorDeTxt_  extends PlainDocument
{
    private JTextField editor;
    private String mStrTipoDeDato;
    private int numeroDeCaracteres;

    public VerificadorDeTxt_(String mStrTipoDeDato,int numeroCaracteres,JTextField editor)
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
                  if (Character.isLetter(arg1.charAt(arg0))||Character.isSpaceChar(arg1.charAt(arg0))||(arg1.charAt(arg0)+"").equals("'")||(arg1.charAt(arg0)+"").equals("@")||(arg1.charAt(arg0)+"").equals("."))
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
                        if (Character.isLetter(arg1.charAt(i))||Character.isSpaceChar(arg1.charAt(i))||(arg1.charAt(i)+"").equals("'")||(arg1.charAt(i)+"").equals("@")||(arg1.charAt(i)+"").equals("."))
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
                        Toolkit.getDefaultToolkit().beep();
                    }
                    return;
                    }
                }
        }
        else
        {
            if (this.mStrTipoDeDato.equals("Numero"))
            {
                if(size>1)
                {
                  if (Character.isDigit(arg1.charAt(arg0)))
                  {
                      if(size+this.editor.getText().length()<=this.numeroDeCaracteres)
                      { super.insertString(arg0, arg1, arg2);}
                      else
                        {
                        Toolkit.getDefaultToolkit().beep();
                        }

                      return;
                  }
                  else
                  {
                   Toolkit.getDefaultToolkit().beep();
                  }
                  return;
                }
                else
                {
                   if(size+this.editor.getText().length()<=this.numeroDeCaracteres)
                    {
                       for (int i=0;i<size;i++)
                        {
                            if (Character.isDigit(arg1.charAt(i)))
                            {
                             super.insertString(arg0, arg1, arg2);
                            }
                            else
                            {
                            Toolkit.getDefaultToolkit().beep();
                            }
                            return;
                        }
                    return;
                    }
                   else
                    {
                    Toolkit.getDefaultToolkit().beep();
                    }
                }
            }
            else
            {
                if (this.mStrTipoDeDato.equals("All"))
        {

            if(size>1)
                {
                  if (Character.isDigit(arg1.charAt(arg0))||Character.isLetter(arg1.charAt(arg0))||Character.isSpaceChar(arg1.charAt(arg0))||(arg1.charAt(arg0)+"").equals("'")||(arg1.charAt(arg0)+"").equals("@")||(arg1.charAt(arg0)+"").equals(".")||Character.isDigit(arg1.charAt(arg0))||(arg1.charAt(arg0)+"").equals("_"))
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
                        if (Character.isDigit(arg1.charAt(i))||Character.isLetter(arg1.charAt(i))||Character.isSpaceChar(arg1.charAt(i))||(arg1.charAt(i)+"").equals("'")||(arg1.charAt(i)+"").equals("@")||(arg1.charAt(i)+"").equals(".")||(arg1.charAt(i)+"").equals("_"))
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
                        Toolkit.getDefaultToolkit().beep();
                    }
                    return;
                    }
                }
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
