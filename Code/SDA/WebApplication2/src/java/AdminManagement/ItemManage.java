/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AdminManagement;

import Users.Admin;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author usman
 */
public class ItemManage extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            if(request.getParameter("editcurritem") != null){
                
                String name = request.getParameter("name");
                String price = request.getParameter("price");
                String url = request.getParameter("url");
                String desc = request.getParameter("desc");
                String stock = request.getParameter("stock");
                String status = request.getParameter("availablestatus");
                String r = Admin.editItem(name, price, url, desc, stock, status);
                if(r.equals("success"))
                {
                    request.setAttribute("success", "Item updated successfully!");
                    RequestDispatcher rd = request.getRequestDispatcher("EditItemPage.jsp");
                    rd.include(request, response);
                }
                else{
                    request.setAttribute("error", r);
                    RequestDispatcher rd = request.getRequestDispatcher("EditItemPage.jsp");
                    rd.include(request, response);
                }
            }
            else if(request.getParameter("addnewitem") != null){
                
                String name = request.getParameter("name");
                String price = request.getParameter("price");
                String url = request.getParameter("url");
                String desc = request.getParameter("desc");
                String stock = request.getParameter("stock");
                String status = request.getParameter("availablestatus");
                
                String r = Admin.addItem(name, price, url, desc, stock, status);
                if(r.equals("success"))
                {
                    request.setAttribute("success", "Item added successfully!");
                    RequestDispatcher rd = request.getRequestDispatcher("AddItemPage.jsp");
                    rd.include(request, response);
                }
                else{
                    request.setAttribute("error", r);
                    RequestDispatcher rd = request.getRequestDispatcher("AddItemPage.jsp");
                    rd.forward(request, response);
                }
            }
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
