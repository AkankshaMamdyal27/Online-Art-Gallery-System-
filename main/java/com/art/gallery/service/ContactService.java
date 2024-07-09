package com.art.galley.service;

import javax.mail.MessagingException;

import com.art.galley.entity.Contact;

public interface ContactService {
	
	void saveContacts(Contact contact) throws MessagingException;

}
