package com.example.demo.codeofCreatefile;

import java.io.File;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class fileCreate {
	

@RequestMapping("/hi")
	public String fileCreaste() {
		try {
File f = new File("/home/nanditha/Desktop/errrrr.txt/texttt.txt");
//f.mkdir();
	f.createNewFile();
	
	
} catch (Exception e) {
	
}
		return "hiiiii";
	}

}
