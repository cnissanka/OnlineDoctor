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
@WebServlet(name = "handleAddDoctor", urlPatterns = {"/handleAddDoctor"})
public class handleAddDoctor extends HttpServlet {

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

            //         boolean result =  new Model.DoctorHandler().createDoctor(request.getParameter("username"), request.getParameter("password"), request.getParameter("fname1"),request.getParameter("mname1"),request.getParameter("lname1"),request.getParameter("mobile1"),request.getParameter("land1"),request.getParameter("address1"),request.getParameter("date1"),request.getParameter("month1"),request.getParameter("year1"),request.getParameter("nic1"),request.getParameter("email1"),request.getParameter("qualificationList"), request.getParameter("category"));
            //         out.write(result+"");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String cpassword = request.getParameter("confirmpassword");
            String fname = request.getParameter("fname1");
            String mname = request.getParameter("mname1");
            String lname = request.getParameter("lname1");
            String dob = request.getParameter("dob");
            String gender = request.getParameter("gender");
            String mobile = "+94" + request.getParameter("mobile1");
            String land = "+94" + request.getParameter("land1");
            String avgPay = request.getParameter("avgPay") + ".00";
            String appointmentCharge = request.getParameter("appCharge") + ".00";
            String address = request.getParameter("address1");
            String qualifications = request.getParameter("qualification");
            String field = request.getParameter("field");
            String nic = request.getParameter("nic");
            String email = request.getParameter("email");

            Session s = Connection.Connector.getSessionFactory().openSession();
            Transaction tf = s.beginTransaction();

            switch (request.getParameter("btn")) {
                case "Save Doctor":

                    POJOs.Doctor doctor = new POJOs.Doctor();
                    boolean isUsernameDuplicate = s.createCriteria(POJOs.User.class).add(Restrictions.eq("username", username)).list().isEmpty();
                    System.out.println(isUsernameDuplicate);
                      
                    System.out.println(password + " ? " + cpassword);
                    
                    if (!password.equals(cpassword)) {
                        response.sendRedirect("admin/admin_doctor.jsp?res=validation&validation_msg=Passwords don't Match!");
                    } else if (!isUsernameDuplicate) {
                        System.out.println("hereee");
                        response.sendRedirect("admin/admin_doctor.jsp?res=validation&validation_msg=Username is already there!!");
                    } else {
                        doctor.setDoctorFname(fname);
                        doctor.setDoctorMname(mname);
                        doctor.setDoctorLname(lname);
                        doctor.setDoctorDob(dob);
                        doctor.setDoctorEmail(email);
                        doctor.setDoctorMobile(mobile);
                        doctor.setDoctorLand(land);
                        doctor.setDoctorAddress(address);
                        doctor.setDoctorNic(nic);
                        doctor.setDoctorAppointmentCharge(Double.parseDouble(appointmentCharge));
                        doctor.setDoctorAveragePay(Double.parseDouble(avgPay));
                        doctor.setDoctorGender(gender);
                        s.save(doctor);

                        POJOs.DoctorField doctorField = (POJOs.DoctorField) s.createCriteria(POJOs.DoctorField.class).add(Restrictions.eq("fieldName", field)).uniqueResult();
                        POJOs.DoctorFieldHasDoctor doctorFields = new POJOs.DoctorFieldHasDoctor();
                        doctorFields.setDoctor(doctor);
                        doctorFields.setDoctorField(doctorField);
                        s.save(doctorFields);

                        String qualificationsList[] = qualifications.split("#");
                        System.out.println(qualifications);
                        for (String qualificationsList1 : qualificationsList) {
                            if (qualificationsList1.equals("")) {
                                break;
                            }
                            POJOs.DoctorHasQualifications qDoc = new POJOs.DoctorHasQualifications();
                            POJOs.DoctorQualifications qual = (POJOs.DoctorQualifications) s.createCriteria(POJOs.DoctorQualifications.class).add(Restrictions.eq("iddoctorQualifications", Integer.parseInt(qualificationsList1))).uniqueResult();
                            qDoc.setDoctor(doctor);
                            qDoc.setDoctorQualifications(qual);
                            s.save(qDoc);
                        }

                        POJOs.User dUser = new POJOs.User();
                        dUser.setDoctor(doctor);
                        dUser.setAdministrator((POJOs.Administrator) s.createCriteria(POJOs.Administrator.class).add(Restrictions.eq("idadministrator", -1)).uniqueResult());
                        dUser.setPatient((POJOs.Patient) s.createCriteria(POJOs.Patient.class).add(Restrictions.eq("idpatient", -1)).uniqueResult());
                        dUser.setUsertype("DOCTOR");
                        dUser.setPassword(password);
                        dUser.setUsername(username);
                        s.save(dUser);

                        tf.commit();
                        s.close();
                        tf = null;
                        response.sendRedirect("admin/admin_doctor.jsp?res=success");
                    }

                    break;
                case "Update Doctor":
                    if (!password.equals(cpassword)) {
                        response.sendRedirect("admin/admin_doctor.jsp?res=validation&validation_msg=Passwords don't Match!");
                    } else {
                        System.out.println(request.getParameter("id"));
                       POJOs.Doctor doctor1 = (POJOs.Doctor) s.createCriteria(POJOs.Doctor.class).add(Restrictions.eq("iddoctor", Integer.parseInt(request.getParameter("id")))).uniqueResult();
                        System.out.println("here");
                       doctor1.setDoctorFname(fname);
                        doctor1.setDoctorMname(mname);
                        doctor1.setDoctorLname(lname);
                        doctor1.setDoctorDob(dob);
                        doctor1.setDoctorEmail(email);
                        doctor1.setDoctorMobile(mobile);
                        doctor1.setDoctorLand(land);
                        doctor1.setDoctorAddress(address);
                        doctor1.setDoctorNic(nic);
                        System.out.println(appointmentCharge);
                        appointmentCharge = appointmentCharge.replace('.', '#');
                        avgPay = avgPay.replace('.', '#');
                        appointmentCharge = appointmentCharge.split("#")[0] + ".00";
                        avgPay = avgPay.split("#")[0] + ".00";
                        doctor1.setDoctorAppointmentCharge(Double.parseDouble(appointmentCharge));
                        System.out.println("here");
                        doctor1.setDoctorAveragePay(Double.parseDouble(avgPay));
                        doctor1.setDoctorGender(gender);
                        s.update(doctor1);
                        tf.commit();
                        response.sendRedirect("admin/admin_doctor.jsp?res=success");
                    }
                    break;
                case "Delete Doctor":
                    POJOs.Doctor doctor2 = (POJOs.Doctor) s.createCriteria(POJOs.Doctor.class).add(Restrictions.eq("iddoctor", Integer.parseInt(request.getParameter("id")))).uniqueResult();
                    POJOs.User user1 = (POJOs.User) doctor2.getUsers().iterator().next();
                    s.delete(user1);
                    
                    Iterator <POJOs.DoctorFieldHasDoctor> doctorFields = doctor2.getDoctorFieldHasDoctors().iterator();
                    while (doctorFields.hasNext()){
                        s.delete(doctorFields.next());
                    }
                    
                    Iterator <POJOs.DoctorHasQualifications> doctorQualifications = doctor2.getDoctorHasQualificationses().iterator();
                    while (doctorQualifications.hasNext()){
                        s.delete(doctorQualifications.next());
                    }
                    
                    Iterator <POJOs.TimeTableServices> doctorMedicals = doctor2.getTimeTableServiceses().iterator();
                    while (doctorMedicals.hasNext()){
                        s.delete(doctorMedicals.next());
                    }
                    
                    Iterator <POJOs.Prescription> doctorPrescriptions = doctor2.getPrescriptions().iterator();
                    while (doctorPrescriptions.hasNext()){
                        s.delete(doctorPrescriptions.next());
                    }
                    
                    Iterator <POJOs.Appointment> doctorAppointments = doctor2.getAppointments().iterator();
                    while (doctorAppointments.hasNext()){
                        s.delete(doctorAppointments.next());
                    }
                    
                    s.delete(doctor2);
                    tf.commit();
                    s.close();
                    tf = null;
                    response.sendRedirect("admin/admin_doctor.jsp?res=success");
                    break;
                default:

                    break;
            }

        } catch (IOException | HibernateException | NumberFormatException e) {
            response.sendRedirect("admin/admin_doctor.jsp?res=fail");
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
