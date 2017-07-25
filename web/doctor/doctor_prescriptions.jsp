<%-- 
    Document   : doctor_prescriptions
    Created on : Dec 21, 2015, 9:57:10 AM
    Author     : codeguy
--%>

<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="org.hibernate.Session"%>
<%@page import="java.util.List"%>
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
            var x;
            var selectedMedicine = [];
            var medicalList = "";
            var counter = 0;
            var searchType = "";
            function selectPatient(x) {
                window.location.replace('doctor_prescriptions.jsp?patientid=' + x);
            }

            function selectMedicine(btn, qty, medicine) {
                var isFound = false;
                for (var i = 0; i < selectedMedicine.length; i++) {
                    if (selectedMedicine [i].split(';')[1] == medicine) {
                        selectedMedicine [i] = qty + ";" + medicine;
                        isFound = true;
                    }
                }

                if (!isFound) {
                    selectedMedicine [counter] = qty + ";" + medicine;
                    counter += 1;
                }
                medicalList = "";
                for (var i = 0; i < selectedMedicine.length; i++) {
                    medicalList += selectedMedicine [i] + "#";
                }
            }

            function generatePrescription(id) {
            //    alert(id);
                $.ajax({
                    url: "../handlePrescriptionCreate",
                    type: 'POST',
                    data: {
                        "medicalList": medicalList,
                        "patientid": id
                    },
                    success: function (data, textStatus, jqXHR) {
                      //  alert(data);
                      alert('Prescription Created Successfully');
                    },
                    error: function (jqXHR, textStatus, errorThrown) {

                    }
                });
            }

            function changeSearchType(searchtype) {
                if (searchtype === "Patient First Name") {
                    searchType = "patientFname";
                } else if (searchtype === "Patient Middle Name") {
                    searchType = "patientMname";
                } else if (searchtype === "Patient Last Name") {
                    searchType = "patientLname";
                }
            }

            function searchPatient(searchbox) {
                window.location.replace('doctor_prescriptions.jsp?search=' + searchbox + "&searchType=" + searchType);
            }
        </script>
    </head>
    <body>
        <%
            POJOs.User user = null;
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
                    <li><a href="doctor_home.jsp">Home and Notifications</a></li>
                    <li class="active"><a href="doctor_prescriptions.jsp">Prescription Editor</a></li>
                    <li><a href="doctor_medical_appointments.jsp">Your Medical Appointments</a></li>
                    <li><a href="doctor_patient_appointments.jsp">Your Patient Appointments</a></li>
                    <li><a href="doctor_account_settings.jsp">Account Settings and Preferences</a></li>
                </ul>
            </div>
            <div class="col-lg-10">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Search Patient" id="txtsearch"/>
                    <div class="input-group-btn" >
                        <button type="button" class="btn btn-default 
                                dropdown-toggle" data-toggle="dropdown">
                            Search By
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu pull-right">
                            <li><a onclick="changeSearchType(this.innerHTML)">Patient First Name</a></li>
                            <li><a onclick="changeSearchType(this.innerHTML)">Patient Middle Name</a></li>
                            <li><a onclick="changeSearchType(this.innerHTML)">Patient Last Name</a></li>                                 
                        </ul>
                    </div><!-- /btn-group -->
                    <span class="input-group-btn">
                        <button class="btn btn-default" onclick="searchPatient(document.getElementById('txtsearch').value)">Search!</button>
                    </span>
                </div>
                <table class="table table-hover">
                    <caption>Patient Details</caption>
                    <thead>
                        <tr>
                            <th>Patient #</th>
                            <th>Patient Name</th>
                            <th>Patient Gender</th>
                            <th>Patient Date of Birth</th>
                            <th>Patient Phone #</th>
                            <th>Patient Email Address</th>
                        </tr>
                    </thead>
                    <%
                        List<POJOs.Patient> listPatient = null;
                        if (request.getParameter("search") == null) {
                            listPatient = Connection.Connector.getSessionFactory().openSession().createCriteria(POJOs.Patient.class).list();
                        } else if (!(request.getParameter("search").equals("") && request.getParameter("searchType").equals(""))){
                            String search = request.getParameter("search");
                            String searchType = request.getParameter("searchType");
                            listPatient = Connection.Connector.getSessionFactory().openSession().createCriteria(POJOs.Patient.class).add(Restrictions.like(searchType, search + "%")).list();
                        }else{
                        listPatient = Connection.Connector.getSessionFactory().openSession().createCriteria(POJOs.Patient.class).list();}
                        int counter = 1;
                        for (POJOs.Patient p : listPatient) {
                            if (p.getIdpatient() != -1) {
                    %>
                    <tr>
                        <td><%=counter%></td>
                        <td><%=p.getPatientFname() + " " + p.getPatientMname() + " " + p.getPatientLname()%></td>
                        <td><%=p.getPatientGender()%></td>
                        <td><%=p.getPatientDob()%></td>
                        <td><%=p.getPatientPhoneno()%></td>
                        <td><%=p.getPatientEmail()%></td>
                        <td>
                            <button onclick="selectPatient(<%=p.getIdpatient()%>)" class='btn btn-warning'><span class='glyphicon glyphicon-eject'></span> Select</button>
                        </td>
                    </tr>
                    <%counter += 1;
                            }
                        }
                    %>
                </table>
                <div class="input-group">
                    <label>Patient Selected :- </label>
                    <%if (request.getParameter("patientid") != null) {%>
                    <input type="text" placeholder="Patient Selected" class="form-control" id="txtpatientid" disabled required value='<%=request.getParameter("patientid")%>'/> 
                    <%}%>                    

                </div>
                <table class="table table-hover">
                    <caption>Medicine Details</caption>
                    <thead>
                        <tr>
                            <th>Medicine #</th>
                            <th>Medicine Name</th>
                            <th>Medicine Description</th>
                            <th>Select Qty</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            Session s = Connection.Connector.getSessionFactory().openSession();
                            List<POJOs.Medicine> medicineList = s.createCriteria(POJOs.Medicine.class).list();
                            int counter2 = 1;
                            for (POJOs.Medicine m : medicineList) {%>
                        <tr>
                            <td><%=counter2%></td>
                            <td><%=m.getMedicineName()%></td>
                            <td><%=m.getMedicineDescription()%></td>
                            <td>
                                <input type='number' class='form-control' id='txtQtySelected<%=m.getIdmedicine()%>' placeholder='Enter Qty'/>
                            </td>
                            <td>
                                <button class='btn btn-primary' onclick="selectMedicine(this, document.getElementById('txtQtySelected<%=m.getIdmedicine()%>').value, <%=m.getIdmedicine()%>)"><span class='glyphicon glyphicon-check'></span> Select Medicine</button>
                            </td>
                        </tr>
                        <%counter2++;
                            }
                        %>
                    </tbody>
                </table>
                <button class="btn btn-info" onclick="generatePrescription(document.getElementById('txtpatientid').value)"><span class="glyphicon glyphicon-file"></span> Generate Prescription</button>
            </div>
        </div>
    </body>
</html>
