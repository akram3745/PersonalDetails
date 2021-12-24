package com.technoelevate.ess.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings({ "serial"})
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EmergencyContactDetails_Table")
public class EmergencyContactDetailsPojo implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "fatherName cannot be empty")
	private String fatherName;
	
	@Range(min = 6000000000l , max = 9999999999l,message = "Please Enter The Correct Contact  Number")
	private long fatherContactNo;
	
	@NotEmpty(message = "father Occupation cannot be empty")
	private String fatherOccupation;
	
	@NotEmpty(message = "motherName cannot be empty")
	private String motherName;
	
	@Range(min = 6000000000l , max = 9999999999l,message = "Please Enter The Correct Contact  Number")
	private long motherContactNo;
	
	@NotEmpty(message = "mother Occupation cannot be empty")
	private String motherOccupation;
	
	@NotEmpty(message = "relationship cannot be empty")
	private String relationship;
	
	@NotEmpty(message = "relativeName cannot be empty")
	private String relativeName;
	
	@Range(min = 6000000000l , max = 9999999999l,message = "Please Enter The Correct Contact  Number")
	private long relativeContactNo;
	
	@NotEmpty(message = "relative Occupation cannot be empty")
	private String relativeOccupation;
	
	public EmergencyContactDetailsPojo(String fatherName, Long fatherContactNo, String fatherOccupation, String motherName,
			Long motherContactNo, String motherOccupation, String relationship, String relativeName,
			Long relativeContactNo, String relativeOccupation) {
		super();
		this.fatherName = fatherName;
		this.fatherContactNo = fatherContactNo;
		this.fatherOccupation = fatherOccupation;
		this.motherName = motherName;
		this.motherContactNo = motherContactNo;
		this.motherOccupation = motherOccupation;
		this.relationship = relationship;
		this.relativeName = relativeName;
		this.relativeContactNo = relativeContactNo;
		this.relativeOccupation = relativeOccupation;
	}
	@Override
	public String toString() {
		return "EmergencyContactDetails [id=" + id + ", fatherName=" + fatherName + ", fatherContactNo="
				+ fatherContactNo + ", fatherOccupation=" + fatherOccupation + ", motherName=" + motherName
				+ ", motherContactNo=" + motherContactNo + ", motherOccupation=" + motherOccupation + ", relationship="
				+ relationship + ", relativeName=" + relativeName + ", relativeContactNo=" + relativeContactNo
				+ ", relativeOccupation=" + relativeOccupation + "]";
	}
	
	
	

}
