package net.ajmichael.bittorrent;

/**
 * Handles all interaction with a specific peer for the lifetime of a torrent. It initiates the
 * connection and implements the Bittorrent protocol for requesting blocks.
 */
final class PeerDownloadManager {
  private final PeerInfo peerInfo;

  PeerDownloadManager(PeerInfo peerInfo) {
    this.peerInfo = peerInfo;
  }
}
