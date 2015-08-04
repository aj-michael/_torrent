package net.ajmichael.bittorrent.metadata;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;

import net.ajmichael.bittorrent.metadata.Metadata;
import net.ajmichael.bittorrent.metadata.TorrentMetadataFile;

@RunWith(JUnit4.class)
public class TorrentMetadataFileTest {

  @Test
  public void testTrackers() {
    String fileName = "ubuntu-15.04-desktop-amd64.iso.torrent";
    Metadata metadata = new TorrentMetadataFile(new File(fileName)).parse();
    ImmutableCollection<String> expectedTrackers =
        ImmutableList.of(
            "udp://tracker.openbittorrent.com:80",
            "udp://open.demonii.com:1337",
            "udp://tracker.coppersurfer.tk:6969",
            "udp://exodus.desync.com:6969");
    assertThat(metadata.trackers, is(expectedTrackers));
  }
}
