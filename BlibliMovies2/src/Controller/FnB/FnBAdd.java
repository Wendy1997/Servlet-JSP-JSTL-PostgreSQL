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

@WebServlet("/admin/fnb/add")
@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB
        maxFileSize=1024*1024*50,      	// 50 MB
        maxRequestSize=1024*1024*100)   	// 100 MB
public class FnBAdd extends HttpServlet{
    FnBService fnbDAO = new FnBServiceDatabase();
    private static final String UPLOAD_DIR = "web\\uploads";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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
            List<FnBSize> fnBSizeList = fnbDAO.getAllFnBSize((int)request.getSession().getAttribute("storeid"));
            List<FnBType> fnBTypeList = fnbDAO.getAllFnBType((int)request.getSession().getAttribute("storeid"));

            request.setAttribute("size", fnBSizeList);
            request.setAttribute("type", fnBTypeList);
        }catch (SQLException e){
            e.printStackTrace();
        }

        request.getRequestDispatcher(address).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        String dateNow = dtf.format(now);

        String[] pathList = getServletContext().getRealPath("").split("\\\\");
        String uploadFilePath = "";
        for(int i = 0; i < pathList.length - 3; i++){
            uploadFilePath += pathList[i] + "\\";
        }
        uploadFilePath += UPLOAD_DIR + "\\" + (int)request.getSession().getAttribute("storeid") + "\\fnb";

        // creates the save directory if it does not exists
        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }

        String fileName = null;

        Part part = request.getPart("file");
        fileName = getFileName(part);
        part.write(uploadFilePath + "\\" + request.getParameter("name") + " (" + request.getParameter("size") + ") [" + dateNow + "].jpg");

        try{
            FnB fnb = new FnB((int)request.getSession().getAttribute("storeid"),
                    "/" + (int)request.getSession().getAttribute("storeid") + "/fnb/" + request.getParameter("name") + " (" + request.getParameter("size") + ") [" + dateNow + "].jpg",
                    request.getParameter("name"),
                    Integer.parseInt(request.getParameter("type")),
                    Integer.parseInt(request.getParameter("size")),
                    Integer.parseInt(request.getParameter("price")));

            fnbDAO.addFnB(fnb);

            String address = "/view/database/success.jsp";
            request.setAttribute("title", "Food and Beverages");
            request.setAttribute("complete", "Created");
            request.setAttribute("link", "/admin/fnb");
            request.getRequestDispatcher(address).forward(request,response);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= "+contentDisp);
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return "";
    }
}
