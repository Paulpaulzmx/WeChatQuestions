package wechat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;

    public DBConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            this.connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/wechat?user=root&password=zdhwjfln&useUnicode=true&characterEncoding=utf8");
            this.statement = this.connect.createStatement();
            System.out.println("db connection ok!");
        } catch (SQLException var2) {
            System.out.println("MySQL Error");
            var2.printStackTrace();
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public ResultSet executeQuery(String strSQL) {
        ResultSet resultSet = null;

        try {
            System.out.println(strSQL);
            if (this.statement == null) {
                System.out.println("statement is null!!");
            } else {
                resultSet = this.statement.executeQuery(strSQL);
            }
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return resultSet;
    }

    public boolean execute(String strSQL) {
        try {
            System.out.println(strSQL);
            int row = this.statement.executeUpdate(strSQL);
            System.out.println("row=" + row);
        } catch (SQLException var3) {
            var3.printStackTrace();
        }

        return true;
    }

    public void close() {
        try {
            if (this.statement != null) {
                this.statement.close();
            }

            if (this.connect != null) {
                this.connect.close();
            }
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

    }

    public static void main(String[] args) throws SQLException {
        DBConnection b = new DBConnection();
        ResultSet rs = b.executeQuery("select * from student where ID = '123'");

        while(rs.next()) {
            System.out.println(rs.getString(2));
        }

        b.close();
    }
}
