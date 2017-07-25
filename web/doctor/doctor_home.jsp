<%-- 
    Document   : doctor_home
    Created on : Dec 21, 2015, 9:57:10 AM
    Author     : codeguy
--%>

<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="org.hibernate.Session"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Doctor Panel</title>
        <link rel="stylesheet" href="../bootstrap-3.3.6-dist/css/bootstrap.min.css"/>
        <script src="../js/jquery-2.1.4.min.js"></script>
        <script src="../bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
    </head>
    <body>
        <%
            POJOs.User user = null;
            System.out.println("request.get session is null or not");
           
            if (request.getSession().getAttribute("user") == null) {
                response.sendRedirect("../homepage.jsp");
            } else {
                user = (POJOs.User) request.getSession().getAttribute("user");
                if (user.getUsertype().equals("DOCTOR")) {
                    user = (POJOs.User) request.getSession().getAttribute("user");
                } else {
                    response.sendRedirect("../homepage.jsp");
                }
            }
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
                    <li class="active"><a href="doctor_home.jsp">Home and Notifications</a></li>
                    <li><a href="doctor_prescriptions.jsp">Prescription Editor</a></li>
                    <li><a href="doctor_medical_appointments.jsp">Your Medical Appointments</a></li>
                    <li><a href="doctor_patient_appointments.jsp">Your Patient Appointments</a></li>
                    <li><a href="doctor_account_settings.jsp">Account Settings and Preferences</a></li>
                </ul>
            </div>
            <div class="col-lg-10">
                <div class="center-block">
                    <center>
                        <h4>Notification Panel <small>Gives overall statistics</small></h4>
                    </center>
                </div>

                <%
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
                    int appointmentcount = s2.createCriteria(POJOs.Appointment.class).add(Restrictions.eq("doctor", user.getDoctor())).add(Restrictions.eq("appointmentDate", fullDate)).list().size();
                    int medicalAppointmentCount = s2.createCriteria(POJOs.TimeTableServices.class).add(Restrictions.eq("doctor", user.getDoctor())).add(Restrictions.eq("day", fullDate)).list().size();
                    s2.close();
                %>

                <div class="alert alert-success alert-dismissable">
                    <button type="button" class="close" data-dismiss="alert"
                            aria-hidden="true">
                        &times;
                    </button>
                    Hi, you have <strong><%=appointmentcount%></strong> Patient Appointments Today
                </div>

                <div class="alert alert-success alert-dismissable">
                    <button type="button" class="close" data-dismiss="alert"
                            aria-hidden="true">
                        &times;
                    </button>
                    Hi, you have <strong><%=medicalAppointmentCount%></strong> Medical Appointments Today
                </div>

            </div>
        </div>
    </body>
</html>
