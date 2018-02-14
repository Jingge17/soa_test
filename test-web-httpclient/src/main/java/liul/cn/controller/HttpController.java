package liul.cn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;

import liul.cn.entity.User;
import liul.cn.service.ApiHttpService;

@Controller
@Scope("prototype")
@RequestMapping("user")
public class HttpController {
	@Autowired
	ApiHttpService httpService;
	@Value("${TEST_WEB_ALL_USERS}")
	private String TEST_WEB_ALL_USERS;
	ObjectMapper mapper = new ObjectMapper();

	@RequestMapping(value = "users", method = RequestMethod.GET)
	public String toUsers() {
      return "allusers";
	}

	@RequestMapping(value = "getallusers", method = RequestMethod.GET)
	public ResponseEntity<MappingJacksonValue> getAllUsers(@RequestParam(value="callback",required=false)String callback) {
		System.out.println("\n\n"+TEST_WEB_ALL_USERS+"\n\n");
      try {
		String jsonData=httpService.doGet(TEST_WEB_ALL_USERS);
		if (null==jsonData) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		MappingJacksonValue jValue=new MappingJacksonValue(mapper.readValue(jsonData, mapper.getTypeFactory().constructCollectionType(List.class,User.class)));
		jValue.setJsonpFunction(callback);
		return ResponseEntity.ok(jValue);
	} catch (Exception e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
}
}