package algorithm.RateLimiter.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class WebUtils {

    private HttpServletRequest request;

    @Autowired
    public void setRequest(HttpServletRequest request){
        this.request = request;
    }

    public String getClientIp(){

        String remoteAdr = "";
        if(request != null){
            remoteAdr = request.getHeader("X=FORWARDED_FOR");
            if(remoteAdr == null || "".equals(remoteAdr)){
                remoteAdr = request.getRemoteAddr();
            }
        }
        return remoteAdr;
    }
}
