package data;


import model.DoiTac;
import model.KhachHang;
import model.Product;
import model.Project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Excel {
    //    public List<Project> Projects = new ArrayList<Project>();
//    public List<HocVien> hocViens = new ArrayList<HocVien>();
    public List<DoiTac> doiTacs = new ArrayList<DoiTac>();
    public List<KhachHang> khachHangs = new ArrayList<KhachHang>();
    public List<Project> projects = new ArrayList<Project>();
    public List<Product> products = new ArrayList<Product>();

    public List<DoiTac> getDoiTacs() {
        return doiTacs;
    }

    public void setDoiTacs(List<DoiTac> doiTacs) {
        this.doiTacs = doiTacs;
    }

    public List<KhachHang> getKhachHangs() {
        return khachHangs;
    }

    public void setKhachHangs(List<KhachHang> khachHangs) {
        this.khachHangs = khachHangs;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void writeProjectToFile() throws IOException {
        File pathName = new File("src/data/data.txt");
        String sProject;
        try {
            FileWriter wf = new FileWriter(pathName);
            String title = "Id Dự án|Tên dự án|Loại hình|Địa chỉ|Diện tích|Chi phí dự án|Ngày bắt đầu|Ngày kết thúc|Hình thức quản lý|Hình thức đầu tư|Id đối tác|Trạng thái|Vị trí X|Vị trí Y|Bán kính";
            wf.write(title + "\n");
            for (Project e : projects
            ) {
                sProject = e.getIdDuAn() + "|" + e.getTenDuAn() + "|" + e.getLoaiHinh() + "|" + e.getDiaChi() + "|" +
                        e.getDienTich() + "|" + e.getChiPhiDuAn() + "|" + e.getNgayBatDau() + "|" + e.getNgayKetThuc() + "|" +
                        e.getHinhThucQuanLi() + "|" + e.getHinhThucDauTu() + "|" + e.getIdDoiTac() + "|" + e.getTrangThai() + "|" +
                        e.getMapX() + "|" + e.getMapY() + "|" + e.getBanKinh()
                ;
                wf.write(sProject + "\n");
            }
            wf.close();
        } catch (Exception e) {
        }
        Runtime.getRuntime().exec("src/data/printExcel.exe", null, new File("src/data/"));
    }

    public void writeProductToFile() throws IOException {
            File pathName = new File("src/data/data.txt");
        String sProduct;
        try {
            FileWriter wf = new FileWriter(pathName);
            String title = "Id sản phẩm|Tên sản phẩm|idDuAn|Địa chỉ|Diện tích|Giá tiền|Mô tả|Ngày tạo|Ngày bán|Tiến độ|Trạng thái|ID khách hàng|";
            wf.write(title + "\n");
            for (Product e : products
            ) {
                sProduct = e.getIdSanPham() + "|" + e.getTenSanPham() + "|" + e.getIdDuAn() + "|" + e.getDiaChi() + "|" +
                        e.getDienTich() + "|" + e.getGiaTien() + "|" + e.getMoTa() + "|" + e.getNgayTao() + "|" +
                        e.getNgayBan() + "|" + e.getTienDo() + "|" + e.getTrangThai() + "|" + e.getIdKhachHang()
                ;
                wf.write(sProduct + "\n");
            }
            wf.close();
        } catch (Exception e) {
        }
        Runtime.getRuntime().exec("src/data/printExcel.exe", null, new File("src/data/"));
    }

    public void writeKhachHangToFile() throws IOException {
        File pathName = new File("src/data/data.txt");
        String sKhachHang;
        try {
            FileWriter wf = new FileWriter(pathName);
            String title = "Id |Tên khách hàng|Giới tính|Số điện thoại|Email|Địa chỉ|";
            wf.write(title + "\n");
            for (KhachHang e : khachHangs
            ) {
                sKhachHang = e.getIdKhachHang() + "|" + e.getTenKhachHang() + "|" + e.getGioiTinh() + "|" + e.getSdt() + "|" +
                        e.getEmail() + "|" + e.getDiaChi()
                ;
                wf.write(sKhachHang + "\n");
            }
            wf.close();
        } catch (Exception e) {
        }
        Runtime.getRuntime().exec("src/data/printExcel.exe", null, new File("src/data/"));
    }

    public void writeDoiTacToFile() throws IOException {
        File pathName = new File("src/data/data.txt");
        String sDoiTac;
        try {
            FileWriter wf = new FileWriter(pathName);
            String title = "Id |Tên đối tác|Lĩnh vực|Địa chỉ|Số điện thoại|Email|Số vốn đầu tư|";
            wf.write(title + "\n");
            for (DoiTac e : doiTacs
            ) {
                sDoiTac = e.getIdDoiTac() + "|" + e.getTenDoitac() + "|" + e.getLinhVuc() + "|" + e.getDiaChi() + "|" +
                        e.getSdt() + "|" + e.getEmail()+ "|" + e.getSoVonDaDauTu()
                ;
                wf.write(sDoiTac + "\n");
            }
            wf.close();
        } catch (Exception e) {
        }
        Runtime.getRuntime().exec("src/data/printExcel.exe", null, new File("src/data/"));
    }

}
