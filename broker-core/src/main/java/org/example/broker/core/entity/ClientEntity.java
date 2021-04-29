package org.example.broker.core.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "clients")
@Data
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String topic;
    private String url;
}
