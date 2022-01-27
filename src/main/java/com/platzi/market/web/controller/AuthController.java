package com.platzi.market.web.controller;

import com.platzi.market.domain.dto.AuthenticationRequest;
import com.platzi.market.domain.dto.AuthenticationResponse;
import com.platzi.market.domain.service.PlatziUserDetailsService;
import com.platzi.market.web.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    //le decimos al gestor de autenticación de spring que verifique si el usuario y la contraseña son correctos.
    //inyectar el authentication manager q tiene spring
    @Autowired
    private AuthenticationManager authenticationManager;

    //obtenemos los detaller del usuario desde el servicio que creamos para este fin.
    //Inyectamos el PlatziUserDetailsSErvice q es el que se encarga en este momento de generar la seguridad por usuario y contraseña
    @Autowired
    private PlatziUserDetailsService platziUserDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    //este método va a recibir peticiones a través de un post
    //al recibir peticiones a través de POST debemos añadirle la anotacion requestbody al parámetro
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> createToken(@RequestBody  AuthenticationRequest request){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            UserDetails userDetails = platziUserDetailsService.loadUserByUsername(request.getUsername());
            String jwt = jwtUtil.generateToken(userDetails);
            return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);
        } catch(BadCredentialsException e){
//va a ocurir cuando el usuario no sea barbi y/o la contraseña sea platzi
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }
}
