package com.liujie.study.springboot.controller;

import com.liujie.study.springboot.domain.User;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {

    private final static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "This is springboot index";
    }

    @RequestMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("title", "springboot");
        return "hello";
    }

    @RequestMapping("/test")
    public String test() {
        return "sub/test";
    }

    /**
     * 通过 HttpServletResponse 输出图片
     */
    @RequestMapping("/image")
    public void image1(HttpServletResponse response) throws IOException {
        String image = "";
        response.setContentType("image/jpeg");
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(image.getBytes());
        outputStream.flush();
        outputStream.close();
    }

    @RequestMapping(value = "/image", produces = {MediaType.IMAGE_JPEG_VALUE})
    @ResponseBody
    public byte[] image2() {
        String image = "";
        return image.getBytes();
    }

    /**
     * 通过 HttpServletRequest 或 @RequestHeader 获取 header
     */
    @RequestMapping("/header")
    @ResponseBody
    public HashMap<String, String> header(HttpServletRequest request, @RequestHeader(value = "user-agent") String userAgent) {
        HashMap<String, String> headers = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String value = request.getHeader(headerName);
            headers.put(headerName, value);
        }

        logger.info(headers.toString());
        logger.info(userAgent);
        System.out.println(headers.toString());
        return headers;
    }

    /**
     * 通过 HttpServletRequest 获取其他请求类参数，如IP
     */
    @RequestMapping("/request")
    @ResponseBody
    public HashMap<String, String> request(HttpServletRequest request) {
        HashMap<String, String> requests = new HashMap<>();

        requests.put("Method", request.getMethod());
        requests.put("QueryString", request.getQueryString());
        requests.put("RequestURI", request.getRequestURI());
        requests.put("RequestURL", request.getRequestURL().toString());
        requests.put("RemoteAddr", request.getRemoteAddr());

        logger.info(requests.toString());
        return requests;
    }

    /**
     * 普通方式获取 GET,POST 参数，不提供参数是null，提供参数无值为空字符串
     */
    @RequestMapping(value = "/getsimple", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String getSimple(String text1, String text2) {
        return text1 + "/" + text2;
    }

    /**
     * 通过注解获取 GET,POST 参数
     * 和普通方式区别的意义在于：可以进行映射、默认值、参数检查等操作
     * RequestParam 注解默认required=true
     */
    @RequestMapping(value = "/get", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String get(@RequestParam("text1") String text1, @RequestParam(value = "text2") String text2) {

        logger.info(text1);
        logger.info(text2);
        return text1 + "/" + text2;
    }

    /**
     * GET 参数获取
     * 不提供参数是 null
     * required=true，无defaultValue，不提供参数会400
     * 多值value必须要提供数组形式
     */
    @RequestMapping(value = "/gets", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String gets(String text1,
                       @RequestParam(value = "rext2", defaultValue = "value2", required = true) String text2,
                       @RequestParam(value = "checkbox[]", required = false) ArrayList<String> checkbox) {

        logger.info(text1);
        logger.info(text2);
        logger.info(ArrayUtils.toString(checkbox));

        StringBuilder response = new StringBuilder();
        response.append(text1 + "/");
        response.append(text2 + "/");
        response.append(ArrayUtils.toString(checkbox));
        return response.toString();
    }

    /**
     * 获取参数集合
     * 同时会接受GET，POST参数
     * 重名参数POST不会覆盖GET
     */
    @RequestMapping("/getmap")
    @ResponseBody
    public Map<String, Object> getMap(@RequestParam Map<String, Object> gets) {
        logger.info(gets.toString());
        return gets;
    }

    /**
     * 参数映射 @ModelAttribute
     */
    @RequestMapping("/getmodel")
    @ResponseBody
    public User getModel(@Valid User user) {
        return user;
    }

    @RequestMapping("/modelattribute")
    public String modelAttribute(@ModelAttribute(value = "model2") User user, Model model) {
        model.addAttribute("user", user);
        return "model";
    }

    /**
     * 模版引擎数据传入，返回是模版文件名
     *
     * @param model    这是个接口
     * @param modelMap 这是个实现
     * @param map      这是Java原生 Map 类
     */
    @RequestMapping("/model")
    public String model(Model model, ModelMap modelMap, Map<String, Object> map) {
        model.addAttribute("title1", "model_title");
        modelMap.addAttribute("title2", "modelMap_title");
        map.put("title2", "map_title");

        User user = new User(1L, "aqiang");
        model.addAttribute("user", user);
        return "model";
    }

    /**
     * 手动渲染模版，返回 ModelAndView 对象
     */
    @RequestMapping("/modelandview")
    public ModelAndView modelAndView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("model");
        modelAndView.addObject("title1", "title1");
        modelAndView.addObject("title2", "title2");

        User user = new User();
        user.setId(1L);
        user.setName("test");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    /**
     * 注解会优先于控制器方法执行
     */
    @ModelAttribute(value = "model1")
    public String modelSet1() {
        logger.info("ModelAttribute medel");
        return "model1";
    }

    @ModelAttribute(value = "model2")
    public User modelSet2() {
        logger.info("ModelAttribute model2");
        User user = new User();
        user.setId(1L);
        user.setName("ModelAttribute User");
        return user;
    }

    @RequestMapping("/setcookie")
    @ResponseBody
    public String setCookie(HttpServletResponse response) {
        Cookie cookie1 = new Cookie("cookie1", "value1");
        cookie1.setMaxAge(1800);
        Cookie cookie2 = new Cookie("cookie2", "value2");
        cookie2.setMaxAge(3600);

        response.addCookie(cookie1);
        response.addCookie(cookie2);
        return "coolie set ok";
    }

    @RequestMapping("/getcookie")
    @ResponseBody
    public String getCookie(HttpServletRequest request,
                            @CookieValue(value = "cookie1", required = false) String cookie1) {
        HashMap<String, String> map = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                map.put(cookie.getName(), cookie.getValue());
            }
        }
        logger.info(cookie1);
        return map.toString();
    }

    @RequestMapping("/delcookie")
    @ResponseBody
    public String delCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setValue(null);
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        return "delete ok";
    }

    @RequestMapping("/setsession")
    @ResponseBody
    public String setSession(HttpSession session) {
        session.setAttribute("session1", "value1");
        session.setAttribute("session2", "value2");
        return "";
    }

    @RequestMapping("/getsession")
    @ResponseBody
    public String getSession(HttpServletRequest request,
                             HttpSession httpSession,
                             @SessionAttribute(value = "session1", required = false) String session1) {
        HttpSession session = request.getSession();
        String session2 = (String) session.getAttribute("session2");
        String http_session1 = (String) httpSession.getAttribute("session1");

        logger.info(http_session1);
        logger.info(session1);
        logger.info(session2);

        HashMap<String, String> sessionMap = new HashMap<>();
        Enumeration<String> sessions = session.getAttributeNames();
        while (sessions.hasMoreElements()) {
            String key = sessions.nextElement();
            sessionMap.put(key, (String) session.getAttribute(key));
        }
        return sessionMap.toString();
    }

    @RequestMapping("/delsession")
    @ResponseBody
    public String delSession(HttpSession httpSession) {
        httpSession.removeAttribute("session1");
        httpSession.removeAttribute("session2");

        return "delete session ok";
    }


}
