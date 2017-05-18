package com.eumji.rabbitmq;

import com.eumji.rabbitmq.vo.RPCClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitSpringRpcApplication.class)
public class RabbitSpringRpcApplicationTests {
	@Autowired
	private RPCClient rpcClient;
	@Test
	public void contextLoads() {
	}

	@Test
	public void send(){
		rpcClient.send();
	}

}
