package tanjian.gui;

import tanjian.Student;
import tanjian.jdbc.StudentDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CIdInputDlg extends InputDlg {
    public List<Student> studentList = new ArrayList<Student>();    // 按班级编号查询到的学生
    public StudentDAO studentDAO;

    public CIdInputDlg(StudentDAO studentDAO) {
        super();
        this.studentDAO = studentDAO;
        setTitle("输入班级号");
        this.label.setText("请输入要查询的班级: ");
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ok || e.getSource() == input) {
            try {
                int cId = Integer.parseInt(input.getText());
                studentList = studentDAO.findByCId(cId);
                if (studentList.size() > 0) {
                    flag = true;
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(
                            this,
                            "没有班级是" + cId,
                            "错误",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(
                        this,
                        "输入有误! 必须为数字",
                        "错误",
                        JOptionPane.ERROR_MESSAGE
                );
            } catch (SQLException throwables) {
                System.out.println("按班级查找sql语句错误");
                throwables.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        }
    }
}
