/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package browse;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

/**
 *
 * @author king
 */
public class NewClass {
   public static void main(String[]args){
       try{
           	final JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 400);
		Container cp = frame.getContentPane();
		cp.setLayout(new FlowLayout());
                JTextArea ta = new JTextArea();
                ta.setBounds(200,200,100,100);

		final JTable tbl = new JTable(new String[][]{{"c1r1", "c2r1"}, {"c1r2", "c2r2"}}, new String[]{"col 1", "col 2"});

		cp.add(tbl);
                cp.add(ta);
		cp.add(new JButton(new AbstractAction("click")
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				ArrayList<String> colValues = new ArrayList<String>();

				for (int i = 0; i < tbl.getRowCount(); i++)
					colValues.add((String) tbl.getValueAt(0, i));
                                for (int i = 0; i < tbl.getColumnCount(); i++)
				 	colValues.add((String) tbl.getValueAt(0, i));

				JOptionPane.showMessageDialog(frame, colValues.toString());
			}
		}));

		frame.pack();
		frame.setVisible(true);
	}
	catch (Throwable e)
	{
		e.printStackTrace();
	}
       }
   }
   
