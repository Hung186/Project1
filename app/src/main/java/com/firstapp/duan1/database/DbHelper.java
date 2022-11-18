package com.firstapp.duan1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context){
        super(context, "SHOPBANHANG", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String dbLoaiHang = "CREATE TABLE LOAIHANG(maloai integer primary key autoincrement, tenloai text)";
        db.execSQL(dbLoaiHang);

        String dbSanPham = "CREATE TABLE SANPHAM(masp integer primary key autoincrement, tensp text, thuonghieu text, giasp real, hinhanh text, motasanpham text, maloai integer references LOAIHANG(maloai))";
        db.execSQL(dbSanPham);

        String dbPhieuGiamGia = "CREATE TABLE PHIEUGIAMGIA(maphieu integer primary key autoincrement, giatrigiam real, ngaynhan text, ngayhethan text, mand integer references NGUOIDUNG(mand))";
        db.execSQL(dbPhieuGiamGia);

        String dbNguoiDung = "CREATE TABLE NGUOIDUNG(mand integer primary key autoincrement, tennd text, email text, sdt text, matkhau text, loaitaikhoan text)";
        db.execSQL(dbNguoiDung);

        String dbHoaDon = "CREATE TABLE HOADON(mahoadon integer primary key autoincrement, trangthai text, ngay text, thanhtien real, mand integer references NGUOIDUNG(mand))";
        db.execSQL(dbHoaDon);

        String dbHoaDonChiTiet = "CREATE TABLE HOADONCHITIET(mahdct integer primary key autoincrement, tensp text, soluong integer, tongtien real, masp integer references SANPHAM(masp), mahoadon integer references HOADON(mahoadon))";
        db.execSQL(dbHoaDonChiTiet);

        db.execSQL("INSERT INTO LOAIHANG VALUES (1, 'Đồng hồ'), (2, 'Nhẫn'), (3, 'Dây chuyền'), (4, 'Vòng tay')");
        db.execSQL("INSERT INTO NGUOIDUNG VALUES (1, 'Admin123', 'nguyenvana123@gmail.com', '0934234244', '12345678', 'Admin'), (2, 'Customer123', 'nguyenvana123@gmail.com', '0934234244', '87654321', 'Customer')");
        db.execSQL("INSERT INTO SANPHAM VALUES (1, 'NHẪN BẠC NỮ ĐÍNH KIM CƯƠNG MOISSANITE POWER LILI_563524', 'APJ', 929000, null, 'Sản phẩm được làm từ bạc S925 đính các viên kim cương Moissanite cao cấp bao quanh. Khoác lên mình thiết kế độc đáo đầy tinh tế, mang đến vẻ đẹp kiêu kỳ, cá tính và sự trẻ trung cho cô nàng sở hữu. Đây cũng là món quà ý nghĩa mà phái mạnh có thể dành cho phái đẹp như thể hiện sự nâng niu, trân trọng, và bảo vệ người phụ nữ mình yêu.\n" +
                "\n" +
                "Lưu ý: Sản phẩm Free Size có thể điều chỉnh kích thước tùy ý\n" +
                "\n', 2), " +
                "(2, 'NHẪN ĐÍNH HÔN VÀNG 18K - LPN1011568.1', 'Lộc Phát', 3180000, null, 'Mang vẻ đẹp sang trọng, quý phái của chất liệu vàng kết hợp cùng nét tinh tế và thanh lịch của thiết kế, nhẫn nữ đính đá xanh lục APJ mang đến cho phái nữ vẻ\n" +
                "3.180.000đ', 2), " +
                "(3, 'NHẪN BẠC NAM ĐÍNH KIM CƯƠNG MOISSANITE KANE LILI_833779', 'APJ', 1360000, null, 'Sản phẩm làm từ bạc S925 đính kim cương Moissanite 1 carat sở hữu vẻ đẹp vừa quý phái lại vừa hiện đại, cho phép bạn sáng tạo, mix match cùng những món trang sức cũng như trang phục khác nhau, sẽ giúp bạn nổi bật và thu hút mọi ánh nhìn dù bạn xuất hiện ở đâu, dạo phố, cafe, tiệc tùng hay đi làm. Đừng ngạc nhiên khi nhiều ánh mắt hướng về bạn vì sự tinh tế này nhé !!', 2), " +
                "(4, 'NHẪN NAM VÀNG VÀNG 10K ĐÁ TRẮNG – N2.0124-1', 'APJ', 11700000, null, ' Nhẫn vàng nam được thiết kế cách điệu cực HOT thay đổi màu đá theo mệnh với thiết kế hình con rồng tượng trưng cho sự đong đầy, cao quý, thiêng liêng và sức mạnh kiên cường luôn bay trên cao - sản phẩm độc quyền bởi Trang Sức TNJ\n" +
                " - Kích cỡ: Bạn đừng quá lo lắng vì tay mình quá to hay quá nhỏ không có size nhẫn?\n" +
                "Bởi vì nhẫn được làm theo size tay của khách hàng (có hướng dẫn đo size tay chi tiết) hoặc bạn có thể nhắn tin với shop để được tư vấn cụ thể hơn nhé.', 2), " +
                "(5, 'Đồng hồ MBT', 'MBT', 3000000, null, 'Thời đại công nghệ 4.0, đồng hồ và các tiện ích khác hầu hết đều được tích hợp trong một chiếc điện thoại thông minh. Vì vậy, người ta đeo đồng hồ với mục đích chính không phải là để xem giờ mà là để tự tin, để khẳng định mình. Họ trót yêu vẻ đẹp và sức hấp dẫn của những chiếc đồng hồ, hay đồng hồ mang đến cho họ một phong cách, vẻ đẹp và sức hấp dẫn riêng.', 1), " +
                "(6, 'Đồng Hồ Q&Q', 'Q&Q', 5000000, null, 'Nhà phân phối độc quyền các thương hiệu đồng hồ, kính mắt, bút ký nổi tiếng thế giới: Epos Swiss, Atlantic Swiss, Diamond D, Philippe Auguste, Jacques Lemans, Citizen, Aries Gold, dây da đồng hồ, dây đồng hồ đeo tay', 1), " +
                "(7, 'ĐỒNG HỒ CASIO MTP', 'CASIO', 2000000, null, 'Mẫu Casio MTP-V004L-1B2UDF mặt số đen size 41mm thiết kế đơn giản trẻ trung 3 kim, phối cùng bộ dây da nâu phiên bản da trơn thời trang.', 1), " +
                "(8, 'ĐỒNG HỒ CASIO EFV', 'CASIO', 4000000, null, 'Mẫu Casio EFV-570D-7AVUDF nổi bật với kiểu dáng 6 kim kèm tính năng Chronograph đo thời gian vượt trội đặc trưng thuộc dòng Edifice dành cho các tín đồ yêu thích phong cách thể thao nhưng lại khoác trên mình vẻ ngoài lịch lãm.', 1)");
        db.execSQL("INSERT INTO PHIEUGIAMGIA VALUES (1, 0.1, null, null, null), (2, 0.15, null, null, null), (3, 0.25, null, null, null), (4, 0.5, null, null, null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS LOAIHANG");
            db.execSQL("DROP TABLE IF EXISTS SANPHAM");
            db.execSQL("DROP TABLE IF EXISTS PHIEUGIAMGIA");
            db.execSQL("DROP TABLE IF EXISTS NGUOIDUNG");
            db.execSQL("DROP TABLE IF EXISTS HOADON");
            db.execSQL("DROP TABLE IF EXISTS HOADONCHITIET");
            onCreate(db);
        }
    }
}
