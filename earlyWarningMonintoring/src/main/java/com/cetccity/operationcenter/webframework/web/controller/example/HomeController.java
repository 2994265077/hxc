package com.cetccity.operationcenter.webframework.web.controller.example;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@Api(tags = "根路径自动跳转swagger")
public class HomeController {

    @ApiOperation(value = "根路径自动跳转swagger",notes = "根路径自动跳转swagger", produces = "application/json")
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public void home(HttpServletResponse response) throws IOException {
//        return "redirect:/swagger-ui.html";
        response.sendRedirect("/document.html");
    }
}
