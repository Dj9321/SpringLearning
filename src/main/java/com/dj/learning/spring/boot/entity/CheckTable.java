package com.dj.learning.spring.boot.entity;

import org.hibernate.annotations.Type;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Data;

// Entity name refers to the identifier used for a persistent domain object managed by JPA 
//@Entity(name = "created_in_db")
@Entity
//@Table(name = "created_in_db", schema = "public")
@Data
public class CheckTable {

//	@Version
//	private Long version;

	/**
	 * @Id: tells this is the primary key column. Not composite however.
	 *      GenerationTYpe.AUTO > the JPA provider will use any strategy it wants to
	 *      generate the identifiers. Use @SequenceGenerator with allocationSize so
	 *      that defaults will not be set like 50
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "created_in_db_seq")
	@SequenceGenerator(name = "created_in_db_seq", sequenceName = "created_in_db_seq", allocationSize = 1)
	Integer primary_key1;

//	@Type(com.vladmihalcea.hibernate.type.array.StringArrayType.class)
	@Column(name = "column1")
	Character column1;

	@Column(name = "column2")
	Character column2;
}
