package net.ajmichael._torrent.bittorrent.metadata;

import java.io.File;

import net.ajmichael.common.lang.NotImplementedException;

/** TODO(aj-michael) Document this. */
public class TorrentMetadataFile {
  private final File metadataFile;
  private Metadata parsedMetadata = null;

  public TorrentMetadataFile(File metadataFile) {
    this.metadataFile = metadataFile;
  }

  Metadata parse() {
    if (parsedMetadata == null) {
      throw new NotImplementedException();
    }
    return parsedMetadata;
  }
}
