/*
 * Copyright 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.gcm.server.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.healthcare.controller.HomeController;

/**
 * Servlet that adds a new message to all registered devices.
 * <p>
 * This servlet is used just by the browser (i.e., not device).
 */

public class GCMHttpSender {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	private static final int MULTICAST_SIZE = 1000;

	private Sender sender;

	private static final Executor threadPool = Executors.newFixedThreadPool(5);

	// @Override
	// public void init(ServletConfig config) throws ServletException {
	// super.init(config);
	// sender = newSender(config);
	// }

	/**
	 * Creates the {@link Sender} based on the servlet settings.
	 */
	// protected Sender newSender(ServletConfig config) {
	// String key = (String) config.getServletContext()
	// .getAttribute(ApiKeyInitializer.ATTRIBUTE_ACCESS_KEY);
	// return new Sender(key);
	// }

	/**
	 * Processes the request to add a new message.
	 * 
	 * @throws IOException
	 */
	// @Override
	public void send(Message message) throws IOException {

		this.sender = new Sender("AIzaSyB2TTsHaCHrj9kufCgwl675kMrKcuslyrI");
		List<String> devices = Datastore.getDevices();
		String status;
		if (devices.isEmpty()) {
			status = "Message ignored as there is no device registered!";
		} else {
			// NOTE: check below is for demonstration purposes; a real
			// application
			// could always send a multicast, even for just one recipient
			if (devices.size() == 1) {
				// send a single message using plain post
				String registrationId = devices.get(0);
				
				Result result = sender.send(message, registrationId, 5);
				status = "Sent message to one device: " + result;
			} else {
				// send a multicast message using JSON
				// must split in chunks of 1000 devices (GCM limit)
				int total = devices.size();
				List<String> partialDevices = new ArrayList<>(total);
				int counter = 0;
				int tasks = 0;
				for (String device : devices) {
					counter++;
					partialDevices.add(device);
					int partialSize = partialDevices.size();
					if (partialSize == MULTICAST_SIZE || counter == total) {
						asyncSend(partialDevices, message);
						partialDevices.clear();
						tasks++;
					}
				}
				status = "Asynchronously sending " + tasks
						+ " multicast messages to " + total + " devices";
			}
		}
		logger.info("status : " + status);
		// req.setAttribute(HomeServlet.ATTRIBUTE_STATUS, status.toString());
		// getServletContext().getRequestDispatcher("/home").forward(req, resp);
	}

	private void asyncSend(List<String> partialDevices, Message msg) {
		// make a copy
		final List<String> devices = new ArrayList<>(partialDevices);
		final Message message = msg;
		threadPool.execute(new Runnable() {

			public void run() {
				MulticastResult multicastResult;
				try {
					multicastResult = sender.send(message, devices, 5);
				} catch (IOException e) {
					logger.error("Error posting messages", e);
					return;
				}
				List<Result> results = multicastResult.getResults();
				// analyze the results
				for (int i = 0; i < devices.size(); i++) {
					String regId = devices.get(i);
					Result result = results.get(i);
					String messageId = result.getMessageId();
					if (messageId != null) {
						logger.info("Succesfully sent message to device: "
								+ regId + "; messageId = " + messageId);
						String canonicalRegId = result
								.getCanonicalRegistrationId();
						if (canonicalRegId != null) {
							// same device has more than on registration id:
							// update it
							logger.info("canonicalRegId " + canonicalRegId);
							Datastore.updateRegistration(regId, canonicalRegId);
						}
					} else {
						String error = result.getErrorCodeName();
						if (error.equals(Constants.ERROR_NOT_REGISTERED)) {
							// application has been removed from device -
							// unregister it
							logger.info("Unregistered device: " + regId);
							Datastore.unregister(regId);
						} else {
							logger.error("Error sending message to " + regId
									+ ": " + error);
						}
					}
				}
			}
		});
	}
	
	public static void main(String [] args) {
		
				
	  }

}
