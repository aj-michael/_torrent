package net.ajmichael.bittorrent;

interface BlockRequestHandler {
  void respond(BlockRequest incomingRequest);
}
