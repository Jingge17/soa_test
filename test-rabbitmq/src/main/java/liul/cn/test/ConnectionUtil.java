package liul.cn.test;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtil {
	 public static Connection getConnection() throws Exception {
	        //�������ӹ���
	        ConnectionFactory factory = new ConnectionFactory();
	        //���÷����ַ
	        factory.setHost("192.168.197.60");
	        //�˿�
	        factory.setPort(5672);//5672
	        //�����˺���Ϣ���û��������롢vhost
	        factory.setVirtualHost("/rbmq_test");
	        factory.setUsername("guest");
	        factory.setPassword("guest");
	        // ͨ�����̻�ȡ����
	        Connection connection = factory.newConnection();
	        return connection;
	    }
}
