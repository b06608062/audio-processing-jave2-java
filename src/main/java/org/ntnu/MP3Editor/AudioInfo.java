package org.ntnu.MP3Editor;

import java.io.File;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.info.MultimediaInfo; 

public class AudioInfo {

	public static void main(String[] args) {
		File file = new File("test.mp3");
		
		if (file != null && file.exists()) {
			try {
				MultimediaObject multimediaObject = new MultimediaObject(file);
				MultimediaInfo m = multimediaObject.getInfo();
				System.out.println("---- Information ----");
				for (Object objectName : m.getMetadata().keySet()) {
					System.out.println(objectName.toString().substring(0,1).toUpperCase() + objectName.toString().substring(1).toLowerCase() + ": " + m.getMetadata().get(objectName).toString());
                }
				System.out.println("Duration: " + m.getDuration() / 60000 + " m:" + (m.getDuration() / 1000) % 60 + " s");
				System.out.println("Format: " + m.getFormat());
				System.out.println("Bitrate: " + m.getAudio().getBitRate() / 1000 + "kbps");
				System.out.println("Sampling rate: " + (double)m.getAudio().getSamplingRate() / 1000 + "kHz");
				System.out.println("Channels: " + m.getAudio().getChannels());
				System.out.println("Decoder: " + m.getAudio().getDecoder());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
		
	}
}
