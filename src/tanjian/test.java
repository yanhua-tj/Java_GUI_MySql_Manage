package tanjian;

import org.junit.jupiter.api.Test;
import tanjian.jdbc.StudentDAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class test {
    @Test
    void find() throws SQLException, ClassNotFoundException {
        StudentDAO studentDAO = new StudentDAO("Stu", "root", "root8888");
        List<Student> students = studentDAO.find();
        for (int i = 0; i < students.size(); i++) {
            Student stu = students.get(i);
            System.out.println(stu.getsName() + ", " + stu.getSex() + ", " + stu.getStuID() + ", " + stu.getcID());
        }
    }

    @Test
    void add() throws SQLException, ClassNotFoundException {
        Student stu = new Student();
        Scanner sc = new Scanner(System.in);
        System.out.print("输入学生姓名: ");
        stu.setsName(sc.nextLine());
        System.out.print("输入学生性别: ");
        stu.setSex(sc.nextLine());
        System.out.print("输入学生学号: ");
        stu.setStuID(sc.nextInt());
        System.out.print("输入学生班级编号: ");
        stu.setcID(sc.nextInt());
        StudentDAO studentDAO = new StudentDAO("Stu", "root", "root8888");
        studentDAO.add(stu);
    }

    @Test
    void delete() throws SQLException, ClassNotFoundException {
        StudentDAO studentDAO = new StudentDAO("Stu", "root", "root8888");
        int stuID;
        Scanner sc = new Scanner(System.in);
        System.out.print("输入要删除的学生学号: ");
        stuID = sc.nextInt();
        studentDAO.delete(stuID);
    }

    @Test
    void update() throws SQLException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入学生学号：");
        int sid = sc.nextInt();
        System.out.println("请输入学生的姓名：");
        String sname = sc.next();
        System.out.println("请输入学生的性别：");
        String sex = sc.next();
        System.out.println("请输入学生的班级编号：");
        int cid = sc.nextInt();

        Student stu = new Student(sname, sid, cid, sex);
        StudentDAO studentDAO = new StudentDAO("Stu", "root", "root8888");
        studentDAO.update(stu);
    }

    @Test
    void findByStuId() throws SQLException, ClassNotFoundException {
        StudentDAO studentDAO = new StudentDAO("Stu", "root", "root8888");
        int stuID;
        Scanner sc = new Scanner(System.in);
        System.out.print("输入要查找的学生学号: ");
        stuID = sc.nextInt();
        Student stu = studentDAO.findByStuId(stuID);
        System.out.println(stu.getsName() + ", " + stu.getSex() + ", " + stu.getStuID() + ", " + stu.getcID());
    }

    @Test
    void findByName() throws SQLException, ClassNotFoundException {
        StudentDAO studentDAO = new StudentDAO("Stu", "root", "root8888");
        String name;
        Scanner sc = new Scanner(System.in);
        System.out.print("输入要查找的姓名关键字: ");
        name = sc.nextLine();
        List<Student> studentList = studentDAO.findByName(name);
        for (int i = 0; i < studentList.size(); i++) {
            Student stu = studentList.get(i);
            System.out.println(stu.getsName() + ", " + stu.getSex() + ", " + stu.getStuID() + ", " + stu.getcID());
        }
    }
}
