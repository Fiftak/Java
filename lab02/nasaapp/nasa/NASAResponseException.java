package nasa;

public class NASAResponseException extends Exception {

	/**
	 * 
	 */
	private int iErrorCode = 0;
	private String szMsg;
	private String szBody;
	private static final long serialVersionUID = 8933288949897761909L;
	
	public NASAResponseException(String string)
	{
		szMsg = string;
	}
	public NASAResponseException(String string, int iErrorCode) {
		// TODO Auto-generated constructor stub
		szMsg = string;
		this.iErrorCode = iErrorCode;
	}
	
	public NASAResponseException(String string, int iErrorCode, String szBody) {
		// TODO Auto-generated constructor stub
		szMsg = string;
		this.iErrorCode = iErrorCode;
		this.szBody = szBody;
	}
	
	@Override
	public String getMessage()
	{
		if(iErrorCode != 0) {
			if(szBody == null)
				return szMsg + " " + Integer.toString(iErrorCode);
			else
				return szMsg + " " + Integer.toString(iErrorCode) + "\n" + szBody;
		}
		return szMsg;
	}
	
}
