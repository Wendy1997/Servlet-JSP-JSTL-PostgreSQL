package Service;

import DAO.InvoiceDAO;
import DAO.OrderDetailDAO;
import DAO.PromoDAO;
import Model.Invoice;
import Model.OrderDetail;
import Model.Promo;

import java.sql.SQLException;
import java.util.List;

public class InvoiceServiceDatabase implements InvoiceService {
    InvoiceDAO invoiceDAO = new InvoiceDAO();
    OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
    PromoDAO promoDAO = new PromoDAO();

    @Override
    public Invoice getInvoice(String id, int storeid) throws SQLException {
        return invoiceDAO.getInvoice(id, storeid);
    }

    @Override
    public List<Invoice> getAllInvoice(int storeid) throws SQLException {
        return invoiceDAO.getAllInvoice(storeid);
    }

    @Override
    public void addInvoice(Invoice invoice) throws SQLException {
        invoiceDAO.addInvoice(invoice);
    }

    @Override
    public Invoice getInvoice(Invoice invoice) throws SQLException {
        return invoiceDAO.getInvoice(invoice);
    }

    @Override
    public List<Invoice> getDailyInvoice(String date, int storeid) throws SQLException {
        String[] dateList = date.split("-");
        return invoiceDAO.getDailyInvoice(dateList[2], dateList[1], dateList[0], storeid);
    }

    @Override
    public List<Invoice> getWeeklyInvoice(String date, int storeid) throws SQLException {
        String[] dateList = date.split("-");
        return invoiceDAO.getWeeklyInvoice(dateList[1].substring(1), dateList[0], storeid);
    }

    @Override
    public List<Invoice> getMonthlyInvoice(String date, int storeid) throws SQLException {
        String[] dateList = date.split("-");
        return invoiceDAO.getMonthlyInvoice(dateList[1], dateList[0], storeid);
    }

    @Override
    public List<Invoice> getYearlyInvoice(String year, int storeid) throws SQLException {
        return invoiceDAO.getYearlyInvoice(year, storeid);
    }

    @Override
    public List<OrderDetail> getAllOrderDetail(String id, int storeid) throws SQLException {
        return orderDetailDAO.getAllOrderDetail(id, storeid);
    }

    @Override
    public void addOrderDetail(OrderDetail orderDetail) throws SQLException {
        orderDetailDAO.addOrderDetail(orderDetail);
    }

    @Override
    public Promo getPromo(String id, int storeid) throws SQLException{
        return promoDAO.getPromo(id, storeid);
    }

    @Override
    public Promo getPromo(int storeid) throws SQLException {
        return promoDAO.getPromo(storeid);
    }
}
