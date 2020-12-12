package tanjian.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StuInfoDlg extends JDialog implements ActionListener {
    JPanel jPanel1 = new JPanel();
    JPanel jPanel2 = new JPanel();

    JPanel jPanelName = new JPanel();
    JPanel jPanelStuId = new JPanel();
    JPanel jPanelSex = new JPanel();
    JPanel jPanelCid = new JPanel();

    JTextField name;
    JTextField stuId;
    JTextField cId;
    String sex;

    ButtonGroup group;
    JRadioButton sexMan;
    JRadioButton sexWoman;

    JButton ok;
    JButton cancel;

    boolean flag = false;  // true表示可以创建学生, false表示不能

    public StuInfoDlg() {
        init();
        setLocationRelativeTo(null);    // 居中
        setResizable(false);            // 设置最大化不可用
        setModal(true);                 // 设置为模态对话框
        // setVisible(true);               // 设置为可显
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    void init() {
        setSize(300, 250);

        jPanel1.setLayout(new GridLayout(4, 1));

        jPanelName.add(new JLabel("姓名: "));
        name = new JTextField(20);
        jPanelName.add(name);

        jPanelStuId.add(new JLabel("学号: "));
        stuId = new JTextField(20);
        jPanelStuId.add(stuId);

        jPanelSex.add(new JLabel("性别: "));
        group = new ButtonGroup();
        sexMan = new JRadioButton("男");
        sexWoman = new JRadioButton("女");
        group.add(sexMan);
        group.add(sexWoman);

        sexMan.setSelected(true);
        sex = sexMan.getText();
        jPanelSex.add(sexMan);
        jPanelSex.add(sexWoman);

        jPanelCid.add(new JLabel("班级: "));
        cId = new JTextField(20);
        jPanelCid.add(cId);

        jPanel1.add(jPanelName);
        jPanel1.add(jPanelStuId);
        jPanel1.add(jPanelSex);
        jPanel1.add(jPanelCid);

        ok = new JButton("确定");
        ok.addActionListener(this);
        jPanel2.add(ok);

        cancel = new JButton("取消");
        cancel.addActionListener(this);
        jPanel2.add(cancel);

        add(jPanel1, BorderLayout.CENTER);
        add(jPanel2, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ok) {
            if (name.getText().length() > 0) {
                try {
                    Integer.parseInt(stuId.getText());
                    Integer.parseInt(cId.getText());
                    if (sexMan.isSelected())
                        sex = sexMan.getText();
                    else
                        sex = sexWoman.getText();
                    flag = true;
                    setVisible(false);
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(
                            this,
                            "输入有误! 学号和班级编号必须为数字",
                            "错误",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        "输入有误! 请仔细检查输入的内容",
                        "错误",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } else if (e.getSource() == cancel) {
            setVisible(false);      // 隐藏窗口
        }
    }

}
