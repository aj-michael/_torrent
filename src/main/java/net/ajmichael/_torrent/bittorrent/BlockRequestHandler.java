package net.ajmichael._torrent.bittorrent;

interface BlockRequestHandler {
  void respond(BlockRequest incomingRequest);
}
