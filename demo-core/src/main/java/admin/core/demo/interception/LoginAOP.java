package admin.core.demo.interception;

import admin.core.demo.aopAnnotation.Admin;
import admin.core.demo.aopAnnotation.Login;
import admin.core.demo.aopAnnotation.Normal;
import admin.core.demo.util.RequestUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Aspect
@Component
public class LoginAOP {

    @Pointcut("@annotation(admin.core.demo.aopAnnotation.Login)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getResponse();
        boolean auth = RequestUtil.isAuth(request);
        if (!auth && response != null) {
            response.setStatus(403);
            return null;
        }
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method targetMethod = methodSignature.getMethod();
        Login annotation = targetMethod.getAnnotation(Login.class);
        Class value = annotation.value();
        if (Admin.class.getName().equals(value.getName())) {
            return admin(point, request, response);
        } else if (Normal.class.getName().equals(value.getName())) {
            return normal(point, request, response);
        }
        return point.proceed();
    }

    private Object admin(ProceedingJoinPoint point,
                         HttpServletRequest request,
                         HttpServletResponse response) throws Throwable {
        if (RequestUtil.isAdmin(request)) {
            return point.proceed();
        }
        response.setStatus(403);
        return null;
    }

    private Object normal(ProceedingJoinPoint point,
                          HttpServletRequest request,
                          HttpServletResponse response) throws Throwable {
        if (RequestUtil.isAdmin(request) || RequestUtil.isNormal(request)) {
            return point.proceed();
        }
        response.setStatus(403);
        return null;
    }
}
