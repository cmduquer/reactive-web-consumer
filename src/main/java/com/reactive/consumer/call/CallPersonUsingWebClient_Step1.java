package com.reactive.consumer.call;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;

import com.reactive.consumer.model.Person;

import reactor.core.publisher.Mono;

public class CallPersonUsingWebClient_Step1 {

	private static final Logger logger = LoggerFactory.getLogger(CallPersonUsingWebClient_Step1.class);

	private static WebClient client = WebClient.create("http://localhost:8080");

	public static void main(String[] args) {

		Instant start = Instant.now();
		

		for (int i = 1; i <= 5; i++) {
			client.get().uri("/person/{id}", i).retrieve().bodyToMono(Person.class);
		}
		
		/*
		 * for (int i = 1; i <= 5; i++) { client.get().uri("/person/{id}",
		 * i).retrieve().bodyToMono(Person.class).subscribe(); }
		 * 
		 * for (int i = 1; i <= 5; i++) { client.get().uri("/person/{id}",
		 * i).retrieve().bodyToMono(Person.class).block(); }
		 * 
		 * List<Mono<Person>> list = Stream.of(1, 2, 3, 4, 5) .map(i ->
		 * client.get().uri("/person/{id}", i).retrieve().bodyToMono(Person.class))
		 * .collect(Collectors.toList());
		 * 
		 * Mono.when(list).block();
		 */


		logTime(start);
	}

	private static void logTime(Instant start) {
		logger.debug("Elapsed time: " + Duration.between(start, Instant.now()).toMillis() + "ms");
	}

}
