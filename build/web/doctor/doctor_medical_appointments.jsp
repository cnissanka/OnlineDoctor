<%-- 
    Document   : doctor_medical_appointments
    Created on : Dec 21, 2015, 9:57:10 AM
    Author     : codeguy
--%>

<%@page import="java.util.Date"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.Session"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Doctor Panel</title>
        <link rel="stylesheet" href="../bootstrap-3.3.6-dist/css/bootstrap.min.css"/>
        <script src="../js/jquery-2.1.4.min.js"></script>
        <script src="../bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
        
        <script>
            function todayAppointment (){
                window.location.replace('doctor_medical_appointments.jsp?todayappointment=23');
            }
        </script>
    </head>
    <body>
        <%
            Session s = Connection.Connector.getSessionFactory().openSession();

            POJOs.User user = (POJOs.User) request.getSession().getAttribute("user");
            if (user == null) {
                response.sendRedirect("../homepage.jsp");
            } else if (!user.getUsertype().equals("DOCTOR")) {
                response.sendRedirect("../homepage.jsp");
            }

            POJOs.Doctor doctor = user.getDoctor();
        %>
        <div class="jumbotron">
            <div class="center-block">
                <center>
                    <h2>Welcome Dr. <%=user.getDoctor().getDoctorFname() + " " + user.getDoctor().getDoctorLname()%> <small>to your own workspace!</small></h2> 
                </center>
            </div>                  
            <div class="pull-right">
                <form action="../handlelogout" method="POST">
                    <button class="btn btn-danger" style="margin-right: 15px;">Sign out <span class="glyphicon glyphicon-user"></span></button>
                </form>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-2" style="margin-top: 40px;">
                <ul class="nav nav-pills nav-stacked">
                    <li><a href="doctor_home.jsp">Home and Notifications</a></li>
                    <li><a href="doctor_prescriptions.jsp">Prescription Editor</a></li>
                    <li class="active"><a href="doctor_medical_appointments.jsp">Your Medical Appointments</a></li>
                    <li><a href="doctor_patient_appointments.jsp">Your Patient Appointments</a></li>
                    <li><a href="doctor_account_settings.jsp">Account Settings and Preferences</a></li>
                </ul>
            </div>
            <div class="col-lg-10">
               
                <table class="table table-hover">
                    <caption>Medical Services List that concern's you</caption>
                    <thead>
                        <tr>
                            <th>Medical Service #</th>
                            <th>Medical Service Name</th>
                            <th>Patient Name</th>
                            <th>Patient Mobile</th>
                            <th>Patient Email Address</th>                          
                            <th>Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<POJOs.TimeTableServices> doctorMedicalService = s.createCriteria(POJOs.TimeTableServices.class).add(Restrictions.eq("doctor", doctor)).list();
                            int counter = 1;

                            if (request.getParameter("todayappointment") != null) {
                                System.out.println("here");
                                String datex = new Date().toString();

                                System.out.println(datex);

                                String dateArray[] = datex.split(" ");

                                String month = "";

                                if (dateArray[1].equals("Jan")) {
                                    month = "01";
                                }

                                if (dateArray[1].equals("Feb")) {
                                    month = "02";
                                }

                                if (dateArray[1].equals("Mar")) {
                                    month = "03";
                                }

                                if (dateArray[1].equals("Apr")) {
                                    month = "04";
                                }
                                if (dateArray[1].equals("May")) {
                                    month = "05";
                                }
                                if (dateArray[1].equals("Jun")) {
                                    month = "06";
                                }
                                if (dateArray[1].equals("Jul")) {
                                    month = "07";
                                }
                                if (dateArray[1].equals("Aug")) {
                                    month = "08";
                                }
                                if (dateArray[1].equals("Sep")) {
                                    month = "09";
                                }
                                if (dateArray[1].equals("Oct")) {
                                    month = "10";
                                }
                                if (dateArray[1].equals("Nov")) {
                                    month = "11";
                                }
                                if (dateArray[1].equals("Dec")) {
                                    month = "12";
                                }

                                //System.out.println(month);
                                String day = dateArray[2];

                                String year = dateArray[5];

                                String fullDate = month + "/" + day + "/" + year;
                                Session s2 = Connection.Connector.getSessionFactory().openSession();
                               doctorMedicalService = s2.createCriteria(POJOs.TimeTableServices.class).add(Restrictions.eq("doctor", doctor)).add(Restrictions.eq("day", fullDate)).list();

                            }

                            for (POJOs.TimeTableServices record : doctorMedicalService) {%>
                        <tr>
                            <td><%=counter%></td>
                            <td><%=record.getMedicalServices().getServiceName()%></td>
                            <td><%=record.getPatient().getPatientFname() + " " + record.getPatient().getPatientLname()%></td>
                            <td><%=record.getPatient().getPatientPhoneno()%></td>
                            <td><%=record.getPatient().getPatientEmail()%></td>                             
                            <td><%=record.getDay()%></td>
                        </tr>                        
                        <%}
                        %>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
