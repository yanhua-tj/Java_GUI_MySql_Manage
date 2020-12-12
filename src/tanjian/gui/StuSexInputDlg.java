package tanjian.gui;

import tanjian.Student;
import tanjian.jdbc.StudentDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;

public class StuSexInputDlg extends InputDlg {
    public List<Student> studentList;     // 按姓名查询到的学生
    public StudentDAO studentDAO;
    public ButtonGroup group;
    public JRadioButton sexMan;
    public JRadioButton sexWoMan;

    public StuSexInputDlg(StudentDAO studentDAO) {
        super();
        remove(this.input);
        this.studentDAO = studentDAO;
        setTitle("选择性别");
        this.label.setText("请选择性别查询: ");
        group = new ButtonGroup();
        sexMan = new JRadioButton("男");
        sexWoMan = new JRadioButton("女");
        group.add(sexMan);
        group.add(sexWoMan);
        add(sexMan, 1);
        add(sexWoMan, 2);
        sexMan.setSelected(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ok) {
            try {
                String sex;
                if (sexMan.isSelected())
                    sex = "男";
                else
                    sex = "女";
                studentList = studentDAO.findBySex(sex);
                if (studentList.size() > 0) {
                    flag = true;
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(
                            this,
                            "没有学生的性别是: " + sex,
                            "错误",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            } catch (SQLException throwables) {
                System.out.println("按姓名查找sql语句错误");
                throwables.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        }
    }
}
