package tanjian.jdbc;

import tanjian.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    MySqlJDBC mySqlJDBC;
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public StudentDAO(String dbName, String user, String password) throws SQLException, ClassNotFoundException {
        mySqlJDBC = new MySqlJDBC(dbName, user, password);
        con = mySqlJDBC.getCon();
    }

    // 添加
    public void add(Student stu) throws SQLException, ClassNotFoundException {
        String sql = "insert into student values(?, ?, ?, ?)";
        con = null;
        ps = null;
        rs = null;
        try {
            con = mySqlJDBC.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(3, stu.getsName());
            ps.setString(4, stu.getSex());
            ps.setInt(1, stu.getStuID());
            ps.setInt(2, stu.getcID());
            ps.executeUpdate();
        } finally {
            mySqlJDBC.close(con, rs, ps);
        }

    }

    // 删除
    public void delete(int stuId) throws SQLException, ClassNotFoundException {
        String sql = "delete from student where StuID = ?";
        con = null;
        ps = null;
        rs = null;
        try {
            con = mySqlJDBC.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, stuId);
            ps.executeUpdate();
        } finally {
            mySqlJDBC.close(con, rs, ps);
        }
    }

    // 修改
    public void update(Student stu) throws SQLException, ClassNotFoundException {
        String sql = "update student set Sname = ?, Sex = ?, Cid = ? where StuID = ?";
        con = null;
        ps = null;
        rs = null;
        try {
            con = mySqlJDBC.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(4, stu.getStuID());
            ps.setInt(3, stu.getcID());
            ps.setString(1, stu.getsName());
            ps.setString(2, stu.getSex());
            ps.executeUpdate();
        } finally {
            mySqlJDBC.close(con, rs, ps);
        }
    }

    // 查询
    public List<Student> find() throws SQLException, ClassNotFoundException {
        List<Student> studentList = new ArrayList<Student>();
        String sql = "select * from student";
        con = null;
        ps = null;
        rs = null;
        try {
            con = mySqlJDBC.getCon();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Student s = new Student(rs.getString(3),
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(4));
                studentList.add(s);
            }
        } finally {
            mySqlJDBC.close(con, rs, ps);
        }
        return studentList;
    }

    // 按学号查询
    public Student findByStuId(int stuId) throws SQLException, ClassNotFoundException {
        Student stu = new Student();
        String sql = "select * from student where StuID = ?";
        con = null;
        ps = null;
        rs = null;
        try {
            con = mySqlJDBC.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, stuId);
            rs = ps.executeQuery();
            if (rs.next()) {
                stu.setStuID(rs.getInt(1));
                stu.setcID(rs.getInt(2));
                stu.setsName(rs.getString(3));
                stu.setSex(rs.getString(4));
            } else
                stu = null;
        } finally {
            mySqlJDBC.close(con, rs, ps);
        }
        return stu;
    }

    // 按姓名查询
    public List<Student> findByName(String name) throws SQLException, ClassNotFoundException {
        List<Student> studentList = new ArrayList<Student>();
        String sql = "select * from student where Sname like ?";
        con = null;
        ps = null;
        rs = null;
        try {
            con = mySqlJDBC.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, '%' + name + '%');
            rs = ps.executeQuery();
            while (rs.next()) {
                Student stu = new Student(rs.getString(3),
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(4));
                studentList.add(stu);
            }
        } finally {
            mySqlJDBC.close(con, rs, ps);
        }
        return studentList;
    }


    // 按性别查询
    public List<Student> findBySex(String sex) throws SQLException, ClassNotFoundException {
        List<Student> studentList = new ArrayList<Student>();
        String sql = "select * from student where Sex like ?";
        con = null;
        ps = null;
        rs = null;
        try {
            con = mySqlJDBC.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, sex);
            rs = ps.executeQuery();
            while (rs.next()) {
                Student stu = new Student(rs.getString(3),
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(4));
                studentList.add(stu);
            }
        } finally {
            mySqlJDBC.close(con, rs, ps);
        }
        return studentList;
    }

    // 按班级查询
    public List<Student> findByCId(int cId) throws SQLException, ClassNotFoundException {
        List<Student> studentList = new ArrayList<Student>();
        String sql = "select * from student where Cid = ?";
        con = null;
        ps = null;
        rs = null;
        try {
            con = mySqlJDBC.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Student stu = new Student();
                stu.setStuID(rs.getInt(1));
                stu.setcID(rs.getInt(2));
                stu.setsName(rs.getString(3));
                stu.setSex(rs.getString(4));
                studentList.add(stu);
            }
        } finally {
            mySqlJDBC.close(con, rs, ps);
        }
        return studentList;
    }
}
