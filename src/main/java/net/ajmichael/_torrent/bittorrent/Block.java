package net.ajmichael._torrent.bittorrent;

/**
 * An immutable unit of data in the Bittorrent protocol. It knows its location in the file that it
 * belongs and it can contain data or be empty.
 */
final class Block {
  private final int offset;
  private final int length;
  private final byte[] bytes;

  Block(int offset, int length) {
    this.offset = offset;
    this.length = length;
    this.bytes = null;
  }

  Block(int offset, int length, byte[] bytes) {
    this.offset = offset;
    this.length = length;
    this.bytes = bytes;
  }

  /** The actual data of the block. Null if the block data is not present. */
  byte[] getBytes() {
    return bytes;
  }

  /**
   * The length of the block in bytes. Most blocks should be same length, but the last block might
   * be shorter.
   */
  int getLength() {
    return length;
  }

  /** The offset of the block within the torrented file in bytes. */
  int getOffset() {
    return offset;
  }

  /** True if this block contains the actual block data. */
  boolean isEmpty() {
    return bytes == null;
  }
}
