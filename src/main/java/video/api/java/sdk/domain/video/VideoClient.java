package video.api.java.sdk.domain.video;

import video.api.java.sdk.domain.QueryParams;
import video.api.java.sdk.domain.exception.ResponseException;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public interface VideoClient {

    Iterable<UploadedVideo> list() throws ResponseException;

    Iterable<UploadedVideo> list(QueryParams queryParams) throws ResponseException, URISyntaxException;

    UploadedVideo get(String videoId) throws ResponseException;

    Status getStatus(String videoId) throws ResponseException;

    UploadedVideo createVideoContainer(Video video) throws ResponseException;

    UploadedVideo upload(File file) throws ResponseException;

    UploadedVideo upload(File file, Video videoInput) throws ResponseException;

    UploadedVideo upload(File file, UploadProgressListener m) throws ResponseException;

    UploadedVideo upload(File file, Video video, UploadProgressListener m) throws ResponseException;

    UploadedVideo uploadThumbnail(String videoId, File file) throws ResponseException, IOException;

    UploadedVideo updateThumbnail(String videoId, String timecode) throws ResponseException;

    UploadedVideo update(UploadedVideo video) throws ResponseException;

    void delete(String videoId) throws ResponseException;
}
