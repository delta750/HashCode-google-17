import java.util.ArrayList;

public class CashServer {

	int id;
	ArrayList<Video> videos = new ArrayList<Video>();
	int size;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<Video> getVideos() {
		return videos;
	}
	public void setVideos(ArrayList<Video> videos) {
		this.videos = videos;
	}

	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public CashServer(int id, int size) {
		this.id = id;
		this.size = size;
	}
	
	public void updateCash(Video video){
		videos.add(video);
		size-=video.getSize();
		
	}
	
}
