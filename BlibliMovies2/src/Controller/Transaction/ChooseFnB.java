package Controller.Transaction;

import DAO.OrderDetailDAO;
import Model.FilmTicket;
import Model.FnB;
import Model.Invoice;
import Model.OrderDetail;
import Service.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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

@WebServlet("/cashier/fnb")
public class ChooseFnB extends HttpServlet{
    FnBService fnBService = new FnBServiceDatabase();
    InvoiceService invoiceService = new InvoiceServiceDatabase();
    FilmService filmService = new FilmServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = "/view/transaction/choose_fnb.jsp";

        // Validasi apakah sudah login store
        if(request.getSession().getAttribute("storename") == null){
            address = "/view/login/store_login.jsp";
            request.getRequestDispatcher(address).forward(request, response);
        }

        // Validasi apakah sudah login akun
        else if (request.getSession().getAttribute("role") == null){
            address = "/view/login/account_login.jsp";
            request.getRequestDispatcher(address).forward(request, response);
        }

        try{
            if(!(request.getParameter("ticketQuantity") == null)){
                request.setAttribute("ticketQuantity", request.getParameter("ticketQuantity"));
                request.setAttribute("film", filmService.getFilm(request.getParameter("film"), (String)request.getSession().getAttribute("storename")));
                request.setAttribute("studio", filmService.getStudio(request.getParameter("studioid"), (String)request.getSession().getAttribute("storename")));
                request.setAttribute("screening", filmService.getScreeningTime(request.getParameter("screeningid"), request.getParameter("film"), (String)request.getSession().getAttribute("storename")));
            }

            List<FnB> fnBList = fnBService.getAllFnB((String)request.getSession().getAttribute("storename"));

            for(int i = 0; i < fnBList.size(); i++){
                fnBList.get(i).setCover("/uploads" + fnBList.get(i).getCover());
            }

            request.setAttribute("fnblist", fnBList);
        } catch (SQLException e){
            e.printStackTrace();
        }

        request.getRequestDispatcher(address).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            int member;
            int promo;
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            System.out.println(request.getParameter("member"));

            if(!(request.getParameter("member") == "")){

                member = Integer.parseInt(request.getParameter("member"));
                promo = invoiceService.getPromo((String)request.getSession().getAttribute("storename")).getId();

                Invoice invoice = new Invoice(member, (String)request.getSession().getAttribute("username") ,(String)request.getSession().getAttribute("storename"), promo, dtf.format(now), Double.parseDouble(request.getParameter("totalHarga")));

                invoiceService.addInvoice(invoice);

                int id = invoiceService.getInvoice(invoice).getId();

                String[] listFnb = request.getParameter("fnb").split(";");

                int i = listFnb.length - 1;

                if(request.getParameter("hasFilm").equals("true")){
                    String[] entry = request.getParameter("idFilm").split(",");

                    OrderDetail orderDetail = new OrderDetail(id, (String)request.getSession().getAttribute("storename"), entry[0], Integer.parseInt(entry[1]), Integer.parseInt(entry[2]), true);
                    invoiceService.addOrderDetail(orderDetail);

                    i--;
                }

                while(i >= 0){
                    String[] entry = listFnb[i].split(",");

                    FnB fnb = fnBService.getFnB(entry[0] + "", (String)request.getSession().getAttribute("storename"));

                    OrderDetail orderDetail = new OrderDetail(id, (String)request.getSession().getAttribute("storename"), fnb.getName(), Integer.parseInt(entry[1]), fnb.getPrice(), true);
                    invoiceService.addOrderDetail(orderDetail);

                    i--;
                }

                PrintWriter out = response.getWriter();
                out.print(id);

            } else {

                Invoice invoice = new Invoice((String)request.getSession().getAttribute("username") ,(String)request.getSession().getAttribute("storename"), dtf.format(now), Integer.parseInt(request.getParameter("totalHarga")));
                invoiceService.addInvoice(invoice);

                int id = invoiceService.getInvoice(invoice).getId();

                String[] listFnb = request.getParameter("fnb").split(";");

                int i = listFnb.length - 1;

                if(request.getParameter("hasFilm").equals("true")){
                    String[] entry = request.getParameter("idFilm").split(",");
                    System.out.println(entry[0] + "," + entry[1] + "," + entry[2]);
                    OrderDetail orderDetail = new OrderDetail(id, (String)request.getSession().getAttribute("storename"), entry[0], Integer.parseInt(entry[1]), Integer.parseInt(entry[2]), true);
                    invoiceService.addOrderDetail(orderDetail);

                    i--;
                }

                while(i >= 0){
                    String[] entry = listFnb[i].split(",");

                    FnB fnb = fnBService.getFnB(entry[0] + "", (String)request.getSession().getAttribute("storename"));
                    System.out.println(entry[0]);
                    OrderDetail orderDetail = new OrderDetail(id, (String)request.getSession().getAttribute("storename"), fnb.getName(), Integer.parseInt(entry[1]), fnb.getPrice(), false);
                    invoiceService.addOrderDetail(orderDetail);

                    i--;
                }

                PrintWriter out = response.getWriter();
                out.print(id);
            }

        } catch (SQLException e){{
            PrintWriter out = response.getWriter();
            out.print(e.getMessage());
        }}
    }
}
