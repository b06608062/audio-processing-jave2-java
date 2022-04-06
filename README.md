# Interenet Programming Final Report
Topic: **Audio Processing with Java**  
Team: 13  
Team Members: 40947021S 謝皓青, 40947006S 郭浩雲, NTU_B06608062 游竣量  

Work Division

- Program developing: 游竣量 80%, 郭浩雲 10%, 謝皓青 10%
- Report: 郭浩雲 65%, 謝皓青 35%

## Goals
- Format convert
- Get information and metadata of audio file
- Audio trimming
- Audio recompose

## Library we used

### JAVE2
We use `JAVE2`, Java Audio Video Encoder 2, for our project. `JAVE2` is [open-sourced on GitHub](https://github.com/a-schild/jave2).  
This library is actually a `ffmpeg` wrapper for Java. Both a simple way and an advanced way to manipulate `ffmpeg` is provided by `JAVE2`, which we will demonstrate in later paragraphs.

 ### ffmpeg
![This is an image](https://i.imgur.com/hemyJFy.png "FFmpeg logo")  
FFmpeg is a free and open-source software project for handling video, audio. Its core is the FFmpeg program itself, designed for command-line-based processing of video and audio files.  
It is widely used for **format transcoding**, **basic editing**, **video scaling**, **video post-production effects** and **standards compliance**.
