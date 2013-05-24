import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


@SuppressWarnings("serial")
public class RecognitionWin extends JFrame {

	private JButton btnReset;
	private JLabel lblDigit;
	private BufferedImage image;
	private int[] rectCoords;
	private boolean[][] bits;

	/**
	 * Creates a window where the image drawn in DrawPanel
	 * is shown inside an ImagesPanel. Shows the interpreted
	 * digit that the user drew.
	 */
	public RecognitionWin() {
		setTitle("Handwritten Digit Recognition");
		setSize(710, 480);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);

		btnReset = new JButton("Reset");
		btnReset.setBounds(565, 400, 100, 25);
		btnReset.setFocusPainted(false);
		btnReset.addActionListener(new ResetButtonListener());
		lblDigit = new JLabel("");
		lblDigit.setFont(new Font("Verdana", Font.PLAIN, 100));
		lblDigit.setBounds(320, 340, 150, 100);
		getContentPane().add(btnReset);
		getContentPane().add(lblDigit);
		getContentPane().add(new ImagesPanel());
	}

	/**
	 * Gets the image data that was drawn in the DrawPanel.
	 */
	public void loadImage() {
		boolean[][] data = Shared.drawPanel.getData();
		image = ImageUtils.getImage(data);
		rectCoords = ImageUtils.getRectangle(data);
		bits = ImageUtils.getBits(rectCoords, data);
	}
	
	public void recognize() {
		boolean[][] booleanBits = Shared.recognitionWin.getImageBits();
		int[] intBits = new int[100];
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				intBits[10*j + i] = (booleanBits[i][j])? 1 : 0;
		int y = Shared.neuralNet.eval(intBits);
		lblDigit.setText(y + "");
	}

	/**
	 * Use it to obtain the coordinates of the renctangle that
	 * contains the drawing.
	 * @return Array of coordinates of the rectangle in the
	 *         format: { xmin, ymin, xmax, ymax }.
	 */
	public int[] getRectangleCoords() {
		return rectCoords;
	}

	/**
	 * Use it to obtain the bits of the reduced 10x10 image.
	 * @return Matrix of bits.
	 */
	public boolean[][] getImageBits() {
		return bits;
	}

	/**
	 * Use it to get the drawn image.
	 * @return Drawn image.
	 */
	public BufferedImage getImage() {
		return image;
	}

	/**
	 * Called when the user clicks the "Reset button"
	 * Takes back to the DrawWin.
	 */
	private class ResetButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			dispose();
			Shared.drawWin = new DrawWin();
		}

	}

}
