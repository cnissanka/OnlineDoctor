/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Chirath Nissanka
 */
@WebServlet(name = "handleCustomerCreate", urlPatterns = {"/handleCustomerCreate"})
public class handleCustomerCreate extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            //Gather the user input
            String cUsername = request.getParameter("username");
            String cPassword = request.getParameter("password");
            String cPasswordConfirm = request.getParameter("confirmpassword");
            String cEmail = request.getParameter("email");
            String cFirstName = request.getParameter("firstname");
            String cLastName = request.getParameter("lastname");
            String cMiddleName = request.getParameter("middlename");
            String cNIC = request.getParameter("nic");
            String cDobDate = request.getParameter("dob_date");
            String cDobMonth = request.getParameter("dob_month");
            String cDobYear = request.getParameter("dob_year");
            String cSalutation = request.getParameter("salutation");
            String cGender = request.getParameter("gender");
            String cDob = request.getParameter("dob");
            String cMobile = "+94" + request.getParameter("phone");
            String cAddress = request.getParameter("address");

            Session s = Connection.Connector.getSessionFactory().openSession();

            boolean isUsernameDuplicate = s.createCriteria(POJOs.User.class).add(Restrictions.eq("username", cUsername)).list().isEmpty();

           

                cFirstName = cSalutation + cFirstName;
                boolean output = false;
                boolean flag = true;

                switch (request.getParameter("btnsub")) {
                    case "Save Patient":
                        if (!(cPassword.equals(cPasswordConfirm))){
                            response.sendRedirect("admin/admin_patient.jsp?res=validation&validation_msg=Passwords Don't match!");
                        }else if (!isUsernameDuplicate){
                            response.sendRedirect("admin/admin_patient.jsp?res=validation&validation_msg=Username is duplicate!");
                        }else{
                        
                        output = new Model.PatientHandler().insertPatient(cUsername, cPassword, cPasswordConfirm, cEmail, cFirstName, cLastName, cMiddleName, cNIC, cDob, cMobile, cGender, cAddress);
                        break;
                        
                        }
                    case "Update Patient":
                        //     Session s = Connection.Connector.getSessionFactory().openSession();
                        Transaction tf = s.beginTransaction();
                        POJOs.Patient patient = (POJOs.Patient) s.createCriteria(POJOs.Patient.class).add(Restrictions.eq("idpatient", Integer.parseInt(request.getParameter("patientid")))).uniqueResult();
                        patient.setPatientFname(cFirstName);
                        patient.setPatientMname(cMiddleName);
                        patient.setPatientLname(cLastName);
                        patient.setPatientDob(cDob);
                        patient.setPatientAddress(cAddress);
                        patient.setPatientNic(cNIC);
                        patient.setPatientGender(cMiddleName);
                        patient.setPatientPhoneno(cMobile);
                        patient.setPatientGender(cGender);
                        patient.setPatientEmail(cEmail);
                        POJOs.User user = (POJOs.User) patient.getUsers().iterator().next();
                        
                        if (!cPassword.equals(request.getParameter("confirmpassword"))) {
                            flag = false;
                        }
                        user.setPassword(cPassword);
                        s.update(patient);
                        s.update(user);
                        tf.commit();
                        output = flag;
                        break;
                    case "Delete Patient":
                        //  Session s = Connection.Connector.getSessionFactory().openSession();
                        POJOs.Patient pSelected = (POJOs.Patient) s.createCriteria(POJOs.Patient.class).add(Restrictions.eq("idpatient", Integer.parseInt(request.getParameter("patientid")))).uniqueResult();
                         user = (POJOs.User) pSelected.getUsers().iterator().next();
                        Iterator <POJOs.BlogCaptions> userComment = user.getBlogCaptionses().iterator();
                        while (userComment.hasNext()){
                            POJOs.BlogCaptions cCaption = (POJOs.BlogCaptions) userComment.next();
                            Iterator <POJOs.BlogReply> iReplies = cCaption.getBlogReplies().iterator();
                            while (iReplies.hasNext()){
                                s.delete(iReplies.next());
                            }
                            s.delete(cCaption);
                        }
                        
                        s.delete(user);
                        Iterator<POJOs.Prescription> prescriptionIterator = pSelected.getPrescriptions().iterator();
                        while (prescriptionIterator.hasNext()) {
                            s.delete(prescriptionIterator.next());
                        }
                        Iterator<POJOs.Appointment> patientAppointments = pSelected.getAppointments().iterator();
                        while (patientAppointments.hasNext()) {
                            s.delete(patientAppointments.next());
                        }
                        Iterator<POJOs.TimeTableServices> patientMedical = pSelected.getTimeTableServiceses().iterator();
                        while (patientMedical.hasNext()) {
                            s.delete(patientMedical.next());
                        }
                        Iterator<POJOs.Invoice> patientInvoices = pSelected.getInvoices().iterator();
                        while (patientInvoices.hasNext()) {
                            s.delete(patientInvoices.next());
                        }
                        
                        s.delete(pSelected);
                        s.beginTransaction().commit();
                        output = true;
                        s.close();
                        break;
                    default:
                        break;
                }

                if (output) {
                    response.sendRedirect("admin/admin_patient.jsp?res=success");
                } else {
                    response.sendRedirect("admin/admin_patient.jsp?res=fail");
                }
            
        } catch (IOException | HibernateException | NumberFormatException e) {
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
