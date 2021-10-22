package com.Trillion.RestEnd.service;

import com.Trillion.RestEnd.bo.CIDR;
import com.Trillion.RestEnd.repository.CidrRepository;
import org.apache.commons.net.util.SubnetUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service methods calling the CRUD operations
 */
@Service
public class CidrService {
    @Autowired
    CidrRepository cidrRepository;

    public ResponseEntity<List<CIDR>> getAll() {
        try {
            List<CIDR> getCidr = cidrRepository.findAll();
            if (getCidr.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(getCidr, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Adds a list of new IP Addresses in the H2 DB
     */
    public ResponseEntity<CIDR> add(CIDR cidr){
        try {
            List<CIDR> ipAcquire;
            //Prevents the same Cidr Block from being added
            ipAcquire = cidrRepository.findAll().stream().filter(cidrCheck -> cidrCheck
                            .getCidr_block()
                            .equalsIgnoreCase(cidr.getCidr_block()))
                            .collect(Collectors.toList());

            if (ipAcquire.isEmpty()) {
                SubnetUtils utils = new SubnetUtils(cidr.getCidr_block());
                String[] allIps = utils.getInfo().getAllAddresses();
                for (String ip : allIps) {
                    // Optional for setting status at API Call
                    //cidrRepository.save(new CIDR(cidr.getCidr_block(), ip, cidr.getStatus()));
                    cidrRepository.save(new CIDR(cidr.getCidr_block(), ip, "available"));
                }
                //cidr.setStatus("available");
                ipAcquire.clear();
                return new ResponseEntity<>(cidr, HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>(cidr, HttpStatus.CONFLICT);
            }
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Method finds specific IP Address and sets the status to acquired
     */
    public ResponseEntity<CIDR> acquireIP(String ipAddress){
        try {
            List<CIDR> ipAcquire;
            ipAcquire = cidrRepository.findAll().stream().filter(cidr -> cidr
                            .getIp_address()
                            .equalsIgnoreCase(ipAddress))
                    .collect(Collectors.toList());

            CIDR updateCidr = new CIDR();
            updateCidr.setId(ipAcquire.get(0).getId());
            updateCidr.setCidr_block(ipAcquire.get(0).getCidr_block());
            updateCidr.setIp_address(ipAcquire.get(0).getIp_address());
            updateCidr.setStatus("acquired");
            cidrRepository.save(updateCidr);
            return new ResponseEntity<>(updateCidr, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Method finds specific IP Address and sets the status to available
     */
    public ResponseEntity<CIDR> releaseIP(String ipAddress){
        try {
            List<CIDR> ipAcquire;
            ipAcquire = cidrRepository.findAll().stream().filter(cidr -> cidr
                            .getIp_address()
                            .equalsIgnoreCase(ipAddress))
                    .collect(Collectors.toList());

            CIDR updateCidr = new CIDR();
            updateCidr.setId(ipAcquire.get(0).getId());
            updateCidr.setIp_address(ipAcquire.get(0).getIp_address());
            updateCidr.setCidr_block(ipAcquire.get(0).getCidr_block());
            updateCidr.setStatus("available");
            cidrRepository.save(updateCidr);
            return new ResponseEntity<>(updateCidr, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}