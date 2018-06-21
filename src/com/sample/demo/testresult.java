package report;

public class testresult {

	public String testCaseID;
	public String testCaseName; 
	public String status;
	
	public testresult(String testCaseID, String testCaseName, String status) {
		super();
		this.testCaseID = testCaseID;
		this.testCaseName = testCaseName;
		this.status = status;
	}
}
