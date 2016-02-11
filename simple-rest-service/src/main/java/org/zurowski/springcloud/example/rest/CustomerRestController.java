package org.zurowski.springcloud.example.rest;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/customers")
@RestController
public class CustomerRestController {

	private final Map<Long, Customer> customers;

	public CustomerRestController() {
		customers = new HashMap<>();
		customers.put(1l, Customer.create(1, "Thomas", "Seibert"));
		customers.put(2l, Customer.create(2, "John", "Rhoton"));
		customers.put(3l, Customer.create(3, "Gregor", "Zurowski"));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Customer> get(@PathVariable Long id) {
		return customers.containsKey(id) ? ResponseEntity.ok(customers.get(id))
				: new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<Customer>> getAll() {
		return ResponseEntity.ok(customers.values());
	}

}
