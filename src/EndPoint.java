import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;

public class EndPoint {

	int id;
	int latency;
	int numberOfCacheServers;
	ArrayList<Connection> connections;
	ArrayList<Integer> cachesLatency;
	ArrayList<Integer> cachesId;
	ArrayList<Request> requests ;
	ArrayList<Video> videos ;
	
	public ArrayList<Video> getVideos() {
		return videos;
	}
	public void setVideos(ArrayList<Video> videos) {
		this.videos = videos;
	}
	public EndPoint(int id,int latency, int numberOfCacheServers,ArrayList<Integer> cachesId, ArrayList<Integer> cachesLatency) {
		this.id = id;
		this.latency = latency;
		this.numberOfCacheServers = numberOfCacheServers;
		this.cachesId = cachesId;
		this.cachesLatency =cachesLatency;
		this.connections = new ArrayList<Connection>();
		for(int i = 0 ; i < this.cachesId.size() ; i++){
			this.connections.add(new Connection(this.cachesId.get(i),this.cachesLatency.get(i)));
		}
		Collections.sort(connections, new Comparator<Connection>(){
			
			public int compare(Connection o1, Connection o2){
				if(o1.cacheLatency < o2.cacheLatency){
					return 1;
				}else if (o1.cacheLatency > o2.cacheLatency){
					return -1;
				}
				return 0;
			}
		});
	}
	
	public ArrayList<Connection> getConnections() {
		return connections;
	}
	public void setConnections(ArrayList<Connection> connections) {
		this.connections = connections;
	}
	public EndPoint() {
	}
	
	
	public int getLatency() {
		return latency;
	}
	public void setLatency(int latency) {
		this.latency = latency;
	}
	public int getNumberOfCacheServers() {
		return numberOfCacheServers;
	}
	public void setNumberOfCacheServers(int numberOfCacheServers) {
		this.numberOfCacheServers = numberOfCacheServers;
	}
	
	public ArrayList<Integer> getCachesId() {
		return cachesId;
	}
	public void setCachesId(ArrayList<Integer> cachesId) {
		this.cachesId = cachesId;
	}
	public ArrayList<Integer> getCachesLatency() {
		return cachesLatency;
	}
	public void setCachesLatency(ArrayList<Integer> cachesLatency) {
		this.cachesLatency = cachesLatency;
	}
	public ArrayList<Request> getRequests() {
		return requests;
	}
	public void setRequests(ArrayList<Request> requests) {
		this.requests = requests;
	}
	
	public void saveItsRequest(){
		ArrayList<Request> newRequests = new ArrayList<Request>();
		for(int i = 0; i < this.requests.size() ; i++){
			if(this.requests.get(i).getIdEndPoint() == this.id)newRequests.add(this.requests.get(i));
		}
		requests = newRequests;
		
	}
	public void requestSorting(){
		Collections.sort(requests, new Comparator<Request>() {
		    @Override
		    public int compare(Request o1, Request o2) {
		        if( o1.getNumberOfRequests() > o2.getNumberOfRequests() ){
		        	return 1;
		        }
		        return -1;
		    }
		});
	}
	
	public class Connection{
		int cacheId;
		int cacheLatency;
		
		public int getCacheId() {
			return cacheId;
		}

		public void setCacheId(int cacheId) {
			this.cacheId = cacheId;
		}

		public int getCacheLatency() {
			return cacheLatency;
		}

		public void setCacheLatency(int cacheLatency) {
			this.cacheLatency = cacheLatency;
		}

		Connection(int cacheId, int cacheLatency){
			this.cacheId = cacheId;
			this.cacheLatency = cacheLatency;
		}
	}
}



