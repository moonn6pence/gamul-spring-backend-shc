package com.gamul.gamul.api;

import com.gamul.gamul.exception.AccessDeniedException;
import com.gamul.gamul.exception.UnauthorizedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exception")
public class ExceptionController {

    @GetMapping("/accessDenied")
    public void accessDeniedException(){
        throw new AccessDeniedException();
    }

    @GetMapping("/unauthorized")
    public void unauthorizedException(){
        throw new UnauthorizedException();
    }
}
