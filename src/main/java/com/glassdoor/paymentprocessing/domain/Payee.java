package com.glassdoor.paymentprocessing.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Payee extends BaseEntity {

    @NotNull
    @Column(nullable = false, unique = true)
    private String userName;

    @NotNull
    @Column(nullable = false)
    private String password;

    @NotNull
    @Column(nullable = false)
    private String cardNumber;

    @NotNull
    @Column(nullable = false)
    private String billingAddress;

    @OneToMany
    private List<Transaction> transactions = new ArrayList<>();

}
