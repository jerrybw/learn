package com.jerry.crud.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 向博文
 * @date 2018年7月5日
 */
@Controller
@RequestMapping("/test")
public class TestController {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@ResponseBody
	@RequestMapping("/json")
	public Object testJson(@RequestBody Map<String,Object> params) {
		Map<String, Object> res = new HashMap<>();
		Set<Entry<String,Object>> entrySet = params.entrySet();
		for (Entry<String, Object> entry : entrySet) {
			logger.info("key "+entry.getKey());
			logger.info("value "+entry.getValue());
		}
		res.put("code", "200");
		return res;
	}

}
