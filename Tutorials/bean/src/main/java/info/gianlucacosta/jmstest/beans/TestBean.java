package info.gianlucacosta.jmstest.beans;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.LocalBean;
import javax.ejb.MessageDriven;
import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


@MessageDriven(name = "TestBean", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/myJmsTest/MyQueue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })


@Stateless
public class TestBean implements MessageListener {

        private final static Logger LOGGER = Logger.getLogger(TestBean.class.toString());

        /**
         * @see MessageListener#onMessage(Message)
         */
        public void onMessage(Message rcvMessage) {
            TextMessage msg = null;
            try {
                if (rcvMessage instanceof TextMessage) {
                    msg = (TextMessage) rcvMessage;
                    LOGGER.info("Received Message from queue: " + msg.getText());
                } else {
                    LOGGER.warning("Message of wrong type: " + rcvMessage.getClass().getName());
                }
            } catch (JMSException e) {
                throw new RuntimeException(e);
            }
        }
    }
