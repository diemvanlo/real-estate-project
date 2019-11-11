package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Project;
import service.PaginatedList;
import service.ProjectService;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class DuAnController implements Initializable {
    @FXML
    JFXTextField clientSearchTextFieldd;
//    public NhanVien nhanVien;
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
    private TableColumn<Project, String> colTGHoanThanh;
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
    Pagination clientPagination;
    PaginatedList<Project> pagingList;

    public DuAnController() throws SQLException {
    }

//    public void getNhanVien(NhanVien nhanVien) {
//        this.nhanVien = nhanVien;
//    }

    @FXML
    public void filter() {
        String search = clientSearchTextFieldd.getText();
        setTableView();
        List<Project> result;
        result = projects.stream()
                .filter(item -> String.valueOf(item.getIdDuAn()).contains(search)
                        || item.getTenDuAn().contains(search)
                        || item.getMapX().contains(search)
                        || item.getMapY().contains(search) )
                .collect(Collectors.toList());
        projects = FXCollections.observableList(result);
        tableView.setItems(projects);
        if (clientSearchTextFieldd.getText().isEmpty()) {
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
//        btnDelete.setDisable(true);
//        btnEdit.setDisable(true);
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
        colTGHoanThanh.setCellValueFactory(new PropertyValueFactory<>("mucTieu"));
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
        if (NV != null) {
            NV = ProjectService.findByMaProject(NV.getIdDuAn());

        }
    }

    public void creatNew(ActionEvent actionEvent) throws IOException, SQLException {
        changeStage(actionEvent, new Project());
    }

    public void changeStage(ActionEvent actionEvent, Project editProject) throws IOException, SQLException {
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

    public void edit(ActionEvent actionEvent) throws IOException, SQLException {
        Project editProject = tableView.getSelectionModel().getSelectedItem();
        changeStage(actionEvent, editProject);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableView();
        clientPagination.setPageFactory(this::setTableView);
    }
}
