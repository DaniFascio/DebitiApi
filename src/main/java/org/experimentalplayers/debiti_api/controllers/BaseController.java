package org.experimentalplayers.debiti_api.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class BaseController {


//	@Autowired
//	JwtTokenUtil jwtTokenUtil;

    @Autowired
    HttpServletRequest request;


}
