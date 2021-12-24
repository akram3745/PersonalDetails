package com.technoelevate.ess.pojo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;

import com.technoelevate.ess.dto.EmergencyContactDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PersonPojo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int personId;
	
	@NotEmpty(message = "name cannot be empty")
	private String candidateName;
	
	//@Past(message = "date from past")
	private String dob;
	
	@Range(min = 6000000000l , max = 9999999999l,message = "Please Enter The Correct Contact  Number")
	private long contactNo;
	
	@Range(min = 6000000000l , max = 9999999999l,message = "Please Enter The Correct Whatup  Number")
	private long whatsUpNo;
	
	@Range(min = 100000000000l , max = 999999999999l,message = "Please Enter The Correct AdharCard Number")
	private long aadharCardNo;
	
	@Pattern(regexp = "(?=.*[0-9])(?=.*[A-Z])(?=\\S+$).{15}",message = "Driveing License Should Contain Capital letter And number")
	private String drivingLincenseNo;
	
	@NotEmpty(message = "Gender  Cannot Be Empty")
	private String gender;
	
	@Pattern(regexp = "(?=.*[0-9])(?=.*[A-Z])(?=\\S+$).{10}",message = "Pancard Number Should Contain Capital letter And number")
	private String panNo;
	
	@Pattern(regexp = "[a-zA-Z]+[0-9]*[@][a-z]*[.][a-z]{2,3}")
	private String email;
	
	@NotEmpty(message = "presentAddress Cannot be null")
	private String presentAddress;
	
	@NotEmpty(message = "permanentAddress Cannot be null")
	private String permanentAddress;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "personId")
	private @Valid EmergencyContactDetailsPojo emergencyContactDetails;
	
	public PersonPojo(int personId, String candidateName, String dob, Long contactNo, Long whatsUpNo, Long aadharCardNo,
			String drivingLincenseNo, String gender, String panNo, String email, String presentAddress,
			String permanentAddress) {
		super();
		this.personId = personId;
		this.candidateName = candidateName;
		this.dob = dob;
		this.contactNo = contactNo;
		this.whatsUpNo = whatsUpNo;
		this.aadharCardNo = aadharCardNo;
		this.drivingLincenseNo = drivingLincenseNo;
		this.gender = gender;
		this.panNo = panNo;
		this.email = email;
		this.presentAddress = presentAddress;
		this.permanentAddress = permanentAddress;
	}

	@Override
	public String toString() {
		return "Person [personId=" + personId + ", candidateName=" + candidateName + ", dob=" + dob + ", contactNo="
				+ contactNo + ", whatsUpNo=" + whatsUpNo + ", aadharCardNo=" + aadharCardNo + ", drivingLincenseNo="
				+ drivingLincenseNo + ", gender=" + gender + ", panNo=" + panNo + ", email=" + email
				+ ", presentAddress=" + presentAddress + ", permanentAddress=" + permanentAddress + "]";
	}



	
	

	
	
	
	
	
	
	

}
