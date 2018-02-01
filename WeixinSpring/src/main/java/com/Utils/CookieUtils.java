package com.Utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Wudi
 * @Description: cookie的工具类
 * @date 11:35  2018/1/29
 */
public class CookieUtils {

    /**
     * cookie中添加数据
     * @param response
     * @param key    key值
     * @param token  value值
     * @param maxAge 过过期时间
     */
    public static void setCookie(HttpServletResponse response,String key,String token,Integer maxAge){

        Cookie cookie = new Cookie(key, token);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);//过期时间
        response.addCookie(cookie);

    }

    /**
     * cookie中删除(cookie中的过期时间为0)，浏览器中的Cookie重新启动一下
     * @param response
     * @param key  key值
     */
    public static void deleteCookie(HttpServletResponse response,String key){
        Cookie cookie = new Cookie(key, "");
         cookie.setMaxAge(0);
         cookie.setPath("/");
         response.addCookie(cookie);

    }

    /**
     * 获取cookie
     * @param request
     * @return
     */
    public static Cookie getCookie(
                                   HttpServletRequest request,
                                   String name){
        Map<String, Cookie> cookieList = getCookieList(request);
            if (cookieList.containsKey(name)){
                return cookieList.get(name);
            }
        return null;
    }
    /**
     * 获取Cookie的list
     * @param request
     * @return
     */
   private static Map<String,Cookie> getCookieList( HttpServletRequest request){
       Map<String,Cookie> map = new HashMap<>();
       Cookie[] cookies = request.getCookies();
           if (cookies !=null){
               for (Cookie cookie:cookies){
                   map.put(cookie.getName(),cookie);
               }
           }
       return map;
   }

}
