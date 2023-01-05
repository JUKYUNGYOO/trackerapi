package in.trackerapi.controller;

import java.sql.Date;
import java.util.List;

import javax.validation.Valid;

import in.trackerapi.entity.SpecializedP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import in.trackerapi.service.SpecializedPService;

@RestController
public class SpecializedPController {

	@Autowired
	private SpecializedPService expenseService;
	
	@GetMapping("/expenses")
	public List<SpecializedP> getAllExpenses(Pageable page) {
		return expenseService.getAllExpenses(page).toList();
	}
	
	@GetMapping("/expenses/{id}")
	public SpecializedP getExpenseById(@PathVariable Long id){
		return expenseService.getExpenseById(id);
	}
	
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@DeleteMapping("/expenses")
	public void deleteExpenseById(@RequestParam Long id) {
		expenseService.deleteExpenseById(id);
	}
	
	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping("/expenses")
	public SpecializedP saveExpenseDetails(@Valid @RequestBody SpecializedP expense) {
		return expenseService.saveExpenseDetails(expense);
	}
	
	@PutMapping("/expenses/{id}")
	public SpecializedP updateExpenseDetails(@RequestBody SpecializedP expense, @PathVariable Long id){
		return expenseService.updateExpenseDetails(id, expense);
	}
	
	@GetMapping("/expenses/category")
	public List<SpecializedP> getExpensesByCategory(@RequestParam String category, Pageable page) {
		return expenseService.readByCategory(category, page);
	}
	
	@GetMapping("/expenses/name")
	public List<SpecializedP> getExpensesByName(@RequestParam String keyword, Pageable page) {
		return expenseService.readByName(keyword, page);
	}
	
	@GetMapping("/expenses/date")
	public List<SpecializedP> getExpensesByDates(@RequestParam(required = false) Date startDate,
												 @RequestParam(required = false) Date endDate,
												 Pageable page) {
		return expenseService.readByDate(startDate, endDate, page);
	}
}






















