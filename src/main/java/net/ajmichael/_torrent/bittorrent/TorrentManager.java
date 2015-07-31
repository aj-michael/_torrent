package net.ajmichael._torrent.bittorrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.ajmichael._torrent.bittorrent.metadata.Metadata;
import net.ajmichael._torrent.bittorrent.tracker.Tracker;
import net.ajmichael._torrent.bittorrent.tracker.TrackerFactory;

/**
 * Primary driver for a specific torrent. Drives all phases of tracker communication, downloading
 * and uploading.
 */
public final class TorrentManager implements Runnable {
  private static final Logger logger = LoggerFactory.getLogger(TorrentManager.class);
  
  private final Metadata metadata;
  private final Tracker tracker;

  public TorrentManager(Metadata metadata) {
    logger.info("Constructed TorrentManager with metadata {}.", metadata);
    this.metadata = metadata;
    this.tracker = new TrackerFactory(metadata).create();
  }

  @Override
  public void run() {
    
  }

}
