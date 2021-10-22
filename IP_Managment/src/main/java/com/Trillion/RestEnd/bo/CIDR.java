package com.Trillion.RestEnd.bo;

import javax.persistence.*;

/**
 * CIDR Class object holding the blueprint of the object
 *  & Constructor and Methods
 */
@Entity
@Table(name = "TEST_DB")
public class CIDR {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name= "CIDR")
    private String cidr_block;

    @Column(name = "IP_ADDRESS")
    private String ip_address;

    @Column(name = "STATUS")
    private String status;

    public CIDR(String cidr_block, String ip_address, String status) {
        this.cidr_block = cidr_block;
        this.ip_address = ip_address;
        this.status = status;
    }

    public CIDR(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCidr_block() {
        return cidr_block;
    }

    public void setCidr_block(String cidr_block) {
        this.cidr_block = cidr_block;
    }

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address){
        this.ip_address = ip_address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
