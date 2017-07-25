<%-- 
    Document   : invoice_medService
    Created on : Jan 26, 2016, 8:17:57 AM
    Author     : codeguy
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="org.hibernate.Session"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Medicine Invoice</title>
        <link rel="stylesheet" href="bootstrap-3.3.6-dist/css/bootstrap.min.css">
        <script src="js/jquery-2.1.4.min.js"></script>
        <script src="bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
        <style>
            .table{
                margin-top: 150px;
            }
        </style>
    </head>
    <%
        POJOs.User user = (POJOs.User) request.getSession().getAttribute("user");
        POJOs.Patient patient = user.getPatient();
        Session s = Connection.Connector.getSessionFactory().openSession();
        //POJOs.MedicalServices medService = (POJOs.MedicalServices) s.createCriteria(POJOs.MedicalServices.class).add(Restrictions.eq("idmedicalServices", Integer.parseInt(request.getParameter("id")))).uniqueResult();
        POJOs.TimeTableServices appointment = (POJOs.TimeTableServices) s.createCriteria(POJOs.TimeTableServices.class).add(Restrictions.eq("idTimeTableServices", Integer.parseInt(request.getParameter("id")))).uniqueResult();
        POJOs.MedicalServices medService = appointment.getMedicalServices();
    %>
    <body onload="window.print()">
        <div class="container">
            <%
                Date today = new Date();
            SimpleDateFormat formattedDate = new SimpleDateFormat("MM/dd/yyyy");
            String fCurrentDate = formattedDate.format(today);
            %>
            <h2>Medicine Service Invoice <small>Generated On <%=fCurrentDate%></small></h2>
            <div class="row">
                <div class="col-lg-12">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Appointment Date</th>
                                <th>Medicine Service ID</th>
                                <th>Medicine Service Name</th>
                                <th>Patient ID</th>
                                <th>Patient Name</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td><%=appointment.getDay()%></td>
                                <td><%=medService.getIdmedicalServices()%></td>
                                <td><%=medService.getServiceName()%></td>
                                <td><%=patient.getIdpatient()%></td>
                                <td><%=patient.getPatientFname() + " " + patient.getPatientLname()%></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-5">
                    <label>Doctor In Charge ID : <%=appointment.getDoctor().getIddoctor()%></label>
                </div>
            </div>
            <%
                if (appointment.getDoctor().getDoctorGender().equals("Female")) {%>
            <div class="row">
                <div class="col-lg-5">
                    <label>Doctor In Charge Name : Dr (Mrs). <%=appointment.getDoctor().getDoctorFname() + " " + appointment.getDoctor().getDoctorLname()%></label>
                </div>
            </div>

            <%} else {%>
            <div class="row">
                <div class="col-lg-5">
                    <label>Doctor In Charge Name : Dr. <%=appointment.getDoctor().getDoctorFname() + " " + appointment.getDoctor().getDoctorLname()%></label>
                </div>
            </div>
            <%}
            %>

            <div class="row">
                <div class="col-lg-12">
                    Gross Price : Rs.<%=medService.getServicePrice()%>0
                </div>
            </div>
            <div class="row">
                <div class="col-lg-5">
                    <%
                        double netprice = medService.getServicePrice() + appointment.getDoctor().getDoctorAppointmentCharge();
                    %>
                    Net Price : (+Rs.<%=appointment.getDoctor().getDoctorAppointmentCharge()%>0) Rs.<%=netprice%>0
                </div>
            </div>
            <%
                s.clear();
                s.close();
            %>
        </div>
    </body>
</html>
