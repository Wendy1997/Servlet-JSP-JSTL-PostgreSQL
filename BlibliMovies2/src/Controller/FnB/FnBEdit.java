package Controller.FnB;

import DAO.FnBDAO;
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

/**
 * Sebuah kelas yang menghandle pengeditan fnb
 * url: /admin/fnb/edit
 */
@WebServlet("/admin/fnb/edit")@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB
        maxFileSize=1024*1024*50,      	// 50 MB
        maxRequestSize=1024*1024*100)   	// 100 MB
public class FnBEdit extends HttpServlet{
    FnBService fnbService = new FnBServiceDatabase();
    private static final String UPLOAD_DIR = "web\\uploads";

    /**
     * Sebuah method GET yang memberikan halaman form edit fnb
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        // Initial Address
        String address = "/view/database/fnb/fnb_edit.jsp";

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

        // Validasi apakah sudah login as admin
        else if(!request.getSession().getAttribute("role").equals("admin")){
            address = "/view/login/account_login.jsp";
            request.getRequestDispatcher(address).forward(request, response);
        }

        try {
            // Pengambilan data fnb yang bersangkutan
            FnB fnb = fnbService.getFnB(request.getParameter("id"), (int)request.getSession().getAttribute("storeid"));
            request.setAttribute("fnb", fnb);

            // Pengambilan seluruh type dan size yang akan ditampilkan pada form
            List<FnBSize> fnBSizeList = fnbService.getAllFnBSizeTrue((int)request.getSession().getAttribute("storeid"));
            List<FnBType> fnBTypeList = fnbService.getAllFnBTypeTrue((int)request.getSession().getAttribute("storeid"));

            request.setAttribute("size", fnBSizeList);
            request.setAttribute("type", fnBTypeList);
        } catch (SQLException e){
            e.printStackTrace();
        }

        request.getRequestDispatcher(address).forward(request, response);
    }

    /**
     * Sebuah method POST yang akan mengolah hasil input form dari halaman edit fnb
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{

            // Pengambilan data waktu saat ini
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            LocalDateTime now = LocalDateTime.now();
            String random = dtf.format(now);

            // Pengambilan data cover sebelumnya
            String cover = fnbService.getFnB(request.getParameter("id"), (int)request.getSession().getAttribute("storeid")).getCover();
            String[] randomList = cover.split("/");
            String randomNumber = randomList[3].substring(randomList[3].length()-19, randomList[3].length()-5);

            FnB fnb = new FnB();

            // Pengecekan apakah cover diupdate atau tidak
            if(request.getPart("file").getSubmittedFileName().length() > 0){

                // Pengambilan data lokasi source project
                String[] pathList = getServletContext().getRealPath("").split("\\\\");
                String uploadFilePath = "";
                for(int i = 0; i < pathList.length - 3; i++){
                    uploadFilePath += pathList[i] + "\\";
                }

                // Memberikan direktori upload file
                uploadFilePath += UPLOAD_DIR + "\\" + (int)request.getSession().getAttribute("storeid") + "\\fnb";

                // Menghapus file lama
                File fileLama = new File(uploadFilePath + "\\" + randomList[3]);
                fileLama.delete();

                // Pengecekan apakah telah ada direktorinya. Jika belum maka akan dibuat
                File fileSaveDir = new File(uploadFilePath);
                if (!fileSaveDir.exists()) {
                    fileSaveDir.mkdirs();
                }

                // Save data ke direktori tersebut
                Part part = request.getPart("file");
                part.write(uploadFilePath + "\\" + request.getParameter("name") + " (" + request.getParameter("size") + ") [" + random + "].jpg");
                randomNumber = random;

                // Inisialisasi FnB
                fnb = new FnB(
                        Integer.parseInt(request.getParameter("id")),
                        (int)request.getSession().getAttribute("storeid"),
                        "/" + (int)request.getSession().getAttribute("storeid") + "/fnb/" + request.getParameter("name") + " (" + request.getParameter("size") + ") [" + randomNumber + "].jpg",
                        request.getParameter("name"),
                        Integer.parseInt(request.getParameter("type")),
                        Integer.parseInt(request.getParameter("size")),
                        Integer.parseInt(request.getParameter("price")));
            } else {

                // Inisialisasi FnB
                fnb = new FnB(
                        Integer.parseInt(request.getParameter("id")),
                        (int)request.getSession().getAttribute("storeid"),
                        fnbService.getFnB(request.getParameter("id"),(int)request.getSession().getAttribute("storeid")).getCover(),
                        request.getParameter("name"),
                        Integer.parseInt(request.getParameter("type")),
                        Integer.parseInt(request.getParameter("size")),
                        Integer.parseInt(request.getParameter("price")));
            }

            // Sebuah method yang akan memasukkan fnb pada database
            fnbService.updateFnB(fnb);

            // Redirect menuju halaman success
            String address = "/view/database/success.jsp";
            request.setAttribute("title", "Food and Beverages");
            request.setAttribute("complete", "Updated");
            request.setAttribute("link", "/admin/fnb");

            request.getRequestDispatcher(address).forward(request, response);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}