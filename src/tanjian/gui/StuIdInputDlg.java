package tanjian.gui;

import tanjian.Student;
import tanjian.jdbc.StudentDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class StuIdInputDlg extends InputDlg {
    public Student student;     // 按学号查询到的学生
    public StudentDAO studentDAO;

    public StuIdInputDlg(StudentDAO studentDAO) {
        super();
        this.studentDAO = studentDAO;
        setTitle("输入学号");
        this.label.setText("请输入要查询的学号: ");
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ok || e.getSource() == input) {
            try {
                int stuId = Integer.parseInt(input.getText());
                student = studentDAO.findByStuId(stuId);
                if (student != null) {
                    flag = true;
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(
                            this,
                            "没有学生的学号是" + stuId,
                            "错误",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(
                        this,
                        "输入有误! 学号必须为数字",
                        "错误",
                        JOptionPane.ERROR_MESSAGE
                );
            } catch (SQLException throwables) {
                System.out.println("按学号查找sql语句错误");
                throwables.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        }
    }
}
