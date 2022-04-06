package org.ntnu.MP3Editor;

import java.io.File;
import ws.schild.jave.Encoder;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;

public class FormatConverter {

	public static void main(String[] args) throws Exception {
		File source = new File("test.wav");
		File target = new File("test.mp3");
		
		AudioAttributes audio = new AudioAttributes();
		audio.setCodec("libmp3lame");
		audio.setBitRate(128000);
		audio.setChannels(2);
		audio.setSamplingRate(44100);
		 
		EncodingAttributes attrs = new EncodingAttributes();
		attrs.setOutputFormat("mp3");
		attrs.setAudioAttributes(audio);
		 
		Encoder encoder = new Encoder();
		encoder.encode(new MultimediaObject(source), target, attrs);
		
		System.out.println("Done!");
	}
}
