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
import model.ImageUpload;
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

    public ImageUpload imageUpload = new ImageUpload();
    List<ImageUpload> imageUploadList = new ArrayList<>();

    @FXML
    private TableView<ImageUpload> tableView;
    @FXML
    private TableColumn<ImageUpload, String> colID;
    @FXML
    private TableColumn<ImageUpload, String> colCheDo;
    @FXML
    private TableColumn<ImageUpload, String> colHinhAnh;
    @FXML
    private TableColumn<ImageUpload, String> colIdSanPham;
    private ObservableList<ImageUpload> imageUploads = FXCollections.observableArrayList();
    @FXML
    private JFXButton btnEdit;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnAdd;
    @FXML
    Pagination clientPagination;
    PaginatedList<ImageUpload> pagingList;
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
        List<ImageUpload> result;
        result = imageUploads.stream()
                .filter(item -> String.valueOf(item.getIdImager()).contains(search)
                        || item.getRegimeImager().contains(search)
                        || (item.getIdSanPham() == Integer.parseInt(search)))
                .collect(Collectors.toList());
        imageUploads = FXCollections.observableList(result);
        tableView.setItems(imageUploads);
        if (clientSearchTextField.getText().isEmpty()) {
            setTableView(0);
        }
    }

    private Node setTableView(Integer integer) {
        setTableView();
        int fromIndex = integer * 10;
        int toIndex = Math.min(fromIndex + 10, imageUploads.size());
        ObservableList<ImageUpload> doiTacObservableArray = FXCollections.observableArrayList(imageUploads.subList(fromIndex, toIndex));
        tableView.setItems(imageUploads);
        return tableView;
    }

    public Node setTableView() {
        btnDelete.setDisable(true);
        btnEdit.setDisable(true);
        tableView.getItems().clear();
        imageUploadList.clear();
        imageUploadList = ImageService.getAll();
        pagingList = new PaginatedList<>(imageUploadList);
        clientPagination.setPageCount(pagingList.listOfPages.size());
        imageUploads = FXCollections.observableList(imageUploadList);
        colID.setCellValueFactory(new PropertyValueFactory<>("idImager"));
        colCheDo.setCellValueFactory(new PropertyValueFactory<>("image"));
        colHinhAnh.setCellValueFactory(new PropertyValueFactory<>("regimeImager"));
        colIdSanPham.setCellValueFactory(new PropertyValueFactory<>("idSanPham"));

        tableView.setItems(imageUploads);
        return tableView;
    }

    public void selectItem() throws SQLException, IOException {
        btnEdit.setDisable(false);
        btnDelete.setDisable(false);
    }

    public void changeStage(ActionEvent actionEvent) throws IOException, SQLException {
        this.imageUpload = ImageService.findByMaImage(this.imageUpload.getIdImager());
        OutputStream os = new FileOutputStream(new File("photo.jpg"));
        byte[] content = new byte[1024];
        int size = 0;
        if (this.imageUpload.getImage() != null) {
            inputStream = this.imageUpload.getImage();
            while ((size = inputStream.read(content)) != -1) {
                os.write(content, 0, size);
            }
        }
        imageView.setImage(new javafx.scene.image.Image("file:photo.jpg", 400, 550, true, true));
        SingleSelectionModel<Tab> selectionModel = tabView.getSelectionModel();
        selectionModel.select(1);
        txtCheDo.setText(this.imageUpload.getRegimeImager());
        txtIdSP.setText(Integer.valueOf(this.imageUpload.getIdSanPham()).toString());

    }

    public void onSave() throws SQLException, IOException {
        ImageService.save(imageUpload, this.file);
        notification.notification("Save thành công", "Đã lưu vào database", 0);
        setTableView();
    }

    @FXML
    public void edit(ActionEvent actionEvent) throws IOException, SQLException {
        this.imageUpload = tableView.getSelectionModel().getSelectedItem();

        changeStage(actionEvent);
    }

    public void deleteItem() throws SQLException {
        ImageUpload imageUpload = tableView.getSelectionModel().getSelectedItem();
        ImageService.deleteByMaImage(String.valueOf(imageUpload.getIdImager()));
        setTableView();
    }

    public void printIntoExcel() throws IOException {
    }

    public void createNew(ActionEvent actionEvent) throws IOException, SQLException {
        this.imageUpload = new ImageUpload();
        changeStage(actionEvent);
    }

    @FXML
    public void onCancel() {
        SingleSelectionModel<Tab> selectionModel = tabView.getSelectionModel();
        selectionModel.select(0);
        this.imageUpload = new ImageUpload();
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
