package net.ajmichael._torrent.bittorrent;

import java.util.concurrent.Future;

/** TODO(aj-michael) Document this. */
public interface Tracker {
  /** TODO(aj-michael) Document this. */
  Future<TrackerConnectResponse> connect();

  /** TODO(aj-michael) Document this. */
  Future<TrackerAnnounceResponse> announce();
  
  /** TODO(aj-michael) Document this. */
  Future<TrackerScrapeResponse> scrape();
}
