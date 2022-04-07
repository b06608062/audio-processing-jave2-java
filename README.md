# Interenet Programming Final Report
Topic: **Audio Processing with Java**  
Team: **13**  
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
FFmpeg is a free and open-source software project for handling video, audio. Its core is the FFmpeg program itself, designed for command-line-based processing of video and audio files. It is widely used for **format transcoding**, **basic editing**, **video scaling**, **video post-production effects** and **standards compliance**.

## Integrate with project
To use `JAVE2` in our project, it provides 2 approaches being `Maven` and `Gradle` respectively. In our case, we decide to use `Maven`. `Maven` is an automation building system for Java applications, which is backed by Apache Foundation. `Maven` helps solving dependency problems and managing the directory structure. `Eclipse` IDE comes with `Maven` on installation.

### Create a Maven Project
![This is an image](https://i.imgur.com/feXfufl.png)  
To create a Maven project in Eclipse, we use File->New->Projects

![This is an image](https://i.imgur.com/zAyuJHN.png)   
Then we select Maven Project.

![This is an image](https://i.imgur.com/TLBMScQ.png)  
Here just go default.

![This is an image](https://i.imgur.com/2UI3wAS.png)  
`Maven` uses Archetype to manage the directory structure of project, just like the blueprint for construction. Here we uses `maven-archetype-quickstart` in order to create a simple Maven Project with the minimal necessary files.

![This is an image](https://i.imgur.com/wc69PnB.png)  
Specifying the project id is required. The format resembles a reversed domain name.

### Add [JAVE2](https://github.com/a-schild/jave2) dependency
Our newly created Maven project comes with a special file `pom.xml`. This is how `Maven` understands the dependency, be sure this file stays safe.

Follow the official guide provided by `JAVE2`, we need to add these information into the `pom.xml`.
```xml
<dependencies>
<!--     Approach #1: All dependency included -->
    <dependency>    
        <groupId>ws.schild</groupId>
        <artifactId>jave-all-deps</artifactId>
        <version>3.1.1</version>
    </dependency>
<!--     Approach #2: Core + Target platform package -->
    <dependency>
        <groupId>ws.schild</groupId>
        <artifactId>jave-core</artifactId>
        <version>3.1.1</version>
    </dependency>
    <dependency>
        <groupId>ws.schild</groupId>
        <artifactId>jave-nativebin-win64</artifactId>
        <version>3.1.1</version>
    </dependency>
</dependencies>
```

Moreover we have to solve `slf4j` dependency.
```xml
<dependencies>
<!-- handling logging -->
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-simple</artifactId>
        <version>1.7.25</version>
    </dependency>
</dependencies>
```

After finishing the configuration of `pom.xml`, `Maven` should be downloading packages from the central repository and handling the rest for us.

## Example
**Source File:**
**`/src/main/java/org/ntnu/MP3Editor/FormatConverter.java`**

**Task:**
Convert a `.wav` file to `.mp3`.

We will specify the codec attributes in order to convert the file, so we should understand some basic concepts first.

### Bitrate

|      | Traditional Chinese (TW) | Simplified Chinese |
| ---- |:------------------------:|:------------------:|
| Term |         位元速率          |        码率         |

*Sampling Rate* * *bit depth* * *Channels* = *Bitrate*

Bitrate is important for describing how many bits are used to store the data in a single second. For some audio enthusiasts, they are eager to get the music file that have higher bitrates because they believe that it's less compressed and more detailed. However bitrate doesn't absolutely guarantee sound quality because an audio file with high bitrate can be made from the source file with a lower one. What does it mean? In a simple word, it can be padded by useless data. An 8-bit integer can be padded with zeros to a 32-bit one but the actual data doesn't change, right?

There are some common bitrates: 128 Kbps,160 Kbps, 320 Kbps, etc.

### Sampling Frequency

![This is an image](https://math.libretexts.org/@api/deki/files/4515/clipboard_eeb1d3a4750bf5c657b34fad8ecf5a98d.png)
![This is an image](https://tomsrayaudiomastering.com/wp-content/uploads/2017/03/bit-depth-OPT.jpg)

Still remember the Riemann Sum in Calculus? The more partition we have, the more accurate approximated area we get.  
The concept of sampling frequency resembles to that. When we sample the audio more frequently, the result is closer to the real information.

### Channels

![](https://cdn.pianodreamers.com/wp-content/uploads/2020/01/stereo-vs-mono-illustration.png)
We may know the term "stereo sound" can bring us a more realistic listening experience. That's because both the left and right channels contains different sound signal, so we may feel the sound is from different directions. When an audio file only have single signal track, we call it "mono channel".
