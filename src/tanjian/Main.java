package tanjian;

import tanjian.gui.LoginDialog;
import tanjian.gui.MainWindow;
import tanjian.jdbc.StudentDAO;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        LoginDialog loginDialog = new LoginDialog();

//        StudentDAO studentDAO = null;
//        try {
//            studentDAO = new StudentDAO("Stu", "root", "root8888");
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        MainWindow mainWindow = new MainWindow();
//        mainWindow.setStudentDAO(studentDAO);
    }
}
