package com.client.math;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.client.math.MathClientServlet.Oper;
import com.mathapp.utilities.JsonHelperForMathApp;
import com.mathapp.utilities.MathResponseData;
import com.mathapp.utilities.ServletUtilities;

/**
 * Servlet implementation class MathNodeServlet
 */
public class MathNodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MathNodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		MathWSClient wsClient = new MathWSClient();
		
		//request.setAttribute("operation", request.getParameter("operation"));
	    request.setAttribute("v1", request.getParameter("value1"));
	    request.setAttribute("v2", request.getParameter("value2"));
	    
	    String responseString = wsClient.getMathNodeSimple(request.getParameter("operation"),
				ServletUtilities.getIntParameter(request, "value1", 0),
				ServletUtilities.getIntParameter(request, "value2", 0));
	    
//		XmlHelperForMathApp xmlHelperForMathApp = new XmlHelperForMathApp();
		JsonHelperForMathApp jsonHelperForMathApp = new JsonHelperForMathApp();

		MathResponseData responseData = jsonHelperForMathApp.getMathResponse(responseString);
		
		//System.out.println("Result is: " + responseData.getResult());
		//System.out.println("Status is: " + responseData.getStatus());
	    
	    
	    request.setAttribute("result", responseData.getResult());
	    request.setAttribute("status", responseData.getStatus());
	    
	    RequestDispatcher rd = getServletContext().getRequestDispatcher("/operation.jsp");
	    rd.forward(request, response);
	    
	   // response.getWriter().write("<html><body>Hello from doGet!</body></html>");	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MathWSClient wsClient = new MathWSClient();
		
		//request.setAttribute("operation", request.getParameter("operation"));
	    request.setAttribute("v1", request.getParameter("value1"));
	    request.setAttribute("v2", request.getParameter("value2"));
	    
	    String responseString = wsClient.getMathNodeSimple(request.getParameter("operation"),
				ServletUtilities.getIntParameter(request, "value1", 0),
				ServletUtilities.getIntParameter(request, "value2", 0));
	    
//		XmlHelperForMathApp xmlHelperForMathApp = new XmlHelperForMathApp();
		JsonHelperForMathApp jsonHelperForMathApp = new JsonHelperForMathApp();

		MathResponseData responseData = jsonHelperForMathApp.getMathResponse(responseString);
		
		//System.out.println("Result is: " + responseData.getResult());
		//System.out.println("Status is: " + responseData.getStatus());
	    
	    
	    request.setAttribute("noderesult", responseData.getResult());
	    request.setAttribute("status", responseData.getStatus());
	    
	    RequestDispatcher rd = getServletContext().getRequestDispatcher("/operation.jsp");
	    rd.forward(request, response);
	}

}
