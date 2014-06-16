package iuy.util;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.Window;

public class OutilsWindow
{
  //pour centrer les fen�tres selon la r�solution de l'�cran
  public static void centrer(Window frm) throws HeadlessException
  {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = frm.getSize();
    if (frameSize.height > screenSize.height)
    {
      frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width)
    {
      frameSize.width = screenSize.width;
    }
    frm.setLocation( (screenSize.width - frameSize.width) / 2,
                    (screenSize.height - frameSize.height) / 2);
  }

}
