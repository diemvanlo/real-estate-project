create database javafxbds;

use javafxbds;


create table Userr(
IdUser int identity(0,1) primary key not null,
NameUser nvarchar(50) not null,
UserName varchar(50) not null,
Password varchar(50) not null,
DiaChi nvarchar(100) not null,
Sdt varchar(15) not null,
Email varchar(30),
GioiTinh bit not null,
ChucVu nvarchar(50) not null,
Role int not null,
);
--insert into NguoiDung(TenNguoiDung,TenDangNhap,Password,DiaChi,Sdt,Email,GioiTinh,LinhVuc,Role) values
--(N'Nguyễn Văn Tân','VanTan','123',N'Quảng trị','0325772929','VanTan@gmail.com','true',N'Giám Đốc',1);

create table KhachHang(
IdKhachHang int identity(0,1) primary key not null,
TenKhachHang nvarchar(50) not null ,
GioiTinh bit,
Sdt varchar(20) not null,
Email nvarchar(50),
DiaChi nvarchar(100) not null,

)


create table DanhGia(
IdKhachHang int not null,
NhanXet nvarchar(100),
SoSao int ,
constraint fk_danhgia foreign key (IdKhachHang) references KhachHang(IdKhachHang),
);

create table DoiTac(
IdDoiTac int identity(0,1) primary key not  null,
TenDoiTac nvarchar(50) not null,
LinhVuc nvarchar(50) ,
DiaChi nvarchar(100) not null,
Sdt varchar(20) not null,
Email varchar(50),
Logo varchar(50),
SoVonDaDauTu float,
)

create table DuAn(
IdDuAn int identity(0,1) primary key not null,
TenDuAn nvarchar(50) not null,
LoaiHinh nvarchar(30) not null,
DiaChi nvarchar(50) not null,
DienTich float not null,
ChiPhi float not null,
MucTieu nvarchar(50),
NgayBatDau date,
NgayKetThuc date,
HinhThucQuanLi nvarchar(50) not null,
HinhThucDauTu nvarchar(50) not null,
IdDoiTac int not null,
TrangThai nvarchar(50) ,
MapX float,
MapY float,
BanKinh float,
constraint fk_DuAn foreign key (IdDoiTac) references DoiTac(IdDoiTac), 
)

create table SanPham(
IdSanPham int identity(0,1) primary key not null,
TenSanPham nvarchar(50) not null,
IdDuAn int not null,
DiaChi nvarchar(50) not null,
DienTich float not null,
GiaTien float ,
MoTa nvarchar(50),
NgayTao date ,
NgayBan date,
ChiTiet nvarchar(50),
trangThai nvarchar(50) not null,
IdKhachHang int ,
constraint fk_SanPham foreign key (IdKhachHang) references KhachHang(IdKhachHang),
constraint fk_DuAn_SP foreign key (IdDuAn) references DuAn(IdDuAn),
)

create table HinhAnh(
IdHinhAnh int identity(0,1) primary key not null,
HinhAnh varchar(50) ,
CheDo nvarchar(20) , 
IdSanPham int not null,
constraint fk_HinhAnh foreign key (IdSanPham) references SanPham(IdSanPham),
)