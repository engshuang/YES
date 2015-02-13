package net.yes.mom;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TemporaryQueue;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Sender implements Runnable {
	private String url;
	private String user;
	private String password;

	public Sender(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}

	public void run() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				user, password, url);
		try {
			Connection connection = connectionFactory.createConnection();

			// The Retailer's session is non-trasacted.
			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);
			Destination vendorOrderQueue = session.createQueue("Sender");
			TemporaryQueue retailerConfirmQueue = session
					.createTemporaryQueue();

			MessageProducer producer = session.createProducer(vendorOrderQueue);
			MessageConsumer replyConsumer = session
					.createConsumer(retailerConfirmQueue);

			connection.start();

			for (int i = 0; i < 5; i++) {
				MapMessage message = session.createMapMessage();
				message.setString("msg", "测试信息");
				message.setJMSReplyTo(retailerConfirmQueue);
				producer.send(message);
				System.out.println("send msg");

				MapMessage reply = (MapMessage) replyConsumer.receive();
				if (reply.getBoolean("Accepted")) {
					System.out.println(reply.getString("msg"));
				} else {
					System.out.println("return fail");
				}
			}

			// Send a non-MapMessage to signal the end
			producer.send(session.createMessage());

			replyConsumer.close();
			connection.close();

		} catch (JMSException e) {
			e.printStackTrace();
		}

	}

}
