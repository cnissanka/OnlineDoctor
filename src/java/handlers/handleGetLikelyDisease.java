/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import POJOs.Symptom;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author codeguy
 */
@WebServlet(name = "handleGetLikelyDisease", urlPatterns = {"/handleGetLikelyDisease"})
public class handleGetLikelyDisease extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            
            String symtoms [] = request.getParameter("symtomlist").split("#");
            Session s = Connection.Connector.getSessionFactory().openSession();
           
            List <POJOs.Symptom> symptomObjs = s.createCriteria(POJOs.Symptom.class).list();
            symptomObjs.clear();
           for (String sy : symtoms){
               if (sy.equals("")){
                   break;
               }
               symptomObjs.add((Symptom) s.createCriteria(POJOs.Symptom.class).add(Restrictions.eq("symptomName", sy)).uniqueResult());
           }
           
           HashMap <POJOs.Diesease, Integer> result = new HashMap<>();
           
           List <POJOs.Diesease> allDiseases = s.createCriteria(POJOs.Diesease.class).list();
           for (POJOs.Diesease d : allDiseases){
               result.put(d, 0);
           }
           
           for (POJOs.Symptom sy : symptomObjs){
               Iterator <POJOs.DieseaseHasSymptom> currentDiseases = sy.getDieseaseHasSymptoms().iterator();
               while (currentDiseases.hasNext()){
                   POJOs.DieseaseHasSymptom cRule = currentDiseases.next();
                   POJOs.Diesease disease = cRule.getDiesease();
                  result.put(disease, (result.get(disease)+1));
               }
           }
           String dataset = "";
           for (POJOs.Diesease d : allDiseases){
               dataset += d.getDieseaseName() + ";" + result.get(d) + "#";
           }
           
           out.write(dataset);
            
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
