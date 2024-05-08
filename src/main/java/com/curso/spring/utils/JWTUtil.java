package com.curso.spring.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JWTUtil {
    @Value("${security.jwt.secret}")
    private String key;

    @Value("${security.jwt.issuer}")
    private String issuer;

    @Value("${security.jwt.ttlMillis}")
    private long ttlMillis;

    // Algoritmo de firma para JWT, en este caso, HS256
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    /**
     * Crea un nuevo token JWT.
     *
     * @param id      Identificador único para el JWT
     * @param subject Sujeto del JWT (por ejemplo, el usuario al que pertenece el JWT)
     * @return Token JWT generado
     */
    public String create(String id, String subject) {
        // Decodifica la clave secreta desde Base64
        byte[] apiKeySecretBytes = Base64.getDecoder().decode(key);

        // Crea una clave secreta con la clave decodificada y el algoritmo de firma
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, SIGNATURE_ALGORITHM.getJcaName());

        // Obtiene la fecha actual en milisegundos
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // Construye el JWT con las reclamaciones necesarias (id, fecha de emisión, sujeto, emisor)
        JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).setSubject(subject).setIssuer(issuer)
                .signWith(SIGNATURE_ALGORITHM, signingKey);

        // Si se ha especificado un tiempo de expiración, establece la fecha de expiración del JWT
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        // Construye el JWT y lo devuelve como una cadena compacta
        return builder.compact();
    }

    /**
     * Método para validar y leer el JWT.
     *
     * @param jwt Token JWT a validar y leer
     * @return Sujeto del JWT (por ejemplo, el usuario al que pertenece el JWT)
     */
    public String getValue(String jwt) {
        // Parsea el JWT y obtiene sus reclamaciones (claims), incluyendo el sujeto
        Claims claims = Jwts.parser().setSigningKey(Base64.getDecoder().decode(key))
                .parseClaimsJws(jwt).getBody();

        // Retorna el sujeto del JWT
        return claims.getSubject();
    }

    /**
     * Método para validar y leer el JWT.
     *
     * @param jwt Token JWT a validar y leer
     * @return Identificador único del JWT
     */
    public String getKey(String jwt) {
        // Parsea el JWT y obtiene sus reclamaciones (claims), incluyendo el identificador único
        Claims claims = Jwts.parser().setSigningKey(Base64.getDecoder().decode(key))
                .parseClaimsJws(jwt).getBody();

        // Retorna el identificador único del JWT
        return claims.getId();
    }
}
