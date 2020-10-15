package video.api.java.sdk.domain.caption;

import video.api.java.sdk.domain.exception.ResponseException;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public interface CaptionClient {

    Caption get(String videoId, Locale language) throws ResponseException;

    Iterable<Caption> list(String videoId) throws ResponseException;

    Caption upload(String videoId, File file, Locale language) throws ResponseException, IOException;

    Caption update(String videoId, CaptionInput captionInput) throws ResponseException;

    void delete(String videoId, Locale language) throws ResponseException;

}
