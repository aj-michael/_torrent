package net.ajmichael.bittorrent.metainfo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;

import net.ajmichael.bittorrent.metainfo.Metainfo;
import net.ajmichael.bittorrent.metainfo.TorrentMetainfoFile;

@RunWith(JUnit4.class)
public class TorrentMetainfoFileTest {

  @Test
  public void testTrackers() throws IOException, MetainfoParseError {
    String fileName = "ubuntu-15.04-desktop-amd64.iso.torrent";
    File torrentFile = new File(this.getClass().getResource(fileName).getFile());
    Metainfo metadata = new TorrentMetainfoFile(torrentFile).parse();
    ImmutableCollection<String> expectedTrackers =
        ImmutableList.of(
            "http://torrent.ubuntu.com:6969/announce",
            "http://ipv6.torrent.ubuntu.com:6969/announce");
    assertThat(metadata.trackers, is(expectedTrackers));
  }
}
