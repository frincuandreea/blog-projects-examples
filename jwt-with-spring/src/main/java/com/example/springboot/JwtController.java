package com.example.springboot;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/jwt")
public class JwtController {

	private final JWTTokenComponent jwtTokenComponent;

	public JwtController(JWTTokenComponent jwtTokenComponent) {
		this.jwtTokenComponent = jwtTokenComponent;
	}

	@GetMapping("/generateToken")
	public String generateJwtToken() {
		TokenInfo tokenInfo = new TokenInfo("testId","private_user");
		return jwtTokenComponent.generateJwtToken(tokenInfo,3600);
	}

	@PostMapping("/decodeToken")
	public ResponseEntity generateJwtToken(@RequestBody String jwtToken) {
		TokenInfo tokenInfo = jwtTokenComponent.decodeJwtToken(jwtToken,TokenInfo.class);
		return ResponseEntity.ok(tokenInfo);
	}

}
