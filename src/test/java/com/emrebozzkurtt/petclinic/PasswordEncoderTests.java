package com.emrebozzkurtt.petclinic;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTests {

	@Test
	public void generatedEncodedPasswords() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		System.out.println( "{bcrypt}" + passwordEncoder.encode("147258") );
		System.out.println( "{bcrypt}" + passwordEncoder.encode("147258") );
		System.out.println( "{bcrypt}" + passwordEncoder.encode("147258") );
	}
}
