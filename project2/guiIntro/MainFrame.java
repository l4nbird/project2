package guiIntro;
import javax.swing.JFrame;
public class MainFrame {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Push Counter");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PushCounterPanel panel = new PushCounterPanel();
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		
	}
}

