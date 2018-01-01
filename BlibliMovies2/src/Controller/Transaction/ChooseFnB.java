package Controller.Transaction;

import DAO.OrderDetailDAO;
import Model.FilmTicket;
import Model.FnB;
import Model.Invoice;
import Model.OrderDetail;
import Service.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.xml.internal.bind.v2.TODO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebFault;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Sebuah kelas yang menghandle pembelian fnb dan input data member jika memiliki
 * url: /cashier/fnb
 */
@WebServlet("/cashier/fnb")
public class ChooseFnB extends HttpServlet{
    FnBService fnBService = new FnBServiceDatabase();
    InvoiceService invoiceService = new InvoiceServiceDatabase();
    FilmService filmService = new FilmServiceDatabase();

    /**
     * Sebuah method GET yang akan menampilkan data fnb, summary ayng telah dibeli dan juga member card
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Initial Address
        String address = "/view/transaction/choose_fnb.jsp";

        // Validasi apakah sudah login store
        if(request.getSession().getAttribute("storeid") == null){
            address = "/view/login/store_login.jsp";
            request.getRequestDispatcher(address).forward(request, response);
        }

        // Validasi apakah sudah login akun
        else if (request.getSession().getAttribute("role") == null){
            address = "/view/login/account_login.jsp";
            request.getRequestDispatcher(address).forward(request, response);
        }

        try{
            // Sebuah pengecekan apabila customer tidak membeli tiket film
            if(!(request.getParameter("ticketQuantity") == null)){
                request.setAttribute("ticketQuantity", request.getParameter("ticketQuantity"));
                request.setAttribute("film", filmService.getFilmTrue(request.getParameter("film"), (int)request.getSession().getAttribute("storeid")));
                request.setAttribute("studio", filmService.getStudioTrue(request.getParameter("studioid"), (int)request.getSession().getAttribute("storeid")));
                request.setAttribute("screening", filmService.getScreeningTimeTrue(request.getParameter("screeningid"), request.getParameter("film"), (int)request.getSession().getAttribute("storeid")));
            }

            // Sebuah method yang mengambil seluruh fnb yang aktif
            List<FnB> fnBList = fnBService.getAllFnBTrue((int)request.getSession().getAttribute("storeid"));

            // Looping untuk menambahkan uploads pada direktori penyimpanan
            for(int i = 0; i < fnBList.size(); i++){
                fnBList.get(i).setCover("/uploads" + fnBList.get(i).getCover());
            }

            request.setAttribute("fnblist", fnBList);
        } catch (SQLException e){
            e.printStackTrace();
        }

        request.getRequestDispatcher(address).forward(request, response);
    }

    /**
     * Sebuah method yang mengolah hasil dari pembelian dan dimasukkan kedalam database
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            int member;
            int promo;

            // Inisialisasi waktu saat ini untuk invoice
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            // Sebuah pengecekan apabila membeli as member
            if(!(request.getParameter("member") == "")){

                // Inisialisasi member
                member = Integer.parseInt(request.getParameter("member"));

                // Memngambil promo yang ada di database
                promo = invoiceService.getPromo((int)request.getSession().getAttribute("storeid")).getId();

                // Inisialisasi invoice dan memasukkan invoice pada database
                Invoice invoice = new Invoice(member, (int)request.getSession().getAttribute("userid") ,(int)request.getSession().getAttribute("storeid"), promo, dtf.format(now), Double.parseDouble(request.getParameter("totalHarga")));
                invoiceService.addInvoice(invoice);

                // Pengambilan data id dari invoice
                int id = invoiceService.getInvoice(invoice).getId();

                // Pengambilan data list apa saja yang telah dibeli
                String[] listFnb = request.getParameter("fnb").split(";");
                int i = listFnb.length - 1;

                // Pengecekan apabila pembeli membeli tiket film
                if(request.getParameter("hasFilm").equals("true")){
                    String[] entry = request.getParameter("idFilm").split(",");

                    // Memasukkan tiket film pada order detail dari invoice tersebut
                    OrderDetail orderDetail = new OrderDetail(id, (int)request.getSession().getAttribute("storeid"), entry[0], Integer.parseInt(entry[1]), Integer.parseInt(entry[2]), true);
                    invoiceService.addOrderDetail(orderDetail);
                    i--;
                }

                // Looping untuk memasukknan list pembelian fnb
                while(i >= 0){
                    String[] entry = listFnb[i].split(",");

                    // Inisialisasi fnb sekaligus memasukkan kedalam order details dari invoice ini
                    FnB fnb = fnBService.getFnB(entry[0] + "", (int)request.getSession().getAttribute("storeid"));
                    OrderDetail orderDetail = new OrderDetail(id, (int)request.getSession().getAttribute("storeid"), fnb.getName(), Integer.parseInt(entry[1]), fnb.getPrice(), true);
                    invoiceService.addOrderDetail(orderDetail);

                    i--;
                }

                // Mengirim response kembali ke ajax
                PrintWriter out = response.getWriter();
                out.print(id);

            } else {

                // Inisialisasi invoice
                Invoice invoice = new Invoice((int)request.getSession().getAttribute("userid") ,(int)request.getSession().getAttribute("storeid"), dtf.format(now), Integer.parseInt(request.getParameter("totalHarga")));
                invoiceService.addInvoice(invoice);

                // Mengambil id dari invoice tersebut
                int id = invoiceService.getInvoice(invoice).getId();

                // Pengambilan data list apa saja yang telah dibeli
                String[] listFnb = request.getParameter("fnb").split(";");
                int i = listFnb.length - 1;

                // Pengecekan apabila pembeli membeli tiket film
                if(request.getParameter("hasFilm").equals("true")){
                    String[] entry = request.getParameter("idFilm").split(",");
                    System.out.println(entry[0] + "," + entry[1] + "," + entry[2]);
                    OrderDetail orderDetail = new OrderDetail(id, (int)request.getSession().getAttribute("storeid"), entry[0], Integer.parseInt(entry[1]), Integer.parseInt(entry[2]), true);
                    invoiceService.addOrderDetail(orderDetail);

                    i--;
                }

                // Looping untuk memasukknan list pembelian fnb
                while(i >= 0){
                    String[] entry = listFnb[i].split(",");

                    FnB fnb = fnBService.getFnB(entry[0] + "", (int)request.getSession().getAttribute("storeid"));
                    System.out.println(entry[0]);
                    OrderDetail orderDetail = new OrderDetail(id, (int)request.getSession().getAttribute("storeid"), fnb.getName(), Integer.parseInt(entry[1]), fnb.getPrice(), false);
                    invoiceService.addOrderDetail(orderDetail);

                    i--;
                }

                // Mengirim response kembali ke ajax
                PrintWriter out = response.getWriter();
                out.print(id);
            }

        } catch (SQLException e){{
            PrintWriter out = response.getWriter();
            out.print(e.getMessage());
        }}
    }
}
