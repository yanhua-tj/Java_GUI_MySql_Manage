package tanjian.gui;

import tanjian.Student;

import javax.swing.table.AbstractTableModel;
import java.util.List;

class MyTableModel extends AbstractTableModel {
    // 列名
    String[] columnNames = {"学号", "姓名", "性别", "班级"};
    // 学生列表
    private List<Student> studentList;

    // 构造函数, 初始化Student List
    public MyTableModel(List<Student> studentList) {
        this.studentList = studentList;
    }

    // 获得列名
    @Override
    public String getColumnName(int column) {
        if (column < 0 || column > this.columnNames.length)
            return "--";
        return this.columnNames[column];
    }

    // 获得表格列数
    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }

    // 获得表格行数
    @Override
    public int getRowCount() {
        return this.studentList.size();
    }

    // 返回表格中第n行m列的数据
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Student s = this.studentList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return s.getStuID();
            case 1:
                return s.getsName();
            case 2:
                return s.getSex();
            case 3:
                return s.getcID();
            default:
                return "--";
        }
    }

    // 设置表格是否可编辑
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

}