package video.api.java.sdk.domain.video;

public interface UploadProgressListener {

    void onProgress(Long bytesWritten, Long totalBytes, int chunkCount, int chunkNum);

}
