package automanager;

import javax.swing.SwingUtilities;

public class AutoProgramm {
	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				Autobestand model = new Autobestand();
				AMFrame frame = new AMFrame(model);
				frame.setVisible(true);
			}

		});

	}
}
