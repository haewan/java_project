package servlets;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Enumeration;

//servlet-api
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//log4j
import org.apache.log4j.Logger;

@WebServlet(value="/Log4jTestServlet")
public class Log4jTestServlet extends HttpServlet 
  {
	private static final long serialVersionUID = 1L;
        private static Logger logger = null;

        public void init() throws ServletException {
         logger= Logger.getLogger(Log4jTestServlet.class);   
        }

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
                        String logLevel=request.getParameter("logLevel");
                        String logMessage=request.getParameter("logMessage");
			out.println("<%@ page language=\"java\" contentType=\"text/html; charset=UTF8\" %>");
                        out.println("<html><head><title>Log4j Logging Demo</title></head><body>");
                        if(logLevel.equals("TRACE"))
                           {
                             out.println("["+logLevel+"] "+logMessage+" (logged inside log file).");
                             logger.trace(logMessage);
                           }
                        if(logLevel.equals("DEBUG"))
                           {
                             out.println("["+logLevel+"] "+logMessage+" (logged inside log file).");
                             logger.debug(logMessage);
                           }
                        if(logLevel.equals("INFO"))
                           {
                             out.println("["+logLevel+"] "+logMessage+" (logged inside log file).");
                             logger.info(logMessage);
                           }
                        if(logLevel.equals("WARN"))
                           {
                             out.println("["+logLevel+"] "+logMessage+" (logged inside log file).");
                             logger.warn(logMessage);
                           }


		out.println("<hr/>");
		out.println("<h1>Headers</h1>");
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			out.print("Header Name: <em>" + headerName);
			String headerValue = request.getHeader(headerName);
			out.print("</em>, Header Value: <em>" + headerValue);
			out.println("</em><br/>");
		}


		out.println("<hr/>");

		out.println("request content-length: " + request.getHeader("content-length"));



                        out.println("<BR><BR><a href=\"log4j_test.jsp\"> Want a different logging level? </a></body></html>");
		     } 
                 catch (Exception e) 
                     {
			e.printStackTrace();
		     }
	}
}

