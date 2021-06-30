package guitools;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GuiTools {

    public static void setLabelFont(JLabel label, int size) {
        Font labelFont = label.getFont();
        label.setFont(new Font(labelFont.getFontName(), labelFont.getStyle(), size));
    }

    public static List<ImageIcon> addIcons(String path) {
        List<ImageIcon> icons = new ArrayList<>();
        File directory = new File(path);
        String[] file_names = directory.list();
        if (file_names != null && file_names.length > 0) {
            for (String name : file_names) {
                icons.add(new ImageIcon(path + '/' + name));
            }

        }

        return icons;
    }
}
