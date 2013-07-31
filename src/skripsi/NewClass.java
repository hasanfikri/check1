/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skripsi;

import java.awt.BorderLayout;
import java.awt.Color;
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

		final JTable tbl = new JTable(new String[][]{{"c1r1", "c2r1","c1r3", "c2r4","c1r5", "c2r6"},
                    {"c1r2", "c2r82","c1r72", "c2r52","c1r32", "c2r12"}}, 
                        new String[]{"col 1", "col 2","col 3", "col 4","col 5"});
                ta.setBackground(Color.red);
		cp.add(tbl);
                cp.add(ta);
		cp.add(new JButton(new AbstractAction("click")
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				ArrayList<String> colValues = new ArrayList<String>();

				for (int i = 0; i < tbl.getRowCount(); i++)
					colValues.add((String) tbl.getValueAt(0, i).toString());
                                for (int i = 0; i < tbl.getColumnCount(); i++)
				 	colValues.add((String) tbl.getValueAt(0, i).toString());

				JOptionPane.showMessageDialog(frame, colValues.toString());
			}
		}));

		frame.pack();
		frame.setViusible(true);
	}
	catch (Throwable e)
	{
		e.printStackTrace();
	}
       }
   }
   
