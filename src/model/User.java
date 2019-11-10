package model;

public class User {
    private int idUser;
    private String nameUser; // tên người dùng
    private String userName;      // tên đăng nhập
    private String passWord;
    private String address;
    private String numberPhone;
    private String email;
    private Boolean gender;
    private String chucVu;
    private int role;

    public User() {
    }

    public User(int idUser, String nameUser, String userName, String passWord, String address, String numberPhone, String email, Boolean gender, String chucVu, int role) {
        this.idUser = idUser;
        this.nameUser = nameUser;
        this.userName = userName;
        this.passWord = passWord;
        this.address = address;
        this.numberPhone = numberPhone;
        this.email = email;
        this.gender = gender;
        this.chucVu = chucVu;
        this.role = role;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
