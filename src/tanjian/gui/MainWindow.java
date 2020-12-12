package tanjian.gui;

import tanjian.Student;
import tanjian.jdbc.StudentDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends JFrame implements ActionListener, WindowListener {

    JMenuBar menuBar;
    JMenu menu;
    JMenu help;
    JMenuItem dbAdd;
    JMenuItem dbDelete;
    JMenuItem dbUpdate;
    JMenu dbFind;
    JMenuItem dbFindAll;
    JMenuItem dbFindByName;
    JMenuItem dbFindByStuId;
    JMenuItem dbFindBySex;
    JMenuItem dbFindByCid;
    JMenuItem clean;
    JMenuItem about;
    JScrollPane jsPane;
    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();  // 单元格渲染器

    JTable table;
    List<Student> studentList = new ArrayList<Student>();
    Student student;
    StudentDAO studentDAO;

    public MainWindow() {
        init();
        setSize(800, 600);
        setLocationRelativeTo(null);
        setTitle("MySQL GUI 管理");
        setResizable(false);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }

    public void setStudentDAO(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    void init() {
        addWindowListener(this);
        menuBar = new JMenuBar();

        menu = new JMenu("菜单");

        dbAdd = new JMenuItem("添加");
        dbAdd.addActionListener(this);
        menu.add(dbAdd);
        menu.addSeparator();

        dbDelete = new JMenuItem("删除");
        dbDelete.addActionListener(this);
        menu.add(dbDelete);
        menu.addSeparator();

        dbUpdate = new JMenuItem("更新");
        dbUpdate.addActionListener(this);
        menu.add(dbUpdate);
        menu.addSeparator();

        dbFind = new JMenu("查找");

        dbFindAll = new JMenuItem("查找全部");
        dbFindAll.addActionListener(this);
        dbFind.add(dbFindAll);
        dbFind.addSeparator();

        dbFindByName = new JMenuItem("按姓名查找");
        dbFindByName.addActionListener(this);
        dbFind.add(dbFindByName);
        dbFind.addSeparator();

        dbFindByStuId = new JMenuItem("按学号查找");
        dbFindByStuId.addActionListener(this);
        dbFind.add(dbFindByStuId);
        dbFind.addSeparator();

        dbFindBySex = new JMenuItem("按性别查找");
        dbFindBySex.addActionListener(this);
        dbFind.add(dbFindBySex);
        dbFind.addSeparator();

        dbFindByCid = new JMenuItem("按班级查找");
        dbFindByCid.addActionListener(this);
        dbFind.add(dbFindByCid);

        menu.add(dbFind);

        clean = new JMenuItem("清空屏幕");
        clean.addActionListener(this);
        menu.add(clean);

        help = new JMenu("帮助");
        about = new JMenuItem("关于");
        about.addActionListener(this);
        help.add(about);

        menuBar.add(menu);
        menuBar.add(help);
        setJMenuBar(menuBar);

        jsPane = new JScrollPane();

        tcr.setHorizontalAlignment(JLabel.CENTER);          // 居中显示
        add(jsPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == dbDelete) {
            //JOptionPane.showMessageDialog(this, "删除数据", "删除", JOptionPane.WARNING_MESSAGE);
            DeleteStu();
            return;
        }
        if (e.getSource() == dbAdd) {
            //JOptionPane.showMessageDialog(this, "添加数据", "添加", JOptionPane.WARNING_MESSAGE);
            AddStu();
            return;
        }
        if (e.getSource() == dbUpdate) {
            //JOptionPane.showMessageDialog(this, "更新数据", "更新", JOptionPane.WARNING_MESSAGE);
            UpdateStu();
            return;
        }
        if (e.getSource() == dbFindAll) {
            //JOptionPane.showMessageDialog(this, "查找全部数据", "查找", JOptionPane.WARNING_MESSAGE);
            FindDbAll();
            return;
        }
        if (e.getSource() == dbFindByName) {
            //JOptionPane.showMessageDialog(this, "按姓名查找数据", "查找", JOptionPane.WARNING_MESSAGE);
            FindByNameOrSex(0);
            return;
        }
        if (e.getSource() == dbFindByStuId) {
            //JOptionPane.showMessageDialog(this, "按学号查找数据", "查找", JOptionPane.WARNING_MESSAGE);
            FindByStuIdOrCId(0);
            return;
        }
        if (e.getSource() == about) {
            JOptionPane.showMessageDialog(
                    this,
                    "MySQL数据库管理软件\n计科183谭剑\n2018213512",
                    "关于",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (e.getSource() == clean) {
            jsPane.setViewportView(null);   // 清空表格
            return;
        }
        if (e.getSource() == dbFindBySex) {
            FindByNameOrSex(1);
            return;
        }
        if (e.getSource() == dbFindByCid) {
            FindByStuIdOrCId(1);
            return;
        }

        JOptionPane.showMessageDialog(
                this,
                "error",
                "error",
                JOptionPane.ERROR_MESSAGE);
    }

    // 按学号删除学生并且更新
    private void DeleteStu() {
        StuIdInputDlg stuIdInputDlg = new StuIdInputDlg(studentDAO);
        if (stuIdInputDlg.flag) {
            student = stuIdInputDlg.student;
            DeleteDbDlg deleteDbDlg = new DeleteDbDlg();

            setStuInfoDlg(deleteDbDlg);

            deleteDbDlg.setVisible(true);
            if (deleteDbDlg.flag) {
                try {
                    int index = studentList.indexOf(student);
                    studentDAO.delete(student.getStuID());
                    FindDbAll();
                    index = (index - 1) < 0 ? 0 : (index - 1);
                    table.setRowSelectionInterval(index, index);
                } catch (SQLException throwables) {
                    // System.out.println("sql delete 语句错误");
                    JOptionPane.showMessageDialog(
                            this,
                            "sql delete 语句出现错误",
                            "错误",
                            JOptionPane.ERROR_MESSAGE
                    );
                    throwables.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }
        }
    }

    // 更新学生信息窗口
    private void setStuInfoDlg(StuInfoDlg stuInfoDlg) {
        stuInfoDlg.name.setText(student.getsName());
        stuInfoDlg.stuId.setText(String.valueOf(student.getStuID()));
        stuInfoDlg.cId.setText(String.valueOf(student.getcID()));

        // 如果是更新窗口就更新性别选中状态
        if (stuInfoDlg.getClass().equals(UpdateDbDlg.class)) {
            if (student.getSex().equals("男"))
                stuInfoDlg.sexMan.setSelected(true);
            else
                stuInfoDlg.sexWoman.setSelected(true);
        }
        // 如果是删除窗口就性别只显示其一, 删除另一个
        else if (stuInfoDlg.getClass().equals(DeleteDbDlg.class)) {
            if (student.getSex().equals("男")) {
                stuInfoDlg.sexMan.setSelected(true);
                stuInfoDlg.group.remove(stuInfoDlg.sexWoman);
                stuInfoDlg.jPanelSex.remove(stuInfoDlg.sexWoman);
            } else {
                stuInfoDlg.sexWoman.setSelected(true);
                stuInfoDlg.group.remove(stuInfoDlg.sexMan);
                stuInfoDlg.jPanelSex.remove(stuInfoDlg.sexMan);
            }
        }
    }

    /*
     * 按姓名或者性别关键字查找学生并更新表格
     * int choice 值为0按姓名查找, 值为1按性别查找
     */
    private void FindByNameOrSex(int choice) {
        InputDlg inputDlg;
        if (choice == 0)
            inputDlg = new StuNameInputDlg(studentDAO);
        else
            inputDlg = new StuSexInputDlg(studentDAO);

        if (inputDlg.flag) {
            if (choice == 0)
                studentList = ((StuNameInputDlg) inputDlg).studentList;
            else
                studentList = ((StuSexInputDlg) inputDlg).studentList;
            // studentList = inputDlg.studentList;
            table = new JTable(new MyTableModel(studentList));
            table.setDefaultRenderer(Object.class, tcr);
            table.setRowSelectionInterval(0, studentList.size() - 1);
            jsPane.setViewportView(table);
        }
    }

    /* 按学号或者班级查找学生并更新表格
     * int choice 0为按学号查找, 1为按班级查找
     */
    private void FindByStuIdOrCId(int choice) {
        InputDlg inputDlg;
        if (choice == 0)
            inputDlg = new StuIdInputDlg(studentDAO);
        else
            inputDlg = new CIdInputDlg(studentDAO);
        if (inputDlg.flag) {
            int index = 0;
            if (choice == 0) {
                student = ((StuIdInputDlg) inputDlg).student;
                studentList.clear();
                studentList.add(student);
            } else {
                studentList = ((CIdInputDlg) inputDlg).studentList;
                index = studentList.size() - 1;
            }
            table = new JTable(new MyTableModel(studentList));
            table.setDefaultRenderer(Object.class, tcr);
            table.setRowSelectionInterval(0, index);
            jsPane.setViewportView(table);
        }
    }

    // 更新学生并更新表格
    private void UpdateStu() {
        try {
            StuIdInputDlg stuIdInputDlg = new StuIdInputDlg(studentDAO);
            if (stuIdInputDlg.flag) {
                student = stuIdInputDlg.student;
                UpdateDbDlg updateDlg = new UpdateDbDlg();
                setStuInfoDlg(updateDlg);

                updateDlg.setVisible(true);
                if (updateDlg.flag) {
                    updateStudent(updateDlg);
                    studentDAO.update(student);
                    FindDbAll();
                    int index = studentList.indexOf(student);
                    table.setRowSelectionInterval(index, index);
                }
            }
        } catch (SQLException throwables) {
            // System.out.println("sql update 语句错误");
            JOptionPane.showMessageDialog(
                    this,
                    "mysql update 语句错误",
                    "错误",
                    JOptionPane.ERROR_MESSAGE
            );
            throwables.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
    }

    // 使用学生信息窗口更新学生
    private void updateStudent(StuInfoDlg stuInfoDlg) {
        student.setsName(stuInfoDlg.name.getText());
        student.setSex(stuInfoDlg.sex);
        student.setcID(Integer.parseInt(stuInfoDlg.cId.getText()));
    }

    // 添加学生并更新表格
    private void AddStu() {
        try {
            AddDbDlg addDlg = new AddDbDlg();
            if (addDlg.flag) {
                student = new Student(
                        addDlg.name.getText(),
                        Integer.parseInt(addDlg.stuId.getText()),
                        Integer.parseInt(addDlg.cId.getText()),
                        addDlg.sex
                );
                studentDAO.add(student);
                FindDbAll();
                int index = studentList.indexOf(student);
                table.setRowSelectionInterval(index, index);
            }
        } catch (SQLException throwables) {
            // System.out.println("sql insert into错误");
            JOptionPane.showMessageDialog(this,
                    "添加学生错误！\n可能学号已经存在！",
                    "错误",
                    JOptionPane.ERROR_MESSAGE);
            throwables.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
    }

    // 查找全部学生并显示表格
    private void FindDbAll() {
        try {
            studentList = studentDAO.find();
            if (studentList.size() == 0) {
                jsPane.setViewportView(null);
                JOptionPane.showMessageDialog(
                        this,
                        "数据库中没有数据",
                        "提示",
                        JOptionPane.INFORMATION_MESSAGE
                );
                return;
            }

            table = new JTable(new MyTableModel(studentList));
            table.setDefaultRenderer(Object.class, tcr);        // 设置渲染器
            jsPane.setViewportView(table);
        } catch (SQLException throwables) {
            JOptionPane.showMessageDialog(
                    this,
                    "mysql select 语句错误",
                    "错误",
                    JOptionPane.ERROR_MESSAGE
            );
            throwables.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        int n = JOptionPane.showConfirmDialog(this,
                "是否退出程序？",
                "退出？",
                JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION)
            System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {
        System.out.println("test");
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
