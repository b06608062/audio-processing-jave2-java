package org.ntnu.MP3Editor;

import java.io.File;
import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderException;
import ws.schild.jave.InputFormatException;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;
import ws.schild.jave.info.MultimediaInfo;

public class AudioTrimming {

	public static void main(String[] args) throws IllegalArgumentException, InputFormatException, EncoderException {
		File source = new File("test.mp3");
		File target = new File("cut.mp3");
		
		MultimediaObject srcMultiObj = new MultimediaObject(source);
		MultimediaInfo srcMediaInfo = srcMultiObj.getInfo();
				
		AudioAttributes audio = new AudioAttributes();
		audio.setBitRate(srcMediaInfo.getAudio().getBitRate());
		audio.setSamplingRate(srcMediaInfo.getAudio().getSamplingRate());
		audio.setChannels(srcMediaInfo.getAudio().getChannels());
		
		EncodingAttributes encodingAttributes = new EncodingAttributes();
		encodingAttributes.setOffset(60.0F);
		encodingAttributes.setDuration(60.0F);
		encodingAttributes.setInputFormat("mp3");
		encodingAttributes.setAudioAttributes(audio);
		
		Encoder encoder = new Encoder();
		encoder.encode(srcMultiObj, target, encodingAttributes);
		
		System.out.println("Done!");
	}
}
