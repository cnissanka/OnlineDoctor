<%-- 
    Document   : admin_pharamacy
    Created on : Dec 20, 2015, 1:49:10 PM
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
        <title>Admin Panel</title>
        <meta name="viewport" content="width=device-width,
              initial-scale=1.0,
              maximum-scale=1.0,
              user-scalable=no">
        <link rel="stylesheet" href="../bootstrap-3.3.6-dist/css/bootstrap.min.css"/>
        <script src="../js/jquery-2.1.4.min.js"></script>
        <script src="../bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
        <script src="../js/bootstrap-datepicker.js"></script>
        <link rel="stylesheet" href="../js/bootstrap-datepicker.min.css"/>
        <script src="../js/phoneValidation.js"></script>
        <style>
            .btn-file {
                position: relative;
                overflow: hidden;
            }
            .btn-file input[type=file] {
                position: absolute;
                top: 0;
                right: 0;
                min-width: 100%;
                min-height: 100%;
                font-size: 100px;
                text-align: right;
                filter: alpha(opacity=0);
                opacity: 0;
                outline: none;
                background: white;
                cursor: inherit;
                display: block;
            }
        </style>

        <script>
            var supplierID;
            var medicineID;
            var searchType = "";
            function changeSupplierID(x) {
                supplierID = x;
                $('#txtSupplierID').val(supplierID);
                $('#supplierID').val(supplierID);
            }

            function changeSearchType(x) {
                if (x === "Medicine Name") {
                    searchType = "medicineName";
                } else if (x === "Medicine Qty") {
                    searchType = "medicineQty";
                } else if (x === "Medicine Price") {
                    searchType = "medicinePrice";
                }
            }

            function searchMedicine(x) {
                window.location.replace('admin_pharamacy.jsp?search=' + x + '&searchType=' + searchType);
            }

            var test = "";
            function getSupplierDetails(x) {
                $.ajax({
                    type: 'POST',
                    url: "../handleGetSupplierGet",
                    data: {
                        "id": x
                    },
                    success: function (data, textStatus, jqXHR) {
                        $('#btnupdate').css({"visibility": "visible"});
                        $('#btndelete').css({"visibility": "visible"});

                        var dataset = data.split('#');
                        test = dataset;
                        $('#fname').val(dataset[0]);
                        $('#mname').val(dataset[1]);
                        $('#lname').val(dataset[2]);

                        $('#address').val(dataset[3]);
                        $('#orgname').val(dataset[4]);

                        $('#land').val(dataset[5]);
                        $('#phone1').val(dataset[6]);
                        $('#nic').val(dataset[7]);

                        $('#idsupplier').val(dataset[8]);
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        alert('error');
                    }
                });
            }

            function changeMedicineID(x) {
                medicineID = x;
                $('#medicineID').val(medicineID);
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
            Session s3 = Connection.Connector.getSessionFactory().openSession();
            List<POJOs.AdminAccessRight> accessRights = s3.createCriteria(POJOs.AdminAccessRight.class).add(Restrictions.eq("administrator", user.getAdministrator())).list();

            for (POJOs.AdminAccessRight a : accessRights) {
                if (a.getAdminAccess().getIdadminAccess() == 1) {
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
            <div class="row">
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
                        <li class='active'><a href="admin_pharamacy.jsp">Pharmacy Details</a></li>
                        <li><a href="admin_knowledge.jsp">Knowledge Base</a></li>
                        <li><a href="admin_medicalservice.jsp">Medical Services</a></li>
                        <li><a href='admin_blog.jsp'>Blog Details</a></li>
                        <li><a href="admin_misc.jsp">Misc and User Account Mgt</a></li>                       
                    </ul>
                </div>
                <div class="col-lg-10">
                    <div class='input-group' style='margin-bottom: 15px;'>
                        <input type='text' placeholder='Search Products' class="form-control" id="searchMed"/>
                        <div class="input-group-btn" >
                            <button type="button" class="btn btn-default 
                                    dropdown-toggle" data-toggle="dropdown">
                                Search By
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu pull-right">
                                <li><a onclick="changeSearchType(this.innerHTML)">Medicine Name</a></li>
                                <li><a onclick="changeSearchType(this.innerHTML)">Medicine Qty</a></li>
                                <li><a onclick="changeSearchType(this.innerHTML)">Medicine Price</a></li>                                 
                            </ul>
                        </div><!-- /btn-group -->
                        <span class='input-group-btn'>
                            <button class='btn btn-default' onclick="searchMedicine(document.getElementById('searchMed').value)">Search!</button>
                        </span>
                    </div>
                    <table class='table table-hover'>
                        <caption>Medicine Details</caption>
                        <thead>
                            <tr>
                                <th>Medicine Name</th>
                                <th>Medicine Description</th>
                                <th>Medicine Price</th>
                                <th>Medicine Qty</th>
                                <th>Medicine Supplier</th>
                                <th>Medicine Image</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                Session s2 = Connection.Connector.getSessionFactory().openSession();
                                List<POJOs.Medicine> medicineList = s2.createCriteria(POJOs.Medicine.class).list();

                                if (request.getParameter("search") != null && request.getParameter("searchType") != null) {
                                    if (!(request.getParameter("searchType").equals("medicineName"))) {
                                        if (request.getParameter("searchType").equals("medicinePrice")) {
                                            medicineList = s2.createCriteria(POJOs.Medicine.class).add(Restrictions.eq(request.getParameter("searchType"), Double.parseDouble(request.getParameter("search")))).list();
                                        } else {
                                            medicineList = s2.createCriteria(POJOs.Medicine.class).add(Restrictions.eq(request.getParameter("searchType"), request.getParameter("search"))).list();
                                        }
                                    } else {
                                        medicineList = s2.createCriteria(POJOs.Medicine.class).add(Restrictions.like(request.getParameter("searchType"), request.getParameter("search") + "%")).list();
                                    }
                                }

                                for (POJOs.Medicine med : medicineList) {%>
                            <tr>
                                <td><%=med.getMedicineName()%></td>
                                <td><%=med.getMedicineDescription()%></td>
                                <td>Rs.<%=med.getMedicinePrice()%>0</td>
                                <td><%=med.getMedicineQty()%> Units Avaliable</td>
                                <td><%=med.getSupplier().getSupplierOrganizationName()%></td>
                                <td>
                                    <img src="../<%=med.getMedicineImage()%>" width="64px" height="64px;" />
                                </td>
                                <td>
                                    <button class="btn btn-info" onclick="changeMedicineID(<%=med.getIdmedicine()%>)" data-target="#medicineViewer" data-toggle="modal"><span class="glyphicon glyphicon-wrench"></span> Update Details</button>
                                </td>
                            </tr>
                            <%}
                            %>
                        </tbody>
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
                    <form method="POST" action="../handleMedicineAdd" enctype="multipart/form-data"> 
                        <div class='form-group'>
                            <input type="hidden" id="txtSupplierID" name="supplier" />
                            <label>Medicine Name :- </label>
                            <input type='text' class='form-control' placeholder="Medicine Name Here" name="medicineName" required/>
                        </div>
                        <div class='form-group'>
                            <label>Medicine Description :- </label>
                            <textarea class='form-control' placeholder='Medicine Description Here' name="medicineDesc" required></textarea>
                        </div>
                        <label>Medicine Price :- </label>
                        <div class='input-group'>
                            <span class='input-group-addon'>Rs.</span>
                            <input type='number' value='1000' class="form-control" placeholder='Medicine Price Here' name="medicinePrice" required/>
                            <span class='input-group-addon'>.00</span>
                        </div>
                        <br/>
                        <div class="form-group">
                            <label>Medicine Qty :- </label>
                            <input type='number' value="10" class="form-control" placeholder="Medince Quantity Here" name="medicineQty" required/>
                        </div>

                        <div class="form-group">
                            <label for="pre">Insert Medicine Image :- </label>
                            <span class="btn btn-primary btn-file" id="pre" > 
                                Browse for Medicine Image (png, jpg)<input type="file" name="txtfile" required>
                            </span>
                        </div>

                        <div class='form-group'>
                            <label>Select Supplier :- </label>
                            <select class='form-control' required name="supplier">
                                <%
                                    Session s = Connection.Connector.getSessionFactory().openSession();
                                    List<POJOs.Supplier> supList = s.createCriteria(POJOs.Supplier.class).list();

                                    for (POJOs.Supplier supplier : supList) {
                                        if (!(supplier.getSupplierFname().equals("") && supplier.getSupplierLname().equals(""))) {%>
                                <option onclick="changeSupplierID(<%=supplier.getIdsupplier()%>)" value="<%=supplier.getIdsupplier()%>"><%=supplier.getSupplierFname() + " " + supplier.getSupplierLname()%></option>
                                <%} else {%>
                                <option onclick="changeSupplierID(<%=supplier.getIdsupplier()%>)" value="<%=supplier.getIdsupplier()%>"><%=supplier.getSupplierOrganizationName()%></option>
                                <%}
                                %>
                                <%}
                                %>
                            </select>
                        </div>
                        <input type="submit" class="btn btn-info" value="Save Medicine" name="jj" />

                    </form>


                    <table class='table table-hover' style="margin-top: 15px;">
                        <caption>Supplier Details</caption>
                        <thead>
                            <tr>
                                <th>Supplier Name</th>
                                <th>Supplier Organization</th>
                                <th>Supplier Address</th>
                                <th>Supplier Land</th>
                                <th>Supplier Phone</th>
                                <th>Supplier NIC</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<POJOs.Supplier> supplierList = s2.createCriteria(POJOs.Supplier.class).list();
                                for (POJOs.Supplier supplier : supplierList) {%>
                            <tr>
                                <td><%=supplier.getSupplierFname() + " " + supplier.getSupplierMname() + " " + supplier.getSupplierLname()%></td>
                                <td><%=supplier.getSupplierOrganizationName()%></td>
                                <td><%=supplier.getSupplierAddress()%></td>
                                <td><%=supplier.getSupplierLand()%></td>
                                <td><%=supplier.getSupplierPhone()%></td>
                                <td><%=supplier.getSupplierNic()%></td>
                                <td>
                                    <button class="btn btn-info" onclick="getSupplierDetails(<%=supplier.getIdsupplier()%>)"><span class="glyphicon glyphicon-wrench"></span> Update Details</button>
                                </td>
                            </tr>
                            <%}
                            %>
                        </tbody>
                    </table>
                    <form action="../handleSupplierAdd" method="POST">
                        <div class='form-group'>
                            <input type="hidden" id="idsupplier" name="id" />
                            <label>Supplier First Name :- </label>
                            <input type='text' class='form-control' placeholder="Supplier First Name Here" name="fname" id="fname" required="true"/>
                        </div>
                        <div class='form-group'>
                            <label>Supplier Middle Name :- </label>
                            <input type='text' class='form-control' placeholder="Supplier Middle Name Here" name="mname" id="mname" required="true"/>
                        </div>
                        <div class='form-group'>
                            <label>Supplier Last Name :- </label>
                            <input type='text' class='form-control' placeholder="Supplier Last Name Here" name="lname" id="lname" required="true"/>
                        </div>
                        <div class='form-group'>
                            <label>Supplier Address :- </label>
                            <textarea class='form-control' placeholder="Supplier Address Here" name="address" id="address" required="true"></textarea>
                        </div>
                        <div class='form-group'>
                            <label>Supplier Organization Name :- </label>
                            <input type='text' class='form-control' placeholder="Supplier Organization Here"  name="orgname" id="orgname" required="true"/>
                        </div>
                        <label>Supplier Land # :- </label>
                        <div class='input-group'>
                            <span class='input-group-addon'>+94</span>
                            <input type='text' class='form-control' placeholder="Supplier Land Here" name="land" id="land" required="true"/>
                        </div>
                        <label>Supplier Phone # :- </label>
                        <div class='input-group'>
                            <span class='input-group-addon'>+94</span>
                            <input type='text' class='form-control' placeholder='Supplier Phone Here' name="mobile" id="mobile1" required="true" onkeypress="return validatePhone(this, event)"/>
                        </div>
                        <div class='form-group'>
                            <label>Supplier NIC :- </label>
                            <input type='text' class='form-control' placeholder='Supplier NIC' name="nic" id="nic" onblur="validateNic(this)"/>
                        </div>
                        <input type="submit" name="btn" class="btn btn-info" name="btn" value="Save Supplier" />
                        <input type="submit" name="btn" class="btn btn-info" name="btn" value="Update Supplier" style="visibility:hidden;" id="btnupdate"/>
                        <input type="submit" name="btn" class="btn btn-info" name="btn" value="Delete Supplier" style="visibility: hidden" id="btndelete"/>
                    </form>
                    <h3>Goods Receive Note (GRN) <small>Mange GRN Data Here</small></h3>
                    <table class="table table-hover">
                        <caption>GRNs So Far</caption>
                        <thead>
                            <tr>
                                <th>Date</th>
                                <th>Medicine</th>
                                <th>Supplier</th>
                                <th>Qty Supplied</th>
                                <th>Comments/Notes</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<POJOs.Grn> grnList = s2.createCriteria(POJOs.Grn.class).list();
                                for (POJOs.Grn g : grnList) {%>
                            <tr>
                                <td><%=g.getDate()%></td>
                                <td><%=g.getMedicine().getMedicineName()%></td>
                                <td><%=g.getSupplier().getSupplierFname() + " " + g.getSupplier().getSupplierLname()%></td>
                                <td><%=g.getQtySupplied()%></td>
                                <td><%=g.getNotes()%></td>
                            </tr>
                            <%}
                            %>
                        </tbody>
                    </table>
                    <form method="POST" action="../handleGRNSave">
                        <div class="form-group">
                            <label>Select Medicine Product :- </label>
                            <select class="form-control"  name="supid">
                                <%
                                    Session s5 = Connection.Connector.getSessionFactory().openSession();
                                    List<POJOs.Medicine> medicineList2 = s5.createCriteria(POJOs.Medicine.class).list();

                                    for (POJOs.Medicine m : medicineList2) {%>
                                <option value="<%=m.getIdmedicine()%>"><%=m.getMedicineName()%></option>
                                <%}
                                %>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Select Supplier :- </label>
                            <select class="form-control" name="medid">
                                <%
                                    for (POJOs.Supplier su : supplierList) {%>
                                <option value="<%=su.getIdsupplier()%>" ><%=su.getSupplierOrganizationName()%></option>
                                <%}
                                %>
                            </select>
                        </div>
                        <label>Date :- </label>
                        <div class="input-group date" data-provide="datepicker">
                            <input type="text" class="form-control" name="date">
                            <div class="input-group-addon">
                                <span class="glyphicon glyphicon-th"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label>Comments/Notes :- </label>
                            <textarea class="form-control" name="comments"></textarea>
                        </div>
                        <div class="form-group">
                            <label>Medicine Qty :- </label>
                            <input type='number' value="10" name="qty" class="form-control" placeholder="Medince Quantity Here" name="medicineQty" required/>
                        </div>
                        <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span> Apply GRN</button>
                    </form>

                    <h4 style="margin-top:40px;">Shipping Routes Management</h4>        
                    <form action="../handleShipDelete" method="POST">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>Shipping Region</th>
                                    <th>Shipping Price</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    //   Session se = Connection.Connector.getSessionFactory().openSession();
                                    List<POJOs.Shipping> shippinglist = s.createCriteria(POJOs.Shipping.class).list();
                                    for (POJOs.Shipping shipping : shippinglist) {%>
                                <tr>
                                    <td><%=shipping.getRegion()%></td>
                                    <td>Rs. <%=shipping.getPrice()%>0</td>
                                    <td>
                                        <button type="submit" class="btn btn-danger" name="btn" value="<%=shipping.getIdshipping()%>"><span class="glyphicon glyphicon-remove"></span></button>
                                    </td>
                                </tr>
                                <%}
                                %>
                            </tbody>
                        </table>
                    </form>
                    <form action="../handleShipAdd" method="POST">
                        <div class="form-group">
                            <label>Region :- </label>
                            <input type="text" class="form-control" name="region" />
                        </div>
                        <div class="form-group">
                            <label>Price :- </label>
                            <input type="number" class="form-control" name="price" />
                        </div>
                        <button class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span> Add Shipping Route</button>
                    </form>
                            
                   <h3>Company Invoices.</h3>   
                   <form method="POST" action="../handleinvoicedelete_admin">
                   <table class="table table-hover">
                    <caption>Company Invoice Record</caption>
                    <thead>
                        <tr>
                            <th>Invoice ID</th>
                            <th>Invoice Date</th>
                            <th>Patient Name</th>
                            <th>Net Price Charged</th>
                        </tr>
                        <%
                            
                            List <POJOs.Invoice> invoiceList = s.createCriteria(POJOs.Invoice.class).list();
                            for (POJOs.Invoice invoice : invoiceList){%>
                            <tr>
                                <td><%=invoice.getIdinvoice()%></td>
                                <td><%=invoice.getInvoiceDate()%></td>
                                <td>
                                    <%=invoice.getPatient().getPatientFname().replace('.', '#').split("#")[1] + " " + invoice.getPatient().getPatientMname() + " " + invoice.getPatient().getPatientLname()%>
                                </td>
                                <td>Rs.<%=invoice.getNetPrice()%>0</td>
                                <td>
                                    <button class="btn btn-danger" type="submit" value="<%=invoice.getIdinvoice()%>" name="id">Remove Invoice</button>
                                </td>
                            </tr>
                            <%}
                        %>
                    </thead>
                </table>
                    </form>

                </div> <!-- end of col -->
            </div> <!-- end of container -->




            <!-- Medicine Viewer -->
            <div class='modal fade' id='medicineViewer' tabindex="-1" role='dialog' aria-labelledby='medicineViewerLabel' aria-hidden='true' onshow="getMedicineDetails()">
                <div class='modal-dialog'>
                    <div class='modal-content'>
                        <div class='modal-header'>
                            <button type='button' class="close" data-dismiss='modal' aria-hidden='true'>
                                &times;
                            </button>
                            <h4 class='modal-title' id='medicineViewerLabel'>
                                Medicine Details Editor
                            </h4>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label>Medicine Name :- </label>
                                <input type="text" class="form-control" id="medicineName" />
                            </div>
                            <div class="form-group">
                                <label>Medicine Description :- </label>
                                <textarea class="form-control" id="medicineDescription"></textarea>
                            </div>
                            <div class='input-group'>
                                <span class='input-group-addon'>Rs.</span>
                                <input type='number' value='1000' class="form-control" placeholder='Medicine Price Here' id="medicinePrice" required/>
                                <span class='input-group-addon'>.00</span>
                            </div>
                            <div class="form-group">
                                <label>Medicine Qty :- </label>
                                <input type='number' value="10" class="form-control" placeholder="Medince Quantity Here" id="medicineQty" required/>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button class='btn btn-warning' onclick="medicineUpdate()">Update Details</button>
                            <button class='btn btn-danger' onclick="medicineDelete()">Delete Details</button> </div>
                    </div>
                </div>
            </div> 

            <script>
                function getMedicineDetails() {
                    $.ajax({
                        url: "../handleMedicineDetailsGet",
                        type: 'POST',
                        data: {
                            "id": medicineID
                        },
                        success: function (data, textStatus, jqXHR) {
                            dataset = data.split('#');
                            $('#medicineName').val(dataset[0]);
                            $('#medicineDescription').val(dataset[1]);
                            $('#medicinePrice').val(dataset[2]);
                            $('#medicineQty').val(dataset[3]);

                        },
                        error: function (jqXHR, textStatus, errorThrown) {

                        }
                    });
                }

                function medicineUpdate() {
                    $.ajax({
                        url: "../handleMedicineUpdate",
                        type: 'POST',
                        data: {
                            "id": medicineID,
                            "name": $('#medicineName').val(),
                            "desc": $('#medicineDescription').val(),
                            "price": $('#medicinePrice').val(),
                            "qty": $('#medicineQty').val()
                        },
                        success: function (data, textStatus, jqXHR) {

                        },
                        error: function (jqXHR, textStatus, errorThrown) {

                        }
                    });
                }

                function medicineDelete() {
                    $.ajax({
                        url: "../handleMedicineDelete",
                        type: 'POST',
                        data: {
                            "id": medicineID
                        },
                        success: function (data, textStatus, jqXHR) {
                            window.location.replace('admin_pharamacy.jsp?res=success');
                        },
                        error: function (jqXHR, textStatus, errorThrown) {

                        }
                    });
                }
            </script>
        </div>
    </body>
</html>
