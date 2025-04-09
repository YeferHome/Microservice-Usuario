package retoPragma.Microusuario.infrastructure.configuracion.jwt;


import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Base64;

public class JwtSecretGenerator {
    public static void main(String[] args) {
        SecretKey key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);


        String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());

        System.out.println("Clave JWT segura: " + base64Key);
    }
}