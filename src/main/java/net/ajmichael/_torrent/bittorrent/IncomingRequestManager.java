package net.ajmichael._torrent.bittorrent;

/** Responds to all incoming requests specific to a running torrent. */
final class IncomingRequestManager {
  private final TorrentContent torrentContent;

  IncomingRequestManager(TorrentContent torrentContent) {
    this.torrentContent = torrentContent;
  }
}
