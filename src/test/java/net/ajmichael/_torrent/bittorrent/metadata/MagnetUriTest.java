package net.ajmichael._torrent.bittorrent.metadata;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;

import net.ajmichael.common.lang.NotImplementedException;

@RunWith(JUnit4.class)
public class MagnetUriTest {
  private static final String MANY_TRACKERS_URI =
      "magnet:?xt=urn:btih:484c9cda1edde496e79415cf1a6b1906326afb94&dn=Spaceballs+%281987%29+720p+BrRip+x264+-+600MB+-+YIFY+&tr=udp%3A%2F%2Ftracker.openbittorrent.com%3A80&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969";
  private static final String MISSING_DISPLAY_NAME_URI =
      "magnet:?xt=urn:btih:484c9cda1edde496e79415cf1a6b1906326afb94&tr=udp%3A%2F%2Ftracker.openbittorrent.com%3A80&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969";
  private static final String NO_TRACKERS_URI =
      "magnet:?xt=urn:btih:484c9cda1edde496e79415cf1a6b1906326afb94&dn=Spaceballs+%281987%29+720p+BrRip+x264+-+600MB+-+YIFY+";
  private static final String PRESENT_DISPLAY_NAME_URI =
      "magnet:?xt=urn:btih:484c9cda1edde496e79415cf1a6b1906326afb94&dn=Spaceballs+%281987%29+720p+BrRip+x264+-+600MB+-+YIFY+&tr=udp%3A%2F%2Ftracker.openbittorrent.com%3A80&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969";

  @Test
  public void testParse() throws UnsupportedEncodingException {
    throw new NotImplementedException();
  }

  @Test
  public void testNoTrackers() throws UnsupportedEncodingException {
    MagnetUri magnet = new MagnetUri(NO_TRACKERS_URI);
    assertThat(magnet.trackers, is(empty()));
  }

  @Test
  public void testManyTrackers() throws UnsupportedEncodingException {
    MagnetUri magnet = new MagnetUri(MANY_TRACKERS_URI);
    ImmutableCollection<String> expected =
        ImmutableList.of(
            "udp://tracker.openbittorrent.com:80",
            "udp://open.demonii.com:1337",
            "udp://tracker.coppersurfer.tk:6969",
            "udp://exodus.desync.com:6969");
    assertThat(magnet.trackers, is(expected));
  }

  @Test
  public void testMissingDisplayName() throws UnsupportedEncodingException {
    MagnetUri magnet = new MagnetUri(MISSING_DISPLAY_NAME_URI);
    assertFalse(magnet.displayName.isPresent());
  }

  @Test
  public void testPresentDisplayName() throws UnsupportedEncodingException {
    MagnetUri magnet = new MagnetUri(PRESENT_DISPLAY_NAME_URI);
    String displayName = "Spaceballs (1987) 720p BrRip x264 - 600MB - YIFY ";
    assertTrue(magnet.displayName.isPresent());
    assertThat(magnet.displayName.get(), is(displayName));
  }
}
