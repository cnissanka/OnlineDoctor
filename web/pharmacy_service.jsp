<%-- 
    Document   : pharmacy_service
    Created on : Dec 18, 2015, 6:02:36 PM
    Author     : codeguy
--%>

<%@page import="POJOs.Medicine"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.Session"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pharamacy</title>
        <!-- bootstrap css-->
        <link rel="stylesheet" href="bootstrap-3.3.6-dist/css/bootstrap.min.css"/>

        <script src="js/jquery-2.1.4.min.js"></script>
        <script src="bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>

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
            var prescriptionid;
            function selectPrescription(id) {
                prescriptionid = id;

                $.ajax({
                    type: 'POST',
                    url: "handleChangePrescriptionId",
                    data: {
                        "pres": id
                    },
                    success: function (data, textStatus, jqXHR) {

                    },
                    error: function (jqXHR, textStatus, errorThrown) {

                    }
                });

                $.ajax({
                    type: 'POST',
                    url: "handleClearCart",
                    data: {
                        "pre": id
                    },
                    success: function (data, textStatus, jqXHR) {
                        window.location.replace('pharmacy_service.jsp?prescription=' + id);

                    },
                    error: function (jqXHR, textStatus, errorThrown) {

                    }
                });



            }

            function generateInvoice() {
                var destination = document.getElementById('cmbshipping').value;
                $.ajax({
                    url: "handleGenerateInvoice",
                    type: 'POST',
                    data: {
                        "shipping":destination
                    },
                    success: function (data, textStatus, jqXHR) {
                        if (data === "empty cart"){
                            window.location.replace('pharmacy_service.jsp?res=validation&validation_msg=Cart Cannot be empty at checkout!');
                        }else{
                            window.location.replace('invoice_gen.jsp?id='+data);
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        alert('error');
                    }
                });
            }

            function addtocart(id, qty, p,button) {
               
                $.ajax({
                    url: "handleAddtoCart",
                    type: 'POST',
                    data: {
                        "id": id,
                        "qty": qty,
                        "pres": p
                    },
                    success: function (data, textStatus, jqXHR) {
                        alert('Added to the cart');
                        window.location.replace('pharmacy_service.jsp?prescription=' +<%=request.getParameter("prescription")%>);
                    },
                    error: function (jqXHR, textStatus, errorThrown) {

                    }
                });
            }

            function removeFromCart(btn) {
                medicineid = btn.value;
                $.ajax({
                    url: "handleRemoveFromCart",
                    type: 'POST',
                    data: {
                        "medid": medicineid
                    },
                    success: function (data, textStatus, jqXHR) {
                        window.location.replace('pharmacy_service.jsp');
                    },
                    error: function (jqXHR, textStatus, errorThrown) {

                    }
                });
            }

            function clearCart() {
                $.ajax({
                    url: "handleClearCart",
                    type: 'POST',
                    data: {
                    },
                    success: function (data, textStatus, jqXHR) {
                        alert('Cart Cleared!');
                        window.location.replace('pharmacy_service.jsp');
                    }
                });
            }
        </script>
    </head>
    <body>
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
            <h2 style="padding-top: 50px;">Pharamacy Services <small>Just select the prescription, and we get the medicine for you!</small></h2>
            <%
                POJOs.User user = (POJOs.User) request.getSession().getAttribute("user");
                if (user != null && user.getUsertype().equals("CUSTOMER")) {%>
                   <%  if (request.getParameter("res") != null) {
                            if (request.getParameter("res").equals("success")) {%>
                    <div id="doctor_success" class="alert alert-success" style="visibility: visible">
                        <a href="#" class="close" data-dismiss="alert">&times;</a>
                        <strong>Success!</strong> Doctor's data was updated successfully!
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
                <caption>Your Prescriptions...</caption>
                <thead>
                    <tr>
                        <th>Prescription Doctor</th>
                        <th>Prescription Expire Date</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        Session s = Connection.Connector.getSessionFactory().openSession();
                        List<POJOs.Prescription> prescriptionList = s.createCriteria(POJOs.Prescription.class).add(Restrictions.eq("patient", user.getPatient())).list();
                        for (POJOs.Prescription p : prescriptionList) {
                        if (p.getPrescriptionHasMedicines().iterator().hasNext()== false){s.delete(p);s.beginTransaction().commit();}else{%>
                    
                    <tr>
                        <td>Dr. <%=p.getDoctor().getDoctorFname() + " " + p.getDoctor().getDoctorLname()%></td>
                        <td><%=p.getPrescriptionEnddate()%></td>
                        <td>
                            <button class="btn btn-success" type="button" onclick="selectPrescription(<%=p.getIdprescription()%>)"><span class="glyphicon glyphicon-yen"></span> Purchase Medicine</button>
                        </td>
                    </tr>
                    <%}}
                    %>
                </tbody>
            </table>
              
                <table class="table table-hover">
                    <caption>Your Past Invoices</caption>
                    <thead>
                        <tr>
                            <th>Invoice ID</th>
                            <th>Invoice Date</th>
                            <th>Net Price Charged</th>
                        </tr>
                        <%
                            List <POJOs.Invoice> invoiceList = s.createCriteria(POJOs.Invoice.class).add(Restrictions.eq("patient", user.getPatient())).list();
                            for (POJOs.Invoice invoice : invoiceList){%>
                            <tr>
                                <td><%=invoice.getIdinvoice()%></td>
                                <td><%=invoice.getInvoiceDate()%></td>
                                <td>Rs.<%=invoice.getNetPrice()%>0</td>
                            </tr>
                            <%}
                        %>
                    </thead>
                </table>
            <%
                if (request.getParameter("prescription") != null) {%>
            <table class="table table-hover">
                <caption>Medicine Recommended in the Prescription</caption>
                <thead>
                    <tr>
                        <th>Medicine Name</th>
                        <th>Medicine Description</th>
                        <th>Medicine Price</th>
                        <th>Medicine Image</th>
                        <th>Availability of Stock</th>
                        <th>Qty to Purchase</th>
                        <th>Sub Price</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        POJOs.Prescription p = (POJOs.Prescription) s.createCriteria(POJOs.Prescription.class).add(Restrictions.eq("idprescription", Integer.parseInt(request.getParameter("prescription")))).uniqueResult();
                        Iterator<POJOs.PrescriptionHasMedicine> medicineList = p.getPrescriptionHasMedicines().iterator();

                        while (medicineList.hasNext()) {
                            POJOs.PrescriptionHasMedicine m = medicineList.next();%>
                    <tr>
                        <td><%=m.getMedicine().getMedicineName()%></td>
                        <td><%=m.getMedicine().getMedicineDescription()%></td>
                        <td>Rs.<%=m.getMedicine().getMedicinePrice()%>0</td>
                        <td>
                            <img src="<%=m.getMedicine().getMedicineImage()%>" width="64" height="64" />
                        </td>
                        <td>
                            <%
                                int qtyPurchase = Integer.parseInt(m.getDirectionUse());
                                String output = "";
                                int qty = Integer.parseInt(m.getMedicine().getMedicineQty()) - qtyPurchase;
                                boolean isnot = false;
                                if (qty <= 0) {
                                    output = "Not in Stock";
                                    isnot = true;
                                } else {
                                    output = "Availabile in Stock";
                                }
                                
                                boolean isThereinCart = false;
                                HashMap <Integer, Integer> mycart = (HashMap<Integer, Integer>) request.getSession().getAttribute("cart");
                                if (mycart.get(m.getMedicine().getIdmedicine()) != null){
                                    isThereinCart = true;
                                }
                            %>
                            <%=output%>
                        </td>
                        <td>
                            <%=qtyPurchase%>
                        </td>
                        <%
                            double subPrice = qtyPurchase * m.getMedicine().getMedicinePrice();
                            // int idpr = m.getPrescription().getIdprescription();
%>
                        <td>Rs.<%=subPrice%>0</td>
                        <td>
                            <%
                                if (!isnot && !isThereinCart) {%>
                            <button class="btn btn-primary" onclick="addtocart(<%=m.getMedicine().getIdmedicine()%>, <%=qtyPurchase%>, <%=m.getPrescription().getIdprescription()%>)"><span class="glyphicon glyphicon-shopping-cart"></span></button>
                                <%}
                                %>
                        </td>
                    </tr>                          
                    <%
                        }
                    %>
                </tbody>
            </table>
            <form>
                <button class="btn btn-danger" type="button" data-toggle="modal" data-target="#checkoutViewer"><span class="glyphicon glyphicon-star"></span> Checkout</button>
                <%
                    if (request.getSession().getAttribute("cart") != null & request.getSession().getAttribute("pres") != null) {%>
                <button type="button" class="btn btn-info" data-toggle="modal" data-target="#cartViewer"><span class="glyphicon glyphicon-list"></span> View Cart</button>
                <button type="button" class="btn btn-warning" onclick="clearCart()"><span class="glyphicon glyphicon-remove-circle"></span> Clear Cart</button>
                <%}
                %>
            </form>
            <%}
            %>
            <%}
            %>

            <!--Modal for qualification viewer -->
            <div class='modal fade' id='cartViewer' tabindex="-1" role='dialog' aria-labelledby='cartViewerLabel' aria-hidden='true' >
                <div class='modal-dialog'>
                    <div class='modal-content'>
                        <div class='modal-header'>
                            <button type='button' class="close" data-dismiss='modal' aria-hidden='true'>
                                &times;
                            </button>
                            <h4 class='modal-title' id='cartViewerLabel'>
                                Your Cart
                            </h4>
                        </div>
                        <div class="modal-body">
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Medicine Name</th>
                                        <th>Quantity Ordered</th>
                                        <th>Sub Total</th>
                                    </tr>
                                </thead>

                                <%
                                    double grossprice = 0;
                                    if (request.getSession().getAttribute("cart") != null) {

                                        HashMap<Integer, Integer> cart = (HashMap<Integer, Integer>) request.getSession().getAttribute("cart");
                                        Iterator<Integer> keys = cart.keySet().iterator();
                                        System.out.println("does cart have objs : - " + keys.hasNext());
                                        while (keys.hasNext()) {
                                            Integer m = keys.next();
                                            Integer qty = cart.get(m);
                                            Session s = Connection.Connector.getSessionFactory().openSession();
                                            POJOs.Medicine med = (POJOs.Medicine) s.createCriteria(POJOs.Medicine.class).add(Restrictions.eq("idmedicine", m)).uniqueResult();
                                            double subtotal = med.getMedicinePrice() * qty;
                                %>
                                <tr>
                                    <td><%=med.getMedicineName()%></td>
                                    <td><%=qty%></td>
                                    <td>Rs.<%=subtotal%>0</td>
                                    <td>
                                        <a href="handleRemoveFromCart?medid=<%=m%>" class="btn btn-danger">Remove</a>
                                    </td>
                                    <%
                                        grossprice += subtotal;
                                    %>
                                </tr>
                                <%}
                                    }
                                %>
                            </table>
                            <p><strong>Gross Price is :- </strong>Rs.<%=grossprice%>0</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- End Modal -->



            <!--Modal for qualification viewer -->
            <div class='modal fade' id='checkoutViewer' tabindex="-1" role='dialog' aria-labelledby='checkoutViewerLabel' aria-hidden='true' >
                <div class='modal-dialog'>
                    <div class='modal-content'>
                        <div class='modal-header'>
                            <button type='button' class="close" data-dismiss='modal' aria-hidden='true'>
                                &times;
                            </button>
                            <h4 class='modal-title' id='checkoutViewerLabel'>
                                Checkout <small>Complete buying our goods by selecting shipping destination!</small>
                            </h4>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label>Select Shipping Destination : </label>
                                <select class="form-control" id="cmbshipping">
                                    <%
                                        Session s = Connection.Connector.getSessionFactory().openSession();
                                        List<POJOs.Shipping> shippingdests = s.createCriteria(POJOs.Shipping.class).list();
                                        for (POJOs.Shipping sh : shippingdests) {%>
                                    <option value="<%=sh.getIdshipping()%>"><%=sh.getRegion()%></option>
                                    <%}
                                    %>
                                </select>
                            </div>
                            <button class="btn btn-success" type="button" onclick="generateInvoice()">Buy Medicine</button>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- End Modal -->
        </div>
    </body>
</html>
