package org.ntnu.MP3Editor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import ws.schild.jave.process.ProcessWrapper;
import ws.schild.jave.process.ffmpeg.DefaultFFMPEGLocator;

public class AudioRecompose {

	public static void main(String[] args) throws IOException {
		String source = "test.mp3";
		String target = "repeat.mp3";
		
		int times = 2;
		DefaultFFMPEGLocator locator = new DefaultFFMPEGLocator();
		ProcessWrapper ffmpeg = locator.createExecutor();
		for (int i = 1; i <= times; i++) {
			ffmpeg.addArgument("-i");
			ffmpeg.addArgument(source);
		}
		ffmpeg.addArgument("-filter_complex");
		ffmpeg.addArgument("[0:0] [1:0] concat=n=" + times + ":v=0:a=1 [a]");
		ffmpeg.addArgument("-map");
		ffmpeg.addArgument("[a]");
		ffmpeg.addArgument(target);
		ffmpeg.execute();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(ffmpeg.getErrorStream()));
		String line;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		
	}
}
