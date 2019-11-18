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
import model.KhachHang;
import service.DoiTacService;
import service.Notification;
import service.PaginatedList;
import service.KhachHangService;

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

public class KhachHangController implements Initializable {
    @FXML
    JFXTextField clientSearchTextField;
    @FXML
    JFXTextField txtName;
    @FXML
    JFXTextField txtDiaChi;
    @FXML
    JFXTextField txtSDT;
    @FXML
    JFXTextField txtEmail;
    public KhachHang khachHang = new KhachHang();
    List<KhachHang> khachHangList = new ArrayList<>();
    @FXML
    private TableView<KhachHang> tableView;
    @FXML
    private TableColumn<KhachHang, String> colID;
    @FXML
    private TableColumn<KhachHang, String> colName;
    @FXML
    private TableColumn<KhachHang, String> colDiaChi;
    @FXML
    private TableColumn<KhachHang, String> colGioiTinh;
    @FXML
    private TableColumn<KhachHang, String> colSDT;
    @FXML
    private TableColumn<KhachHang, String> colEmail;
    private ObservableList<KhachHang> khachHangs = FXCollections.observableArrayList();
    @FXML
    private JFXButton btnEdit;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnAdd;
    @FXML
    Pagination clientPagination;
    PaginatedList<KhachHang> pagingList;
    @FXML
    JFXTabPane tabView;
    Notification notification = new Notification();
    @FXML
    ToggleGroup q;

    @FXML
    private RadioButton rdNam;

    @FXML
    private RadioButton rdNu;

    String EMAIL_REGEX = "^(.+)@(.+)$";
    String PHONE_REGEX = "^[0]+[9,12,16,18,19]+[0-9]{8}$";
    Pattern emailPattern = Pattern.compile(EMAIL_REGEX);
    Pattern phonePattern = Pattern.compile(PHONE_REGEX);
    Matcher matcher;

    public KhachHangController() throws SQLException {
    }

//    public void getNhanVien(NhanVien nhanVien) {
//        this.nhanVien = nhanVien;
//    }

    @FXML
    public void filter() {
        String search = clientSearchTextField.getText();
        System.out.println(search);
        setTableView();
        List<KhachHang> result;
        result = khachHangs.stream()
                .filter(item -> String.valueOf(item.getIdKhachHang()).contains(search)
                        || item.getTenKhachHang().contains(search)
                        || item.getEmail().contains(search)
                        || item.getSdt().contains(search)
                        || item.getDiaChi().contains(search)
                )
                .collect(Collectors.toList());
        khachHangs = FXCollections.observableList(result);
        tableView.setItems(khachHangs);
        if (clientSearchTextField.getText().isEmpty()) {
            setTableView(0);
        }
    }

    private Node setTableView(Integer integer) {
        setTableView();
        int fromIndex = integer * 10;
        int toIndex = Math.min(fromIndex + 10, khachHangs.size());
        ObservableList<KhachHang> khachHangObservableArray = FXCollections.observableArrayList(khachHangs.subList(fromIndex, toIndex));
        tableView.setItems(khachHangs);
        return tableView;
    }

    public Node setTableView() {
        btnDelete.setDisable(true);
        btnEdit.setDisable(true);
        tableView.getItems().clear();
        khachHangList.clear();
        khachHangList = KhachHangService.getAll();
        pagingList = new PaginatedList<>(khachHangList);
        clientPagination.setPageCount(pagingList.listOfPages.size());
        khachHangs = FXCollections.observableList(khachHangList);
        colID.setCellValueFactory(new PropertyValueFactory<>("idKhachHang"));
        colName.setCellValueFactory(new PropertyValueFactory<>("tenKhachHang"));
        colGioiTinh.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
        colDiaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        colSDT.setCellValueFactory(new PropertyValueFactory<>("sdt"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tableView.setItems(khachHangs);
        return tableView;
    }

    public void selectItem() throws SQLException, IOException {
        btnEdit.setDisable(false);
        btnDelete.setDisable(false);
    }

    public void changeStage(ActionEvent actionEvent) throws IOException, SQLException {
        SingleSelectionModel<Tab> selectionModel = tabView.getSelectionModel();
        selectionModel.select(1);
        txtName.setText(this.khachHang.getTenKhachHang());
        txtDiaChi.setText(this.khachHang.getDiaChi());
        txtEmail.setText(this.khachHang.getEmail());
        txtSDT.setText(this.khachHang.getSdt());
        if (this.khachHang.getGioiTinh() != null && !this.khachHang.getGioiTinh()) {
            rdNu.setSelected(true);
        }
    }

    public void onSave(ActionEvent actionEvent) throws SQLException, FileNotFoundException {
        matcher = emailPattern.matcher(txtEmail.getText());
        if (!matcher.matches()) {
            notification.notification("Lỗi", "email không hợp lê", 1);
            return;
        }
        matcher = phonePattern.matcher(txtSDT.getText());
        if (!matcher.matches()) {
            notification.notification("Lỗi", "phone không hợp lê", 1);
            return;
        }
        boolean isMale = true;
        RadioButton selectedRadioButton = (RadioButton) q.getSelectedToggle();
        String gioiTinh = selectedRadioButton.getText();
        if (gioiTinh.equals("Nữ")) isMale = false;
        KhachHang khachHang = new KhachHang(this.khachHang.getIdKhachHang(), txtName.getText(), isMale, txtSDT.getText(),
                txtEmail.getText(), txtDiaChi.getText());

        khachHang.setIdKhachHang(this.khachHang.getIdKhachHang());
        KhachHangService.save(khachHang);
        notification.notification("Save thành công", "Đã lưu vào database", 0);
        setTableView();
    }

    public void edit(ActionEvent actionEvent) throws IOException, SQLException {
        this.khachHang = tableView.getSelectionModel().getSelectedItem();
        changeStage(actionEvent);
    }

    public void deleteItem(ActionEvent actionEvent) throws SQLException {
        KhachHang khachHang = tableView.getSelectionModel().getSelectedItem();
        KhachHangService.deleteByMaKhachHang(String.valueOf(khachHang.getIdKhachHang()));
        setTableView();
    }

    public void printIntoExcel() throws IOException {
//        excel.khachHangs = this.khachHangList;
//        excel.printKhachHangToExcel();
    }

    public void creatNew(ActionEvent actionEvent) throws IOException, SQLException {
        this.khachHang = new KhachHang();
        changeStage(actionEvent);
    }

    @FXML
    public void onCancel(ActionEvent actionEvent) {
        SingleSelectionModel<Tab> selectionModel = tabView.getSelectionModel();
        selectionModel.select(0);
        this.khachHang = new KhachHang();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableView();
        clientPagination.setPageFactory(this::setTableView);
//        if (this.khachHang.getIdDoiTac() != null) {
//            comIDDoiTac.getSelectionModel().select(this.khachHang.getIdDoiTac());
//        }
    }

}
