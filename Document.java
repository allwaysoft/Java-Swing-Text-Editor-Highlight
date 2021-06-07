import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;
import java.io.*;

@SuppressWarnings("serial")
public class Document extends JFrame implements ActionListener, DocumentListener {
	private void newFile() {
		if (changed)
			saveFile();
		file = null;
		textArea.setText("");
		changed = false;
		setTitle("Editor");
	}

	private String readFile(File file) {
		StringBuilder result = new StringBuilder();
		try (FileReader fr = new FileReader(file); BufferedReader reader = new BufferedReader(fr);) {
			String line;
			while ((line = reader.readLine()) != null) {
				result.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Cannot read file !", "Error !", JOptionPane.ERROR_MESSAGE);
		}
		return result.toString();
	}

	private void saveAs(String dialogTitle) {
		JFileChooser dialog = new JFileChooser(System.getProperty("Document.this"));
		dialog.setDialogTitle(dialogTitle);
		int result = dialog.showSaveDialog(this);
		if (result != JFileChooser.APPROVE_OPTION)
			return;
		file = dialog.getSelectedFile();
		try (PrintWriter writer = new PrintWriter(file);) {
			writer.write(textArea.getText());
			changed = false;
			setTitle("Editor - " + file.getName());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void saveFile() {
		if (changed) {
			int ans = JOptionPane.showConfirmDialog(null, "The file has changed. You want to save it?", "Save file",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if (ans == JOptionPane.NO_OPTION)
				return;
		}
		if (file == null) {
			saveAs("Save");
			return;
		}
		String text = textArea.getText();
		System.out.println(text);
		try (PrintWriter writer = new PrintWriter(file);) {
			if (!file.canWrite())
				throw new Exception("Cannot write file!");
			writer.write(text);
			changed = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadFile() {
		JFileChooser dialog = new JFileChooser(System.getProperty("Document.this"));
		dialog.setMultiSelectionEnabled(false);
		try {
			int result = dialog.showOpenDialog(this);
			if (result == JFileChooser.CANCEL_OPTION)
				return;
			if (result == JFileChooser.APPROVE_OPTION) {
				if (changed)
					saveFile();
				file = dialog.getSelectedFile();
				textArea.setText(readFile(file));
				changed = false;
				setTitle("Editor - " + file.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private File file;
	public boolean changed = false;
	private boolean toolBarEnable = true;

	RSyntaxTextArea textArea = new RSyntaxTextArea(20, 60);

	JFrame frame = new JFrame("Input Dialog Box Frame");
	JButton button = new JButton("Show Input Dialog Box");
	JFileChooser fc = new JFileChooser();

	private JTextArea ta;
	private int count;
	private JMenuBar menuBar;
	private String pad;
	private JToolBar toolBar;
	private JLabel lblNewLabel;
	private JMenu file_1;
	private JMenuItem n;
	private JMenuItem open;
	private JMenuItem save;
	private JMenuItem saveas;
	private JMenuItem 退出;
	private JMenu edit;
	private JMenuItem cut;
	private JMenuItem copy;
	private JMenuItem paste;
	private JMenuItem find;
	private JMenuItem sall;
	private JMenu format;
	private JMenuItem auto;
	private JMenuItem font;
	private JMenu view;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem status;
	private JMenu help;
	private JMenuItem about;

	public Document() {

		super("Text Editor");
		setSize(600, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		textArea.getDocument().addDocumentListener(this);
		Container pane = getContentPane();
		pane.setLayout(new BorderLayout());

		textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
		textArea.setCodeFoldingEnabled(true);
		RTextScrollPane sp = new RTextScrollPane(textArea);
		pane.add(sp);

		count = 0;
		pad = " ";
		ta = new JTextArea(); // textarea

		menuBar = new JMenuBar();
		toolBar = new JToolBar();
		toolBar.setFloatable(false);

		ta.setLineWrap(true);
		ta.setWrapStyleWord(true);

		setJMenuBar(menuBar);

		file_1 = new JMenu("文件");
		file_1.setMnemonic('F');
		menuBar.add(file_1);

		n = new JMenuItem("新建");
		n.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newFile();
			}
		});
		n.setMnemonic('N');
		file_1.add(n);

		open = new JMenuItem("打开");
		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadFile();

			}
		});
		open.setMnemonic('O');
		file_1.add(open);

		save = new JMenuItem("保存");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveFile();
			}
		});
		save.setMnemonic('S');
		file_1.add(save);

		saveas = new JMenuItem("另存为...");
		saveas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveAs("另存为...");
			}
		});
		file_1.add(saveas);

		退出 = new JMenuItem("退出");
		退出.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (changed)
					saveFile();

				System.exit(0);
			}
		});
		退出.setMnemonic('Q');
		file_1.add(退出);

		edit = new JMenu("编辑");
		edit.setMnemonic('E');
		menuBar.add(edit);

		cut = new JMenuItem("剪切");
		cut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.cut();
			}
		});
		cut.setMnemonic('T');
		edit.add(cut);

		copy = new JMenuItem("复制");
		copy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.copy();
			}
		});
		copy.setMnemonic('C');
		edit.add(copy);

		paste = new JMenuItem("粘贴");
		paste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.paste();
			}
		});
		paste.setMnemonic('P');
		edit.add(paste);

		find = new JMenuItem("查找");
		find.addActionListener(this);
		find.setMnemonic('F');
		edit.add(find);

		sall = new JMenuItem("选择全部");
		sall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.selectAll();
			}
		});
		sall.setMnemonic('A');
		edit.add(sall);

		format = new JMenu("格式");
		menuBar.add(format);

		auto = new JMenuItem("自动换行");
		format.add(auto);

		font = new JMenuItem("字体");
		format.add(font);

		view = new JMenu("查看");
		menuBar.add(view);

		mntmNewMenuItem = new JMenuItem("缩放");
		view.add(mntmNewMenuItem);

		status = new JMenuItem("状态栏");
		status.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (toolBarEnable == true) {
					toolBar.setVisible(false);
					toolBarEnable = false;

				} else {
					toolBar.setVisible(true);
					toolBarEnable = true;
				}
			}
		});
		view.add(status);

		help = new JMenu("帮助");
		menuBar.add(help);

		about = new JMenuItem("关于");
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Text Editor in Java. \n\n 2021", "Text Editor", 1);
			}
		});
		help.add(about);

		pane.add(toolBar, BorderLayout.SOUTH);

		lblNewLabel = new JLabel("状态栏");
		toolBar.add(lblNewLabel);

		setVisible(true);

	}

	void writetofile(File ff) throws Exception {
		FileWriter fw = new FileWriter(ff.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(textArea.getText());
		bw.close();
	}

	private static void printLines(String name, InputStream ins) throws Exception {
		String line = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(ins));
		while ((line = in.readLine()) != null) {
			System.out.println(name + " " + line);
		}
	}

	private static void runProcess(String command) throws Exception {
		Process pro = Runtime.getRuntime().exec(command);
		printLines(command + "\n============================================================\nOUTPUT:\n\n\n\n",
				pro.getInputStream());
		printLines(command + " stderr:", pro.getErrorStream());
		pro.waitFor();
	}

//	public void actionPerformed(ActionEvent e) {
//		JMenuItem choice = (JMenuItem) e.getSource();
//
//	}

	public static void main(String[] args) {

		new Document();
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		changed = true;
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		changed = true;
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		changed = true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action.equals("查找")) {
			FindDialogDoc find = new FindDialogDoc(this, true);
			find.showDialog();
		}
	}

}