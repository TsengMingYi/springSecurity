package com.example.json;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//攔截器要變成bean而且要繼承HandlerInterceptor
//@Component
public class MyInterceptor  {
//public class MyInterceptor implements HandlerInterceptor {

//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    private JwtTokenUtil jwtTokenUtil;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String authHeader = request.getHeader("Authorization");
//        if(authHeader != null && authHeader.startsWith("Bearer ")){
//            System.out.println("Hello");
//            final String autoToken = authHeader.substring(7);
//            String email = jwtTokenUtil.getUsernameFromToken(autoToken);
//            if(email != null && SecurityContextHolder.getContext().getAuthentication() == null){
//                UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
//                if(jwtTokenUtil.validateToken(autoToken,userDetails)){
//                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//                            userDetails,null,userDetails.getAuthorities());
//                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//                    return true;
//                }
//            }
//            return true;
//        }else{
//            System.out.println("執行 MyInterceptor 的 preHandle 方法");
//            response.setStatus(401);
//            return false;
//        }
////        response.setStatus(HttpStatus.UNAUTHORIZED.value());
//    }
}
