package model;//スタッフの情報を保管するJavaBeans

import java.io.Serializable;

public class Staff implements Serializable {
    private String userId;
    private String password;
    private String staffName;

    public Staff() {}
    public Staff(String userId, String password, String staffName) {
        this.userId = userId;
        this.password = password;
        this.staffName = staffName;
    }
    // 以下、各フィールドのgetterとsetterを作成
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getStaffName() { return staffName; }
    public void setStaffName(String staffName) { this.staffName = staffName; }
}