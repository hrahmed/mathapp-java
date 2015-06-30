package com.client.math;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mathapp.utilities.JsonHelperForMathApp;
import com.mathapp.utilities.MathResponseData;

/**
 * Servlet implementation class MathComplexServlet
 */
public class MathComplexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public MathComplexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public enum Oper{
    	mean,median,mode,barcode,python,flush;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		MathWSClient wsClient = new MathWSClient();
		
		switch (Oper.valueOf(request.getParameter("operation"))){
		case mean: request.setAttribute("oper", "MEAN");
		break;
		case median: request.setAttribute("oper", "MEDIAN");
		break;
		case mode: request.setAttribute("oper", "MODE");
		break;
		case barcode: request.setAttribute("oper", "BARCODE");
		break;
		case python: request.setAttribute("oper", "PYTHON");
		break;
		case flush: request.setAttribute("oper", "FLUSH");




		}
		request.setAttribute("vs", request.getParameter("values"));
	    
	    String responseString = wsClient.getMathComplexResult(request.getParameter("operation"), 
	    														request.getParameter("values"));
    
	    request.setAttribute("complexresult", responseString);
	    
	    RequestDispatcher rd = getServletContext().getRequestDispatcher("/operation.jsp");
	    rd.forward(request, response);
	}
	    
	    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}


}
