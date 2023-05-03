package com.app.dodamdodam.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "TBL_RECRUITMENTFILE")
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RandomKey {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    private int randomkey;
}
