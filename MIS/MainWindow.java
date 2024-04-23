import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class MainWindow extends JFrame implements ActionListener{
	private JToolBar toolBar;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JTable table;
	
	private JPanel queryPanel;
	private JLabel lblName;
	private JTextField txtName;
	private JLabel lblScore;
	private JComboBox comConditions;
	private JTextField txtScore;
	private JButton btnQuery;
	private JCheckBox ckRough;
	
	private List<Player> players = new ArrayList<Player>();
	
	public MainWindow() {
		initWorkPanel();
		initQueryPanel();
		
		loadPlayerFile();

		this.setSize(600,450);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initWorkPanel() {
		JPanel pane = (JPanel) this.getContentPane();
		pane.setLayout(new BorderLayout(0, 0));
		
		JPanel workareaPanel = new JPanel();
		pane.add(workareaPanel, BorderLayout.CENTER);
		workareaPanel.setLayout(new BorderLayout(0, 0));
		
		toolBar = new JToolBar();
		workareaPanel.add(toolBar, BorderLayout.NORTH);
		
		btnAdd = new JButton("新建",new ImageIcon(this.getClass().getResource("/images/add16.png")));
		btnUpdate = new JButton("修改",new ImageIcon(this.getClass().getResource("/images/update16.png")));
		btnDelete = new JButton("删除",new ImageIcon(this.getClass().getResource("/images/delete16.png")));
		toolBar.add(btnAdd);
		toolBar.add(btnUpdate);
		toolBar.add(btnDelete);
		
		String[] columns = new String[] {"玩家姓名","玩家口令","游戏次数","玩家积分","玩家状态̬"};
		DefaultTableModel model = new DefaultTableModel(null, columns);
		table = new JTable(model);
		JScrollPane jp = new JScrollPane(table);
		workareaPanel.add(jp,BorderLayout.CENTER);
		
		btnAdd.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnDelete.addActionListener(this);
		
	}
	private void initQueryPanel() {
		JPanel pane = (JPanel) this.getContentPane();
		queryPanel = new JPanel();
		pane.add(queryPanel, BorderLayout.NORTH);
		
		
		lblName = new JLabel("玩家姓名");
		txtName = new JTextField();
		lblScore = new JLabel("玩家积分");
		comConditions = new JComboBox();
		comConditions.addItem("相等");
		comConditions.addItem("大于");
		comConditions.addItem("小于");
		txtScore = new JTextField();
		btnQuery = new JButton("查询");
		ckRough = new JCheckBox("模糊匹配");
		queryPanel.setLayout(new GridLayout(1,7));
		queryPanel.add(lblName);
		queryPanel.add(txtName);
		queryPanel.add(lblScore);
		queryPanel.add(comConditions);
		queryPanel.add(txtScore);
		queryPanel.add(btnQuery);
		queryPanel.add(ckRough);
		
		btnQuery.addActionListener(this);
	}
	
	private void loadPlayerFile() {
		URL url = this.getClass().getResource("/player.csv");
		
		FileReader fr = null;
		BufferedReader reader = null;
		try {
			fr = new FileReader(url.getFile());
			reader = new BufferedReader(fr);
			String line = null;
			while((line = reader.readLine()) != null) {
				if(line.trim().equals(""))continue;
				String[] ss = line.split(",");
				String state = "离线";
				if(ss.length >= 5)state = ss[4];
				
				Player player = new Player(ss[0],ss[1],
						Integer.parseInt(ss[2]),
						Integer.parseInt(ss[3]),
						state);
				
				this.players.add(player);
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(this, "读取玩家数据文件出错："+e.getMessage());
		}finally {
			try {
				if(reader != null)reader.close();
			}catch(Exception e) {}
		}
		
		showPlayer();
	}
	
	public void savePlayerFile() {
		URL url = this.getClass().getResource("/player.csv");
		
		FileWriter fr = null;
		BufferedWriter writer = null;
		try {
			fr = new FileWriter(url.getFile());
			writer = new BufferedWriter(fr);
			for(Player player : this.players) {
				String line = player.toString();
				writer.write(line);
				writer.newLine();
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(this, "保存玩家数据文件出错："+e.getMessage());
		}finally {
			try {
				if(writer != null)writer.close();
			}catch(Exception e) {}
		}
	}
	
	private void showPlayer() {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
		for(int i=0;i<this.players.size();i++) {
			if(!this.isMatchVisibleCondition(this.players.get(i)))
				continue;
			tableModel.addRow(new Object[] {
					this.players.get(i).getName(),
					this.players.get(i).getPassword(),
					this.players.get(i).getCount(),
					this.players.get(i).getScore(),
					this.players.get(i).getState()
			});
		}
		table.invalidate();
	}
	
	private boolean isMatchVisibleCondition(Player player) {
		if(!this.txtName.getText().equals("")) {
			if(!this.ckRough.isSelected()) {
				if(!player.getName().equals(this.txtName.getText()))
					return false;
			}else {
				if(!player.getName().contains(this.txtName.getText())) 
					return false;
			}
		}
		
		if(!this.txtScore.getText().equals("")){
			int s = Integer.parseInt(this.txtScore.getText());
			if(this.comConditions.getSelectedIndex() == 0) {
				if(s != player.getScore())return false;
			}else if(this.comConditions.getSelectedIndex() == 1) {
				if(s > player.getScore())return false;
			}else if(this.comConditions.getSelectedIndex() == 2) {
				if(s < player.getScore())return false;
			}
		}
		return true;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.btnQuery) {
			this.showPlayer();
		}else if(e.getSource() == this.btnAdd) {
			Editor editor = new Editor(this,null);
			editor.setVisible(true);
			if(editor.isCancled())return;
			
			this.players.add(editor.getPlayer());
			this.savePlayerFile();
			this.showPlayer();
		}else if(e.getSource() == this.btnUpdate) {
			DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
			int selectedRowIndex = table.getSelectedRow();
			String name = (String) tableModel.getValueAt(selectedRowIndex,0);
			for(int i=0;i<this.players.size();i++) {
				if(this.players.get(i).getName().equals(name)) {
					Editor editor = new Editor(this,this.players.get(i));
					editor.setVisible(true);
					if(editor.isCancled())return;
					this.savePlayerFile();
					this.showPlayer();
					return;
				}
			}
		}else if(e.getSource() == this.btnDelete) {
			DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
			int selectedRowIndex = table.getSelectedRow();
			String name = (String) tableModel.getValueAt(selectedRowIndex,0);
			for(int i=0;i<this.players.size();i++) {
				if(this.players.get(i).getName().equals(name)) {
					int r = JOptionPane.showConfirmDialog(this, "该记录将被永久删除，确认？");
					if(r == JOptionPane.OK_OPTION) {
						this.players.remove(i);
						this.savePlayerFile();
						this.showPlayer();
					}
					
					return;
				}
			}
		}
	}
	
	public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.setVisible(true);
    }

}
