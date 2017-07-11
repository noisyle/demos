package com.noisyle.demo.j2ee.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(asyncSupported = true, 
    name = "myAsyncServlet", 
    urlPatterns = {"/async"}, 
    initParams = {@WebInitParam(name = "timeout", value = "10000")})
public class MyAsyncServlet extends HttpServlet {
    private static final long serialVersionUID = -2616778759139448382L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doAsync(request, response);
    }

    private void doAsync(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final AsyncContext ctx = req.startAsync();
        final String timeout = getServletConfig().getInitParameter("timeout");
        ctx.setTimeout(Integer.valueOf(timeout));
        ctx.addListener(new MyAsyncListener());
        ctx.start(new Runnable() {
            @Override
            public void run() {
                PrintWriter out = null;
                try {
                    ctx.getResponse().setContentType("text/html; encoding=utf-8");
                    out = ctx.getResponse().getWriter();
                    int t = Integer.valueOf(req.getParameter("t"));
                    out.write("Async Support: " + ctx.getRequest().isAsyncSupported() + "<br />");
                    out.write("Async Timeout: "+timeout+"ms<br />");
                    out.write("Loop Count: " + ctx.getRequest().getParameter("t") + "<br />");
                    out.flush();
                    for (int i = 0; i < t; i++) {
                        Thread.sleep(1000);
                        out.write("[Servlet] Loop " + i + "<br />");
                        out.flush();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (out != null) {
                        out.write("[Servlet] Loop Finish!<br />");
                        out.flush();
                    }
                    ctx.complete();
                }
            }
        });
    }
}
