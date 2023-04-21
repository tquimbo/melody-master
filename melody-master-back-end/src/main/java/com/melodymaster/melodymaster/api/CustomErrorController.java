package com.melodymaster.melodymaster.api;

import org.springframework.stereotype.Controller;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class CustomErrorController implements ErrorController {

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public ModelAndView handleException(HttpServletRequest request, Exception ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", request.getRequestURL());
        mav.setViewName("error");
        return mav;
    }

    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("status", request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
        mav.addObject("error", request.getAttribute(RequestDispatcher.ERROR_EXCEPTION));
        mav.addObject("message", request.getAttribute(RequestDispatcher.ERROR_MESSAGE));
        mav.addObject("timestamp", new Date());
        mav.addObject("url", request.getRequestURL());
        mav.setViewName("error");
        return mav;
    }

   
    public String getErrorPath() {
        return "/error";
    }
}

// package com.melodymaster.melodymaster.api;

// import org.springframework.stereotype.Controller;
// import org.springframework.context.annotation.Bean;
// import org.springframework.boot.web.servlet.error.ErrorController;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.servlet.ModelAndView;

// import javax.servlet.RequestDispatcher;
// import javax.servlet.http.HttpServletRequest;
// import java.util.Date;

// @Controller
// public class CustomErrorController implements ErrorController {

//     @ExceptionHandler(value = {Exception.class, RuntimeException.class})
//     public ModelAndView handleException(HttpServletRequest request, Exception ex) {
//         ModelAndView mav = new ModelAndView();
//         mav.addObject("exception", ex);
//         mav.addObject("url", request.getRequestURL());
//         mav.setViewName("error");
//         return mav;
//     }

//     @RequestMapping("/error")
//     public ModelAndView handleError(HttpServletRequest request) {
//         ModelAndView mav = new ModelAndView();
//         mav.addObject("status", request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
//         mav.addObject("error", request.getAttribute(RequestDispatcher.ERROR_EXCEPTION));
//         mav.addObject("message", request.getAttribute(RequestDispatcher.ERROR_MESSAGE));
//         mav.addObject("timestamp", new Date());
//         mav.addObject("url", request.getRequestURL());
//         mav.setViewName("error");
//         return mav;
//     }

//     @Override
//     public String getErrorPath() {
//         return "/error";
//     }
// }
// package com.melodymaster.melodymaster.api;


// import org.springframework.web.bind.annotation.ControllerAdvice;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.RequestMapping;
// import javax.servlet.http.HttpServletRequest;
// import org.springframework.web.servlet.ModelAndView;
// import java.util.Date;
// import javax.servlet.RequestDispatcher;
// import org.springframework.boot.web.servlet.error.CustomErrorController;


// // @ControllerAdvice
// // public class CustomErrorController {

// //   @ExceptionHandler(value = {Exception.class, RuntimeException.class})
// //   public ModelAndView handleException(HttpServletRequest request, Exception ex) {
// //     ModelAndView mav = new ModelAndView();
// //     mav.addObject("exception", ex);
// //     mav.addObject("url", request.getRequestURL());
// //     mav.setViewName("error");
// //     return mav;
// //   }

// //   @RequestMapping("/error")
// //   public ModelAndView handleError(HttpServletRequest request) {
// //     ModelAndView mav = new ModelAndView();
// //     mav.addObject("status", request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
// //     mav.addObject("error", request.getAttribute(RequestDispatcher.ERROR_EXCEPTION));
// //     mav.addObject("message", request.getAttribute(RequestDispatcher.ERROR_MESSAGE));
// //     mav.addObject("timestamp", new Date());
// //     mav.addObject("url", request.getRequestURL());
// //     mav.setViewName("error");
// //     return mav;
// //   }
// // }

// @Controller
// public class CustomErrorController {  

//   @ExceptionHandler(value = {Exception.class, RuntimeException.class})
//   public ModelAndView handleException(HttpServletRequest request, Exception ex) {
//     ModelAndView mav = new ModelAndView();
//     mav.addObject("exception", ex);
//     mav.addObject("url", request.getRequestURL());
//     mav.setViewName("error");
//     return mav;
//   }

//   @RequestMapping("/error")
//   public ModelAndView handleError(HttpServletRequest request) {
//     ModelAndView mav = new ModelAndView();
//     mav.addObject("status", request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
//     mav.addObject("error", request.getAttribute(RequestDispatcher.ERROR_EXCEPTION));
//     mav.addObject("message", request.getAttribute(RequestDispatcher.ERROR_MESSAGE));
//     mav.addObject("timestamp", new Date());
//     mav.addObject("url", request.getRequestURL());
//     mav.setViewName("error");
//     return mav;
//   }

//   @Override
//   public String getErrorPath(){
//     return "/error";
//   }

//   @Bean
//   public CustomErrorController customErrorController() {
//     return new CustomErrorController();
//   }
// }
// package com.melodymaster.melodymaster.api;

// import org.springframework.stereotype.Controller;
// import org.springframework.context.annotation.Bean;
// import org.springframework.boot.web.servlet.error.ErrorController;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.servlet.ModelAndView;

// import javax.servlet.RequestDispatcher;
// import javax.servlet.http.HttpServletRequest;
// import java.util.Date;

// @Controller
// public class CustomErrorController implements ErrorController {

//     @ExceptionHandler(value = {Exception.class, RuntimeException.class})
//     public ModelAndView handleException(HttpServletRequest request, Exception ex) {
//         ModelAndView mav = new ModelAndView();
//         mav.addObject("exception", ex);
//         mav.addObject("url", request.getRequestURL());
//         mav.setViewName("error");
//         return mav;
//     }

//     @RequestMapping("/error")
//     public ModelAndView handleError(HttpServletRequest request) {
//         ModelAndView mav = new ModelAndView();
//         mav.addObject("status", request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
//         mav.addObject("error", request.getAttribute(RequestDispatcher.ERROR_EXCEPTION));
//         mav.addObject("message", request.getAttribute(RequestDispatcher.ERROR_MESSAGE));
//         mav.addObject("timestamp", new Date());
//         mav.addObject("url", request.getRequestURL());
//         mav.setViewName("error");
//         return mav;
//     }

   
//     @Override
//     public String getErrorPath() {
//         return "/error";
//     }
// }