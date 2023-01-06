package main.services;

import main.bean.*;
import main.db.ConnectMysqlExample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OderService {
    private static OderService instance;

    private OderService() {

    }

    private List<User> alluser;

    public static OderService getInstance() {
        if (instance == null) {
            instance = new OderService();
        }
        return instance;
    }

    public ArrayList<Order> getAllInforOder(String oderId) {
        ArrayList<Order> allOder = new ArrayList<Order>();
        try {
            Connection conn = ConnectMysqlExample.getConnection(ConnectMysqlExample.getDbUrl(), ConnectMysqlExample.getUserName(), ConnectMysqlExample.getPASSWORD());

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(("SELECT orders.ID_ORDER, NAMES, PHONE_NUMBER , NOTE , ORDER_DATE, STATUSS , ID_ACCOUNT \n" +
                    "from orders \n" +
                    "JOIN order_account_details \n" +
                    "on order_account_details.ID_ORDER = orders.ID_ORDER"));

            while (rs.next()) {

                Address ad = null;
                Voucher vc = null;
                ArrayList<OderCart> pr = null;
                allOder.add(new Order(
                                rs.getString(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getString(4),
                                rs.getDate(5),
                                rs.getString(6),
                                rs.getString(7),
                                ad,
                                vc,
                                pr)
                );
            }
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return allOder;
    }
    public ArrayList<Order> getAllOder(){
        ArrayList<Order> allOder = new ArrayList<Order>();
        ArrayList<OderCart> orc;
        try {
            Connection conn = ConnectMysqlExample.getConnection(ConnectMysqlExample.getDbUrl(), ConnectMysqlExample.getUserName(), ConnectMysqlExample.getPASSWORD());

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(("SELECT orders.ID_ORDER, ID_ACCOUNT , STATUSS  \n" +
                    "from orders \n" +
                    "JOIN order_account_details \n" +
                    "on order_account_details.ID_ORDER = orders.ID_ORDER"));

            while (rs.next()) {
                String idOder = rs.getString(1);
                Voucher voucher = VoucherService.getInstance().getVcByOderId(idOder);
                Address ad = AdressService.getInstance().getAdressByOderId(idOder);
                orc = (ProductsService.getInstance().getProductByOderID(idOder));
                allOder.add(new Order(idOder,rs.getString(2),ad,orc,rs.getString(3),voucher));
            }
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return allOder;
    }
    public Order getInforOder(String idOder){
        ArrayList<OderCart> orc ;
        Order rsl  = new Order();
        try {
            Connection conn = ConnectMysqlExample.getConnection(ConnectMysqlExample.getDbUrl(), ConnectMysqlExample.getUserName(), ConnectMysqlExample.getPASSWORD());

            PreparedStatement stmt = conn.prepareStatement("\n" +
                    "SELECT orders.ID_ORDER, NAMES, PHONE_NUMBER , NOTE , ORDER_DATE, STATUSS , DELIVERY_CHARGES \n" +
                    "from orders \n" +
                    "JOIN order_account_details \n" +
                    "on order_account_details.ID_ORDER = orders.ID_ORDER WHERE orders.ID_ORDER =?");
            stmt.setString(1,idOder);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Voucher voucher = VoucherService.getInstance().getVcByOderId(idOder);
                Address ad = AdressService.getInstance().getAdressByOderId(idOder);
                orc = (ProductsService.getInstance().getProductByOderID(idOder));
                rsl.setIdOder(rs.getString(1));
                rsl.setPbuyName(rs.getString(2));
                rsl.setPhoneNumber(rs.getString(3));
                rsl.setNote(rs.getString(4));
                rsl.setStatus(rs.getString(6));
                rsl.setShip(rs.getInt(7));
                rsl.setVoucher(voucher);
                rsl.setAddress(ad);
                rsl.setAllOderCart(orc);
            }
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rsl;
    }
}
