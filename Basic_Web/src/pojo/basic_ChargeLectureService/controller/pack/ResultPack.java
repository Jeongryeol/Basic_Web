package pojo.basic_ChargeLectureService.controller.pack;

public class ResultPack {

	private boolean isRedirect = false; //true:sendRedirect | false:forward
	private String	path = null;		//목적페이지 경로\
	
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
