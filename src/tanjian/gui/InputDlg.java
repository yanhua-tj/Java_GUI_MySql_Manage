package tanjian.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputDlg extends JDialog implements ActionListener {
    public JTextField input;
    public JButton ok;
    public JLabel label;
    public boolean flag = false;

    public InputDlg() {
        init();
    }
    void init() {
        setSize(200, 120);
        setLocationRelativeTo(null);    // 居中
        setResizable(false);            // 关闭最大化
        setModal(true);                 // 模态对话框
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setLayout(new FlowLayout());
        label = new JLabel();
        add(label);
        input = new JTextField(10);
        input.addActionListener(this);
        add(input);
        ok = new JButton("确定");
        ok.addActionListener(this);
        add(ok);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
