package microservice.interceptor;


import com.auth0.jwt.JWTVerifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.exceptions.JWTVerificationException;


public class JWTInterceptor extends HandlerInterceptorAdapter {

    public static final String SECRET = "dev@ibm#santanderTEST11043512WhereIsMyCategorizedSpend";
    private static final String ISSUER = "http://api.santandertest.com.br";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, PATCH, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "6000");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With");

        String requestMethod = request.getMethod();

        if (!requestMethod.equals("OPTIONS")) {
            String token = request.getHeader("Authorization");
            try {
                Algorithm algorithm = Algorithm.HMAC256(SECRET);
                JWTVerifier verifier = JWT.require(algorithm)
                                          .withIssuer(ISSUER)
                                          .build();
                DecodedJWT jwt = verifier.verify(token);
            }
            catch (JWTVerificationException e) {
                if (token == null)
                    response.sendError(HttpStatus.UNAUTHORIZED.value());
                else 
                    response.sendError(HttpStatus.FORBIDDEN.value());
                
                return false;
            }
            
        }
        return super.preHandle(request, response, handler);
    }
}
