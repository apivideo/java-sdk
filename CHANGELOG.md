# Changelog
All notable changes to this project will be documented in this file.


## [0.6.0] - 2020-10-15

### Changed
- Replaced JSONObject.has() with !JSONObject.isNull()
- Allow objects in metadata values
- TODO refresh token

### Fixed
- Fixed missing panoramic attribute in video serializer
- Fixed missing source attribute in video serializer


## [0.5.5] - 2020-10-13

### Changed
- Upgraded to Unirest v3. Dependencies on JSONObject and JSONArray have been updated. [More info here](https://github.com/Kong/unirest-java/blob/main/UPGRADE_GUIDE.md)
- Large file upload (> 128MB) no longer creates temporary files. Reads a custom FileInputStream instead.


## [0.5.4] - 2020-07-31

### Added
- QueryParams `liveStreamId` filter


## [0.5.3] - 2020-06-25

### Added
- Video `mp4Support` property defaulting to true

### Fixed
- Missing `source.uri` in video response throws exception

## [0.5.2] - 2020-01-27

### Fixed
- Valid `QueryParams.sortBy` parameter is not in query string (`publishedAt`)
- `QueryParams.sortOrder` parameter is not in query string

## [0.5.1] - 2020-01-23

### Removed
- `Player.language` unsupported API property


## [0.5.0] - 2020-01-21

### Added
- `Client.account.get()` method (https://docs.api.video/5.1/account)


## [0.4.0] - 2020-01-17

### Added
- `Client.players.deleteLogo()` method (https://docs.api.video/5.1/players/delete-logo)
- `Client.chapters(videoId)` subclient (https://docs.api.video/5.1/chapters)


## [0.3.0] - 2019-12-17

### Added
- `Video.updatedAt` property

### Changed
- `Video.publishedAt` property type from `String` to `Calendar`
- `Info.loadedAt` property type from `String` to `Calendar`
- `Info.endedAt` property type from `String` to `Calendar`
- `PlayerSessionEvent.emittedAt` property type from `String` to `Calendar`
- `Client.videos.upload(String sourceInfo)` -> `Client.videos.upload(File file)`
- `Client.videos.uploadThumbnail(Video video, String thumbnailSource)` -> `Client.videos.uploadThumbnail(Video video, File file)`
- `Client.videos.uploadThumbnailWithTimecode(Video video, String timecode)` -> `Client.videos.uploadThumbnail(Video video, String timecode)`
- `Client.liveStreams.uploadThumbnail(String liveStreamId, String thumbnailSource)` -> `Client.liveStreams.uploadThumbnail(String liveStreamId, File file)`
- `Client.liveStreams.create(LiveStream liveStream)` -> `Client.liveStreams.create(LiveStreamInput liveStreamInput)`
- `Client.players.uploadLogo(String playerId, String logoSource, String link)` -> `Client.players.uploadLogo(String playerId, File file, String link)`
- `Client.captions.upload(String VideoId, String captionSource, String lang)` -> `Client.captions.upload(String VideoId, File file, String lang)`  
- `Client.videos.uploadThumbnail(Video video, File file)` -> `Client.videos.uploadThumbnail(Identifier videoId, File file)`
- `Client.videos.updateThumbnail(Video video, String timecode)` -> `Client.videos.updateThumbnail(Identifier videoId, String timecode)`
- `Client.videos.delete(Video video)` -> `Client.videos.delete(Identifier videoId)`
- `Client.captions.updateDefault(String VideoId, String lang, boolean isDefault)` -> `Client.captions.update(String videoId, CaptionInput captionInput)`
- `Client.captions.getAll(String VideoId)` -> `Client.captions.list(String videoId)`


## [0.2.0] - 2019-09-20

### Changed
- Move UploadProgressListener to domain
- Rename sub-client interfaces to match implementations
- Use interfaces in Client
- Replace PageIterator references with Iterator in domain
- Replace Iterator with Iterable in domain interfaces
