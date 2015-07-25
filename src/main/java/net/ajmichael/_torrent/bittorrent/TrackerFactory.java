package net.ajmichael._torrent.bittorrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.ajmichael._torrent.bittorrent.metadata.Metadata;

class TrackerFactory {
  private static final Logger logger = LoggerFactory.getLogger(TrackerFactory.class);

  private final Metadata metadata;

  TrackerFactory(Metadata metadata) {
    logger.info("Constructed TrackerFactory with metadata {}.", metadata);
    this.metadata = metadata;
  }

  Tracker create() {
    return new UdpTracker(this.metadata);
  }
}
