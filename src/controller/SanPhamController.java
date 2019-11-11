package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Product;
import service.PaginatedList;
import service.ProductService;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
    private TableColumn<Product, String> colChiTiet;
    @FXML
    private TableColumn<Product, String> colTrangThai;
    @FXML
    private TableColumn<Product, String> colIdKhachHang;
    private ObservableList<Product> product = FXCollections.observableArrayList();
    @FXML
    private JFXButton btnEdit;
    @FXML
    private JFXButton btnDelete;
    @FXML
    Pagination clientPagination;
    PaginatedList<Product> pagingList;

    public SanPhamController() throws SQLException {
    }

    @FXML
    public void filter() {
        String search = clientSearchTextFieldd.getText();
        setTableView();
        List<Product> result;
        result = product.stream()
                .filter(item -> String.valueOf(item.getIdSanPham()).contains(search)
                        || item.getTenSanPham().contains(search)
                         )
                .collect(Collectors.toList());
        product = FXCollections.observableList(result);
        tableView.setItems(product);
        if (clientSearchTextFieldd.getText().isEmpty()) {
            setTableView(0);
        }
    }

    private Node setTableView(Integer integer) {
        setTableView();
        int fromIndex = integer * 10;
        int toIndex = Math.min(fromIndex + 10, product.size());
        ObservableList<Product> ProductObservableArray = FXCollections.observableArrayList(product.subList(fromIndex, toIndex));
        tableView.setItems(product);
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
        product = FXCollections.observableList(productList);
        colID.setCellValueFactory(new PropertyValueFactory<>("idSanPham"));
        colName.setCellValueFactory(new PropertyValueFactory<>("tenSanPham"));
        colidDuAn.setCellValueFactory(new PropertyValueFactory<>("idDuAn"));
        colDiaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        colDienTich.setCellValueFactory(new PropertyValueFactory<>("dienTich"));
        colGiaTien.setCellValueFactory(new PropertyValueFactory<>("giaTien"));
        colMoTa.setCellValueFactory(new PropertyValueFactory<>("moTa"));
        colNgayTao.setCellValueFactory(new PropertyValueFactory<>("ngayTao"));
        colNgayBan.setCellValueFactory(new PropertyValueFactory<>("ngayBan"));
        colChiTiet.setCellValueFactory(new PropertyValueFactory<>("chiTiet"));
        colTrangThai.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
        colIdKhachHang.setCellValueFactory(new PropertyValueFactory<>("idKhachHang"));
        tableView.setItems(product);
        return tableView;
    }

    public void selectItem() throws SQLException, IOException {
        Product NV = tableView.getSelectionModel().getSelectedItem();
        btnEdit.setDisable(false);
        btnDelete.setDisable(false);
        if (NV != null) {
            NV = ProductService.findByMaProduct(NV.getIdSanPham());

        }
    }

    public void creatNew(ActionEvent actionEvent) throws IOException, SQLException {
        changeStage(actionEvent, new Product());
    }

    public void changeStage(ActionEvent actionEvent, Product editProduct) throws IOException, SQLException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/EidtProduct.fxml"));
//        Parent root = (Parent) loader.load();
//        EditProductController editProductController = loader.getController();
//        editProductController.init(this.nhanVien, editProduct);
//        Stage stage = new Stage();
//        stage.setResizable(false);
//        stage.initModality(Modality.APPLICATION_MODAL);
//        stage.setScene(new Scene(root));
//        stage.show();
    }

    public void edit(ActionEvent actionEvent) throws IOException, SQLException {
        Product editProduct = tableView.getSelectionModel().getSelectedItem();
        changeStage(actionEvent, editProduct);
    }

    public void deleteItem(ActionEvent actionEvent) throws SQLException {
        Product Product = tableView.getSelectionModel().getSelectedItem();
        ProductService.deleteByMaProduct(String.valueOf(Product.getIdSanPham()));
        setTableView();
    }

    public void printIntoExcel() throws IOException {
//        excel.product = this.ProductList;
//        excel.printProductToExcel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableView();
        clientPagination.setPageFactory(this::setTableView);
    }
}
