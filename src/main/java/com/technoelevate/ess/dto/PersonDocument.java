package com.technoelevate.ess.dto;

import java.io.Serializable;
import java.nio.file.Path;
import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PersonDocument implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int fileId;
	private String fileName;
	private String fileType;
	private String filePath;


	public PersonDocument(String fileName, String fileType, String filePath) {
		super();
		this.fileName = fileName;
		this.fileType = fileType;
		this.filePath = filePath;

	}

}
