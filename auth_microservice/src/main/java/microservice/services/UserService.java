package microservice.services;


import microservice.repositories.UserRepository;
import microservice.util.PasswordHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import microservice.models.Authorization;
import microservice.models.Message;
import microservice.models.User;
import java.util.concurrent.CompletableFuture;
import org.springframework.scheduling.annotation.Async;
import org.bson.types.ObjectId;
import java.util.Calendar;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.exceptions.JWTVerificationException;


@Service
public class UserService {

    @Value("${jwt.issuer}")
    private String ISSUER;

    @Value("${jwt.secret}")
    private String SECRET;

    @Value("${jwt.duration.in.days}")
    private int ACCESS_TOKEN_DURATION;

    @Autowired
    private UserRepository userRepo;


    @Async("ThreadPoolExecutor")
    public CompletableFuture<User> insertNewUser(User user) {
        user.set_id(ObjectId.get());
        user.setPassword(PasswordHandler.encryptPassword(user.getPassword()));
        return CompletableFuture.completedFuture(userRepo.save(user));  
    }

    @Async("ThreadPoolExecutor")
    public CompletableFuture<?> authorizeUser(User user) {
        User storedUser = userRepo.findByUsername(user.getUsername());
        String msg = "invalid username or password";

        if (storedUser != null) {
            boolean isValidPassword = PasswordHandler.checkPassword(user.getPassword(), 
                                                                    storedUser.getPassword());

            if (isValidPassword) {
                Calendar issuedAt = Calendar.getInstance();
                Calendar expiresAt = Calendar.getInstance();
                expiresAt.add(Calendar.DATE, ACCESS_TOKEN_DURATION);

                try {
                    Algorithm algorithm = Algorithm.HMAC256(SECRET);
                    String token = JWT.create()
                        .withIssuer(ISSUER)
                        .withIssuedAt(issuedAt.getTime())
                        .withExpiresAt(expiresAt.getTime())
                        .withClaim("userId", storedUser.get_id())
                        .sign(algorithm);

                    Authorization auth = new Authorization(token, expiresAt.getTime(), true);
                    return CompletableFuture.completedFuture(auth);
                } 
                catch (JWTCreationException e) {
                    // invalid signing configuration / couldn't convert claims
                    msg = e.getMessage();
                }
            }
        }

        return CompletableFuture.completedFuture(new Message(msg, false));
    }

    @Async("ThreadPoolExecutor")
    public CompletableFuture<Message> authenticateUser(String token) {
        String msg = "an error has occurred";
        boolean status = false;
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                                        .withIssuer(ISSUER)
                                        .build();

            DecodedJWT jwt = verifier.verify(token);
            String userId = jwt.getClaim("userId").asString();
            msg = "valid token";
            status = true;
        }
        catch (JWTVerificationException e) {
            msg = "invalid token";
        }
        catch (NullPointerException e) {
            msg = "no token provided";
        }
        
        return CompletableFuture.completedFuture(new Message(msg, status));
    }
    
}
