package com.siva.poc.hazelcast;

import com.siva.poc.hazelcast.apps.hazelcast.HazelcastApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HazelcastApplication.class)
public class HazelcastApplicationTests {

	@Test
	public void contextLoads() {
	}

}
