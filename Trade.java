import java.time.*;

public class Trade {
private String tradeId;
private int version;
private String counterPartyId;
private String bookId;
private LocalDate maturityDate;
private LocalDate createdDate;
private String expired;



public Trade(String tradeId, int version, String counterPartyId, String bookId, LocalDate maturityDate,
		LocalDate createdDate, String expired) {
	super();
	this.tradeId = tradeId;
	this.version = version;
	this.counterPartyId = counterPartyId;
	this.bookId = bookId;
	this.maturityDate = maturityDate;
	this.createdDate = createdDate;
	this.expired = expired;
}
public String getTradeId() {
	return tradeId;
}
public void setTradeId(String tradeId) {
	this.tradeId = tradeId;
}
public int getVersion() {
	return version;
}
public void setVersion(int version) {
	this.version = version;
}
public String getCounterPartyId() {
	return counterPartyId;
}
public void setCounterPartyId(String counterPartyId) {
	this.counterPartyId = counterPartyId;
}
public String getBookId() {
	return bookId;
}
public void setBookId(String bookId) {
	this.bookId = bookId;
}
public LocalDate getMaturityDate() {
	return maturityDate;
}
public void setMaturityDate(LocalDate maturityDate) {
	this.maturityDate = maturityDate;
}
public LocalDate getCreatedDate() {
	return createdDate;
}
public void setCreatedDate(LocalDate createdDate) {
	this.createdDate = createdDate;
}
public String isExpired() {
	return expired;
}
public void setExpired(String expired) {
	expired = expired;
}

public String toString() {
    return "Trade{" +
            "tradeId='" + tradeId + '\'' +
            ", version=" + version +
            ", counterParty='" + counterPartyId + '\'' +
            ", bookId='" + bookId + '\'' +
            ", maturityDate=" + maturityDate +
            ", createdDate=" + createdDate +
            ", expiredFlag='" + expired + '\'' +
            '}';
}
}
