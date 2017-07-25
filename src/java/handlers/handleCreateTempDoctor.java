/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author codeguy
 */
@WebServlet(name = "handleCreateTempDoctor", urlPatterns = {"/handleCreateTempDoctor"})
public class handleCreateTempDoctor extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            //     Session s = Connection.Connector.getSessionFactory().openSession();
            //    PrintWriter out = response.getWriter();
            Session session = Connection.Connector.getSessionFactory().openSession();
            Transaction tf = session.beginTransaction();
            System.out.println(request.getParameter("btn"));

            String path = "uploads/cv/";
            FileItemFactory fip = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(fip);

            String fname = "";
            String mname = "";
            String lname = "";
            String username = "";
            String password = "";
            String ppassword = "";
            int packageid = 0;
            String dob = "";
            String gender = "";
            String mobile = "";
            String land = "";
            String address = "";
            String nic = "";
            String email = "";

            FileItem cvItem = null;
            List<FileItem> items = upload.parseRequest(request);
            for (FileItem item : items) {
                if (item.isFormField()) {
                   
                    if (item.getFieldName().equals("username")) {
                        username = item.getString();
                    }
                    if (item.getFieldName().equals("password")) {
                        password = item.getString();
                    }
                    if (item.getFieldName().equals("confirmpassword")) {
                        ppassword = item.getString();
                    }
                    if (item.getFieldName().equals("fname1")) {
                        fname = item.getString();
                    }
                    if (item.getFieldName().equals("mname1")) {
                        mname = item.getString();
                    }
                    if (item.getFieldName().equals("lname1")) {
                        lname = item.getString();
                    }
                    if (item.getFieldName().equals("dob")) {
                        dob = item.getString();
                    }
                    if (item.getFieldName().equals("gender")) {
                        gender = item.getString();
                    }
                    if (item.getFieldName().equals("mobile1")) {
                        mobile = item.getString();
                    }
                    if (item.getFieldName().equals("land1")) {
                        land = item.getString();
                    }
                    if (item.getFieldName().equals("address1")) {
                        address = item.getString();
                    }
                    if (item.getFieldName().equals("package")){
                        packageid = Integer.parseInt(item.getString());
                    }
                    if (item.getFieldName().equals("nic")){
                        nic = item.getString();
                    }
                    if (item.getFieldName().equals("email")){
                        email = item.getString();
                    }
                    
                } else {
                    //System.out.println("Content Type : " + item.getContentType());
                    //System.out.println("Real Path : " + request.getServletContext().getRealPath("/uploads/medicine/"));

                    path += System.currentTimeMillis() + item.getName();
                    cvItem = item;

                }
            }

            if (!password.equals(ppassword)) {
                response.sendRedirect("doctor_careers.jsp?res=validation&validation_msg=Passwords don't match!");
            } else {
                File saved_file = new File(request.getServletContext().getRealPath("") + "/" + path);
                cvItem.write(saved_file);
                
                POJOs.DoctorTemp tempDoctor = new POJOs.DoctorTemp();
                POJOs.DoctorClasses cPackage = (POJOs.DoctorClasses) session.createCriteria(POJOs.DoctorClasses.class).add(Restrictions.eq("iddoctorClasses", packageid)).uniqueResult();
                
                tempDoctor.setDoctorCv(path);
                tempDoctor.setDoctorMobile(mobile);
                tempDoctor.setDoctorLand(land);
                tempDoctor.setDoctorNic(nic);
                tempDoctor.setDoctorFname(fname);
                tempDoctor.setDoctorMname(mname);
                tempDoctor.setDoctorLname(lname);
                tempDoctor.setDoctorEmail(email);
                tempDoctor.setDoctorDob(dob);
                tempDoctor.setDoctorAddress(address);
                tempDoctor.setDoctorClasses(cPackage);
                
                if (cPackage.getClassName().equals("Silver")){
                    tempDoctor.setDoctorAveragePay(60000.0);
                    tempDoctor.setDoctorAppointmentCharge(1500.0);
                }else if (cPackage.getClassName().equals("Gold")){
                    
                    tempDoctor.setDoctorAveragePay(160000.0);
                    tempDoctor.setDoctorAppointmentCharge(2500.0);
                }else if (cPackage.getClassName().equals("Platinum")){
                    
                    tempDoctor.setDoctorAveragePay(260000.0);
                    tempDoctor.setDoctorAppointmentCharge(3500.0);
                }
                
                session.save(tempDoctor);
                tf.commit();
                
                
                response.sendRedirect("doctor_careers.jsp?res=success");
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
