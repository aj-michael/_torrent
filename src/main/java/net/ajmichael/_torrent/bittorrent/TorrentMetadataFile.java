package net.ajmichael._torrent.bittorrent;

import java.io.File;

import net.ajmichael._torrent.bittorrent.metadata.Metadata;
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
