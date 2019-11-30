package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import data.Excel;
import data.Image360Vr;
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
import model.ImageUpload;
import service.ImageService;
import service.Notification;
import service.PaginatedList;
import service.ProductService;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ImageUploaderController implements Initializable {
    @FXML
    JFXTextField clientSearchTextField;
    @FXML
    JFXComboBox comIdSanPham;

    public ImageUpload image = new ImageUpload();
    List<ImageUpload> imageList = new ArrayList<>();
    @FXML
    private TableView<ImageUpload> tableView;
    @FXML
    private TableColumn<ImageUpload, String> colID;
    @FXML
    private TableColumn<ImageUpload, String> colImageMode;
    @FXML
    private TableColumn<ImageUpload, String> colIdSanPham;
    private ObservableList<ImageUpload> images = FXCollections.observableArrayList();
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
    Excel excel = new Excel();
    @FXML
    ImageView imageView;

    @FXML
    ToggleGroup q;

    @FXML
    private RadioButton rdDone;

    @FXML
    private RadioButton rdUndone;

    public ImageUploaderController() throws SQLException {
    }

//    public void getNhanVien(NhanVien nhanVien) {
//        this.nhanVien = nhanVien;
//    }

    @FXML
    public void filter() {
        String search = clientSearchTextField.getText();
        System.out.println(search);
        setTableView();
        List<ImageUpload> result;
        result = images.stream()
                .filter(item -> String.valueOf(item.getIdImager()).contains(search)
                        || item.getIdSanPham() == Integer.parseInt(search))
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
        ObservableList<ImageUpload> imageObservableArray = FXCollections.observableArrayList(images.subList(fromIndex, toIndex));
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
        colID.setCellValueFactory(new PropertyValueFactory<>("IdHinhAnh"));
        colImageMode.setCellValueFactory(new PropertyValueFactory<>("CheDo"));
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
        imageView.setImage(new Image("file:photo.jpg", 400, 550, true, true));
        SingleSelectionModel<Tab> selectionModel = tabView.getSelectionModel();
        selectionModel.select(1);

        comIdSanPham.getSelectionModel().select(this.image.getIdSanPham());
        if (this.image.getRegimeImager() != null && this.image.getRegimeImager().equals("ẢNH THƯỜNG")) {
            rdUndone.setSelected(true);
        }
    }

    public void onSave(ActionEvent actionEvent) throws SQLException, IOException {
        String IdSanPham = "Chưa đặt";
        if (comIdSanPham.getSelectionModel().selectedItemProperty().getValue() != null) {
            IdSanPham = comIdSanPham.getSelectionModel().selectedItemProperty().getValue().toString();
        }
        RadioButton selectedRadioButton = (RadioButton) q.getSelectedToggle();
        String imageMode = selectedRadioButton.getText();
        ImageUpload image = new ImageUpload(this.image.getIdImager(), imageMode, Integer.parseInt(IdSanPham));
        ImageService.save(image, this.file);
        notification.notification("Save thành công", "Đã lưu vào database", 0);
        setTableView();
    }

    public void edit(ActionEvent actionEvent) throws IOException, SQLException {
        this.image = tableView.getSelectionModel().getSelectedItem();
        changeStage(actionEvent);
    }

    public void displayImage360Vr() throws IOException {
        if (this.image.getImage() != null)
            Image360Vr.writeToImageFile(this.image.getImage());
        File htmlFile = new File("1.html");
        Desktop.getDesktop().browse(htmlFile.toURI());
        notification.notification("Chú ý", " nếu bạn không thể mở trên chrome. Hãy mở trên moliza firefox hoặc microsoft edge", 0);
    }

    public void deleteItem(ActionEvent actionEvent) throws SQLException {
        ImageUpload image = tableView.getSelectionModel().getSelectedItem();
        ImageService.deleteByMaImage(String.valueOf(image.getIdImager()));
        setTableView();
    }

    public void creatNew(ActionEvent actionEvent) throws IOException, SQLException {
        this.image = new ImageUpload();
        changeStage(actionEvent);
    }

    @FXML
    public void onCancel(ActionEvent actionEvent) {
        SingleSelectionModel<Tab> selectionModel = tabView.getSelectionModel();
        selectionModel.select(0);
        this.image = new ImageUpload();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableView();
        clientPagination.setPageFactory(this::setTableView);
        List<String> listProductId = ProductService.getAllID();
        ObservableList<String> data = FXCollections.observableArrayList(listProductId);
        comIdSanPham.setItems(data);
    }

    @FXML
    public void uploadImage(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            imageView.setImage(new Image(file.toURI().toString(), 1200, 1500, true, true));
        }
    }
}
