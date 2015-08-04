package net.ajmichael.bittorrent.metainfo;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

import net.ajmichael.common.lang.NotImplementedException;

/**
 * Implementation of BEP9, primarily to support TPB.
 * 
 * @see <a href="http://www.bittorrent.org/beps/bep_0009.html">BEP9</a>
 */
public class MagnetUri {
  private static final Logger logger = LoggerFactory.getLogger(MagnetUri.class);

  private static final String DEFAULT_CHARSET = Charsets.UTF_8.name();
  private static final String MAGNET_SCHEME = "magnet";
  private static final String INFOHASH_PREFIX = "urn:btih:";

  // The following fields are required.
  private static final String EXACT_TOPIC_FIELD = "xt";

  // The following fields are optional.
  private static final String TRACKER_FIELD = "tr";
  private static final String DISPLAY_NAME_FIELD = "dn";

  public final Optional<String> displayName;
  public final String exactTopic;
  public final String infohash;
  public final ImmutableCollection<String> trackers;

  public MagnetUri(String magnetUri) throws UnsupportedEncodingException {
    this(URI.create(magnetUri));
  }

  public MagnetUri(URI magnetUri) throws UnsupportedEncodingException {
    Preconditions.checkArgument(magnetUri.getScheme().equals(MAGNET_SCHEME));
    ImmutableMultimap<String, String> content =
        decodeMagnetSpecificPart(magnetUri.getSchemeSpecificPart());
    this.exactTopic = getRequiredField(content, EXACT_TOPIC_FIELD);
    this.displayName = getOptionalField(content, DISPLAY_NAME_FIELD);
    this.infohash = this.exactTopic.substring(INFOHASH_PREFIX.length());
    this.trackers = content.get(TRACKER_FIELD);
    logger.info("Constructed TorrentMagnetUri from uri {}.", magnetUri);
  }

  /**
   * Constructs a TorrentMetadata object from the infohash contents of the magnet uri that is
   * consumable by the Bittorrent API.
   * @throws UnsupportedEncodingException 
   */
  public Object parse() throws UnsupportedEncodingException {
    throw new NotImplementedException();
  }

  private ImmutableMultimap<String, String> decodeMagnetSpecificPart(String magnetSpecificPart)
      throws UnsupportedEncodingException {
    Preconditions.checkArgument(magnetSpecificPart.startsWith("?"));
    ImmutableMultimap.Builder<String, String> map = ImmutableMultimap.builder();
    // Unfortunately, Guava only provides MapSplitter, no MultimapSplitter. See Guava issue #1004.
    for (String entry : Splitter.on('&').split(magnetSpecificPart.substring(1))) {
      String[] keyAndValue = entry.split("=");
      String key = keyAndValue[0];
      String value = keyAndValue[1];
      map.put(URLDecoder.decode(key, DEFAULT_CHARSET), URLDecoder.decode(value, DEFAULT_CHARSET));
    }
    return map.build();
  }

  private static Optional<String> getOptionalField(Multimap<String, String> mmap, String field) {
    Iterator<String> values = mmap.get(field).iterator();
    if (!values.hasNext()) {
      return Optional.empty();
    }
    String result = values.next();
    if (values.hasNext()) {
      logger.warn("Multiple values were found for field: " + field + ". Only one was used.");
    }
    return Optional.of(result);
  }

  private static String getRequiredField(Multimap<String, String> mmap, String field) {
    Optional<String> possibleResult = getOptionalField(mmap, field);
    if (!possibleResult.isPresent()) {
      throw new IllegalArgumentException("Magnet uri missing required field: " + field + ".");
    }
    return possibleResult.get();
  }
}
