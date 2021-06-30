package guitools;

import javax.swing.*;
import java.awt.*;

public class GuiTools {

    public static void setLabelFont(JLabel label, int size)
    {
        Font labelFont = label.getFont();
        label.setFont(new Font(labelFont.getFontName(), labelFont.getStyle(), size));
    }
}
