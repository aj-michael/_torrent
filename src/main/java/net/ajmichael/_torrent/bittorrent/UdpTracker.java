package net.ajmichael._torrent.bittorrent;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.ajmichael._torrent.bittorrent.metadata.Metadata;
import net.ajmichael._torrent.bittorrent.metadata.TorrentMetadata;

/** TODO(aj-michael) Document this. */
class UdpTracker implements Tracker {
  private static final Logger logger = LoggerFactory.getLogger(UdpTracker.class);

  private final Metadata metadata;

  UdpTracker(Metadata metadata) {
    logger.info("Constructed UdpTracker with metadata {}.", metadata);
    this.metadata = metadata;
  }

  @Override
  public Future<TrackerConnectResponse> connect() {
    return null;
  }

  @Override
  public Future<TrackerAnnounceResponse> announce() {
    return null;
  }

  @Override
  public Future<TrackerScrapeResponse> scrape() {
    return null;
  }
}
