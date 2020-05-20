/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demos.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author oracle
 */
@WebServlet(name = "ErrorHandler", urlPatterns = {"/errors"})
public class ErrorHandler extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            Throwable ex = (Throwable)request.getAttribute("javax.servlet.error.exception");
            String errorMessage = (String)request.getAttribute("javax.servlet.error.message");
            Integer status = (Integer)request.getAttribute("javax.servlet.error.status_code");
            String message = "Error: ";
            switch(status){
                case HttpServletResponse.SC_INTERNAL_SERVER_ERROR:
                    request.getServletContext().log(errorMessage,ex);
                    message += errorMessage;
                    message += ". - Please contact server administrator.";
                    break;
                case HttpServletResponse.SC_NOT_FOUND:
                    message += "Requested page not found";
                    break;
            }
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<link rel='stylesheet' type='text/css' href='/pm/css/pm.css'>");
            out.println("<title>Error Page</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<header class='header'>Errors</header>");
            out.println("<nav class='nav'><a href='/pm'>Home</a></nav>");
            out.println("<section class='content'>");
            out.println("<div class='error'>"+message+"</div>");
            out.println("</section>");
            out.println("<footer class='footer'>Click on the Home page link to start over</footer>");
            out.println("</body>");
            out.println("</html>");
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
