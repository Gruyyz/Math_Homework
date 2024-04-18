package FiveInRow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Stack;

public class ChessBoard extends JFrame {
    private final int boardSize = 12;// 棋盘大小
    private final int squareSize = 50;// 每个格子大小
    private final int edgeSize = 25;// 边缘空格大小
    private final int[][] chessboard =new int[boardSize+1][boardSize+1];// 在这里声明棋子
    int vector=0;
    private Stack<int[]> moveHistory = new Stack<>(); // 用于追踪棋步（行，列）

    public ChessBoard(){
        setTitle(("五子棋"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ChessBoardPanel chessBoardPanel = new ChessBoardPanel(chessboard,boardSize,squareSize,edgeSize);// 传递棋子数组

        chessBoardPanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                int lx = getWidth();
                int ly = getHeight();
                int boardWidth = (boardSize + 1) * squareSize;
                int boardHeight = (boardSize + 1) * squareSize;
                int col = (x - (lx - boardWidth) / 2) / squareSize;
                int row = (y - (ly - boardHeight) / 2 + edgeSize) / squareSize;
        
                if (col >= 0 && col <= boardSize && row >= 0 && row <= boardSize && chessboard[row][col] == 0) {
                    vector++;
                    int currentplayer = getCurrentPlayer();
                    chessboard[row][col] = currentplayer;// 1：黑子 2：白子
                    chessBoardPanel.updateUI();
                    int[] move = { row, col }; // 存储当前棋步
                    moveHistory.push(move); // 将棋步添加到历史记录
                }
                if (checkHorizontalWin(getCurrentPlayer()) || checkVerticalWin(getCurrentPlayer()) || checkDiagonalWinLR(getCurrentPlayer()) || checkDiagonalWinRL(getCurrentPlayer())) {
                    int n = 0;
        
                    if (getCurrentPlayer() == 1) {
                        n = JOptionPane.showConfirmDialog(null, "黑棋赢了 要再来一次吗", null, JOptionPane.OK_CANCEL_OPTION);
                    } else if (getCurrentPlayer() == 2) {
                        n = JOptionPane.showConfirmDialog(null, "白棋赢了 要再来一次吗", null, JOptionPane.OK_CANCEL_OPTION);
                    }
                    if (n == JOptionPane.OK_OPTION) {
                        resetBoard();
                        chessBoardPanel.repaint();
                        vector = 0;
                    } else if (n == JOptionPane.CANCEL_OPTION) {
                        System.exit(0);
                    }
                }
            }
        });

        // 创建菜单栏
        JMenuBar menuBar = new JMenuBar();
        // 创建菜单
        JMenu menu = new JMenu("选项");
        // 创建菜单项（悔棋）
        JMenuItem undoMenuItem = new JMenuItem("悔棋");
        undoMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
                undoMove(); // 点击“悔棋”菜单项时执行悔棋操作
                chessBoardPanel.updateUI();
			}
        });
        // 添加菜单项到菜单
        menu.add(undoMenuItem);
        // 将菜单添加到菜单栏
        menuBar.add(menu);
        // 设置菜单栏
        setJMenuBar(menuBar);
        // 设置快捷键（Ctrl+R）
        KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK);
        undoMenuItem.setAccelerator(keyStroke);

        //重画窗口
        addComponentListener(new ComponentAdapter() {
            public void componentMoved(ComponentEvent e){
                chessBoardPanel.repaint();
            }
        });

        add(chessBoardPanel);
        pack();
        setLocationRelativeTo(null);// 窗口居中
        setVisible(true);// 显示窗口
    }

    // 确定棋手颜色
    private int getCurrentPlayer() {
        if (vector % 2 == 0 && vector != 0) {
            return 2;
        }
        else if (vector % 2 == 1){
            return 1;
        }
        return 1;// 假设黑子先手
    }

    // 胜利条件
    // 从左到右
    private boolean checkHorizontalWin(int player) {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j <= boardSize - 5; j++) {
                boolean win = true;
                for (int k = 0; k < 5; k++) {
                    if (chessboard[i][j + k] != player) {
                        win = false;
                        break;
                    }
                }
                if (win) {
                    return true;
                }
            }
        }
        return false;
    }
    // 从上到下
    private boolean checkVerticalWin(int player) {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j <= boardSize - 5; j++) {
                boolean win = true;
                for (int k = 0; k < 5; k++) {
                    if (chessboard[j + k][i] != player) {
                        win = false;
                        break;
                    }
                }
                if (win) {
                    return true;
                }
            }
        }
        return false;
    }
    // 从左下到右上
    private boolean checkDiagonalWinLR(int player) {
        for (int i = 0; i <= boardSize - 5; i++) {
            for (int j = 0; j <= boardSize - 5; j++) {
                boolean win = true;
                for (int k = 0; k < 5; k++) {
                    if (chessboard[i + k][j + k] != player) {
                        win = false;
                        break;
                    }
                }
                if (win) {
                    return true;
                }
            }
        }
        return false;
    }
    // 从右下到左上
    private boolean checkDiagonalWinRL(int player) {
        for (int i = 0; i <= boardSize - 5; i++) {
            for (int j = 4; j < boardSize; j++) {
                boolean win = true;
                for (int k = 0; k < 5; k++) {
                    if (chessboard[i + k][j - k] != player) {
                        win = false;
                        break;
                    }
                }
                if (win) {
                    return true;
                }
            }
        }
        return false;
    }

    //悔棋
    private void undoMove() {
        if (!moveHistory.isEmpty()) {
            int[] lastMove = moveHistory.pop(); // 获取上一步棋子位置
            int row = lastMove[0];
            int col = lastMove[1];
            chessboard[row][col] = 0; // 恢复棋子之前的状态
            vector--; // 更新棋手步数
        }
    }

    // 结束后重置棋盘
    private void resetBoard() {
        for (int i = 0; i <= boardSize; i++) {
            for (int j = 0; j <= boardSize; j++) {
                chessboard[i][j] = 0;
            }
        }
    }
}

class ChessBoardPanel extends JPanel{
    private int boardSize;// 在这声明棋盘大小
    private int squareSize;// 在这声明每个格子大小
    private int edgeSize;// 在这声明边缘空格大小
    private int[][] chessboard; // 在这里声明棋子

    public ChessBoardPanel(int[][] chessboard,int boardSize,int squareSize,int edgeSize) {
        this.chessboard = chessboard;
        this.boardSize = boardSize;
        this.squareSize = squareSize;
        this.edgeSize = edgeSize;
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();

        int boardWidth = (boardSize + 1) * squareSize;
        int boardHeight = (boardSize + 1) * squareSize;

        int x = (width - boardWidth) / 2;
        int y = (height - boardHeight) / 2;
        int lx = x + edgeSize;
        int ly = y + edgeSize;

        // 画棋盘背景
        g.setColor(new Color(240, 230, 140)); // 米黄色
        g.fillRect(x, y, boardWidth, boardHeight);

        g.setColor(Color.BLACK);
        // 画横线
        for (int row = 0; row <= boardSize; row++) {
            g.drawLine(lx, ly + row * squareSize, lx + boardWidth - 2 * edgeSize, ly + row * squareSize);
        }
        // 画竖线
        for (int col = 0; col <= boardSize; col++) {
            g.drawLine(lx + col * squareSize, ly, lx + col * squareSize, ly + boardHeight - 2 * edgeSize);
        }
        
        // 根据棋盘数组绘制棋子
        for (int i = 0; i <= boardSize; i++) {
            for (int j = 0; j <= boardSize; j++) {
                if (chessboard[i][j] == 1) {
                    // 在 (i, j) 位置绘制黑色棋子
                    g.setColor(Color.BLACK);
                    g.fillOval(lx + j * squareSize - squareSize / 2, ly + i * squareSize - squareSize / 2, squareSize, squareSize);
                } else if (chessboard[i][j] == 2) {
                    // 在 (i, j) 位置绘制白色棋子
                    g.setColor(Color.WHITE);
                    g.fillOval(lx + j * squareSize - squareSize / 2, ly + i * squareSize - squareSize / 2, squareSize, squareSize);
                }
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(boardSize * squareSize + 8 * edgeSize, boardSize * squareSize + 4 * edgeSize);
    }
}