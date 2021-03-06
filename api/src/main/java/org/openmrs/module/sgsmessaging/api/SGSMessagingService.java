/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.sgsmessaging.api;

import java.io.IOException;
import java.util.List;

import org.apache.http.auth.AuthenticationException;
import org.apache.http.client.ClientProtocolException;
import org.openmrs.Person;
import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.sgsmessaging.domain.SGSMessagingMessage;
import org.springframework.transaction.annotation.Transactional;

/**
 * This service exposes module's core functionality. It is a Spring managed bean which is configured
 * in moduleApplicationContext.xml.
 * <p>
 * It can be accessed only via Context:<br>
 * <code>
 * Context.getService(SGSMessagingService.class).someMethod();
 * </code>
 * 
 * @see org.openmrs.api.context.Context
 */
public interface SGSMessagingService extends OpenmrsService {
	
	/**
	 * @return all messages
	 */
	@Transactional(readOnly = true)
	public List<SGSMessagingMessage> getAllMessages();
	
	/**
	 * @param messageId
	 * @return the message with the corresponding messageId
	 */
	@Transactional(readOnly = true)
	public SGSMessagingMessage getMessage(Integer messageId);
	
	/**
	 * @param sender
	 * @return all messages that sender has sent
	 */
	@Transactional(readOnly = true)
	public List<SGSMessagingMessage> getMessagesFromPerson(Person sender);
	
	/**
	 * @param recipient
	 * @return all messages that recipient has received
	 */
	@Transactional(readOnly = true)
	public List<SGSMessagingMessage> getMessagesToPerson(Person recipient);
	
	/**
	 * @param person
	 * @return all messages to or from person
	 */
	@Transactional(readOnly = true)
	public List<SGSMessagingMessage> getMessagesToOrFromPerson(Person person);
	
	/**
	 * Create or update message
	 */
	@Transactional
	public void saveMessage(SGSMessagingMessage message) throws APIException;
	
	/**
	 * Delete message
	 */
	@Transactional
	public void deleteMessage(SGSMessagingMessage message) throws APIException;
	
	@Transactional(readOnly = true)
	public List<SGSMessagingMessage> getOutboxMessages();
	
	void sendLabResultsNotifications() throws AuthenticationException, ClientProtocolException, IOException;
	
	void sendAppointmentReminders() throws AuthenticationException, ClientProtocolException, IOException;
	
}
