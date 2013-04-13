
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class DrawWin extends JFrame {
	
	private JButton btnRecognize;
	
	public DrawWin() {
		setTitle("Handwritten Digit Recognition");
		setSize(new Dimension(300, 400));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		
		Shared.drawPanel = new DrawPanel();
		btnRecognize = new JButton("Recognize");
		btnRecognize.setBounds(100, 300, 100, 50);
		btnRecognize.addActionListener(new RecognizeListener());

		getContentPane().add(Shared.drawPanel);
		getContentPane().add(btnRecognize);
		setVisible(true);
	}
	
	private class RecognizeListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			Shared.recognitionWin.setVisible(true);
		}
	}
}
