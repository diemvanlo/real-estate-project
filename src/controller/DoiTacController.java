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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.DoiTac;
import service.DoiTacService;
import service.Notification;
import service.PaginatedList;
import service.DoiTacService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class DoiTacController implements Initializable {
    @FXML
    JFXTextField clientSearchTextField;
    @FXML
    JFXTextField txtName;
    @FXML
    JFXTextField txtDiaChi;
    @FXML
    JFXTextField txtVonDauTu;
    @FXML
    JFXTextField txtSDT;
    @FXML
    JFXTextField txtEmail;
    @FXML
    JFXComboBox comLinhVuc;

    public DoiTac doiTac = new DoiTac();
    List<DoiTac> doiTacList = new ArrayList<>();
    @FXML
    private TableView<DoiTac> tableView;
    @FXML
    private TableColumn<DoiTac, String> colID;
    @FXML
    private TableColumn<DoiTac, String> colName;
    @FXML
    private TableColumn<DoiTac, String> colDiaChi;
    @FXML
    private TableColumn<DoiTac, String> colLinhVuc;
    @FXML
    private TableColumn<DoiTac, String> colSDT;
    @FXML
    private TableColumn<DoiTac, String> colEmail;
    @FXML
    private TableColumn<DoiTac, String> colSoVonDauTu;
    private ObservableList<DoiTac> doiTacs = FXCollections.observableArrayList();
    @FXML
    private JFXButton btnEdit;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnAdd;
    @FXML
    Pagination clientPagination;
    PaginatedList<DoiTac> pagingList;
    @FXML
    JFXTabPane tabView;
    Notification notification = new Notification();

    File file;
    final FileChooser fileChooser = new FileChooser();
    InputStream inputStream;
    @FXML
    ImageView imageView;

    public DoiTacController() throws SQLException {
    }

//    public void getNhanVien(NhanVien nhanVien) {
//        this.nhanVien = nhanVien;
//    }

    @FXML
    public void filter() {
        String search = clientSearchTextField.getText();
        System.out.println(search);
        setTableView();
        List<DoiTac> result;
        result = doiTacs.stream()
                .filter(item -> String.valueOf(item.getIdDoiTac()).contains(search)
                        || item.getTenDoitac().contains(search)
                        || (item.getSoVonDaDauTu() != 0 && item.getSoVonDaDauTu().toString().contains(search))
                        || item.getDiaChi().contains(search))
                .collect(Collectors.toList());
        doiTacs = FXCollections.observableList(result);
        tableView.setItems(doiTacs);
        if (clientSearchTextField.getText().isEmpty()) {
            setTableView(0);
        }
    }

    private Node setTableView(Integer integer) {
        setTableView();
        int fromIndex = integer * 10;
        int toIndex = Math.min(fromIndex + 10, doiTacs.size());
        ObservableList<DoiTac> doiTacObservableArray = FXCollections.observableArrayList(doiTacs.subList(fromIndex, toIndex));
        tableView.setItems(doiTacs);
        return tableView;
    }

    public Node setTableView() {
        btnDelete.setDisable(true);
        btnEdit.setDisable(true);
        tableView.getItems().clear();
        doiTacList.clear();
        doiTacList = DoiTacService.getAll();
        pagingList = new PaginatedList<>(doiTacList);
        clientPagination.setPageCount(pagingList.listOfPages.size());
        doiTacs = FXCollections.observableList(doiTacList);
        colID.setCellValueFactory(new PropertyValueFactory<>("idDoiTac"));
        colName.setCellValueFactory(new PropertyValueFactory<>("tenDoitac"));
        colLinhVuc.setCellValueFactory(new PropertyValueFactory<>("linhVuc"));
        colDiaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        colSDT.setCellValueFactory(new PropertyValueFactory<>("sdt"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colSoVonDauTu.setCellValueFactory(new PropertyValueFactory<>("soVonDaDauTu"));
        tableView.setItems(doiTacs);
        return tableView;
    }

    public void selectItem() throws SQLException, IOException {
        DoiTac NV = tableView.getSelectionModel().getSelectedItem();
        btnEdit.setDisable(false);
        btnDelete.setDisable(false);
//        if (NV != null) {
//            NV = DoiTacService.findByMaDoiTac(NV.getIdDuAn());
//        }
    }

    public void changeStage(ActionEvent actionEvent) throws IOException, SQLException {
        SingleSelectionModel<Tab> selectionModel = tabView.getSelectionModel();
        selectionModel.select(1);
        txtName.setText(this.doiTac.getTenDoitac());
        txtDiaChi.setText(this.doiTac.getDiaChi());
        if (this.doiTac.getSoVonDaDauTu() != null) {
            txtVonDauTu.setText(Double.valueOf(this.doiTac.getSoVonDaDauTu()).toString());
        }
        comLinhVuc.getSelectionModel().select(this.doiTac.getLinhVuc());

    }

    public void onSave(ActionEvent actionEvent) throws SQLException, FileNotFoundException {
        String loaiHinh = "Chưa đặt";
        if (comLinhVuc.getSelectionModel().selectedItemProperty().getValue() != null) {
            loaiHinh = comLinhVuc.getSelectionModel().selectedItemProperty().getValue().toString();
        }
        DoiTac doiTac = new DoiTac(this.doiTac.getIdDoiTac(), txtName.getText(), loaiHinh,
                txtDiaChi.getText(), txtSDT.getText(),
                txtEmail.getText(), Double.parseDouble(txtVonDauTu.getText()));

        DoiTacService.save(doiTac, this.file);
        notification.notification("Save thành công", "Đã lưu vào database", 0);
        setTableView();
    }

    public void edit(ActionEvent actionEvent) throws IOException, SQLException {
        this.doiTac = tableView.getSelectionModel().getSelectedItem();
        changeStage(actionEvent);
    }

    public void deleteItem(ActionEvent actionEvent) throws SQLException {
        DoiTac doiTac = tableView.getSelectionModel().getSelectedItem();
        DoiTacService.deleteByMaDoiTac(String.valueOf(doiTac.getIdDoiTac()));
        setTableView();
    }

    public void printIntoExcel() throws IOException {
//        excel.doiTacs = this.doiTacList;
//        excel.printDoiTacToExcel();
    }

    public void creatNew(ActionEvent actionEvent) throws IOException, SQLException {
        this.doiTac = new DoiTac();
        changeStage(actionEvent);
    }

    @FXML
    public void onCancel(ActionEvent actionEvent) {
        SingleSelectionModel<Tab> selectionModel = tabView.getSelectionModel();
        selectionModel.select(0);
        this.doiTac = new DoiTac();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableView();
        clientPagination.setPageFactory(this::setTableView);
        List<String> listDoiTacId = DoiTacService.getAllID();
        txtVonDauTu.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                txtVonDauTu.setText(newValue.replaceAll("[^\\d*|\\d+\\,\\d]", ""));
                if (!newValue.matches("\\d*|\\d+\\,\\d*") && !newValue.contains(".")) {
                    txtVonDauTu.setText(newValue.replaceAll("[^\\d*|\\d+\\,\\d]", ""));
                    notification.notification("Ký tự nhập không phải là số", "Vui lòng nhập lại", 1);
                }
                try {
                    if (Double.parseDouble(newValue) < 0) {
                        txtVonDauTu.setText(newValue.replaceAll(newValue, oldValue));
                        notification.notification("Số nhập phải lớn hơn 0", "Vui lòng nhập lại", 1);
                    }
                } catch (Exception e) {

                }
            }
        });
    }

    public void uploadImage(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            imageView.setImage(new Image(file.toURI().toString(), 1200, 1500, true, true));
        }
    }
}
