import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	public static ArrayList<Video> videos = new ArrayList<Video>();
	public  static ArrayList<EndPoint> endPoints = new ArrayList<EndPoint>();
	public static ArrayList<Request> requests = new ArrayList<Request>();
	public static ArrayList<CashServer> caches = new  ArrayList<CashServer>();
	
	public static void main(String[] args) throws FileNotFoundException 
    {
		readFile("src\\videos_worth_spreading.in");
		for(int i = 0 ; i < endPoints.size() ; i ++){
			endPoints.get(i).setRequests(requests);
			endPoints.get(i).requestSorting();
		}
		cacheOptimizer();
		writeFile(new File("src\\videos_worth_spreading.out"));
    }
	
	public static void cacheOptimizer(){
		
		for(int i=0; i<endPoints.size();i++){
			EndPoint current = endPoints.get(i);
			for(int j=0 ;j<current.getRequests().size();j++){
				int id = current.getRequests().get(j).getRequestedVideo();
				for(int k = 0; k < current.getConnections().size(); k++){
					if( videos.get(id).getSize()< caches.get(current.getConnections().get(k).getCacheId()).getSize() ){
						caches.get(current.getConnections().get(k).getCacheId()).updateCash(videos.get(id));
					}
				}
			}
			
			
		}
		
	}
	public static void readFile(String pathTxt) throws FileNotFoundException{
		
		Path path = Paths.get(pathTxt);
		if(!Files.exists(path,new LinkOption[]{ LinkOption.NOFOLLOW_LINKS})){
		   	throw new FileNotFoundException();
		}
		
		try (BufferedReader reader = Files.newBufferedReader(path)){
			
			String line = null;
			StringTokenizer tokens;
			line=reader.readLine();
			tokens = new StringTokenizer(line);	
			 int numberOfVideos = Integer.parseInt(tokens.nextToken());
			 int numberOfendPoints = Integer.parseInt(tokens.nextToken());
			 int numberOfrequests = Integer.parseInt(tokens.nextToken());
			 int numberOfCaches = Integer.parseInt(tokens.nextToken());
			 int cachesSize = Integer.parseInt(tokens.nextToken());
			 
			 line=reader.readLine();
			 tokens = new StringTokenizer(line);	
			 
			 for(int i = 0 ; i < numberOfVideos ; i++){
				 Video video = new Video(i,Integer.parseInt(tokens.nextToken()));
				 videos.add(video);
			 }
			 for(int i = 0 ; i < numberOfendPoints ; i++){
				 line=reader.readLine();
				 tokens = new StringTokenizer(line);
				 
				 int latency = Integer.parseInt(tokens.nextToken());
				 int numberOfCacheConnections = Integer.parseInt(tokens.nextToken());
				 ArrayList<Integer> cachesLatency = new  ArrayList<Integer>();
					ArrayList<Integer> cachesId = new  ArrayList<Integer>();
				 for(int j = 0 ; j < numberOfCacheConnections ; j++){
					 line=reader.readLine();
					 tokens = new StringTokenizer(line);
					 cachesId.add(Integer.parseInt(tokens.nextToken()));
					 cachesLatency.add(Integer.parseInt(tokens.nextToken()));
				
				 }
				 EndPoint endPoint = new EndPoint(i,latency,numberOfCacheConnections,cachesId,cachesLatency);
				 endPoints.add(endPoint);
			 }
			 for(int i = 0 ; i < numberOfrequests ; i++){
				 line=reader.readLine();
				 tokens = new StringTokenizer(line);
				 Request request = new Request(Integer.parseInt(tokens.nextToken()),Integer.parseInt(tokens.nextToken()),Integer.parseInt(tokens.nextToken()));
				 requests.add(request);
			 }
			 
			 for(int i = 0; i < numberOfCaches; i++){
				 caches.add(new CashServer(i, cachesSize));
			 }
			 
		}catch (IOException e){
			System.err.format("Error reading file.");
			//e.printStackTrace();
		}
	}
	
	
	public static void writeFile(File f){
		Writer writer = null;
		try{
			
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));
			writer.write(caches.size()+"\n");
			for(int i = 0 ; i <caches.size();i++){
				writer.write(caches.get(i).getId() + " ");
				for(int j = 0; j < caches.get(i).getVideos().size(); j++){
					writer.write(caches.get(i).getVideos().get(j).getId() + " ");
				}
				writer.write("\n");
			}
			
		}catch (IOException e){
			System.err.format("Error creating file.");
			//e.printStackTrace();
		}finally {
			if(writer != null){
				try{
					writer.close();
				}catch(IOException e) {
					System.out.println("Error Closing file.");
					//e.printStackTrace();
				}
			}
		}
	}
}
