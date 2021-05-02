import java.time.*;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

public class StoreKeeper {

	TreeMap<String, Trade> store = new TreeMap<>();

	public static void main(String[] args) {
		StoreKeeper sk=new StoreKeeper();
		sk.createStore();
		sk.updateExpiryFlagOfTrade();
		sk.printStore();

	}
	
	public void printStore() {
		store.forEach((k,v) -> System.out.println("Existing store:: " +v.toString() ));
	}

	public TreeMap<String, Trade> createStore() {

		Trade t1 = new Trade("T1", 1, "CP-1", "B1", LocalDate.parse("2021-05-20"), LocalDate.now(), "N");
		addToStore(t1);
		
		Trade t2 = new Trade("T2", 2, "CP-2", "B1", LocalDate.parse("2021-05-20"), LocalDate.now(), "N");
		addToStore(t2);
		
		// Handling case of lower version trade being rejected
		Trade t3 = new Trade("T2", 1, "CP-2", "B1", LocalDate.parse("2021-05-20"), LocalDate.parse("2015-03-14"), "N");
		addToStore(t3);
		
		Trade t4 = new Trade("T3", 1, "CP-3", "B1", LocalDate.parse("2021-05-20"), LocalDate.parse("2015-03-14"), "N");
		addToStore(t4);
		
		//Handling the case of lower maturity date trade not allowed.
		Trade t5 = new Trade("T5", 1, "CP-5", "B1", LocalDate.parse("2020-05-20"), LocalDate.parse("2015-03-14"), "N");
		addToStore(t5);
		
		
		return store;
	}

	public boolean addToStore(Trade trade) {
		boolean eligible = isValid(trade);
		if (eligible) {
			store.put(trade.getTradeId(), trade);
		}
		return eligible;

	}

	public boolean isValid(Trade trade) {
		
		if (validateMaturityDate(trade)) {
			// Trade exsitingTrade = tradeDao.findTrade(trade.getTradeId());
			if (store.containsKey(trade.getTradeId())) {
				Trade exsitingTrade = store.get(trade.getTradeId());	
				
				try {
					return validateVersion(trade, exsitingTrade);
				}
				catch(InvalidTradeException exception) {
					exception.printStackTrace();
				}
			}else {
				return true;
			}
		}
		return false;		
	}

	private boolean validateVersion(Trade trade, Trade oldTrade) {
		// validation 1 During transmission if the
		// lower version is being received by the store it will reject the trade and
		// throw an exception.
		if (trade.getVersion() >= oldTrade.getVersion()) {
			return true;
		}
		 throw new InvalidTradeException(trade.getTradeId()+"  Trade version is invalid");
		//return false;
		
	}

	// 2. Store should not allow the trade which has less maturity date then today
	// date
	private boolean validateMaturityDate(Trade trade) {
		boolean validMaturity = trade.getMaturityDate().isBefore(LocalDate.now());
		if(validMaturity)
			System.out.println("Maturity date for trade " + trade.getTradeId() +" is not valid");
		return !validMaturity;
	}


	public void updateExpiryFlagOfTrade() {
	
		store.values().forEach(t -> {
			if (!validateMaturityDate(t)) {
				t.setExpired("Y");
			}
		
		});
	}
}
