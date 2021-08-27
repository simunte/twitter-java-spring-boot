package com.twitter.demo.Domain;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Base entity class
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class Base implements Serializable {

	private static final long serialVersionUID = -7369920601847524273L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;


	@Version
	@Column(name = "version")
	private Integer version = 0;

}
