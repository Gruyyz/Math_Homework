package homework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginGUI extends JFrame implements ActionListener {
    private JTextField accountField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton cancelButton;

    public LoginGUI() {
        setTitle("登录界面");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel accountLabel = new JLabel("账户:");
        accountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(accountLabel);

        accountField = new JTextField();
        panel.add(accountField);

        JLabel passwordLabel = new JLabel("口令:");
        passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        panel.add(passwordField);

        loginButton = new JButton("登录");
        loginButton.addActionListener(this);
        panel.add(loginButton);

        cancelButton = new JButton("取消");
        cancelButton.addActionListener(this);
        panel.add(cancelButton);

        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String account = accountField.getText();
            String password = new String(passwordField.getPassword());
            JOptionPane.showMessageDialog(this, "账户: " + account + "\n口令: " + password, "登录信息", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == cancelButton) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginGUI();
            }
        });
    }
}