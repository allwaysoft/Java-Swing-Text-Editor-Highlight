import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.TextComponent;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GetCaretPosition_TextComponent {
	public static void main(String[] args) {
		Frame frame = new Frame("GetCaretPosition_TextComponent");
		// TextComponent is a super class of TextArea
		TextComponent textComponent = new TextArea("Welcome To JavaTips.net", 10, 25);
		textComponent.setEditable(false);
		textComponent.addTextListener(new MyTextListener("textArea"));
		System.out.println(textComponent.getCaretPosition());
		textComponent.getSelectedText();
		textComponent.getSelectionEnd();
		textComponent.getSelectionStart();
		textComponent.getText();
		textComponent.getTextListeners();
		textComponent.isEditable();

		textComponent.removeTextListener(new MyTextListener("textArea"));
		textComponent.select(10, 20);
		textComponent.selectAll();
		textComponent.setCaretPosition(10);
		textComponent.setEditable(true);
		textComponent.setSelectionEnd(1);
		textComponent.setSelectionStart(2);
		textComponent.setText("text");

		frame.add(textComponent);
		frame.setLayout(new FlowLayout());
		frame.setSize(300, 250);
		frame.setVisible(true);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
}

class MyTextListener implements TextListener {
	String preface;

	public MyTextListener(String source) {
		preface = source + " text value changed.\n" + "   First 10 characters: \"";
	}

	public void textValueChanged(TextEvent e) {
		TextComponent tc = (TextComponent) e.getSource();
		System.out.println("Typed value in TextComponent " + tc.getText());
	}
}