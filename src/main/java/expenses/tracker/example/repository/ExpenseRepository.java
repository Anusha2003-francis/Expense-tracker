package expenses.tracker.example.repository;

import java.time.LocalDate;
import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import expenses.tracker.example.dto.CategoryExpenseDTO;
import expenses.tracker.example.dto.MonthlyExpenseDTO;
import expenses.tracker.example.entity.Expense;
import expenses.tracker.example.entity.User;

public interface ExpenseRepository extends JpaRepository<Expense,Integer> {
//fetch expense by user
	List<Expense> findByUser(User user);
// fetch expense by category
	List<Expense> findByUserAndCategory(User user,String category);
//fetch expense by date range
	List<Expense> findByUserAndDateBetween(User user,LocalDate startDate,LocalDate endDate);
//pageing
Page<Expense> findByUser(User user,Pageable pageable);
//monthly filter
@Query("SELECT COALESCE(SUM(e.amount), 0) " +
        "FROM Expense e " +
        "WHERE e.user = :user " +
        "AND MONTH(e.date) = :month " +
        "AND YEAR(e.date) = :year")
double  getTotalExpenseByMonth(@Param("user") User user,@Param("month")int month,@Param("year") int year);

@Query("SELECT new expenses.tracker.example.dto.CategoryExpenseDTO(e.category,SUM(amount))"+
"FROM Expense e WHERE e.user= :user GROUP BY e.category")
List<CategoryExpenseDTO> getcategorywiseExpense(@Param("user") User user);

@Query("""
	       SELECT new expenses.tracker.example.dto.MonthlyExpenseDTO(
	           MONTH(e.date),
	           YEAR(e.date),
	           SUM(e.amount)
	       )
	       FROM Expense e
	       WHERE e.user = :user
	       GROUP BY YEAR(e.date), MONTH(e.date)
	       ORDER BY YEAR(e.date), MONTH(e.date)
	       """)
	List<MonthlyExpenseDTO> getMonthlyExpenses(@Param("user") User user);


}
