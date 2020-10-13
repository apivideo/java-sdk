package video.api.java.sdk.infrastructure.unirest.request;

import kong.unirest.HttpRequest;
import kong.unirest.HttpRequestWithBody;
import kong.unirest.MultipartBody;
import video.api.java.sdk.domain.video.UploadProgressListener;

import java.io.InputStream;

public class InputStreamBodyBuilder implements BodyBuilder {
    private final String                 filename;
    private final InputStream            inputStream;
    private final int                    chunkCount;
    private final int                    chunkNum;
    private final UploadProgressListener progressListener;

    public InputStreamBodyBuilder(String filename, InputStream inputStream, int chunkCount, int chunkNum, UploadProgressListener progressListener) {
        this.filename         = filename;
        this.inputStream      = inputStream;
        this.chunkCount       = chunkCount;
        this.chunkNum         = chunkNum;
        this.progressListener = progressListener;
    }

    @Override
    public HttpRequest build(HttpRequestWithBody request) {
        MultipartBody body = request.field("file", inputStream, filename);

        if (progressListener != null) {
            body.uploadMonitor(
                    (field, fileName, bytesWritten, totalBytes) -> progressListener.onProgress(bytesWritten, totalBytes, chunkCount, chunkNum)
            );
        }

        return body;
    }
}
