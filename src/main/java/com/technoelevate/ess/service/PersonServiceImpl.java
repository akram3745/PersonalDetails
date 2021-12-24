package com.technoelevate.ess.service;

import static com.technoelevate.ess.message.Message.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import com.technoelevate.ess.dto.EmergencyContactDetails;
import com.technoelevate.ess.dto.Person;
import com.technoelevate.ess.dto.PersonDocument;
import com.technoelevate.ess.exception.CustomException;
import com.technoelevate.ess.pojo.EmergencyContactDetailsPojo;
import com.technoelevate.ess.pojo.PersonPojo;
import com.technoelevate.ess.repository.PersonDocumentRepository;
import com.technoelevate.ess.repository.PersonRepository;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice.This;
@SuppressWarnings("unused")
@Slf4j
@Service
@Validated
@Transactional
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;
  
	@Autowired
	private PersonDocumentRepository documentRepository;
	
	@Valid Person person = new Person();
	   @Valid EmergencyContactDetails emergencyContactDetails = new EmergencyContactDetails();

	private Path directory;
	private String dir;;

	@SuppressWarnings("unchecked")
	@Override
	public Person savePerson(List<MultipartFile> multipartFile, @Valid PersonPojo person2) {

		EmergencyContactDetailsPojo emergencyContactDetailspojo = person2.getEmergencyContactDetails();
		
		BeanUtils.copyProperties(emergencyContactDetailspojo, emergencyContactDetails);
		BeanUtils.copyProperties(person2, person);
		person.setDob(LocalDate.parse(person2.getDob()));
		person.setEmergencyContactDetails(emergencyContactDetails);
		List<PersonDocument> list = new ArrayList();
		

		try {

			if (person.getPersonId() > 0) {
				Person person3 = personRepository.findByPersonId(person.getPersonId());
				if (person != null) {
					documentRepository.deleteAll(person3.getPersonDocuments());
					String pathString = person2.getCandidateName() + person2.getContactNo();
					this.directory = getPath(pathString);
					try {

						FileUtils.forceDelete(new java.io.File(this.directory.toString()));

					} catch (Exception e) {
						throw new CustomException(FILE);
					}

				}

			}
			try {
				this.directory = getPath(person.getCandidateName() + person.getContactNo());
				Files.createDirectories(this.directory);
			} catch (Exception e1) {
				throw new CustomException(SOMETHIN_WENT_WRONG);
			}

			for (MultipartFile file : multipartFile) {
				try {
					String filename = file.getOriginalFilename();
					Path path = this.directory.resolve(filename);
					System.out.println(path);
					Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
					PersonDocument document = new PersonDocument(filename, file.getContentType(), path.toString());
					list.add(document);
				} catch (Exception e) {
					throw new CustomException(SOMETHIN_WENT_WRONG);
				}
			}
			person.setPersonDocuments(list);

			return personRepository.save(person);

		} catch (CustomException exception) {
			throw exception;
		} catch (Exception exception) {
			throw new CustomException(SOMETHIN_WENT_WRONG);
		}

	}

	public Path getPath(String filename) {
		String dir = "E:\\images\\";
		this.dir = dir + "\\" + filename;
		this.directory = Paths.get(this.dir).toAbsolutePath().normalize();
		return this.directory;

	}

	@Override
	public Person deletePerson(int id) throws IOException {
		try {

			person = personRepository.findByPersonId(id);
			if (person != null) {
				personRepository.delete(person);
				String pathString = person.getCandidateName() + person.getContactNo();
				this.directory = getPath(pathString);
				try {
					FileUtils.forceDelete(new File(this.directory.toString()));
				} catch (Exception e) {
					throw new CustomException(FILE);
				}

			} else {
				throw new CustomException(INVALIDID);
			}
			return person;
		} catch (CustomException exception) {
			throw exception;
		} catch (Exception e) {
			throw new CustomException(SOMETHIN_WENT_WRONG);
		}
	}

	@Override
	public Person getPersonById(int id) {
		try {

			return personRepository.findByPersonId(id);

		} catch (Exception e) {
			throw new CustomException(SOMETHIN_WENT_WRONG);
		}
	}

}
