package pl.olszak.japanesehelper.japanesehelper.security;

import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@NoArgsConstructor
public final class SecurityUtils {

    public static String getCurrentLoggedUserLogin(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String userName = null;

        if(authentication != null){
            if(authentication.getPrincipal() instanceof UserDetails){
                UserDetails springSecurityUer = (UserDetails) authentication.getPrincipal();
                userName = springSecurityUer.getUsername();
            } else if(authentication.getPrincipal() instanceof  String) {
                userName = (String) authentication.getPrincipal();
            }
        }
        return userName;
    }
}
