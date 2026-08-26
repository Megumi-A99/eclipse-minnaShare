package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Staff;

public class StaffDAO {
    // H2データベースの接続情報
    private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/minnaShare";
    private final String DB_USER = "sa";
    private final String DB_PASS = "";
    
    //ここから重要なデータベース照合の処理を書く。
    //引数はログイン画面で入力されたユーザIDとパスワードの二つ
    //(LoginServletがリクエストパラメータを取得→LoginLogicが引数送ってくれるからOK)
    //戻り値はstaffインスタンスになるよ。
    public Staff findByLogin(String userId, String password) {
        Staff staff = null;
        //JDBCドライバの読み込みをする
        try {
            Class.forName("org.h2.Driver");//これはH2DBの翻訳係として今からJDBCドライバを読み込みますよーという宣言みたいなもの。
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            // SELECT文の準備
            String sql = "SELECT user_id, password, staff_name FROM staff WHERE user_id = ? AND password = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, userId);
            pStmt.setString(2, password);

            // SELECTを実行し、結果表を取得
            ResultSet rs = pStmt.executeQuery();

            if (rs.next()) {
                // 見つかった場合はStaffインスタンスを生成
                String id = rs.getString("user_id");
                String pass = rs.getString("password");
                String name = rs.getString("staff_name");
                staff = new Staff(id, pass, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return staff;
    }
}