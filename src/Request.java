import java.util.ArrayList;

public class Request {

	int requestedVideo;
	int numberOfRequests;
	int idEndPoint;
	
	
	
	public Request(int requestedVideo, int numberOfRequests, int idEndPoint) {
		super();
		this.requestedVideo = requestedVideo;
		this.numberOfRequests = numberOfRequests;
		this.idEndPoint = idEndPoint;
	}
	public int getRequestedVideo() {
		return requestedVideo;
	}
	public void setRequestedVideo(int requestedVideo) {
		this.requestedVideo = requestedVideo;
	}
	public int getNumberOfRequests() {
		return numberOfRequests;
	}
	public void setNumberOfRequests(int numberOfRequests) {
		this.numberOfRequests = numberOfRequests;
	}
	public int getIdEndPoint() {
		return idEndPoint;
	}
	public void setIdEndPoint(int idEndPoint) {
		this.idEndPoint = idEndPoint;
	}
	
	
	
	
	
	
	
}
