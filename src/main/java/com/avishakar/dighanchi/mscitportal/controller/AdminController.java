package com.avishakar.dighanchi.mscitportal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avishakar.dighanchi.mscitportal.security.JwtUtil;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	private final JwtUtil jwtUtil;
	
	public AdminController(JwtUtil jwtUtil) {
		this.jwtUtil=jwtUtil;
	}

    @GetMapping("/dashboard")
    public String adminDashboard(@RequestHeader("Authorization") String token) {
        String role = jwtUtil.extractRole(token.substring(7));
        if (!role.equals("ADMIN")) {
            throw new RuntimeException("Access Denied");
        }
        return "Welcome Admin!";
    }
}
