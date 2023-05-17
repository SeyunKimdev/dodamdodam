package com.app.dodamdodam.entity.embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor
@ToString
//@AllArgsConstructor
public class Address {
//    private String postcode;
    private String address;
    private String addressDetail;

    public Address(String address, String addressDetail) {
        this.address = address;
        this.addressDetail = addressDetail;
    }
}
