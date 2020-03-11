package com.pet.bigdatabegginer.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "limits_per_hour", schema = "traffic_limits")
@Data
@NoArgsConstructor
public class Limits {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "limit_name")
    private String name;
    @Column(name = "limit_value")
    private long value;
    @Column(name = "effective_date")
    private Date date;
}
