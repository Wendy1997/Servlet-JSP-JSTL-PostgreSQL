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
@MultipartConfig(
        fileSizeThreshold=1024*1024*10, 	// 10 MB
        maxFileSize=1024*1024*50,      	    // 50 MB
        maxRequestSize=1024*1024*100)   	// 100 MB
public class FnBAdd extends HttpServlet{
    FnBService fnbDAO = new FnBServiceDatabase();
    private static final String UPLOAD_DIR = "web\\uploads";
    private final String storeLoginAddress = "/view/login/store_login.jsp";
    private final String accountLoginAddress = "/view/login/account_login.jsp";
    private final String addFnBAddress = "/view/database/fnb/fnb_add.jsp";
    private final String successAddress = "/view/database/success.jsp";
    private final String storeIdSession = "storeid";
    private final String roleAccountSession = "role";
    private final String roleAdmin = "admin";
    private final String title = "Food and Beverages";
    private final String statusAddBerhasil = "Created";
    private final String link = "/admin/fnb";

    /**
     * Sebuah method GET yang memberikan form penambahan fnb
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        // Validasi apakah sudah login store
        if(request.getSession().getAttribute(storeIdSession) == null){
            request.getRequestDispatcher(storeLoginAddress).forward(request, response);
        }
        // Validasi apakah sudah login akun
        else if (request.getSession().getAttribute(roleAccountSession) == null){
            request.getRequestDispatcher(accountLoginAddress).forward(request, response);
        }
        // Validasi apakah sudah login as admin
        else if(!request.getSession().getAttribute(roleAccountSession).equals(roleAdmin)){
            request.getRequestDispatcher(accountLoginAddress).forward(request, response);
        }


        try{

            // Pengambilan data seluruh type dan size fnb yang akan dimasukkan ke dalam form
            List<FnBSize> fnBSizeList = fnbDAO.getAllFnBSizeTrue((int)request.getSession().getAttribute(storeIdSession));
            List<FnBType> fnBTypeList = fnbDAO.getAllFnBTypeTrue((int)request.getSession().getAttribute(storeIdSession));

            request.setAttribute("size", fnBSizeList);
            request.setAttribute("type", fnBTypeList);
        }catch (SQLException e){
            e.printStackTrace();
        }

        request.getRequestDispatcher(addFnBAddress).forward(request, response);
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
        uploadFilePath += UPLOAD_DIR + "\\" + (int)request.getSession().getAttribute(storeIdSession) + "\\fnb";

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
            FnB fnb = new FnB((int)request.getSession().getAttribute(storeIdSession),
                    "/" + (int)request.getSession().getAttribute(storeIdSession) + "/fnb/" + request.getParameter("name") + " (" + request.getParameter("size") + ") [" + dateNow + "].jpg",
                    request.getParameter("name"),
                    Integer.parseInt(request.getParameter("type")),
                    Integer.parseInt(request.getParameter("size")),
                    Integer.parseInt(request.getParameter("price")));

            // Sebuah method yang akan memasukkan fnb pada database
            fnbDAO.addFnB(fnb);

            // Redirect menuju halaman success
            request.setAttribute("title", title);
            request.setAttribute("complete", statusAddBerhasil);
            request.setAttribute("link", link);

            request.getRequestDispatcher(successAddress).forward(request,response);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
