package Controller.FnB;

import DAO.FnBDAO;
import Model.FilmGenre;
import Model.FnB;
import Model.FnBSize;
import Model.FnBType;
import Service.FnBService;
import Service.FnBServiceDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.zip.Adler32;

/**
 * Sebuah kelas yang menghandle penambahan fnb
 * url: /admin/fnb/add
 */
@WebServlet("/admin/fnb/add")
@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB
        maxFileSize=1024*1024*50,      	// 50 MB
        maxRequestSize=1024*1024*100)   	// 100 MB
public class FnBAdd extends HttpServlet{
    FnBService fnbDAO = new FnBServiceDatabase();
    private static final String UPLOAD_DIR = "web\\uploads";

    /**
     * Sebuah method GET yang memberikan form penambahan fnb
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        // Initial Address
        String address = "/view/database/fnb/fnb_add.jsp";

        // Validasi apakah sudah login store
        if(request.getSession().getAttribute("storeid") == null){
            address = "/view/login/store_login.jsp";
        }

        // Validasi apakah sudah login akun
        else if (request.getSession().getAttribute("role") == null){
            address = "/view/login/account_login.jsp";
        }

        // Validasi apakah sudah login as admin
        else if(!request.getSession().getAttribute("role").equals("admin")){
            address = "/view/login/account_login.jsp";
        }

        try{

            // Pengambilan data seluruh type dan size fnb yang akan dimasukkan ke dalam form
            List<FnBSize> fnBSizeList = fnbDAO.getAllFnBSizeTrue((int)request.getSession().getAttribute("storeid"));
            List<FnBType> fnBTypeList = fnbDAO.getAllFnBTypeTrue((int)request.getSession().getAttribute("storeid"));

            request.setAttribute("size", fnBSizeList);
            request.setAttribute("type", fnBTypeList);
        }catch (SQLException e){
            e.printStackTrace();
        }

        request.getRequestDispatcher(address).forward(request, response);
    }

    /**
     * Sebuah method POST yang akan mengolah hasil input form dari halaman tambah fnb
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        // Pengambilan data waktu saat ini
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        String dateNow = dtf.format(now);

        // Pengambilan data lokasi source project
        String[] pathList = getServletContext().getRealPath("").split("\\\\");
        String uploadFilePath = "";
        for(int i = 0; i < pathList.length - 3; i++){
            uploadFilePath += pathList[i] + "\\";
        }

        // Memberikan direktori upload file
        uploadFilePath += UPLOAD_DIR + "\\" + (int)request.getSession().getAttribute("storeid") + "\\fnb";

        // Pengecekan apakah telah ada direktorinya. Jika belum maka akan dibuat
        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }

        // Save data ke direktori tersebut
        Part part = request.getPart("file");
        part.write(uploadFilePath + "\\" + request.getParameter("name") + " (" + request.getParameter("size") + ") [" + dateNow + "].jpg");

        try{
            // Inisialisasi FnB
            FnB fnb = new FnB((int)request.getSession().getAttribute("storeid"),
                    "/" + (int)request.getSession().getAttribute("storeid") + "/fnb/" + request.getParameter("name") + " (" + request.getParameter("size") + ") [" + dateNow + "].jpg",
                    request.getParameter("name"),
                    Integer.parseInt(request.getParameter("type")),
                    Integer.parseInt(request.getParameter("size")),
                    Integer.parseInt(request.getParameter("price")));

            // Sebuah method yang akan memasukkan fnb pada database
            fnbDAO.addFnB(fnb);

            // Redirect menuju halaman success
            String address = "/view/database/success.jsp";
            request.setAttribute("title", "Food and Beverages");
            request.setAttribute("complete", "Created");
            request.setAttribute("link", "/admin/fnb");

            request.getRequestDispatcher(address).forward(request,response);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
