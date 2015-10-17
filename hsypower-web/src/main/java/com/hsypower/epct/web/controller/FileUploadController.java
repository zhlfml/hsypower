package com.hsypower.epct.web.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class FileUploadController extends BaseController {

	@ResponseBody
	@RequestMapping(value = "/fileupload", method = RequestMethod.POST)
	public String doFileupload(
			@RequestParam("imgFile") CommonsMultipartFile file,
			HttpServletRequest request) throws IOException {
		JSONObject json = new JSONObject();
		json.put("error", 0);
		json.put("url", request.getContextPath() + uploadFile(file));

		return json.toString();
	}
}
