/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package EmployeeOrdering;

import Reservation.Table;
import Users.Employee;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author usman
 */
public class AssignTable extends HttpServlet {

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
            
            
            if(request.getParameter("assign") != null)
            {
                if(Employee.tableList == null)
                    Employee.getTablesFromDB();
               String check = request.getParameter("tableid");
                if(check.isEmpty()){
                    request.setAttribute("error", "No table id entered"); 
                    RequestDispatcher dispatcher = request.getRequestDispatcher("AssignTablePage.jsp");
                    dispatcher.forward(request, response);
                }
                
                else
                {
                    int tableID = Integer.valueOf(request.getParameter("tableid"));

                    int index = Integer.valueOf(request.getParameter("index"));
                    String r = Employee.assignTable(tableID, index);
                    if(r.equals("success"))
                    {
                        request.setAttribute("assigned", "Successfully assigned table to customer!"); 
                    }
                    else{
                        request.setAttribute("error", r); 
                    }

                    RequestDispatcher dispatcher = request.getRequestDispatcher("AssignTablePage.jsp");
                    dispatcher.forward(request, response);
                }
            }
            
            else if(request.getParameter("decline") != null){
                int index = Integer.valueOf(request.getParameter("index"));
                
                String r = Employee.declineTableRequest(index);
                if(r.equals("success"))
                {
                    request.setAttribute("assigned", "Successfully declined table request!"); 
                }
                else{
                    request.setAttribute("error", r); 
                }
                RequestDispatcher dispatcher = request.getRequestDispatcher("AssignTablePage.jsp");
                dispatcher.forward(request, response);
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
