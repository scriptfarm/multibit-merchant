package org.multibit.mbm.test;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import org.multibit.mbm.api.hal.HalMediaType;
import org.multibit.mbm.db.dto.User;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * A base test class for testing resources against a simulated container
 */
public abstract class BaseResourceTest {
  protected UriInfo uriInfo;
  protected HttpHeaders httpHeaders;
  protected Optional<User> principal=Optional.absent();

  /**
   * @param baseHref Optional with default of "http://example.org"
   * @throws Exception If the URI cannot be constructed
   */
  protected void setUpUriInfo(Optional<String> baseHref) throws Exception {
    if (!baseHref.isPresent()) {
      baseHref=Optional.of("http://example.org");
    }
    final URI uri = new URI(baseHref.get());
    uriInfo = mock(UriInfo.class);
    when(uriInfo.getBaseUri()).thenReturn(uri);
  }

  /**
   * @param acceptableMediaTypes An optional list of acceptable media types with default of HAL+JSON
   */
  protected void setUpHttpHeaders(Optional<List<MediaType>> acceptableMediaTypes) {
    if (!acceptableMediaTypes.isPresent()) {
      List<MediaType> defaultAcceptableMediaTypes= Lists.newArrayList();
      defaultAcceptableMediaTypes.add(HalMediaType.APPLICATION_HAL_JSON_TYPE);
      acceptableMediaTypes=Optional.of(defaultAcceptableMediaTypes);
    }
    httpHeaders = mock(HttpHeaders.class);
    when(httpHeaders.getAcceptableMediaTypes()).thenReturn(acceptableMediaTypes.get());
  }

  /**
   * @param principal An optional {@link User} to be the security principal with a default of absent
   */
  protected void setUpPrincipal(Optional<User> principal) {
    this.principal=principal;
  }

}