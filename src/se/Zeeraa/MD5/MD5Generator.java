package se.Zeeraa.MD5;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MD5Generator extends JFrame {
	private static final long serialVersionUID = 1L;

	private JTextField in = new JTextField(35);
	private JLabel ut = new JLabel();
	private JButton b = new JButton();
	private JButton copy = new JButton();
	private JButton clear = new JButton();
	private JLabel i = new JLabel(); 
	
	public static void main(String[] args) {
		new MD5Generator();
	}
	
	public MD5Generator() {
		in.setText("input");
		in.setVisible(true);
		
		i.setText("MD5 Generator by Zeeraa");
		i.setVisible(true);
		
		ut.setText("                  ");
		ut.setVisible(true);
		
		b.setText("Generate MD5");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generate();
			}
		});
		b.setVisible(true);
		
		copy.setText("Copy to clipboard");
		copy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				copy();
			}
		});
		copy.setVisible(true);
		
		clear.setText("Clear text");
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		clear.setVisible(true);
		
		setLayout(new FlowLayout());
		
		add(i);
		add(in);
		add(copy);
		add(clear);
		add(b);
		add(ut);
		pack();
		
		try {
			setIconImage(getImage("/icon.png").getImage());
		} catch(Exception e) {
			
		}
		
		setSize(new Dimension(400, 150));
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("MD5 Generator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		requestFocus();
	}
	
	private void generate() {
		ut.setText("                  ");
		String t = toMD5(in.getText());
		if(t == null) {
			ut.setText("Error");
		} else {
			ut.setText(t);
		}
	}
	
	public void copy() {
		if(ut.getText() == "                  ")
			return;
		StringSelection selection = new StringSelection(ut.getText());
	    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	    clipboard.setContents(selection, selection);
	}
	
	public void clear() {
		in.setText("");
		ut.setText("                  ");
	}
	
	private ImageIcon getImage(String path)
	{
	    URL url = getClass().getResource(path);
	    if (url != null)
	        return (new ImageIcon(url));
	    return null;
	}
	
	public String toMD5(String string) {
		try {
			byte[] msgBytes = string.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.reset();
			md.update(msgBytes);
			byte[] resultByte = md.digest();
			BigInteger bigInt = new BigInteger(1, resultByte);
			String res = bigInt.toString(16);
			
			while (res.length() < 32) {
				res = "0" + res;
			}
			
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
