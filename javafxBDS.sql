create database javafxbds;

use javafxbds;


create table NguoiDung(
IdNguoiDung int identity(0,1) primary key not null,
TenNguoiDung nvarchar(50) not null,
TenDangNhap varchar(50) not null,
Password varchar(50) not null,
DiaChi nvarchar(100) not null,
Sdt varchar(15) not null,
Email varchar(30),
GioiTinh bit not null,
LinhVuc nvarchar(50) not null,
Role int not null,
);
--insert into NguoiDung(TenNguoiDung,TenDangNhap,Password,DiaChi,Sdt,Email,GioiTinh,LinhVuc,Role) values
--(N'Nguyễn Văn Tân','VanTan','123',N'Quảng trị','0325772929','VanTan@gmail.com','true',N'BDS Nhà đất',1);

create table DanhGia(
IdNguoiDung int not null,
DanhGia nvarchar(100),
constraint fk_danhgia foreign key (IdNguoiDung) references NguoiDung (IdNguoiDung)
);

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
IdNguoiDung int not null,
TrangThai nvarchar(50) ,
MapX float,
MapY float,
BanKinh float,
constraint fk_DuAn foreign key (IdNguoiDung) references NguoiDung(IdNguoiDung), 
)

create table SanPham(
IdSanPham int identity(0,1) primary key not null,
TenSanPham nvarchar(50) not null,
IdDuAn int not null,
LoaiHinh nvarchar(30) not null,
DiaChi nvarchar(50) not null,
DienTich float not null,
GiaTien float ,
MoTa nvarchar(50),
NgayTao date ,
NgayBan date,
ChiTiet nvarchar(50),
trangThai nvarchar(50) not null,
KiGui varchar(10) not null,
IdNguoiDung int ,
constraint fk_SanPham foreign key (IdNguoiDung) references NguoiDung(IdNguoiDung),
constraint fk_DuAn_SP foreign key (IdDuAn) references DuAn(IdDuAn),
)

create table HinhAnh(
IdHinhAnh int identity(0,1) primary key not null,
HinhAnh varchar(50) ,
CheDo nvarchar(20) , 
IdSanPham int not null,
constraint fk_HinhAnh foreign key (IdSanPham) references SanPham(IdSanPham),
)