package thermal;

public class CracControl {
	/*****************************
	 	FIELDS
	******************************/
	private int[] cracInfo;
	private int currentTempSetting;
	/*****************************
	 	GETTERS AND SETTERS
	******************************/
	public int[] getCracInfo() {
		return cracInfo;
	}
	public void setCracInfo(int[] cracInfo) {
		this.cracInfo = cracInfo;
	}
	public int getCurrentTempSetting() {
		return currentTempSetting;
	}
	public void setCurrentTempSetting(int currentTempSetting) {
		this.currentTempSetting = currentTempSetting;
	}
	/*****************************
	 	CUSTOMIZED METHODS
	******************************/
	public int updateRequiredCracTemp(int rct){//basically a setter but left in for completeness
		return rct;
	}
}
