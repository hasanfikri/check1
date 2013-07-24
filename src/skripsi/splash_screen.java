/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skripsi;

import java.awt.BorderLayout;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;

/**
 *
 * @author king
 */
public class splash_screen extends JWindow{
    public static void main(final String[] args) {
            try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MOOD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        new Thread(new Runnable (){
            public void run(){
                try{
                    final splash_screen mcd = new splash_screen();
                    mcd.setAlwaysOnTop(true);
                    mcd.setImage(new ImageIcon(getClass().getResource("/image/splash.jpg")).getImage());
                    mcd.setLocationRelativeTo(null);
                    mcd.setVisible(true);
                    Thread.sleep(1500);
                    
                    MOOD home = new MOOD();
                    home.setLocationRelativeTo(null);
                    mcd.setVisible(false);
                    home.setVisible(true);
                } catch (final InterruptedException ex){
                    Logger.getLogger(splash_screen.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }).start();
    }
    private Image image;
    private final JLabel lb;
    
    public splash_screen(){
        super();
        this.lb = new JLabel();
        setLayout(new BorderLayout());
        add(this.lb);
    }
    
    public Image getImage(){
        return this.image;
    }
    
    public void setImage(final Image image){
        this.image =image;
        this.lb.setIcon(new ImageIcon(image));
        pack();
    }
    
}
