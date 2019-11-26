package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.DoiTac;
import model.Image;
import service.ImageService;
import service.Notification;
import service.PaginatedList;

import java.awt.event.ActionEvent;
import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class UpdateAnh implements Initializable {
    @FXML
    JFXTextField clientSearchTextField;
    @FXML
    JFXTextField txtCheDo;
    @FXML
    JFXTextField txtIdSP;

    public Image image = new Image();
    List<Image> imageList = new ArrayList<>();

    @FXML
    private TableView<Image> tableView;
    @FXML
    private TableColumn<Image, String> colID;
    @FXML
    private TableColumn<Image, String> colCheDo;
    @FXML
    private TableColumn<Image, String> colHinhAnh;
    @FXML
    private TableColumn<Image, String> colIdSanPham;
    private ObservableList<Image> images = FXCollections.observableArrayList();
    @FXML
    private JFXButton btnEdit;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnAdd;
    @FXML
    Pagination clientPagination;
    PaginatedList<Image> pagingList;
    @FXML
    JFXTabPane tabView;
    Notification notification = new Notification();

    InputStream inputStream;
    File file;
    final FileChooser fileChooser = new FileChooser();
    @FXML
    ImageView imageView;

    @FXML
    public void filter() {
        java.lang.String search = clientSearchTextField.getText();
        System.out.println(search);
        setTableView();
        List<Image> result;
        result = images.stream()
                .filter(item -> String.valueOf(item.getIdImager()).contains(search)
                        || item.getRegimeImager().contains(search)
                        || (item.getIdSanPham() == Integer.parseInt(search)))
                .collect(Collectors.toList());
        images = FXCollections.observableList(result);
        tableView.setItems(images);
        if (clientSearchTextField.getText().isEmpty()) {
            setTableView(0);
        }
    }

    private Node setTableView(Integer integer) {
        setTableView();
        int fromIndex = integer * 10;
        int toIndex = Math.min(fromIndex + 10, images.size());
        ObservableList<Image> doiTacObservableArray = FXCollections.observableArrayList(images.subList(fromIndex, toIndex));
        tableView.setItems(images);
        return tableView;
    }

    public Node setTableView() {
        btnDelete.setDisable(true);
        btnEdit.setDisable(true);
        tableView.getItems().clear();
        imageList.clear();
        imageList = ImageService.getAll();
        pagingList = new PaginatedList<>(imageList);
        clientPagination.setPageCount(pagingList.listOfPages.size());
        images = FXCollections.observableList(imageList);
        colID.setCellValueFactory(new PropertyValueFactory<>("idImager"));
        colCheDo.setCellValueFactory(new PropertyValueFactory<>("image"));
        colHinhAnh.setCellValueFactory(new PropertyValueFactory<>("regimeImager"));
        colIdSanPham.setCellValueFactory(new PropertyValueFactory<>("idSanPham"));

        tableView.setItems(images);
        return tableView;
    }

    public void selectItem() throws SQLException, IOException {
        btnEdit.setDisable(false);
        btnDelete.setDisable(false);
    }

    public void changeStage(ActionEvent actionEvent) throws IOException, SQLException {
        this.image = ImageService.findByMaImage(this.image.getIdImager());
        OutputStream os = new FileOutputStream(new File("photo.jpg"));
        byte[] content = new byte[1024];
        int size = 0;
        if (this.image.getImage() != null) {
            inputStream = this.image.getImage();
            while ((size = inputStream.read(content)) != -1) {
                os.write(content, 0, size);
            }
        }
        imageView.setImage(new javafx.scene.image.Image("file:photo.jpg", 400, 550, true, true));
        SingleSelectionModel<Tab> selectionModel = tabView.getSelectionModel();
        selectionModel.select(1);
        txtCheDo.setText(this.image.getRegimeImager());
        txtIdSP.setText(Integer.valueOf(this.image.getIdSanPham()).toString());

    }

    public void onSave() throws SQLException, IOException {
        ImageService.save(image, this.file);
        notification.notification("Save thành công", "Đã lưu vào database", 0);
        setTableView();
    }

    @FXML
    public void edit(ActionEvent actionEvent) throws IOException, SQLException {
        this.image = tableView.getSelectionModel().getSelectedItem();

        changeStage(actionEvent);
    }

    public void deleteItem() throws SQLException {
        Image image = tableView.getSelectionModel().getSelectedItem();
        ImageService.deleteByMaImage(String.valueOf(image.getIdImager()));
        setTableView();
    }

    public void printIntoExcel() throws IOException {
    }

    public void createNew(ActionEvent actionEvent) throws IOException, SQLException {
        this.image = new Image();
        changeStage(actionEvent);
    }

    @FXML
    public void onCancel() {
        SingleSelectionModel<Tab> selectionModel = tabView.getSelectionModel();
        selectionModel.select(0);
        this.image = new Image();
    }


    public void uploadImage(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            imageView.setImage(new javafx.scene.image.Image(file.toURI().toString(), 1200, 1500, true, true));
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
