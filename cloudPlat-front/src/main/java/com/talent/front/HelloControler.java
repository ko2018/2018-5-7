package com.talent.front;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.talent.front.dao.SysUserDao;
import com.talent.front.entity.Msg;
import com.talent.front.entity.SysUser;

@Controller
public class HelloControler {
	private static final Logger logger = LoggerFactory.getLogger(HelloControler.class);

	@Autowired
	HelloService helloService;
	@Autowired
	SysUserDao sysUserDao;

	@Autowired
	RedisTemplate<String, String> redisTemplate;

	@Autowired
	private ApplicationContext ctx;

	@Value("${server.port}")
	String port;

	@RequestMapping("/accessDenied")
	@ResponseBody
	public String accessDenied(Model model) {
		Msg msg = new Msg("测试标题", "测试内容", "额外信息，只对管理员显示");
		model.addAttribute("msg", msg);
		return "登录失败";
	}

	@RequestMapping("/insert")
	@ResponseBody
	public String insert(Model model) {
		Msg msg = new Msg("测试标题", "测试内容", "额外信息，只对管理员显示");
		model.addAttribute("msg", msg);
		return "需要权限";
	}
	
   @RequestMapping(value = "uploadtest", method = RequestMethod.GET)
    public String upload() {
        return "uploadtest";
    }

	// @RequestMapping("/login")
	// @ResponseBody
	// public String loginto(Model model, @RequestParam(defaultValue = "") String
	// username,
	// @RequestParam(defaultValue = "") String password, HttpServletRequest request)
	// {
	// logger.info("EEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
	// if (username == null || username.isEmpty() || password == null ||
	// password.isEmpty()) {
	// return "用户名或密码不能为空";
	// }
	// // UsernamePasswordAuthenticationToken authRequest = new
	// // UsernamePasswordAuthenticationToken(username, password);
	//
	// try {
	// // Authentication authentication =
	// // authenticationManager.authenticate(authRequest);
	// // SecurityContextHolder.getContext().setAuthentication(authentication);
	// // HttpSession session = request.getSession();
	// // session.setAttribute("SPRING_SECURITY_CONTEXT",
	// // SecurityContextHolder.getContext()); // 这个非常重要，否则验证后将无法登陆
	//
	// return "登录成功！";
	// } catch (AuthenticationException ex) {
	// return "用户名或密码错误";
	// } // end catch
	// }

	// @RequestMapping(value = "/login")
	// public String login() {
	// System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
	// return "login";
	// }

	@RequestMapping(value = "/fwp123")
	@ResponseBody
	public String accessDenied() {
		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		return "请求错误";
	}

	// @RequestMapping(value = "/user")
	// @ResponseBody
	// public String user() {
	// return "用户信息";
	// }

	@RequestMapping(value = "/hi")
	public String hi(@RequestParam String name) {
		SysUser sysUser = sysUserDao.selectDtoByPrimaryKey("1");
		logger.info(sysUser.getNickname());
		System.out.println("HHHHHHHHHHHHHHHHH: " + port);
		System.out.println("MMMMMMMMMMMMMMMMMMMM" + ctx.getEnvironment().getProperty("eureka.instance.hostname"));
		System.out.println("NNNNNNNNNNNNNNNNNNNNNNN" + ctx.getEnvironment().getProperty("spring.datasource.url"));

		ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();

		// 数据插入测试：
		opsForValue.set("ffffffff", "ppppppppppppppp");
		String valueFromRedis = opsForValue.get("ffffffff");
		logger.info("redis value after set: {}", valueFromRedis);

		return helloService.hiService(name);
	}

	@RequestMapping(value = "/hidcs")
	public String hidcs(@RequestParam String name) {
		System.out.println("FFFFFFFFFF: " + port);
		System.out.println("MMMMMMMMMMMMMMMMMMMM" + ctx.getEnvironment().getProperty("eureka.instance.hostname"));
		System.out.println("PPPPPPPPPPPPPPPPPPP" + ctx.getEnvironment().getProperty("spring.datasource.url"));

		return helloService.hiServiceDcs(name);
	}

	@RequestMapping(value = "/hifwp")
	public String hifwp(@RequestParam String name) {
		System.out.println("FFFFFFFFFF11111111: " + port);
		System.out.println("MMMMMMMMMMMMMMMMMMMM22222" + ctx.getEnvironment().getProperty("eureka.instance.hostname"));
		System.out.println("PPPPPPPPPPPPPPPPPPP333" + ctx.getEnvironment().getProperty("spring.datasource.url"));

		return helloService.hifwpService(name);
	}

}