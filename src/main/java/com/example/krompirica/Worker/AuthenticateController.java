package com.example.krompirica.Worker;

import com.example.krompirica.Worker.services.ApplicationUserDetailsService;
import com.example.krompirica.model.AuthenticationRequest;
import com.example.krompirica.model.AuthenticationResponce;
import com.example.krompirica.model.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class AuthenticateController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtTokenUtil;
    private final ApplicationUserDetailsService userDetailsService;

    @RequestMapping(value="/authenticate")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthenticationResponce authenticate(@RequestBody AuthenticationRequest req) throws Exception{

        WorkerEntity worker;
        try {
            worker=userDetailsService.authenticate(
                    req.getUsername(),req.getPassword()
            );
            System.out.println(req.getUsername()+" "+req.getPassword());
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect username or password",e);
        }catch (Exception e) {
            throw new Exception("Incorrect username or password", e);
        }
        var userDetails=userDetailsService.loadUserByUsername(worker.getUsername());
        System.out.println(userDetails);
        var jwt=jwtTokenUtil.generateToken(userDetails,worker.getRole().getId());

        return new AuthenticationResponce(jwt);
    }
    @GetMapping("/checkToken/{token}")
    public Boolean checkToken(@RequestParam String token){
        return jwtTokenUtil.isTokenValid(token);
    }
}
