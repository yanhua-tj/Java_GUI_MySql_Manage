package tanjian.gui;

import tanjian.jdbc.StudentDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginDialog extends JFrame implements ActionListener {
    public JTextField database;
    public JTextField user;
    public JPasswordField password;
    JButton login;
    JButton exit;
    StudentDAO studentDAO;

    public LoginDialog() {
        init();
        setSize(500, 300);
        setLocationRelativeTo(null);
        setTitle("登录MySQL数据库");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void init() {
        JPanel jPanel1 = new JPanel();
        JPanel jPanel2 = new JPanel();
        jPanel1.setLayout(new GridLayout(3, 1));
        JPanel jPanel11 = new JPanel();
        JPanel jPanel12 = new JPanel();
        JPanel jPanel13 = new JPanel();

        jPanel11.add(new JLabel("数据库名: "));
        database = new JTextField(20);
        jPanel11.add(database);

        jPanel12.add(new JLabel("用户名: "));
        user = new JTextField(20);
        jPanel12.add(user);

        jPanel13.add(new JLabel("密码: "));
        password = new JPasswordField(20);
        jPanel13.add(password);

        jPanel1.add(jPanel11);
        jPanel1.add(jPanel12);
        jPanel1.add(jPanel13);

        jPanel2.setLayout(new FlowLayout());
        login = new JButton("登录");
        login.addActionListener(this);
        exit = new JButton("退出");
        exit.addActionListener(this);
        jPanel2.add(login);
        jPanel2.add(exit);

        add(jPanel1, BorderLayout.CENTER);
        add(jPanel2, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            System.exit(0);
        }
        if (e.getSource() == login) {
            try {
                studentDAO = new StudentDAO(database.getText(), user.getText(), new String(password.getPassword()));
                System.out.println("成功登录");
                setVisible(false);
                MainWindow mainWindow = new MainWindow();
                mainWindow.setStudentDAO(studentDAO);
            } catch (SQLException throwables) {
                JOptionPane.showMessageDialog(this, "数据库未启动！\n或用户名或密码错误！\n请重新检查输入!", "错误", JOptionPane.ERROR_MESSAGE);
                password.setText("");
                throwables.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                JOptionPane.showMessageDialog(this, "java mysql连接器不存在!", "错误", JOptionPane.ERROR_MESSAGE);
                classNotFoundException.printStackTrace();
            }
        }
    }
}
