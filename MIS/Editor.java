import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Editor extends JDialog implements ActionListener{
	private JTextField txtName;
	private JPasswordField passwordField;
	private JTextField txtCount;
	private JTextField txtScore;
	private JTextField txtState;
	private JButton btnOk;
	private JButton btnCancel;
	
	private boolean cancled;
	
	private Player player;
	
	public Editor(JFrame parent,Player player) {
		this.setTitle("编辑窗口");
		
		this.setSize(291,300);
		this.setModal(true);
		
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(parent);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("玩家姓名");
		lblNewLabel.setBounds(22, 21, 73, 15);
		getContentPane().add(lblNewLabel);
		
		txtName = new JTextField();
		txtName.setBounds(96, 18, 144, 21);
		getContentPane().add(txtName);
		txtName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("玩家口令");
		lblNewLabel_1.setBounds(22, 54, 73, 15);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("游戏次数");
		lblNewLabel_2.setBounds(22, 88, 73, 15);
		getContentPane().add(lblNewLabel_2);
		
		txtCount = new JTextField();
		txtCount.setColumns(10);
		txtCount.setBounds(96, 85, 144, 21);
		getContentPane().add(txtCount);
		
		JLabel lblNewLabel_3 = new JLabel("玩家积分");
		lblNewLabel_3.setBounds(22, 128, 73, 15);
		getContentPane().add(lblNewLabel_3);
		
		txtScore = new JTextField();
		txtScore.setColumns(10);
		txtScore.setBounds(96, 125, 144, 21);
		getContentPane().add(txtScore);
		
		JLabel lblNewLabel_4 = new JLabel("玩家状态");
		lblNewLabel_4.setBounds(22, 167, 73, 15);
		getContentPane().add(lblNewLabel_4);
		
		txtState = new JTextField();
		txtState.setColumns(10);
		txtState.setBounds(96, 164, 144, 21);
		getContentPane().add(txtState);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(96, 49, 144, 21);
		getContentPane().add(passwordField);
		
		btnOk = new JButton("确定",new ImageIcon(this.getClass().getResource("/images/ok16.png")));
		btnOk.setBounds(22, 212, 93, 23);
		getContentPane().add(btnOk);
		
		btnCancel = new JButton("取消",new ImageIcon(this.getClass().getResource("/images/cancel16.png")));
		btnCancel.setBounds(149, 212, 93, 23);
		getContentPane().add(btnCancel);
		
		this.player = player;
		showPlayerInfo();
		
		this.btnOk.addActionListener(this);
		this.btnCancel.addActionListener(this);
	}
	
	private void showPlayerInfo() {
		if(this.player == null) {
			this.txtCount.setText("");
			this.txtName.setText("");
			this.passwordField.setText("");
			this.txtScore.setText("");
			this.txtState.setText("");
			return;
		}
		this.txtCount.setText(player.getCount()+"");
		this.txtName.setText(player.getName());
		this.passwordField.setText(player.getPassword());
		this.txtScore.setText(player.getScore()+"");
		this.txtState.setText(player.getState());
	}
	
	public static void main(String[] args) {
		new Editor(null,null).setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnCancel) {
			int result = JOptionPane.showConfirmDialog(this, "放弃修改，确认？");
			if(result == JOptionPane.OK_OPTION) {
				this.setVisible(false);
				cancled = true;
			}
		}else if(e.getSource() == btnOk) {
			if(this.player == null)
				this.player = new Player(this.txtName.getText(),
						                 new String(this.passwordField.getPassword()),
						                 Integer.parseInt(this.txtCount.getText()),
						                 Integer.parseInt(this.txtScore.getText()),
						                 this.txtState.getText());
			else {
				this.player.setName(this.txtName.getText());
				this.player.setPassword(new String(this.passwordField.getPassword()));
				this.player.setCount(Integer.parseInt(this.txtCount.getText()));
				this.player.setScore(Integer.parseInt(this.txtScore.getText()));
				this.player.setState(this.txtState.getText());
			}
			cancled = false;
			this.setVisible(false);
		}
	}

	public boolean isCancled() {
		return cancled;
	}

	public Player getPlayer() {
		return player;
	}
}
//1编辑器
//未做非空校验
//取消逻辑有错：可以直接关闭窗口，仍以为新增
//口令二次验证，加密
//2.主窗口
//翻页
//下方显示总记录数
