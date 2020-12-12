package tanjian.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DeleteDbDlg extends StuInfoDlg {

    public DeleteDbDlg() {
        super();
        setTitle("删除数据");
        stuId.setEditable(false);
        name.setEditable(false);
        cId.setEditable(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ok) {
            int n = JOptionPane.showConfirmDialog(this, "确认删除该条数据吗？", "警告", JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION)
                flag = true;
            setVisible(false);
        }
        else if (e.getSource() == cancel) {
            setVisible(false);      // 隐藏窗口
        }
    }
}
