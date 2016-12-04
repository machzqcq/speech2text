package test.test;

import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;

public class TranscriberDemo {       
                                     
    public static void main(String[] args) throws Exception {
                                     
        Configuration configuration = new Configuration();

        configuration
                .setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        configuration
                .setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
        configuration
                .setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
        
        File dir = new File(".//1028-20100710-hne//wav");
        File[] directoryListing = dir.listFiles();
        ArrayList<String> resultArray = new ArrayList<String>();
        if (directoryListing != null) {       	
          for (File child : directoryListing) {
        	  String myresult = speechTotext(child,configuration);
              resultArray.add(myresult);
          }
        } else {
          System.out.println("Not a directory");
        }
        
        for(int i=0;i<resultArray.size();i++)
        {
        	System.out.println(resultArray.get(i));
        }
        
        
    }
    
    public static String speechTotext(File f, Configuration configuration) throws IOException
    {
    	StreamSpeechRecognizer recognizer = new StreamSpeechRecognizer(
                configuration);
        InputStream stream = new FileInputStream(f);

        recognizer.startRecognition(stream);
        SpeechResult result;
        String resultText = null;
        while ((result = recognizer.getResult()) != null) {
        	resultText = result.getHypothesis();
        }
        recognizer.stopRecognition();
		return resultText;
    }
    
    
}