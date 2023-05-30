USE [QuanLyBanHangSieuThi]
GO
/****** Object:  Table [dbo].[hoa_don]    Script Date: 5/30/2023 10:32:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[hoa_don](
    [ma] [varchar](10) NOT NULL,
    [ngay_lap_hoa_don] [date] NULL,
    [so_luong] [int] NULL,
    [tong_tien] [bigint] NULL,
    [ma_nhan_vien] [varchar](10) NULL,
    [ma_san_pham] [varchar](10) NULL,
    [ma_khach_hang] [varchar](10) NULL,
    CONSTRAINT [PK__hoa_don__3213C8B7D08574E4] PRIMARY KEY CLUSTERED
(
[ma] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[khach_hang]    Script Date: 5/30/2023 10:32:53 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[khach_hang](
    [ma] [varchar](10) NOT NULL,
    [ten] [nvarchar](255) NULL,
    [cccd] [varchar](13) NULL,
    [sdt] [varchar](10) NULL,
    CONSTRAINT [PK__khach_ha__3213C8B77F44170A] PRIMARY KEY CLUSTERED
(
[ma] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[nha_cung_cap]    Script Date: 5/30/2023 10:32:53 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[nha_cung_cap](
    [ma] [varchar](10) NOT NULL,
    [ten] [nvarchar](255) NULL,
    CONSTRAINT [PK__nha_cung__3213C8B730813F76] PRIMARY KEY CLUSTERED
(
[ma] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[nhan_vien]    Script Date: 5/30/2023 10:32:53 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[nhan_vien](
    [ma] [varchar](10) NOT NULL,
    [ten] [nvarchar](255) NULL,
    [cccd] [varchar](13) NULL,
    [sdt] [varchar](10) NULL,
    [dia_chi] [nvarchar](255) NULL,
    CONSTRAINT [PK__nhan_vie__3213C8B7388E9297] PRIMARY KEY CLUSTERED
(
[ma] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[phieu_nhap]    Script Date: 5/30/2023 10:32:53 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[phieu_nhap](
    [ma] [varchar](10) NOT NULL,
    [ngay_lap_phieu_nhap] [date] NULL,
    [so_luong] [int] NULL,
    [tong_tien] [bigint] NULL,
    [ma_nhan_vien] [varchar](10) NULL,
    [ma_san_pham] [varchar](10) NULL,
    [ma_nha_cung_cap] [varchar](10) NULL,
    CONSTRAINT [PK__phieu_nh__3213C8B7AAFB05EB] PRIMARY KEY CLUSTERED
(
[ma] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[san_pham]    Script Date: 5/30/2023 10:32:53 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[san_pham](
    [ma] [varchar](10) NOT NULL,
    [ten] [nvarchar](255) NULL,
    [don_vi_tinh] [nvarchar](50) NULL,
    [don_gia] [bigint] NULL,
    [so_luong_ton] [int] NULL,
    CONSTRAINT [PK__san_pham__3213C8B7135FA124] PRIMARY KEY CLUSTERED
(
[ma] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
    INSERT [dbo].[hoa_don] ([ma], [ngay_lap_hoa_don], [so_luong], [tong_tien], [ma_nhan_vien], [ma_san_pham], [ma_khach_hang]) VALUES (N'HD01', CAST(N'2023-04-30' AS Date), 10, 70000, N'NV01', N'SP01', NULL)
    INSERT [dbo].[hoa_don] ([ma], [ngay_lap_hoa_don], [so_luong], [tong_tien], [ma_nhan_vien], [ma_san_pham], [ma_khach_hang]) VALUES (N'HD02', CAST(N'2023-04-30' AS Date), 10, 1000000, N'NV01', N'SP02', N'KH15')
    INSERT [dbo].[hoa_don] ([ma], [ngay_lap_hoa_don], [so_luong], [tong_tien], [ma_nhan_vien], [ma_san_pham], [ma_khach_hang]) VALUES (N'HD03', CAST(N'2023-04-30' AS Date), 5, 900000, N'NV02', N'SP05', NULL)
    INSERT [dbo].[hoa_don] ([ma], [ngay_lap_hoa_don], [so_luong], [tong_tien], [ma_nhan_vien], [ma_san_pham], [ma_khach_hang]) VALUES (N'HD04', CAST(N'2023-04-30' AS Date), 10, 200000, N'NV02', N'SP09', N'KH18')
    INSERT [dbo].[hoa_don] ([ma], [ngay_lap_hoa_don], [so_luong], [tong_tien], [ma_nhan_vien], [ma_san_pham], [ma_khach_hang]) VALUES (N'HD05', CAST(N'2023-04-30' AS Date), 15, 900000, N'NV03', N'SP14', N'KH07')
    INSERT [dbo].[hoa_don] ([ma], [ngay_lap_hoa_don], [so_luong], [tong_tien], [ma_nhan_vien], [ma_san_pham], [ma_khach_hang]) VALUES (N'HD06', CAST(N'2023-04-30' AS Date), 10, 1000000, N'NV04', N'SP16', NULL)
    INSERT [dbo].[hoa_don] ([ma], [ngay_lap_hoa_don], [so_luong], [tong_tien], [ma_nhan_vien], [ma_san_pham], [ma_khach_hang]) VALUES (N'HD07', CAST(N'2023-05-30' AS Date), 10, 100000, N'NV03', N'SP07', NULL)
    INSERT [dbo].[hoa_don] ([ma], [ngay_lap_hoa_don], [so_luong], [tong_tien], [ma_nhan_vien], [ma_san_pham], [ma_khach_hang]) VALUES (N'HD08', CAST(N'2023-05-30' AS Date), 15, 1800000, N'NV04', N'SP15', N'KH06')
    INSERT [dbo].[hoa_don] ([ma], [ngay_lap_hoa_don], [so_luong], [tong_tien], [ma_nhan_vien], [ma_san_pham], [ma_khach_hang]) VALUES (N'HD09', CAST(N'2023-05-30' AS Date), 5, 1600000, N'NV01', N'SP04', N'KH08')
    INSERT [dbo].[hoa_don] ([ma], [ngay_lap_hoa_don], [so_luong], [tong_tien], [ma_nhan_vien], [ma_san_pham], [ma_khach_hang]) VALUES (N'HD10', CAST(N'2023-05-30' AS Date), 100, 500000, N'NV02', N'SP10', N'KH11')
    INSERT [dbo].[hoa_don] ([ma], [ngay_lap_hoa_don], [so_luong], [tong_tien], [ma_nhan_vien], [ma_san_pham], [ma_khach_hang]) VALUES (N'HD11', CAST(N'2023-05-30' AS Date), 10, 120000, N'NV01', N'SP11', N'KH03')
    INSERT [dbo].[hoa_don] ([ma], [ngay_lap_hoa_don], [so_luong], [tong_tien], [ma_nhan_vien], [ma_san_pham], [ma_khach_hang]) VALUES (N'HD12', CAST(N'2023-05-30' AS Date), 10, 70000, N'NV04', N'SP01', N'KH12')
    INSERT [dbo].[hoa_don] ([ma], [ngay_lap_hoa_don], [so_luong], [tong_tien], [ma_nhan_vien], [ma_san_pham], [ma_khach_hang]) VALUES (N'HD14', CAST(N'2023-05-30' AS Date), 700, 224000000, N'NV01', N'SP04', N'KH01')
    INSERT [dbo].[hoa_don] ([ma], [ngay_lap_hoa_don], [so_luong], [tong_tien], [ma_nhan_vien], [ma_san_pham], [ma_khach_hang]) VALUES (N'HD15', CAST(N'2023-04-30' AS Date), 500, 60000000, N'NV03', N'SP15', N'KH03')
    GO
    INSERT [dbo].[khach_hang] ([ma], [ten], [cccd], [sdt]) VALUES (N'KH01', N'Hồ Ngọc Khắc Hưng', N'051200018016', N'0355320210')
    INSERT [dbo].[khach_hang] ([ma], [ten], [cccd], [sdt]) VALUES (N'KH02', N'Phạm Minh Quốc', N'051200018016', N'0355320210')
    INSERT [dbo].[khach_hang] ([ma], [ten], [cccd], [sdt]) VALUES (N'KH03', N'Nguyễn Huy Hoàng', N'051200018016', N'0355320210')
    INSERT [dbo].[khach_hang] ([ma], [ten], [cccd], [sdt]) VALUES (N'KH04', N'Nguyễn Quốc Doanh', N'051200018016', N'0355320210')
    INSERT [dbo].[khach_hang] ([ma], [ten], [cccd], [sdt]) VALUES (N'KH05', N'Hồ Huy Hoàng', N'051200018016', N'0355320210')
    INSERT [dbo].[khach_hang] ([ma], [ten], [cccd], [sdt]) VALUES (N'KH06', N'Lê Thanh Danh', N'051200018016', N'0355320210')
    INSERT [dbo].[khach_hang] ([ma], [ten], [cccd], [sdt]) VALUES (N'KH07', N'Nguyễn Chánh', N'051200018016', N'0355320210')
    INSERT [dbo].[khach_hang] ([ma], [ten], [cccd], [sdt]) VALUES (N'KH08', N'Huỳnh Thị Ly', N'051200018016', N'0355320210')
    INSERT [dbo].[khach_hang] ([ma], [ten], [cccd], [sdt]) VALUES (N'KH09', N'Nguyễn Lâm Khánh Quỳnh', N'051200018016', N'0355320210')
    INSERT [dbo].[khach_hang] ([ma], [ten], [cccd], [sdt]) VALUES (N'KH10', N'Ngô Huy Hoàng', N'051200018016', N'0355320210')
    INSERT [dbo].[khach_hang] ([ma], [ten], [cccd], [sdt]) VALUES (N'KH11', N'Nguyễn Khắc Huy', N'051200018016', N'0355320210')
    INSERT [dbo].[khach_hang] ([ma], [ten], [cccd], [sdt]) VALUES (N'KH12', N'Lâm Xuân Triệu', N'051200018016', N'0355320210')
    INSERT [dbo].[khach_hang] ([ma], [ten], [cccd], [sdt]) VALUES (N'KH14', N'Đoàn Thị Diễm', N'051200018016', N'0355320210')
    INSERT [dbo].[khach_hang] ([ma], [ten], [cccd], [sdt]) VALUES (N'KH15', N'Nguyễn Thị Hà Nhi', N'051200018016', N'0355320210')
    INSERT [dbo].[khach_hang] ([ma], [ten], [cccd], [sdt]) VALUES (N'KH16', N'Nguyễn Trần Phúc', N'051200018016', N'0355320210')
    INSERT [dbo].[khach_hang] ([ma], [ten], [cccd], [sdt]) VALUES (N'KH17', N'Nguyễn Thị Kim Lai', N'051200018016', N'0355320210')
    INSERT [dbo].[khach_hang] ([ma], [ten], [cccd], [sdt]) VALUES (N'KH18', N'Trần Thanh Chính', N'051200018016', N'0355320210')
    INSERT [dbo].[khach_hang] ([ma], [ten], [cccd], [sdt]) VALUES (N'KH19', N'Ðoàn Minh Son', N'051200018016', N'0355320210')
    INSERT [dbo].[khach_hang] ([ma], [ten], [cccd], [sdt]) VALUES (N'KH20', N'Ðào Quang', N'051200018016', N'0355320210')
    INSERT [dbo].[khach_hang] ([ma], [ten], [cccd], [sdt]) VALUES (N'KH21', N'Phan Mạnh Quỳnh', N'051200018016', N'0355320210')
    INSERT [dbo].[khach_hang] ([ma], [ten], [cccd], [sdt]) VALUES (N'KH22', N'Mỹ Tâm', N'051200018016', N'0355320210')
    INSERT [dbo].[khach_hang] ([ma], [ten], [cccd], [sdt]) VALUES (N'KH23', N'Hoài Lâm', N'051200018016', N'0355320210')
    INSERT [dbo].[khach_hang] ([ma], [ten], [cccd], [sdt]) VALUES (N'KH24', N'Huỳnh Phi Quyền', N'051200018016', N'0355320210')
    INSERT [dbo].[khach_hang] ([ma], [ten], [cccd], [sdt]) VALUES (N'KH25', N'Huỳnh Thị Lan', N'051200018016', N'0355320210')
    GO
    INSERT [dbo].[nha_cung_cap] ([ma], [ten]) VALUES (N'NCC01', N'Vinamilk')
    INSERT [dbo].[nha_cung_cap] ([ma], [ten]) VALUES (N'NCC02', N'CocaCola')
    INSERT [dbo].[nha_cung_cap] ([ma], [ten]) VALUES (N'NCC03', N'SunShine Food')
    INSERT [dbo].[nha_cung_cap] ([ma], [ten]) VALUES (N'NCC04', N'Alibaba')
    INSERT [dbo].[nha_cung_cap] ([ma], [ten]) VALUES (N'NCC05', N'Heineken International')
    GO
    INSERT [dbo].[nhan_vien] ([ma], [ten], [cccd], [sdt], [dia_chi]) VALUES (N'NV01', N'Hồ Ngọc Khắc Hưng', N'051200018016', N'0355320210', N'Quảng Ngãi')
    INSERT [dbo].[nhan_vien] ([ma], [ten], [cccd], [sdt], [dia_chi]) VALUES (N'NV02', N'Phạm Minh Quốc', N'051200018016', N'0355320210', N'Quảng Ngãi')
    INSERT [dbo].[nhan_vien] ([ma], [ten], [cccd], [sdt], [dia_chi]) VALUES (N'NV03', N'Nguyễn Huy Hoàng', N'051200018016', N'0355320210', N'Hồ Chí Minh')
    INSERT [dbo].[nhan_vien] ([ma], [ten], [cccd], [sdt], [dia_chi]) VALUES (N'NV04', N'Nguyễn Quốc Doanh', N'051200018016', N'0355320210', N'Hồ Chí Minh')
    INSERT [dbo].[nhan_vien] ([ma], [ten], [cccd], [sdt], [dia_chi]) VALUES (N'NV05', N'Hồ Huy Hoàng', N'051200018016', N'0355320210', N'Quảng Ngãi')
    INSERT [dbo].[nhan_vien] ([ma], [ten], [cccd], [sdt], [dia_chi]) VALUES (N'NV06', N'Lê Thanh Danh', N'051200018016', N'0355320210', N'Quảng Ngãi')
    INSERT [dbo].[nhan_vien] ([ma], [ten], [cccd], [sdt], [dia_chi]) VALUES (N'NV07', N'Nguyễn Chánh', N'051200018016', N'0355320210', N'Kiên Giang')
    INSERT [dbo].[nhan_vien] ([ma], [ten], [cccd], [sdt], [dia_chi]) VALUES (N'NV08', N'Huỳnh Thị Ly', N'051200018016', N'0355320210', N'Quảng Ngãi')
    INSERT [dbo].[nhan_vien] ([ma], [ten], [cccd], [sdt], [dia_chi]) VALUES (N'NV09', N'Nguyễn Lâm Khánh Quỳnh', N'051200018016', N'0355320210', N'Quảng Ngãi')
    INSERT [dbo].[nhan_vien] ([ma], [ten], [cccd], [sdt], [dia_chi]) VALUES (N'NV10', N'Ngô Huy Hoàng', N'051200018016', N'0355320210', N'Quảng Ngãi')
    INSERT [dbo].[nhan_vien] ([ma], [ten], [cccd], [sdt], [dia_chi]) VALUES (N'NV11', N'Nguyễn Khắc Huy', N'051200018016', N'0355320210', N'Quảng Ngãi')
    INSERT [dbo].[nhan_vien] ([ma], [ten], [cccd], [sdt], [dia_chi]) VALUES (N'NV12', N'Lâm Xuân Triệu', N'051200018016', N'0355320210', N'Quảng Ngãi')
    INSERT [dbo].[nhan_vien] ([ma], [ten], [cccd], [sdt], [dia_chi]) VALUES (N'NV14', N'Đoàn Thị Diễm', N'051200018016', N'0355320210', N'Quảng Ngãi')
    INSERT [dbo].[nhan_vien] ([ma], [ten], [cccd], [sdt], [dia_chi]) VALUES (N'NV15', N'Nguyễn Thị Hà Nhi', N'051200018016', N'0355320210', N'Quảng Ngãi')
    INSERT [dbo].[nhan_vien] ([ma], [ten], [cccd], [sdt], [dia_chi]) VALUES (N'NV16', N'Nguyễn Trần Phúc', N'051200018016', N'0355320210', N'Quảng Ngãi')
    INSERT [dbo].[nhan_vien] ([ma], [ten], [cccd], [sdt], [dia_chi]) VALUES (N'NV17', N'Nguyễn Thị Kim Lai', N'051200018016', N'0355320210', N'Quảng Ngãi')
    INSERT [dbo].[nhan_vien] ([ma], [ten], [cccd], [sdt], [dia_chi]) VALUES (N'NV18', N'Trần Thanh Chính', N'051200018016', N'0355320210', N'Quảng Ngãi')
    INSERT [dbo].[nhan_vien] ([ma], [ten], [cccd], [sdt], [dia_chi]) VALUES (N'NV19', N'Đoàn Minh Sơn', N'051200018016', N'0355320210', N'Quảng Ngãi')
    INSERT [dbo].[nhan_vien] ([ma], [ten], [cccd], [sdt], [dia_chi]) VALUES (N'NV20', N'Đào Quang', N'051200018016', N'0355320210', N'Quảng Ngãi')
    INSERT [dbo].[nhan_vien] ([ma], [ten], [cccd], [sdt], [dia_chi]) VALUES (N'NV21', N'Phan Mạnh Quỳnh', N'051200018016', N'0355320210', N'Hồ Chí Minh')
    INSERT [dbo].[nhan_vien] ([ma], [ten], [cccd], [sdt], [dia_chi]) VALUES (N'NV22', N'Mỹ Tâm', N'051200018016', N'0355320210', N'Hồ Chí Minh')
    INSERT [dbo].[nhan_vien] ([ma], [ten], [cccd], [sdt], [dia_chi]) VALUES (N'NV23', N'Hoài Lâm', N'051200018016', N'0355320210', N'Hồ Chí Minh')
    INSERT [dbo].[nhan_vien] ([ma], [ten], [cccd], [sdt], [dia_chi]) VALUES (N'NV24', N'Huỳnh Phi Quyền', N'051200018016', N'0355320210', N'Quảng Ngãi')
    INSERT [dbo].[nhan_vien] ([ma], [ten], [cccd], [sdt], [dia_chi]) VALUES (N'NV25', N'Huỳnh Thị Lan', N'051200018016', N'0355320210', N'Quảng Ngãi')
    GO
    INSERT [dbo].[phieu_nhap] ([ma], [ngay_lap_phieu_nhap], [so_luong], [tong_tien], [ma_nhan_vien], [ma_san_pham], [ma_nha_cung_cap]) VALUES (N'PN01', CAST(N'2023-04-30' AS Date), 1000, 6300000, N'NV01', N'SP01', N'NCC01')
    INSERT [dbo].[phieu_nhap] ([ma], [ngay_lap_phieu_nhap], [so_luong], [tong_tien], [ma_nhan_vien], [ma_san_pham], [ma_nha_cung_cap]) VALUES (N'PN02', CAST(N'2023-04-30' AS Date), 1000, 9000000, N'NV02', N'SP07', N'NCC02')
    INSERT [dbo].[phieu_nhap] ([ma], [ngay_lap_phieu_nhap], [so_luong], [tong_tien], [ma_nhan_vien], [ma_san_pham], [ma_nha_cung_cap]) VALUES (N'PN03', CAST(N'2023-04-30' AS Date), 1000, 54000000, N'NV04', N'SP14', N'NCC03')
    INSERT [dbo].[phieu_nhap] ([ma], [ngay_lap_phieu_nhap], [so_luong], [tong_tien], [ma_nhan_vien], [ma_san_pham], [ma_nha_cung_cap]) VALUES (N'PN04', CAST(N'2023-04-30' AS Date), 1000, 4500000, N'NV04', N'SP10', N'NCC04')
    INSERT [dbo].[phieu_nhap] ([ma], [ngay_lap_phieu_nhap], [so_luong], [tong_tien], [ma_nhan_vien], [ma_san_pham], [ma_nha_cung_cap]) VALUES (N'PN05', CAST(N'2023-04-30' AS Date), 1000, 288000000, N'NV05', N'SP04', N'NCC05')
    INSERT [dbo].[phieu_nhap] ([ma], [ngay_lap_phieu_nhap], [so_luong], [tong_tien], [ma_nhan_vien], [ma_san_pham], [ma_nha_cung_cap]) VALUES (N'PN06', CAST(N'2023-04-30' AS Date), 1000, 108000000, N'NV01', N'SP15', N'NCC03')
    INSERT [dbo].[phieu_nhap] ([ma], [ngay_lap_phieu_nhap], [so_luong], [tong_tien], [ma_nhan_vien], [ma_san_pham], [ma_nha_cung_cap]) VALUES (N'PN07', CAST(N'2023-04-30' AS Date), 1000, 9000000, N'NV02', N'SP03', N'NCC02')
    INSERT [dbo].[phieu_nhap] ([ma], [ngay_lap_phieu_nhap], [so_luong], [tong_tien], [ma_nhan_vien], [ma_san_pham], [ma_nha_cung_cap]) VALUES (N'PN08', CAST(N'2023-04-30' AS Date), 1000, 9000000, N'NV01', N'SP03', N'NCC04')
    INSERT [dbo].[phieu_nhap] ([ma], [ngay_lap_phieu_nhap], [so_luong], [tong_tien], [ma_nhan_vien], [ma_san_pham], [ma_nha_cung_cap]) VALUES (N'PN09', CAST(N'2023-04-30' AS Date), 1000, 10800000, N'NV03', N'SP11', N'NCC05')
    INSERT [dbo].[phieu_nhap] ([ma], [ngay_lap_phieu_nhap], [so_luong], [tong_tien], [ma_nhan_vien], [ma_san_pham], [ma_nha_cung_cap]) VALUES (N'PN10', CAST(N'2023-05-30' AS Date), 1000, 90000000, N'NV04', N'SP02', N'NCC02')
    INSERT [dbo].[phieu_nhap] ([ma], [ngay_lap_phieu_nhap], [so_luong], [tong_tien], [ma_nhan_vien], [ma_san_pham], [ma_nha_cung_cap]) VALUES (N'PN11', CAST(N'2023-05-30' AS Date), 1000, 90000000, N'NV01', N'SP16', N'NCC03')
    INSERT [dbo].[phieu_nhap] ([ma], [ngay_lap_phieu_nhap], [so_luong], [tong_tien], [ma_nhan_vien], [ma_san_pham], [ma_nha_cung_cap]) VALUES (N'PN12', CAST(N'2023-05-30' AS Date), 1000, 162000000, N'NV02', N'SP05', N'NCC04')
    INSERT [dbo].[phieu_nhap] ([ma], [ngay_lap_phieu_nhap], [so_luong], [tong_tien], [ma_nhan_vien], [ma_san_pham], [ma_nha_cung_cap]) VALUES (N'PN14', CAST(N'2023-05-30' AS Date), 1000, 18000000, N'NV03', N'SP09', N'NCC01')
    GO
    INSERT [dbo].[san_pham] ([ma], [ten], [don_vi_tinh], [don_gia], [so_luong_ton]) VALUES (N'SP01', N'Sữa Vinamilk', N'Hộp', 7000, 980)
    INSERT [dbo].[san_pham] ([ma], [ten], [don_vi_tinh], [don_gia], [so_luong_ton]) VALUES (N'SP02', N'Bánh Socola', N'Bịch', 100000, 990)
    INSERT [dbo].[san_pham] ([ma], [ten], [don_vi_tinh], [don_gia], [so_luong_ton]) VALUES (N'SP03', N'Nước giải khát Revive', N'Chai', 10000, 1000)
    INSERT [dbo].[san_pham] ([ma], [ten], [don_vi_tinh], [don_gia], [so_luong_ton]) VALUES (N'SP04', N'Bia Tiger', N'Thùng', 320000, 295)
    INSERT [dbo].[san_pham] ([ma], [ten], [don_vi_tinh], [don_gia], [so_luong_ton]) VALUES (N'SP05', N'Dầu gội Clear', N'Chai', 180000, 995)
    INSERT [dbo].[san_pham] ([ma], [ten], [don_vi_tinh], [don_gia], [so_luong_ton]) VALUES (N'SP06', N'Mì Gói Hảo Hảo', N'Thùng', 150000, 0)
    INSERT [dbo].[san_pham] ([ma], [ten], [don_vi_tinh], [don_gia], [so_luong_ton]) VALUES (N'SP07', N'Nước ngọt CocaCola', N'Lon', 10000, 990)
    INSERT [dbo].[san_pham] ([ma], [ten], [don_vi_tinh], [don_gia], [so_luong_ton]) VALUES (N'SP08', N'Xoài', N'Kg', 15000, 0)
    INSERT [dbo].[san_pham] ([ma], [ten], [don_vi_tinh], [don_gia], [so_luong_ton]) VALUES (N'SP09', N'Mận', N'Kg', 20000, 990)
    INSERT [dbo].[san_pham] ([ma], [ten], [don_vi_tinh], [don_gia], [so_luong_ton]) VALUES (N'SP10', N'Muỗng', N'Chiếc', 5000, 900)
    INSERT [dbo].[san_pham] ([ma], [ten], [don_vi_tinh], [don_gia], [so_luong_ton]) VALUES (N'SP11', N'Nước tăng lực', N'Lon', 12000, 990)
    INSERT [dbo].[san_pham] ([ma], [ten], [don_vi_tinh], [don_gia], [so_luong_ton]) VALUES (N'SP12', N'Dao', N'Chiếc', 50000, 0)
    INSERT [dbo].[san_pham] ([ma], [ten], [don_vi_tinh], [don_gia], [so_luong_ton]) VALUES (N'SP14', N'Thịt heo', N'Kg', 60000, 985)
    INSERT [dbo].[san_pham] ([ma], [ten], [don_vi_tinh], [don_gia], [so_luong_ton]) VALUES (N'SP15', N'Thịt bò', N'Kg', 120000, 485)
    INSERT [dbo].[san_pham] ([ma], [ten], [don_vi_tinh], [don_gia], [so_luong_ton]) VALUES (N'SP16', N'Thịt gà', N'Kg', 100000, 990)
    GO
ALTER TABLE [dbo].[hoa_don]  WITH CHECK ADD  CONSTRAINT [FK__hoa_don__ma_khac__656C112C] FOREIGN KEY([ma_khach_hang])
    REFERENCES [dbo].[khach_hang] ([ma])
    GO
ALTER TABLE [dbo].[hoa_don] CHECK CONSTRAINT [FK__hoa_don__ma_khac__656C112C]
    GO
ALTER TABLE [dbo].[hoa_don]  WITH CHECK ADD  CONSTRAINT [FK__hoa_don__ma_nhan__6477ECF3] FOREIGN KEY([ma_nhan_vien])
    REFERENCES [dbo].[nhan_vien] ([ma])
    GO
ALTER TABLE [dbo].[hoa_don] CHECK CONSTRAINT [FK__hoa_don__ma_nhan__6477ECF3]
    GO
ALTER TABLE [dbo].[hoa_don]  WITH CHECK ADD  CONSTRAINT [FK__hoa_don__ma_san___66603565] FOREIGN KEY([ma_san_pham])
    REFERENCES [dbo].[san_pham] ([ma])
    GO
ALTER TABLE [dbo].[hoa_don] CHECK CONSTRAINT [FK__hoa_don__ma_san___66603565]
    GO
ALTER TABLE [dbo].[phieu_nhap]  WITH CHECK ADD  CONSTRAINT [FK__phieu_nha__ma_nh__693CA210] FOREIGN KEY([ma_nhan_vien])
    REFERENCES [dbo].[nhan_vien] ([ma])
    GO
ALTER TABLE [dbo].[phieu_nhap] CHECK CONSTRAINT [FK__phieu_nha__ma_nh__693CA210]
    GO
ALTER TABLE [dbo].[phieu_nhap]  WITH CHECK ADD  CONSTRAINT [FK__phieu_nha__ma_nh__6B24EA82] FOREIGN KEY([ma_nha_cung_cap])
    REFERENCES [dbo].[nha_cung_cap] ([ma])
    GO
ALTER TABLE [dbo].[phieu_nhap] CHECK CONSTRAINT [FK__phieu_nha__ma_nh__6B24EA82]
    GO
ALTER TABLE [dbo].[phieu_nhap]  WITH CHECK ADD  CONSTRAINT [FK__phieu_nha__ma_sa__6A30C649] FOREIGN KEY([ma_san_pham])
    REFERENCES [dbo].[san_pham] ([ma])
    GO
ALTER TABLE [dbo].[phieu_nhap] CHECK CONSTRAINT [FK__phieu_nha__ma_sa__6A30C649]
    GO
