go
use QuanLyBanHangSieuThi

create table nhan_vien(
	ma varchar(10),
	ten varchar(255),
	cccd varchar(13),
	sdt int,
	dia_chi varchar(255),
	primary key(ma),

)

create table khach_hang(
	ma varchar(10),
	ten varchar(255),
	cccd varchar(13),
	sdt int,
	primary key(ma),

)

create table san_pham(
	ma varchar(10),
	ten varchar(255),
	don_vi_tinh varchar(20),
	don_gia bigint,
	so_luong_ton int,
	primary key(ma)

)

create table nha_cung_cap(
	ma varchar(10),
	ten varchar(255),
	primary key(ma)
)

create table hoa_don(
	ma varchar(10),
	ngay_lap_hoa_don date,
	so_luong int,
	tong_tien bigint,
	ma_nhan_vien varchar(10),
	ma_san_pham varchar(10),
	ma_khach_hang varchar(10)
	primary key(ma),
	foreign key (ma_nhan_vien) references nhan_vien(ma),
	foreign key (ma_khach_hang) references khach_hang(ma),
	foreign key (ma_san_pham) references san_pham(ma)
)


create table phieu_nhap(
	ma varchar(10),
	ngay_lap_phieu_nhap date,
	so_luong int,
	tong_tien bigint,
	ma_nhan_vien varchar(10),
	ma_san_pham varchar(10),
	ma_nha_cung_cap varchar(10),
	primary key(ma),
	foreign key (ma_nhan_vien) references nhan_vien(ma),
	foreign key (ma_san_pham) references san_pham(ma),
	foreign key (ma_nha_cung_cap) references nha_cung_cap(ma)
)
