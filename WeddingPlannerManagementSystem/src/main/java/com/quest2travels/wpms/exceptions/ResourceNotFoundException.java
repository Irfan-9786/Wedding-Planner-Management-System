package com.quest2travels.wpms.exceptions;


public class ResourceNotFoundException extends RuntimeException {
public ResourceNotFoundException() {
	super("Resource not found in database...!!!");
}
public ResourceNotFoundException(String message) {
	super(message);
}}

