# API.video java-sdk

This SDK provides a Java client for [api.video](https://api.video/) service.
This is an early version, feel free to report any issue.

# Install

## With Gradle

1. Download the [latest release](https://github.com/apivideo/java-sdk/releases).
2. Add the following dependencies in your `build.gradle` file:

```gradle
// build.gradle
dependencies {
    implementation 'com.konghq:unirest-java:2.3.17'
    implementation files('libs/java-sdk-0.2.0.jar')
}
``` 

# Quick start

```java
import java.io.File;
import video.api.java.sdk.Client;
import video.api.java.sdk.ClientFactory;
import video.api.java.sdk.domain.exception.ResponseException;
import video.api.java.sdk.domain.video.UploadedVideo;

public class Main {
    public static void main(String[] args) throws ResponseException {
        Client client = new ClientFactory().create("YourProductionApiKey");
        // Client client = new ClientFactory().createSandbox("YourSandboxApiKey");
    
        // Upload a new video
        Video video = client.videos.upload(new File("/path/to/file.mp4"));

        // Get its embed code 
        String embedCode = video.assets.get("iframe"); // <iframe src="..."></iframe>
    }
}
```

# More examples
```java
// Upload a video with properties
VideoInput videoInput = new VideoInput();
videoInput.title = "My title";
videoInput.tags.add("my tag");
videoInput = client.videos.upload(new File("/path/to/file.mp4"), videoInput);

// Iterate over videos (paging is handled by the client)
for (Video v : client.videos.list()) {
    String videoTitle = v.title;
}
```

# API coverage
Most of _api.video_ features are implemented and autocompleted within your favorite IDE:

```java
client.videos; // https://docs.api.video/5.1/videos
client.players; // https://docs.api.video/5.1/players
client.captions; // https://docs.api.video/5.1/captions
client.liveStreams; // https://docs.api.video/5.1/live
client.chapters(videoId); // https://docs.api.video/5.1/chapters

// https://docs.api.video/5.1/analytics
client.liveStreamAnalytics; 
client.sessionEventAnalytics; 
client.videoAnalytics;
```
