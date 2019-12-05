package controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.TongHopDuAn;
import service.ChartService;
import service.PaginatedList;
import service.ProjectService;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ThongKeDaBan implements Initializable {
    @FXML
    public void onCancel(ActionEvent actionEvent) {
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private TableView<TongHopDuAn> tableView;
    @FXML
    private TableColumn<TongHopDuAn, String> colNam;
    @FXML
    private TableColumn<TongHopDuAn, String> colDienTich;
    @FXML
    private TableColumn<TongHopDuAn, String> colDoanhThu;
    @FXML
    private TableColumn<TongHopDuAn, String> colSoKhachHang;
    private ObservableList<TongHopDuAn> tongHopDuAns = FXCollections.observableArrayList();
    List<TongHopDuAn> tongHopDuAnList = new ArrayList<>();
    @FXML
    Pagination clientPagination;
    PaginatedList<TongHopDuAn> pagingList;
    @FXML
    private LineChart lineChartDienTich;
    @FXML
    private LineChart lineChartDoanhThu;
    @FXML
    private LineChart lineChartSoKhachHang;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableView();
        clientPagination.setPageFactory(this::setTableView);
    }

    private Node setTableView(Integer integer) {
        setTableView();
        tableView.setItems(tongHopDuAns);
        return tableView;
    }

    public Node setTableView() {
        tableView.getItems().clear();
        tongHopDuAnList.clear();
        tongHopDuAnList = ChartService.getDoanhThuTheoNam();
        pagingList = new PaginatedList<>(tongHopDuAnList);
        clientPagination.setPageCount(pagingList.listOfPages.size());
        tongHopDuAns = FXCollections.observableList(tongHopDuAnList);
        colNam.setCellValueFactory(new PropertyValueFactory<>("Nam"));
        colDienTich.setCellValueFactory(new PropertyValueFactory<>("dienTich"));
        colDoanhThu.setCellValueFactory(new PropertyValueFactory<>("doanhThu"));
        colSoKhachHang.setCellValueFactory(new PropertyValueFactory<>("soKhachHang"));
        tableView.setItems(tongHopDuAns);
        plotBarChart();
        return tableView;
    }

    public void selectKhoaHoc() {
        setTableView();
    }

    private void plotBarChart() {
        lineChartDienTich.getData().clear();
        List<XYChart.Series> serieses1 = new ArrayList<>();
        XYChart.Series series1 = new XYChart.Series();
        for (TongHopDuAn tongHopDuAn :
                tongHopDuAnList) {
            series1.setName("Diện tích bán được theo năm");
            if (tongHopDuAn.getNam() != 0) {
                series1.getData().add(new XYChart.Data(tongHopDuAn.getNam().toString(), tongHopDuAn.getDienTich()));
            }
        }
        serieses1.add(series1);
        lineChartDienTich.getData().addAll(serieses1);

        lineChartDoanhThu.getData().clear();
        List<XYChart.Series> serieses2 = new ArrayList<>();
        XYChart.Series series2 = new XYChart.Series();
        for (TongHopDuAn tongHopDuAn :
                tongHopDuAnList) {
            series2.setName("Doanh thu theo năm");
            if (tongHopDuAn.getNam() != 0) {
                series2.getData().add(new XYChart.Data(tongHopDuAn.getNam().toString(), tongHopDuAn.getDoanhThu()));
            }
        }
        serieses2.add(series2);
        lineChartDoanhThu.getData().addAll(serieses2);

        lineChartSoKhachHang.getData().clear();
        List<XYChart.Series> serieses3 = new ArrayList<>();
        XYChart.Series series3 = new XYChart.Series();
        for (TongHopDuAn tongHopDuAn :
                tongHopDuAnList) {
            series3.setName("Số khách hàng đã mua theo năm");
            if (tongHopDuAn.getNam() != 0) {
                series3.getData().add(new XYChart.Data(tongHopDuAn.getNam().toString(), tongHopDuAn.getSoKhachHang()));
            }
        }
        serieses3.add(series3);
        lineChartSoKhachHang.getData().addAll(serieses3);

    }
}
