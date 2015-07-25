package net.ajmichael._torrent.bittorrent;

import java.util.List;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

/** The blocks of the torrent file. There should be exactly one per torrent. */
class TorrentContent {
  private final List<Block> blocks;
  
  TorrentContent() {
    // Placeholder so that the code compiles.
    blocks = ImmutableList.of();
  }

  /** Returns a snapshot of the current status of the blocks of the torrent. */
  ImmutableList<Block> getBlocks() {
    return ImmutableList.copyOf(blocks);
  }

  /** Adds a non-empty block to the torrent content. */
  synchronized boolean addBlock(Block b) {
    Preconditions.checkArgument(!b.isEmpty());
    return false;
  }
}
