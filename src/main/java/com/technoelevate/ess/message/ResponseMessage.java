package com.technoelevate.ess.message;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.technoelevate.ess.dto.Person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMessage {
	
	private boolean error;
	private String message;
	private Person data;
}
