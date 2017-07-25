<%-- 
    Document   : our_blog
    Created on : Dec 18, 2015, 6:03:29 PM
    Author     : codeguy
--%>

<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.hibernate.Session"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- bootstrap css-->
        <link rel="stylesheet" href="bootstrap-3.3.6-dist/css/bootstrap.min.css"/>

        <script src="js/jquery-2.1.4.min.js"></script>
        <script src="bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
        
        <script>
            function searchBlog(search){
                window.location.replace('our_blog.jsp?search='+search);
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
            <h2 style="padding-top: 50px;">Our Blog <small>Read what is happening in our Medical Care center!</small></h2>

            <form for="form">
                <div class="input-group" style="margin-top:  50px; margin-bottom: 50px;">
                    <input type="text" placeholder="Search Blog - Enter Blog Topic" class="form-control" id="searchbox"/>
                    <span class="input-group-btn">
                        <button class="btn btn-default" type="button" onclick="searchBlog(document.getElementById('searchbox').value)">
                            Search Blog
                        </button>
                    </span>
                </div>
            </form>
            <div class="row">
                <%  Session s = Connection.Connector.getSessionFactory().openSession();
                    List<POJOs.Blog> blogList = s.createCriteria(POJOs.Blog.class).list();
                    if (request.getParameter("search") != null) {
                        System.out.println(request.getParameter("search"));
                        blogList = s.createCriteria(POJOs.Blog.class).add(Restrictions.like("blogTitle", request.getParameter("search") + "%")).list();
                    }
                    int elementSize = blogList.size();
                    System.out.print(elementSize);
                      int counter = 0;
                      for (POJOs.Blog b : blogList) {%>
                <div class="col-lg-4">
                    <div class="thumbnail">
                        <%
                            Iterator<POJOs.BlogImages> blogImages = b.getBlogImageses().iterator();
                            POJOs.BlogImages blogimage = blogImages.next();
                            counter += 1;
                        %>
                        <img src="<%=blogimage.getBlogImage()%>" alt="Blog image">
                        <div class="caption">
                            <h3><%=b.getBlogTitle()%></h3>
                            <p><%=b.getBlogSummary()%></p>
                            <p><a href="blogpage.jsp?blog=<%=b.getIdblog()%>" class="btn btn-primary" role="button">More Details</a> 
                        </div>
                    </div>
                </div>
                <%  if (counter == 3) {
                            counter = 0;%>
            </div><div class="row">
                <%}
                    }
                %>
            </div>
    </body>
</html>
