package in.trackerapi.service;

import java.sql.Date;
import java.util.List;

import in.trackerapi.entity.SpecializedP;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

// 3. create an Expense Service
public interface SpecializedPService {
	
	Page<SpecializedP> getAllExpenses(Pageable page);
	
	SpecializedP getExpenseById(Long id);
	
	void deleteExpenseById(Long id);

	SpecializedP saveExpenseDetails(SpecializedP expense);
	
	SpecializedP updateExpenseDetails(Long id, SpecializedP expense);
	
	List<SpecializedP> readByCategory(String category, Pageable page);
	
	List<SpecializedP> readByName(String keyword, Pageable page);
	
	List<SpecializedP> readByDate(Date startDate, Date endDate, Pageable page);
}
