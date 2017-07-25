<%-- 
    Document   : invoice_gen
    Created on : Jan 19, 2016, 8:33:09 PM
    Author     : codeguy
--%>

<%@page import="java.util.HashMap"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="org.hibernate.Session"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Invoice</title>
        <script src="js/jquery-2.1.4.min.js"></script>
        <script src="bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="bootstrap-3.3.6-dist/css/bootstrap.min.css">
    </head>
    <body onload="window.print()">
        <div class="container">
            <div class="row">
                <%
                    Session s = Connection.Connector.getSessionFactory().openSession();
                    int invoiceId = Integer.parseInt(request.getParameter("id"));
                    POJOs.Invoice invoice = (POJOs.Invoice) s.createCriteria(POJOs.Invoice.class).add(Restrictions.eq("idinvoice", invoiceId)).uniqueResult();
                %>
                <div class="col-lg-3">
                    <label>Date      : <%=invoice.getInvoiceDate()%></label>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-3">
                    <label>Invoice # : <%=invoiceId%></label>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-3">
                    <label>Patient # : <%=invoice.getPatient().getIdpatient()%></label>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-3">
                    <label>Patient Name : <%=invoice.getPatient().getPatientFname() + " " + invoice.getPatient().getPatientMname() + " " + invoice.getPatient().getPatientLname()%></label>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <table class="table table-bordered">
                        <caption>Medicine List</caption>
                        <thead>
                            <tr>
                                <th>Medicine Id</th>
                                <th>Medicine Name</th>
                                <th>Qty Ordered</th>
                                <th>Sub Price</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                HashMap<Integer, Integer> cart = (HashMap<Integer, Integer>) request.getSession().getAttribute("cart");
                                Iterator<Integer> medIds = cart.keySet().iterator();
                                while (medIds.hasNext()) {
                                    int cMedid = medIds.next();
                                    POJOs.Medicine med = (POJOs.Medicine) s.createCriteria(POJOs.Medicine.class).add(Restrictions.eq("idmedicine", cMedid)).uniqueResult();
                                    double subprice = cart.get(cMedid) * med.getMedicinePrice();
                            %>
                            <tr>
                                <td><%=med.getIdmedicine()%></td>
                                <td><%=med.getMedicineName()%></td>
                                <td><%=cart.get(cMedid)%></td>
                                <td><%=subprice%></td>
                            </tr>
                            <%
                                }
                            %>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-3">
                    <label>Gross Price : Rs.<%=invoice.getGrossPrice()%>0</label>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-5">
                    <label>Shipping Destination : <%=invoice.getShipping().getRegion()%> (Rs. <%=invoice.getShipping().getPrice()%>)</label>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-3">
                    <label>Net Price : Rs.<%=invoice.getNetPrice()%>0</label>
                </div>
            </div>

        </div>
    </body>
</html>
