package tanjian.jdbc;

import java.sql.*;

public class MySqlJDBC {
    private String url;
    private String user;
    private String password;

    private void setUrl(String dbName) {
        this.url = "jdbc:mysql://localhost:3306/";
        url = url + dbName;
        url = url + "?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8&rewriteBatchedStatements=true";
    }

    public MySqlJDBC(String dbName, String user, String password) {
        setUrl(dbName);
        this.user = user;
        this.password = password;
    }

    public Connection getCon() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, user, password);
        return con;
    }

    public void close(Connection con, ResultSet rs, Statement st) throws SQLException {
        if (con != null)
            con.close();
        if (rs != null)
            rs.close();
        if (st != null)
            st.close();
    }
}
