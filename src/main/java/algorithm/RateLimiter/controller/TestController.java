package algorithm.RateLimiter.controller;

import algorithm.RateLimiter.SlidingWindow.RateLimiter;
import algorithm.RateLimiter.model.Client;
import algorithm.RateLimiter.service.ClientService;
import algorithm.RateLimiter.util.WebUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.concurrent.atomic.AtomicInteger;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final WebUtils webUtils;
    private final RateLimiter rateLimiter;
    private final ClientService clientService;

    @GetMapping("/")
        public String ip(Model model){
            //System.out.println(webUtils.getClientIp());

        Client client = clientService.findByAddressIp(webUtils.getClientIp());
            if(client == null){
                System.out.println("           new               ");
                client = new Client();
                client.setAddressIP(webUtils.getClientIp());
                client.setMaxRequest(rateLimiter.maxRequestPerMin);
                clientService.saveClient(client);
            } else {
                System.out.println("            old               ");
            }
            model.addAttribute("ip", webUtils.getClientIp());
            model.addAttribute("maxRequest", rateLimiter.maxRequestPerMin);
            model.addAttribute("requestCount", client.getPreCount());
            return "home";
    }

    @PostMapping("/")
        public String request(){
        Client client = clientService.findByAddressIp(webUtils.getClientIp());
        System.out.println("---------------- client ----------------  "+client.getAddressIP());
        int curLimiter = rateLimiter.allow(new AtomicInteger(client.getPreCount()), client.getCurWindowKey());
        if (curLimiter > rateLimiter.maxRequestPerMin){
            return "error";
        } else {
            client.setPreCount(curLimiter);
            client.setCurWindowKey(rateLimiter.getCurWindowKey());
            clientService.saveClient(client);
            return "redirect:/";
        }
    }

}
