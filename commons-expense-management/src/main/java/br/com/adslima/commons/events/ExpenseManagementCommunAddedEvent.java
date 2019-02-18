///**
// * 
// */
//package br.com.adslima.commons.events;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//
//import br.com.adslima.commons.model.ExpenseCategory;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//
//
///**
// * @author andrews.silva
// *
// */
//@Getter
//@Setter
//@ToString
//@NoArgsConstructor
//public class ExpenseManagementCommunAddedEvent extends AbstractCommonsEvent {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	private String id;
//	private Integer userCode;
//	private String description;
//	private LocalDateTime date;
//	private BigDecimal value;
//	private ExpenseCategory category;
//
//	/**
//	 * @param id
//	 */
//	public ExpenseManagementCommunAddedEvent(String id, Integer userCode, String description, LocalDateTime date,
//			BigDecimal value, ExpenseCategory category) {
//		super(id);
//		this.userCode = userCode;
//		this.description = description;
//		this.date = date;
//		this.value = value;
//		this.category = category;
//	}
//
//}