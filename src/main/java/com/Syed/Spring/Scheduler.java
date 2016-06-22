package com.Syed.Spring;

import java.util.Random;
import java.util.Map;
import java.util.HashMap;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.apache.http.entity.StringEntity;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.HttpClientBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Scheduler {
	@Scheduled(fixedRate=3000)
	public void CreateRequest(){        
		Random r = new Random();
		Integer MissionID = r.nextInt((800) + 1) + 100; // Mission ID
		Integer Seed = r.nextInt((19000) + 1) + 1000; // seed between 1000 and 20,000
		Map<String, String> FullJSONObject = new HashMap<>();
		FullJSONObject.put("Seed", String.valueOf(Seed));
		FullJSONObject.put("MissionID",String.valueOf(MissionID));
        HttpClient httpClient = HttpClientBuilder.create().build();

        try {
            HttpPost URL = new HttpPost("http://localhost:8080/message");
			String JsonRequest = new ObjectMapper().writeValueAsString(FullJSONObject);
            StringEntity fields = new StringEntity(JsonRequest);
			System.out.println("Send Message :"+JsonRequest);
            URL.addHeader("content-type", "application/json");
            URL.addHeader("Accept","application/json");
            URL.setEntity(fields);
            HttpResponse reply = httpClient.execute(URL);
            HttpEntity ResponseEntity = reply.getEntity();
            String output = ResponseEntity != null ? EntityUtils.toString(ResponseEntity) : null;
            System.out.println(reply.getStatusLine()+"-->"+ output);
        }
        catch (HttpClientErrorException e){
            System.out.println("HttpStatus: "+((HttpResponse) e).getEntity());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
