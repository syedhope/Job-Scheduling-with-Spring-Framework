package com.Syed.Spring;

import java.util.Map;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	private static Set<Integer> SAN = new HashSet<Integer>(); // Set of Amicable Numbers...
    private static Set<String> MID = new HashSet<String>(); // Set of Message Ids...   
    static {
		int n = 20000;
    	int sum[] = new int[n]; 
    	Arrays.fill(sum, 0);
    	for(int i=1;i<n/2;i++){
    		for(int j=i;j<n;j+=i){
    			if(j!=i){
    				sum[j]+=i;
				}
    		}	
    	}
    	for(int i=1;i<n;i++){
    		if(SAN.contains(i)){
				continue;
			}
    		int s = sum[i];
    		if ( s<20000 && sum[s]==i && s!=i){
    			SAN.add(i);
    			SAN.add(s);
    		}
    	}
    }
    @RequestMapping(value="/message", method=RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> index(@RequestBody Map<String, String> CallJsonObject) {
    	if (MID.contains(CallJsonObject.get("MissionID")))
    		return new ResponseEntity<String>("Error -  missionId "+CallJsonObject.get("MissionID")+" Has already been received...", HttpStatus.CONFLICT);
    	else
    		MID.add(CallJsonObject.get("MissionID")); // Mission ID...
    	Integer S = Integer.parseInt(CallJsonObject.get("Seed")); //Seed...
    	int sum = 0;
    	for(Integer i : SAN){
    		if (i<S){
    			sum+=i;
			}
    	}
		System.out.println("the sum of all the amicable numbers under "+S);
		return new ResponseEntity<String>(String.valueOf(sum), HttpStatus.OK);
    }
}