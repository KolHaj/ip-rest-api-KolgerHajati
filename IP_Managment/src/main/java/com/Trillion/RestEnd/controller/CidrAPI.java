package com.Trillion.RestEnd.controller;

import com.Trillion.RestEnd.bo.CIDR;
import com.Trillion.RestEnd.service.CidrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class CidrAPI {
    @Autowired
    private CidrService cidrService;

    /**
     * Post API to save the list of IP address in a CIDR Block
     */
    @PostMapping("/saveCidr")
    public ResponseEntity<CIDR> saveCidr(@RequestBody CIDR cidr) {
        return cidrService.add(cidr);
    }

    /**
     * Get API to grab the list of IP Addresses with their status
     */
    @GetMapping ("/getCidr")
    public ResponseEntity<List<CIDR>> getAll() {
       return cidrService.getAll();
    }

    /**
     * Put API to Acquire the specific IP and change status to acquired
     */
    @PutMapping("/acquireIP")
    public ResponseEntity<CIDR> acquireIP(@RequestBody CIDR cidr) {
        return cidrService.acquireIP(cidr.getIp_address());
    }

    /**
     * Put API to Release the specific IP and change status to available
     */
    @PutMapping("/releaseIP")
    public ResponseEntity<CIDR> releaseIP(@RequestBody CIDR cidr) {
        return cidrService.releaseIP(cidr.getIp_address());
    }
}
