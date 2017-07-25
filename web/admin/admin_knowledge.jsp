<%-- 
    Document   : admin_knowledge
    Created on : Dec 25, 2015, 4:33:23 PM
    Author     : codeguy
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="org.hibernate.Session"%>
<%@page import="javax.swing.JOptionPane"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Panel</title>
        <link rel="stylesheet" href="../bootstrap-3.3.6-dist/css/bootstrap.min.css" />
        <script src="../js/jquery-2.1.4.min.js"></script>
        <script src="../bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>

        <script>
            var searchType = "symptomName";
            var diseaseName = 0;
            var symptomName = 0;

            function changeSearchType(changeVal) {
                if (changeVal === "Symptom Name") {
                    searchType = "symptomName";
                } else if (changeVal === "Disease Name") {
                    searchType = "dieseaseName";
                }
            }

            function changeDisease(d) {
                //  alert(d);
                diseaseName = d;
            }

            function changeSymptom(s) {
                //   alert(s);
                symptomName = s;
            }

            function searchRule() {
                window.location.replace('admin_knowledge.jsp?searchType=' + searchType + "&search=" + $('#search').val());
            }


            function updateSymptoms() {
                $.ajax({
                    type: 'POST',
                    url: "../handlegetSymptoms",
                    data: {
                        "id": symptomName
                    },
                    success: function (data, textStatus, jqXHR) {
                        //   alert(data);
                        var dataset = data.split("#");

                        $('#symtomName').val(dataset[0]);
                        $('#symtomDesc').val(dataset[1]);
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        //  alert(error);
                    }
                });
            }

            function updateDisease() {
                $.ajax({
                    type: 'POST',
                    url: "../handlegetDisease",
                    data: {
                        "id": diseaseName
                    },
                    success: function (data, textStatus, jqXHR) {
                        var dataset = data.split('#');
                        $('#diseasename').val(dataset[0]);
                        $('#disease_desc').val(dataset[1]);
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        //   alert(errorThrown + " " + textStatus + " " + jqXHR.toString());
                    }
                });
            }

            function deleteRules(a) {
                $.ajax({
                    type: 'POST',
                    url: "../handleRuleDelete",
                    data: {
                        "id": a
                    },
                    success: function (data, textStatus, jqXHR) {

                    },
                    error: function (jqXHR, textStatus, errorThrown) {

                    }
                });
            }
        </script>

    </head>
    <body>
          <%
            POJOs.User user = null;
            if (request.getSession() == null) {
                response.sendRedirect("../login_service.jsp");
            } else {
                user = (POJOs.User) request.getSession().getAttribute("user");
                if (user == null) {
                    response.sendRedirect("../login_service.jsp");
                } else if (!user.getUsertype().equals("ADMINISTRATOR")) {
                    response.sendRedirect("../login_service.jsp");
                }
            }
            //security mechanism applied for the blog.           
            boolean isAccessRighed = false;
            Session s1 = Connection.Connector.getSessionFactory().openSession();
            List<POJOs.AdminAccessRight> accessRights = s1.createCriteria(POJOs.AdminAccessRight.class).add(Restrictions.eq("administrator", user.getAdministrator())).list();

            for (POJOs.AdminAccessRight a : accessRights) {
                if (a.getAdminAccess().getIdadminAccess() == 5) {
                    System.out.println("I AM AWESOME!!!");
                    isAccessRighed = true;
                    break;
                }
            }

            if (!isAccessRighed) {
                response.sendRedirect("admin_home.jsp");
            }
        %>
        <div class="container-fluid">
            <div class=row>
                <div class="jumbotron">
                    <div class="center-block">
                        <center>
                            <h2>Welcome <%=user.getAdministrator().getAdminFname() + " " + user.getAdministrator().getAdminLname()%>! <small>to the Control Panel of OnlineDoctor!</small></h2> 
                        </center>
                    </div>
                    <div class="pull-right">
                        <form action="../handlelogout" method="POST">            
                            <button class="btn btn-danger" style="margin-right: 15px;" type="submit">Sign out <span class="glyphicon glyphicon-user"></span></button>
                        </form>
                    </div>
                </div>
            </div>
            <div class="row" style="margin-top: 40px;">
                <div class="col-lg-2 " >
                    <ul class="nav nav-pills nav-stacked">
                        <li><a href="admin_home.jsp">Home and Notifications</a></li>
                        <li><a href="admin_patient.jsp">Patient Details</a></li>
                        <li><a href="admin_doctor.jsp">Doctor Details</a></li>
                        <li><a href="admin_pharamacy.jsp">Pharmacy Details</a></li>
                        <li class="active"><a href="admin_knowledge.jsp">Knowledge Base</a></li>
                        <li><a href="admin_medicalservice.jsp">Medical Services</a></li>
                        <li><a href='admin_blog.jsp'>Blog Details</a></li>
                        <li><a href="admin_misc.jsp">Misc and User Account Mgt</a></li>                       
                    </ul>
                </div>
                <div class="col-lg-10">
                    <div class='input-group'>
                        <input type='text' placeholder="Search Medical Services" class='form-control' id="search"/>
                        <div class="input-group-btn" >
                            <button type="button" class="btn btn-default 
                                    dropdown-toggle" data-toggle="dropdown">
                                Search By
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu pull-right">
                                <li><a onclick="changeSearchType(this.innerHTML)" href="#">Symptom Name</a></li>
                                <li><a onclick="changeSearchType(this.innerHTML)" href="#">Disease Name</a></li>
                            </ul>
                        </div><!-- /btn-group -->
                        <span class='input-group-btn'>
                            <button class='btn btn-default' onclick="searchRule()">Search!</button>
                        </span>

                    </div>

                    <table class="table table-hover">
                        <caption>Knowledge Base Information List</caption>
                        <thead>
                            <tr>
                                <th>Symptom Name</th>
                                <th>Disease Name</th>
                            </tr>
                        </thead>
                        <%

                            List<POJOs.Symptom> symList2 = null;
                            List<POJOs.Diesease> disList2 = null;
                            Session session1 = Connection.Connector.getSessionFactory().openSession();
                            List<POJOs.DieseaseHasSymptom> ruleList = session1.createCriteria(POJOs.DieseaseHasSymptom.class).list();
                            if (request.getParameter("search") != null && request.getParameter("searchType") != null) {

                                if (request.getParameter("searchType").equals("symptomName")) {
                                    System.out.println("hekki");
                                    symList2 = session1.createCriteria(POJOs.Symptom.class).add(Restrictions.like("symptomName", request.getParameter("search") + "%")).list();
                                    System.out.println("fed");
                                } else {
                                    System.out.println("mem");
                                    disList2 = session1.createCriteria(POJOs.Diesease.class).add(Restrictions.like("dieseaseName", request.getParameter("search") + "%")).list();
                                    System.out.println("fed");
                                }

                                if (symList2 != null) {
                                    System.out.println("symptom here");
                                    for (POJOs.Symptom s : symList2) {
                                        System.out.println("goinggg");
                                        List<POJOs.DieseaseHasSymptom> temp = session1.createCriteria(POJOs.DieseaseHasSymptom.class).add(Restrictions.eq("symptom", s)).list();
                                        System.out.println("mmm >> " + temp.size());
                                        ruleList.clear();
                                        ruleList.addAll(temp);

                                    }
                                } else if (disList2 != null) {
                                    System.out.println("diseae here");
                                    for (POJOs.Diesease d : disList2) {
                                        List<POJOs.DieseaseHasSymptom> temp = session1.createCriteria(POJOs.DieseaseHasSymptom.class).add(Restrictions.eq("diesease", d)).list();
                                        ruleList.clear();
                                        ruleList.addAll(temp);
                                    }
                                }
                                System.out.println(ruleList);
                            } else {
                                ruleList = session1.createCriteria(POJOs.DieseaseHasSymptom.class).list();

                            }

                            for (POJOs.DieseaseHasSymptom rule : ruleList) {%>
                        <tr>
                            <td><%=rule.getSymptom().getSymptomName()%></td>
                            <td><%=rule.getDiesease().getDieseaseName()%></td>
                            <td>
                                <button class="btn btn-danger" onclick="deleteRules(<%=rule.getIdRule()%>)"><span class="glyphicon glyphicon-remove"></span> Delete Rule</button>
                            </td>
                        </tr>                        
                        <%}
                        %>
                    </table>

                    <%  if (request.getParameter("res") != null) {
                            if (request.getParameter("res").equals("success")) {%>
                    <div id="doctor_success" class="alert alert-success" style="visibility: visible">
                        <a href="#" class="close" data-dismiss="alert">&times;</a>
                        <strong>Success!</strong> Data was updated successfully!
                    </div>

                    <%} else if (request.getParameter("res").equals("fail")) {%>

                    <div id="doctor_fail" class="alert alert-danger" style="visibility: visible">
                        <a href="#" class="close" data-dismiss="alert">&times;</a>
                        <strong>Oops!</strong> Something went wrong while saving data!
                    </div>
                    <%} else if (request.getParameter("res").equals("validation")) {%>
                    <div id="doctor_valid" class="alert alert-warning">
                        <a href="#" class="close" data-dismiss="alert">&times;</a>
                        <strong>Validation Error Occurred</strong> <%=request.getParameter("validation_msg")%>
                    </div>
                    <%}
                        }
                    %>

                    <table class="table table-hover">
                        <caption>Symptom Details</caption>
                        <thead>
                            <tr>
                                <th>Symptom Name</th>
                                <th>Symptom Description</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<POJOs.Symptom> symList = Connection.Connector.getSessionFactory().openSession().createCriteria(POJOs.Symptom.class).list();
                                for (POJOs.Symptom s : symList) {%>
                            <tr>
                                <td><%=s.getSymptomName()%></td>
                                <td><%=s.getSymptomDescription()%></td>
                                <td>
                                    <button class="btn btn-warning" data-target='#symptomViewer' data-toggle='modal' onclick="changeSymptom(<%=s.getIdsymptom()%>)"><span class="glyphicon glyphicon-wrench" ></span> Update Details</button>
                                </td>
                            </tr>
                            <%}
                            %>
                        </tbody>
                    </table>

                    <form method="POST" action="../handleSymptomAdd">
                        <div class="form-group">
                            <label>Symptom Name :- </label>
                            <input type="text" required name="sym_name" class="form-control" />
                        </div>
                        <div class="form-group">
                            <label>Symptom Description :- </label>
                            <textarea class="form-control" name="sym_desc" required></textarea>
                        </div>
                        <button class="btn btn-info"><span class="glyphicon glyphicon-plus"></span> Save Details</button>
                    </form>

                    <table class="table table-hover">
                        <caption>Disease Details</caption>
                        <thead>
                            <tr>
                                <th>Disease Name</th>
                                <th>Disease Description</th>
                            </tr>                         
                        </thead>
                        <tbody>
                            <%
                                List<POJOs.Diesease> diseaseList = Connection.Connector.getSessionFactory().openSession().createCriteria(POJOs.Diesease.class).list();
                                for (POJOs.Diesease d : diseaseList) {%>
                            <tr>
                                <td><%=d.getDieseaseName()%></td>
                                <td><%=d.getDieseaseDescription()%></td>
                                <td>
                                    <button class="btn btn-warning" data-target='#diseaseViewer' data-toggle='modal' onclick="changeDisease(<%=d.getIddiesease()%>)"><span class="glyphicon glyphicon-wrench"></span> Update Details</button>
                                </td>
                            </tr>
                            <%}
                            %>
                        </tbody>
                    </table>

                    <form style="margin-top:15px;" method="POST" action="../handleDiseaseAdd">
                        <div class="form-group">
                            <label>Disease Name :- </label>
                            <input type="text" required name="dis_name" class="form-control" />
                        </div>
                        <div class="form-group">
                            <label>Disease Description :- </label>
                            <textarea class="form-control" name="dis_desc" class="form-control" required></textarea>
                        </div>
                        <button class="btn btn-info"><span class="glyphicon glyphicon-plus"></span> Save Details</button>
                    </form>

                    <h3>Create A Rule! <small>Specify the Symptom name and the Disease name...</small></h3>

                    <form method="POST" action="../handleAddRule">
                        <div class="form-group">
                            <label>Select Symptom From List :- </label>
                            <select class="form-control" name="symptom">
                                <%
                                    for (POJOs.Symptom s : symList) {%>
                                <option><%=s.getSymptomName()%></option>
                                <%}
                                %>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Select Disease From List :- </label>
                            <select class="form-control" name="disease">
                                <%
                                    for (POJOs.Diesease d : diseaseList) {%>
                                <option><%=d.getDieseaseName()%></option>
                                <%}
                                %>
                            </select>
                        </div>
                        <button class="btn btn-info"><span class="glyphicon glyphicon-save"></span> Add Rule!</button>
                    </form>
                </div>
            </div>

            <!-- Symptom Viewer -->
            <div class='modal fade' id='symptomViewer' tabindex="-1" role='dialog' aria-labelledby='symptomViewerLabel' aria-hidden='true' onshow="updateSymptoms()">
                <div class='modal-dialog'>
                    <div class='modal-content'>
                        <div class='modal-header'>
                            <button type='button' class="close" data-dismiss='modal' aria-hidden='true'>
                                &times;
                            </button>
                            <h4 class='modal-title' id='symptomViewerLabel'>
                                Symptom Editor
                            </h4>
                        </div>
                        <div class="modal-body">
                            <div class='form-group'>
                                <label>Symptom Name :- </label>
                                <input type='text' class='form-control' id='symtomName' />
                            </div>
                            <div class='form-group'>
                                <label>Symptom Description :- </label>
                                <textarea class='form-control' id='symtomDesc'></textarea>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button class='btn btn-warning' onclick="symptomUpdate()">Update Details</button>
                            <button class='btn btn-danger' onclick="symptomDelete()">Delete Details</button>

                        </div>
                    </div>
                </div>
            </div>
            <!-- Disease Viewer -->
            <div class='modal fade' id='diseaseViewer' tabindex="-1" role='dialog' aria-labelledby='diseaseViewer' aria-hidden='true' onshow="updateDisease()">
                <div class='modal-dialog'>
                    <div class='modal-content'>
                        <div class='modal-header'>
                            <button type='button' class="close" data-dismiss='modal' aria-hidden='true'>
                                &times;
                            </button>
                            <h4 class='modal-title' id='symptomViewerLabel'>
                                Disease Editor
                            </h4>
                        </div>
                        <div class="modal-body">
                            <div class='form-group'>
                                <label>Disease Name :- </label>
                                <input type='text' class='form-control' id='diseasename' />
                            </div>
                            <div class='form-group'>
                                <label>Disease Description :- </label>
                                <textarea class='form-control' id='disease_desc' ></textarea>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button class='btn btn-warning' onclick="diseaseUpdate()">Update Details</button>
                            <button class='btn btn-danger' onclick="diseaseDelete()">Delete Details</button> </div>
                    </div>
                </div>
            </div>
        </div>

        <script>
            function diseaseUpdate() {
                $.ajax({
                    type: 'POST',
                    url: "../handleDiseaseUpdate",
                    data: {
                        "id": diseaseName,
                        "name": $('#diseasename').val(),
                        "desc": $('#disease_desc').val()
                    },
                    success: function (data, textStatus, jqXHR) {

                    },
                    error: function (jqXHR, textStatus, errorThrown) {

                    }
                });
            }

            function diseaseDelete() {
                $.ajax({
                    type: 'POST',
                    url: "../handleDiseaseDelete",
                    data: {
                        "id": diseaseName
                    },
                    success: function (data, textStatus, jqXHR) {

                    },
                    error: function (jqXHR, textStatus, errorThrown) {

                    }
                });
            }

            function symptomUpdate() {
                $.ajax({
                    type: 'POST',
                    url: "../handleSymptomUpdate",
                    data: {
                        "id": symptomName,
                        "name": $('#symtomName').val(),
                        "desc": $('#symtomDesc').val()
                    },
                    success: function (data, textStatus, jqXHR) {

                    },
                    error: function (jqXHR, textStatus, errorThrown) {

                    }
                });
            }

            function symptomDelete() {
                $.ajax({
                    type: 'POST',
                    url: "../handleSymptomDelete",
                    data: {
                        "id": symptomName
                    },
                    success: function (data, textStatus, jqXHR) {

                    },
                    error: function (jqXHR, textStatus, errorThrown) {

                    }
                });
            }

        </script>
    </body>
</html>
