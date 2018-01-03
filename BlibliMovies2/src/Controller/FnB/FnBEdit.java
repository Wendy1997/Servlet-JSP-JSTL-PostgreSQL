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

    private final String storeLoginAddress = "/view/login/store_login.jsp";
    private final String accountLoginAddress = "/view/login/account_login.jsp";
    private final String editFnBAddress = "/view/database/fnb/fnb_edit.jsp";
    private final String successAddress = "/view/database/success.jsp";

    private final String storeIdSession = "storeid";
    private final String roleAccountSession = "role";
    private final String roleAdmin = "admin";

    private final String title = "Food and Beverages";
    private final String statusEditBerhasil = "Updated";
    private final String link = "/admin/fnb";

    /**
     * Sebuah method GET yang memberikan halaman form edit fnb
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


        try {
            // Pengambilan data fnb yang bersangkutan
            FnB fnb = fnbService.getFnB(request.getParameter("id"), (int)request.getSession().getAttribute(storeIdSession));
            request.setAttribute("fnb", fnb);

            // Pengambilan seluruh type dan size yang akan ditampilkan pada form
            List<FnBSize> fnBSizeList = fnbService.getAllFnBSizeTrue((int)request.getSession().getAttribute(storeIdSession));
            List<FnBType> fnBTypeList = fnbService.getAllFnBTypeTrue((int)request.getSession().getAttribute(storeIdSession));

            fnb.toString();

            request.setAttribute("size", fnBSizeList);
            request.setAttribute("type", fnBTypeList);
        } catch (SQLException e){
            e.printStackTrace();
        }

        request.getRequestDispatcher(editFnBAddress).forward(request, response);
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
            String cover = fnbService.getFnB(request.getParameter("id"), (int)request.getSession().getAttribute(storeIdSession)).getCover();
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
                uploadFilePath += UPLOAD_DIR + "\\" + (int)request.getSession().getAttribute(storeIdSession) + "\\fnb";

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
                        (int)request.getSession().getAttribute(storeIdSession),
                        "/" + (int)request.getSession().getAttribute(storeIdSession) + "/fnb/" + request.getParameter("name") + " (" + request.getParameter("size") + ") [" + randomNumber + "].jpg",
                        request.getParameter("name"),
                        Integer.parseInt(request.getParameter("type")),
                        Integer.parseInt(request.getParameter("size")),
                        Integer.parseInt(request.getParameter("price")));
            } else {

                // Inisialisasi FnB
                fnb = new FnB(
                        Integer.parseInt(request.getParameter("id")),
                        (int)request.getSession().getAttribute(storeIdSession),
                        fnbService.getFnB(request.getParameter("id"),(int)request.getSession().getAttribute(storeIdSession)).getCover(),
                        request.getParameter("name"),
                        Integer.parseInt(request.getParameter("type")),
                        Integer.parseInt(request.getParameter("size")),
                        Integer.parseInt(request.getParameter("price")));
            }

            // Sebuah method yang akan memasukkan fnb pada database
            fnbService.updateFnB(fnb);

            // Redirect menuju halaman success
            request.setAttribute("title", title);
            request.setAttribute("complete", statusEditBerhasil);
            request.setAttribute("link", link);

            request.getRequestDispatcher(successAddress).forward(request, response);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}