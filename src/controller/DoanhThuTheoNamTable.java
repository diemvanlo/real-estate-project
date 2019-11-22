package controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.Chart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.TongHopDoanhThu;
import model.TongHopDoanhThu;
import service.ChartService;
import service.PaginatedList;
import service.ProjectService;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DoanhThuTheoNamTable implements Initializable {
    @FXML
    public void onCancel(ActionEvent actionEvent) {
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private TableView<TongHopDoanhThu> tableView;
    @FXML
    private TableColumn<TongHopDoanhThu, String> colID;
    @FXML
    private TableColumn<TongHopDoanhThu, String> colSoSanPham;
    @FXML
    private TableColumn<TongHopDoanhThu, String> colSum;
    @FXML
    private TableColumn<TongHopDoanhThu, String> colMax;
    @FXML
    private TableColumn<TongHopDoanhThu, String> colMin;
    @FXML
    private TableColumn<TongHopDoanhThu, String> colAVG;
    private ObservableList<TongHopDoanhThu> TongHopDoanhThus = FXCollections.observableArrayList();
    List<TongHopDoanhThu> TongHopDoanhThuList = new ArrayList<>();
    @FXML
    Pagination clientPagination;
    PaginatedList<TongHopDoanhThu> pagingList;
    @FXML
    private BarChart bar_chart1;
    @FXML
    private BarChart bar_chart2;
    @FXML
    private JFXComboBox comYear;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> listKhoaHocId = ProjectService.getAllYear();
        ObservableList<String> data = FXCollections.observableArrayList(listKhoaHocId);
        comYear.setItems(data);
        setTableView();
        clientPagination.setPageFactory(this::setTableView);
        setTableView();
        clientPagination.setPageFactory(this::setTableView);
    }

    private Node setTableView(Integer integer) {
        setTableView();
        tableView.setItems(TongHopDoanhThus);
        return tableView;
    }

    public Node setTableView() {
        tableView.getItems().clear();
        TongHopDoanhThuList.clear();
        String year = "2019";
        if (comYear.getSelectionModel().selectedItemProperty().getValue() != null) {
            year = comYear.getSelectionModel().selectedItemProperty().getValue().toString();
        }
        TongHopDoanhThuList = ChartService.getDoanhThu(year);
        pagingList = new PaginatedList<>(TongHopDoanhThuList);
        clientPagination.setPageCount(pagingList.listOfPages.size());
        TongHopDoanhThus = FXCollections.observableList(TongHopDoanhThuList);
        colID.setCellValueFactory(new PropertyValueFactory<>("idDuAn"));
        colSoSanPham.setCellValueFactory(new PropertyValueFactory<>("SoSp"));
        colSum.setCellValueFactory(new PropertyValueFactory<>("TongDoanhThu"));
        colMin.setCellValueFactory(new PropertyValueFactory<>("DoanhThuCaoNhat"));
        colMax.setCellValueFactory(new PropertyValueFactory<>("DoanhThuThapNhat"));
        colAVG.setCellValueFactory(new PropertyValueFactory<>("DoanhThuTB"));
        tableView.setItems(TongHopDoanhThus);
        plotBarChart();
        return tableView;
    }

    public void selectKhoaHoc() {
        setTableView();
    }

    private void plotBarChart() {
        bar_chart1.getData().clear();
        bar_chart2.getData().clear();
        List<XYChart.Series> serieses1 = new ArrayList<>();
        List<XYChart.Series> serieses2 = new ArrayList<>();
        String soHocVien = "Số học viên";
        String max = "Cao nhất";
        String min = "Thấp nhất";
        String avg = "Trung bình";
        for (TongHopDoanhThu TongHopDoanhThu :
                TongHopDoanhThuList) {
            Integer chuyenDe = TongHopDoanhThu.getIdDuAn();
            XYChart.Series series1 = new XYChart.Series();
            series1.setName(chuyenDe.toString());
            series1.getData().add(new XYChart.Data(soHocVien, TongHopDoanhThu.getSoSp()));
            serieses1.add(series1);

            XYChart.Series series2 = new XYChart.Series();
            series2.setName(chuyenDe.toString());
            series2.getData().add(new XYChart.Data(min, TongHopDoanhThu.getTongDoanhThu()));
            series2.getData().add(new XYChart.Data(min, TongHopDoanhThu.getDoanhThuCaoNhat()));
            series2.getData().add(new XYChart.Data(max, TongHopDoanhThu.getDoanhThuThapNhat()));
            series2.getData().add(new XYChart.Data(avg, TongHopDoanhThu.getDoanhThuTB()));
            serieses2.add(series2);

        }
        bar_chart1.getData().addAll(serieses1);
        bar_chart2.getData().addAll(serieses2);

    }
}
