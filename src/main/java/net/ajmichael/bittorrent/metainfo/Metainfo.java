package net.ajmichael.bittorrent.metainfo;

import java.util.List;
import java.util.Optional;

/** All the data about a torrent that is extracted from either a magnet link or a .torrent file. */
public final class Metainfo {
  public final String announce;

  // Fields that are in magnet URIs.
  public final String infohash;
  public final Optional<String> displayName;
  public final Iterable<String> trackers;

  // Fields that are in .torrent files.
  public InfoDictionary infoDictionary;
  // public Optional<List<String>> announceList; --> trackers
  public Optional<Integer> creationDate;
  public Optional<String> comment;
  public Optional<String> createdBy;
  public Optional<String> encoding;

  // I made this for magnet URI.
  public Metainfo(Iterable<String> trackers, String infohash, Optional<String> displayName) {
    this(trackers.iterator().next(), infohash, displayName, trackers);
  }

  // I made this for magnet URI.
  public Metainfo(
      String announce, String infohash, Optional<String> displayName, Iterable<String> trackers) {
    this.announce = announce;
    this.displayName = displayName;
    this.infohash = infohash;
    this.trackers = trackers;
  }

  // I made this for metadata.
  public Metainfo(String announce,
                  List<String> trackers,
                  InfoDictionary infoDictionary,
                  Optional<Integer> creationDate,
                  Optional<String> comment,
                  Optional<String> createdBy,
                  Optional<String> encoding) {
    this.announce = announce;
    this.trackers = trackers;
    this.infoDictionary = infoDictionary;
    this.infohash = null;   // TODO(aj-michael) This is wrong. Fix it.
    this.creationDate = creationDate;
    this.comment = comment;
    this.createdBy = createdBy;
    this.encoding = encoding;
    this.displayName = Optional.of(infoDictionary.name);
  }
}
