package tanjian;

public class Student {
    // 学生姓名
    private String sName;

    // 学生学号
    private int stuID;

    // 学生班级编号
    private int cID;

    // 学生性别
    private String sex;

    public Student() {
    }

    public Student(String sName, int stuID, int cID, String sex) {
        this.sName = sName;
        this.stuID = stuID;
        this.cID = cID;
        this.sex = sex;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public int getStuID() {
        return stuID;
    }

    public void setStuID(int stuID) {
        this.stuID = stuID;
    }

    public int getcID() {
        return cID;
    }

    public void setcID(int cID) {
        this.cID = cID;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return stuID == student.stuID;
    }
}
