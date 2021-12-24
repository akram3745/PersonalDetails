package com.technoelevate.ess.service;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.multipart.MultipartFile;

import com.technoelevate.ess.dto.Person;
import com.technoelevate.ess.pojo.PersonPojo;

import lombok.val;

public interface PersonService {

	public Person savePerson(List<MultipartFile> multipartFile,@Valid PersonPojo person);
	

	public Person deletePerson(int id) throws IOException;

	public Person getPersonById(int id);
//
//	public PersonInfo updatePerson(List<MultipartFile> multipartFiles,  PersonPojo personPojo);

}
