<%-- 
    Document   : virtual_doc
    Created on : Dec 18, 2015, 5:50:40 PM
    Author     : codeguy
--%>

<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.Session"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Doctor - Virtual Doctor Service</title>
        <!-- bootstrap css-->
        <link rel="stylesheet" href="bootstrap-3.3.6-dist/css/bootstrap.min.css"/>

        <script src="js/jquery-2.1.4.min.js"></script>
        <script src="bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
        <script src="bootstrap-3.3.6-dist/js/bootstrap-timepicker.min.js"></script>
        <script src="js/bootstrap-datepicker.js"></script>
        <link rel="stylesheet" href="js/bootstrap-datepicker.min.css"/>
        <link rel="stylesheet" href="bootstrap-3.3.6-dist/css/bootstrap-timepicker.min.css"/>
        <script>
            var array = [];
            var symptomList = "";
            var counter = 0;
            var subcount = 0;

            function addtotable() {
                var table = document.getElementById("tb_symptoms");
                var inTable = false;
                var data = "";
                console.log(array);
                for (var i = 0; i < array.length; i++) {
                    if (array[i] === document.getElementById('symptom').value) {
                        inTable = true;
                        break;
                    }
                }

                console.log(inTable);
                if (!inTable) {
                    data = "<tr><td>" + (counter + 1) + "</td><td>" + document.getElementById('symptom').value + "</td></tr>";
                    table.innerHTML += data;
                    array [counter] = document.getElementById('symptom').value;
                    symptomList += document.getElementById('symptom').value + "#";
                    counter += 1;
                } else {
                    data = "";
                }
            }

            function removefromtable(trid) {}

            function getSymptom(id) {
                //alert('hello');
                $.ajax({
                    url: "handleSymptomGet",
                    type: 'POST',
                    data: {
                        "id": id
                    },
                    success: function (data, textStatus, jqXHR) {
                        //alert(data);
                        if (counter === 0) {
                            counter += 1;
                        }
                        document.getElementById('tb_symptoms').innerHTML += '<tr><td>' + counter + '</td><td>' + data + '</td></tr>';
                        counter += 1;
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        alert('erro you are not awesome');
                    }
                });
            }

            function findDisease() {
                $.ajax({
                    url: "handleGetLikelyDisease",
                    type: 'POST',
                    data: {
                        "symtomlist": symptomList
                    },
                    success: function (data, textStatus, jqXHR) {
                        var result = data.split("#");
                        console.log(data);
                        var table = document.getElementById('tb_res');
                        table.innerHTML = "<thead><tr><th>Disease Name</th><th>Likely Chance of infection</th></tr></thead>";
                        for (var i = 0; i < (result.length - 1); i++) {
                            var precentage = (parseInt(result[i].split(';')[1]) / (result.length - 1)) * 100;
                            table.innerHTML += "<tr><td>" + result[i].split(';')[0] + "</td><td>" + precentage + "%</td></tr>";
                        }

                        $('#createAppointments').css({'visibility': 'visible'});

                    },
                    error: function (jqXHR, textStatus, errorThrown) {

                    }
                });
            }

            function removeAppointment(id) {
                $.ajax({
                    url: "handlePatientAppointmentDelete",
                    type: "POST",
                    data: {
                        "id": id
                    },
                    success: function (data, textStatus, jqXHR) {
                        window.location.replace('virtual_doc.jsp');
                    },
                    error: function (jqXHR, textStatus, errorThrown) {

                    }
                });
            }

            function showAppointmentForm() {

            }

            function updateDoctorID(id) {
                //  alert('hello');
                document.getElementById('doctorid').value = selectBox.options[selectBox.selectedIndex].value;
            }
        </script>
    </head>
    <body>
        <%
            POJOs.User uCustomer = (POJOs.User) request.getSession().getAttribute("user");
            boolean isCustomer = false;
            if (uCustomer != null) {
                isCustomer = uCustomer.getUsertype().equals("CUSTOMER");
            }
        %>
        <div class="container">
            <ul class="navbar navbar-default navbar-fixed-top" role="navigation">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse"
                            data-target="#example-navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a href="" class="navbar-brand">OnlineDoctor!</a>
                </div>

                <div class="collapse navbar-collapse" id="example-navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li ><a href="homepage.jsp">Home</a></li>
                        <li><a href="virtual_doc.jsp">Ask Virtual Doctor</a></li>
                        <li><a href="pharmacy_service.jsp">Pharmacy Services</a></li>
                        <li><a href="medical_services.jsp">Medical Services</a></li>
                        <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">More Stuff...   <span class="glyphicon glyphicon-triangle-bottom"></span>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="our_blog.jsp">Our Blog</a></li>
                                <li><a href="login_service.jsp">Your Account!</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </ul>
            <!--end of navbar code-->
            <h2 style="padding-top: 50px;">Virtual Doctor Service <small>Enter your symptoms, we give you what disease you might have.</small></h2>
            <div  class="form-inline">
                <div class="form-group form-group-lg" style="margin-top: 50px;">
                    <label>Select Symptom from list :- </label>
                    <select class="form-control" id="symptom">
                        <%
                            Session s = Connection.Connector.getSessionFactory().openSession();
                            List<POJOs.Symptom> symList = s.createCriteria(POJOs.Symptom.class).list();
                            for (POJOs.Symptom sy : symList) {%>
                        <option><%=sy.getSymptomName()%></option>
                        <%}
                        %>
                    </select>
                    <button type="button" class="btn btn-primary" onclick="addtotable()"><span class="glyphicon glyphicon-check"></span> Select</button> 
                </div>

            </div>

            <table class="table table-hover" id="tb_symptoms">
                <caption>Your Symptom List</caption>
                <thead>
                    <tr>
                        <th>Symptom #</th>
                        <th>Symptom Name</th>
                    </tr>
                </thead>
            </table>

            <button class="btn btn-success" onclick="findDisease()">Find my disease <span class="glyphicon glyphicon-search"></span></button>        

            <div class="well-lg">
                <h3>Your Results <small>Warning! These are computer-generated results, based on symptoms and not 100% accurate.</small></h3>
                <table class="table table-hover" id="tb_res">
                    <thead>
                        <tr>
                            <th>Disease Name</th>
                            <th>Likely Chance of infection</th>
                        </tr>
                    </thead>
                </table>
                <button class="btn btn-info" id="btnA" onclick="showAppointmentForm()" style="visibility: hidden">Create A Appointment <span class="glyphicon glyphicon-star"></span></button>
            </div>

            <%
                if (isCustomer && uCustomer != null) {%>

            <div class="row">
                <div class="col-lg-6" id="createAppointments" style="visibility: hidden">
                    <div class="well">
                        <h4>Create Appointment</h4>
                        <form action="handleAppointmentCreate" method="POST">
                            <div class="form-group">
                                <input type="hidden" id="doctorid" name="doctor"/>
                                <input type="hidden" name="customer" id="customer" value="<%=uCustomer.getPatient().getIdpatient()%>" />
                                <label>Select Doctor :- </label>
                                <select name="doctor" class="form-control" onchange="updateDoctorID('a')" id="selectBox">
                                    <%
                                        List<POJOs.Doctor> doctorList = Connection.Connector.getSessionFactory().openSession().createCriteria(POJOs.Doctor.class).list();
                                        for (POJOs.Doctor d : doctorList) {
                                            if (d.getIddoctor() != -1) {
                                    %>
                                    <option value="<%=d.getIddoctor()%>">Dr. <%=d.getDoctorFname() + " " + d.getDoctorLname()%></option>
                                    <%}
                                        }
                                    %>
                                </select>
                            </div>
                            <label>Select Date :- </label>
                            <div class="input-group date" data-provide="datepicker" style="margin-bottom: 40px;">
                                <input type="text" class="form-control" name="date">
                                <div class="input-group-addon">
                                    <span class="glyphicon glyphicon-th"></span>
                                </div>
                            </div>                            
                            <button type="submit" class="btn btn-success">Create New Appointment <span class="glyphicon glyphicon-ok-circle"></span></button>
                        </form>
                    </div>
                </div>

                <div class="col-lg-6">
                    <div class="well">
                        <h4>Your Appointments</h4>
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>Doctor's Name</th>
                                    <th>Appointment Date</th>
                                </tr>
                            </thead>
                            <thead>
                                <%
                                    List<POJOs.Appointment> appointments = s.createCriteria(POJOs.Appointment.class).add(Restrictions.eq("patient", uCustomer.getPatient())).list();
                                    for (POJOs.Appointment app : appointments) {%>
                                <tr>
                                    <td>Dr. <%=app.getDoctor().getDoctorFname() + " " + app.getDoctor().getDoctorLname()%></td>
                                    <td><%=app.getAppointmentDate()%></td>
                                    <td>
                                        <button class="btn btn-danger" onclick="removeAppointment(<%=app.getIdappointment()%>)"><span class="glyphicon glyphicon-remove"></span></button>
                                    </td>
                                </tr>
                                <%}
                                %>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
            <%}
            %>
        </div>
    </body>
</html>
