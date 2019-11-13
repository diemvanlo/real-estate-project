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
import model.Project;
import service.DoiTacService;
import service.Notification;
import service.PaginatedList;
import service.ProjectService;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class DuAnController implements Initializable {
    @FXML
    JFXTextField clientSearchTextField;
    @FXML
    JFXTextField txtName;
    @FXML
    JFXComboBox comLoaiHinh;
    @FXML
    JFXTextField txtDiaChi;
    @FXML
    JFXTextField txtDienTich;
    @FXML
    JFXTextField txtVonDauTu;
    @FXML
    JFXDatePicker dateStart;
    @FXML
    JFXDatePicker dateEnd;
    @FXML
    JFXComboBox comHTQuanLy;
    @FXML
    JFXComboBox comHTDauTu;
    @FXML
    JFXComboBox comIDDoiTac;
    @FXML
    JFXTextField txtMapX;
    @FXML
    JFXTextField txtMapY;
    @FXML
    JFXTextField txtBanKinh;
    public Project project = new Project();
    List<Project> projectList = new ArrayList<>();
    @FXML
    private TableView<Project> tableView;
    @FXML
    private TableColumn<Project, String> colID;
    @FXML
    private TableColumn<Project, String> colName;
    @FXML
    private TableColumn<Project, String> colLoaiHinh;
    @FXML
    private TableColumn<Project, String> colDiaChi;
    @FXML
    private TableColumn<Project, String> colDienTich;
    @FXML
    private TableColumn<Project, String> colVonDauTu;
    @FXML
    private TableColumn<Project, String> colNgayBatDau;
    @FXML
    private TableColumn<Project, String> colNgayKetThuc;
    @FXML
    private TableColumn<Project, String> colHTQuanLy;
    @FXML
    private TableColumn<Project, String> colHTDauTu;
    @FXML
    private TableColumn<Project, String> colIDDoiTac;
    @FXML
    private TableColumn<Project, String> colTrangThai;
    @FXML
    private TableColumn<Project, String> colMapX;
    @FXML
    private TableColumn<Project, String> colMapY;
    @FXML
    private TableColumn<Project, String> colBanKinh;
    private ObservableList<Project> projects = FXCollections.observableArrayList();
    @FXML
    private JFXButton btnEdit;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnAdd;
    @FXML
    Pagination clientPagination;
    PaginatedList<Project> pagingList;
    @FXML
    JFXTabPane tabView;
    Notification notification = new Notification();
    @FXML
    ToggleGroup q;

    @FXML
    private RadioButton rdDone;

    @FXML
    private RadioButton rdUndone;
    public DuAnController() throws SQLException {
    }

//    public void getNhanVien(NhanVien nhanVien) {
//        this.nhanVien = nhanVien;
//    }

    @FXML
    public void filter() {
        String search = clientSearchTextField.getText();
        System.out.println(search);
        setTableView();
        List<Project> result;
        result = projects.stream()
                .filter(item -> String.valueOf(item.getIdDuAn()).contains(search)
                        || item.getTenDuAn().contains(search)
                        || (item.getMapX() != null && item.getMapX().toString().contains(search))
                        || (item.getMapY() != null && item.getMapY().toString().contains(search)))
                .collect(Collectors.toList());
        projects = FXCollections.observableList(result);
        tableView.setItems(projects);
        if (clientSearchTextField.getText().isEmpty()) {
            setTableView(0);
        }
    }

    private Node setTableView(Integer integer) {
        setTableView();
        int fromIndex = integer * 10;
        int toIndex = Math.min(fromIndex + 10, projects.size());
        ObservableList<Project> projectObservableArray = FXCollections.observableArrayList(projects.subList(fromIndex, toIndex));
        tableView.setItems(projects);
        return tableView;
    }

    public Node setTableView() {
        btnDelete.setDisable(true);
        btnEdit.setDisable(true);
        tableView.getItems().clear();
        projectList.clear();
        projectList = ProjectService.getAll();
        pagingList = new PaginatedList<>(projectList);
        clientPagination.setPageCount(pagingList.listOfPages.size());
        projects = FXCollections.observableList(projectList);
        colID.setCellValueFactory(new PropertyValueFactory<>("idDuAn"));
        colName.setCellValueFactory(new PropertyValueFactory<>("tenDuAn"));
        colLoaiHinh.setCellValueFactory(new PropertyValueFactory<>("loaiHinh"));
        colDiaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        colDienTich.setCellValueFactory(new PropertyValueFactory<>("dienTich"));
        colVonDauTu.setCellValueFactory(new PropertyValueFactory<>("chiPhiDuAn"));
        colNgayBatDau.setCellValueFactory(new PropertyValueFactory<>("ngayBatDau"));
        colNgayKetThuc.setCellValueFactory(new PropertyValueFactory<>("ngayKetThuc"));
        colHTQuanLy.setCellValueFactory(new PropertyValueFactory<>("hinhThucQuanLi"));
        colHTDauTu.setCellValueFactory(new PropertyValueFactory<>("hinhThucDauTu"));
        colIDDoiTac.setCellValueFactory(new PropertyValueFactory<>("idDoiTac"));
        colTrangThai.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
        colMapX.setCellValueFactory(new PropertyValueFactory<>("mapX"));
        colMapY.setCellValueFactory(new PropertyValueFactory<>("mapY"));
        colBanKinh.setCellValueFactory(new PropertyValueFactory<>("banKinh"));
        tableView.setItems(projects);
        return tableView;
    }

    public void selectItem() throws SQLException, IOException {
        Project NV = tableView.getSelectionModel().getSelectedItem();
        btnEdit.setDisable(false);
        btnDelete.setDisable(false);
//        if (NV != null) {
//            NV = ProjectService.findByMaProject(NV.getIdDuAn());
//        }
    }

    public void changeStage(ActionEvent actionEvent) throws IOException, SQLException {
        SingleSelectionModel<Tab> selectionModel = tabView.getSelectionModel();
        selectionModel.select(1);
        txtName.setText(this.project.getTenDuAn());
        txtDiaChi.setText(this.project.getDiaChi());
        if (this.project.getDienTich() != null) {
            txtDienTich.setText(this.project.getDienTich().toString());
        }
        if (this.project.getChiPhiDuAn() != null) {
            txtVonDauTu.setText(this.project.getChiPhiDuAn().toString());
        }
        dateStart.setValue(LocalDate.of(Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH) + 1,
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)));
        dateEnd.setValue(LocalDate.of(Calendar.getInstance().get(Calendar.YEAR) + 2,
                Calendar.getInstance().get(Calendar.MONTH) + 1,
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)));
        if (this.project.getIdDoiTac() != null) {
            comIDDoiTac.getSelectionModel().select(this.project.getIdDoiTac());
        }
        comHTDauTu.getSelectionModel().select(this.project.getHinhThucDauTu());
        comHTQuanLy.getSelectionModel().select(this.project.getHinhThucQuanLi());
        if (this.project.getMapX() != null) {
            txtMapX.setText(this.project.getMapX().toString());
        }
        if (this.project.getMapY() != null) {
            txtMapY.setText(this.project.getMapY().toString());
        }
        if (this.project.getBanKinh() != null) {
            txtBanKinh.setText(this.project.getBanKinh().toString());
        }
        if (this.project.getTrangThai().equals("CHƯA ĐỦ VỐN")){
            rdUndone.setSelected(true);
        }
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/EidtProject.fxml"));
//        Parent root = (Parent) loader.load();
//        EditProjectController editProjectController = loader.getController();
//        editProjectController.init(this.nhanVien, editProject);
//        Stage stage = new Stage();
//        stage.setResizable(false);
//        stage.initModality(Modality.APPLICATION_MODAL);
//        stage.setScene(new Scene(root));
//        stage.show();
    }

    public void onSave(ActionEvent actionEvent) throws SQLException, FileNotFoundException {
        String loaiHinh = "Chưa đặt";
        if (comLoaiHinh.getSelectionModel().selectedItemProperty().getValue() != null) {
            loaiHinh = comLoaiHinh.getSelectionModel().selectedItemProperty().getValue().toString();
        }
        String hinhThucQuanLi = "Chưa đặt";
        if (comHTQuanLy.getSelectionModel().selectedItemProperty().getValue() != null) {
            hinhThucQuanLi = comHTQuanLy.getSelectionModel().selectedItemProperty().getValue().toString();
        }
        String hinhThucDauTu = "Chưa đặt";
        if (comHTDauTu.getSelectionModel().selectedItemProperty().getValue() != null) {
            hinhThucDauTu = comHTDauTu.getSelectionModel().selectedItemProperty().getValue().toString();
        }
        String IDDoiTac = "Chưa đặt";
        if (comIDDoiTac.getSelectionModel().selectedItemProperty().getValue() != null) {
            IDDoiTac = comIDDoiTac.getSelectionModel().selectedItemProperty().getValue().toString();
        }
        RadioButton selectedRadioButton = (RadioButton) q.getSelectedToggle();
        String trangThai = selectedRadioButton.getText();
        Project project = new Project(this.project.getIdDuAn(), txtName.getText(), loaiHinh,
                txtDiaChi.getText(), Double.parseDouble(txtDienTich.getText()),
                Double.parseDouble(txtVonDauTu.getText()),
                dateStart.getValue().toString(),
                dateEnd.getValue().toString(),
                hinhThucQuanLi, hinhThucDauTu, Integer.parseInt(IDDoiTac), trangThai,
                Double.parseDouble(txtMapX.getText()),
                Double.parseDouble(txtMapY.getText()),
                Double.parseDouble(txtBanKinh.getText())
        );
        project.setIdDuAn(this.project.getIdDuAn());
        ProjectService.save(project);
        notification.notification("Save thành công", "Đã lưu vào database", 0);
        setTableView();
    }

    public void edit(ActionEvent actionEvent) throws IOException, SQLException {
        this.project = tableView.getSelectionModel().getSelectedItem();
        changeStage(actionEvent);
    }

    public void deleteItem(ActionEvent actionEvent) throws SQLException {
        Project project = tableView.getSelectionModel().getSelectedItem();
        ProjectService.deleteByMaProject(String.valueOf(project.getIdDuAn()));
        setTableView();
    }

    public void printIntoExcel() throws IOException {
//        excel.projects = this.projectList;
//        excel.printProjectToExcel();
    }

    public void creatNew(ActionEvent actionEvent) throws IOException, SQLException {
        this.project = new Project();
        changeStage(actionEvent);
    }

    @FXML
    public void onCancel(ActionEvent actionEvent) {
        SingleSelectionModel<Tab> selectionModel = tabView.getSelectionModel();
        selectionModel.select(0);
        this.project = new Project();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableView();
        clientPagination.setPageFactory(this::setTableView);
        List<String> listDoiTacId = DoiTacService.getAllID();
        ObservableList<String> data = FXCollections.observableArrayList(listDoiTacId);
        comIDDoiTac.setItems(data);
//        if (this.project.getIdDoiTac() != null) {
//            comIDDoiTac.getSelectionModel().select(this.project.getIdDoiTac());
//        }
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
        txtMapX.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                txtMapX.setText(newValue.replaceAll("[^\\d*|\\d+\\,\\d]", ""));
                if (!newValue.matches("\\d*|\\d+\\,\\d*") && !newValue.contains(".")) {
                    txtMapX.setText(newValue.replaceAll("[^\\d*|\\d+\\,\\d]", ""));
                    notification.notification("Ký tự nhập không phải là số", "Vui lòng nhập lại", 1);
                }
                try {
                    if (Double.parseDouble(newValue) < 0) {
                        txtMapX.setText(newValue.replaceAll(newValue, oldValue));
                        notification.notification("Số nhập phải lớn hơn 0", "Vui lòng nhập lại", 1);
                    }
                } catch (Exception e) {

                }
            }
        });
        txtMapY.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                txtMapY.setText(newValue.replaceAll("[^\\d*|\\d+\\,\\d]", ""));
                if (!newValue.matches("\\d*|\\d+\\,\\d*") && !newValue.contains(".")) {
                    txtMapY.setText(newValue.replaceAll("[^\\d*|\\d+\\,\\d]", ""));
                    notification.notification("Ký tự nhập không phải là số", "Vui lòng nhập lại", 1);
                }
                try {
                    if (Double.parseDouble(newValue) < 0) {
                        txtMapY.setText(newValue.replaceAll(newValue, oldValue));
                        notification.notification("Số nhập phải lớn hơn 0", "Vui lòng nhập lại", 1);
                    }
                } catch (Exception e) {

                }
            }
        });
        txtBanKinh.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                txtBanKinh.setText(newValue.replaceAll("[^\\d*|\\d+\\,\\d]", ""));
                if (!newValue.matches("\\d*|\\d+\\,\\d*") && !newValue.contains(".")) {
                    txtBanKinh.setText(newValue.replaceAll("[^\\d*|\\d+\\,\\d]", ""));
                    notification.notification("Ký tự nhập không phải là số", "Vui lòng nhập lại", 1);
                }
                try {
                    if (Double.parseDouble(newValue) < 0) {
                        txtBanKinh.setText(newValue.replaceAll(newValue, oldValue));
                        notification.notification("Số nhập phải lớn hơn 0", "Vui lòng nhập lại", 1);
                    }
                } catch (Exception e) {

                }
            }
        });
    }

}
