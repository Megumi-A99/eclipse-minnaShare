package model;

import dao.StaffDAO;

public class LoginLogic {
    
    /**
     * ログイン処理を実行するメソッド
     * @param userId ユーザーID
     * @param password パスワード
     * @return ログイン成功時はStaffインスタンス、失敗時はnull
     */
    public Staff execute(String userId, String password) {
        // DAOをインスタンス化してデータベース照合を行う
        StaffDAO dao = new StaffDAO();
        Staff staff = dao.findByLogin(userId, password);
        
        return staff;
    }
}