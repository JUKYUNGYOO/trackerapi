package in.trackerapi.repository;

import java.sql.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.trackerapi.entity.SpecializedP;

// 2. create a jpa repository for expense entity
@Repository
public interface SpecializedPRepository extends JpaRepository<SpecializedP, Long> {
	
	//SELECT * FROM tbl_expenses WHERE category=?
	Page<SpecializedP> findByUserIdAndCategory(Long userId, String category, Pageable page);

	//SELECT * FROM tbl_expenses WHERE name LIKE '%keyword%'
	Page<SpecializedP> findByUserIdAndNameContaining(Long userId, String keyword, Pageable page);
	
	
	//SELECT * FROM tbl_expenses WHERE date BETWEEN 'startDate' AND 'endDate'
	Page<SpecializedP> findByUserIdAndDateBetween(Long userId, Date startDate, Date endDate, Pageable page);

	Page<SpecializedP> findByUserId(Long userId, Pageable page);

	Optional<SpecializedP> findByUserIdAndId(Long userId, Long expenseId);
}
