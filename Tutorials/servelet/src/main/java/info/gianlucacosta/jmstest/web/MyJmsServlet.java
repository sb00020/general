package info.gianlucacosta.jmstest.web;

import javax.annotation.Resource;
import javax.jms.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
 
@WebServlet(urlPatterns = "/send")
public class MyJmsServlet extends HttpServlet {
    @Resource(lookup = "java:/myJmsTest/MyConnectionFactory")
    ConnectionFactory connectionFactory;

    @Resource(lookup = "java:/myJmsTest/MyQueue")
    Destination destination;


    @Override
    protected void doGet(
      HttpServletRequest req,
      HttpServletResponse resp
    ) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        try {
            //Authentication info can be omitted if we are using in-vm
            QueueConnection connection = (QueueConnection)
              connectionFactory.createConnection();

            try {
                QueueSession session =
                  connection.createQueueSession(
                    false,
                    Session.AUTO_ACKNOWLEDGE
                  );

                try {
                    MessageProducer producer =
                      session.createProducer(destination);

                    try {
                        TextMessage message =
                          session.createTextMessage(
                            "Hello, world! ^__^"
                          );

                        producer.send(message);

                        writer.println(
                          "Message sent! ^__^"
                        );
                    } finally {
                        producer.close();
                    }
                } finally {
                    session.close();
                }

            } finally {
                connection.close();
            }

        } catch (Exception ex) {
            ex.printStackTrace(writer);
        }
    }
}