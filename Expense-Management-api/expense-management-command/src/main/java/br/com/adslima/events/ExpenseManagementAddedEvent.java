/**
 * 
 */
package br.com.adslima.events;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.adslima.model.ExpenseCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author andrews.silva
 *
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseManagementAddedEvent {

	private String id;
	private Integer userCode;
	private String description;
	private LocalDateTime date;
	private BigDecimal value;
	private ExpenseCategory category;

}
