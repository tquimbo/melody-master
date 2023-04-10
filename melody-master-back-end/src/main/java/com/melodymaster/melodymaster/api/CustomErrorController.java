import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import java.util.Date;
import javax.servlet.RequestDispatcher;


@ControllerAdvice
public class CustomErrorController {

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
}

