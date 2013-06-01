/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package browse;

/**
 *
 * @author king
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.ArrayList;
import javax.swing.*;
public class MultiSelectList {
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        String pp="D:/File Kuliah/Skripsi & proposal/browse/src/browse/MultiSelect.java";
        File f = new File(pp);
        InputStream is = new FileInputStream(f);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        final ArrayList<String> lines = new ArrayList<String>();
        String line = br.readLine();
        while (line!=null) {
            lines.add(line);
            line = br.readLine();
        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JList list = new JList(lines.toArray());
                list.setSelectionMode(
                    ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                int[] select = {19, 20, 22};
                list.setSelectedIndices(select);
                JOptionPane.showMessageDialog(null, new JScrollPane(list));
            }
        });
    }
}
