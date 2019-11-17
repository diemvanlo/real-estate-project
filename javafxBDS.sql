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

insert into Userr(NameUser,UserName,Password,DiaChi,Sdt,Email,GioiTinh,ChucVu,Role) values
(N'Nguyễn Văn Tân','VanTan','123',N'Quảng trị','0325772929','VanTan@gmail.com','true',N'Giám Đốc',1);

create table KhachHang(
IdKhachHang int identity(0,1) primary key not null,
TenKhachHang nvarchar(50) not null ,
GioiTinh bit,
DiaChi nvarchar(100) not null,
Sdt varchar(20) not null,
Email nvarchar(50),
);

insert into KhachHang(TenKhachHang,GioiTinh,DiaChi,Sdt,Email) values
(N'Nguyễn Văn A','true',N'Quảng trị','0327772999','Vana@gmail.com'),
(N'Nguyễn Văn B','true',N'Quảng Bình','0566778998','Basd@gmail.com');



create table DoiTac(
IdDoiTac int identity(0,1) primary key not  null,
TenDoiTac nvarchar(30) not null,
LinhVuc nvarchar(30) ,
DiaChi nvarchar(60) not null,
Sdt varchar(15) not null,
Email varchar(15),
Logo Varbinary(max)  ,
SoVonDaDauTu float,
)
insert into DoiTac(TenDoiTac,LinhVuc,DiaChi,Sdt,Email,SoVonDaDauTu) values
(N'Cty ABC',N'Xây Dựng',N'Quảng Nam','0325111222','abc@gmail.com',455465),
(N'Cty BDS',N'Bất Động Sản',N'Quảng Ngãi','0326777988','dda@gmail.com',78826);

create table DuAn(
IdDuAn int identity(0,1) primary key not null,
TenDuAn nvarchar(50) not null,
LoaiHinh nvarchar(30) not null,
DiaChi nvarchar(50) not null,
DienTich float not null,
ChiPhi float not null,
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

insert into DuAn(TenDuAn,LoaiHinh,DiaChi,DienTich,ChiPhi,NgayBatDau,NgayKetThuc,HinhThucQuanLi,HinhThucDauTu,IdDoiTac,TrangThai,MapX,MapY,BanKinh) values
(N'Chung cư ABCD',N'Chung Cư',N'08 Hà văn Tính',112,550,'2017-05-02','',N'Chung Cư',N'góp vốn',0,N'Đang xây dựng',54545,6565,4545),
(N'Chung cư sa',N'Chung Cư',N'03 Hà văn Ngọc',4545,10289,'2019-01-02','',N'Chung Cư',N'góp vốn ',0,N'Đang xây dựng',85698,5454,87887);

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

insert into SanPham(TenSanPham,IdDuAn,DiaChi,DienTich,GiaTien,MoTa,NgayTao,NgayBan,ChiTiet,trangThai,IdKhachHang) values
(N'Phòng 101',0,N'08 Hà Văn Tính',50,510,N'đang xây dựng','2017-05-02','',N'phòng đầy đủ tiện nghi',N'chưa bán',''),
(N'Phòng 02',1,N'03 Hà Văn Ngọc',40,390,N'đang xây dựng','2019-03-02','',N'phòng đầy đủ tiện nghi',N'chưa bán','');

create table DanhGia(
IdKhachHang int not null,
NhanXet nvarchar(100),
SoSao int ,
IdSanPham int,
constraint fk_danhgia foreign key (IdKhachHang) references KhachHang(IdKhachHang),
constraint fk_danhgia2 foreign key (IdSanPham) references SanPham(IdSanPham) 
);

create table HinhAnh(
IdHinhAnh int identity(0,1) primary key not null,
HinhAnh varchar(50) ,
CheDo nvarchar(20) , 
IdSanPham int not null,
constraint fk_HinhAnh foreign key (IdSanPham) references SanPham(IdSanPham),
)