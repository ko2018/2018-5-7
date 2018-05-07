package com.talent.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

	@Autowired
	RestTemplate restTemplate;

	public String hiService(String name) {
		return restTemplate.getForObject("http://service-mds/hi?name=" + name, String.class);
	}

	public String hiServiceDcs(String name) {
		return restTemplate.getForObject("http://service-dcs/hi?name=" + name, String.class);
	}

	public String hifwpService(String name) {
		return restTemplate.getForObject("http://service-mds/hifwp?name=" + name, String.class);
	}

}
