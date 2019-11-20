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
import model.DanhGiaKhachHang;
import org.controlsfx.control.Rating;
import service.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class DanhGiaController implements Initializable {
    @FXML
    JFXTextField clientSearchTextFieldd;

    List<DanhGiaKhachHang> danhGiaKhachHangList = new ArrayList<>();
    @FXML
    private TableView<DanhGiaKhachHang> tableView;
    @FXML
    private TableColumn<DanhGiaKhachHang, String> colIDKhachHang;
    @FXML
    private TableColumn<DanhGiaKhachHang, String> colSoSao;
    @FXML
    private TableColumn<DanhGiaKhachHang, String> colNhanXet;
    @FXML
    private TableColumn<DanhGiaKhachHang, String> colSanPham;
    private ObservableList<DanhGiaKhachHang> danhGiaKhachHangs = FXCollections.observableArrayList();
    @FXML
    private JFXButton btnEdit;
    @FXML
    private JFXButton btnDelete;
    @FXML
    Pagination clientPagination;
    PaginatedList<DanhGiaKhachHang> pagingList;
    @FXML
    private JFXTextArea txtNhanXet;
    @FXML
    private JFXComboBox comIdSanPham;
    @FXML
    private JFXComboBox comIdKhachHang;
    @FXML
    JFXTabPane tabView;
    @FXML
    Rating rating;

    private DanhGiaKhachHang danhGiaKhachHang;

    Notification notification = new Notification();

    public DanhGiaController() throws SQLException {
    }

    @FXML
    public void filter() {
        String search = clientSearchTextFieldd.getText();
        setTableView();
        List<DanhGiaKhachHang> result;
        result = danhGiaKhachHangs.stream()
                .filter(item -> String.valueOf(item.getIdSanPham()).contains(search)
                        || item.getNhanXet().contains(search)
                )
                .collect(Collectors.toList());
        danhGiaKhachHangs = FXCollections.observableList(result);
        tableView.setItems(danhGiaKhachHangs);
        if (clientSearchTextFieldd.getText().isEmpty()) {
            setTableView(0);
        }
    }

    private Node setTableView(Integer integer) {
        setTableView();
        int fromIndex = integer * 10;
        int toIndex = Math.min(fromIndex + 10, danhGiaKhachHangs.size());
        tableView.setItems(danhGiaKhachHangs);
        return tableView;
    }

    public Node setTableView() {
//        btnDelete.setDisable(true);
//        btnEdit.setDisable(true);
        tableView.getItems().clear();
        danhGiaKhachHangList.clear();
        danhGiaKhachHangList = DanhGiaKhachHangService.getAll();
        pagingList = new PaginatedList<>(danhGiaKhachHangList);
        clientPagination.setPageCount(pagingList.listOfPages.size());
        danhGiaKhachHangs = FXCollections.observableList(danhGiaKhachHangList);
        colIDKhachHang.setCellValueFactory(new PropertyValueFactory<>("idKhachHang"));
        colSoSao.setCellValueFactory(new PropertyValueFactory<>("soSaoBinhChon"));
        colNhanXet.setCellValueFactory(new PropertyValueFactory<>("nhanXet"));
        colSanPham.setCellValueFactory(new PropertyValueFactory<>("idSanPham"));
        tableView.setItems(danhGiaKhachHangs);
        return tableView;
    }

    public void selectItem() throws SQLException, IOException {
        DanhGiaKhachHang NV = tableView.getSelectionModel().getSelectedItem();
        btnEdit.setDisable(false);
        btnDelete.setDisable(false);
    }

    public void creatNew(ActionEvent actionEvent) throws IOException, SQLException {
        this.danhGiaKhachHang = new DanhGiaKhachHang();
        changeStage();
    }

    public void changeStage() throws IOException, SQLException {
        SingleSelectionModel<Tab> selectionModel = tabView.getSelectionModel();
        selectionModel.select(1);
        if (this.danhGiaKhachHang.getNhanXet() != null) {
            txtNhanXet.setText(this.danhGiaKhachHang.getNhanXet());
        }
        comIdSanPham.getSelectionModel().select(this.danhGiaKhachHang.getIdSanPham());
        comIdKhachHang.getSelectionModel().select(this.danhGiaKhachHang.getIdKhachHang());
        rating.setRating(this.danhGiaKhachHang.getSoSaoBinhChon());
    }

    public void edit(ActionEvent actionEvent) throws IOException, SQLException {
        this.danhGiaKhachHang = tableView.getSelectionModel().getSelectedItem();
        changeStage();
    }

    public void deleteItem(ActionEvent actionEvent) throws SQLException {
        DanhGiaKhachHang DanhGiaKhachHang = tableView.getSelectionModel().getSelectedItem();
        DanhGiaKhachHangService.deleteByMaDanhGiaKhachHang(String.valueOf(DanhGiaKhachHang.getIdKhachHang()));
        setTableView();
    }

    public void onSave(ActionEvent actionEvent) throws SQLException, IOException {
        String sanPhamId = "Chưa đặt";
        if (comIdSanPham.getSelectionModel().selectedItemProperty().getValue() != null) {
            sanPhamId = comIdSanPham.getSelectionModel().selectedItemProperty().getValue().toString();
        }
        String IDkhachHang = "";
        if (comIdKhachHang.getSelectionModel().selectedItemProperty().getValue() != null) {
            IDkhachHang = comIdKhachHang.getSelectionModel().selectedItemProperty().getValue().toString();
        }
        DanhGiaKhachHang danhGiaKhachHang = new DanhGiaKhachHang();
        danhGiaKhachHang.setIdKhachHang(this.danhGiaKhachHang.getIdKhachHang());
        danhGiaKhachHang.setSoSaoBinhChon((int) Math.round(rating.getRating()));
        danhGiaKhachHang.setNhanXet(txtNhanXet.getText());
        danhGiaKhachHang.setIdSanPham(Integer.valueOf(sanPhamId));
        if (!IDkhachHang.equals("")) {
            danhGiaKhachHang.setIdKhachHang(Integer.parseInt(IDkhachHang));
        }
        DanhGiaKhachHangService.save(danhGiaKhachHang);
        notification.notification("Save thành công", "Đã lưu vào database", 0);
        setTableView();
    }

    public void printIntoExcel() throws IOException {
//        excel.danhGiaKhachHang = this.DanhGiaKhachHangList;
//        excel.printDanhGiaKhachHangToExcel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableView();
        clientPagination.setPageFactory(this::setTableView);
        List<String> listProjectId = ProductService.getAllID();
        ObservableList<String> doiTacData = FXCollections.observableArrayList(listProjectId);
        comIdSanPham.setItems(doiTacData);
        List<String> listKhachHangId = KhachHangService.getAllID();
        ObservableList<String> khachHangdata = FXCollections.observableArrayList(listKhachHangId);
        comIdKhachHang.setItems(khachHangdata);
    }
}
