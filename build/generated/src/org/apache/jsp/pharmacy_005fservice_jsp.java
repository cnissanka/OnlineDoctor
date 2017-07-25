package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import POJOs.Medicine;
import java.util.HashMap;
import java.util.Iterator;
import org.hibernate.criterion.Restrictions;
import java.util.List;
import org.hibernate.Session;

public final class pharmacy_005fservice_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("        <!-- bootstrap css-->\n");
      out.write("        <link rel=\"stylesheet\" href=\"bootstrap-3.3.6-dist/css/bootstrap.min.css\"/>\n");
      out.write("\n");
      out.write("        <script src=\"js/jquery-2.1.4.min.js\"></script>\n");
      out.write("        <script src=\"bootstrap-3.3.6-dist/js/bootstrap.min.js\"></script>\n");
      out.write("\n");
      out.write("        <style>\n");
      out.write("            .btn-file {\n");
      out.write("                position: relative;\n");
      out.write("                overflow: hidden;\n");
      out.write("            }\n");
      out.write("            .btn-file input[type=file] {\n");
      out.write("                position: absolute;\n");
      out.write("                top: 0;\n");
      out.write("                right: 0;\n");
      out.write("                min-width: 100%;\n");
      out.write("                min-height: 100%;\n");
      out.write("                font-size: 100px;\n");
      out.write("                text-align: right;\n");
      out.write("                filter: alpha(opacity=0);\n");
      out.write("                opacity: 0;\n");
      out.write("                outline: none;\n");
      out.write("                background: white;\n");
      out.write("                cursor: inherit;\n");
      out.write("                display: block;\n");
      out.write("            }\n");
      out.write("        </style>\n");
      out.write("\n");
      out.write("        <script>\n");
      out.write("            var prescriptionid;\n");
      out.write("            function selectPrescription(id) {\n");
      out.write("                prescriptionid = id;\n");
      out.write("                window.location.replace('pharmacy_service.jsp?prescription=' + id);\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            function addtocart(id, qty) {\n");
      out.write("                $.ajax({\n");
      out.write("                    url: \"handleAddtoCart\",\n");
      out.write("                    type: 'POST',\n");
      out.write("                    data: {\n");
      out.write("                        \"id\": id,\n");
      out.write("                        \"qty\": qty\n");
      out.write("                    },\n");
      out.write("                    success: function (data, textStatus, jqXHR) {\n");
      out.write("                        alert('Added to the cart');\n");
      out.write("                        window.location.replace('pharmacy_service.jsp?prescription=' +");
      out.print(request.getParameter("prescription"));
      out.write(");\n");
      out.write("                    },\n");
      out.write("                    error: function (jqXHR, textStatus, errorThrown) {\n");
      out.write("\n");
      out.write("                    }\n");
      out.write("                });\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            function clearCart() {\n");
      out.write("                $.ajax({\n");
      out.write("                    url: \"handleClearCart\",\n");
      out.write("                    type: 'POST',\n");
      out.write("                    data: {\n");
      out.write("                    },\n");
      out.write("                    success: function (data, textStatus, jqXHR) {\n");
      out.write("                        alert('Cart Cleared!');\n");
      out.write("                        window.location.replace('pharmacy_service.jsp');\n");
      out.write("                    }\n");
      out.write("                });\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div class=\"container\">\n");
      out.write("            <ul class=\"navbar navbar-default navbar-fixed-top\" role=\"navigation\">\n");
      out.write("                <div class=\"navbar-header\">\n");
      out.write("                    <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\"\n");
      out.write("                            data-target=\"#example-navbar-collapse\">\n");
      out.write("                        <span class=\"sr-only\">Toggle navigation</span>\n");
      out.write("                        <span class=\"icon-bar\"></span>\n");
      out.write("                        <span class=\"icon-bar\"></span>\n");
      out.write("                        <span class=\"icon-bar\"></span>\n");
      out.write("                    </button>\n");
      out.write("                    <a href=\"\" class=\"navbar-brand\">OnlineDoctor!</a>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div class=\"collapse navbar-collapse\" id=\"example-navbar-collapse\">\n");
      out.write("                    <ul class=\"nav navbar-nav\">\n");
      out.write("                        <li ><a href=\"homepage.jsp\">Home</a></li>\n");
      out.write("                        <li><a href=\"virtual_doc.jsp\">Ask Virtual Doctor</a></li>\n");
      out.write("                        <li><a href=\"pharmacy_service.jsp\">Pharmacy Services</a></li>\n");
      out.write("                        <li><a href=\"medical_services.jsp\">Medical Services</a></li>\n");
      out.write("                        <li class=\"dropdown\"><a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">More Stuff...   <span class=\"glyphicon glyphicon-triangle-bottom\"></span>\n");
      out.write("                            </a>\n");
      out.write("                            <ul class=\"dropdown-menu\">\n");
      out.write("                                <li><a href=\"our_blog.jsp\">Our Blog</a></li>\n");
      out.write("                                <li><a href=\"login_service.jsp\">Your Account!</a></li>\n");
      out.write("                            </ul>\n");
      out.write("                        </li>\n");
      out.write("                    </ul>\n");
      out.write("                </div>\n");
      out.write("            </ul>\n");
      out.write("            <h2 style=\"padding-top: 50px;\">Pharamacy Services <small>Just select the prescription, and we get the medicine for you!</small></h2>\n");
      out.write("            ");

                POJOs.User user = (POJOs.User) request.getSession().getAttribute("user");
                if (user != null && user.getUsertype().equals("CUSTOMER")) {
      out.write("\n");
      out.write("            <table class=\"table table-hover\">\n");
      out.write("                <caption>Your Prescriptions...</caption>\n");
      out.write("                <thead>\n");
      out.write("                    <tr>\n");
      out.write("                        <th>Prescription Doctor</th>\n");
      out.write("                        <th>Prescription Expire Date</th>\n");
      out.write("                    </tr>\n");
      out.write("                </thead>\n");
      out.write("                <tbody>\n");
      out.write("                    ");

                        Session s = Connection.Connector.getSessionFactory().openSession();
                        List<POJOs.Prescription> prescriptionList = s.createCriteria(POJOs.Prescription.class).add(Restrictions.eq("patient", user.getPatient())).list();
                        for (POJOs.Prescription p : prescriptionList) {
      out.write("\n");
      out.write("                    <tr>\n");
      out.write("                        <td>Dr. ");
      out.print(p.getDoctor().getDoctorFname() + " " + p.getDoctor().getDoctorLname());
      out.write("</td>\n");
      out.write("                        <td>");
      out.print(p.getPrescriptionEnddate());
      out.write("</td>\n");
      out.write("                        <td>\n");
      out.write("                            <button class=\"btn btn-success\" type=\"button\" onclick=\"selectPrescription(");
      out.print(p.getIdprescription());
      out.write(")\"><span class=\"glyphicon glyphicon-yen\"></span> Purchase Medicine</button>\n");
      out.write("                        </td>\n");
      out.write("                    </tr>\n");
      out.write("                    ");
}
                    
      out.write("\n");
      out.write("                </tbody>\n");
      out.write("            </table>\n");
      out.write("\n");
      out.write("            ");

                if (request.getParameter("prescription") != null) {
      out.write("\n");
      out.write("            <table class=\"table table-hover\">\n");
      out.write("                <caption>Medicine Recommended in the Prescription</caption>\n");
      out.write("                <thead>\n");
      out.write("                    <tr>\n");
      out.write("                        <th>Medicine Name</th>\n");
      out.write("                        <th>Medicine Description</th>\n");
      out.write("                        <th>Medicine Price</th>\n");
      out.write("                        <th>Medicine Image</th>\n");
      out.write("                        <th>Availability of Stock</th>\n");
      out.write("                        <th>Qty to Purchase</th>\n");
      out.write("                        <th>Sub Price</th>\n");
      out.write("                    </tr>\n");
      out.write("                </thead>\n");
      out.write("                <tbody>\n");
      out.write("                    ");

                        POJOs.Prescription p = (POJOs.Prescription) s.createCriteria(POJOs.Prescription.class).add(Restrictions.eq("idprescription", Integer.parseInt(request.getParameter("prescription")))).uniqueResult();
                        Iterator<POJOs.PrescriptionHasMedicine> medicineList = p.getPrescriptionHasMedicines().iterator();

                        while (medicineList.hasNext()) {
                            POJOs.PrescriptionHasMedicine m = medicineList.next();
      out.write("\n");
      out.write("                    <tr>\n");
      out.write("                        <td>");
      out.print(m.getMedicine().getMedicineName());
      out.write("</td>\n");
      out.write("                        <td>");
      out.print(m.getMedicine().getMedicineDescription());
      out.write("</td>\n");
      out.write("                        <td>Rs.");
      out.print(m.getMedicine().getMedicinePrice());
      out.write("0</td>\n");
      out.write("                        <td>\n");
      out.write("                            <img src=\"");
      out.print(m.getMedicine().getMedicineImage());
      out.write("\" width=\"64\" height=\"64\" />\n");
      out.write("                        </td>\n");
      out.write("                        <td>\n");
      out.write("                            ");

                                int qtyPurchase = Integer.parseInt(m.getDirectionUse());
                                String output = "";
                                int qty = Integer.parseInt(m.getMedicine().getMedicineQty()) - qtyPurchase;
                                if (qty < 0) {
                                    output = "Not in Stock";
                                } else {
                                    output = "Availabile in Stock";
                                }
                            
      out.write("\n");
      out.write("                            ");
      out.print(output);
      out.write("\n");
      out.write("                        </td>\n");
      out.write("                        <td>\n");
      out.write("                            ");
      out.print(qtyPurchase);
      out.write("\n");
      out.write("                        </td>\n");
      out.write("                        ");

                            double subPrice = qtyPurchase * m.getMedicine().getMedicinePrice();
                        
      out.write("\n");
      out.write("                        <td>Rs.");
      out.print(subPrice);
      out.write("0</td>\n");
      out.write("                        <td>\n");
      out.write("                            <button class=\"btn btn-primary\" onclick=\"addtocart(");
      out.print(m.getMedicine().getIdmedicine());
      out.write(',');
      out.write(' ');
      out.print(qtyPurchase);
      out.write(")\"><span class=\"glyphicon glyphicon-shopping-cart\"></span></button>\n");
      out.write("                        </td>\n");
      out.write("                    </tr>                          \n");
      out.write("                    ");

                        }
                    
      out.write("\n");
      out.write("                </tbody>\n");
      out.write("            </table>\n");
      out.write("            <form>\n");
      out.write("                <button class=\"btn btn-danger\"><span class=\"glyphicon glyphicon-star\"></span> Checkout</button>\n");
      out.write("                ");

                    if (request.getSession().getAttribute("cart") != null) {
      out.write("\n");
      out.write("                <button type=\"button\" class=\"btn btn-info\" data-toggle=\"modal\" data-target=\"#cartViewer\"><span class=\"glyphicon glyphicon-list\"></span> View Cart</button>\n");
      out.write("                <button type=\"button\" class=\"btn btn-warning\" onclick=\"clearCart()\"><span class=\"glyphicon glyphicon-remove-circle\"></span> Clear Cart</button>\n");
      out.write("                ");
}
                
      out.write("\n");
      out.write("            </form>\n");
      out.write("            ");
}
            
      out.write("\n");
      out.write("            ");
}
            
      out.write("\n");
      out.write("\n");
      out.write("            <!--Modal for qualification viewer -->\n");
      out.write("            <div class='modal fade' id='cartViewer' tabindex=\"-1\" role='dialog' aria-labelledby='cartViewerLabel' aria-hidden='true' >\n");
      out.write("                <div class='modal-dialog'>\n");
      out.write("                    <div class='modal-content'>\n");
      out.write("                        <div class='modal-header'>\n");
      out.write("                            <button type='button' class=\"close\" data-dismiss='modal' aria-hidden='true'>\n");
      out.write("                                &times;\n");
      out.write("                            </button>\n");
      out.write("                            <h4 class='modal-title' id='cartViewerLabel'>\n");
      out.write("                                Your Cart\n");
      out.write("                            </h4>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"modal-body\">\n");
      out.write("                            <table class=\"table table-hover\">\n");
      out.write("                                <thead>\n");
      out.write("                                    <tr>\n");
      out.write("                                        <th>Medicine Name</th>\n");
      out.write("                                        <th>Quantity Ordered</th>\n");
      out.write("                                        <th>Sub Total</th>\n");
      out.write("                                    </tr>\n");
      out.write("                                </thead>\n");
      out.write("\n");
      out.write("                                ");

                                    double grossprice = 0;
                                    if (request.getSession().getAttribute("cart") != null) {

                                        HashMap<POJOs.Medicine, Integer> cart = (HashMap<Medicine, Integer>) request.getSession().getAttribute("cart");
                                        Iterator<POJOs.Medicine> keys = cart.keySet().iterator();

                                        while (keys.hasNext()) {
                                            POJOs.Medicine m = keys.next();
                                            Integer qty = cart.get(m);
                                            double subtotal = m.getMedicinePrice() * qty;
                                
      out.write("\n");
      out.write("                                <tr>\n");
      out.write("                                    <td>");
      out.print(m.getMedicineName());
      out.write("</td>\n");
      out.write("                                    <td>");
      out.print(qty);
      out.write("</td>\n");
      out.write("                                    <td>Rs.");
      out.print(subtotal);
      out.write("0</td>\n");
      out.write("\n");
      out.write("                                    ");

                                        grossprice += subtotal;
                                    
      out.write("\n");
      out.write("                                </tr>\n");
      out.write("                                ");
}
                                    }
                                
      out.write("\n");
      out.write("                            </table>\n");
      out.write("                            <p><strong>Gross Price is :- </strong>Rs.");
      out.print(grossprice);
      out.write("0</p>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"modal-footer\">\n");
      out.write("                            <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Close\n");
      out.write("                            </button>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <!-- End Modal -->\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
