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

        String os  = System.getProperty("os.name");

        if(!os.contains("Windows")){
            file_names = reverseArray(file_names);
        }


        if (file_names != null && file_names.length > 0) {
            for (String name : file_names) {
                icons.add(new ImageIcon(path + '/' + name));
            }

        }

        return icons;
    }

    private static String[] reverseArray(String[] file_names) {
        String [] output = new String[file_names.length];

        for(int i = (file_names.length - 1),j = 0; i >= 0; i--, j++){
            output[j] = file_names[i];

        }

        return output;
    }

    public static void MessageBox(String msg, String title, int messageType)
    {
        JOptionPane.showMessageDialog(new JDialog(), msg, title, messageType);
    }
}
