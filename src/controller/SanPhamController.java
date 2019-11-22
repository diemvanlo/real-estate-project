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
import model.Product;
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

public class SanPhamController implements Initializable {
    @FXML
    JFXTextField clientSearchTextFieldd;

    List<Product> productList = new ArrayList<>();
    @FXML
    private TableView<Product> tableView;
    @FXML
    private TableColumn<Product, String> colID;
    @FXML
    private TableColumn<Product, String> colName;
    @FXML
    private TableColumn<Product, String> colidDuAn;
    @FXML
    private TableColumn<Product, String> colDiaChi;
    @FXML
    private TableColumn<Product, String> colDienTich;
    @FXML
    private TableColumn<Product, String> colGiaTien;
    @FXML
    private TableColumn<Product, String> colMoTa;
    @FXML
    private TableColumn<Product, String> colNgayTao;
    @FXML
    private TableColumn<Product, String> colNgayBan;
    @FXML
    private TableColumn<Product, String> colTienDo;
    @FXML
    private TableColumn<Product, String> colIDKhachHang;
    private ObservableList<Product> products = FXCollections.observableArrayList();
    @FXML
    private JFXButton btnEdit;
    @FXML
    private JFXButton btnDelete;
    @FXML
    Pagination clientPagination;
    PaginatedList<Product> pagingList;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtDiaChi;
    @FXML
    private JFXTextField txtDienTich;
    @FXML
    private JFXTextField txtGiaTien;
    @FXML
    private JFXTextField txtMoTa;
    @FXML
    private JFXComboBox comIdDuAn;
    @FXML
    private JFXDatePicker dateStart;
    @FXML
    private JFXDatePicker dateEnd;
    @FXML
    private JFXSlider slideTienDo;
    @FXML
    private JFXComboBox comTinhTrang;
    @FXML
    private JFXComboBox comIdKhachHang;
    @FXML
    JFXTabPane tabView;
    private Product product;

    Notification notification = new Notification();

    public SanPhamController() throws SQLException {
    }

    @FXML
    public void filter() {
        String search = clientSearchTextFieldd.getText();
        setTableView();
        List<Product> result;
        result = products.stream()
                .filter(item -> String.valueOf(item.getIdSanPham()).contains(search)
                        || item.getTenSanPham().contains(search)
                        || item.getDiaChi().contains(search)
                        || item.getMoTa().contains(search)
                )
                .collect(Collectors.toList());
        products = FXCollections.observableList(result);
        tableView.setItems(products);
        if (clientSearchTextFieldd.getText().isEmpty()) {
            setTableView(0);
        }
    }

    private Node setTableView(Integer integer) {
        setTableView();
        int fromIndex = integer * 10;
        int toIndex = Math.min(fromIndex + 10, products.size());
        tableView.setItems(products);
        return tableView;
    }

    public Node setTableView() {
//        btnDelete.setDisable(true);
//        btnEdit.setDisable(true);
        tableView.getItems().clear();
        productList.clear();
        productList = ProductService.getAll();
        pagingList = new PaginatedList<>(productList);
        clientPagination.setPageCount(pagingList.listOfPages.size());
        products = FXCollections.observableList(productList);
        colID.setCellValueFactory(new PropertyValueFactory<>("idSanPham"));
        colName.setCellValueFactory(new PropertyValueFactory<>("tenSanPham"));
        colidDuAn.setCellValueFactory(new PropertyValueFactory<>("idDuAn"));
        colDiaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        colDienTich.setCellValueFactory(new PropertyValueFactory<>("dienTich"));
        colGiaTien.setCellValueFactory(new PropertyValueFactory<>("giaTien"));
        colMoTa.setCellValueFactory(new PropertyValueFactory<>("moTa"));
        colNgayTao.setCellValueFactory(new PropertyValueFactory<>("ngayTao"));
        colNgayBan.setCellValueFactory(new PropertyValueFactory<>("ngayBan"));
        colTienDo.setCellValueFactory(new PropertyValueFactory<>("tienDo"));
        colIDKhachHang.setCellValueFactory(new PropertyValueFactory<>("idKhachHang"));
        tableView.setItems(products);
        return tableView;
    }

    public void selectItem() throws SQLException, IOException {
        Product NV = tableView.getSelectionModel().getSelectedItem();
        btnEdit.setDisable(false);
        btnDelete.setDisable(false);
    }

    public void creatNew(ActionEvent actionEvent) throws IOException, SQLException {
        this.product = new Product();
        changeStage();
    }

    public void changeStage() throws IOException, SQLException {
        SingleSelectionModel<Tab> selectionModel = tabView.getSelectionModel();
        selectionModel.select(1);
        if (this.product.getTenSanPham() != null) {
            txtName.setText(this.product.getTenSanPham());
        }
        txtDiaChi.setText(this.product.getDiaChi());
        if (this.product.getDienTich() != null) {
            txtDienTich.setText(Double.valueOf(this.product.getDienTich()).toString());
        }
        if (this.product.getGiaTien() != null) {
            txtGiaTien.setText(Double.valueOf(this.product.getGiaTien()).toString());
        }
        txtMoTa.setText(this.product.getMoTa());
        comIdDuAn.getSelectionModel().select(this.product.getIdDuAn());

        comTinhTrang.getSelectionModel().select(this.product.getTrangThai());
        if (this.product.getTrangThai() == null) {
            comTinhTrang.getSelectionModel().select("Chưa bán");
        }
        System.out.println(this.product.getTrangThai());
        if (this.product.getTrangThai() != null && this.product.getTrangThai().equalsIgnoreCase("Đã bán")) {
            comIdKhachHang.setVisible(true);
        }
        if (this.product.getNgayTao() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDateStart = LocalDate.parse(this.product.getNgayTao(), formatter);
            dateStart.setValue(localDateStart);
            LocalDate localDateEnd = LocalDate.parse(this.product.getNgayBan(), formatter);
            dateEnd.setValue(localDateEnd);
        } else {
            dateStart.setValue(LocalDate.of(Calendar.getInstance().get(Calendar.YEAR),
                    Calendar.getInstance().get(Calendar.MONTH) + 1,
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH)));
            dateEnd.setValue(LocalDate.of(Calendar.getInstance().get(Calendar.YEAR) + 2,
                    Calendar.getInstance().get(Calendar.MONTH) + 1,
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH)));
        }
    }

    public void edit(ActionEvent actionEvent) throws IOException, SQLException {
        this.product = tableView.getSelectionModel().getSelectedItem();
        changeStage();
    }

    public void deleteItem(ActionEvent actionEvent) throws SQLException {
        Product Product = tableView.getSelectionModel().getSelectedItem();
        ProductService.deleteByMaProduct(String.valueOf(Product.getIdSanPham()));
        setTableView();
    }

    public void onSave(ActionEvent actionEvent) throws SQLException, IOException {
        String tinhTrang = "Chưa đặt";
        if (comTinhTrang.getSelectionModel().selectedItemProperty().getValue() != null) {
            tinhTrang = comTinhTrang.getSelectionModel().selectedItemProperty().getValue().toString();
        }
        String duAnId = "Chưa đặt";
        if (comIdDuAn.getSelectionModel().selectedItemProperty().getValue() != null) {
            duAnId = comIdDuAn.getSelectionModel().selectedItemProperty().getValue().toString();
        }
        String IDkhachHang = "";
        if (comIdKhachHang.getSelectionModel().selectedItemProperty().getValue() != null) {
            IDkhachHang = comIdKhachHang.getSelectionModel().selectedItemProperty().getValue().toString();
        }
        Product sanPham = new Product();
        sanPham.setIdSanPham(this.product.getIdSanPham());
        sanPham.setTenSanPham(txtName.getText());
        sanPham.setIdDuAn(Integer.parseInt(duAnId));
        sanPham.setDiaChi(txtDiaChi.getText());
        sanPham.setDienTich(Double.parseDouble(txtDienTich.getText()));
        sanPham.setGiaTien(Double.parseDouble(txtGiaTien.getText()));
        sanPham.setMoTa(txtMoTa.getText());
        sanPham.setNgayTao(dateStart.getValue().toString());
        sanPham.setNgayBan(dateEnd.getValue().toString());
        sanPham.setTienDo(slideTienDo.getValue() + "%");
        sanPham.setTrangThai(tinhTrang);
        if (!IDkhachHang.equals("")) {
            sanPham.setIdKhachHang(Integer.parseInt(IDkhachHang));
        }
        ProductService.save(sanPham);
        notification.notification("Save thành công", "Đã lưu vào database", 0);
        setTableView();
    }

    public void printIntoExcel() throws IOException {
//        excel.product = this.ProductList;
//        excel.printProductToExcel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableView();
        btnDelete.setDisable(true);
        btnEdit.setDisable(true);
        clientPagination.setPageFactory(this::setTableView);
        List<String> listProjectId = ProjectService.getAllID();
        ObservableList<String> doiTacData = FXCollections.observableArrayList(listProjectId);
        comIdDuAn.setItems(doiTacData);
        List<String> listKhachHangId = KhachHangService.getAllID();
        ObservableList<String> khachHangdata = FXCollections.observableArrayList(listKhachHangId);
        comIdKhachHang.setItems(khachHangdata);
        comTinhTrang.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("Đã bán")) {
                    comIdKhachHang.setVisible(true);
                } else {
                    comIdKhachHang.setVisible(false);
                }
            }
        });
        txtDienTich.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                txtDienTich.setText(newValue.replaceAll("[^\\d*|\\d+\\,\\d]", ""));
                if (!newValue.matches("\\d*|\\d+\\,\\d*") && !newValue.contains(".")) {
                    txtDienTich.setText(newValue.replaceAll("[^\\d*|\\d+\\,\\d]", ""));
                    notification.notification("Ký tự nhập không phải là số", "Vui lòng nhập lại", 1);
                }
                try {
                    if (Double.parseDouble(newValue) < 0) {
                        txtDienTich.setText(newValue.replaceAll(newValue, oldValue));
                        notification.notification("Số nhập phải lớn hơn 0", "Vui lòng nhập lại", 1);
                    }
                } catch (Exception e) {
                }
            }
        });
        txtGiaTien.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                txtGiaTien.setText(newValue.replaceAll("[^\\d*|\\d+\\,\\d]", ""));
                if (!newValue.matches("\\d*|\\d+\\,\\d*") && !newValue.contains(".")) {
                    txtGiaTien.setText(newValue.replaceAll("[^\\d*|\\d+\\,\\d]", ""));
                    notification.notification("Ký tự nhập không phải là số", "Vui lòng nhập lại", 1);
                }
                try {
                    if (Double.parseDouble(newValue) < 0) {
                        txtGiaTien.setText(newValue.replaceAll(newValue, oldValue));
                        notification.notification("Số nhập phải lớn hơn 0", "Vui lòng nhập lại", 1);
                    }
                } catch (Exception e) {
                }
            }
        });
    }
}
