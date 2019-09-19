package com.ss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/")
@SpringBootApplication
public class CounterApplication {
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@Value("${counter_file_location}")
	private String counter_file_location;

	public static void main(String[] args) {
		SpringApplication.run(CounterApplication.class, args);
	}

	@GetMapping("/counter")
	public String getCounter() {
		return "Your login counter in my app is " + storeCounter();
	}
	
	private Integer storeCounter() {
		Integer count = 0;
		Resource res = resourceLoader.getResource("file:" + counter_file_location);
		try {		
			BufferedReader br = new BufferedReader(new FileReader(res.getFile()));
			String cnStr = br.readLine();
			BufferedWriter bw = new BufferedWriter(new FileWriter(res.getFile()));	
			if(cnStr != null) {				
				count = Integer.parseInt(cnStr) + 1;
				bw.write(""+count+"\n");
			} else {
				count = count + 1;
				bw.write(""+count+"\n");
			}
			bw.close();	
			br.close();
			

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return count;
	}
}
