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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/admin/fnb/edit")@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB
        maxFileSize=1024*1024*50,      	// 50 MB
        maxRequestSize=1024*1024*100)   	// 100 MB
public class FnBEdit extends HttpServlet{
    FnBService fnbService = new FnBServiceDatabase();
    private static final String UPLOAD_DIR = "web\\uploads";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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
            FnB fnb = fnbService.getFnB(request.getParameter("id"), (int)request.getSession().getAttribute("storeid"));
            request.setAttribute("fnb", fnb);

            List<FnBSize> fnBSizeList = fnbService.getAllFnBSize((int)request.getSession().getAttribute("storeid"));
            List<FnBType> fnBTypeList = fnbService.getAllFnBType((int)request.getSession().getAttribute("storeid"));

            request.setAttribute("size", fnBSizeList);
            request.setAttribute("type", fnBTypeList);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        request.getRequestDispatcher(address).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            LocalDateTime now = LocalDateTime.now();
            String random = dtf.format(now);

            String cover = fnbService.getFnB(request.getParameter("id"), (int)request.getSession().getAttribute("storeid")).getCover();
            String[] randomList = cover.split("/");
            String randomNumber = randomList[3].substring(randomList[3].length()-19, randomList[3].length()-5);

            if(request.getPart("file").getSubmittedFileName().length() > 0){
                String[] pathList = getServletContext().getRealPath("").split("\\\\");
                String uploadFilePath = "";
                for(int i = 0; i < pathList.length - 3; i++){
                    uploadFilePath += pathList[i] + "\\";
                }
                uploadFilePath += UPLOAD_DIR + "\\" + (int)request.getSession().getAttribute("storeid") + "\\film";

                File fileLama = new File(uploadFilePath + "\\" + randomList[3]);
                fileLama.delete();

                // creates the save directory if it does not exists
                File fileSaveDir = new File(uploadFilePath);
                if (!fileSaveDir.exists()) {
                    fileSaveDir.mkdirs();
                }

                String fileName = null;

                Part part = request.getPart("file");
                fileName = getFileName(part);
                part.write(uploadFilePath + "\\" + request.getParameter("nama") + " (" + request.getParameter("waktu_mulai").substring(0,4) + ") [" + random + "].jpg");
                randomNumber = random;
            }

            FnB fnb = new FnB(
                Integer.parseInt(request.getParameter("id")),
                (int)request.getSession().getAttribute("storeid"),
                "/" + (int)request.getSession().getAttribute("storeid") + "/film/" + request.getParameter("name") + " (" + request.getParameter("size") + ") [" + randomNumber + "].jpg",
                request.getParameter("name"),
                Integer.parseInt(request.getParameter("type")),
                Integer.parseInt(request.getParameter("size")),
                Integer.parseInt(request.getParameter("price")));

            fnbService.updateFnB(fnb);

            String address = "/view/database/success.jsp";
            request.setAttribute("title", "Food and Beverages");
            request.setAttribute("complete", "Updated");
            request.setAttribute("link", "/admin/fnb");

            request.getRequestDispatcher(address).forward(request, response);

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return "";
    }
}