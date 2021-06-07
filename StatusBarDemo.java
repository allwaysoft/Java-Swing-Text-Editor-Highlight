import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StatusBarDemo {

	private JFrame frame;
	private JToolBar toolBar;
	private boolean toolBarEnable=true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StatusBarDemo window = new StatusBarDemo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StatusBarDemo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu file_1 = new JMenu("文件");
		file_1.setMnemonic('F');
		menuBar.add(file_1);
		
		JMenuItem n = new JMenuItem("新建");
		n.setMnemonic('N');
		file_1.add(n);
		
		JMenuItem open = new JMenuItem("打开");
		open.setMnemonic('O');
		file_1.add(open);
		
		JMenuItem save = new JMenuItem("保存");
		save.setMnemonic('S');
		file_1.add(save);
		
		JMenuItem saveas = new JMenuItem("另存为...");
		file_1.add(saveas);
		
		JMenuItem 退出 = new JMenuItem("退出");
		退出.setMnemonic('Q');
		file_1.add(退出);
		
		JMenu edit = new JMenu("编辑");
		edit.setMnemonic('E');
		menuBar.add(edit);
		
		JMenuItem cut = new JMenuItem("剪切");
		cut.setMnemonic('T');
		edit.add(cut);
		
		JMenuItem copy = new JMenuItem("复制");
		copy.setMnemonic('C');
		edit.add(copy);
		
		JMenuItem paste = new JMenuItem("粘贴");
		paste.setMnemonic('P');
		edit.add(paste);
		
		JMenuItem find = new JMenuItem("查找");
		find.setMnemonic('F');
		edit.add(find);
		
		JMenuItem sall = new JMenuItem("选择全部");
		sall.setMnemonic('A');
		edit.add(sall);
		
		JMenu format = new JMenu("格式");
		menuBar.add(format);
		
		JMenuItem auto = new JMenuItem("自动换行");
		format.add(auto);
		
		JMenuItem font = new JMenuItem("字体");
		format.add(font);
		
		JMenu view = new JMenu("查看");
		menuBar.add(view);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("缩放");
		view.add(mntmNewMenuItem);
		
		JMenuItem status = new JMenuItem("状态栏");
		status.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (toolBarEnable==true) {
				toolBar.setVisible(false);
				toolBarEnable=false;
				
				}else {
					toolBar.setVisible(true);
					toolBarEnable=true;
				}
				
			}
		});
		view.add(status);
		
		JMenu help = new JMenu("帮助");
		menuBar.add(help);
		
		JMenuItem about = new JMenuItem("关于");
		help.add(about);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		frame.getContentPane().add(toolBar, BorderLayout.SOUTH);
		
		JLabel lblNewLabel = new JLabel("状态栏");
		toolBar.add(lblNewLabel);
		
		JTextPane textPane = new JTextPane();
		frame.getContentPane().add(textPane, BorderLayout.CENTER);
	}

}
