package Semi.message.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MessageDetail {

	private int messageNo;
	private int sendNo;
	private String messageDate;
	private String messageContent;
	private char sendDelete;
	private int receiveNo;
	private String receiveDate;
 	private char receiveDelete;
	
}
