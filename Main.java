
import java.awt.Font;

/**
 *
 * @author Pankaj
 */
public class Main {

	public static void main(String[] args) {
		JFontDialog fontDialog = new JFontDialog();
		fontDialog.setVisible(true);
		Font selectedFont = fontDialog.getFont();
		System.out.println(selectedFont.toString());
	}
}
