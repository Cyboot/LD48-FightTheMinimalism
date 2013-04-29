package de.timweb.ld48.minimalism;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import de.timweb.ld48.minimalism.engine.Canvas;


public class Main {

	public static void main(final String[] args) {
		Canvas canvas = Canvas.getInstance();

		JFrame frame = new JFrame("Fight the Minimalism");
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(canvas);
		frame.setContentPane(panel);
		frame.pack();
		frame.setResizable(false);
		// frame.setLocation(-1180, -100);
		frame.setLocationRelativeTo(null);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		canvas.start();
	}
}
