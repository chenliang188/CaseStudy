package autologin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginControl extends HttpServlet
{
    /**
     * The doGet method of the servlet. <br>
     * 
     * This method is called when a form has its tag value method equals to get.
     * 
     * @param request
     *            the request send by the client to the server
     * @param response
     *            the response send by the server to the client
     * @throws ServletException
     *             if an error occurred
     * @throws IOException
     *             if an error occurred
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        this.doPost(request, response);
    }

    /**
     * The doPost method of the servlet. <br>
     * 
     * This method is called when a form has its tag value method equals to
     * post.
     * 
     * @param request
     *            the request send by the client to the server
     * @param response
     *            the response send by the server to the client
     * @throws ServletException
     *             if an error occurred
     * @throws IOException
     *             if an error occurred
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String savetime = request.getParameter("saveTime");
        if (LoginService.login(username, password))
        {
            if (null != savetime && !savetime.isEmpty())
            {
                int seconds = 7 * 24 * 60 * 60;
                Cookie cookie = new Cookie("user", username + "==" + password);
                cookie.setMaxAge(seconds);
                response.addCookie(cookie);
            }
            request.setAttribute("username", username);
            request.getRequestDispatcher("/main.jsp")
                    .forward(request, response);
        }
        else
        {
            request.getRequestDispatcher("/index.jsp").forward(request,
                    response);
        }
    }
}
