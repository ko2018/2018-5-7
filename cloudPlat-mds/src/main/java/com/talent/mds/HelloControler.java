package com.talent.mds;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.talent.mds.dao.SysUserDao;
import com.talent.mds.entity.SysUser;
import com.talent.mds.util.redis.CloudPlatRedisTemplate;

@RestController
public class HelloControler {
	private static final Logger logger = LoggerFactory.getLogger(HelloControler.class);

	@Autowired
	HelloService helloService;

	@Autowired
	private ApplicationContext ctx;

	@Autowired
	private SysUserDao sysUserDao;

	@Autowired
	private CloudPlatRedisTemplate cloudPlatRedisTemplate;

	@Value("${server.port}")
	String port;

	@RequestMapping(value = "/hi")
	public String hi(@RequestParam String name) {
		logger.info("EEEEEEEEEEEEEEEEEEEEEEEEEQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ");
		cloudPlatRedisTemplate.set("fwp", "AAAAAAAAAAAAAAAA");
		logger.info("EEEEEEEEEEEEEEEEEEEEEEEEE" + cloudPlatRedisTemplate.get("fwp"));
		System.out.println("AAAAAAAAA: " + port);
		System.out.println("BBBBBBBBBBBBBBBBBB" + ctx.getEnvironment().getProperty("eureka.instance.hostname"));
		System.out.println("CCCCCCCCCCCCCCCCCC" + ctx.getEnvironment().getProperty("spring.datasource.url"));

		SysUser sysUser = sysUserDao.findUser("fwp");
		if (sysUser != null) {
			System.out.println(sysUser.getNickname());
		} else {
			System.out.println("DDDDDDDDDDDDDDDDDDD: " + port);
		}

		return helloService.hiService(name);
	}

	@RequestMapping("/hifwp")
	public String home(@RequestParam String name) {
		return "hi FFFFFFFFWWWWWWWWWWWWWWWPPPPPPPPPPPPPP" + name + ",i am from port:" + port;
	}

}