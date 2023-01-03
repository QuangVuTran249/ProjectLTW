CREATE TABLE TAI_KHOAN (
	IDTAIKHOAN CHAR(10) ,
	NGUOIDUNG VARCHAR(100) ,
	MATKHAU TEXT,
	IDNGUOIDUNG CHAR(10),
	TRANGTHAI VARCHAR(50),
	ISBLOCK BIT,
	EMAIL CHAR(50),
	AVATAR CHAR(300),
	PRIMARY KEY(IDTAIKHOAN, IDNGUOIDUNG)
)
CREATE TABLE THONG_TIN_NGUOI_DUNG(
	IDNGUOIDUNG CHAR(10) PRIMARY KEY,
	TENNGUOIDUNG VARCHAR(100),
	SDT CHAR(10),
	IDDIACHI CHAR(10),
)
CREATE TABLE DIA_CHI(
	IDDIACHI CHAR(10) PRIMARY KEY,
	XA VARCHAR(50),
	HUYEN VARCHAR(50),
	TINH VARCHAR(50),
	CHITIETDIACHI VARCHAR(50),
)
CREATE TABLE DANH_SACH_VOUCHER(
	ID CHAR(10) PRIMARY KEY,
	IDVOUCHER CHAR(10),
	NGAYBD DATE,
	NGAYKT DATE,
	TENVOUCHER VARCHAR(50),
)
CREATE TABLE VOUCHER(
	IDVOUCHER CHAR(10) PRIMARY KEY,
	MAVOUCHER CHAR(13),
	LOAIVOUCHER VARCHAR(20),
	GIATRI INT,
	TEN VARCHAR(20),
)
CREATE TABLE YEU_THICH(
	IDMONAN CHAR(10) ,
	IDTAIKHOAN CHAR(10),
	PRIMARY KEY(IDMONAN, IDTAIKHOAN),
)
CREATE TABLE CHI_TIET_TAI_KHOAN_DAT_HANG(
	IDTAIKHOAN CHAR(10),
	IDDONHANG CHAR(10),
	PRIMARY KEY(IDTAIKHOAN, IDDONHANG)
)
CREATE TABLE DON_HANG(
	IDDONHANG CHAR(10) PRIMARY KEY,
	NGAYLAPDONHANG DATE,
	SDT CHAR(10),
	TEN VARCHAR(30),
	GHICHU VARCHAR(35),
	IDVOUCHER CHAR(10),
	IDDIACHI CHAR(10),
)

CREATE TABLE CHI_TIET_DON_HANG(
	IDDONHANG CHAR(10) PRIMARY KEY,
	IDMONAN CHAR(10),
	GIA INT,
	SOLUONG INT,
	PHISHIP INT,
	TINHTRANG VARCHAR(30),
)
CREATE TABLE MON_AN(
	IDMONAN CHAR(10) PRIMARY KEY,
	TENMONAN VARCHAR(50),
	IDSIZE CHAR(10),
	GIANIEMYET INT,
	LOAIMONAN VARCHAR(30),
	SOLUONG INT,
	TINHTRANG VARCHAR(30),
	ISCOMBO BIT,
	IDGIAMGIA CHAR(10),
	MOTA TEXT,
)
CREATE TABLE LOAI_SAN_PHAM(
	IDMONAN CHAR(10) PRIMARY KEY,
	TENLOAI VARCHAR(15),
)
CREATE TABLE CHI_TIET_GIAM_GIA(
	IDGIAMGIA CHAR(10) PRIMARY KEY,
	LOAIGIAMGIA VARCHAR(30),
	MENHGIAGIAM INT,
)
CREATE TABLE RO_LE(
	IDSTATUS CHAR(10) PRIMARY KEY,
	LOAI VARCHAR(30),
)
CREATE TABLE CHI_TIET_SIZE(
	IDMONAN CHAR(10) PRIMARY KEY,
	TIEN INT,
	IDSIZE CHAR(10),
)
CREATE TABLE SIZE(
	IDSIZE CHAR(10) PRIMARY KEY,
	LOAISIZE VARCHAR(10),
)
CREATE TABLE DANH_GIA(
	IDDANHGIA CHAR(10) PRIMARY KEY,
	NOIDUNG TEXT,
	LOAIDANHGIA VARCHAR(30),
	IDMONAN CHAR(10),
)
CREATE TABLE CHI_TIET_DANH_GIA(
	IDDANHGIA CHAR(10),
	IDTAIKHOAN CHAR(10),
	THOIGIANDANHGIA DATE,
)