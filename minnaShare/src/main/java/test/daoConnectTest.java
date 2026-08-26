package test;

import dao.StaffDAO;
import model.Staff;

public class daoConnectTest {
    public static void main(String[] args) {
        System.out.println("--- データベース接続テストを開始します ---");

        // 1. StaffDAOのインスタンスを生成
        StaffDAO dao = new StaffDAO();

        // 2. 登録されているテストデータ（test01 / Password01）で検索を実行
        String targetId = "test01";
        String targetPass = "Password01";
        
        System.out.println("検索対象ユーザーID: " + targetId);
        Staff staff = dao.findByLogin(targetId, targetPass);

        // 3. 結果の判定
        if (staff != null) {
            System.out.println("【成功】データベースへの接続、およびデータの取得に成功しました！");
            System.out.println("取得したスタッフ名: " + staff.getStaffName());
        } else {
            System.out.println("【失敗】データが取得できませんでした。");
            System.out.println("原因の可能性:");
            System.out.println("1. StaffDAO内のJDBC_URL、ユーザー名、パスワードが間違っている");
            System.out.println("2. H2 Databaseが起動していない、またはデータがまだ挿入されていない");
            System.out.println("3. 接続URLのパスが異なり、別の空のデータベースを見に行っている");
        }
        
        System.out.println("--- データベース接続テストを終了します ---");
    }
}
