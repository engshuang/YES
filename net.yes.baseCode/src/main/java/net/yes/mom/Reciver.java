package net.yes.mom;

import java.util.Random;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Reciver implements Runnable{
	private String url;
	private String user;
	private String password;
	
	public Reciver(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}

	public void run() {

		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, url);
		Session session = null;
		Destination senderQueue;
		try {
			Connection connection = connectionFactory.createConnection();

			session = connection.createSession(true, Session.SESSION_TRANSACTED);
			senderQueue = session.createQueue("Sender");
			MessageConsumer consumer = session.createConsumer(senderQueue);
			
			connection.start();
			
			while (true) {
				try {
					Message message = consumer.receive();
					MessageProducer producer = session.createProducer(message
							.getJMSReplyTo());
					MapMessage senderMessage;

					if (message instanceof MapMessage) {
						senderMessage = (MapMessage) message;
					} else {
						// End of Stream
//						producer.send(session.createMessage());
						session.commit();
						producer.close();
						break;
					}

					if (new Random().nextInt(3) == 0) {
						throw new JMSException("Simulated Database Error.");
					}

					MapMessage outMessage = session.createMapMessage();
					String msg = senderMessage.getString("msg") + " 返回结果";
					outMessage.setString("msg", msg);
					outMessage.setBoolean("Accepted", true);

					producer.send(outMessage);
					System.out.println("Reciver return");
					session.commit();
					System.out.println("Reciver: committed transaction");
					producer.close();
				} catch (JMSException e) {
					System.out.println("Reciver: JMSException Occured: "
							+ e.getMessage());
					e.printStackTrace();
					session.rollback();
					System.out.println("Reciver: Rolled Back Transaction.");
				}
			}
			connection.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	
		
	}

}
