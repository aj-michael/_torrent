package net.ajmichael.bittorrent;

/**
 * A function for selecting the next block to download, given the current state of downloaded and
 * remaining blocks.
 */
interface BlockSelectorAlgorithm {
  Block nextBlock();
}
