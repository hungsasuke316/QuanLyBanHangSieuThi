go
use QuanLyBanHangSieuThi

create table NhanVien(
	ma varchar(20),
	ten varchar(255),
	cccd int,
	sdt int,
	dia_chi varchar(255),
	primary key(ma),

)

create table KhachHang(
	ma varchar(20),
	ten varchar(255),
	cccd int,
	sdt int,
	primary key(ma),

)

create table SanPham(
	ma varchar(20),
	ten varchar(255),
	don_vi_tinh varchar(20),
	don_gia int,
	so_luong_ton int,
	primary key(ma)

)

create table NhaCungCap(
	ma varchar(20),
	ten varchar(255),
	primary key(ma)
)

create table HoaDon(
	ma varchar(20),
	ngay_lap_hoa_don date,
	so_luong int,
	tong_tien int,
	ma_nhan_vien varchar(20),
	ma_san_pham varchar(20),
	ma_khach_hang varchar(20)
	primary key(ma),
	foreign key (ma_nhan_vien) references NhanVien(ma),
	foreign key (ma_khach_hang) references KhachHang(ma),
	foreign key (ma_san_pham) references SanPham(ma)
)


create table PhieuNhap(
	ma varchar(20),
	ngay_lap_phieu_nhap date,
	so_luong int,
	tong_tien int,
	ma_nhan_vien varchar(20),
	ma_san_pham varchar(20),
	ma_nha_cung_cap varchar(20),
	primary key(ma),
	foreign key (ma_nhan_vien) references NhanVien(ma),
	foreign key (ma_san_pham) references SanPham(ma),
	foreign key (ma_nha_cung_cap) references NhaCungCap(ma)
)