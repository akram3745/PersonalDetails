package com.technoelevate.ess.controller;

import static com.technoelevate.ess.message.Message.SAVE_PERSON;
import static com.technoelevate.ess.message.Message.SOMETHIN_WENT_WRONG;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technoelevate.ess.dto.Person;
import com.technoelevate.ess.exception.CustomException;
import com.technoelevate.ess.message.Message;
import com.technoelevate.ess.message.ResponseMessage;
import com.technoelevate.ess.pojo.PersonPojo;
import com.technoelevate.ess.service.PersonService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Validated
@RequestMapping(path = "/api/v1/person")
public class PersonController {
	@Autowired
	private PersonService personService;
	@Autowired
	private ObjectMapper mappper;


	@PostMapping(path = "/person")
	public ResponseEntity<ResponseMessage> SavePerson(@RequestParam("file") MultipartFile[] multipartFile,
			@RequestParam("data") String personPojo) throws IOException {
		List<MultipartFile> files = Arrays.asList(multipartFile).stream().collect(Collectors.toList());

	@Valid
	PersonPojo person;
	try {
		person = mappper.readValue(personPojo, PersonPojo.class);
	} catch (Exception e) {
		throw new CustomException(SOMETHIN_WENT_WRONG);
	} 
		Person savePerson = personService.savePerson(files, person);
		if (savePerson != null) {
			System.out.println(savePerson.getDob());
			return new ResponseEntity<ResponseMessage>(
					new ResponseMessage( false, SAVE_PERSON, savePerson),
					HttpStatus.OK);

		}
		return new ResponseEntity<ResponseMessage>(new ResponseMessage( false,
				SOMETHIN_WENT_WRONG, savePerson), HttpStatus.OK);

	}

	@DeleteMapping(path = "/person/{id}")
	public ResponseEntity<ResponseMessage> deletePerson(@PathVariable("id") Integer id) throws IOException {
		return new ResponseEntity<ResponseMessage>(new ResponseMessage( false,
				Message.DELETE, personService.deletePerson(id)), HttpStatus.OK);
	}

	@GetMapping(path = "/person/{id}")
	public ResponseEntity<ResponseMessage> getPerson(@PathVariable("id") Integer id) {
		Person response = personService.getPersonById(id);
		System.out.println(response.getDob());
		return new ResponseEntity<ResponseMessage>(new ResponseMessage( false,
				Message.GET_DATA, response), HttpStatus.OK);

	}

	
}
