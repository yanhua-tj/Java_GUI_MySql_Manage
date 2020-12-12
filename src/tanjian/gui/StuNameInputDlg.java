package tanjian.gui;

import tanjian.Student;
import tanjian.jdbc.StudentDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;

public class StuNameInputDlg extends InputDlg {
    public List<Student> studentList;     // 按姓名查询到的学生
    public StudentDAO studentDAO;

    public StuNameInputDlg(StudentDAO studentDAO) {
        super();
        this.studentDAO = studentDAO;
        setTitle("输入姓名");
        this.label.setText("请输入查询的姓名关键字: ");
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ok || e.getSource() == input) {
            try {
                String name = input.getText();
                studentList = studentDAO.findByName(name);
                if (studentList.size() > 0) {
                    flag = true;
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(
                            this,
                            "没有学生的姓名含有: " + name,
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
