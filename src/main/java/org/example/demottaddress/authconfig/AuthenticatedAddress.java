//package org.example.demottaddress.authconfig;
//
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import java.util.Objects;
//
//@Getter
//@Setter
//public class AuthenticatedAddress extends UsernamePasswordAuthenticationToken {
//    private final String code;
//
//    public AuthenticatedAddress(String code) {
//        super(null, null, null);
//        this.code = code;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) return true;
//        if (obj == null || getClass() != obj.getClass()) return false;
//        AuthenticatedAddress that = (AuthenticatedAddress) obj;
//        return Objects.equals(code, that.code);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(code);
//    }
//}
