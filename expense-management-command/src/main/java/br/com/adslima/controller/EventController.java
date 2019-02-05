package br.com.adslima.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

/**
 * 
 * @author andrews.silva
 *
 */
@RestController
@AllArgsConstructor
@RequestMapping("/events")
public class EventController {

	private EventStore eventStore;

	@GetMapping
	@RequestMapping("/{aggregateId}")
	@Transactional(readOnly = true)
	public List<Object> listEvents(@PathVariable String aggregateId) {
		return eventStore.readEvents(aggregateId).asStream().collect(Collectors.toList());
	}
}