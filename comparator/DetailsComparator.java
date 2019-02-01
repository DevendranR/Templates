package comparator;


import java.util.Comparator;

public class DetailsComparator implements Comparator<Transaction>{

	@Override
	public int compare(Transaction o1, Transaction o2) {
		
		int flag = o1.getClientId().compareTo(o2.getClientId());
		if(flag == 0){
			flag = o1.getTransactionType().compareTo(o2.getTransactionType());
			if(flag == 0){
				flag = o1.getTransactionDate().compareTo(o2.getTransactionDate());
				if(flag == 0){
					flag = o1.getPriorityFlag().compareTo(o2.getPriorityFlag());
				}
			}
		}
		return flag;
	}


		
}
