package com.wh.controller;

import com.wh.bean.Result;
import com.wh.bean.User;
import com.wh.service.UserService;
import com.wh.util.CaptchaUtil;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("user")
@CrossOrigin    //允许跨域
@Slf4j
@RestController
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 用户登录
     */
    @PostMapping("login")
    public Result login(@RequestBody User user){
        Result result = new Result();
        try {
            userService.checkLogin(user);
            result.setMsg("登录成功！");
        } catch (Exception e) {
            result.setMsg("登录失败！");
            result.setState(false);
        }
        return result;
    }

    /**
     * 用户注册
     * @param code
     * @param user
     * @return
     */
    @PostMapping("register")
    public Result register(String code, @RequestBody User user, HttpServletRequest request){
        Result result = new Result();
        String realCode = (String) request.getServletContext().getAttribute("key");
        try {
            if (realCode.equalsIgnoreCase(code)){
                userService.register(user);
                result.setMsg("注册成功!");
            }else {
                throw new RuntimeException("验证码错误！");
            }
        }catch (Exception e ){
            e.printStackTrace();
            result.setMsg(e.getMessage()).setState(false);
        }
        return result;
    }


    /**
     * 生成验证码
     * @throws IOException
     */
    @GetMapping("/getImage")
    public Map getImage(HttpServletRequest request) throws IOException {
        HashMap<String, String> result = new HashMap<>();

        //获取验证码并传入到session中
        CaptchaUtil util = CaptchaUtil.Instance();
        String code = util.getString();

        String key = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        request.getServletContext().setAttribute("key",code);

        BufferedImage image = util.getImage();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(image,"png",bos);
        String string = Base64Utils.encodeToString(bos.toByteArray());

        result.put("key",key);
        result.put("image",string);

        return result;
    }


    @RequestMapping("lackPermission")
    public Result lackPermission(){
        Result result = new Result();
        result.setState(false).setMsg("没有访问权限");
        return result;

    }

}
