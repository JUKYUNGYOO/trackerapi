package in.trackerapi.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import in.trackerapi.entity.SpecializedP;
import in.trackerapi.exceptions.ResourceNotFoundException;
import in.trackerapi.repository.SpecializedPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

//4. Create an Expense Service implementation
@Service
public class SpecializedServiceImpl implements SpecializedPService {

	@Autowired
	private SpecializedPRepository expenseRepo;

	@Autowired
	private UserService userService;
	
	@Override
	public Page<SpecializedP> getAllExpenses(Pageable page) {
		return expenseRepo.findByUserId(userService.getLoggedInUser().getId(),
				page);
	}

	@Override
	public SpecializedP getExpenseById(Long id){
		Optional<SpecializedP> expense = expenseRepo.findByUserIdAndId(userService.getLoggedInUser().getId(),
				id);
		if (expense.isPresent()) {
			return expense.get();
		}
		throw new ResourceNotFoundException("Expense is not found for the id "+id);
	}

	@Override
	public void deleteExpenseById(Long id) {
		SpecializedP expense = getExpenseById(id);
		expenseRepo.deleteById(id);
	}

	@Override
	public SpecializedP saveExpenseDetails(SpecializedP expense) {
		expense.setUser(userService.getLoggedInUser());
		return expenseRepo.save(expense);
	}

	@Override
	public SpecializedP updateExpenseDetails(Long id, SpecializedP expense){
		SpecializedP existingExpense = getExpenseById(id);
		existingExpense.setName(expense.getName() != null ? expense.getName() : existingExpense.getName());
		existingExpense.setDescription(expense.getDescription() != null ? expense.getDescription() : existingExpense.getDescription());
		existingExpense.setCategory(expense.getCategory() != null ? expense.getCategory() : existingExpense.getCategory());
		existingExpense.setDate(expense.getDate() != null ? expense.getDate() : existingExpense.getDate());
		existingExpense.setAmount(expense.getAmount() != null ? expense.getAmount() : existingExpense.getAmount());
		return expenseRepo.save(existingExpense);
	}

	@Override
	public List<SpecializedP> readByCategory(String category, Pageable page) {
		return expenseRepo.findByUserIdAndCategory(userService.getLoggedInUser().getId(), category, page).toList();
	}

	@Override
	public List<SpecializedP> readByName(String keyword, Pageable page) {
		return expenseRepo.findByUserIdAndNameContaining(
				userService.getLoggedInUser().getId(),
				keyword, page).toList();
	}

	@Override
	public List<SpecializedP> readByDate(Date startDate, Date endDate, Pageable page) {
		
		if (startDate == null) {
			startDate = new Date(0);
		}
		
		if (endDate == null) {
			endDate = new Date(System.currentTimeMillis());
		}
		
		return expenseRepo.findByUserIdAndDateBetween
				(userService.getLoggedInUser().getId(), startDate, endDate, page).toList();
	}


}



























