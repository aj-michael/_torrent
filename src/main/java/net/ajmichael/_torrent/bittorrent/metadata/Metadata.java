package net.ajmichael._torrent.bittorrent.metadata;

import java.util.Optional;

/** All the data about a torrent that is extracted from either a magnet link or a .torrent file. */
public final class Metadata {
  public final String announce;
  
  // Fields that are in magnet URIs.
  public final String infohash;
  public final Optional<String> displayName;
  public final Iterable<String> trackers;
  
  // Fields that are in .torrent files.
  public InfoDictionary infoDictionary;

  public Metadata(Iterable<String> trackers, String infohash, Optional<String> displayName) {
    this(trackers.iterator().next(), infohash, displayName, trackers);
  }

  public Metadata(
      String announce, String infohash, Optional<String> displayName, Iterable<String> trackers) {
    this.announce = announce;
    this.displayName = displayName;
    this.infohash = infohash;
    this.trackers = trackers;
  }
}
