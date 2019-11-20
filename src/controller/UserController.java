package controller;

import com.jfoenix.controls.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.User;
import service.DoiTacService;
import service.Notification;
import service.PaginatedList;
import service.UserService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class UserController implements Initializable {
    @FXML
    JFXTextField clientSearchTextField;
    @FXML
    JFXTextField txtName;
    @FXML
    JFXTextField txttendangnhap;
    @FXML
    JFXTextField txtpassword;
    @FXML
    JFXTextField txtEmail;
    @FXML
    JFXTextField txtDiachi;
    @FXML
    JFXTextField txtRole;
    @FXML
    JFXTextField txtSdt;
    @FXML
    JFXRadioButton rdnam;
    @FXML
    JFXRadioButton rdnu;
    @FXML
    JFXComboBox comChucvu;
    @FXML
    ToggleGroup q;
    public User user = new User();
    List<User> userList = new ArrayList<>();
    @FXML
    private TableView<User> tableView;
    @FXML
    private TableColumn<User, String> colID;
    @FXML
    private TableColumn<User, String> colName;
    @FXML
    private TableColumn<User, String> coltendangnhap;
    @FXML
    private TableColumn<User, String> colPass;
    @FXML
    private TableColumn<User, String> colDiachi;
    @FXML
    private TableColumn<User, String> colSdt;
    @FXML
    private TableColumn<User, String> colEmail;
    @FXML
    private TableColumn<User, String> colGioitinh;
    @FXML
    private TableColumn<User, String> colChucvu;
    @FXML
    private TableColumn<User, String> colRole;


    private ObservableList<User> users = FXCollections.observableArrayList();
    @FXML
    private JFXButton btnEdit;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnAdd;
    @FXML
    Pagination clientPagination;
    PaginatedList<User> pagingList;
    @FXML
    JFXTabPane tabView;
    Notification notification = new Notification();


    String EMAIL_REGEX = "^(.+)@(.+)$";
    String PHONE_REGEX = "^[0]+[9,12,16,18,19]+[0-9]{8}$";
    Pattern emailPattern = Pattern.compile(EMAIL_REGEX);
    Pattern phonePattern = Pattern.compile(PHONE_REGEX);
    Matcher matcher;

    public UserController() throws SQLException {
    }

//    public void getNhanVien(NhanVien nhanVien) {
//        this.nhanVien = nhanVien;
//    }

    @FXML
    public void filter() {
        String search = clientSearchTextField.getText();
        System.out.println(search);
        setTableView();
        List<User> result;
        result = users.stream()
                .filter(item -> String.valueOf(item.getIdUser()).contains(search)
                        || item.getNameUser().contains(search)
                        || item.getEmail().contains(search)
                        || item.getNumberPhone().contains(search)
                        || item.getUserName().contains(search)
                )
                .collect(Collectors.toList());
        users = FXCollections.observableList(result);
        tableView.setItems(users);
        if (clientSearchTextField.getText().isEmpty()) {
            setTableView(0);
        }
    }

    private Node setTableView(Integer integer) {
        setTableView();
        int fromIndex = integer * 10;
        int toIndex = Math.min(fromIndex + 10, users.size());
        ObservableList<User> userObservableArray = FXCollections.observableArrayList(users.subList(fromIndex, toIndex));
        tableView.setItems(users);
        return tableView;
    }

    public Node setTableView() {
        btnDelete.setDisable(true);
        btnEdit.setDisable(true);
        tableView.getItems().clear();
        userList.clear();
        userList = UserService.getAll();
        pagingList = new PaginatedList<>(userList);
        clientPagination.setPageCount(pagingList.listOfPages.size());
        users = FXCollections.observableList(userList);
        colID.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        colName.setCellValueFactory(new PropertyValueFactory<>("NameUser"));
        colChucvu.setCellValueFactory(new PropertyValueFactory<>("ChucVu"));
        colDiachi.setCellValueFactory(new PropertyValueFactory<>("address"));
        colGioitinh.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        colPass.setCellValueFactory(new PropertyValueFactory<>("passWord"));
        colSdt.setCellValueFactory(new PropertyValueFactory<>("numberPhone"));
        coltendangnhap.setCellValueFactory(new PropertyValueFactory<>("UserName"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        tableView.setItems(users);
        return tableView;
    }

    public void selectItem() throws SQLException, IOException {
        btnEdit.setDisable(false);
        btnDelete.setDisable(false);
    }

    public void changeStage(ActionEvent actionEvent) throws IOException, SQLException {
        SingleSelectionModel<Tab> selectionModel = tabView.getSelectionModel();
        selectionModel.select(1);
        txtName.setText(this.user.getNameUser());
        txttendangnhap.setText(this.user.getUserName());
        txtEmail.setText(this.user.getEmail());
        txtSdt.setText(this.user.getNumberPhone());
        txtDiachi.setText(this.user.getAddress());
        txtpassword.setText(this.user.getPassWord());
        comChucvu.getSelectionModel().select(this.user.getChucVu());
        rdnam.setSelected(true);
        if (this.user.getGender() != null && !this.user.getGender()) {
            rdnu.setSelected(true);
        }
    }

    public void onSave(ActionEvent actionEvent) throws SQLException, FileNotFoundException {
        matcher = emailPattern.matcher(txtEmail.getText());
        if (!matcher.matches()) {
            notification.notification("Lỗi", "email không hợp lê", 1);
            return;
        }
        matcher = phonePattern.matcher(txtSdt.getText());
        if (!matcher.matches()) {
            notification.notification("Lỗi", "phone không hợp lê", 1);
            return;
        }
        boolean isMale = true;
        String chucvu = "Chưa đặt";
        if (comChucvu.getSelectionModel().selectedItemProperty().getValue() != null) {
            chucvu = comChucvu.getSelectionModel().selectedItemProperty().getValue().toString();
        }
        RadioButton selectedRadioButton = (RadioButton) q.getSelectedToggle();
        String gioiTinh = selectedRadioButton.getText();
        if (gioiTinh.equals("Nữ")) isMale = false;
        User user = new User
                (this.user.getIdUser(), txtName.getText(), txttendangnhap.getText(),
                txtpassword.getText(), txtDiachi.getText(), txtSdt.getText(), txtEmail.getText(), isMale ,chucvu);

        user.setIdUser(this.user.getIdUser());
        UserService.save(user);
        notification.notification("Save thành công", "Đã lưu vào database", 0);
        setTableView();
    }

    public void edit(ActionEvent actionEvent) throws IOException, SQLException {
        this.user = tableView.getSelectionModel().getSelectedItem();
        changeStage(actionEvent);
    }

    public void deleteItem(ActionEvent actionEvent) throws SQLException {
        User user = tableView.getSelectionModel().getSelectedItem();
        UserService.deleteByMaUser(String.valueOf(user.getIdUser()));
        setTableView();
    }

    public void printIntoExcel() throws IOException {
//        excel.users = this.userList;
//        excel.printUserToExcel();
    }

    public void creatNew(ActionEvent actionEvent) throws IOException, SQLException {
        this.user = new User();
        changeStage(actionEvent);
    }

    @FXML
    public void onCancel(ActionEvent actionEvent) {
        SingleSelectionModel<Tab> selectionModel = tabView.getSelectionModel();
        selectionModel.select(0);
        this.user = new User();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableView();
        clientPagination.setPageFactory(this::setTableView);
//        if (this.user.getIdDoiTac() != null) {
//            comIDDoiTac.getSelectionModel().select(this.user.getIdDoiTac());
//        }
    }

}
